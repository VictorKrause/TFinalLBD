/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import java.sql.*;
import java.util.ArrayList;

import model.Equipamento;
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
		Connection conexao = DriverManager.getConnection("jdbc:oracle:thin:@//camburi.pucrs.br:1521/facin11g",
				"BG111019", "BG111019");

		return conexao;
	}

	//Control = 1 -> Funcionario | Control = 2 ->Equipamento
	public Object getFuncionarioOuEquipamento (String parametro, int control) throws SQLException {
		Object retorno = null;
		Connection con = getConnection();
		if(control == 1){
			PreparedStatement stmt = con.prepareStatement("SELECT F.LOGIN, F.SENHA, F.DATA_NASCIMENTO, F.DATA_ADMISSAO, F.SEXO, F.NOME_COMPLETO, F.ENDERECO, F.SALARIO_MENSAL\n"
					+ "FROM FUNCIONARIOS_TRAB F\n"
					+ "WHERE F.NOME_COMPLETO LIKE (%?%)");
			stmt.setString(1, parametro);
			
			ResultSet resultado = stmt.executeQuery();
			
			if(resultado.next()){
				String login = resultado.getString("LOGIN");
				String senha = resultado.getString("Senha");
				Date dataNascimento = resultado.getDate("DATA_NASCIMENTO");
				Date dataAdmissao = resultado.getDate("DATA_ADMISSAO");
				char sexo = resultado.getString("SEXO").charAt(0);
				String nomeCompleto = resultado.getString("NOME_COMPLETO");
				String endereco = resultado.getString("ENDERECO");
				Double salarioMensal = resultado.getDouble("SALARIO_MENSAL");	
				
				retorno = new Funcionario(login, senha, dataAdmissao, dataNascimento, sexo, nomeCompleto, endereco, salarioMensal);
			}
			resultado.close();
			stmt.close();
		}
		else{
			PreparedStatement stmt = con.prepareStatement("SELECT E.ID_EQUIPAMENTO, E.DATA_AQUISICAO, E.DESCRICAO, E.CUSTO_DIARIA, T.TIPO_EQUIPAMENTO, MANUTENCAO\n"
					+ "FROM EQUIPAMENTOS_TRAB E INNER JOIN TIPO_EQUIPAMENTO_TRAB T ON E.TIPO = T.ID_TIPO_EQUIPAMENTO\n"
					+ "WHERE E.DESCRICAO LIKE (%?%)");
			
			stmt.setString(1, parametro);
			
			ResultSet resultado = stmt.executeQuery();
			
			if(resultado.next()){
				int idEquipamento = resultado.getInt("ID_EQUIPAMENTO");
				Date dataAquisicao = resultado.getDate("DATA_AQUISICAO");
				String descricao = resultado.getString("DESCRICAO");
				double custoDiaria = resultado.getDouble("CUSTO_DIARIA");
				String tipo = resultado.getString("TIPO_EQUIPAMENTO");
				char manutencao = resultado.getString("MANUTENCAO").charAt(0);
				
				retorno = new Equipamento(idEquipamento, dataAquisicao, descricao, custoDiaria, tipo, manutencao);
			}
			
			resultado.close();
			stmt.close();
		}
		
		con.close();
		
		return retorno;
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

	//Se retornar -1 é porque não achou
	public double getValorTotalDeReservaByEquipamentoId(int idEquipamento) throws SQLException {
		double acum = 0;

		Connection con = getConnection();
		PreparedStatement stmt = con.prepareStatement("SELECT R.DATA_INICIAL, R.DATA_FINAL, E.CUSTO_DIARIA\n"
				+ "FROM RESERVAS_TRAB R INNER JOIN EQUIPAMENTOS_TRAB E ON R.EQUIPAMENTO_ID = E.ID_EQUIPAMENTO\n"
				+ "WHERE E.ID_EQUIPAMENTO = ?");
		stmt.setInt(1, idEquipamento);

		ResultSet result = stmt.executeQuery();

		if (result.next()) {
			Date dtFim;
			Date dtInicio;
			int dias;
			double custoDiario;

			dtFim = result.getDate("DATA_FINAL");
			dtInicio = result.getDate("DATA_INCIAL");
			custoDiario = result.getDouble("CUSTO_DIARIA");

			for (dias = 0; dtFim.after(dtInicio); dias++) {
				dtFim.setDate(dtFim.getDate() - 1);
			}
			acum = custoDiario * dias;
		}
		else
			acum = -1;
		
		result.close();
		stmt.close();
		con.close();
		
		return acum;

	}

	public ArrayList<String> getAllFuncionariosSemReserva() throws SQLException{
		ArrayList<String> nomes = new ArrayList<String>();
		
		Connection con = getConnection();
		Statement stmt = con.createStatement();
		String sql = "SELECT F.NOME_COMPLETO, F.LOGIN\n"
				+ "FROM FUNCIONARIOS_TRAB F\n"
				+ "WHERE F.LOGIN NOT IN (SELECT E.FUNCIONARIO_LOGIN FROM EQUIPAMENTOS_TRAB E)\n"
				+ "ORDER BY F.NOME_COMPLETO";
		ResultSet result = stmt.executeQuery(sql);
		while(result.next()){
			nomes.add(result.getString("NOME_COMPLETO"));
			nomes.add(result.getString("LOGIN"));
		}
		
		result.close();
		stmt.close();
		con.close();
		
		return nomes;
	}
}
