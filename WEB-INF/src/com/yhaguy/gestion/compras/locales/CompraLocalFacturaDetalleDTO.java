package com.yhaguy.gestion.compras.locales;

import org.zkoss.bind.annotation.DependsOn;

import com.coreweb.dto.DTO;
import com.coreweb.util.MyArray;
import com.coreweb.util.MyPair;
import com.yhaguy.Configuracion;

@SuppressWarnings("serial")
public class CompraLocalFacturaDetalleDTO extends DTO {

	private double costoGs = 0;
	private double costoDs = 0;
	private double importeExentaGs = 0;
	private double importeExentaDs = 0;
	private double importeGravadaGs = 0;
	private double importeGravadaDs = 0;
	private double descuentoGs = 0;
	private double descuentoDs = 0;
	private String textoDescuento = "0";
	private double importeDescuentoGs = 0;
	private double importeDescuentoDs = 0;
	private boolean descuento = false;
	private MyPair tipoDescuento;
	private int cantidad = 0;
	private int cantidadRecibida = 0;
	private int volcadoPendiente = 0;
	
	private boolean controlCarga = false;
	private int conteo1 = 0;
	private int conteo2 = 0;
	private int conteo3 = 0;
	
	private MyArray articulo = new MyArray();	
	private MyPair iva;
	
	/**
	 * @return la cantidad recepcionada..
	 */
	public int getCantidadRecepcionada(CompraLocalFacturaDTO cab) {
		if (cab.isConteo3()) {
			return this.conteo3;
		} else if (cab.isConteo2()) {
			return this.conteo2;
		} else {
			return this.conteo1;
		}
	}
	
	@DependsOn({ "cantidad", "conteo1" })
	public double getDiferencia1() {
		return this.cantidad - this.conteo1;
	}
	
	@DependsOn({ "cantidad", "conteo2" })
	public double getDiferencia2() {
		return this.cantidad - this.conteo2;
	}
	
	@DependsOn({ "cantidad", "conteo3" })
	public double getDiferencia3() {
		return this.cantidad - this.conteo3;
	}
	
	@DependsOn({ "costoGs", "cantidad", "descuentoGs" })
	public double getImporteGs(){
		int signo = this.isDescuento()? -1 : 1;
		double desc = this.isDescuento() ? 0 : this.getImporteDescuentoGs_();
		return ((costoGs * cantidad) * signo) - desc;
	}
	
	@DependsOn({ "costoDs", "cantidad", "descuentoDs" })
	public double getImporteDs(){
		int signo = this.isDescuento()? -1 : 1;
		double desc = this.isDescuento() ? 0 : this.getImporteDescuentoDs_();
		return ((costoDs * cantidad) * signo) - desc;
	}
	
	@DependsOn({ "cantidad", "descuentoGs" })
	public double getImporteDescuentoGs_() { 
		return ( this.descuentoGs * cantidad );
	}
	
	@DependsOn({ "cantidad", "descuentoDs" })
	public double getImporteDescuentoDs_() {
		return ( this.descuentoDs * cantidad );
	}
	
	@DependsOn({"iva", "costoGs", "cantidad"})
	public double getImporteIva() {
		if(this.isExenta())
			return 0;
		int porc = this.isIva10()? 10 : 5;
		return this.getMisc().calcularIVA(this.getImporteGs(), porc);
	}
	
	@DependsOn({"cantidad", "cantidadRecibida"})
	public int getDiferenciaCantidad(){
		return cantidadRecibida - cantidad;
	}
	
	@SuppressWarnings("static-access")
	@DependsOn("diferenciaCantidad")
	public String getStyleItem(){
		int dif = this.getDiferenciaCantidad();
		if (dif > 0) {
			return this.getMisc().TEXTO_VERDE;
		} else if( dif < 0){
			return this.getMisc().TEXTO_ROJO;
		} else {
			return this.getMisc().TEXTO_NORMAL;
		}
	}
	
