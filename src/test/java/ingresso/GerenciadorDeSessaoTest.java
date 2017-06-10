package ingresso;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.GerenciadorDeSessao;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;

public class GerenciadorDeSessaoTest {
	
	@Test
	public void deveAdicionarSeListaDaSessaoEstiverVazia() {
		List<Sessao> sessoes = Collections.emptyList();
		GerenciadorDeSessao gerenciadorDeSessoes = new GerenciadorDeSessao(sessoes);
		Filme filme = new Filme("Ninja Gaiden", Duration.ofMinutes(120), "Muita ação", BigDecimal.valueOf(10));
		filme.setDuracao(120);
		LocalTime horario = LocalTime.now();
		Sala sala = new Sala("3D Emotion", BigDecimal.valueOf(20));
		Sessao sessao = new Sessao(horario, filme, sala);
		boolean cabe = gerenciadorDeSessoes.cabe(sessao);
		Assert.assertTrue(cabe);
	}
	
	@Test
	public void garanteQueNaoDevePermitirSessaoNoMesmoHorario() {
		Filme filme = new Filme("Ninja Gaiden", Duration.ofMinutes(120), "Muita ação", BigDecimal.valueOf(10));
		filme.setDuracao(120);
		LocalTime horario = LocalTime.now();
		
		Sala sala = new Sala("3D Emotion", BigDecimal.valueOf(20));
		List<Sessao> sessoes = Arrays.asList(new Sessao(horario, filme, sala));
		
		Sessao sessao = new Sessao(horario, filme, sala);
		
		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoes);
		
		Assert.assertFalse(gerenciador.cabe(sessao));
	}
	
	@Test
	public void garanteQueNaoDevePermitirSessoesIniciandoDentroDoHorarioDeUmaSessaoJaExistente() {
		Filme filme = new Filme("Ninja Gaiden", Duration.ofMinutes(120), "Muita ação", BigDecimal.valueOf(10));
		filme.setDuracao(120);
		LocalTime horario = LocalTime.now();
		Sala sala = new Sala("3D Emotion", BigDecimal.valueOf(20));
		List<Sessao> sessoesDaSala =  Collections.singletonList(new Sessao(horario, filme, sala));
		GerenciadorDeSessao gerenciadorDeSessoes = new GerenciadorDeSessao(sessoesDaSala);
		Assert.assertFalse(gerenciadorDeSessoes.cabe(new Sessao(horario.plus(1, ChronoUnit.HOURS), filme, sala)));
	}
	
	@Test
	public void garanteQueNaoDevePermitirSessoesTerminandoDentroDoHorarioDeUmaSessaoJaExistente() {
		Filme filme = new Filme("Ninja Gaiden", Duration.ofMinutes(120), "Muita ação", BigDecimal.valueOf(10));
		filme.setDuracao(120);
		LocalTime horario = LocalTime.now();
		
		Sala sala = new Sala("3D Emotion", BigDecimal.valueOf(20));
		List<Sessao> sessoes = Arrays.asList(new Sessao(horario, filme, sala));
		
		Sessao sessao = new Sessao(horario.plusHours(1), filme, sala);
		
		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoes);
		
		Assert.assertFalse(gerenciador.cabe(sessao));
	}
	
	@Test
	public void garanteQueDevePermitirUmaInsercaoentreDoisFilmes(){
		Sala sala = new Sala("3D Emotion", BigDecimal.valueOf(20));
		Filme filme = new Filme("Ninja Gaiden", Duration.ofMinutes(90), "Muita ação", BigDecimal.valueOf(10));
		filme.setDuracao(90);
		LocalTime dezHoras = LocalTime.parse("10:00:00");
		Sessao sessaoDasDez = new Sessao(dezHoras, filme, sala);
		Filme outroFilme = new Filme("Ninja Gaiden 2", Duration.ofMinutes(120), "Muita ação", BigDecimal.valueOf(10));
		outroFilme.setDuracao(120);
		LocalTime dezoitoHoras = LocalTime.parse("18:00:00");
		Sessao sessaoDasDezoito = new Sessao(dezoitoHoras, outroFilme, sala);
		List<Sessao> sessoes = Arrays.asList(sessaoDasDez,sessaoDasDezoito);
		GerenciadorDeSessao gerenciadorDeSessoes = new GerenciadorDeSessao(sessoes);
		Assert.assertTrue(gerenciadorDeSessoes.cabe(new Sessao(LocalTime.parse("13:00:00"), outroFilme, sala)));
	}
}
