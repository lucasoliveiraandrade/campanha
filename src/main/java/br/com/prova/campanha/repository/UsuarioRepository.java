package br.com.prova.campanha.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.prova.campanha.collection.Usuario;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String>{

}
