package com.spaz.backend.controller;


import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spaz.backend.exception.ErrorCode;
import com.spaz.backend.service.NotaService;
import com.spaz.backend.servicedto.BaseWebResponse;
import com.spaz.backend.servicedto.NotaRequest;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

@RestController
public class NotaControlador {

	@Autowired
	NotaService notaService;
	
	@GetMapping( path="/notas",	
            produces = MediaType.APPLICATION_JSON_VALUE	
    )	
    public Single<ResponseEntity<BaseWebResponse>> getNota(@RequestParam Long idUsuario) {
        return notaService.getNota(idUsuario)
                .subscribeOn(Schedulers.io())
                .map(s -> ResponseEntity
                        .created(URI.create("/notas/" + s))
                        .body(BaseWebResponse.successWithData(s)));
    }
	
	@PostMapping( path="/notas",	
			consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE	
    )	
    public Single<ResponseEntity<BaseWebResponse>> createNota(@RequestBody NotaRequest notaRequest) {
        return notaService.createNota(notaRequest)
                .subscribeOn(Schedulers.io())
                .map(s -> ResponseEntity
                        .created(URI.create("/notas/" + s))
                        .body(BaseWebResponse.successWithData(s)));
    }
}
