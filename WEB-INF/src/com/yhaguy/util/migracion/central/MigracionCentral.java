package com.yhaguy.util.migracion.central;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import com.coreweb.domain.Tipo;
import com.coreweb.domain.Usuario;
import com.coreweb.extras.csv.CSV;
import com.yhaguy.Configuracion;
import com.yhaguy.domain.AccesoApp;
import com.yhaguy.domain.Articulo;
import com.yhaguy.domain.ArticuloAPI;
import com.yhaguy.domain.ArticuloAplicacion;
import com.yhaguy.domain.ArticuloDeposito;
import com.yhaguy.domain.ArticuloFamilia;
import com.yhaguy.domain.ArticuloGasto;
import com.yhaguy.domain.ArticuloGrupo;
import com.yhaguy.domain.ArticuloIndicecarga;
import com.yhaguy.domain.ArticuloLado;
import com.yhaguy.domain.ArticuloLinea;
import com.yhaguy.domain.ArticuloListaPrecio;
import com.yhaguy.domain.ArticuloMarca;
import com.yhaguy.domain.ArticuloModelo;
import com.yhaguy.domain.ArticuloProcedencia;
import com.yhaguy.domain.ArticuloSubGrupo;
import com.yhaguy.domain.ArticuloSubLinea;
import com.yhaguy.domain.Cliente;
import com.yhaguy.domain.CtaCteEmpresaMovimiento;
import com.yhaguy.domain.CtaCteLineaCredito;
import com.yhaguy.domain.CuentaContable;
import com.yhaguy.domain.DepartamentoApp;
import com.yhaguy.domain.Deposito;
import com.yhaguy.domain.Empresa;
import com.yhaguy.domain.EmpresaRubro;
import com.yhaguy.domain.Funcionario;
import com.yhaguy.domain.PlanDeCuenta;
import com.yhaguy.domain.Proveedor;
import com.yhaguy.domain.RegisterDomain;
import com.yhaguy.domain.SucursalApp;
import com.yhaguy.domain.Vendedor;
import com.yhaguy.util.Utiles;

public class MigracionCentral {

	static final String DIR_MIGRACION = "./WEB-INF/docs/migracion/central/";
	
	static final String SUC_CENTRAL = "CENTRAL";
	static final String SUC_MRA = "M.R.A";
	static final String SUC_MCAL = "MCAL";
	static final String SUC_GAM = "GRAN ALMACEN";
	
	/**
	 * SISTEMA
	 */
	
	/**
	 * sucursales departamentos depositos
	 */
	public static void migrarSucursales() throws Exception {
		RegisterDomain rr = RegisterDomain.getInstance();

		String[] sucs = new String[] { SUC_MRA, SUC_CENTRAL, SUC_MCAL, SUC_GAM };
		String[] dirs = new String[] { "", "", "", "" };
		String[] estbc = new String[] { "2", "1", "3", "4" };
		String[] tels = new String[] { "", "", "", "" };
		
		String[][] departamentos = new String[][] { { "ADMINISTRACION", "VENTAS" },
				{ "ADMINISTRACION", "RR.HH", "INFORMATICA", "COBRANZAS", "ABASTECIMIENTO", "VENTAS", "MARKETING",
						"AUDITORIA", "LOGISTICA", "DIRECTORIO" },
				{ "ADMINISTRACION", "VENTAS" }, { "ADMINISTRACION", "VENTAS" } };

		for (int i = 0; i < sucs.length; i++) {
			SucursalApp suc = new SucursalApp();
			suc.setDescripcion(sucs[i]);
			suc.setDireccion(dirs[i]);
			suc.setEstablecimiento(estbc[i]);
			suc.setNombre(sucs[i]);
			suc.setTelefono(tels[i]);
			rr.saveObject(suc, "sys");
			
			String[] depmts = departamentos[i];
			for (int j = 0; j < depmts.length; j++) {
				DepartamentoApp dep = new DepartamentoApp();
				dep.setDescripcion(depmts[j]);
				dep.setNombre(depmts[j]);
				dep.setSucursal(suc);
				rr.saveObject(dep, "sys");
			}
			
			System.out.println(suc.getDescripcion());
		}	
	}
	
	
	/**
	 * funcionarios..
	 */
	public static void migrarFuncionarios() throws Exception {
		RegisterDomain rr = RegisterDomain.getInstance();
		String src = DIR_MIGRACION + "FUNCIONARIOS.csv";

		String[][] cab = { { "Empresa", CSV.STRING } };
		String[][] det = { { "RAZONSOCIAL", CSV.STRING }, { "RUC", CSV.STRING }, { "DIRECCION", CSV.STRING }, { "TELEFONO", CSV.STRING } };

		CSV csv = new CSV(cab, det, src);
		csv.start();
		while (csv.hashNext()) {
			String rs = csv.getDetalleString("RAZONSOCIAL");
			String ruc = csv.getDetalleString("RUC");
			String dir = csv.getDetalleString("DIRECCION");
			String tel = csv.getDetalleString("TELEFONO");

			Empresa emp = new Empresa();
			emp.setRazonSocial(rs);
			emp.setRuc(ruc);
			emp.setCi(ruc);
			emp.setDireccion_(dir);
			emp.setTelefono_(tel);
			rr.saveObject(emp, "sys");
			
			Funcionario fun = new Funcionario();
			fun.setEmpresa(emp);
			rr.saveObject(fun, "sys");

			System.out.println(emp.getRazonSocial());

		}
	}
	
