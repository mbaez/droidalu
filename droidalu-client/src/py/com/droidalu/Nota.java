package py.com.droidalu;

import java.util.Date;


/**
 * Clase que encapsula una nota. Esta clase está anotada de manera que sea
 * convertible a JSON. Ejemplo:
 * {"asignatura":"Algoritmos I","calificacion":5,"fecha":
 * "2011-09-21T15:09:35.631-04:00"}
 * 
 * @author Marcio Duarte <marcioadr88@gmail.com>
 */

public class Nota {
	private Integer calificacion;
	private String calificacionLetra;
	private String asignatura;

	/**
	 * Constructor por defecto.
	 */
	public Nota() {
	}

	/**
	 * Constructor parametrizado.
	 * 
	 * @param nota
	 *            la nota a fijar.
	 * @param fecha
	 *            fecha de la nota.
	 * @param asignatura
	 *            asignatura a que pertenece la nota.
	 */
	public Nota(Integer nota, String calificacionLetra, String asignatura) {
		this.setCalificacion(nota);
		this.setCalificacionLetra(calificacionLetra);
		this.setAsignatura(asignatura);
	}

	/**
	 * Asigna la calificación.
	 * 
	 * @param calificacion
	 *            calificación a fijar.
	 */
	public void setCalificacion(Integer calificacion) {
		this.calificacion = calificacion;
	}

	/**
	 * Obtiene la calificación.
	 */

	public Integer getCalificacion() {
		return calificacion;
	}

	/**
	 * Setea la fecha para la nota.
	 * 
	 * @param fecha
	 *            la fecha de la calificación.
	 */
	public void setCalificacionLetra(String calificacionLetra) {
		this.calificacionLetra = calificacionLetra;
	}

	/**
	 * Obtiene la fecha de la calificación
	 */

	public String getCalificacionLetra() {
		return calificacionLetra;
	}

	/**
	 * Asigna la asignatura a la nota.
	 * 
	 * @param asignatura
	 *            nombre de la asignatura.
	 */
	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
	}

	/**
	 * Obtiene el nombre de la asignatura.
	 */

	public String getAsignatura() {
		return asignatura;
	}
}
