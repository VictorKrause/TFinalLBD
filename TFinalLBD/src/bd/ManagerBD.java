/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import java.sql.*;
import java.util.ArrayList;
import model.Funcionario;

/**
 *
 * @author 15201677
 */
public class ManagerBD {

	private static ManagerBD ref = null;

	public static ManagerBD getInstance() {
		if (ref == null) {
			ref = new ManagerBD();
		}
		return ref;
	}

	private Connection getConnection() throws SQLException {
		Connection conexao = DriverManager.getConnection(
				"jdbc:oracle:thin:@//camburi.pucrs.br:1521/facin11g",
				"BG111019", "BG111019");

		return conexao;
	}

	public ArrayList<String> getAllFuncionarios() throws SQLException {
		Connection con = getConnection();
		Statement stmt = con.createStatement();
		String sql = "SELECT NOME_COMPLETO FROM FUNCIONARIOS_TRAB ORDER BY NOME_COMPLETO ASC";

		ArrayList<String> allFuncionarios = new ArrayList<String>();

		ResultSet resultado = stmt.executeQuery(sql);

		while (resultado.next()) {
			allFuncionarios.add(resultado.getString("NOME_COMPLETO"));
		}

		resultado.close();
		stmt.close();
		con.close();

		return allFuncionarios;
	}

	public ArrayList<String> getRelatorioDeReservasFuturas() throws SQLException {
		Connection con = getConnection();
		Statement stmt = con.createStatement();

		ArrayList<String> strings = new ArrayList<String>();

		String sql = "SELECT FUN.NOME_COMPLETO, EQ.DESCRICAO, R.DATA_INICIAL, R.DATA_FINAL\n"
				+ "FROM RESERVAS_TRAB R INNER JOIN FUNCIONARIOS_TRAB FUN ON R.FUNCIONARIO_LOGIN = FUN.LOGIN\n"
				+ "INNER JOIN EQUIPAMENTOS_TRAB EQ ON R.EQUIPAMENTO_ID = EQ.ID_EQUIPAMENTO\n"
				+ "WHERE R.DATA_INICIAL > SYSDATE";

		ResultSet resultado = stmt.executeQuery(sql);

		while (resultado.next()) {
			strings.add(resultado.getString("NOME_COMPLETO"));
			strings.add(resultado.getString("DESCRICAO"));
			strings.add(resultado.getDate("DATA_INICIAL").toString());
			strings.add(resultado.getDate("DATA_FINAL").toString());

		}
		resultado.close();
		stmt.close();
		con.close();

		return strings;
	}

	public double getValorTotalDeReservaByEquipamentoId (int idEquipamento) throws SQLException{
		double acum = 0;


		/**
		for (dias = 0; dtFim.after(dyInicio); dias++) {  
			dtFim.setDate(dtFim.getDate - 1);  
		} 
		 **/

		return acum;
	}
}
