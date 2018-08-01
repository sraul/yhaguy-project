package com.yhaguy.gestion.empresa;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;

import com.coreweb.dto.Assembler;
import com.coreweb.dto.DTO;
import com.coreweb.extras.browser.Browser;
import com.coreweb.util.MyArray;
import com.yhaguy.ID;
import com.yhaguy.UtilDTO;
import com.yhaguy.domain.CondicionPago;
import com.yhaguy.domain.Proveedor;
import com.yhaguy.domain.RegisterDomain;


public class ProveedorControlBody extends EmpresaControlBody {

	ProveedorDTO dto = new ProveedorDTO();
	

	@Init(superclass = true)
	public void initProveeddorControlBody() {
	}

	@AfterCompose(superclass = true)
	public void afterComposeProveedorControlBody() {
	}

	public String textoCabecera() {
		String nombre = this.dto.getNombre();
		if (nombre.trim().length() < 1){
			nombre = "-- seleccione una empresa --";
		}
		String out = "";
		out += "" + nombre.toUpperCase();
		return out;
	}

	public ProveedorDTO getDto() {
		return dto;
	}

	public void setDto(ProveedorDTO dto) {
		this.dto = dto;
		this.setDtoEmp(this.dto.getEmpresa());
	}


	@Override
	public DTO getDTOCorriente() {
		return this.dto;
	}

	@Override
	public void setDTOCorriente(DTO dto) {
		this.dto = (ProveedorDTO) dto;
		this.setDtoEmp(this.dto.getEmpresa());
	}

	@Override
	public DTO nuevoDTO() {
		ProveedorDTO aux = new ProveedorDTO();
		aux.setTipoProveedor(this.utilDto.getProveedorTipoLocal());
		aux.setEstadoProveedor(this.utilDto.getProveedorEstadoActivo());
		aux.setCuentaContable(this.utilDto.getCuentaClientesOcasionales());
		aux.getEmpresa().setPais(this.utilDto.getPaisParaguay());
		aux.getEmpresa().setEmpresaGrupoSociedad(this.utilDto.getGrupoEmpresaNoDefinido());
		aux.getEmpresa().setRegimenTributario(this.utilDto.getRegimenTributarioNoExenta());	
		aux.setCondicionPago(this.utilDto.getCondicionPagoContado());
		
		//Valores por defecto para CtaCteProveedor
		aux.getEmpresa().getCtaCteEmpresa().setEstadoComoProveedor(this.utilDto.getCtaCteEmpresaEstadoSinCuenta());
		
		this.setSelectedSucursal(null);
		this.setSelectedContacto(null);
		return aux;
	}

	@Override
	public String getEntidadPrincipal() {
		return Proveedor.class.getName();
	}

	@Override
	public List<DTO> getAllModel() throws Exception {
		return this.getAllDTOs(this.getEntidadPrincipal());
	}

	@Override
	public Assembler getAss() {
		// TODO Auto-generated method stub
		return new AssemblerProveedor();
	}
	
	public Browser getBrowser(){
		return new ProveedorBrowser();
	}
	
	@Command
	public void sugerirDiasCredito() {
		if (this.dto.getCondicionPago().getId().longValue() > 1) {
			this.dto.setCondicionPagoDias(30);
		} else {
			this.dto.setCondicionPagoDias(0);
		}
		BindUtils.postNotifyChange(null, null, this.dto, "condicionPagoDias");
	}
	
	/************************************* UTILES ****************************************/
	
	private UtilDTO utilDto = this.getDtoUtil();
	
	
	//============================= VALIDAR FORMULARIO ===================================
	
		private String mensajeError = "";
		
		@Override
		public boolean verificarAlGrabar() {
			return this.validarFormulario();
		}

		@Override
		public String textoErrorVerificarGrabar() {
			return this.mensajeError;
		}


		
		private boolean validarFormulario(){
			boolean out = true;
			
			out = this.validarFormularioCompleto();
			
			// control de RUC
			
			
			if (out == false){
				
				this.mensajeError(this.mensajeError);
				
				if (this.mensajeSiNo("La informacion esta incompleta/incorrecta, \n Desea grabar lo mismo? ")==true){
					this.dto.setCompleto(false);
					out = true;
				}else{
					out = false;
				}
				
			}
			return out;
		}

		
		private boolean validarFormularioCompleto() {
			return true;
		}		
		
		/**
		 * GETS / SETS
		 */
		
		/**
		 * @return las condiciones..
		 */
		public List<MyArray> getCondicionPagos() throws Exception {
			List<MyArray> out = new ArrayList<MyArray>();
			RegisterDomain rr = RegisterDomain.getInstance();
			CondicionPago con = (CondicionPago) rr.getObject(CondicionPago.class.getName(), 1);
			CondicionPago cre = (CondicionPago) rr.getObject(CondicionPago.class.getName(), 2);
			MyArray my1 = new MyArray(con.getDescripcion().toUpperCase());
			MyArray my2 = new MyArray(cre.getDescripcion().toUpperCase());
			my1.setId(con.getId()); my2.setId(cre.getId());
			out.add(my1); out.add(my2);
			return out;
		}
		
		public boolean isConsultaCtaCteDisabled() throws Exception{
			if (this.operacionHabilitada("ConsultarCtaCteProveedoresABM", ID.F_PROVEEDOR_ABM_BODY))
				return false;
			return true;
		}
		
		public boolean isCtaCteVisible() throws Exception{
			return !this.isConsultaCtaCteDisabled();
			
		}
		
		public boolean isCtaCteDisabled() throws Exception{
			if (this.operacionHabilitada("EditarCtaCteProveedoresABM", ID.F_PROVEEDOR_ABM_BODY))
				return false;
			return true;
			
		}

}
