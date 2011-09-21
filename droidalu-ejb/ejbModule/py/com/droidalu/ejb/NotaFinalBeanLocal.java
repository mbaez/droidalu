package py.com.droidalu.ejb;
import java.util.ArrayList;

import javax.ejb.Local;
/**
 * 
 * @author mxbg
 *
 */

@Local
public interface NotaFinalBeanLocal {
	public ArrayList<NotaFinal> getNotasFinales(String id, String pin);

}
