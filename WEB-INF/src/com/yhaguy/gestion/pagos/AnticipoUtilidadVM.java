package com.yhaguy.gestion.pagos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Popup;

import com.coreweb.control.SimpleViewModel;
import com.coreweb.util.AutoNumeroControl;
import com.yhaguy.domain.AnticipoUtilidad;
import com.yhaguy.domain.RegisterDomain;

public class AnticipoUtilidadVM extends SimpleViewModel {

	private AnticipoUtilidad nvo_anticipo;
	
	@Init(superclass = true)
	public void init() {
		try {
			this.inicializar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@AfterCompose(superclass = true)
	public void afterCompose() {
	}

	@Command
	@NotifyChange("*")
	public void addAnticipo(@BindingParam("comp") Popup comp) throws Exception {
		RegisterDomain rr = RegisterDomain.getInstance();
		rr.saveObject(this.nvo_anticipo, this.getLoginNombre());
		comp.close();
		Clients.showNotification("REGISTRO GUARDADO..");
		this.inicializar();
	}
	
	/**
	 * inicializacion de datos..
	 */
	private void inicializar() throws Exception {
		this.nvo_anticipo = new AnticipoUtilidad();
		this.nvo_anticipo.setNumero("ANT-" + AutoNumeroControl.getAutoNumero("ANT", 5, true));
		this.nvo_anticipo.setFecha(new Date());
	}
	
	/**
	 * GETS / SETS
	 */
	
	@SuppressWarnings("unchecked")
	public List<AnticipoUtilidad> getAnticipos() throws Exception {
		RegisterDomain rr = RegisterDomain.getInstance();
		return rr.getObjects(AnticipoUtilidad.class.getName());
	}
	
	public List<String> getDatos() {
		List<String> out = new ArrayList<>();
		out.add("FEDERICO FRUTOS"); 
		out.add("ESTELA DE FRUTOS");
		out.add("RAUL FRUTOS");
		out.add("JAZMIN FRUTOS");
		return out;
	}
	
	public AnticipoUtilidad getNvo_anticipo() {
		return nvo_anticipo;
	}

	public void setNvo_anticipo(AnticipoUtilidad nvo_anticipo) {
		this.nvo_anticipo = nvo_anticipo;
	}
}
