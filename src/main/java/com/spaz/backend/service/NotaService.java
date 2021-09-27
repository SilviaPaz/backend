package com.spaz.backend.service;

import com.spaz.backend.servicedto.NotaRequest;
import com.spaz.backend.servicedto.NotaResponse;
import com.spaz.backend.servicedto.NotasListResponse;

import io.reactivex.Single;

public interface NotaService {
	Single<NotasListResponse> getNota(Long idUsuario);
	
	Single<NotaResponse> createNota(NotaRequest notaRequest);
}