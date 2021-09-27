package com.spaz.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spaz.backend.entity.Nota;
import com.spaz.backend.entity.Usuario;
import com.spaz.backend.repositorio.UsuarioRepositorio;
import com.spaz.backend.servicedto.UsuarioRequest;
import com.spaz.backend.servicedto.UsuarioResponse;

import io.reactivex.Single;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	UsuarioRepositorio usuarioRepositorio;
	
	@Override
	public Single<UsuarioResponse> getUsuario(Long idUsuario)
	{
		return Single.create(singleSubscriber -> {
			UsuarioResponse usuarioResponse = new UsuarioResponse();
			Usuario usuario = usuarioRepositorio.findById(idUsuario).get();
			System.out.print("usuario" + usuario.getUsuario() );
			
			usuarioResponse.setUsuario(usuario);
			
            singleSubscriber.onSuccess(usuarioResponse);
            });
	}
	
	@Override
	public Single<UsuarioResponse> createUsuario(UsuarioRequest usuarioRequest)
	{
		return Single.create(singleSubscriber -> {
			UsuarioResponse usuarioResponse = new UsuarioResponse();
			Usuario usuario = usuarioRepositorio.save(usuarioRequest.getUsuario());
			usuarioResponse.setUsuario(usuario);
			
            singleSubscriber.onSuccess(usuarioResponse);
            });
	}
	
	@Override
	public boolean validarUsuario(String usuario, String password)
	{
		Usuario usuari = usuarioRepositorio.findByUsuarioAndPassword(usuario,password);
		return usuari!=null ? true:false;
	}
}
