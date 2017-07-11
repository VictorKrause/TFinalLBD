/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author 15201677
 */
public class Funcionario {
    
    private String login;
    private String senha;
    private Date dataNascimento;
    private Date dataAdmissao;
    private char sexo;
    private String nomeCompleto;
    private String endereco;
    private double salarioMensal;

    public Funcionario(String login, String senha, Date dataNascimento, Date dataAdmissao, char sexo, String nomeCompleto, String endereco, double salarioMensal) {
        this.login = login;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
        this.dataAdmissao = dataAdmissao;
        this.sexo = sexo;
        this.nomeCompleto = nomeCompleto;
        this.endereco = endereco;
        this.salarioMensal = salarioMensal;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public Date getDataAdmissao() {
        return dataAdmissao;
    }

    public char getSexo() {
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
        return "Funcionario{" + "login=" + login + ", senha=" + senha + ", dataNascimento=" + dataNascimento + ", dataAdmissao=" + dataAdmissao + ", sexo=" + sexo + ", nomeCompleto=" + nomeCompleto + ", endereco=" + endereco + ", salarioMensal=" + salarioMensal + '}';
    }
    
    
    
}
