package com.spaz.backend.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spaz.backend.entity.Nota;
import com.spaz.backend.entity.Usuario;

@Repository
public interface NotaRepositorio extends JpaRepository<Nota, Long> {
    List<Nota> findAllById(Long id);
    List<Nota> findAllByUsuario(Usuario usuario);
}
