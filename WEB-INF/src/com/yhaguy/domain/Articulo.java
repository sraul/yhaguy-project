package com.yhaguy.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.coreweb.domain.Domain;
import com.coreweb.domain.Tipo;

@SuppressWarnings("serial")
public class Articulo extends Domain {
	
	public static final String FAMILIA_BATERIAS = "BATERIAS";
	public static final String FAMILIA_FILTROS = "FILTROS";
	public static final String FAMILIA_LUBRICANTES = "LUBRICANTES";
	public static final String FAMILIA_NEUMATICOS = "CUBIERTAS";
	public static final String FAMILIA_REPUESTOS = "REPUESTOS";

	private String descripcion = ""; 
	private String codigoInterno = ""; 
	private String codigoProveedor = "";
	private String codigoOriginal = ""; 
	private String codigoBarra = ""; 
	private String observacion = ""; 
	private String urlImagen = "";
	private String urlEspecificacion = "";
	private String peso_;
	private String volumen_;
	private double peso;
	private double volumen; 
	private double costoGs;
	private double costoDs;
	private double precioGs;
	private double precioMinoristaGs;
	private double precioListaGs;
	private Date fechaAlta; 
	private boolean importado; 
	private boolean servicio;
	long prioridad = 0; 
	boolean completo = false;
	private Tipo articuloEstado; 
	private Tipo articuloFamilia; 
	private Tipo articuloMarca; 
	private Tipo articuloParte; 						
	private Tipo articuloLinea; 
	private Tipo articuloUnidadMedida; 
	
	private ArticuloFamilia familia;
	private ArticuloMarca marca;
	private ArticuloGrupo grupo;
	private ArticuloSubGrupo subgrupo;
	private ArticuloLinea linea;
	private ArticuloSubLinea sublinea;
	private ArticuloAplicacion aplicacion;
	private ArticuloModelo modelo;
	private ArticuloAPI API;
	private ArticuloProcedencia procedencia;
	private ArticuloIndicecarga indiceCarga;
	private ArticuloLado lado;
	
	private long stock = 0;

	private Set<ProveedorArticulo> proveedorArticulos = new HashSet<ProveedorArticulo>();

	// en este dato ponemos toda la info extra que tengamos de los artículos
	// cómo tener marca y modelo era complicado de mantener, la idea es poner toda
	// la infor con un texto con tokens
	private Set<ArticuloInformacionExtra> articuloInformacionExtras = new HashSet<ArticuloInformacionExtra>();

	
	// Cuanto viene en caja, bolsa, juego, pallets, etc. teniendo como
	// referencia la Unidad de Medida de Venta
	private ArticuloPresentacion articuloPresentacion;
	
	private Set<ArticuloUbicacion> ubicaciones = new HashSet<ArticuloUbicacion>();
	
	@Override
	public int compareTo(Object o) {
		return -1;
	}
	
