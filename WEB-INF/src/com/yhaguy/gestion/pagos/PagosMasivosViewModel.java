package com.yhaguy.gestion.pagos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.DependsOn;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.util.Clients;

import com.coreweb.control.SimpleViewModel;
import com.coreweb.util.AutoNumeroControl;
import com.coreweb.util.MyArray;
import com.yhaguy.Configuracion;
import com.yhaguy.domain.BancoCheque;
import com.yhaguy.domain.BancoCta;
import com.yhaguy.domain.Caja;
import com.yhaguy.domain.CajaPeriodo;
import com.yhaguy.domain.Funcionario;
import com.yhaguy.domain.Recibo;
import com.yhaguy.domain.ReciboDetalle;
import com.yhaguy.domain.ReciboFormaPago;
import com.yhaguy.domain.RegisterDomain;
import com.yhaguy.util.Utiles;

public class PagosMasivosViewModel extends SimpleViewModel {
	
	private Date desde = new Date();
	private Date hasta;
	
	private List<MyArray> items = new ArrayList<MyArray>();
	private List<MyArray> selectedItems = new ArrayList<MyArray>();

	@Init(superclass = true)
	public void init() {
	}
	
	@AfterCompose(superclass = true)
	public void afterCompose() {
	}
	
	@Command
	@NotifyChange("items")
	public void getMovimientos() throws Exception {
		if (this.desde == null || this.hasta == null) {
			this.items = new ArrayList<>();
		}
		RegisterDomain rr = RegisterDomain.getInstance();
		List<Object[]> list = rr.getSaldosByVencimiento(this.desde, this.hasta, Configuracion.SIGLA_CTA_CTE_CARACTER_MOV_PROVEEDOR, 0, 0, 31);
		Date chequeVto = new Date();
		for (Object[] item : list) {
			double importe = (double) item[9];
			MyArray my = new MyArray(item, Utiles.agregarDias(chequeVto, 30), 1, Utiles.getIVA(importe, 10) * 0.30);
			my.setId((long) item[15]);
			this.items.add(my);
		}
	}
	
	@Command
	@NotifyChange("*")
	public void generarOrdenesPago() throws Exception {
		RegisterDomain rr = RegisterDomain.getInstance();
		Date fechaActual = new Date(); 
		int cant = 0;
		long nro = AutoNumeroControl.getAutoNumero("REC-PAG", true);
		long chequeNro = AutoNumeroControl.getAutoNumero("CHQ-00001", true);
		
		CajaPeriodo planilla = new CajaPeriodo();
		planilla.setApertura(fechaActual);
		planilla.setCaja((Caja) rr.getObject(Caja.class.getName(), 5));
		planilla.setEstado(rr.getTipoPorSigla(Configuracion.SIGLA_CAJA_PERIODO_CERRADA));
		planilla.setNumero("CJP-" + AutoNumeroControl.getAutoNumero("CJP"));
		planilla.setResponsable((Funcionario) rr.getObject(Funcionario.class.getName(), 8));
		planilla.setTipo(CajaPeriodo.TIPO_PAGOS);
		
		for (MyArray item : this.selectedItems) {			
			long idEmpresa = (long) ((Object[]) item.getPos1())[14];
			long idMovimiento = (long) ((Object[]) item.getPos1())[15];
			double importeGs = (double) ((Object[]) item.getPos1())[9];
			Date chequeVto = ((Date) item.getPos2());
			
			Recibo pago = new Recibo();
			pago.setFechaEmision(fechaActual);
			pago.setMoneda(rr.getTipoPorSigla(Configuracion.SIGLA_MONEDA_GUARANI));
			pago.setNumero("REC-PAG-" + nro);
			pago.setProveedor(rr.getProveedorByEmpresa(idEmpresa));
			pago.setSucursal(rr.getSucursalAppById(2));
			pago.setTipoCambio(1);
			pago.setNombreUsuarioCarga(this.getLoginNombre());
			pago.setNumeroRecibo("");
			pago.setNumeroPlanilla(planilla.getNumero());
			pago.setMovimientoBancoActualizado(true);
			pago.setEstadoComprobante(rr.getTipoPorSigla(Configuracion.SIGLA_ESTADO_COMPROBANTE_CONFECCIONADO));
			pago.setTipoMovimiento(rr.getTipoMovimientoBySigla(Configuracion.SIGLA_TM_RECIBO_PAGO));
			pago.setTotalImporteGs(importeGs);
			
			ReciboDetalle det = new ReciboDetalle();
			det.setConcepto("GENERADO PAGOS MASIVOS");
			det.setMontoGs(importeGs);
			det.setMovimiento(rr.getCtaCteEmpresaMovimientoById(idMovimiento));
			
			ReciboFormaPago fp = new ReciboFormaPago();
			fp.setDepositoBancoCta(rr.getBancosCta().get(0));
			fp.setChequeFecha(chequeVto);
			fp.setChequeNumero(chequeNro + "");
			fp.setMoneda(pago.getMoneda());
			fp.setMontoChequeGs(importeGs);
			fp.setMontoGs(importeGs);
			fp.setDescripcion(chequeNro + " - " + fp.getDepositoBancoCta().getBancoDescripcion());
			fp.setTipo(rr.getTipoPorSigla(Configuracion.SIGLA_FORMA_PAGO_CHEQUE_PROPIO));
			
			pago.getDetalles().add(det);
			pago.getFormasPago().add(fp);
			
			rr.saveObject(pago, this.getLoginNombre());
			
			BancoCheque cheque = new BancoCheque();
			cheque.setBanco(fp.getDepositoBancoCta());
			cheque.setBeneficiario(pago.getProveedor().getRazonSocial());
			cheque.setConcepto(pago.getNumero());
			cheque.setEstadoComprobante(pago.getEstadoComprobante());
			cheque.setFechaEmision(fechaActual);
			cheque.setFechaVencimiento(fp.getChequeFecha());
			cheque.setMoneda(fp.getMoneda());
			cheque.setMonto(fp.getMontoGs());
			cheque.setNumero(chequeNro);
			cheque.setNumeroCaja(planilla.getNumero());
			cheque.setNumeroOrdenPago(pago.getNumero());
			cheque.setReciboFormaPago(fp);
			
			rr.saveObject(cheque, this.getLoginNombre());
			planilla.getRecibos().add(pago);
			cant ++; nro ++; chequeNro ++;
		}
		rr.saveObject(planilla, this.getLoginNombre());
		Clients.showNotification("-PLANILLA DE CAJA:" + planilla.getNumero() + " -ORDENES DE PAGO GENERADOS: " + cant);
	}
	
	/**
	 * GETS / SETS
	 */
	
	@DependsOn("selectedItems")
	public List<MyArray> getVistaPrevia() throws Exception {
		List<MyArray> out = new ArrayList<MyArray>();
		long nro = AutoNumeroControl.getAutoNumero("REC-PAG", true);
		long chequeNro = AutoNumeroControl.getAutoNumero("CHQ-00001", true);
		for (MyArray item : this.selectedItems) {
			out.add(new MyArray("REC-PAG-" + nro, item, chequeNro));
			nro ++;
			chequeNro ++;
		}
		return out;
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

	public List<MyArray> getItems() {
		return items;
	}

	public void setItems(List<MyArray> items) {
		this.items = items;
	}

	public List<MyArray> getSelectedItems() {
		return selectedItems;
	}

	public void setSelectedItems(List<MyArray> selectedItems) {
		this.selectedItems = selectedItems;
	}
}
