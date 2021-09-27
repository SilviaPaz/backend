package com.spaz.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Nota")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Nota {
	@Id
    @Column(name = "id")
    private Long id;

    @Column(name = "descripcion")
    private String descripcion;
    
    @Column(name = "nota")
    private int nota;
    
    @ManyToOne (fetch = FetchType.LAZY)
    @JsonIgnore
	@JoinColumn(name= "usuario")
	private Usuario usuario;
}
