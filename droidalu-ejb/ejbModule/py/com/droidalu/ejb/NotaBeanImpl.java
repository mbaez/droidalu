package py.com.droidalu.ejb;

import java.util.ArrayList;
import java.util.Date;

import javax.ejb.Stateless;

import org.jboss.ejb3.annotation.LocalBinding;

import py.com.droidalu.dto.ListaNotas;
import py.com.droidalu.dto.Nota;

/**
 * Session Bean que implementa la lógica de obtención de las notas.
 * 
 * @author Marcio Duarte <marcioadr88@gmail.com>
 */
@Stateless
@LocalBinding(jndiBinding = "DroidAlu/NotaBean")
public class NotaBeanImpl implements NotaBean {

	/**
	 * Contructor por defecto.
	 */
	public NotaBeanImpl() {
	}

	/**
	 * Obtiene la lista de notas, para el usuario especificado.
	 * 
	 * @param id
	 *            el id del usuario
	 * @param pin
	 *            el pin del usuario
	 */
	@Override
	public ListaNotas getNotas(String id, String pin) {
		/**
		 * Obtener aquí el listado de las notas, utilizar NotaHelper
		 */
		ArrayList<Nota> list = new ArrayList<Nota>();
		list.add(new Nota(5, new Date(), "Algoritmos I"));
		list.add(new Nota(2, new Date(), "Algoritmos II"));

		return new ListaNotas(list);
	}
}
