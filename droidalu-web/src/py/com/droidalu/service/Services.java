package py.com.droidalu.service;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import py.com.droidalu.dto.Alumno;
import py.com.droidalu.ejb.NotaBean;

/**
 * Clase que define los servicios con los que contamos en el path /services. En
 * el caso de esta implementación solamente tenemos el servicio de consulta de
 * notas en el path /services/consulta/notas
 * 
 * @author Marcio Duarte <marcioadr88@gmail.com>
 * 
 */
@Path("services")
public class Services {
	private InitialContext context;
	private NotaBean notaBean;

	/**
	 * Constructor por defecto
	 */
	public Services() {
		/*
		 * En este paso hacemos un lookup para obtener un proxy para el Session
		 * Bean NotaBean, a través del cual obtendremos las notas de un usuario
		 * específico.
		 */
		try {
			context = new InitialContext();
			notaBean = (NotaBean) context.lookup("DroidAlu/NotaBean");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Recurso al que accedemos para obtener la nota de un usuario. Para obtener
	 * la nota el usuario debe proporcionar su id y su pin, accediento a la URI
	 * de ésta manera, por ejemplo: http://localhost:8080/droidalu/services/consulta/notas?id=45345&pin=14323
	 * <br/>
	 * 
	 * OBS: Esta implementación no implementa ningún tipo de
	 * seguridad al respecto, puesto que el fin es simplemente mostrar el
	 * funcionamiento de un RESTful WS. Para una aplicación más "realista", se debe
	 * tener en cuenta:<br/>
	 * 1- No pasar el pin del usuario en la URL (se pueden usar
	 * cookies por ejemplo).<br/>
	 * 2- No pasar en pin en texto plano.
	 * 
	 * @param id el id del usuario en texto plano
	 * @param pin el pin del usuario en texto plano
	 * @return Una cadena JSON que representa la lista de notas.
	 * 
	 * @see py.com.droidalu.dto.Alumno
	 */
	@GET
	@Path("consulta/notas")
	@Produces("application/json")
	public Alumno getNotas(@QueryParam("id") String id,
			@QueryParam("pin") String pin) {
		return getListaNotas(id, pin);
	}
	
	/**
	 * Método encargado de invocar al session bean para obtener la lista de notas. 
	 * @param id el id del usuario en texto plano
	 * @param pin el pin del usuario en texto plano
	 * @return Una cadena JSON que representa la lista de notas.
	 */
	private Alumno getListaNotas(String id, String pin) {
		Alumno notas = null;

		if (notaBean != null) {
			notas = notaBean.getNotas(id, pin);
		}

		return notas;
	}

}
