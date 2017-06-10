package br.com.caelum.ingresso.model.desconto;

import java.math.BigDecimal;

public class DescontoBanco implements Desconto {
	
	@Override
	public BigDecimal aplicarDescontoSobre(BigDecimal precoOriginal) {
		return precoOriginal.multiply(BigDecimal.valueOf(0.7));
	}

	@Override
	public String GetDescricao() {
		return "Banco Original";
	}
}
