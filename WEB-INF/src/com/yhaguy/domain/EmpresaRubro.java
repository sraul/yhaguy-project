package com.yhaguy.domain;

import java.util.ArrayList;
import java.util.List;

import com.coreweb.domain.Domain;

@SuppressWarnings("serial")
public class EmpresaRubro extends Domain {

	private String descripcion;
	private String subrubro; // para definir segmentaciones adicionales
	
	private double descuentoBaterias;
	private double descuentoCubiertas;
	private double descuentoFiltros;
	private double descuentoLubricantes;
	private double descuentoRepuestos;
	
	@Override
	public int compareTo(Object arg0) {
		return -1;
	}
	
	/**
	 * @return la escala de descuentos..
	 */
	public List<Object[]> getEscalaDescuentos() {
		List<Object[]> out = new ArrayList<Object[]>();
		Object[] bat = { Articulo.FAMILIA_BATERIAS, this.descuentoBaterias };
		Object[] cub = { Articulo.FAMILIA_NEUMATICOS, this.descuentoCubiertas };
		Object[] fil = { Articulo.FAMILIA_FILTROS, this.descuentoFiltros };
		Object[] lub = { Articulo.FAMILIA_LUBRICANTES, this.descuentoLubricantes };
		Object[] rep = { Articulo.FAMILIA_REPUESTOS, this.descuentoRepuestos };
		out.add(bat);
		out.add(cub);
		out.add(fil);
		out.add(lub);
		out.add(rep);
		return out;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getSubrubro() {
		return subrubro;
	}

	public void setSubrubro(String subrubro) {
		this.subrubro = subrubro;
	}

	public double getDescuentoBaterias() {
		return descuentoBaterias;
	}

	public void setDescuentoBaterias(double descuentoBaterias) {
		this.descuentoBaterias = descuentoBaterias;
	}

	public double getDescuentoCubiertas() {
		return descuentoCubiertas;
	}

	public void setDescuentoCubiertas(double descuentoCubiertas) {
		this.descuentoCubiertas = descuentoCubiertas;
	}

	public double getDescuentoFiltros() {
		return descuentoFiltros;
	}

	public void setDescuentoFiltros(double descuentoFiltros) {
		this.descuentoFiltros = descuentoFiltros;
	}

	public double getDescuentoLubricantes() {
		return descuentoLubricantes;
	}

	public void setDescuentoLubricantes(double descuentoLubricantes) {
		this.descuentoLubricantes = descuentoLubricantes;
	}

	public double getDescuentoRepuestos() {
		return descuentoRepuestos;
	}

	public void setDescuentoRepuestos(double descuentoRepuestos) {
		this.descuentoRepuestos = descuentoRepuestos;
	}
}
