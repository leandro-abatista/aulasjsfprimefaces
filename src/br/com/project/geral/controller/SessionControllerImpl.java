package br.com.project.geral.controller;

import java.util.HashMap;

import javax.faces.bean.ApplicationScoped;
import javax.servlet.http.HttpSession;


@ApplicationScoped
public class SessionControllerImpl implements SessionController {

	private static final long serialVersionUID = 1L;
	
	private HashMap<String, HttpSession> hashMap = new HashMap<String, HttpSession>();

	@Override
	public void addSession(String keyLoginUser, HttpSession httpSession) {
		hashMap.put(keyLoginUser, httpSession);
	}

	/**
	 * Esse método invalida a sessão do usuário
	 */
	@Override
	public void invalidateSession(String keyLoginUser) {
		HttpSession session = hashMap.get(keyLoginUser);
		
		if (session != null) {// remove sessão do usuário passado como parâmetro
			try {
				
				session.invalidate();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("-- Não tem usuário logado! --");
		}
		
		/*Esse keyLoginUser é o código do usuário*/
		hashMap.remove(keyLoginUser);
	}

}
