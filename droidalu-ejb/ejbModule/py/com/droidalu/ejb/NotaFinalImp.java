package py.com.droidalu.ejb;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import py.com.droidalu.util.Conexion;

/**
 * @author mxbg
 *
 */
public class NotaFinalImp implements NotaFinalBeanLocal {

	private Statement st;
	private ResultSet rs;

	public NotaFinalImp() {
		Conexion con = new Conexion();
		try {
			st = con.conectar("localhost", "acad", "droidalu", "droidalu")
					.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<NotaFinal> getNotasFinales(String id, String pin) {
		ArrayList<NotaFinal> list = new ArrayList<NotaFinal>();
		
		try {
			rs = st.executeQuery("select....");
			
			while (rs.next()) {

				NotaFinal nota = new NotaFinal();
				nota.setMateria(rs.getString(1));
				nota.setNota(rs.getInt(2));
				nota.setFechaNota(rs.getString(3));
				
				list.add(nota);
			}
		} catch (SQLException e) {}
		
		return list;
	}
}
