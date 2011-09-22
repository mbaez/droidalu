package py.com.droidalu.ejb;
import javax.ejb.Local;

import py.com.droidalu.dto.ListaNotas;
/**
 * 
 * @author Maximiliano Báez <mxbg.py@gmail.com>
 *
 */

@Local
public interface NotaBean {
	public ListaNotas getNotas(String id, String pin);
}
