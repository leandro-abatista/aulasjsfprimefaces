package br.com.repository.interfaces;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

/**
 * Interface de conex�o com o banco de dados
 * @author Leandro
 *
 */
@Repository
public interface RepositoryLogin extends Serializable {

	boolean autentico(String login, String senha) throws Exception;
}
