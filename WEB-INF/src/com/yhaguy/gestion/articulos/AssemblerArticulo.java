package com.yhaguy.gestion.articulos;

import java.util.ArrayList;
import java.util.List;

import com.coreweb.domain.Domain;
import com.coreweb.dto.Assembler;
import com.coreweb.dto.DTO;
import com.coreweb.util.MyArray;
import com.yhaguy.domain.Articulo;
import com.yhaguy.domain.ArticuloListaPrecio;
import com.yhaguy.domain.ArticuloReferencia;
import com.yhaguy.domain.ProveedorArticulo;
import com.yhaguy.domain.RegisterDomain;
import com.yhaguy.gestion.empresa.AssemblerProveedor;

public class AssemblerArticulo extends Assembler {

	static String[] attIguales = { "descripcion", "codigoInterno",
			"codigoProveedor", "codigoOriginal", "codigoBarra", "observacion",
			"peso", "volumen", "fechaAlta", "prioridad", "completo",
			"urlImagen", "urlEspecificacion", "importado", "servicio" };

	static String[] attPresentacion = { "descripcion", "observacion", "unidad",
			"peso", "unidadMedida" };

	static String[] attInformacionExtra = { "descripcion" };

	static String[] attUbicacion = { "estante", "fila", "columna" };

	@Override
	public Domain dtoToDomain(DTO dtoG) throws Exception {
		ArticuloDTO dto = (ArticuloDTO) dtoG;
		Articulo domain = (Articulo) getDomain(dto, Articulo.class);

		this.copiarValoresAtributos(dto, domain, attIguales);
		this.myPairToDomain(dto, domain, "articuloEstado");
		this.myPairToDomain(dto, domain, "articuloFamilia");
		this.myPairToDomain(dto, domain, "articuloMarca");
		this.myPairToDomain(dto, domain, "articuloParte");
		this.myPairToDomain(dto, domain, "articuloLinea");
		this.myPairToDomain(dto, domain, "articuloUnidadMedida");
		
		this.myPairToDomain(dto, domain, "familia");
		this.myPairToDomain(dto, domain, "marca");
		this.myPairToDomain(dto, domain, "grupo");
		this.myPairToDomain(dto, domain, "subgrupo");
		this.myPairToDomain(dto, domain, "linea");
		this.myPairToDomain(dto, domain, "sublinea");
		this.myPairToDomain(dto, domain, "aplicacion");
		this.myPairToDomain(dto, domain, "modelo");
		this.myPairToDomain(dto, domain, "API");
		this.myPairToDomain(dto, domain, "procedencia");
		this.myPairToDomain(dto, domain, "indiceCarga");
		this.myPairToDomain(dto, domain, "lado");

		this.myArrayToDomain(dto, domain, "articuloPresentacion");

		this.listaDTOToListaDomain(dto, domain, "proveedorArticulos", true,
				true, new AssemblerProveedorArticulo());
		
		this.listaMyArrayToListaDomain(dto, domain, "ubicaciones");
		
		this.addReferencia(dto.getReferenciasDeleted(), dto.getReferencias(), dto.getId());

		return domain;
	}

	@Override
	public DTO domainToDto(Domain domain) throws Exception {

		ArticuloDTO dto = (ArticuloDTO) getDTO(domain, ArticuloDTO.class);
		this.copiarValoresAtributos(domain, dto, attIguales);
		this.domainToMyPair(domain, dto, "articuloEstado");
		this.domainToMyPair(domain, dto, "articuloFamilia");
		this.domainToMyPair(domain, dto, "articuloMarca");
		this.domainToMyPair(domain, dto, "articuloParte");
		this.domainToMyPair(domain, dto, "articuloLinea");
		this.domainToMyPair(domain, dto, "articuloUnidadMedida");
		
		this.domainToMyPair(domain, dto, "familia");
		this.domainToMyPair(domain, dto, "marca");
		this.domainToMyPair(domain, dto, "grupo");
		this.domainToMyPair(domain, dto, "subgrupo");
		this.domainToMyPair(domain, dto, "linea");
		this.domainToMyPair(domain, dto, "sublinea");
		this.domainToMyPair(domain, dto, "aplicacion");
		this.domainToMyPair(domain, dto, "modelo");
		this.domainToMyPair(domain, dto, "API");
		this.domainToMyPair(domain, dto, "procedencia");
		this.domainToMyPair(domain, dto, "indiceCarga");
		this.domainToMyPair(domain, dto, "lado");

		this.listaDomainToListaDTO(domain, dto, "proveedorArticulos",
				new AssemblerProveedorArticulo());

		this.domainToMyArray(domain, dto, "articuloPresentacion",
				attPresentacion);

		this.listaDomainToListaMyArray(domain, dto,
				"articuloInformacionExtras", attInformacionExtra);
		
		this.listaDomainToListaMyArray(domain, dto,
				"ubicaciones", attUbicacion);
		
		dto.setReferencias(this.getReferencias(dto.getId()));
		dto.setPrecios(this.getPrecios((Articulo) domain));
		
		return dto;
	}
	
