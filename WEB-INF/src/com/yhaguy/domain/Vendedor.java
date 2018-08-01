package com.yhaguy.domain;

import com.coreweb.domain.Domain;

@SuppressWarnings("serial")
public class Vendedor extends Domain {
	
	public static final String VENDEDOR_EXTERNO = "EXTERNO";
	public static final String VENDEDOR_AUTOCENTRO = "AUTOCENTRO";
	public static final String VENDEDOR_MOSTRADOR = "MOSTRADOR";
	public static final String VENDEDOR_ADMINISTRACION = "ADM";

	private String nombre;
	private String dependencia;
	private String zona;
	private String supervisor;
	private String funcionario;
	
	@Override
	public int compareTo(Object o) {
		return -1;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDependencia() {
		return dependencia;
	}

	public void setDependencia(String dependencia) {
		this.dependencia = dependencia;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public String getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}

	public String getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(String funcionario) {
		this.funcionario = funcionario;
	}
}
