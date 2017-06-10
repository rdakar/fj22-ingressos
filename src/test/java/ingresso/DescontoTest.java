package ingresso;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.desconto.DescontoBanco;
import br.com.caelum.ingresso.model.desconto.DescontoEstudante;
import br.com.caelum.ingresso.model.desconto.SemDesconto;

public class DescontoTest {

	@Test
	public void deveConcederDescontoDe30PorcentoParaIngressosDeClientesDeBancos() {
		Sala sala = new Sala("3D Emotion", BigDecimal.valueOf(20));
		Filme filme = new Filme("Ninja Gaiden", Duration.ofMinutes(120), "Muita ação", BigDecimal.valueOf(10));
		Sessao sessao = new Sessao(LocalTime.now(), filme, sala);
		Ingresso ingresso = new Ingresso(sessao, new DescontoBanco());
		assertEquals(BigDecimal.valueOf(21.0), ingresso.getPreco());
	}
	
	@Test
	public void deveConcederDescontoDe50PorcentoParaIngressosDeEstudante() {
		Sala sala = new Sala("3D Emotion", BigDecimal.valueOf(20));
		Filme filme = new Filme("Ninja Gaiden", Duration.ofMinutes(120), "Muita ação", BigDecimal.valueOf(10));
		Sessao sessao = new Sessao(LocalTime.now(), filme, sala);
		Ingresso ingresso = new Ingresso(sessao, new DescontoEstudante());
		assertEquals(BigDecimal.valueOf(15), ingresso.getPreco());
	}
	
	@Test
	public void naoDeveConcederDescontoParaIngressoNormal() {
		Sala sala = new Sala("3D Emotion", BigDecimal.valueOf(20));
		Filme filme = new Filme("Ninja Gaiden", Duration.ofMinutes(120), "Muita ação", BigDecimal.valueOf(10));
		Sessao sessao = new Sessao(LocalTime.now(), filme, sala);
		Ingresso ingresso = new Ingresso(sessao, new SemDesconto());
		assertEquals(BigDecimal.valueOf(30), ingresso.getPreco());
	}
}
