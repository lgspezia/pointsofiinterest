package com.poi.springbootjpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vehicles")
public class Poi {

	private long id;
	
	private String nome;
	private Long raio;
	private Long longitude;
	private Long latitude;	
	
	public Poi() {
		
	}	
	
	public Poi(String nome, Long raio, Long longitude, Long latitude) {
		this.nome = nome;
		this.raio = raio;
		this.longitude = longitude;
		this.latitude = latitude;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}


	@Column(name = "nome", nullable = false)
	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	@Column(name = "raio", nullable = false)
	public Long getRaio() {
		return raio;
	}


	public void setRaio(Long raio) {
		this.raio = raio;
	}

	@Column(name = "longitude", nullable = false)
	public Long getLongitude() {
		return longitude;
	}


	public void setLongitude(Long longitude) {
		this.longitude = longitude;
	}

	@Column(name = "latitude", nullable = false)
	public Long getLatitude() {
		return latitude;
	}


	public void setLatitude(Long latitude) {
		this.latitude = latitude;
	}
	
	
	
	@Override
	public String toString() {
		return "Poi [id=" + id + ", nome=" + nome + ", long=" + longitude +
				", lat=" + latitude + ", raio=" + raio + "]";
	}
	
}
