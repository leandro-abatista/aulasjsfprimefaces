package br.com.srv.interfaces;

import java.io.Serializable;

import org.springframework.stereotype.Service;

@Service
public interface ServiceLogin extends Serializable{

	/*m�todos de servi�o do login*/
	
	boolean autentico(String login, String senha) throws Exception;
}