	@DependsOn("iva")
	public boolean isExenta() {
		return this.iva.getSigla().equals(Configuracion.SIGLA_IVA_EXENTO);
	}
	
	@DependsOn("iva")
	public boolean isIva10() {
		return this.iva.getSigla().equals(Configuracion.SIGLA_IVA_10);
	}
	
	@DependsOn("iva")
	public boolean isIva5() {
		return this.iva.getSigla().equals(Configuracion.SIGLA_IVA_5);
	}

	public double getCostoGs() {
		return costoGs;
	}

	public void setCostoGs(double costoGs) {
		this.costoGs = costoGs;
	}

	public double getCostoDs() {
		return this.getMisc().redondeoDosDecimales(costoDs);
	}

	public void setCostoDs(double costoDs) {
		this.costoDs = costoDs;
	}

	public double getImporteExentaGs() {
		return importeExentaGs;
	}

	public void setImporteExentaGs(double importeExentaGs) {
		this.importeExentaGs = importeExentaGs;
	}

	public double getImporteExentaDs() {
		return importeExentaDs;
	}

	public void setImporteExentaDs(double importeExentaDs) {
		this.importeExentaDs = importeExentaDs;
	}

	public double getImporteGravadaGs() {
		return importeGravadaGs;
	}

	public void setImporteGravadaGs(double importeGravadaGs) {
		this.importeGravadaGs = importeGravadaGs;
	}

	public double getImporteGravadaDs() {
		return importeGravadaDs;
	}

	public void setImporteGravadaDs(double importeGravadaDs) {
		this.importeGravadaDs = importeGravadaDs;
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

	public boolean isDescuento() {
		return descuento;
	}

	public void setDescuento(boolean descuento) {
		this.descuento = descuento;
	}

	public MyPair getTipoDescuento() {
		return tipoDescuento;
	}

	public void setTipoDescuento(MyPair tipoDescuento) {
		this.tipoDescuento = tipoDescuento;
	}

	public String getTextoDescuento() {
		return textoDescuento;
	}

	public void setTextoDescuento(String textoDescuento) {
		this.textoDescuento = textoDescuento;
	}

	public double getImporteDescuentoGs() {
		return importeDescuentoGs;
	}

	public void setImporteDescuentoGs(double importeDescuentoGs) {
		this.importeDescuentoGs = importeDescuentoGs;
	}

	public double getImporteDescuentoDs() {
		return importeDescuentoDs;
	}

	public void setImporteDescuentoDs(double importeDescuentoDs) {
		this.importeDescuentoDs = importeDescuentoDs;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getCantidadRecibida() {
		return cantidadRecibida;
	}

	public void setCantidadRecibida(int cantidadRecibida) {
		this.cantidadRecibida = cantidadRecibida;
	}

	public MyArray getArticulo() {
		return articulo;
	}

	public void setArticulo(MyArray articulo) {
		this.articulo = articulo;
	}

	public MyPair getIva() {
		return iva;
	}

	public void setIva(MyPair iva) {
		this.iva = iva;
	}

	public boolean isControlCarga() {
		return controlCarga;
	}

	public void setControlCarga(boolean controlCarga) {
		this.controlCarga = controlCarga;
	}

	public int getConteo1() {
		return conteo1;
	}

	public void setConteo1(int conteo1) {
		this.conteo1 = conteo1;
	}

	public int getConteo2() {
		return conteo2;
	}

	public void setConteo2(int conteo2) {
		this.conteo2 = conteo2;
	}

	public int getConteo3() {
		return conteo3;
	}

	public void setConteo3(int conteo3) {
		this.conteo3 = conteo3;
	}

	public int getVolcadoPendiente() {
		return volcadoPendiente;
	}

	public void setVolcadoPendiente(int volcadoPendiente) {
		this.volcadoPendiente = volcadoPendiente;
	}
	
}
