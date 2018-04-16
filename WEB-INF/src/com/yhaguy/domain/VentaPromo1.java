package com.yhaguy.domain;

import java.util.Date;

import com.coreweb.domain.Domain;

@SuppressWarnings("serial")
public class VentaPromo1 extends Domain {
	
	/**
	 * Promo 2000 por cada bateria / registro de clientes..
	 */

	private Date fecha;
	private Empresa empresa;
	
	@Override
	public int compareTo(Object arg0) {
		return -1;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
