package py.com.droidalu.service;

import javax.ws.rs.core.Application;
import java.util.Set;
import java.util.HashSet;

/**
 * Define los componenetes para la aplicación RESTful Web Service, en este caso
 * la aplicación DroidAlu
 * 
 * @see javax.ws.rs.core.Application
 * @author Marcio Duarte <marcioadr88@gmail.com>
 * 
 */
public class DroidAlu extends Application {
	HashSet<Object> singletons = new HashSet<Object>();

	/**
	 * Constructor por defecto.
	 */
	public DroidAlu() {
		/* Agregamos los servicios */
		singletons.add(new Services());
	}

	/**
	 * @see javax.ws.rs.core.Application#getClasses()
	 */
	@Override
	public Set<Class<?>> getClasses() {
		HashSet<Class<?>> set = new HashSet<Class<?>>();
		return set;
	}

	/**
	 * @see javax.ws.rs.core.Application#getSingletons()
	 */
	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
}
