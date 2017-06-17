package br.com.caelum.ingresso.model;

import java.time.YearMonth;

public class Cartao {

	private String numero;
	private String cvv;
	private YearMonth vencimento;
	
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	public YearMonth getVencimento() {
		return vencimento;
	}
	public void setVencimento(YearMonth vencimento) {
		this.vencimento = vencimento;
	}
	
	public Cartao() {
		
	}
	
	public boolean isValido() {
		return vencimento.isAfter(YearMonth.now());
	}
}
