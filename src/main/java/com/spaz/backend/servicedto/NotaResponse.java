package com.spaz.backend.servicedto;

import com.spaz.backend.entity.Nota;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotaResponse {
    private Nota nota;
}