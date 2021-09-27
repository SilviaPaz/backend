package com.spaz.backend.controller;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spaz.backend.dto.Usuario;
import com.spaz.backend.service.UsuarioService;
import com.spaz.backend.servicedto.BaseWebResponse;
import com.spaz.backend.servicedto.NotaRequest;
import com.spaz.backend.servicedto.UsuarioRequest;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

@RestController
public class UsuarioControlador {

	@Autowired
	UsuarioService usuarioService;
	
	@PostMapping("token")
	public Usuario logeo(@RequestParam("usuario") String username, @RequestParam("password") String password) {
		
		String token = getJWTToken(username);
		Usuario user = new Usuario();
		user.setUsuario(username);
		user.setPassword(password);
		user.setToken(token);	
		return user;
		
	}

	private String getJWTToken(String username) {
		//aqui autenticacion de bd
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		String token = Jwts
				.builder()
				.setId("softtekJWT")
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return "Bearer " + token;
	}
	
	@GetMapping( path="/usuarios",	
            produces = MediaType.APPLICATION_JSON_VALUE	
    )	
    public Single<ResponseEntity<BaseWebResponse>> getUsuario(@RequestParam Long idUsuario) {
        return usuarioService.getUsuario(idUsuario)
                .subscribeOn(Schedulers.io())
                .map(s -> ResponseEntity
                        .created(URI.create("/usuarios/" + s))
                        .body(BaseWebResponse.successWithData(s)));
    }
	
	@PostMapping( path="/usuarios",	
			consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE	
    )	
    public Single<ResponseEntity<BaseWebResponse>> createUsuario(@RequestBody UsuarioRequest usuarioRequest) {
        return usuarioService.createUsuario(usuarioRequest)
                .subscribeOn(Schedulers.io())
                .map(s -> ResponseEntity
                        .created(URI.create("/usuarios/" + s))
                        .body(BaseWebResponse.successWithData(s)));
    }
}
