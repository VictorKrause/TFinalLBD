package model;

import java.util.Date;

public class Equipamento {
	private int idEquipamento;
	private Date dataAquisicao;
	private String descricao;
	private double custoDiaria;
	private String tipo;
	private String manutencao;
	
	
	
	public Equipamento(int idEquipamento, Date dataAquisicao, String descricao, double custoDiaria, String tipo,
			char manutencao) {
		this.idEquipamento = idEquipamento;
		this.dataAquisicao = dataAquisicao;
		this.descricao = descricao;
		this.custoDiaria = custoDiaria;
		this.tipo = tipo;
		if(manutencao == 'S' || manutencao == 's')
			this.manutencao = "Sim";
		else
			this.manutencao  = "Não";
	}
	public int getIdEquipamento() {
		return idEquipamento;
	}
	public Date getDataAquisicao() {
		return dataAquisicao;
	}
	public String getDescricao() {
		return descricao;
	}
	public double getCustoDiaria() {
		return custoDiaria;
	}
	public String getTipo() {
		return tipo;
	}
	public String getManutencao() {
		return manutencao;
	}
	@Override
	public String toString() {
		return descricao;
	}
	
	
}
