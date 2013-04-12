package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/pessoa")
public class Pessoa extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8335174445967572494L;
	
	private DataSource ds;
 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		out.println("<html><body>inserido!</body></html>");
	}
	
//	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException{
//		PrintWriter out = res.getWriter();
//		out.println("<html><body>inserido!</body></html>");
//	}
 
	public Pessoa() throws SQLException{
	  try {
		Context ctx = new InitialContext();
		ds = (DataSource)ctx.lookup("java:comp/env/jdbc/meurecurso");
		
		// teste de utilizacao do datasource
		this.inserir();
		
	  } catch (NamingException e) {
		e.printStackTrace();
	  }
	}
 
	public boolean inserir() throws SQLException{
 
	  //get database connection
	  Connection con = ds.getConnection();
	  PreparedStatement ps = con.prepareStatement("insert into pessoa (nome) values(?)");
	  ps.setString(1, "Luis");
	  boolean retorno = ps.execute();
	  return retorno;
	}
}