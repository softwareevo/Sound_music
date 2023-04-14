package org.ana.rivero.service.db;

import java.util.List;
import java.util.Optional;

import org.ana.rivero.entity.Usuario;
import org.ana.rivero.repository.UsuariosRepository;
import org.ana.rivero.service.IntServiceUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
@Service
@Primary
public class UsuariosServiceJpa implements IntServiceUsuarios {
	
	@Autowired
	private UsuariosRepository repoUsuarios;

	@Override
	public List<Usuario> obtnerUsuarios() {
		// TODO Auto-generated method stub
		return repoUsuarios.findAll();
	}

	@Override
	public void agregar(Usuario usuario) {
		// TODO Auto-generated method stub
		repoUsuarios.save(usuario);

	}

	@Override
	public Usuario buscarPorId(Integer idUsuario) {
		// TODO Auto-generated method stub
		Optional<Usuario> optional = repoUsuarios.findById(idUsuario);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;

	}

	@Override
	public void eliminar(Integer idUsuario) {
		// TODO Auto-generated method stub
		repoUsuarios.deleteById(idUsuario);

	}

	@Override
	public long totalUsuarios() {
		// TODO Auto-generated method stub
		return repoUsuarios.count();
	}

	@Override
	public Page<Usuario> buscarTodas(Pageable page) {
		// TODO Auto-generated method stub
		return repoUsuarios.findAll(page);
	}

}