	/**
	 * Graba las referencias de articulo..
	 */
	private void addReferencia(List<MyArray> delRef, List<MyArray> addRef, long idArt)
			throws Exception {
		RegisterDomain rr = RegisterDomain.getInstance();
		
		for (MyArray item : delRef) {
			long id = (long) item.getPos3();
			if (id > 0) {
				ArticuloReferencia ref = (ArticuloReferencia) rr.getObject(
						ArticuloReferencia.class.getName(), (long) item.getPos3());
				rr.deleteObject(ref);
			}			
		}
		
		for (MyArray item : addRef) {
			long id = (long) item.getPos3();
			if (id < 0) {
				ArticuloReferencia art = new ArticuloReferencia();
				art.setIdArticulo(idArt);
				art.setIdReferencia(item.getId());
				rr.saveObject(art, getLogin());
			}			
		}
	}
	
	/**
	 * @return las referencias de articulo..
	 */
	private List<MyArray> getReferencias(long idArticulo) throws Exception {
		List<MyArray> out = new ArrayList<MyArray>();
		RegisterDomain rr = RegisterDomain.getInstance();
		List<ArticuloReferencia> refs = rr.getArticulosReferencias(idArticulo);
		List<ArticuloReferencia> refs_ = rr.getArticulosReferenciados(idArticulo);

		for (ArticuloReferencia item : refs) {
			Articulo art = (Articulo) rr.getObject(Articulo.class.getName(),
					item.getIdReferencia());
			MyArray mart = new MyArray();
			mart.setId(art.getId());
			mart.setPos1(art.getCodigoInterno());
			mart.setPos2(art.getDescripcion());
			mart.setPos3(item.getId());
			out.add(mart);
		}
		
		for (ArticuloReferencia item : refs_) {
			Articulo art = (Articulo) rr.getObject(Articulo.class.getName(),
					item.getIdArticulo());
			MyArray mart = new MyArray();
			mart.setId(art.getId());
			mart.setPos1(art.getCodigoInterno());
			mart.setPos2(art.getDescripcion());
			mart.setPos3(item.getId());
			out.add(mart);
		}
		return out;
	}
	
	/**
	 * @return los precios vigentes del articulo..
	 */
	private List<MyArray> getPrecios(Articulo art) throws Exception {
		RegisterDomain rr = RegisterDomain.getInstance();
		double costo = rr.getUltimoCostoGs(art.getId());
		List<ArticuloListaPrecio> precios = rr.getListasDePrecio();
		
		List<MyArray> out = new ArrayList<MyArray>();
		for (ArticuloListaPrecio precio : precios) {
			MyArray mprecio = new MyArray();
			mprecio.setId(precio.getId());
			mprecio.setPos1(precio.getDescripcion());
			mprecio.setPos2(this.getPrecio(costo, precio.getMargen()));
			out.add(mprecio);
		}		
		return out;
	}
	
	/**
	 * @return el precio calculado..
	 */
	private double getPrecio(double costo, int margen) {
		double ganancia = this.getM().obtenerValorDelPorcentaje(costo, margen);
		return costo + ganancia;
	}
}

class AssemblerProveedorArticulo extends Assembler {
	private static String[] attProveedorArticulo = {
		"descripcionArticuloProveedor", "codigoFabrica"};
	@Override
	public Domain dtoToDomain(DTO dto) throws Exception {
		ProveedorArticulo domain = (ProveedorArticulo) getDomain(dto, ProveedorArticulo.class);
		this.copiarValoresAtributos(dto, domain, attProveedorArticulo);		
		this.hijoDtoToHijoDomain(dto, domain, "proveedor", new AssemblerProveedor(), false);
		this.hijoDtoToHijoDomain(dto, domain, "proveedor", null, false);

		return domain;
	}
	
	@Override
	public DTO domainToDto(Domain domain) throws Exception{
		ProveedorArticuloDTO dto = (ProveedorArticuloDTO) getDTO(domain, ProveedorArticuloDTO.class);
		this.copiarValoresAtributos(domain, dto, attProveedorArticulo);
		this.hijoDomainToHijoDTO(domain, dto, "proveedor", new AssemblerProveedor());
		
		return dto;
	}
	
}
