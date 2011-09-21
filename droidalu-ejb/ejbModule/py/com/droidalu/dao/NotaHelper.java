package py.com.droidalu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import py.com.droidalu.dto.Nota;
import py.com.droidalu.util.Conexion;

/**
 * @author mxbg
 *
 */
public class NotaHelper {

	private Statement st;
	private ResultSet rs;

	public NotaHelper() {
		Conexion con = new Conexion();
		try {
			st = con.conectar("localhost", "acad", "droidalu", "droidalu")
					.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Nota> getNotasFinales(String id, String pin) {
		ArrayList<Nota> list = new ArrayList<Nota>();
		
		try {
			rs = st.executeQuery("select....");
			
			while (rs.next()) {

				Nota nota = new Nota();
				nota.setAsignatura(rs.getString(1));
				nota.setCalificacion(rs.getInt(2));
				nota.setFecha(rs.getDate(3));
				
				list.add(nota);
			}
		} catch (SQLException e) {}
		
		return list;
	}
}
