package com.yhaguy.gestion.mobile.appstore;

import java.util.Date;
import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;

import com.coreweb.control.SimpleViewModel;
import com.yhaguy.domain.Empresa;
import com.yhaguy.domain.RegisterDomain;
import com.yhaguy.domain.VentaPromo1;

public class Promo1ViewModel extends SimpleViewModel {
	
	private String ruc = "";
	private String razonSocial = "";
	private String mensaje = "";
	private Empresa empresa;
	
	@Init(superclass = true)
	public void init() {
	}
	
	@AfterCompose(superclass = true)
	public void afterCompose() {
	}
	
	@Command
	@NotifyChange("*")
	public void registrarse() throws Exception {
		if (this.chequearRuc()) {
			if (!chequearRegistro()) {
				this.registrar();
			} else {
				this.mensaje = "CLIENTE YA FUE REGISTRADO : " + this.empresa.getRazonSocial();
			}
		} else {
			this.mensaje = "CLIENTE NO ENCONTRADO..";
		}
	}

	/**
	 * chequea el ruc en la bd..
	 */
	private boolean chequearRuc() throws Exception {
		if (this.ruc.trim().isEmpty()) {
			return false;
		}
		RegisterDomain rr = RegisterDomain.getInstance();
		List<Empresa> emps = rr.getEmpresasByRuc(this.ruc);
		if(emps.size() > 0) {
			this.razonSocial = emps.get(0).getRazonSocial();
			this.empresa = emps.get(0);
		}
		return emps.size() > 0;
	}
	
	/**
	 * chequea el ruc en la bd..
	 */
	private boolean chequearRegistro() throws Exception {
		RegisterDomain rr = RegisterDomain.getInstance();
		List<VentaPromo1> promos = rr.getVentaPromo1Registros(this.ruc);	
		return promos.size() > 0;
	}
	
	/**
	 * realiza el registro..
	 */
	private void registrar() throws Exception {
		RegisterDomain rr = RegisterDomain.getInstance();
		VentaPromo1 promo = new VentaPromo1();
		promo.setEmpresa(this.empresa);
		promo.setFecha(new Date());
		rr.saveObject(promo, "mobile");
		this.mensaje = "CLIENTE CORRECTAMENTE REGISTRADO: " + promo.getEmpresa().getRazonSocial();
	}
	
	/**
	 * GETS / SETS..
	 */
	
	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}