	/**
	 * usuarios..
	 */
	public static void migrarUsuariosCentral() throws Exception {
		RegisterDomain rr = RegisterDomain.getInstance();
		String src = DIR_MIGRACION + "USUARIOS_CENTRAL.csv";
		SucursalApp central = rr.getSucursalAppByNombre(SUC_CENTRAL);
		Set<SucursalApp> sucs = new HashSet<SucursalApp>();
		sucs.add(central);
		DepartamentoApp dep = (DepartamentoApp) rr.getObject(DepartamentoApp.class.getName(), 1);

		String[][] cab = { { "Empresa", CSV.STRING } };
		String[][] det = { { "NOMBRE", CSV.STRING }, { "LOGIN", CSV.STRING } };

		CSV csv = new CSV(cab, det, src);
		csv.start();
		while (csv.hashNext()) {
			String nombre = csv.getDetalleString("NOMBRE");
			String login = csv.getDetalleString("LOGIN");

			Usuario user = new Usuario();
			user.setNombre(nombre);
			user.setLogin(login);
			user.setClave(Utiles.encriptar("123", true));
			rr.saveObject(user, "sys");
			
			AccesoApp acceso = new AccesoApp();
			acceso.setDescripcion("");
			acceso.setSucursales(sucs);
			acceso.setUsuario(user);
			acceso.setDepartamento(dep);
			rr.saveObject(acceso, "sys");
			
			Set<AccesoApp> accs = new HashSet<AccesoApp>();
			accs.add(acceso);
			
			Funcionario fun = rr.getFuncionario(nombre);
			fun.setAccesos(accs);
			rr.saveObject(fun, "sys");
			
			System.out.println(user.getLogin());

		}
	}
	
	/**
	 * usuarios mcal..
	 */
	public static void migrarUsuariosMCAL() throws Exception {
		RegisterDomain rr = RegisterDomain.getInstance();
		String src = DIR_MIGRACION + "USUARIOS_MCAL.csv";
		SucursalApp mcal = rr.getSucursalAppByNombre(SUC_MCAL);
		Set<SucursalApp> sucs = new HashSet<SucursalApp>();
		sucs.add(mcal);

		String[][] cab = { { "Empresa", CSV.STRING } };
		String[][] det = { { "NOMBRE", CSV.STRING }, { "LOGIN", CSV.STRING } };

		CSV csv = new CSV(cab, det, src);
		csv.start();
		while (csv.hashNext()) {
			String nombre = csv.getDetalleString("NOMBRE");
			String login = csv.getDetalleString("LOGIN");

			Usuario user = new Usuario();
			user.setNombre(nombre);
			user.setLogin(login);
			user.setClave(Utiles.encriptar("123", true));
			rr.saveObject(user, "sys");
			
			AccesoApp acceso = new AccesoApp();
			acceso.setDescripcion("");
			acceso.setSucursales(sucs);
			acceso.setUsuario(user);
			rr.saveObject(acceso, "sys");
			
			Set<AccesoApp> accs = new HashSet<AccesoApp>();
			accs.add(acceso);
			
			Funcionario fun = rr.getFuncionario(nombre);
			fun.setAccesos(accs);
			rr.saveObject(fun, "sys");
			
			System.out.println(user.getLogin());

		}
	}
	
	/**
	 * usuarios gran almacen..
	 */
	public static void migrarUsuariosGAM() throws Exception {
		RegisterDomain rr = RegisterDomain.getInstance();
		String src = DIR_MIGRACION + "USUARIOS_GAM.csv";
		SucursalApp gam = rr.getSucursalAppByNombre(SUC_GAM);
		Set<SucursalApp> sucs = new HashSet<SucursalApp>();
		sucs.add(gam);

		String[][] cab = { { "Empresa", CSV.STRING } };
		String[][] det = { { "NOMBRE", CSV.STRING }, { "LOGIN", CSV.STRING } };

		CSV csv = new CSV(cab, det, src);
		csv.start();
		while (csv.hashNext()) {
			String nombre = csv.getDetalleString("NOMBRE");
			String login = csv.getDetalleString("LOGIN");

			Usuario user = new Usuario();
			user.setNombre(nombre);
			user.setLogin(login);
			user.setClave(Utiles.encriptar("123", true));
			rr.saveObject(user, "sys");
			
			AccesoApp acceso = new AccesoApp();
			acceso.setDescripcion("");
			acceso.setSucursales(sucs);
			acceso.setUsuario(user);
			rr.saveObject(acceso, "sys");
			
			Set<AccesoApp> accs = new HashSet<AccesoApp>();
			accs.add(acceso);
			
			Funcionario fun = rr.getFuncionario(nombre);
			fun.setAccesos(accs);
			rr.saveObject(fun, "sys");
			
			System.out.println(user.getLogin());

		}
	}
	
	/**
	 * plan de cuentas..
	 */
	public static void migrarPlanDeCuenta() throws Exception {
		RegisterDomain rr = RegisterDomain.getInstance();
		
		Hashtable<String, Tipo> tipos = new Hashtable<String, Tipo>();
		Tipo activo = rr.getTipoPorSigla(Configuracion.SIGLA_TIPO_CTA_CONTABLE_ACTIVO);
		Tipo pasivo = rr.getTipoPorSigla(Configuracion.SIGLA_TIPO_CTA_CONTABLE_PASIVO);
		Tipo ingreso = rr.getTipoPorSigla(Configuracion.SIGLA_TIPO_CTA_CONTABLE_INGRESO);
		Tipo egreso = rr.getTipoPorSigla(Configuracion.SIGLA_TIPO_CTA_CONTABLE_EGRESO);

		tipos.put("1", activo);
		tipos.put("2", pasivo);
		tipos.put("3", ingreso);
		tipos.put("4", egreso);
		
		String src = DIR_MIGRACION + "PLAN_CUENTA.csv";

		String[][] cab = { { "Empresa", CSV.STRING } };
		String[][] det = { { "IDPLANCUENTA", CSV.STRING },
				{ "NOMBRE", CSV.STRING }, { "IDTIPOCUENTA", CSV.STRING },
				{ "IMPUTABLE", CSV.STRING }, { "IMPOSITIVO", CSV.STRING },
				{ "CCOSTO", CSV.STRING }, { "NIVEL", CSV.NUMERICO }, };
		
		CSV csv = new CSV(cab, det, src);

		csv.start();
		while (csv.hashNext()) {
			String codigo = csv.getDetalleString("IDPLANCUENTA");
			String descripcion = csv.getDetalleString("NOMBRE");
			String tipoCta = csv.getDetalleString("IDTIPOCUENTA");

			String imputable = csv.getDetalleString("IMPUTABLE");
			String impositivo = csv.getDetalleString("IMPOSITIVO");
			String ccosto = csv.getDetalleString("CCOSTO");
			int nivel = ((Float)csv.getDetalle("NIVEL")).intValue();
						
			PlanDeCuenta pct = new PlanDeCuenta();
			pct.setCodigo(codigo);
			pct.setDescripcion(descripcion);
			pct.setTipoCuenta(tipos.get(tipoCta));
			pct.setImputable(imputable);
			pct.setImpositivo(impositivo);
			pct.setCcosto(ccosto);
			pct.setNivel(nivel);
			rr.saveObject(pct, "sys");
			
			System.out.println(pct.getDescripcion());
		}
	}
	