	/**
	 * @return si el proveedor esta definido para este articulo..
	 */
	public boolean isProveedor(long idProveedor) {
		if (this.proveedorArticulos.size() == 0) {
			return false;
		}
		for (ProveedorArticulo proveedor : this.proveedorArticulos) {
			if (proveedor.getProveedor().getId().longValue() == idProveedor) {
				return true;
			}
		}
		return false;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCodigoInterno() {
		return codigoInterno;
	}

	public void setCodigoInterno(String codigoInterno) {
		this.codigoInterno = codigoInterno;
	}

	public String getCodigoOriginal() {
		return codigoOriginal;
	}

	public void setCodigoOriginal(String codigoOriginal) {
		this.codigoOriginal = codigoOriginal;
	}
		
	public String getCodigoBarra() {
		return codigoBarra;
	}

	public void setCodigoBarra(String codigoBarra) {
		this.codigoBarra = codigoBarra;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getUrlImagen() {
		return urlImagen;
	}

	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}

	public String getUrlEspecificacion() {
		return urlEspecificacion;
	}

	public void setUrlEspecificacion(String urlEspecificacion) {
		this.urlEspecificacion = urlEspecificacion;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getVolumen() {
		return volumen;
	}

	public void setVolumen(double volumen) {
		this.volumen = volumen;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public long getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(long prioridad) {
		this.prioridad = prioridad;
	}

	public boolean isCompleto() {
		return completo;
	}

	public void setCompleto(boolean completo) {
		this.completo = completo;
	}

	public Tipo getArticuloEstado() {
		return articuloEstado;
	}

	public void setArticuloEstado(Tipo articuloEstado) {
		this.articuloEstado = articuloEstado;
	}

	public Tipo getArticuloFamilia() {
		return articuloFamilia;
	}

	public void setArticuloFamilia(Tipo articuloFamilia) {
		this.articuloFamilia = articuloFamilia;
	}
		
	public Tipo getArticuloMarca() {
		return articuloMarca;
	}

		
	public void setArticuloMarca(Tipo articuloMarca) {
		this.articuloMarca = articuloMarca;
	}

	public Tipo getArticuloParte() {
		return articuloParte;
	}

	public void setArticuloParte(Tipo articuloParte) {
		this.articuloParte = articuloParte;
	}

	public Tipo getArticuloLinea() {
		return articuloLinea;
	}

	public void setArticuloLinea(Tipo articuloLinea) {
		this.articuloLinea = articuloLinea;
	}

	public Tipo getArticuloUnidadMedida() {
		return articuloUnidadMedida;
	}

	public void setArticuloUnidadMedida(Tipo articuloUnidadMedida) {
		this.articuloUnidadMedida = articuloUnidadMedida;
	}	
	
	public ArticuloPresentacion getArticuloPresentacion() {
		return articuloPresentacion;
	}

	public void setArticuloPresentacion(
			ArticuloPresentacion articuloPresentacion) {
		this.articuloPresentacion = articuloPresentacion;
	}

	public Set<ProveedorArticulo> getProveedorArticulos() {
		return proveedorArticulos;
	}

	public Set<ArticuloInformacionExtra> getArticuloInformacionExtras() {
		return articuloInformacionExtras;
	}

	public void setArticuloInformacionExtras(
			Set<ArticuloInformacionExtra> articuloInformacionExtras) {
		this.articuloInformacionExtras = articuloInformacionExtras;
	}

	public void setProveedorArticulos(Set<ProveedorArticulo> proveedorArticulos) {
		this.proveedorArticulos = proveedorArticulos;
	}

	public boolean isImportado() {
		return importado;
	}

	public void setImportado(boolean importado) {
		this.importado = importado;
	}

	public Set<ArticuloUbicacion> getUbicaciones() {
		return ubicaciones;
	}

	public void setUbicaciones(Set<ArticuloUbicacion> ubicaciones) {
		this.ubicaciones = ubicaciones;
	}

	public String getCodigoProveedor() {
		return codigoProveedor;
	}

	public void setCodigoProveedor(String codigoProveedor) {
		this.codigoProveedor = codigoProveedor;
	}

	public boolean isServicio() {
		return servicio;
	}

	public void setServicio(boolean servicio) {
		this.servicio = servicio;
	}

	public double getCostoGs() {
		return costoGs;
	}

	public void setCostoGs(double costoGs) {
		this.costoGs = costoGs;
	}

	public long getStock() {
		return stock;
	}

	public void setStock(long stock) {
		this.stock = stock;
	}

	public double getCostoDs() {
		return costoDs;
	}

	public void setCostoDs(double costoDs) {
		this.costoDs = costoDs;
	}

	public double getPrecioGs() {
		return precioGs;
	}

	public void setPrecioGs(double precioGs) {
		this.precioGs = precioGs;
	}

	public ArticuloFamilia getFamilia() {
		return familia;
	}

	public void setFamilia(ArticuloFamilia familia) {
		this.familia = familia;
	}

	public ArticuloMarca getMarca() {
		return marca;
	}

	public void setMarca(ArticuloMarca marca) {
		this.marca = marca;
	}

	public ArticuloGrupo getGrupo() {
		return grupo;
	}

	public void setGrupo(ArticuloGrupo grupo) {
		this.grupo = grupo;
	}

	public ArticuloSubGrupo getSubgrupo() {
		return subgrupo;
	}

	public void setSubgrupo(ArticuloSubGrupo subgrupo) {
		this.subgrupo = subgrupo;
	}

	public ArticuloLinea getLinea() {
		return linea;
	}

	public void setLinea(ArticuloLinea linea) {
		this.linea = linea;
	}

	public ArticuloSubLinea getSublinea() {
		return sublinea;
	}

	public void setSublinea(ArticuloSubLinea sublinea) {
		this.sublinea = sublinea;
	}

	public ArticuloAplicacion getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(ArticuloAplicacion aplicacion) {
		this.aplicacion = aplicacion;
	}

	public ArticuloModelo getModelo() {
		return modelo;
	}

	public void setModelo(ArticuloModelo modelo) {
		this.modelo = modelo;
	}

	public ArticuloAPI getAPI() {
		return API;
	}

	public void setAPI(ArticuloAPI aPI) {
		API = aPI;
	}

	public ArticuloProcedencia getProcedencia() {
		return procedencia;
	}

	public void setProcedencia(ArticuloProcedencia procedencia) {
		this.procedencia = procedencia;
	}

	public String getPeso_() {
		return peso_;
	}

	public void setPeso_(String peso_) {
		this.peso_ = peso_;
	}

	public String getVolumen_() {
		return volumen_;
	}

	public void setVolumen_(String volumen_) {
		this.volumen_ = volumen_;
	}

	public ArticuloIndicecarga getIndiceCarga() {
		return indiceCarga;
	}

	public void setIndiceCarga(ArticuloIndicecarga indiceCarga) {
		this.indiceCarga = indiceCarga;
	}

	public ArticuloLado getLado() {
		return lado;
	}

	public void setLado(ArticuloLado lado) {
		this.lado = lado;
	}

	public double getPrecioMinoristaGs() {
		return precioMinoristaGs;
	}

	public void setPrecioMinoristaGs(double precioMinoristaGs) {
		this.precioMinoristaGs = precioMinoristaGs;
	}

	public double getPrecioListaGs() {
		return precioListaGs;
	}

	public void setPrecioListaGs(double precioListaGs) {
		this.precioListaGs = precioListaGs;
	}
}
