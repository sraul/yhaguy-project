package com.yhaguy.gestion.compras.locales;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.zkoss.bind.annotation.DependsOn;

import com.coreweb.dto.DTO;
import com.coreweb.util.MyArray;
import com.coreweb.util.MyPair;
import com.yhaguy.Configuracion;
import com.yhaguy.gestion.empresa.ProveedorDTO;

@SuppressWarnings("serial")
public class CompraLocalFacturaDTO extends DTO{
	
	private String numero = "";
	private Date fechaCreacion = new Date();
	private Date fechaOriginal;
	private Date fechaVencimiento;
	private double tipoCambio = 0;
	private String observacion = "";
	private double descuentoGs = 0;
	private double descuentoDs = 0;
	private double totalAsignadoGs = 0;
	private double totalAsignadoDs = 0;
	private boolean recepcionConfirmada = false;
	private int condicionPagoDias = 0;
	private String recepcionConfirmadaPor = "";
	
	private boolean conteo1 = false;
	private boolean conteo2 = false;
	private boolean conteo3 = false;
	
	private String tiempoConteo1 = "";
	private String tiempoConteo2 = "";
	private String tiempoConteo3 = "";
	
	private ProveedorDTO proveedor = new ProveedorDTO();
	private MyArray condicionPago = new MyArray();
	private MyArray moneda = new MyArray();
	private MyPair sucursal = new MyPair();	
	private MyArray tipoMovimiento = new MyArray();
	private MyArray timbrado = new MyArray("", new Date());
	private List<CompraLocalFacturaDetalleDTO> detalles = new ArrayList<CompraLocalFacturaDetalleDTO>();
	
	/**
	 * @return true si es una compra en moneda local..
	 */
	public boolean isMonedaLocal() {
		String sigla = (String) this.moneda.getPos2();
		return sigla.equals(Configuracion.SIGLA_MONEDA_GUARANI);
	}
	
	@DependsOn("detalles")
	public double getTotalImporteGs() {
		double out = 0;		
		for (CompraLocalFacturaDetalleDTO item : this.getDetalles()) {
			out += item.getImporteGs();
		}		
		return out;
	}
	
	@DependsOn("detalles")
	public double getTotalImporteDs() {
		double out = 0;		
		for (CompraLocalFacturaDetalleDTO item : this.getDetalles()) {
			out += item.getImporteDs();
		}		
		return out;
	}
	
	@DependsOn("detalles")
	public double getTotalIva10() {
		double out = 0;		
		for (CompraLocalFacturaDetalleDTO item : this.getDetalles()) {
			if(item.isIva10())
				out += item.getImporteIva();
		}		
		return out - this.getDescuentoIva10();
	}
	
	@DependsOn("detalles")
	public double getTotalIva5() {
		double out = 0;		
		for (CompraLocalFacturaDetalleDTO item : this.getDetalles()) {
			if(item.isIva5())
				out += item.getImporteIva();
		}		
		return out - this.getDescuentoIva5();
	}
	
	/**
	 * @return descuento prorrateado al iva 10..
	 */
	private double getDescuentoIva10() {
		int countIva10 = 0;
		double totalDescuento = 0;
		for (CompraLocalFacturaDetalleDTO item : this.getDetalles()) {
			if (item.isIva10())
				countIva10++;
			if (item.isDescuento())
				totalDescuento += (item.getImporteGs() * -1);
		}
		if(totalDescuento == 0 || countIva10 == 0)
			return 0;
		return ((totalDescuento / (countIva10)) / 11) * countIva10;
	}
	
	/**
	 * @return descuento prorrateado al iva 5..
	 */
	private double getDescuentoIva5() {
		int countIva5 = 0;
		double totalDescuento = 0;
		for (CompraLocalFacturaDetalleDTO item : this.getDetalles()) {
			if (item.isIva5())
				countIva5++;
			if (item.isDescuento()) 
				totalDescuento += (item.getImporteGs() * -1);
		}
		if(totalDescuento == 0 || countIva5 == 0)
			return 0;
		return ((totalDescuento / (countIva5)) / 21) * countIva5;
	}
	
	/**
	 * @return el detalle ordenado..
	 */
	public List<CompraLocalFacturaDetalleDTO> getDetalles() {
		Collections.sort(this.detalles,
				new Comparator<CompraLocalFacturaDetalleDTO>() {
					@Override
					public int compare(CompraLocalFacturaDetalleDTO o1,
							CompraLocalFacturaDetalleDTO o2) {
						long id1 = o1.getId().longValue();
						long id2 = o2.getId().longValue();
						if (id1 < 0) {
							return 1;
						}
						return (int) (id1 - id2);
					}
				});
		return detalles;
	}
	
