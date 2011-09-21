package py.com.droidalu.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Clase que encapsula una lista de notas con anotaciones XML para producir un formato JSON.
 * Ejemplo del formato JSON de esta clase:<br/>
 * {"lista":{"notas":[{"asignatura":"Algoritmos I","calificacion":5,"fecha":"2011-09-21T15:09:35.631-04:00"},
 * {"asignatura":"Algoritmos II","calificacion":2,"fecha":"2011-09-21T15:09:35.631-04:00"}]}}
 * 
 * @author Marcio Duarte <marcioadr88@gmail.com>
 */
@XmlRootElement(name = "lista")
public class ListaNotas {
	private List<Nota> notas;

	/**
	 * Constructor por defecto.
	 */
	public ListaNotas() {
	}

	/**
	 * Constructor que recibe una lista de notas.
	 * 
	 * @param notas
	 *            notas con las que se van a inicializar la lista.
	 */
	public ListaNotas(List<Nota> notas) {
		this.notas = notas;
	}

	/**
	 * Asigna la lista de notas.
	 * 
	 * @param notas
	 *            la lista de notas a asignar.
	 */
	public void setNotas(List<Nota> notas) {
		this.notas = notas;
	}

	/**
	 * Obtiene la lista de notas.
	 */
	@XmlElement
	public List<Nota> getNotas() {
		return notas;
	}

}