	/**
	 * cuentas contables..
	 */
	public static void migrarCuentasContables() throws Exception {
		RegisterDomain rr = RegisterDomain.getInstance();
		String src = DIR_MIGRACION + "CUENTAS_CONTABLES.csv";

		String[][] cab = { { "Empresa", CSV.STRING } };
		String[][] det = { { "CODIGO", CSV.STRING }, { "DESCRIPCION", CSV.STRING }, { "ALIAS", CSV.STRING } };

		PlanDeCuenta pc = (PlanDeCuenta) rr.getObject(PlanDeCuenta.class.getName(), 1);
		
		CSV csv = new CSV(cab, det, src);
		csv.start();
		while (csv.hashNext()) {
			String cod = csv.getDetalleString("CODIGO");
			String desc = csv.getDetalleString("DESCRIPCION");
			String alias = csv.getDetalleString("ALIAS");
			
			CuentaContable cta = new CuentaContable();
			cta.setAlias(alias);
			cta.setCodigo(cod);
			cta.setDescripcion(desc);
			cta.setPlanCuenta(pc);			
			rr.saveObject(cta, "sys");
			
			ArticuloGasto ag = new ArticuloGasto();
			ag.setCuentaContable(cta);
			ag.setDescripcion(desc);
			rr.saveObject(cta, "sys");

			System.out.println(desc);
		}
	}
	
	
	/**
	 * ARTICULOS - DEPOSITOS
	 */
	
	/**
	 * familias de articulos..
	 */
	public static void migrarArticuloFamilias() throws Exception {
		RegisterDomain rr = RegisterDomain.getInstance();

		String[] familias = new String[] { "FILTROS", "LUBRICANTES", "CUBIERTAS", "REPUESTOS", "BATERIAS" };

		for (int i = 0; i < familias.length; i++) {
			ArticuloFamilia flia = new ArticuloFamilia();
			flia.setDescripcion(familias[i]);
			rr.saveObject(flia, "sys");
			System.out.println(flia.getDescripcion());
		}
	}
	
	/**
	 * grupos de articulos..
	 */
	public static void migrarArticuloGrupo() throws Exception {
		RegisterDomain rr = RegisterDomain.getInstance();
		String src = DIR_MIGRACION + "ARTICULO_GRUPO.csv";

		String[][] cab = { { "Empresa", CSV.STRING } };
		String[][] det = { { "CODIGO", CSV.STRING }, { "DESCRIPCION", CSV.STRING } };

		CSV csv = new CSV(cab, det, src);
		csv.start();
		while (csv.hashNext()) {
			String desc = csv.getDetalleString("DESCRIPCION");

			ArticuloGrupo grupo = new ArticuloGrupo();
			grupo.setDescripcion(desc.toUpperCase());
			rr.saveObject(grupo, "sys");

			System.out.println(desc);

		}
	}
	
	/**
	 * marcas de articulos..
	 */
	public static void migrarArticuloMarca() throws Exception {

		RegisterDomain rr = RegisterDomain.getInstance();
		String src = DIR_MIGRACION + "ARTICULO_MARCA.csv";

		String[][] cab = { { "Empresa", CSV.STRING } };
		String[][] det = { { "CODIGO", CSV.STRING }, { "DESCRIPCION", CSV.STRING } };

		CSV csv = new CSV(cab, det, src);
		csv.start();
		while (csv.hashNext()) {
			String desc = csv.getDetalleString("DESCRIPCION");

			ArticuloMarca marca = new ArticuloMarca();
			marca.setDescripcion(desc.toUpperCase());
			rr.saveObject(marca, "sys");

			System.out.println(desc);

		}
	}
	
	/**
	 * aplicaciones de articulos..
	 */
	public static void migrarArticuloAplicacion() throws Exception {

		RegisterDomain rr = RegisterDomain.getInstance();
		String src = DIR_MIGRACION + "ARTICULO_APLICACION.csv";

		String[][] cab = { { "Empresa", CSV.STRING } };
		String[][] det = { { "CODIGO", CSV.STRING }, { "DESCRIPCION", CSV.STRING } };

		CSV csv = new CSV(cab, det, src);
		csv.start();
		while (csv.hashNext()) {
			String desc = csv.getDetalleString("DESCRIPCION");

			ArticuloAplicacion aplicacion = new ArticuloAplicacion();
			aplicacion.setDescripcion(desc.toUpperCase());
			rr.saveObject(aplicacion, "sys");

			System.out.println(desc);

		}
	}
	
	/**
	 * modelos de articulos..
	 */
	public static void migrarArticuloModelo() throws Exception {

		RegisterDomain rr = RegisterDomain.getInstance();
		String src = DIR_MIGRACION + "ARTICULO_MODELO.csv";

		String[][] cab = { { "Empresa", CSV.STRING } };
		String[][] det = { { "CODIGO", CSV.STRING }, { "DESCRIPCION", CSV.STRING } };

		CSV csv = new CSV(cab, det, src);
		csv.start();
		while (csv.hashNext()) {
			String desc = csv.getDetalleString("DESCRIPCION");

			ArticuloModelo modelo = new ArticuloModelo();
			modelo.setDescripcion(desc.toUpperCase());
			rr.saveObject(modelo, "sys");

			System.out.println(desc);
		}
	}
	
