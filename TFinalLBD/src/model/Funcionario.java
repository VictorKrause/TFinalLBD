package model;

import java.sql.Date;

public class Funcionario {
	
	private String login;
	private String senha;
	private Date dataAdmissao;
	private Date dataNascimento;
	private String sexo;
	private String nomeCompleto;
	private String endereco;
	private double salarioMensal;
	
	public Funcionario(String login, String senha, Date dataAdmissao, Date dataNascimento, char sexo,
			String nomeCompleto, String endereco, double salarioMensal) {
		this.login = login;
		this.senha = senha;
		this.dataAdmissao = dataAdmissao;
		this.dataNascimento = dataNascimento;
		this.nomeCompleto = nomeCompleto;
		this.endereco = endereco;
		this.salarioMensal = salarioMensal;
		if(sexo == 'M' || sexo == 'm')
			this.sexo = "Masculino";
		else
			this.sexo = "Feminino";
	}
	
	public String getLogin() {
		return login;
	}
	public String getSenha() {
		return senha;
	}
	public Date getDataAdmissao() {
		return dataAdmissao;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public String getSexo() {
		return sexo;
	}
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	public String getEndereco() {
		return endereco;
	}
	public double getSalarioMensal() {
		return salarioMensal;
	}
	
	@Override
	public String toString() {
		return nomeCompleto;
	}
	
	
	

}
