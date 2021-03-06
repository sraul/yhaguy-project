package com.yhaguy.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.coreweb.domain.Domain;

@SuppressWarnings("serial")
public class ArticuloListaPrecio extends Domain {
	
	public static final long ID_LISTA = 1;
	public static final long ID_MAYORISTA = 3;
	
	public static final String LISTA = "LISTA";
	public static final String MAYORISTA = "MAYORISTA";
	public static final String MINORISTA = "MINORISTA";
	public static final String DOLARES = "U$D";
	
	private String descripcion;
	private String formula;
	private int margen;
	private int rango_descuento_1;
	private int rango_descuento_2;
	private int rango_descuento_3;
	
	private double incremento_repuestos;
	private double incremento_filtros;
	private double incremento_neumaticos;
	private double incremento_lubricantes;
	
	private boolean activo;
	
	private Date desde;
	private Date hasta;
	
	private Set<ArticuloListaPrecioDetalle> detalles = new HashSet<ArticuloListaPrecioDetalle>();

	@Override
	public int compareTo(Object o) {
		return -1;
	}
	
	/**
	 * @return el incremento segun familia..
	 */
	public double getIncremento(String familia) {
		switch (familia) {
		case "REPUESTOS":
			return incremento_repuestos;
		case "FILTROS":
			return incremento_filtros;
		case "LUBRICANTES":
			return incremento_lubricantes;
		case "CUBIERTAS":
			return incremento_neumaticos;
		}
		return 0;
	}
	
	/**
	 * @return true si el precio es vigente..
	 */
	public boolean isVigente() {
		Date hoy = new Date();
		return (hoy.after(this.desde) && this.hasta == null) || hoy.after(this.desde) && hoy.before(this.hasta);		
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getMargen() {
		return margen;
	}

	public void setMargen(int margen) {
		this.margen = margen;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public Set<ArticuloListaPrecioDetalle> getDetalles() {
		return detalles;
	}

	public void setDetalles(Set<ArticuloListaPrecioDetalle> detalles) {
		this.detalles = detalles;
	}

	public Date getDesde() {
		return desde;
	}

	public void setDesde(Date desde) {
		this.desde = desde;
	}

	public Date getHasta() {
		return hasta;
	}

	public void setHasta(Date hasta) {
		this.hasta = hasta;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public int getRango_descuento_1() {
		return rango_descuento_1;
	}

	public void setRango_descuento_1(int rango_descuento_1) {
		this.rango_descuento_1 = rango_descuento_1;
	}

	public int getRango_descuento_2() {
		return rango_descuento_2;
	}

	public void setRango_descuento_2(int rango_descuento_2) {
		this.rango_descuento_2 = rango_descuento_2;
	}

	public int getRango_descuento_3() {
		return rango_descuento_3;
	}

	public void setRango_descuento_3(int rango_descuento_3) {
		this.rango_descuento_3 = rango_descuento_3;
	}

	public double getIncremento_repuestos() {
		return incremento_repuestos;
	}

	public void setIncremento_repuestos(double incremento_repuestos) {
		this.incremento_repuestos = incremento_repuestos;
	}

	public double getIncremento_filtros() {
		return incremento_filtros;
	}

	public void setIncremento_filtros(double incremento_filtros) {
		this.incremento_filtros = incremento_filtros;
	}

	public double getIncremento_neumaticos() {
		return incremento_neumaticos;
	}

	public void setIncremento_neumaticos(double incremento_neumaticos) {
		this.incremento_neumaticos = incremento_neumaticos;
	}

	public double getIncremento_lubricantes() {
		return incremento_lubricantes;
	}

	public void setIncremento_lubricantes(double incremento_lubricantes) {
		this.incremento_lubricantes = incremento_lubricantes;
	}
}
