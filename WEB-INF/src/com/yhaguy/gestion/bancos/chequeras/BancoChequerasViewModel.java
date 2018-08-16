package com.yhaguy.gestion.bancos.chequeras;

import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Popup;

import com.coreweb.control.SimpleViewModel;
import com.yhaguy.domain.BancoChequera;
import com.yhaguy.domain.BancoCta;
import com.yhaguy.domain.RegisterDomain;

public class BancoChequerasViewModel extends SimpleViewModel {
	
	private BancoChequera n_chequera = new BancoChequera();
	
	@Init(superclass = true)
	public void init() {
	}
	
	@AfterCompose(superclass = true)
	public void afterCompose() {
	}

	@Command
	@NotifyChange("*")
	public void addChequera(@BindingParam("comp") Popup comp) throws Exception {
		RegisterDomain rr = RegisterDomain.getInstance();
		rr.saveObject(this.n_chequera, this.getLoginNombre());
		Clients.showNotification("REGISTRO AGREGADO..");
		comp.close();
		this.n_chequera = new BancoChequera();
	}
	
	/**
	 * GETS / SETS
	 */
	
	@SuppressWarnings("unchecked")
	public List<BancoChequera> getChequeras() throws Exception {
		RegisterDomain rr = RegisterDomain.getInstance();
		return rr.getObjects(BancoChequera.class.getName());
	}
	
	public List<BancoCta> getBancos() throws Exception {
		RegisterDomain rr = RegisterDomain.getInstance();
		return rr.getBancosCta();
	}
	
	public BancoChequera getN_chequera() {
		return n_chequera;
	}

	public void setN_chequera(BancoChequera n_chequera) {
		this.n_chequera = n_chequera;
	}
}
