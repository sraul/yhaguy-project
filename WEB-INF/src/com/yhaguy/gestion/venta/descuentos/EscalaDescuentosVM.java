package com.yhaguy.gestion.venta.descuentos;

import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.DependsOn;
import org.zkoss.bind.annotation.Init;

import com.coreweb.control.SimpleViewModel;
import com.yhaguy.domain.Articulo;
import com.yhaguy.domain.ArticuloFamilia;
import com.yhaguy.domain.Cliente;
import com.yhaguy.domain.EmpresaRubro;
import com.yhaguy.domain.RegisterDomain;

public class EscalaDescuentosVM extends SimpleViewModel {
	
	private EmpresaRubro selectedRubro;
	private EmpresaRubro selectedRubro_;
	private String filterRuc = "";
	private String filterRazonSocial = "";

	@Init(superclass = true)
	public void init() {
	}
	
	@AfterCompose(superclass = true)
	public void afterCompose() {
	}
	
	@Command
	public void updateRubro(@BindingParam("item") Object[] item) throws Exception {
		String flia = (String) item[0];
		double dto = (double) item[1];
		switch (flia) {
		case Articulo.FAMILIA_BATERIAS:
			this.selectedRubro_.setDescuentoBaterias(dto);
			break;
		case Articulo.FAMILIA_NEUMATICOS:
			this.selectedRubro_.setDescuentoCubiertas(dto);
			break;
		case Articulo.FAMILIA_FILTROS:
			this.selectedRubro_.setDescuentoFiltros(dto);
			break;
		case Articulo.FAMILIA_LUBRICANTES:
			this.selectedRubro_.setDescuentoLubricantes(dto);
			break;
		case Articulo.FAMILIA_REPUESTOS:
			this.selectedRubro_.setDescuentoRepuestos(dto);
			break;
		}
		RegisterDomain rr = RegisterDomain.getInstance();
		rr.saveObject(this.selectedRubro_, this.getLoginNombre());
	}
	
	@Command
	public void updateCliente(@BindingParam("cliente") Cliente cliente) throws Exception {
		RegisterDomain rr = RegisterDomain.getInstance();
		rr.saveObject(cliente, this.getLoginNombre());
	}
	
	/**
	 * GETS / SETS
	 */
	
	/**
	 * @return los rubros..
	 */
	public List<EmpresaRubro> getRubros() throws Exception {
		RegisterDomain rr = RegisterDomain.getInstance();
		return rr.getEmpresaRubros();
	}
	
	/**
	 * @return las familias..
	 */
	public List<ArticuloFamilia> getFamilias() throws Exception {
		RegisterDomain rr = RegisterDomain.getInstance();
		return rr.getArticuloFamilias();
	}

	@DependsOn({ "selectedRubro", "filterRuc", "filterRazonSocial" })
	public List<Cliente> getClientes() throws Exception {
		String rubro = this.selectedRubro != null ? this.selectedRubro.getDescripcion() : "";
		RegisterDomain rr = RegisterDomain.getInstance();
		return rr.getClientesPorRubro(rubro, this.filterRuc, this.filterRazonSocial);
	}
	
	public EmpresaRubro getSelectedRubro() {
		return selectedRubro;
	}

	public void setSelectedRubro(EmpresaRubro selectedRubro) {
		this.selectedRubro = selectedRubro;
	}

	public String getFilterRuc() {
		return filterRuc;
	}

	public void setFilterRuc(String filterRuc) {
		this.filterRuc = filterRuc;
	}

	public String getFilterRazonSocial() {
		return filterRazonSocial;
	}

	public void setFilterRazonSocial(String filterRazonSocial) {
		this.filterRazonSocial = filterRazonSocial;
	}

	public EmpresaRubro getSelectedRubro_() {
		return selectedRubro_;
	}

	public void setSelectedRubro_(EmpresaRubro selectedRubro_) {
		this.selectedRubro_ = selectedRubro_;
	}
}
