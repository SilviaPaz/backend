package com.spaz.backend.servicedto;

import java.util.List;

import com.spaz.backend.entity.Nota;
import com.spaz.backend.entity.Usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotasListResponse {

    private Usuario usuario;
    private List<Nota> notas;
    
}
