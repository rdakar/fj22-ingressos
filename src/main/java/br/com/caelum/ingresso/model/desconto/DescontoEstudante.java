package br.com.caelum.ingresso.model.desconto;

import java.math.BigDecimal;

public class DescontoEstudante implements Desconto {

	@Override
	public BigDecimal aplicarDescontoSobre(BigDecimal precoOriginal) {
		return precoOriginal.divide(BigDecimal.valueOf(2));
	}
}