	/**
	 * lineas de articulos..
	 */
	public static void migrarArticuloLinea() throws Exception {

		RegisterDomain rr = RegisterDomain.getInstance();
		String src = DIR_MIGRACION + "ARTICULO_LINEA.csv";

		String[][] cab = { { "Empresa", CSV.STRING } };
		String[][] det = { { "CODIGO", CSV.STRING }, { "DESCRIPCION", CSV.STRING } };

		CSV csv = new CSV(cab, det, src);
		csv.start();
		while (csv.hashNext()) {
			String desc = csv.getDetalleString("DESCRIPCION");

			ArticuloLinea linea = new ArticuloLinea();
			linea.setDescripcion(desc.toUpperCase());
			rr.saveObject(linea, "sys");

			System.out.println(desc);

		}
	}
	
	/**
	 * sub-lineas de articulos..
	 */
	public static void migrarArticuloSubLinea() throws Exception {

		RegisterDomain rr = RegisterDomain.getInstance();
		String src = DIR_MIGRACION + "ARTICULO_SUBLINEA.csv";

		String[][] cab = { { "Empresa", CSV.STRING } };
		String[][] det = { { "CODIGO", CSV.STRING }, { "DESCRIPCION", CSV.STRING } };

		CSV csv = new CSV(cab, det, src);
		csv.start();
		while (csv.hashNext()) {
			String desc = csv.getDetalleString("DESCRIPCION");

			ArticuloSubLinea sublinea = new ArticuloSubLinea();
			sublinea.setDescripcion(desc.toUpperCase());
			rr.saveObject(sublinea, "sys");

			System.out.println(desc);
		}
	}
	
	/**
	 * sub-grupos de articulos..
	 */
	public static void migrarArticuloSubGrupo() throws Exception {

		RegisterDomain rr = RegisterDomain.getInstance();
		String src = DIR_MIGRACION + "ARTICULO_SUBGRUPO.csv";

		String[][] cab = { { "Empresa", CSV.STRING } };
		String[][] det = { { "CODIGO", CSV.STRING }, { "DESCRIPCION", CSV.STRING } };

		CSV csv = new CSV(cab, det, src);
		csv.start();
		while (csv.hashNext()) {
			String desc = csv.getDetalleString("DESCRIPCION");

			ArticuloSubGrupo subgrupo = new ArticuloSubGrupo();
			subgrupo.setDescripcion(desc.toUpperCase());
			rr.saveObject(subgrupo, "sys");

			System.out.println(desc);
		}
	}
	
	/**
	 * API de articulos..
	 */
	public static void migrarArticuloAPI() throws Exception {

		RegisterDomain rr = RegisterDomain.getInstance();
		String src = DIR_MIGRACION + "ARTICULO_API.csv";

		String[][] cab = { { "Empresa", CSV.STRING } };
		String[][] det = { { "CODIGO", CSV.STRING }, { "DESCRIPCION", CSV.STRING } };

		CSV csv = new CSV(cab, det, src);
		csv.start();
		while (csv.hashNext()) {
			String desc = csv.getDetalleString("DESCRIPCION");

			ArticuloAPI api = new ArticuloAPI();
			api.setDescripcion(desc.toUpperCase());
			rr.saveObject(api, "sys");

			System.out.println(desc);
		}
	}
	
	/**
	 * procedencia de articulos..
	 */
	public static void migrarArticuloProcedencia() throws Exception {

		RegisterDomain rr = RegisterDomain.getInstance();
		String src = DIR_MIGRACION + "ARTICULO_PROCEDENCIA.csv";

		String[][] cab = { { "Empresa", CSV.STRING } };
		String[][] det = { { "CODIGO", CSV.STRING }, { "DESCRIPCION", CSV.STRING } };

		CSV csv = new CSV(cab, det, src);
		csv.start();
		while (csv.hashNext()) {
			String desc = csv.getDetalleString("DESCRIPCION");

			ArticuloProcedencia proc = new ArticuloProcedencia();
			proc.setDescripcion(desc.toUpperCase());
			rr.saveObject(proc, "sys");

			System.out.println(desc);
		}
	}
	
	/**
	 * indice de carga de articulos..
	 */
	public static void migrarArticuloIndicecarga() throws Exception {

		RegisterDomain rr = RegisterDomain.getInstance();
		String src = DIR_MIGRACION + "ARTICULO_INDICECARGA.csv";

		String[][] cab = { { "Empresa", CSV.STRING } };
		String[][] det = { { "CODIGO", CSV.STRING }, { "DESCRIPCION", CSV.STRING } };

		CSV csv = new CSV(cab, det, src);
		csv.start();
		while (csv.hashNext()) {
			String desc = csv.getDetalleString("DESCRIPCION");

			ArticuloIndicecarga indice = new ArticuloIndicecarga();
			indice.setDescripcion(desc.toUpperCase());
			rr.saveObject(indice, "sys");

			System.out.println(desc);
		}
	}
	
	/**
	 * lados de articulos..
	 */
	public static void migrarArticuloLados() throws Exception {

		RegisterDomain rr = RegisterDomain.getInstance();
		String src = DIR_MIGRACION + "ARTICULO_LADOS.csv";

		String[][] cab = { { "Empresa", CSV.STRING } };
		String[][] det = { { "CODIGO", CSV.STRING }, { "DESCRIPCION", CSV.STRING } };

		CSV csv = new CSV(cab, det, src);
		csv.start();
		while (csv.hashNext()) {
			String desc = csv.getDetalleString("DESCRIPCION");

			ArticuloLado lados = new ArticuloLado();
			lados.setDescripcion(desc.toUpperCase());
			rr.saveObject(lados, "sys");

			System.out.println(desc);
		}
	}
	
