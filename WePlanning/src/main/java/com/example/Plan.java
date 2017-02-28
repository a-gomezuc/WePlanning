package com.example;

import java.util.Date;

public class Plan {
	private long id;
	private String titulo;
	private String categoria;
	private String autor;
	private String lugar;
	private String direccion;
	private long precio;
	private Date fecha;
	private String descripcion;
	// private List<Usuario> asistentes= new ArrayList<>();
	public Plan() {

	}

	public Plan(long id, String titulo, String categoria, String autor, String lugar, String direccion, long precio,
			Date fecha, String descripcion) {
		this.id = id;
		this.titulo = titulo;
		this.categoria = categoria;
		this.autor = autor;
		this.lugar = lugar;
		this.direccion = direccion;
		this.precio = precio;
		this.fecha = fecha;
		this.descripcion = descripcion;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public long getPrecio() {
		return precio;
	}

	public void setPrecio(long precio) {
		this.precio = precio;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
