package com.yhaguy.gestion.empresa;


import com.coreweb.domain.Domain;
import com.coreweb.dto.Assembler;
import com.coreweb.dto.DTO;
import com.yhaguy.domain.Proveedor;

public class AssemblerProveedor extends Assembler {

	static String[] camposCtaCtb = { "codigo", "descripcion", "alias" };	
	private static String[] camposCondicion = { "descripcion" };	
	private static String[] attIgualesProveedor = { "prioridad", "completo", "condicionPagoDias" };
	
	@Override
	public Domain dtoToDomain(DTO dtog) throws Exception {

		ProveedorDTO dto = (ProveedorDTO)dtog;
		Proveedor domain = (Proveedor) getDomain(dto, Proveedor.class);

		this.copiarValoresAtributos(dto, domain, attIgualesProveedor);
		
		this.myPairToDomain(dto, domain, "estadoProveedor");
		this.myPairToDomain(dto, domain, "tipoProveedor");
		this.myArrayToDomain(dto, domain, "cuentaContable");
		this.myArrayToDomain(dto, domain, "condicionPago");
		this.hijoDtoToHijoDomain(dto, domain, "empresa", new AssemblerEmpresa(), true);
		
		return domain;
	}

	@Override
	public DTO domainToDto(Domain dom) throws Exception {
		
		Proveedor domain = (Proveedor) dom;
		ProveedorDTO dto = (ProveedorDTO) getDTO(domain, ProveedorDTO.class);

		this.copiarValoresAtributos(domain, dto, attIgualesProveedor);
		
		this.domainToMyPair(domain, dto, "estadoProveedor");
		this.domainToMyPair(domain, dto, "tipoProveedor");
		this.domainToMyArray(domain, dto, "cuentaContable", camposCtaCtb);
		this.domainToMyArray(domain, dto, "condicionPago", camposCondicion);
		this.hijoDomainToHijoDTO(domain, dto, "empresa", new AssemblerEmpresa());
		
		return dto;
	}	
}