	/**
	 * articulos filtros..
	 */
	public static void migrarArticulos(String archivo) throws Exception {

		RegisterDomain rr = RegisterDomain.getInstance();
		String src = DIR_MIGRACION + archivo + ".csv";

		String[][] cab = { { "Empresa", CSV.STRING } };
		String[][] det = { { "CODIGO", CSV.STRING }, { "DESCRIPCION", CSV.STRING }, { "FAMILIA", CSV.STRING },
				{ "GRUPO", CSV.STRING }, { "MARCA", CSV.STRING }, { "APLICACION", CSV.STRING },
				{ "MODELO", CSV.STRING }, { "LINEA", CSV.STRING }, { "PESO", CSV.STRING }, { "VOLUMEN", CSV.STRING } };

		CSV csv = new CSV(cab, det, src);
		csv.start();
		while (csv.hashNext()) {
			String cod = csv.getDetalleString("CODIGO");
			String desc = csv.getDetalleString("DESCRIPCION");
			String flia = csv.getDetalleString("FAMILIA");
			String grupo = csv.getDetalleString("GRUPO");
			String marca = csv.getDetalleString("MARCA");
			String aplicacion = csv.getDetalleString("APLICACION");
			String modelo = csv.getDetalleString("MODELO");
			String linea = csv.getDetalleString("LINEA");
			String peso = csv.getDetalleString("PESO");
			String volumen = csv.getDetalleString("VOLUMEN");

			ArticuloFamilia artFlia = rr.getArticuloFamilia(flia);
			ArticuloGrupo artGrupo = rr.getArticuloGrupo(grupo);
			ArticuloMarca artMarca = rr.getArticuloMarca(marca);
			ArticuloAplicacion artAplicacion = rr.getArticuloAplicacion(aplicacion);
			ArticuloModelo artModelo = rr.getArticuloModelo(modelo);
			ArticuloLinea artLinea = rr.getArticuloLinea(linea);
			ArticuloSubLinea artSubLinea = rr.getArticuloSubLinea("SIN SUBLINEA");
			ArticuloSubGrupo artSubGrupo = rr.getArticuloSubGrupo("SIN SUBGRUPO");
			ArticuloProcedencia artProcedencia = rr.getArticuloProcedencia("SIN PROCEDENCIA");
			ArticuloAPI artAPI = rr.getArticuloAPI("SIN API");
			
			Articulo art = new Articulo();
			art.setCodigoInterno(cod);
			art.setDescripcion(desc);
			art.setPeso_(peso);
			art.setVolumen_(volumen);
			
			art.setFamilia(artFlia);
			art.setMarca(artMarca);
			art.setGrupo(artGrupo);
			art.setAplicacion(artAplicacion);
			art.setModelo(artModelo);
			art.setLinea(artLinea);
			art.setSublinea(artSubLinea);
			art.setSubgrupo(artSubGrupo);
			art.setProcedencia(artProcedencia);
			art.setAPI(artAPI);
			art.setArticuloEstado(rr.getTipoPorSigla(Configuracion.SIGLA_ARTICULO_ESTADO_ACTIVO));
			
			rr.saveObject(art, "sys");
			
			artMarca.setFamilia(flia);
			rr.saveObject(artMarca, "sys");

			System.out.println(art.getCodigoInterno());

		}
	}
	
	/**
	 * machear articulos / stock / costos / precios..
	 */
	public static void machearArticulosSaldos() throws Exception {
		RegisterDomain rr = RegisterDomain.getInstance();
		
		String[] archivos = { "ARTICULO_STOCK_MCAL_TEMPORAL", "ARTICULO_STOCK_MAYORISTA", "ARTICULO_STOCK_MAYORISTA_TEMPORAL" };
		
		for (int i = 0; i < archivos.length; i++) {
			String src = DIR_MIGRACION + archivos[i] + ".csv"; 								
			
			String[][] cab = { { "Empresa", CSV.STRING } };			
			String[][] det = { { "CODIGO", CSV.STRING }, { "DESCRIPCION", CSV.STRING }, { "FAMILIA", CSV.STRING },
					{ "MARCA", CSV.STRING }, { "ESTADO", CSV.STRING }, { "SALDO", CSV.STRING },
					{ "COSTO", CSV.STRING }, { "MAYORISTA", CSV.STRING }, { "DOLARES", CSV.STRING }, { "MINORISTA", CSV.STRING },
					{ "AUTOCENTRO", CSV.STRING } };
			
			Deposito dep = rr.getDepositoById(i + 5);
			
			CSV csv = new CSV(cab, det, src);
			csv.start();
			while (csv.hashNext()) {
				String codigo = csv.getDetalleString("CODIGO");
				String stock = csv.getDetalleString("SALDO");
				String costo = csv.getDetalleString("COSTO");
				String mayorista = csv.getDetalleString("MAYORISTA");
				String minorista = csv.getDetalleString("MINORISTA");
				String autocentro = csv.getDetalleString("AUTOCENTRO");
				
				Articulo art = rr.getArticulo(codigo);
				if (art != null) {
					art.setCostoGs(Double.parseDouble(costo));
					art.setPrecioGs(Double.parseDouble(mayorista));
					art.setPrecioListaGs(Double.parseDouble(autocentro));
					art.setPrecioMinoristaGs(Double.parseDouble(minorista));
					
					ArticuloDeposito ad = new ArticuloDeposito();
					ad.setArticulo(art);
					ad.setDeposito(dep);
					ad.setStock(Long.parseLong(stock));
					
					rr.saveObject(art, "sys");
					rr.saveObject(ad, "sys");
					System.out.println(codigo);
				} else {
					System.err.println(codigo);
				}
			}
		}
	}

	
	
