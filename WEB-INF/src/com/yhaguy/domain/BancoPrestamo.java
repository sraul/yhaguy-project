package com.yhaguy.domain;

import java.util.Date;

import org.zkoss.bind.annotation.DependsOn;

import com.coreweb.domain.Domain;
import com.coreweb.domain.Tipo;

@SuppressWarnings("serial")
public class BancoPrestamo extends Domain {

	private Date fecha;
	private String numero;
	private int cuotas;
	private double capital; // monto del prestamo..
	private double interes;
	private double impuestos;
	private double gastosAdministrativos;
	private double seguro;
	
	private BancoCta banco;
	private Empresa ctacte;
	private Tipo moneda;
	
	@Override
	public int compareTo(Object arg0) {
		return -1;
	}
	
	@DependsOn({ "capital", "interes" })
	public double getDeudaTotal() {
		return this.capital + this.interes;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public int getCuotas() {
		return cuotas;
	}

	public void setCuotas(int cuotas) {
		this.cuotas = cuotas;
	}

	public double getCapital() {
		return capital;
	}

	public void setCapital(double capital) {
		this.capital = capital;
	}

	public double getInteres() {
		return interes;
	}

	public void setInteres(double interes) {
		this.interes = interes;
	}

	public double getImpuestos() {
		return impuestos;
	}

	public void setImpuestos(double impuestos) {
		this.impuestos = impuestos;
	}

	public double getGastosAdministrativos() {
		return gastosAdministrativos;
	}

	public void setGastosAdministrativos(double gastosAdministrativos) {
		this.gastosAdministrativos = gastosAdministrativos;
	}

	public double getSeguro() {
		return seguro;
	}

	public void setSeguro(double seguro) {
		this.seguro = seguro;
	}

	public BancoCta getBanco() {
		return banco;
	}

	public void setBanco(BancoCta banco) {
		this.banco = banco;
	}

	public Tipo getMoneda() {
		return moneda;
	}

	public void setMoneda(Tipo moneda) {
		this.moneda = moneda;
	}

	public Empresa getCtacte() {
		return ctacte;
	}

	public void setCtacte(Empresa ctacte) {
		this.ctacte = ctacte;
	}
}
