package com.yhaguy.gestion.mobile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.DependsOn;
import org.zkoss.bind.annotation.Init;

import com.coreweb.control.SimpleViewModel;
import com.coreweb.domain.Tipo;
import com.coreweb.util.AutoNumeroControl;
import com.yhaguy.Configuracion;
import com.yhaguy.domain.Articulo;
import com.yhaguy.domain.Deposito;
import com.yhaguy.domain.RegisterDomain;
import com.yhaguy.domain.Transferencia;

public class TransferenciasMobileVM extends SimpleViewModel {
	
	private String filterCodigo = "";
	
	private Transferencia transferencia;
	private Articulo selectedArticulo;

	@Init(superclass = true)
	public void init() {
		try {
			this.inicializarTransferencia();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@AfterCompose(superclass = true)
	public void afterCompose() {
	}
	
	private void inicializarTransferencia() throws Exception {
		String key = Configuracion.NRO_TRANSFERENCIA_INTERNA;
		RegisterDomain rr = RegisterDomain.getInstance();
		this.transferencia = new Transferencia();
		this.transferencia.setFechaCreacion(new Date());
		this.transferencia.setSucursal(rr.getSucursalAppById(2));
		this.transferencia.setNumero(key + "-" + AutoNumeroControl.getAutoNumero(key, 7, true));
		this.transferencia.setTransferenciaTipo(rr.getTipoPorSigla(Configuracion.SIGLA_TM_TRANSF_INTERNA));
	}

	/**
	 * GETS / SETS
	 */
	
	@DependsOn("filterCodigo")
	public List<Articulo> getArticulos() throws Exception {
		if (this.filterCodigo.isEmpty()) {
			return new ArrayList<Articulo>();
		}
		RegisterDomain rr = RegisterDomain.getInstance();		
		return rr.getArticulos(this.filterCodigo, 50);
	}
	
	/**
	 * @return los tipos de transferencia..
	 */
	public List<Tipo> getTiposTransferencia() throws Exception {
		List<Tipo> out = new ArrayList<Tipo>();
		RegisterDomain rr = RegisterDomain.getInstance();
		out.add(rr.getTipoPorSigla(Configuracion.SIGLA_TM_TRANSF_INTERNA));
		out.add(rr.getTipoPorSigla(Configuracion.SIGLA_TM_TRANSF_EXTERNA));
		return out;
	}
	
	@DependsOn("transferencia")
	public List<Deposito> getDepositosOrigen() throws Exception {
		RegisterDomain rr = RegisterDomain.getInstance();
		List<Deposito> out = rr.getDepositosPorSucursal((long) 2);
		out.remove(this.transferencia.getDepositoSalida());
		return out;
	}
	
	@DependsOn("transferencia")
	public List<Deposito> getDepositosDestino() throws Exception {
		RegisterDomain rr = RegisterDomain.getInstance();
		List<Deposito> out = rr.getDepositosPorSucursal((long) 2);
		out.remove(this.transferencia.getDepositoEntrada());
		return out;
	}
	
	public Articulo getSelectedArticulo() {
		return selectedArticulo;
	}

	public void setSelectedArticulo(Articulo selectedArticulo) {
		this.selectedArticulo = selectedArticulo;
	}

	public String getFilterCodigo() {
		return filterCodigo;
	}

	public void setFilterCodigo(String filterCodigo) {
		this.filterCodigo = filterCodigo;
	}

	public Transferencia getTransferencia() {
		return transferencia;
	}

	public void setTransferencia(Transferencia transferencia) {
		this.transferencia = transferencia;
	}	
}