	/**
	 * EMPRESAS - CLIENTES - PROVEEDORES
	 */
	
	
	/**
	 * rubros de empresas..
	 */
	public static void migrarRubrosClientes() throws Exception {
		RegisterDomain rr = RegisterDomain.getInstance();

		String[] rubros = new String[] { "VENTA DE CUBIERTAS", "TRANSPORTE DE PASAJEROS", "TRANSPORTE DE CARGAS",
				"TALLER MECANICO", "PUERTOS", "NAVIERA", "GOMERIA", "ESTACION DE SERVICIO", "DISTRIBUIDOR DE YHAGUY",
				"COOPERATIVAS", "CONSTRUCTORA", "CONSECIONARIAS", "CENTRO DE LUBRICACION", "CASA DE REPUESTOS",
				"CASA DE FILTROS", "CASA DE BATERIAS", "CONSUMIDOR FINAL" };
		
		String[] subrubros = new String[] { "CUBIERTERO", "TRANSPORTE", "TRANSPORTE", "TALLERES", "PUERTOS", "NAVIERA",
				"GOMERIA", "SURTIDORES", "DISTRIBUIDORES", "COOPERATIVAS", "CONSTRUCTORA", "CONSECIONARIAS",
				"LUBRICENTRISTAS", "REPUESTERO", "FILTRERO", "BATERISTAS", "FINALES" };
		
		Double[] descuentos = new Double[] { 10.0, 25.0, 20.0, 20.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0,
				10.0, 25.0, 10.0, 10.0, 10.0 };

		for (int i = 0; i < rubros.length; i++) {
			EmpresaRubro rubro = new EmpresaRubro();
			rubro.setDescripcion(rubros[i]);
			rubro.setSubrubro(subrubros[i]);
			rubro.setDescuentoRepuestos(descuentos[i]);
			rr.saveObject(rubro, "sys");
			System.out.println(rubro.getDescripcion());
		}
	}	
	
	/**
	 * vendedores..
	 */
	public static void migrarVendedores() throws Exception {
		RegisterDomain rr = RegisterDomain.getInstance();		
		String src = DIR_MIGRACION + "VENDEDORES.csv";

		String[][] cab = { { "Empresa", CSV.STRING } };
		String[][] det = { { "VENDEDOR", CSV.STRING }, { "DEPENDENCIA", CSV.STRING }, { "ZONA", CSV.STRING },
				{ "SUPERVISOR", CSV.STRING }, { "AUXI", CSV.STRING } };

		CSV csv = new CSV(cab, det, src);
		csv.start();
		while (csv.hashNext()) {
			String vendedor = csv.getDetalleString("VENDEDOR");
			String dependencia = csv.getDetalleString("DEPENDENCIA");
			String zona = csv.getDetalleString("ZONA");
			String supervisor = csv.getDetalleString("SUPERVISOR");
			String auxi = csv.getDetalleString("AUXI");
			
			Vendedor ven = new Vendedor();
			ven.setAuxi(auxi);
			ven.setDependencia(dependencia);
			ven.setNombre(vendedor);
			ven.setZona(zona);
			ven.setSupervisor(supervisor);
			
			rr.saveObject(ven, "sys");

			System.out.println(vendedor);
		}
	}
	
	/**
	 * clientes..
	 */
	public static void migrarClientes() throws Exception {
		RegisterDomain rr = RegisterDomain.getInstance();
		String src = DIR_MIGRACION + "CLIENTES.csv";

		String[][] cab = { { "Empresa", CSV.STRING } };
		String[][] det = { { "RAZONSOCIAL", CSV.STRING }, { "RUC", CSV.STRING }, { "DIRECCION", CSV.STRING },
				{ "TELEFONO", CSV.STRING }, { "RUBRO", CSV.STRING }, { "IDPERSONA", CSV.STRING } };
		
		EmpresaRubro rubroOcasional = rr.getEmpresaRubro("CONSUMIDOR FINAL");
		Empresa empOcasional = new Empresa();
		empOcasional.setRubro(rubroOcasional);
		empOcasional.setCodigoEmpresa(Configuracion.CODIGO_CLIENTE_OCASIONAL_CL);
		empOcasional.setRazonSocial("CONSUMIDOR FINAL");
		empOcasional.setRuc(Configuracion.RUC_EMPRESA_LOCAL);
		empOcasional.setMoneda(rr.getTipoPorSigla(Configuracion.SIGLA_MONEDA_GUARANI));
		rr.saveObject(empOcasional, "sys");
		
		Cliente cli = new Cliente();
		cli.setEmpresa(empOcasional);
		rr.saveObject(cli, "sys");
		
		System.out.println(empOcasional.getRazonSocial());

		CtaCteLineaCredito linea = new CtaCteLineaCredito();
		linea.setDescripcion(Configuracion.CTA_CTE_EMPRESA_LINEA_CREDITO_DEFAULT);
		linea.setLinea(0);
		rr.saveObject(linea, "sys");
		
		CSV csv = new CSV(cab, det, src);
		csv.start();
		while (csv.hashNext()) {
			String rs = csv.getDetalleString("RAZONSOCIAL");
			String ruc = csv.getDetalleString("RUC");
			String direccion = csv.getDetalleString("DIRECCION");
			String telefono = csv.getDetalleString("TELEFONO");
			String rubro = csv.getDetalleString("RUBRO");
			String idpersona = csv.getDetalleString("IDPERSONA");

			EmpresaRubro rubro_ = rr.getEmpresaRubro(rubro);
			
			Empresa emp = new Empresa();
			emp.setRazonSocial(rs.toUpperCase());
			emp.setNombre(rs.toUpperCase());
			emp.setRuc(ruc);
			emp.setDireccion_(direccion.toUpperCase());
			emp.setTelefono_(telefono);
			emp.setRubro(rubro_);
			rr.saveObject(emp, "sys");

			Cliente cl = new Cliente();
			cl.setEmpresa(emp);
			cl.setIdPersonaJedi(idpersona);
			rr.saveObject(cl, "sys");

			System.out.println(cl.getRazonSocial() + " - " + emp.getRubro().getDescripcion());

		}
	}
	
