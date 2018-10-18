package mvc.model;
import java.sql.Date;
import java.sql.*;
import java.util.*;
public class DAO {
 private Connection connection = null;

public DAO() {
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost/projeto2?useTimezone=true&serverTimezone=UTC", "root", "Elikevin1");
		} 
	catch (SQLException | ClassNotFoundException e)
		{e.printStackTrace();}
 	}


public void adicionaDescricao(Notas nota) {
	try {
		String sql = "INSERT INTO Notas (conteudo) values(?)";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1,nota.getConteudo());
		stmt.execute();
		stmt.close();
		} 
	catch (SQLException e) {e.printStackTrace();}
 }

public void adiciona(Notas nota) {
	try {
		String sql = "INSERT INTO Notas" + "(conteudo) values(?,?,?)";
		//String sql = "INSERT INTO Notas" + "(conteudo,finalizado,dataFinalizacao) values(?,?,?)";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1,nota.getConteudo());
		//stmt.setBoolean(2, nota.isFinalizado());
		//stmt.setDate(3, new Date(nota.getDataFinalizacao().getTimeInMillis()));
		stmt.execute();
		stmt.close();
		} 
	catch (SQLException e) {e.printStackTrace();}
	}

public List<Notas> getLista() {
	List<Notas> notas = new ArrayList<Notas>();
	try {
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Notas");
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Notas nota = new Notas();
			nota.setId(rs.getInt("id"));
			nota.setConteudo(rs.getString("conteudo"));

			//nota.setFinalizado(rs.getBoolean("finalizado"));
			//Calendar data = Calendar.getInstance();
			//Date dataFinalizacao = rs.getDate("dataFinalizacao");
			//if(dataFinalizacao!=null) {
			//	data.setTime(dataFinalizacao);
			//	tarefa.setDataFinalizacao(data);
			//}
			notas.add(nota);
		}
		rs.close();
		stmt.close();
		} 
	catch(SQLException e) {System.out.println(e);}
	return notas;
 }

public void remove(Notas nota) {
	try {
		PreparedStatement stmt = connection.prepareStatement("DELETE FROM Notas WHERE id=?");
		stmt.setLong(1, nota.getId());
		stmt.execute();
		stmt.close();
	} 
	catch(SQLException e) {System.out.println(e);}
 }

public Notas buscaPorId(Integer id) {
	Notas nota = new Notas();
	try {
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Notas WHERE id=?");
		stmt.setLong(1, id);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			nota.setId(rs.getInt("id"));
			nota.setConteudo(rs.getString("conteudo"));

			//nota.setFinalizado(rs.getBoolean("finalizado"));
			//Calendar data = Calendar.getInstance();
			//Date dataFinalizacao = rs.getDate("dataFinalizacao");
			//if(dataFinalizacao!=null) {
			//	data.setTime(dataFinalizacao);
			//	tarefa.setDataFinalizacao(data);
			//}
		}
		rs.close();
		stmt.close();
	} 
	catch(SQLException e) {System.out.println(e);}
	return nota;
 }


public void altera(Notas nota) {
	try {
		String sql = "UPDATE Notas SET " + " conteudo=? WHERE id=?";
		//String sql = "UPDATE Notas SET " + "tipo=?, conteudo=?, data_atualizada=? WHERE id=?";
		//String sql = "UPDATE Notas SET conteudo=?, finalizado=?, " + "dataFinalizacao=? WHERE id=?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, nota.getConteudo());
		//stmt.setBoolean(2, nota.isFinalizado());
		//if(tarefa.getDataFinalizacao()!=null) {
		//	stmt.setDate(3, new Date(tarefa.getDataFinalizacao().getTimeInMillis()));
		//} else {
		//	stmt.setDate(3, new
		//			Date(Calendar.getInstance().getTimeInMillis()));
		//}
		stmt.setInt(4, nota.getId());
		stmt.executeUpdate();
		stmt.close();
	} catch(SQLException e) {System.out.println(e);}
  	}


public void close() {
	try { connection.close();}
	catch (SQLException e) {e.printStackTrace();}
		   }
		  }