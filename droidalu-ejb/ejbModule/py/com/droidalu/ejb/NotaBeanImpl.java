package py.com.droidalu.ejb;

import java.util.ArrayList;
import java.util.Date;

import javax.ejb.Stateless;

import org.jboss.ejb3.annotation.LocalBinding;

import py.com.droidalu.dao.NotaHelper;
import py.com.droidalu.dto.Alumno;
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
	private NotaHelper helper;
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
	public Alumno getNotas(String id, String pin) {
		helper = new NotaHelper(); 
		return helper.getNotasFinales(id, pin);
	}
}