	/**
	 * machear clientes / vendedores / plan de venta..
	 */
	public static void machearClientesVendedores() throws Exception {
		RegisterDomain rr = RegisterDomain.getInstance();
		String src = DIR_MIGRACION + "CLIENTES_VENDEDOR.csv";
		
		String[][] cab = { { "Empresa", CSV.STRING } };			
		String[][] det = { { "CLIENTE", CSV.STRING }, { "VENDEDOR", CSV.STRING }, { "DEPARTAMENTO", CSV.STRING },
				{ "CIUDAD", CSV.STRING }, { "PLANVENTA", CSV.STRING }, { "DESCUENTO", CSV.STRING }, { "IDPERSONA", CSV.STRING } };
		
		CSV csv = new CSV(cab, det, src);
		csv.start();
		while (csv.hashNext()) {
			String razonsocial = csv.getDetalleString("CLIENTE");
			String vendedor = csv.getDetalleString("VENDEDOR");
			String listaprecio = csv.getDetalleString("PLANVENTA");
			String descuento = csv.getDetalleString("DESCUENTO");
			String idpersona = csv.getDetalleString("IDPERSONA");
			String departamento = csv.getDetalleString("DEPARTAMENTO");
			String ciudad = csv.getDetalleString("CIUDAD");
			
			Vendedor vend = rr.getVendedor(vendedor);
			ArticuloListaPrecio lp = rr.getArticuloListaPrecio(listaprecio);
			Cliente cli = rr.getClienteByIdpersona(idpersona);
			double desc = Double.parseDouble(descuento.replace("%", ""));
			if (cli != null) {
				cli.setVendedor(vend);
				cli.setListaPrecio(lp);
				cli.setDescuentoBaterias(desc);
				cli.setDescuentoCubiertas(desc);
				cli.setDescuentoFiltros(desc);
				cli.setDescuentoLubricantes(desc);
				cli.setDescuentoRepuestos(desc);
				cli.setDepartamento(departamento);
				cli.setCiudad(ciudad);
				rr.saveObject(cli, "sys");

				System.out.println(cli.getRazonSocial());
			} else {
				System.err.println(razonsocial);
			}
		}
	}
	
	/**
	 * machear clientes / saldos / linea de credito / cartera..
	 */
	public static void machearClientesSaldos() throws Exception {
		RegisterDomain rr = RegisterDomain.getInstance();
		String src = DIR_MIGRACION + "CLIENTES_SALDOS.csv";												
		
		String[][] cab = { { "Empresa", CSV.STRING } };			
		String[][] det = { { "IDMOVIMIENTO", CSV.STRING }, { "NUMERO", CSV.STRING }, { "FECHA", CSV.STRING },
				{ "VENCIMIENTO", CSV.STRING }, { "IDPERSONA", CSV.STRING }, { "IDCUENTA", CSV.STRING },
				{ "PERSONA", CSV.STRING }, { "RUC", CSV.STRING }, { "MONEDA", CSV.STRING }, { "ESTADO", CSV.STRING },
				{ "LIMITECREDITO", CSV.STRING }, { "CARTERA", CSV.STRING }, { "SALDO_FACTURA", CSV.STRING }};
		
		CSV csv = new CSV(cab, det, src);
		csv.start();
		while (csv.hashNext()) {
			String idmovim = csv.getDetalleString("IDMOVIMIENTO");
			String nro = csv.getDetalleString("NUMERO");
			String fecha = csv.getDetalleString("FECHA");
			String vto = csv.getDetalleString("VENCIMIENTO");
			String razonsocial = csv.getDetalleString("PERSONA");
			String idpersona = csv.getDetalleString("IDPERSONA");
			String estado = csv.getDetalleString("ESTADO");
			String limcred = csv.getDetalleString("LIMITECREDITO");
			String cartera = csv.getDetalleString("CARTERA");
			String saldofac = csv.getDetalleString("SALDO_FACTURA");
			
			Cliente cli = rr.getClienteByIdpersona(idpersona);
			double saldo = Double.parseDouble(saldofac);
			if (limcred.isEmpty() || limcred.equals("Null") || limcred.equals("LIMITECREDITO")) {
				limcred = "0";
			}
			double linea = Double.parseDouble(limcred);
			boolean bloqueado = (!estado.equals("0"));
			if (cli != null) {
				Empresa emp = cli.getEmpresa();
				emp.setCuentaBloqueada(bloqueado);
				cli.setLimiteCredito(linea);
				cli.setVentaCredito(true);
				
				rr.saveObject(emp, "sys");
				rr.saveObject(cli, "sys");
				
				CtaCteEmpresaMovimiento movim = new CtaCteEmpresaMovimiento();
				movim.setAnulado(false);
				movim.setCerrado(false);
				movim.setFechaEmision(Utiles.getFecha(fecha, "dd/MM/yyyy hh:mm:ss"));
				movim.setFechaVencimiento(Utiles.getFecha(vto, "dd/MM/yyyy hh:mm:ss"));
				movim.setIdEmpresa(emp.getId());
				movim.setIdMovimientoOriginal(Long.parseLong(idmovim));
				movim.setIdVendedor(0);
				movim.setImporteOriginal(Double.parseDouble(saldofac));
				movim.setMoneda(rr.getTipoPorSigla(Configuracion.SIGLA_MONEDA_GUARANI));
				movim.setNroComprobante(nro);
				movim.setNumeroImportacion("");
				movim.setCartera(cartera);
				movim.setSaldo(saldo);
				movim.setSucursal(rr.getSucursalAppById(2));
				movim.setTipoCambio(1);
				movim.setTipoCaracterMovimiento(rr.getTipoPorSigla(Configuracion.SIGLA_CTA_CTE_CARACTER_MOV_CLIENTE));
				movim.setTipoMovimiento(rr.getTipoMovimientoBySigla(Configuracion.SIGLA_TM_FAC_VENTA_CREDITO));
				
				rr.saveObject(movim, "sys");
				System.out.println(cli.getRazonSocial() + " " + saldofac);
			} else {
				System.err.println(razonsocial);
			}
		}
	}
	
