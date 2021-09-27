package com.spaz.backend.service;

import com.spaz.backend.servicedto.UsuarioRequest;
import com.spaz.backend.servicedto.UsuarioResponse;

import io.reactivex.Single;

public interface UsuarioService {
	
	Single<UsuarioResponse> getUsuario(Long idUsuario);
	
	Single<UsuarioResponse> createUsuario(UsuarioRequest usuarioRequest);
}
