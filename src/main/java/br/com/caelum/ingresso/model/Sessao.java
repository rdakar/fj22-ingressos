package br.com.caelum.ingresso.model;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Sessao {

    @Id
    @GeneratedValue
    private Integer id;
    
    @ManyToOne
    private Sala sala;
    
    @ManyToOne
    private Filme filme;
    
    @NotNull
    private LocalTime horario;
    
    private BigDecimal preco;
    
    public Sessao() {
    	
    }
    
    public Sessao(LocalTime horario, Filme filme, Sala sala) {
    	this.horario = horario;
    	this.filme = filme;
    	this.sala = sala;
    	this.preco = sala.getPreco().add(filme.getPreco());
    }
    
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public Filme getFilme() {
		return filme;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}

	public LocalTime getHorario() {
		return horario;
	}

	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}
	
	public LocalTime getHorarioTermino() {
		return this.horario.plus(filme.getDuracao().toMinutes(), ChronoUnit.MINUTES);
	}
	
	public BigDecimal getPreco() {
		return preco;
	}
}
