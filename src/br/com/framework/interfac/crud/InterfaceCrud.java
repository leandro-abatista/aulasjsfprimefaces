package br.com.framework.interfac.crud;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Essa classe gen�rica, vai trabalhar com qualquer objeto
 * 
 * @author Leandro
 *
 * @param <T>
 */
@Component
@Transactional
public interface InterfaceCrud<T> extends Serializable {

	/**
	 * m�todo salvar
	 * 
	 * @param obj
	 * @throws Exception
	 */
	void save(T obj) throws Exception;

	void persist(T obj) throws Exception;

	/**
	 * m�todo salva ou atualiza
	 * 
	 * @param obj
	 * @throws Exception
	 */
	void saveOrUpdate(T obj) throws Exception;

	/**
	 * realiza update/atualiza��o de dados
	 * 
	 * @param obj
	 * @throws Exception
	 */
	void update(T obj) throws Exception;

	/**
	 * realiza delete de dados
	 * 
	 * @param obj
	 * @throws Exception
	 */
	void delete(T obj) throws Exception;

	/**
	 * salva ou atualiza e retorna o objeto em estado persistente
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	T merge(T obj) throws Exception;

	/**
	 * carrega lista de determinada classe
	 * 
	 * @param objs
	 * @return
	 * @throws Exception
	 */
	List<T> findList(Class<T> objs) throws Exception;

	Object findById(Class<T> entidade, Long id) throws Exception;

	T findByPorId(Class<T> entidade, Long id) throws Exception;

	List<T> findListByQueryDinamic(String s) throws Exception;

	/**
	 * executar update com HQL
	 * 
	 * @param s
	 * @throws Exception
	 */
	void executeUpdateQueryDinamic(String s) throws Exception;

	/**
	 * execute update com SQL puro
	 * 
	 * @param s
	 * @throws Exception
	 */
	void executeUpdateSQLDinamic(String s) throws Exception;

	/**
	 * limpa a sess�o do hibernate
	 * 
	 * @throws Exception
	 */
	void cleanSession() throws Exception;

	/**
	 * retira um objeto da sess�o do hibernate
	 * 
	 * @param objs
	 * @throws Exception
	 */
	void evict(Object objs) throws Exception;
	
	Session getSession() throws Exception;
	
	List<?> getListSQLDinamic(String s) throws Exception;
	
	/**
	 * JDBC do spring
	 * @return
	 */
	JdbcTemplate getJdbcTemplate();
	
	SimpleJdbcTemplate getSimpleJdbcTemplate();
	
	SimpleJdbcInsert getSimpleJdbcInsert();
	
	Long totalDeRegistros(String table) throws Exception;
	
	Query obterQuery(String query) throws Exception;
	
	List<Object[]> getListSQLDinamicArray(String sql) throws Exception;
	
	/**
	 * usado para carregar lista por demanda, ou carregamento din�mico
	 * @param query
	 * @param iniciaNoRegistro
	 * @param maximoResultado
	 * @return
	 * @throws Exception
	 */
	List<T> findListByQueryDinamic(String query, int iniciaNoRegistro, int maximoResultado) throws Exception;
}
