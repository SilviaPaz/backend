package com.spaz.backend.servicedto;

import com.spaz.backend.entity.Usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequest {
	private Long idUsuario;
	private Usuario usuario;
}