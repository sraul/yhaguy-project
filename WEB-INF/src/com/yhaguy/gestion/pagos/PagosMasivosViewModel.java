package com.yhaguy.gestion.pagos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.DependsOn;
import org.zkoss.bind.annotation.Init;

import com.coreweb.control.SimpleViewModel;
import com.yhaguy.Configuracion;
import com.yhaguy.domain.BancoCta;
import com.yhaguy.domain.RegisterDomain;

public class PagosMasivosViewModel extends SimpleViewModel {
	
	private Date desde = new Date();
	private Date hasta;

	@Init(superclass = true)
	public void init() {
	}
	
	@AfterCompose(superclass = true)
	public void afterCompose() {
	}
	
	/**
	 * GETS / SETS
	 */
	
	@DependsOn({ "desde", "hasta" })
	public List<Object[]> getMovimientos() throws Exception {
		if (this.desde == null || this.hasta == null) {
			return new ArrayList<>();
		}
		RegisterDomain rr = RegisterDomain.getInstance();
		return rr.getSaldosByVencimiento(this.desde, this.hasta, Configuracion.SIGLA_CTA_CTE_CARACTER_MOV_PROVEEDOR, 0, 0, 31);
	}
	
	/**
	 * @return los bancos..
	 */
	public List<BancoCta> getBancos() throws Exception {
		RegisterDomain rr = RegisterDomain.getInstance();
		return rr.getBancosCta();
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
}
