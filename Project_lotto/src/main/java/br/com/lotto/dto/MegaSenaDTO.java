package br.com.lotto.dto;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

public class MegaSenaDTO {

	private Integer concurso;
	private Date dataSorteio;
	private String arrecadacaoTotal;
	private Integer ganhadoresSena;
	private String cidade;
	private String uf;
	private BigDecimal rateioSena;
	private Integer ganhadoresQuina;
	private BigDecimal rateioQuina;
	private Integer ganhadoresQuadra;
	private BigDecimal rateioQuadra;
	private Boolean acumulado;
	private BigDecimal valorAcumulado;
	private BigDecimal estimativaPremio;
	private BigDecimal acumuladoMegadaVirada;
	private Collection<NumeroDTO> numerosSorteados;
	public Integer getConcurso() {
		return concurso;
	}
	public void setConcurso(Integer concurso) {
		this.concurso = concurso;
	}
	public Date getDataSorteio() {
		return dataSorteio;
	}
	public void setDataSorteio(Date dataSorteio) {
		this.dataSorteio = dataSorteio;
	}
	public String getArrecadacaoTotal() {
		return arrecadacaoTotal;
	}
	public void setArrecadacaoTotal(String arrecadacaoTotal) {
		this.arrecadacaoTotal = arrecadacaoTotal;
	}
	public Integer getGanhadoresSena() {
		return ganhadoresSena;
	}
	public void setGanhadoresSena(Integer ganhadoresSena) {
		this.ganhadoresSena = ganhadoresSena;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public BigDecimal getRateioSena() {
		return rateioSena;
	}
	public void setRateioSena(BigDecimal rateioSena) {
		this.rateioSena = rateioSena;
	}
	public Integer getGanhadoresQuina() {
		return ganhadoresQuina;
	}
	public void setGanhadoresQuina(Integer ganhadoresQuina) {
		this.ganhadoresQuina = ganhadoresQuina;
	}
	public BigDecimal getRateioQuina() {
		return rateioQuina;
	}
	public void setRateioQuina(BigDecimal rateioQuina) {
		this.rateioQuina = rateioQuina;
	}
	public Integer getGanhadoresQuadra() {
		return ganhadoresQuadra;
	}
	public void setGanhadoresQuadra(Integer ganhadoresQuadra) {
		this.ganhadoresQuadra = ganhadoresQuadra;
	}
	public BigDecimal getRateioQuadra() {
		return rateioQuadra;
	}
	public void setRateioQuadra(BigDecimal rateioQuadra) {
		this.rateioQuadra = rateioQuadra;
	}
	public Boolean getAcumulado() {
		return acumulado;
	}
	public void setAcumulado(Boolean acumulado) {
		this.acumulado = acumulado;
	}
	public BigDecimal getValorAcumulado() {
		return valorAcumulado;
	}
	public void setValorAcumulado(BigDecimal valorAcumulado) {
		this.valorAcumulado = valorAcumulado;
	}
	public BigDecimal getEstimativaPremio() {
		return estimativaPremio;
	}
	public void setEstimativaPremio(BigDecimal estimativaPremio) {
		this.estimativaPremio = estimativaPremio;
	}
	public BigDecimal getAcumuladoMegadaVirada() {
		return acumuladoMegadaVirada;
	}
	public void setAcumuladoMegadaVirada(BigDecimal acumuladoMegadaVirada) {
		this.acumuladoMegadaVirada = acumuladoMegadaVirada;
	}
	public Collection<NumeroDTO> getNumerosSorteados() {
		return numerosSorteados;
	}
	public void setNumerosSorteados(Collection<NumeroDTO> numerosSorteados) {
		this.numerosSorteados = numerosSorteados;
	}
	
	

}
