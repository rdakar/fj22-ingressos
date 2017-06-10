package ingresso;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.Lugar;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.TipoDeIngresso;

public class SessaoTest {

	@Test
	public void oPrecoDaSessaoDeveSerIgualASomaDoPrecoDaSalaMaisOPrecoDoFilme() {
		Sala sala = new Sala("3D Emotion", BigDecimal.valueOf(20));
		Filme filme = new Filme("Ninja Gaiden", Duration.ofMinutes(120), "Muita ação", BigDecimal.valueOf(10));
		
		BigDecimal somaDosPrecosDaSalaEFilme = sala.getPreco().add(filme.getPreco());
		
		Sessao sessao = new Sessao(LocalTime.now(), filme, sala);
		
		assertEquals(somaDosPrecosDaSalaEFilme, sessao.getPreco());
	}
	
	@Test
	public void garanteQueOLugarA1EstaOcupadoEOsLugaresA2EA3Disponiveis() {
		Lugar a1 = new Lugar("A", 1);
		Lugar a2 = new Lugar("A", 2);
		Lugar a3 = new Lugar("A", 3);
		
		Filme filme = new Filme("Ninja Gaiden", Duration.ofMinutes(120), "Muita ação", BigDecimal.valueOf(10));
		Sala sala = new Sala("3D Emotion", BigDecimal.valueOf(20));
		
		Sessao sessao = new Sessao(LocalTime.now(), filme, sala);
		
		Ingresso ingresso = new Ingresso(sessao, TipoDeIngresso.INTEIRO, a1);
		Set<Ingresso> ingressos = Stream.of(ingresso).collect(Collectors.toSet());
		
		sessao.setIngressos(ingressos);
		
		assertTrue(sessao.isOcupado(a1));
		assertFalse(sessao.isOcupado(a2));
		assertFalse(sessao.isOcupado(a3));
	}
}