	public String getNumero() {
		return numero;
	}
	
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	public Date getFechaOriginal() {
		return fechaOriginal;
	}
	
	public void setFechaOriginal(Date fechaOriginal) {
		this.fechaOriginal = fechaOriginal;
	}
	
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	
	public double getTipoCambio() {
		return tipoCambio;
	}
	
	public void setTipoCambio(double tipoCambio) {
		this.tipoCambio = tipoCambio;
	}
	
	public String getObservacion() {
		return observacion;
	}
	
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
	public double getDescuentoGs() {
		return descuentoGs;
	}

	public void setDescuentoGs(double descuentoGs) {
		this.descuentoGs = descuentoGs;
	}

	public double getDescuentoDs() {
		return descuentoDs;
	}

	public void setDescuentoDs(double descuentoDs) {
		this.descuentoDs = descuentoDs;
	}

	public double getTotalAsignadoGs() {
		return totalAsignadoGs;
	}

	public void setTotalAsignadoGs(double totalAsignadoGs) {
		this.totalAsignadoGs = totalAsignadoGs;
	}

	public double getTotalAsignadoDs() {
		return totalAsignadoDs;
	}

	public void setTotalAsignadoDs(double totalAsignadoDs) {
		this.totalAsignadoDs = totalAsignadoDs;
	}
	
	public boolean isRecepcionConfirmada() {
		return recepcionConfirmada;
	}

	public void setRecepcionConfirmada(boolean recepcionConfirmada) {
		this.recepcionConfirmada = recepcionConfirmada;
	}

	public ProveedorDTO getProveedor() {
		return proveedor;
	}
	
	public void setProveedor(ProveedorDTO proveedor) {
		this.proveedor = proveedor;
	}
	
	public MyArray getCondicionPago() {
		return condicionPago;
	}

	public void setCondicionPago(MyArray condicionPago) {
		this.condicionPago = condicionPago;
	}

	public MyArray getMoneda() {
		return moneda;
	}
	
	public void setMoneda(MyArray moneda) {
		this.moneda = moneda;
	}
	
	public MyPair getSucursal() {
		return sucursal;
	}
	
	public void setSucursal(MyPair sucursal) {
		this.sucursal = sucursal;
	}

	public MyArray getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(MyArray tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	public MyArray getTimbrado() {
		return timbrado;
	}

	public void setTimbrado(MyArray timbrado) {
		this.timbrado = timbrado;
	}

	public void setDetalles(List<CompraLocalFacturaDetalleDTO> detalles) {
		this.detalles = detalles;
	}

	public double getImporteGs() {
		return this.getTotalImporteGs();
	}

	public void setImporteGs(double importeGs) {
	}

	public double getImporteDs() {
		double ds = this.getTotalImporteGs() / this.tipoCambio;
		return this.getMisc().redondeoDosDecimales(ds);
	}

	public void setImporteDs(double importeDs) {
	}

	public double getImporteIva10() {
		return this.getTotalIva10();
	}

	public void setImporteIva10(double importeIva10) {
	}

	public double getImporteIva5() {
		return this.getTotalIva5();
	}

	public void setImporteIva5(double importeIva5) {
	}

	public int getCondicionPagoDias() {
		return condicionPagoDias;
	}

	public void setCondicionPagoDias(int condicionPagoDias) {
		this.condicionPagoDias = condicionPagoDias;
	}

	public String getRecepcionConfirmadaPor() {
		return recepcionConfirmadaPor;
	}

	public void setRecepcionConfirmadaPor(String recepcionConfirmadaPor) {
		this.recepcionConfirmadaPor = recepcionConfirmadaPor;
	}

	public boolean isConteo1() {
		return conteo1;
	}

	public void setConteo1(boolean conteo1) {
		this.conteo1 = conteo1;
	}

	public boolean isConteo2() {
		return conteo2;
	}

	public void setConteo2(boolean conteo2) {
		this.conteo2 = conteo2;
	}

	public boolean isConteo3() {
		return conteo3;
	}

	public void setConteo3(boolean conteo3) {
		this.conteo3 = conteo3;
	}

	public String getTiempoConteo1() {
		return tiempoConteo1;
	}

	public void setTiempoConteo1(String tiempoConteo1) {
		this.tiempoConteo1 = tiempoConteo1;
	}

	public String getTiempoConteo2() {
		return tiempoConteo2;
	}

	public void setTiempoConteo2(String tiempoConteo2) {
		this.tiempoConteo2 = tiempoConteo2;
	}

	public String getTiempoConteo3() {
		return tiempoConteo3;
	}

	public void setTiempoConteo3(String tiempoConteo3) {
		this.tiempoConteo3 = tiempoConteo3;
	}
}
