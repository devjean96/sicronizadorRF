package br.com.devjean.sicronizadorReceita.model;


public class Conta {
	
	private String agencia;
	private String conta;
	private double saldo;
	private String status;
	private boolean resultadoReceita;
	
	
	public Conta() {}

	public Conta(String agencia, String conta, double saldo, String status, boolean resultadoReceita) {
		super();
		this.agencia = agencia;
		this.conta = conta;
		this.saldo = saldo;
		this.status = status;
		this.resultadoReceita = resultadoReceita;
	}
	
	public String getAgencia() {
		return agencia;
	}
	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}
	public String getConta() {
		return conta;
	}
	public void setConta(String conta) {
		this.conta = conta;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public boolean isResultadoReceita() {
		return resultadoReceita;
	}

	public void setResultadoReceita(boolean resultadoReceita) {
		this.resultadoReceita = resultadoReceita;
	}

	@Override
	public String toString() {
		return "Conta [agencia=" + agencia + ", conta=" + conta + ", saldo=" + saldo + ", status=" + status + ", resultadoReceita=" + resultadoReceita +"]";
	}
	
}
