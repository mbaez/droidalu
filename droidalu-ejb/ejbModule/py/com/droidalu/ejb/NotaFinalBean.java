package py.com.droidalu.ejb;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;


/**
 * Session Bean implementation class NotaFinalBean
 */
@Stateless
@LocalBean
public class NotaFinalBean implements NotaFinalBeanLocal {

    public NotaFinalBean() {
    	
    }

	@Override
	public ArrayList<NotaFinal> getNotasFinales(String id, String pin) {
		
		NotaFinalImp nota = new NotaFinalImp();
		
		return nota.getNotasFinales(id, pin);
	}

}
