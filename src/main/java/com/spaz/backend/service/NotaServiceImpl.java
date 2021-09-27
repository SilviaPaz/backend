package com.spaz.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spaz.backend.entity.Nota;
import com.spaz.backend.entity.Usuario;
import com.spaz.backend.repositorio.NotaRepositorio;
import com.spaz.backend.repositorio.UsuarioRepositorio;
import com.spaz.backend.servicedto.NotaRequest;
import com.spaz.backend.servicedto.NotaResponse;
import com.spaz.backend.servicedto.NotasListResponse;

import io.reactivex.Maybe;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.Single;

@Service
public class NotaServiceImpl implements NotaService {
	
	@Autowired
	private NotaRepositorio notaRepositorio;
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	@Override
	public Single<NotasListResponse> getNota(Long idUsuario) {
		
		NotaRequest notaRequest = new NotaRequest();
		notaRequest.setIdUsuario(idUsuario);
		
		return Single.create(singleSubscriber -> {
			NotasListResponse notasListResponse = new NotasListResponse();
			Usuario usuario = usuarioRepositorio.findById(idUsuario).get();
			System.out.print("usuario" + usuario.getUsuario() );
			//notaResponse.setUsuario(usuario);
			
			notasListResponse.setNotas(notaRepositorio.findAllByUsuario(usuario));
			
            singleSubscriber.onSuccess(notasListResponse);
            });
	}
	
	/*@Override
	public Single<NotaResponse> createNota(NotaRequest notaRequest) {
		
		NotaResponse notaResponse = new NotaResponse();
		
		return Single.create(singleSubscriber -> {
			Usuario usuario = usuarioRepositorio.findById(notaRequest.getIdUsuario()).get();
			notaRequest.getNota().setUsuario(usuario);
			Nota nota = notaRepositorio.save(notaRequest.getNota());
			notaResponse.setNota(nota);
			
            singleSubscriber.onSuccess(notaResponse);
        });
	}*/
	
	@Override
	public Single<NotaResponse> createNota(NotaRequest notaRequest) {
		
		NotaResponse notaResponse = new NotaResponse();
		
		return Single.create(singleSubscriber -> {
			Optional<Usuario> usuarioOptional = usuarioRepositorio.findById(notaRequest.getIdUsuario());
			
				if(!usuarioOptional.isPresent())
				{
					singleSubscriber.onError(new EntityNotFoundException());
				}
				else
				{
					Optional <Nota> notaOptional = notaRepositorio.findById(notaRequest.getNota().getId());
					if (notaOptional.isPresent())
					{
						singleSubscriber.onError(new EntityExistsException());
					}
					else {
						notaRequest.getNota().setUsuario(usuarioOptional.get());
						Nota nota = notaRepositorio.save(notaRequest.getNota());
						if (nota== null)
						{
							singleSubscriber.onError(new EntityNotFoundException());
						}
						else {
							notaResponse.setNota(nota);
							singleSubscriber.onSuccess(notaResponse);
						}
					}
				}
			
        });
	}
	
}
