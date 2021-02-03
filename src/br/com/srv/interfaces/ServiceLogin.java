package br.com.srv.interfaces;

import java.io.Serializable;

import org.springframework.stereotype.Service;

@Service
public interface ServiceLogin extends Serializable{

	/*métodos de serviço do login*/
	
	boolean autentico(String login, String senha) throws Exception;
}