	/**
	 * proveedores..
	 */
	public static void migrarProveedores() throws Exception {

		RegisterDomain rr = RegisterDomain.getInstance();
		String src = DIR_MIGRACION + "PROVEEDORES.csv";
		String src_ = DIR_MIGRACION + "PROVEEDORES_SALDOS.csv";

		String[][] cab = { { "Empresa", CSV.STRING } };
		String[][] det = { { "IDCUENTA", CSV.STRING }, { "RAZONSOCIAL", CSV.STRING }, { "RUC", CSV.STRING },
				{ "TELEFONO", CSV.STRING }, { "DIRECCION", CSV.STRING } };		  	  

		String[][] det_ = { { "RAZONSOCIAL", CSV.STRING }, { "NRODOCUMENTO", CSV.STRING }, { "CONCEPTO", CSV.STRING },
				{ "EMISION", CSV.STRING }, { "VENCIMIENTO", CSV.STRING }, { "IMPORTE", CSV.STRING }, { "SALDO", CSV.STRING } };

		Map<String, Object[]> map = new HashMap<String, Object[]>();
		
		CSV csv = new CSV(cab, det, src);
		csv.start();
		while (csv.hashNext()) {
			String idcuenta = csv.getDetalleString("IDCUENTA");
			String rs = csv.getDetalleString("RAZONSOCIAL");
			String ruc = csv.getDetalleString("RUC");
			String direccion = csv.getDetalleString("DIRECCION");
			String telefono = csv.getDetalleString("TELEFONO");
			
			Object[] datos = { rs, ruc, direccion, telefono };
			map.put(idcuenta, datos);
		}
		
		CSV csv_ = new CSV(cab, det_, src_);
		csv_.start();
		while (csv_.hashNext()) {
			String razonsocial = csv_.getDetalleString("RAZONSOCIAL");
			String concepto = csv_.getDetalleString("CONCEPTO");
			String nrodocumento = csv_.getDetalleString("NRODOCUMENTO");
			String emision = csv_.getDetalleString("EMISION");
			String vencimiento = csv_.getDetalleString("VENCIMIENTO");
			String importe = csv_.getDetalleString("IMPORTE");
			String saldo = csv_.getDetalleString("SALDO");
			
			String idcuenta = razonsocial.substring(razonsocial.indexOf("COD."), razonsocial.length()).replace("COD. CAT.:", "");
			Object[] datos = map.get(idcuenta);
			
			if (datos != null) {
				Empresa emp = rr.getEmpresa(((String) datos[0]).replace("'", ""));
				if (emp == null) {
					emp = new Empresa();
					emp.setRazonSocial((String) datos[0]);
					emp.setNombre((String) datos[0]);
					emp.setRuc((String) datos[1]);
					emp.setDireccion_((String) datos[2]);
					emp.setTelefono_((String) datos[3]);
					rr.saveObject(emp, "sys");

					Proveedor pr = new Proveedor();
					pr.setEmpresa(emp);
					rr.saveObject(pr, "sys");
				}
				
				CtaCteEmpresaMovimiento movim = new CtaCteEmpresaMovimiento();
				movim.setAnulado(false);
				movim.setCerrado(false);
				movim.setFechaEmision(Utiles.getFecha(emision, "dd/MM/yyyy"));
				movim.setFechaVencimiento(Utiles.getFecha(vencimiento, "dd/MM/yyyy"));
				movim.setIdEmpresa(emp.getId());
				movim.setIdMovimientoOriginal(0);
				movim.setIdVendedor(0);
				movim.setImporteOriginal(Double.parseDouble(importe));
				movim.setMoneda(concepto.equals("Proveedores Ext.") ? rr.getTipoPorSigla(Configuracion.SIGLA_MONEDA_DOLAR) : rr.getTipoPorSigla(Configuracion.SIGLA_MONEDA_GUARANI));
				movim.setNroComprobante(nrodocumento);
				movim.setNumeroImportacion("");
				movim.setCartera("");
				movim.setSaldo(Double.parseDouble(saldo));
				movim.setSucursal(rr.getSucursalAppById(2));
				movim.setTipoCambio(1);
				movim.setTipoCaracterMovimiento(rr.getTipoPorSigla(Configuracion.SIGLA_CTA_CTE_CARACTER_MOV_PROVEEDOR));
				movim.setTipoMovimiento(rr.getTipoMovimientoBySigla(Configuracion.SIGLA_TM_FAC_COMPRA_CREDITO));
				
				rr.saveObject(movim, "sys");
				
				System.out.println(movim.getNroComprobante());
			
			} else {
				System.err.println(razonsocial);
			}
		}
	}
	
	public static void main(String[] args) {
		try {			
			
		/**	MigracionCentral.migrarSucursales();
			MigracionCentral.migrarFuncionarios();
			MigracionCentral.migrarUsuariosCentral();
			MigracionCentral.migrarUsuariosMCAL();
			MigracionCentral.migrarUsuariosGAM();
			MigracionCentral.migrarPlanDeCuenta();
			MigracionCentral.migrarCuentasContables(); **/ 
			
		 /**MigracionCentral.migrarArticuloFamilias();
			MigracionCentral.migrarArticuloGrupo();
			MigracionCentral.migrarArticuloMarca();
			MigracionCentral.migrarArticuloAplicacion();
			MigracionCentral.migrarArticuloModelo();
			MigracionCentral.migrarArticuloLinea();
			MigracionCentral.migrarArticuloSubLinea();
			MigracionCentral.migrarArticuloSubGrupo();
			MigracionCentral.migrarArticuloAPI();
			MigracionCentral.migrarArticuloProcedencia();
			MigracionCentral.migrarArticulos("ARTICULO_FILTROS");
			MigracionCentral.migrarArticulos("ARTICULO_LUBRICANTES");
			MigracionCentral.migrarArticulos("ARTICULO_NEUMATICOS");
			MigracionCentral.migrarArticulos("ARTICULO_REPUESTOS"); **/
		MigracionCentral.machearArticulosSaldos();
			
		//	MigracionCentral.migrarVendedores();
		//	MigracionCentral.migrarRubrosClientes();
		//	MigracionCentral.migrarClientes();
		//	MigracionCentral.machearClientesVendedores();
		//	MigracionCentral.machearClientesSaldos();
			MigracionCentral.migrarProveedores();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
