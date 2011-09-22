package py.com.droidalu.ejb;
import javax.ejb.Local;

import py.com.droidalu.dto.Alumno;
/**
 * 
 * @author Maximiliano Báez <mxbg.py@gmail.com>
 *
 */

@Local
public interface NotaBean {
	public Alumno getNotas(String id, String pin);
}
