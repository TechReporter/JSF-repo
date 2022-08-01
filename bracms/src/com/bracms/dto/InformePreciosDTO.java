package com.bracms.dto;

public class InformePreciosDTO {
	
	private int idRetrospectiva;
	private String titulo;
	private String descripcion;
	private String activo;
	
	public InformePreciosDTO(){}
	
	public int getIdRetrospectiva() {
		return idRetrospectiva;
	}
	public void setIdRetrospectiva(int idRetrospectiva) {
		this.idRetrospectiva = idRetrospectiva;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getActivo() {
		return activo;
	}
	public void setActivo(String activo) {
		this.activo = activo;
	}
	

}
