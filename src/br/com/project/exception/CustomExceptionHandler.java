package br.com.project.exception;

import java.util.Iterator;
import java.util.Map;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import javax.swing.text.StyledEditorKit.ItalicAction;

import org.hibernate.SessionFactory;
import org.primefaces.context.RequestContext;

import br.com.framework.hibernate.util.HibernateUtil;

public class CustomExceptionHandler extends ExceptionHandlerWrapper{
	
	private ExceptionHandler wrapperd;
	
	final FacesContext context = FacesContext.getCurrentInstance();
	
	final Map<String, Object> requestMap = context.getExternalContext().getRequestMap();
	
	final NavigationHandler navigationHandler = context.getApplication().getNavigationHandler();
	
	public CustomExceptionHandler(ExceptionHandler exceptionHandler) {
		this.wrapperd = exceptionHandler;
	}

	/**
	 * Sobreescreve o método ExceptionHandler que retorna a pilha 
	 */
	@Override
	public ExceptionHandler getWrapped() {
		return wrapperd;
	}
	
	/**
	 * Sobreescreve o método hande que é responsável por manipular as exceções do JSF
	 */
	@Override
	public void handle() throws FacesException {
		final Iterator<ExceptionQueuedEvent> iterator =  getUnhandledExceptionQueuedEvents().iterator();
		
		while (iterator.hasNext()) {
			ExceptionQueuedEvent event = iterator.next();
			ExceptionQueuedEventContext conEventContext = (ExceptionQueuedEventContext) event.getSource();
			
			/*Recupera a exceçõa do conEventContext*/
			Throwable exception = conEventContext.getException();
			
			/*Aqui trabalhamos a exceção*/
			try {
				
				requestMap.put("exceptionMessage", exception.getMessage());
				
				if (exception != null && exception.getMessage() != null && exception.getMessage().indexOf("ConstraintViolationException") != -1) {
					FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, 
							"Registro não pode ser removido por " + " estar associado.", ""));
					
				} else if (exception != null && exception.getMessage() != null && exception.getMessage().indexOf("org.hibernate.StaleObjectStateException") != -1) {
					FacesContext.getCurrentInstance().addMessage("msg" ,new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Registro foi atualizado ou excluído por outro usuário. Consulte novamente.",""));
				} else {
					/*Avisa o usuário do erro*/
					FacesContext.getCurrentInstance().addMessage("msg" ,new FacesMessage(FacesMessage.SEVERITY_FATAL, "O sistema se recuperou de um erro inesperado.", ""));
					
					// Tranquiliza o usuário para que ele continue usando o sistema
					FacesContext.getCurrentInstance().addMessage("msg" ,new FacesMessage(FacesMessage.SEVERITY_INFO, "Você pode continuar usando o sistema normalmente!", ""));
					
					FacesContext.getCurrentInstance().addMessage("msg" ,new FacesMessage(FacesMessage.SEVERITY_FATAL, "O erro foi causado por:\n" + exception.getMessage(), ""));
					
					// SETA A NAVEGAÇÃO PARA UMA PÁGINA PADRÃO - REDIRECIONA PARA UMA PAGINA DE ERRO.
					//esse alert apenas é exibido se a página não for redirecionar
					RequestContext.getCurrentInstance().execute("alert('O sistema se recuperou de um erro inesperado.')"); 
					
					RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro", "O sistema se recuperou de um erro inesperado..."));
					
					navigationHandler.handleNavigation(context, null, "/error/error.jsf?faces-redirect=true&expired=true");
				}

				// Renderiza a página de erro e exibe as mensagens
				context.renderResponse();
				
				
			} finally {
				SessionFactory sf = HibernateUtil.getSessionFactory();
				if (sf.getCurrentSession().getTransaction().isActive()) {
					sf.getCurrentSession().getTransaction().rollback();
				}
				/*imprime o erro no console*/
				exception.printStackTrace();
				
				iterator.remove();
			} 
		}
		
		getWrapped().handle();
	}

}
