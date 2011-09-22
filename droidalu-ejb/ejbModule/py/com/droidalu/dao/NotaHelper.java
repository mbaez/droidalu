package py.com.droidalu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import py.com.droidalu.dto.Alumno;
import py.com.droidalu.dto.Nota;
import py.com.droidalu.util.Conexion;

/**
 * 
 * @author Maximiliano Báez <mxbg.py@gmail.com>
 *
 */
public class NotaHelper {

	private Connection conn;
	private ResultSet rs;
	private PreparedStatement ps;
	private String notasQuery = "SELECT asig.descrip, insc.codnota, esc.nombre "+
								" FROM inscexafinal insc JOIN escaladet esc "+
                                " ON insc.codescala = esc.codescala and "+ 
                                " insc.codnota = esc.codnota " +
                                " JOIN asignat asig "+
                                " ON asig.codasign = insc.codasign "+
                                " JOIN alumno alu "+
                                " ON alu.cedula = insc.cedula "+
								" WHERE insc.ausente = 'N' "+ 
								" AND alu.cedula = '#' "+ 
								" AND alu.cip = '?'" ;
	
	private String alumnoQuery = "SELECT nombre, apellido FROM alumno where cedula='#' and cip='?'";
	
	
	public NotaHelper() {
		Conexion conexion = new Conexion();
		try {
			conn = conexion.conectar("200.10.228.84", "acadfac", "droidalu", "droidalu");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Alumno getNotasFinales(String id, String pin) {
		
		ArrayList<Nota> notas = new ArrayList<Nota>();
		Alumno alu = new Alumno();
		try {
			String query = notasQuery.replace("#",id).replace("?", pin);
			
			ps = conn.prepareStatement(query);
			System.out.println("PS:"+ ps);
			rs = ps.executeQuery();
			
			while (rs.next()) {

				Nota nota = new Nota();
				nota.setAsignatura(rs.getString(1));
				nota.setCalificacion(rs.getInt(2));
				nota.setCalificacionLetra(rs.getString(3));
				System.out.println("Nota: "+ nota);
				notas.add(nota);
			}
			
			query = alumnoQuery.replace("#",id).replace("?", pin);
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			if(rs.next()) {
				alu.setNombre(rs.getString(1));
				alu.setApellido(rs.getString(2));
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		alu.setNotas(notas);
		
		return alu;
	}
}
