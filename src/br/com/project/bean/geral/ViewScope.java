package br.com.project.bean.geral;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.web.context.request.FacesRequestAttributes;

/**
 * Implementação do scopo em view para o spring
 * @author Leandro
 *
 */
public class ViewScope implements Scope, Serializable{

	private static final long serialVersionUID = 1L;
	
	public static final String VIEW_SCOPE_CALLBACKS = "viewScope.callBacks";

	/**
	 * responsável por retornar o object de escopo
	 */
	@Override
	public Object get(String name, ObjectFactory<?> objectFactory) {
		Object instancia = getViewMap().get(name);
		
		if (instancia == null) {
			instancia = objectFactory.getObject();
			getViewMap().put(name, objectFactory);
		}
		return instancia;
	}

	@Override
	public String getConversationId() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		FacesRequestAttributes facesRequestAttributes = new FacesRequestAttributes(facesContext);
		
		return facesRequestAttributes.getSessionId() + "-" + facesContext.getViewRoot().getViewId();
	}

	@Override
	public void registerDestructionCallback(String name, Runnable runnable) {
		Map<String, Runnable> callBacks = (Map<String, Runnable>) getViewMap().get(VIEW_SCOPE_CALLBACKS);
		
		if (callBacks != null) {
			callBacks.put(VIEW_SCOPE_CALLBACKS, runnable);
		}
	}

	@Override
	public Object remove(String name) {
		Object instancia = getViewMap().remove(name);
		
		if (instancia != null) {
			Map<String, Runnable> callBacks = (Map<String, Runnable>) getViewMap().get(VIEW_SCOPE_CALLBACKS);
			
			if (callBacks != null) {
				callBacks.remove(name);
			}
		}
		return instancia;
	}

	@Override
	public Object resolveContextualObject(String name) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		FacesRequestAttributes facesRequestAttributes = new FacesRequestAttributes(facesContext);
		
		return facesRequestAttributes.resolveReference(name);
	}
	
	/**
	 * getViewRoot()
	 * retorna o componente raiz, que está associado a esta solicitação que no caso é o request.
	 * getViewMap retorna um Map que atua como a interface para o armazenamento de dados
	 * @return
	 */
	private Map<String, Object> getViewMap(){
		return FacesContext.getCurrentInstance() != null ? 
				FacesContext.getCurrentInstance()
				.getViewRoot()
				.getViewMap() : new HashMap<String, Object>();
	}

}
