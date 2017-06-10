package br.com.caelum.ingresso.model;

import java.time.LocalTime;
import java.util.List;

public class GerenciadorDeSessao {
	private List<Sessao> sessoesDaSala;

	public GerenciadorDeSessao(List<Sessao> sessoesDaSala) {
		super();
		this.sessoesDaSala = sessoesDaSala;
	}
	
	public boolean cabe(final Sessao sessaoAtual) {
		return sessoesDaSala.stream()
				.map(sessaoExistente -> horarioIsValido(sessaoExistente, sessaoAtual))
				.reduce(Boolean::logicalAnd)
				.orElse(true);
	}
	
	private boolean horarioIsValido(Sessao sessaoExistente, Sessao sessaoAtual) {
		LocalTime horarioSessao = sessaoExistente.getHorario();
		LocalTime horarioAtual = sessaoAtual.getHorario();
		
		boolean ehAntes =  horarioAtual.isBefore(horarioSessao);
		
		if (ehAntes)
			return sessaoAtual.getHorarioTermino().isBefore(sessaoExistente.getHorario());
		else
			return sessaoExistente.getHorarioTermino().isBefore(horarioAtual);
	}
}
