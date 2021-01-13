package com.poi.springbootjpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vehicles")
public class Vehicle {

	private long id;
	
	private String data;
	private String placa;
	private Long velocidade;

	private Long longitude;
	private Long latitude;
	private Boolean ligado;
	
	public Vehicle() {
		
	}
	
	
	public Vehicle(String data, String placa, Long velocidade, Long longitude, Long latitude, Boolean ligado) {
		this.data = data;
		this.placa = placa;
		this.velocidade = velocidade;
		this.longitude = longitude;
		this.latitude = latitude;
		this.ligado = ligado;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}


	@Column(name = "data_posicao", nullable = false)
	public String getData() {
		return data;
	}


	public void setData(String data) {
		this.data = data;
	}

	@Column(name = "placa", nullable = false)
	public String getPlaca() {
		return placa;
	}


	public void setPlaca(String placa) {
		this.placa = placa;
	}

	@Column(name = "velocidade", nullable = false)
	public Long getVelocidade() {
		return velocidade;
	}


	public void setVelocidade(Long velocidade) {
		this.velocidade = velocidade;
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

	@Column(name = "ignicao", nullable = false)
	public Boolean isLigado() {
		return ligado;
	}


	public void setLigado(Boolean ligado) {
		this.ligado = ligado;
	}
	
	
	
	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", placa=" + placa + ", data=" + data + ", long=" + longitude +
				", lat=" + latitude + ", ligado=" + ligado + "]";
	}
	
}
