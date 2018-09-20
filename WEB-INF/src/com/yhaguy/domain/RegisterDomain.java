package com.yhaguy.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.coreweb.domain.Formulario;
import com.coreweb.domain.IiD;
import com.coreweb.domain.Modulo;
import com.coreweb.domain.Operacion;
import com.coreweb.domain.Perfil;
import com.coreweb.domain.Permiso;
import com.coreweb.domain.Register;
import com.coreweb.domain.Tipo;
import com.coreweb.domain.TipoTipo;
import com.coreweb.domain.Usuario;
import com.coreweb.extras.csv.CSV;
import com.coreweb.util.Misc;
import com.coreweb.util.MyArray;
import com.yhaguy.Configuracion;
import com.yhaguy.util.Utiles;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class RegisterDomain extends Register {

	// El register tiene que ser un sigleton
	private RegisterDomain() {
		super();
	}

	public synchronized static RegisterDomain getInstance() {
		return (RegisterDomain) Register.getInstanceCore(RegisterDomain.class.getName());
	}

	Misc misc = new Misc();

	/************************************************************
	 * Metodos especificos de las clases del dominio
	 ************************************************************/

	public List getTestAs() throws Exception {
		List out = super.getObjects(com.yhaguy.domain.TestA.class.getName(),
				new Vector(), new Vector());
		return out;
	}

	public int getNumeroTestA() throws Exception {
		int out = super.getSizeObjects(com.yhaguy.domain.TestA.class.getName(),
				new Vector());
		return out;
	}

	public List getArticulos() throws Exception {
		List out = super.getObjects(com.yhaguy.domain.Articulo.class.getName(),
				new Vector(), new Vector());
		return out;
	}

	public List<Cliente> getClientes() throws Exception {
		List out = super.getObjects(com.yhaguy.domain.Cliente.class.getName(),
				new Vector(), new Vector());
		return out;
	}

	public List getProveedores() throws Exception {
		List out = super.getObjects(
				com.yhaguy.domain.Proveedor.class.getName(), new Vector(),
				new Vector());
		return out;
	}

	public List<Funcionario> getFuncionarios() throws Exception {
		List out = super.getObjects(
				com.yhaguy.domain.Funcionario.class.getName(), new Vector(),
				new Vector());
		return out;
	}

	public List getSucursales() throws Exception {
		List out = super.getObjects(com.yhaguy.domain.Sucursal.class.getName(),
				new Vector(), new Vector());
		return out;
	}

	public List getContactosSexo() throws Exception {
		List out = super.getObjects(
				com.yhaguy.domain.ContactoSexo.class.getName(), new Vector(),
				new Vector());
		return out;
	}

	public List getEstadosCiviles() throws Exception {
		List out = super.getObjects(
				com.yhaguy.domain.EstadoCivil.class.getName(), new Vector(),
				new Vector());
		return out;
	}

	public List<Tipo> getMonedas() throws Exception {
		List<Tipo> out = this.getTipos(Configuracion.ID_TIPO_MONEDA);
		return out;
	}

	public List getLineasCredito() throws Exception {
		List out = super.getObjects(
				com.yhaguy.domain.CtaCteLineaCredito.class.getName(),
				new Vector(), new Vector());
		return out;
	}

	public List getImportacionCondicionPagos() throws Exception {
		List out = super.getObjects(
				com.yhaguy.domain.CondicionPago.class.getName(), new Vector(),
				new Vector());
		return out;
	}

	public List getImportacionPedidosCompra() throws Exception {
		List out = super.getObjects(
				com.yhaguy.domain.ImportacionPedidoCompra.class.getName(),
				new Vector(), new Vector());
		return out;
	}

	public List<DepartamentoApp> getDepartamentosApp() throws Exception {
		List<DepartamentoApp> out = super.getObjects(
				com.yhaguy.domain.DepartamentoApp.class.getName(),
				new Vector(), new Vector());
		return out;
	}

	public int getNumeroCliente() throws Exception {
		int out = super.getSizeObjects(
				com.yhaguy.domain.Cliente.class.getName(), new Vector());
		return out;
	}

	public int getNumeroEstadoCivil() throws Exception {
		int out = super.getSizeObjects(
				com.yhaguy.domain.EstadoCivil.class.getName(), new Vector());
		return out;
	}

	public int getNumeroLineaCredito() throws Exception {
		int out = super.getSizeObjects(
				com.yhaguy.domain.CtaCteLineaCredito.class.getName(),
				new Vector());
		return out;
	}

	public int getNumeroPersona() throws Exception {
		int out = super.getSizeObjects(
				com.yhaguy.domain.Empresa.class.getName(), new Vector());
		return out;
	}

	public int getNumeroPersonaSexo() throws Exception {
		int out = super.getSizeObjects(
				com.yhaguy.domain.ContactoSexo.class.getName(), new Vector());
		return out;
	}

	public int getNumeroSucursal() throws Exception {
		int out = super.getSizeObjects(
				com.yhaguy.domain.Sucursal.class.getName(), new Vector());
		return out;
	}

	public Proveedor getProveedorByCodigo(String codigoEmpresa)
			throws Exception {
		Vector v = new Vector();
		v.add(Restrictions.like("codigoEmpresa", codigoEmpresa));
		List<Empresa> emp = getObjects(
				com.yhaguy.domain.Empresa.class.getName(), v, new Vector());
		Proveedor proveedor = null;
		for (int i = 0; i < emp.size(); i++) {
			proveedor = this.getSupplierByEmpresa(emp.get(i));
		}
		return proveedor;
	}

	public DepartamentoApp getDepartamentoAppById(long id) throws Exception {
		return (DepartamentoApp) getObject(
				com.yhaguy.domain.DepartamentoApp.class.getName(), id);
	}

	public Cliente getClienteById(long id) throws Exception {
		return (Cliente) getObject(com.yhaguy.domain.Cliente.class.getName(),
				id);
	}

	public ContactoInterno getContactoInternoObjects(long id) throws Exception {
		return (ContactoInterno) getObject(
				com.yhaguy.domain.ContactoInterno.class.getName(), id);
	}

	public AccesoApp getAccesoAppById(long id) throws Exception {
		return (AccesoApp) getObject(
				com.yhaguy.domain.AccesoApp.class.getName(), id);
	}

	public Empresa getEmpresaById(long id) throws Exception {
		return (Empresa) getObject(com.yhaguy.domain.Empresa.class.getName(), id);
	}

	public Proveedor getProveedorById(long id) throws Exception {
		return (Proveedor) getObject(
				com.yhaguy.domain.Proveedor.class.getName(), id);
	}

	public CtaCteLineaCredito getLineaCreditoById(long id) throws Exception {
		return (CtaCteLineaCredito) getObject(
				com.yhaguy.domain.CtaCteLineaCredito.class.getName(), id);
	}

	public ContactoInterno getContactoInternoById(long id) throws Exception {
		return (ContactoInterno) getObject(
				com.yhaguy.domain.ContactoInterno.class.getName(), id);
	}

	public Funcionario getFuncionarioById(long id) throws Exception {
		return (Funcionario) getObject(
				com.yhaguy.domain.Funcionario.class.getName(), id);
	}

	public Contacto getContactoById(long id) throws Exception {
		return (Contacto) getObject(com.yhaguy.domain.Contacto.class.getName(),
				id);
	}

	public ContactoSexo getContactoSexoById(long id) throws Exception {
		return (ContactoSexo) getObject(
				com.yhaguy.domain.ContactoSexo.class.getName(), id);
	}

	public EstadoCivil getEstadoCivilById(long id) throws Exception {
		return (EstadoCivil) getObject(
				com.yhaguy.domain.EstadoCivil.class.getName(), id);
	}

	public Sucursal getSucursalById(long id) throws Exception {
		return (Sucursal) getObject(com.yhaguy.domain.Sucursal.class.getName(),
				id);
	}

	public CuentaContable getCuentaContableById(long id) throws Exception {
		return (CuentaContable) getObject(
				com.yhaguy.domain.CuentaContable.class.getName(), id);
	}

	public Gasto getGastoById(long id) throws Exception {
		return (Gasto) getObject(com.yhaguy.domain.Gasto.class.getName(), id);
	}

	public CondicionPago getCondicionPagoById(long id) throws Exception {
		return (CondicionPago) getObject(
				com.yhaguy.domain.CondicionPago.class.getName(), id);
	}

	public BancoCheque getChequeById(long id) throws Exception {
		return (BancoCheque) getObject(
				com.yhaguy.domain.BancoCheque.class.getName(), id);
	}

	public TipoMovimiento getTipoMovimientoById(long id) throws Exception {
		return (TipoMovimiento) getObject(
				com.yhaguy.domain.TipoMovimiento.class.getName(), id);
	}

	public Reserva getReservaById(long id) throws Exception {
		return (Reserva) getObject(com.yhaguy.domain.Reserva.class.getName(),
				id);
	}

	public ReservaDetalle getReservaDetalleById(long id) throws Exception {
		return (ReservaDetalle) getObject(
				com.yhaguy.domain.ReservaDetalle.class.getName(), id);
	}

	public VentaDetalle getVentaPedidoDetalleById(long id) throws Exception {
		return (VentaDetalle) getObject(
				com.yhaguy.domain.VentaDetalle.class.getName(), id);
	}

	public SucursalApp getSucursalAppById(long id) throws Exception {
		return (SucursalApp) getObject(
				com.yhaguy.domain.SucursalApp.class.getName(), id);
	}

	public CompraLocalFactura getFacturaCompraById(long id) throws Exception {
		return (CompraLocalFactura) getObject(
				CompraLocalFactura.class.getName(), id);
	}

	public SucursalApp getSucursalAppByNombre(String nombre) throws Exception {
		String query = "select sucursal from SucursalApp sucursal where sucursal.nombre = '"
				+ nombre + "'";
		Object o = this.hqlToObject(query);
		SucursalApp sucursal = (SucursalApp) o;
		return sucursal;
	}

	public List getClienteByCedula(int cedula) throws Exception {
		Vector v = new Vector();
		// v.add(Restrictions.like("cedula", cedula));
		v.add(Restrictions.eq("cedula", cedula));
		return getObjects(com.yhaguy.domain.Cliente.class.getName(), v,
				new Vector());
	}

	public Empresa SESSIONgetEmpresaByRuc(String ruc, Session session)
			throws Exception {
		// estaba que retornada una lista, pero debería ser una sola
		// no deberíamos tener dos empresas con el mismo RUC

		String query = "select e from Empresa e where e.ruc = ? ";
		String[] param = { ruc };
		RegisterDomain rr = RegisterDomain.getInstance();
		List l = rr.SESSIONhql(query, param, session);
		if (l.size() == 1) {
			return (Empresa) l.get(0);
		}
		return null;
	}

	public Empresa getEmpresaByRuc(String ruc) throws Exception {
		Empresa out = (Empresa) getObject(Empresa.class.getName(), "ruc", ruc);
		return out;
	}

	public List getEmpresaByRazonSocial(String razonsocial) throws Exception {
		Vector v = new Vector();
		v.add(Restrictions.like("razonsocial", razonsocial));
		// v.add(Restrictions.eq("ruc", ruc));
		return getObjects(com.yhaguy.domain.Empresa.class.getName(), v,
				new Vector());
	}

	public List getClienteByEmpresa(Empresa empresa) throws Exception {
		Vector v = new Vector();
		// v.add(Restrictions.like("cedula", cedula));
		v.add(Restrictions.eq("empresa", empresa));
		return getObjects(com.yhaguy.domain.Cliente.class.getName(), v,
				new Vector());
	}

	public List getProveedorByEmpresa(Empresa empresa) throws Exception {
		Vector v = new Vector();
		v.add(Restrictions.eq("empresa", empresa));
		return getObjects(com.yhaguy.domain.Proveedor.class.getName(), v,
				new Vector());
	}

	public Proveedor getSupplierByEmpresa(Empresa empresa) throws Exception {
		Vector v = new Vector();
		v.add(Restrictions.eq("empresa", empresa));
		List<Proveedor> prv = getObjects(
				com.yhaguy.domain.Proveedor.class.getName(), v, new Vector());
		Proveedor proveedor = null;
		for (int i = 0; i < prv.size(); i++) {
			proveedor = prv.get(i);
		}
		return proveedor;
	}

	public List getContactoByCedula(String cedula) throws Exception {
		Vector v = new Vector();
		// v.add(Restrictions.like("cedula", cedula));
		v.add(Restrictions.eq("cedula", cedula));
		return getObjects(com.yhaguy.domain.Contacto.class.getName(), v,
				new Vector());
	}

	/**
	 * @return el articulo a partir del codigo interno..
	 */
	public Articulo getArticuloByCodigoInterno(String codInterno)
			throws Exception {

		Vector v = new Vector();
		v.add(Restrictions.eq("codigoInterno", codInterno));
		List<Articulo> art = getObjects(
				com.yhaguy.domain.Articulo.class.getName(), v, new Vector());
		Articulo articulo = null;
		for (int i = 0; i < art.size(); i++) {
			articulo = art.get(i);
		}

		return articulo;
	}

	public ArticuloCosto getArticuloCostoByArticulo(Articulo articulo)
			throws Exception {
		Vector v = new Vector();
		v.add(Restrictions.eq("articulo", articulo));
		List<ArticuloCosto> art = getObjects(
				com.yhaguy.domain.ArticuloCosto.class.getName(), v,
				new Vector());
		ArticuloCosto articuloCosto = null;
		for (int i = 0; i < art.size(); i++) {
			articuloCosto = art.get(i);
		}
		return articuloCosto;
	}

	public ArticuloMarcaAplicacion getArticuloMarcaAplicacionById(long id)
			throws Exception {
		return (ArticuloMarcaAplicacion) getObject(
				com.yhaguy.domain.ArticuloMarcaAplicacion.class.getName(), id);
	}

	public ArticuloMarcaAplicacion getArticuloMarcaAplicacionByDescripcion(
			String desc) throws Exception {
		String query = "select a from ArticuloMarcaAplicacion a"
				+ " where a.descripcion like '" + desc + "'";
		ArticuloMarcaAplicacion out = (ArticuloMarcaAplicacion) hqlToObject(query);
		return out;
	}

	public ArticuloModeloAplicacion getArticuloModeloAplicacionByDescripcion(
			String desc, String marca) throws Exception {

		String query = "select ma from ArticuloModeloAplicacion ma "
				+ "where ma.descripcion = '" + desc
				+ "' and ma.articuloMarcaAplicacion.sigla = '" + marca + "'";

		ArticuloModeloAplicacion ma = (ArticuloModeloAplicacion) this
				.hqlToObject(query);

		return ma;

	}

	public ArticuloModeloAplicacion getArticuloModeloAplicacionById(long id)
			throws Exception {
		return (ArticuloModeloAplicacion) getObject(
				com.yhaguy.domain.ArticuloModeloAplicacion.class.getName(), id);
	}

	public Articulo getArticuloById(long id) throws Exception {
		return (Articulo) getObject(com.yhaguy.domain.Articulo.class.getName(),
				id);
	}

	public ArticuloCosto getArticuloCostoById(long id) throws Exception {
		return (ArticuloCosto) getObject(
				com.yhaguy.domain.ArticuloCosto.class.getName(), id);
	}

	public ArticuloPresentacion getArticuloPresentacionById(long id)
			throws Exception {
		return (ArticuloPresentacion) getObject(
				com.yhaguy.domain.ArticuloPresentacion.class.getName(), id);
	}

	public int getNumeroArticulo() throws Exception {
		int out = super.getSizeObjects(
				com.yhaguy.domain.Articulo.class.getName(), new Vector());
		return out;
	}

	// *********************************************************************************
	// METODOS PARA GESTION DE IMPORTACION
	// *********************************************************************************

	public CondicionPago getImportacionCondicionPagoById(long id)
			throws Exception {
		return (CondicionPago) getObject(
				com.yhaguy.domain.CondicionPago.class.getName(), id);
	}

	public ImportacionPedidoCompraDetalle getImportacionPedidoDetalleById(
			long id) throws Exception {
		return (ImportacionPedidoCompraDetalle) getObject(
				com.yhaguy.domain.ImportacionPedidoCompraDetalle.class
						.getName(),
				id);
	}

	public ImportacionPedidoCompra getImportacionPedidoCompraById(long id)
			throws Exception {
		return (ImportacionPedidoCompra) getObject(
				com.yhaguy.domain.ImportacionPedidoCompra.class.getName(), id);
	}

	public ImportacionFactura getImportacionFacturaById(long id)
			throws Exception {
		return (ImportacionFactura) getObject(
				com.yhaguy.domain.ImportacionFactura.class.getName(), id);
	}

	public ImportacionFacturaDetalle getImportacionFacturaDetalleById(long id)
			throws Exception {
		return (ImportacionFacturaDetalle) getObject(
				com.yhaguy.domain.ImportacionFacturaDetalle.class.getName(), id);
	}

	public ImportacionResumenGastosDespacho getImportacionGastosDespachoById(
			long id) throws Exception {
		return (ImportacionResumenGastosDespacho) getObject(
				com.yhaguy.domain.ImportacionResumenGastosDespacho.class
						.getName(),
				id);
	}

	public List getImportacionPedidoCompraByFechaCreacion(Date fecha)
			throws Exception {
		Vector v = new Vector();
		v.add(Restrictions.ge("fechaCreacion", fecha));
		return getObjects(
				com.yhaguy.domain.ImportacionPedidoCompra.class.getName(), v,
				new Vector());
	}

	public List getImportacionPedidoCompra() throws Exception {
		Vector v = new Vector();
		v.add(Order.asc("numeroPedidoCompra"));
		return getObjects(
				com.yhaguy.domain.ImportacionPedidoCompra.class.getName(),
				new Vector(), v);
	}

	public List<Object[]> getArticuloCodigoFabrica(long idProveedor,
			String codigoArticuloProveedor) throws Exception {
		String query = ""
				+ " select art.codigoInterno, pa.proveedor.empresa.nombre "
				+ " from Articulo art join art.proveedorArticulos pa"
				+ " where pa.proveedor.id = " + idProveedor
				+ " and pa.codigoArticuloProveedor = '"
				+ codigoArticuloProveedor.toUpperCase() + "'";

		List<Object[]> list = this.hql(query);

		return list;
	}

	/**
	 * @return los articulos de referencia..
	 */
	public List<ArticuloReferencia> getArticulosReferencias(long idArticulo)
			throws Exception {
		String query = "Select ar from ArticuloReferencia ar "
				+ " where ar.idArticulo = " + idArticulo;
		return this.hql(query);
	}

	/**
	 * @return los articulos a los cuales se hace referencia..
	 */
	public List<ArticuloReferencia> getArticulosReferenciados(long idArticulo)
			throws Exception {
		String query = "Select ar from ArticuloReferencia ar "
				+ " where ar.idReferencia = " + idArticulo;
		return this.hql(query);
	}

	public List<Object[]> getArticulosSinReferencias() throws Exception {
		List<Object[]> list = null;
		String query = ""
				+ " select art.id, art.codigoInterno, art.descripcion "
				+ " from Articulo art "
				+ " where   art.id not in ( select ar.idArticulo "
				+ "                    from ArticuloReferencia ar )   ";
		list = this.hql(query);

		return list;
	}

	public List<Object[]> getArticulosTodos() throws Exception {
		List<Object[]> list = null;
		String query = ""
				+ " select art.id, art.codigoInterno, art.descripcion "
				+ " from Articulo art   ";
		list = this.hql(query);

		return list;
	}

	// Retorna la lista de items con codigo Interno @Gastos - @Descuento
	public List<Articulo> getItemsGastoDescuento() throws Exception {

		String codGasto = Configuracion.CODIGO_ITEM_GASTO_KEY;
		String codDescto = Configuracion.CODIGO_ITEM_DESCUENTO_KEY;
		String codProrrateo = Configuracion.CODIGO_ITEM_PRORRATEO_KEY;

		List<Articulo> list = null;
		String query = "select a from Articulo a where a.codigoInterno "
				+ "like '" + codGasto + "%' or a.codigoInterno like '"
				+ codDescto + "%' or a.codigoInterno like '" + codProrrateo
				+ "%'";
		list = this.hql(query);

		return list;
	}

	public List<Object[]> getTiposGastoDescuento(String t) throws Exception {
		List<Object[]> list = null;
		String query = "" + " select tipo.id, tipo.codigo, tipo.descripcion "
				+ " from TipoDescuento tipo where tipo.tipo like " + "'" + t
				+ "'";
		list = this.hql(query);

		return list;
	}

	// Retorna un MyArray con el Codigo y la Razon Social del Proveedor en base
	// al id del Pedido Compra
	public MyArray getDatosProveedorByImportacionPedidoCompra(long id)
			throws Exception {

		List<Proveedor> list = null;
		String query = "" + " select ped.proveedor"
				+ " from ImportacionPedidoCompra ped where ped.id = " + id;
		list = this.hql(query);
		MyArray m = new MyArray();

		if (list.size() == 1) {
			Proveedor prv = list.get(0);
			m.setPos1(prv.getEmpresa().getCodigoEmpresa()); // proveedor
															// codigoEmpresa
			m.setPos2(prv.getRazonSocial()); // proveedor razonSocial
			m.setPos3(prv.getCuentaContable().getAlias()); // proveedor alias
															// cta contable
		}
		return m;
	}

	public List<MyArray> getProveedores_Query() throws Exception {
		List<Object[]> list = null;
		String query = ""
				+ " select prv.id, prv.empresa.codigoEmpresa, prv.empresa.razonSocial, prv.empresa.ruc "
				+ " from Proveedor prv";
		list = this.hql(query);
		List<MyArray> out = new ArrayList<MyArray>();
		for (int i = 0; i < list.size(); i++) {
			MyArray m = new MyArray();
			m.setId((Long) list.get(i)[0]); // proveedor id
			m.setPos1(list.get(i)[1]); // proveedor codigoEmpresa
			m.setPos2(list.get(i)[2]); // proveedor razonSocial
			m.setPos3(list.get(i)[3]); // proveedor ruc
			out.add(m);
		}
		return out;
	}

	/**
	 * @return los timbrados segun el id del proveedor..
	 */
	public List<MyArray> getTimbrados(long idproveedor, String timbrado)
			throws Exception {
		List<Object> list = null;
		String query = " select t " + " from Proveedor p join p.timbrados t "
				+ " where p.id=" + idproveedor
				+ " and t.vencimiento > current_date";
		list = this.hql(query);
		List<MyArray> out = new ArrayList<MyArray>();
		for (int i = 0; i < list.size(); i++) {
			Timbrado t = (Timbrado) list.get(i);
			MyArray m = new MyArray();
			m.setId(t.getId()); // timbrado id
			m.setPos1(t.getNumero()); // timbrado numero
			m.setPos2(t.getVencimiento()); // timbrado vencimiento
			out.add(m);
		}
		return out;
	}

	// Devuelve el tipo de Cambio del Despacho, recibe como parametro el id del
	// Pedido Compra..
	public Object[] getTipoCambioDespacho(long id) throws Exception {
		List<Object[]> list = null;
		String query = ""
				+ " select desp.id, desp.tipoCambio from ImportacionPedidoCompra ped "
				+ " join ped.resumenGastosDespacho desp" + " where ped.id = "
				+ id;
		list = this.hql(query);
		return list.get(0);
	}

	public List<AccesoApp> getAllAccesos() throws Exception {
		List l = getObjects(com.yhaguy.domain.AccesoApp.class.getName(),
				new Vector(), new Vector());
		return l;
	}

	public AccesoApp getAcceso(String login) throws Exception {
		List<AccesoApp> list = null;
		AccesoApp a = new AccesoApp();
		String queryAcceso = "" + " select ac " + " from AccesoApp ac"
				+ " where ac.usuario.login = ? ";
		list = this.hql(queryAcceso, login);
		if (list.size() == 1) {
			a = list.get(0);
		} else {
			throw new Exception(
					"Error: Este usuario no tiene ningun acceso asignado.");
		}
		return a;
	}

	public Funcionario getFuncionario(Long id) throws Exception {
		List<Funcionario> list = null;
		Funcionario f = new Funcionario();
		String queryFuncionario = "" + " select fu "
				+ " from Funcionario fu join fu.accesos ac "
				+ " where ac in elements(fu.accesos) and ac.id = ? ";
		list = this.hql(queryFuncionario, id);
		if (list.size() == 1) {
			f = list.get(0);
		} else {
			throw new Exception(
					"Error: Mas de un funcionario para el mismo acceso.");
		}
		return f;

	}

	public List<Deposito> getDepositosPorSucursal(Long id) throws Exception {
		List<Deposito> list = null;
		String queryDepositos = "" + " select d from SucursalApp s join s.depositos d"
				+ " where s.id = ? order by d.descripcion";
		list = this.hql(queryDepositos, id);
		return list;

	}
	
	public List<Deposito> getAllDepositos() throws Exception {
		List<Deposito> list = null;
		String query = "select d from Deposito d order by d.orden";
		list = this.hql(query);
		return list;

	}

	public List<ArticuloDeposito> getArticulosPorDeposito(Long id)
			throws Exception {
		List<ArticuloDeposito> list = null;
		String queryArticulos = ""
				+ " select art from ArticuloDeposito art where art.deposito.id = ?";
		list = this.hql(queryArticulos, id);
		return list;

	}

	public ArticuloDeposito getArticuloDepById(long id) throws Exception {
		return (ArticuloDeposito) getObject(
				com.yhaguy.domain.ArticuloDeposito.class.getName(), id);
	}

	/**
	 * Recupera el ArticuloDeposito a partir del id del deposito y del id del
	 * Articulo.. Retorna null si no lo encuentra ó encuentra mas de uno..
	 */
	public ArticuloDeposito recuperarArticuloDep(Long iddeposito,
			Long idarticulo) throws Exception {
		ArticuloDeposito out = null;

		Register rr = Register.getInstance();

		String query = ""
				+ " select ad from ArticuloDeposito ad where  ad.deposito.id = ? and ad.articulo.id = ? ";

		Object[] param = { iddeposito, idarticulo };
		List<ArticuloDeposito> list = rr.hql(query, param);
		if (list.size() == 1) {
			out = list.get(0);
		}
		return out;
	}

	public List<Timbrado> getTimbradosByNumeroFactura(String numero)
			throws Exception {
		List<Timbrado> list = null;
		String queryTimbrado = "select g.timbrado from Gasto g where g.numeroFactura like '%"
				+ numero + "%'";
		list = this.hql(queryTimbrado);
		return list;
	}

	// Gestion de Pagos
	public Recibo getOrdenPagoById(long id) throws Exception {
		return (Recibo) getObject(com.yhaguy.domain.Recibo.class.getName(), id);
	}

	public BancoCheque getChequeDtoByMovimiento(long idMovimiento)
			throws Exception {
		BancoCheque out = new BancoCheque();
		String query = "select b from BancoCheque b where b.movimiento.id = "
				+ idMovimiento;
		List<BancoCheque> list = null;
		list = this.hql(query);
		if (list.size() == 0) {
			return null;
		} else {
			out = list.get(0);
		}
		return out;
	}

	public BancoCheque getChequeDtoByReciboFormaPago(long idRfp)
			throws Exception {
		BancoCheque out = new BancoCheque();
		String query = "select b from BancoCheque b where b.reciboFormaPago.id = "
				+ idRfp;
		List<BancoCheque> list = null;
		list = this.hql(query);
		if (list.size() == 0) {
			return null;
		} else {
			out = list.get(0);
		}
		return out;
	}

	public InvArticulo getInvArticulo(String codigo) throws Exception {
		List l = this.getObjects(InvArticulo.class.getName(), "codigoInterno",
				codigo);
		if (l.size() != 1) {
			// throw new
			// Exception("El resultado para ["+codigo+"] no es único
			// ("+l.size()+")...");
			System.out.println("El resultado para [" + codigo
					+ "] no es único (" + l.size() + ")...");
			return null;
		}
		return (InvArticulo) l.get(0);
	}

	public Long getInvPlanilla(String codV, int lote) throws Exception {
		String query = "select p.id from InvPlanilla p where p.lote=" + lote
				+ " and p.codigoVerificacion='" + codV + "'";
		System.out.println("----------Register query: " + query);
		List<Object> list = null;
		list = this.hql(query);
		if (list.size() == 0) {
			return new Long(-1);
		} else {
			return (Long) list.get(0);
		}
	}

	public String validarLoginInventario(String usuario, String clave)
			throws Exception {
		String query = "select p.nombre from InvPersona p where p.usuario='"
				+ usuario + "' and p.clave='" + clave + "'";

		List<Object> list = null;
		list = this.hql(query);
		if (list.size() == 0) {
			return "";
		} else {
			return ((String) list.get(0)).trim();
		}

	}

	public boolean validarInvAdmin(String clave) throws Exception {
		String query = "select p.clave from InvPersona p where p.usuario='admin'";
		List<Object> list = null;
		list = this.hql(query);
		if (list.size() > 0 && clave.compareTo((String) list.get(0)) == 0) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * @return el ultimo Costo en Guaranies segun el Articulo..
	 */
	public double getUltimoCostoGs(long idArticulo) throws Exception {
		String query = "select a.costoFinalGs from ArticuloCosto a where a.articulo.id = "
				+ idArticulo
				+ " and a.fechaCompra = (select max(ar.fechaCompra) from ArticuloCosto ar"
				+ " where ar.articulo.id = " + idArticulo + ")";
		List<Object> list = this.hql(query);
		if (list.size() > 0) {
			return (double) list.get(0);
		} else {
			return 0;
		}
	}

	// Retorna el ultimo Costo FOB en Dolares segun el Articulo y el Tipo de
	// Movimiento
	public double getUltimoCostoFOBDs(long idArticulo, long idTipoMovimiento)
			throws Exception {
		String query = "select a.costoFinalGs from ArticuloCosto a where a.articulo.id = "
				+ idArticulo
				+ " and a.fechaCompra = (select max(ar.fechaCompra) from ArticuloCosto ar"
				+ " where ar.tipoMovimiento.id = "
				+ idTipoMovimiento
				+ " and ar.articulo.id = " + idArticulo + ")";
		List<Object> list = this.hql(query);
		if (list.size() > 0) {
			return (double) list.get(0);
		} else {
			return 0;
		}
	}

	// verifica si existe la factura de Compra Local en la BD..
	public boolean existeFacturaCompra(long idProveedor, String nroFactura,
			String nroTimbrado) throws Exception {
		String query = "select c from CompraLocalFactura c where c.proveedor.id = "
				+ idProveedor
				+ " and c.numero like '"
				+ nroFactura
				+ "' and c.timbrado.numero like '" + nroTimbrado + "'";
		List<Object> list = this.hql(query);
		if (list.size() == 0) {
			return false;
		} else {
			return true;
		}
	}

	public List<TipoMovimiento> getTipoMovimiento(String descripcion)
			throws Exception {
		List<TipoMovimiento> list = null;
		String queryTipo = "select t from TipoMovimiento t where t.descripcion='"
				+ descripcion + "'";
		list = this.hql(queryTipo);
		return list;
	}

	/**
	 * Retorna una lista de Tipos de Movimiento segun el tipo de Comprobante ej:
	 * compras, gastos, pagos, ventas, cobros, remisiones
	 * 
	 * @param tipoOperacion
	 * @return
	 */
	public List<TipoMovimiento> getTiposDeMovimientoByTipoOperacion(
			IiD tipoOperacion) throws Exception {
		List<TipoMovimiento> list = null;
		long idtc = tipoOperacion.getId();
		String query = "select t from TipoMovimiento t where t.tipoOperacion.id = "
				+ idtc;
		list = this.hql(query);
		return list;
	}

	/**
	 * Retorna el TipoMovimiento segun la sigla
	 * 
	 * @return TipoMovimiento
	 */
	public TipoMovimiento getTipoMovimientoBySigla(String sigla)
			throws Exception {
		TipoMovimiento out = null;
		String query = "select t from TipoMovimiento t where t.sigla = '"
				+ sigla + "'";
		out = (TipoMovimiento) this.hqlToObject(query);
		return out;
	}

	/**
	 * @param codInterno
	 * @return el Articulo a partir del Codigo Interno
	 * @throws Exception
	 */
	public Articulo getArticulo(String codInterno) throws Exception {
		String query = "select a from Articulo a where a.codigoInterno like '"
				+ codInterno + "'";
		List<Articulo> list = null;
		list = this.hql(query);
		if (list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}

	public TipoCambio xgetTipoCambio(long tipoMoneda, long cambioDeQuien)
			throws Exception {
		return null; // getTipoCambio(tipoMoneda, cambioDeQuien, new Date());
	}

	public TipoCambio xxgetTipoCambio(long tipoMoneda, long cambioDeQuien,
			Date fecha) throws Exception {

		TipoCambio out = null;
		List<TipoCambio> list = null;
		String query = "select a from TipoCambio a where a.moneda.id = ? "
				+ " and a.tipoCambio.id = ? and a.fecha <= ? order by a.fecha desc limit 0, 1";

		Object[] param = new Object[3];
		param[0] = tipoMoneda;
		param[1] = cambioDeQuien;
		param[2] = fecha;

		list = this.hql(query, param);
		if (list.size() > 0) {
			out = list.get(0);
		}
		return out;
	}

	/**
	 * 
	 * @param tipoMoneda
	 * @param cambioDeQuien
	 *            : APP o BCP
	 * @param fecha
	 *            : La fecha que buscamos
	 * @return
	 * @throws Exception
	 */
	public List<TipoCambio> getUltimosTipoCambio(long tipoMoneda,
			long cambioDeQuien, Date fecha) throws Exception {

		List<TipoCambio> list = null;
		String query = "select a from TipoCambio a where a.moneda.id = ? "
				+ " and a.tipoCambio.id = ? and a.fecha <= ? order by a.fecha desc limit 0, 10";

		Object[] param = new Object[3];
		param[0] = tipoMoneda;
		param[1] = cambioDeQuien;
		param[2] = fecha;

		list = this.hql(query, param);
		return list;
	}

	/**
	 * Retorna la Cuenta Contable en forma de MyArray a partir del Alias.. pos1:
	 * codigo - pos2: descripcion pos3: cuentaPlanCuenta - pos4: alias
	 */
	public MyArray getCuentaContableByAlias(String alias) throws Exception {

		String query = "select c.id, c.codigo, c.descripcion,"
				+ " c.alias from CuentaContable c where c.alias" + " like '"
				+ alias + "'";

		Object[] cuenta = (Object[]) this.hqlToObject(query);

		MyArray out = new MyArray();
		out.setId((Long) cuenta[0]);
		out.setPos1(cuenta[1]);
		out.setPos2(cuenta[2]);
		out.setPos3(cuenta[3]);

		return out;
	}

	/**
	 * @param alias
	 * @return la CuentaContable segun el alias..
	 */
	public CuentaContable getCuentaContableByAlias_(String alias)
			throws Exception {

		String query = "select c from CuentaContable c where c.alias"
				+ " like '" + alias + "'";

		CuentaContable cuenta = null;

		List<CuentaContable> l = this.hql(query);
		if (l.size() == 1) {
			cuenta = l.get(0);
		}

		return cuenta;
	}

	/**
	 * 
	 * Retorna los usuarios que no estan asociados a un acceso
	 * 
	 */

	public List<Usuario> getAllUsuarios() throws Exception {
		List l = getObjects(com.coreweb.domain.Usuario.class.getName(),
				new Vector(), new Vector());
		return l;
	}

	public List<Object[]> getUsuariosConAcceso() {
		List<Object[]> list = new ArrayList<Object[]>();
		try {
			String query = ""
					+ " select ac.usuario.id, ac.usuario.nombre, ac.usuario.login "
					+ " from AccesoApp ac ";

			list = this.hql(query);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Object[]> getTodosUsuarios() {
		List<Object[]> list = new ArrayList<Object[]>();
		try {
			String query = ""
					+ " select usuario.id, usuario.nombre, usuario.login "
					+ " from Usuario usuario ";

			list = this.hql(query);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public Object getAlertaListaDestino(String descripcion) {
		Object result = "";
		try {
			String query = "" + " select ad.destinos "
					+ " from AlertaDestinos ad "
					+ " where ad.descripcion like '" + descripcion + "'";
			result = (this.hql(query)).get(0);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Retorna un objeto de tipo EmpresaGrupoSociedad a partir de la
	 * descripcion..
	 */
	public EmpresaGrupoSociedad getGrupoEmpresaByDescripcion(String descripcion)
			throws Exception {

		String query = "select e from EmpresaGrupoSociedad e "
				+ "where e.descripcion like '" + descripcion + "'";
		EmpresaGrupoSociedad out = (EmpresaGrupoSociedad) this
				.hqlToObject(query);

		return out;
	}

	/**
	 * Retorna la Razon Social correspondiente al ruc segun la BD del SET
	 */
	public String getRazonSocialSET(String ruc) throws Exception {
		RucSet set = (RucSet) this.getObject(RucSet.class.getName(), "ruc",
				ruc.trim());
		if (set == null) {
			return "";
		}
		return set.getRazonSocial();
	}

	/**
	 * Retorna el ArticuloDeposito a partir del id del Articulo y el id del
	 * Deposito..
	 */
	public ArticuloDeposito getArticuloDeposito(long idArticulo, long idDeposito)
			throws Exception {
		ArticuloDeposito out = null;
		String query = " select art from ArticuloDeposito art where "
				+ " art.deposito.id = " + idDeposito
				+ " and art.articulo.id = " + idArticulo;
		List<ArticuloDeposito> l = this.hql(query);
		if (l.size() == 1) {
			out = l.get(0);
		}
		return out;
	}

	/**
	 * Retorna el Stock disponible a partir del id del Articulo y el id del
	 * Deposito..
	 */
	public long getStockDisponible(long idArticulo, long idDeposito)
			throws Exception {
		long out = 0;
		ArticuloDeposito art = this.getArticuloDeposito(idArticulo, idDeposito);

		if (art != null) {
			out = art.getStock();
		}

		return out;
	}
	
	/**
	 * @return el Stock disponible..
	 */
	public Object[] getStockDisponible_(long idArticulo, long idDeposito) throws Exception {
		String query = "select a.id, a.stock from ArticuloDeposito a where a.articulo.id = " + idArticulo + " and a.deposito.id = " + idDeposito;
		List<Object[]> list = this.hql(query);
		return list.size() > 0 ? list.get(0) : null;
	}

	/**
	 * Retorna una lista de subdiarios segun los parametros de fecha
	 */
	public List<SubDiario> getSubdiariosByFecha(Date desde, Date hasta)
			throws Exception {

		desde = this.misc.toFecha0000(desde);
		hasta = this.misc.toFecha2400(hasta);

		String query = " select sd from SubDiario sd "
				+ " where sd.confirmado = :confirm and sd.fecha between :fdesde and :fhasta "
				+ " order by sd.fecha, sd.numero";

		Hashtable<String, Object> params = new Hashtable<>();
		params.put(":confirm", true);
		params.put(":fdesde", desde);
		params.put(":fhasta", hasta);

		List<SubDiario> out = this.hql(query, params);

		return out;
	}

	/**
	 * Retorna los banco movimientos a partir de numero de cuenta
	 */
	public List<BancoMovimiento> getBancoMovimientosTodos(IiD nroCuenta)
			throws Exception {
		String query = " select bm from BancoMovimiento bm "
				+ " where bm.nroCuenta.id = " + nroCuenta.getId()
				+ " and bm.anulado = 'FALSE'"
				+ " order by bm.fecha";
		List<BancoMovimiento> l = this.hql(query);
		return l;
	}

	/*********************************************************************************
	 * METODOS PARA GESTION DE CtaCteEmpresa
	 * 
	 * @throws Exception
	 *********************************************************************************/

	/**
	 * Obtiene la informacion de Cta Cte de una empresa por su ID
	 * 
	 * @param idEmpresa
	 * @return
	 * @throws Exception
	 */
	public CtaCteEmpresa getCtaCteEmpresaByIdEmpresa(long idEmpresa)
			throws Exception {
		String queryCtaCte = "select e.ctaCteEmpresa from Empresa e"
				+ " where e.id = ?";
		Object o = this.hqlToObject(queryCtaCte, idEmpresa);
		CtaCteEmpresa ctaCte = (CtaCteEmpresa) o;
		return ctaCte;

	}

	/**
	 * Obtiene la linea de credito de una empresa.
	 * 
	 * @param idEmpresa
	 *            Empresa de la que se requiere saber su linea de credito.
	 * @return Linea de Credito de la empresa.
	 * @throws Exception
	 */
	public CtaCteLineaCredito getCtaCteEmpresaLineaCreditoClienteByIdEmpresa(
			long idEmpresa) throws Exception {

		String queryCtaCteLinea = "select e.ctaCteEmpresa.lineaCredito from Empresa e"
				+ " where e.id = ?";
		Object o = this.hqlToObject(queryCtaCteLinea, idEmpresa);
		CtaCteLineaCredito lineaCredito = (CtaCteLineaCredito) o;
		return lineaCredito;

	}

	/**
	 * Obtiene la linea de credito de una empresa por su descripcion.
	 * 
	 * @param descripcion
	 * @return Linea de Credito
	 * @throws Exception
	 */
	public CtaCteLineaCredito getCtaCteEmpresaLineaCreditoByDescripcion(
			String descripcion) throws Exception {
		String queryCtaCteLinea = "select linea from CtaCteLineaCredito linea where linea.descripcion like '"
				+ descripcion + "'";
		Object o = this.hqlToObject(queryCtaCteLinea);
		CtaCteLineaCredito lineaCredito = (CtaCteLineaCredito) o;
		return lineaCredito;
	}

	public CtaCteLineaCredito getCtaCteEmpresaLineaCreditoByMontoLinea(
			double monto) throws Exception {
		String query = "select linea from CtaCteLineaCredito linea where linea.linea = ?";
		Object o = this.hqlToObject(query, monto);
		CtaCteLineaCredito linea = (CtaCteLineaCredito) o;
		return linea;
	}

	/**
	 * Obtiene el estado de la CtaCte en caracter de proveedor de la
	 * empresa(Activo/Inactivo/Bloqueado/Sin Cta. Cte.)
	 * 
	 * @param idEmpresa
	 *            Id de la empresa de la cual se quiere conocer el estado de su
	 *            CtaCte.
	 * @return El estado de la CtaCte
	 * @throws Exception
	 */
	public Tipo getCtaCteEmpresaEstadoProveedorByIdEmpresa(long idEmpresa)
			throws Exception {

		String queryCtaCteEstado = "select e.ctaCteEmpresa.estadoComoProveedor from Empresa e"
				+ " where e.id = ?";
		Object o = this.hqlToObject(queryCtaCteEstado, idEmpresa);
		Tipo estado = (Tipo) o;

		return estado;

	}

	/**
	 * Obtiene el estado de la CtaCte en caracter de proveedor de la
	 * empresa(Activo/Inactivo/Bloqueado/Sin Cta. Cte..)
	 * 
	 * @param idEmpresa
	 *            Id de la empresa de la cual se quiere conocer el estado de su
	 *            CtaCte.
	 * @return El estado de la CtaCte
	 * @throws Exception
	 */
	public Tipo getCtaCteEmpresaEstadoClienteByIdEmpresa(long idEmpresa)
			throws Exception {

		String queryCtaCteEstado = "select e.ctaCteEmpresa.estadoComoCliente from Empresa e"
				+ " where e.id = ?";
		Object o = this.hqlToObject(queryCtaCteEstado, idEmpresa);
		Tipo estado = (Tipo) o;

		return estado;

	}

	/**
	 * Devuelve una estructura con los saldos de los movimientos de la
	 * CtaCteEmpresa.
	 * 
	 * @param empresa
	 *            Empresa de la cual se requiere conocer el estado de sus
	 *            saldos.
	 * @param caracterMovimientos
	 *            Caracter de los movimientos de la empresa (Como cliente o
	 *            proveedor)
	 * @return Devuelve una List<Object[]> con los siguientes valores: pos 0 =
	 *         id del movimiento pos 1 = saldo del movimiento pos 2 = moneda del
	 *         movimiento
	 * @throws Exception
	 */
	public List<Object[]> getCtaCteEmpresaSaldos(IiD empresa,
			Tipo caracterMovimientos) throws Exception {
		String queryMovimientos = "select m.id, m.saldo, m.moneda from CtaCteEmpresaMovimiento m"
				+ " where m.idEmpresa = ? and m.tipoCaracterMovimiento.id = ?  order by m.fechaEmision asc";

		Object[] parametros = new Object[2];
		parametros[0] = empresa.getId();
		parametros[1] = caracterMovimientos.getId();

		List<Object[]> lista = new ArrayList<Object[]>();
		lista = this.hql(queryMovimientos, parametros);
		return lista;
	}

	/**
	 * Obtiene el movimiento de una CtaCteEmpresa de acuerdo a su ID
	 * 
	 * @param id
	 *            Id del movimiento que se quiere recuperar
	 * @return Retorna el movimiento.
	 */
	public CtaCteEmpresaMovimiento getCtaCteEmpresaMovimientoById(long id)
			throws Exception {

		return (CtaCteEmpresaMovimiento) getObject(
				com.yhaguy.domain.CtaCteEmpresaMovimiento.class.getName(), id);
	}

	/**
	 * @return los movimientos con saldo..
	 */
	public List<CtaCteEmpresaMovimiento> getMovimientosPendientes(IiD empresa, Tipo caracter, long idMoneda) throws Exception {

		String query = "select m from CtaCteEmpresaMovimiento m where m.idEmpresa = ? and  m.saldo != 0 and m.moneda.id = " + idMoneda;
		String orderQuery = " order by m.fechaEmision";

		List<Object> listaParametros = new ArrayList<Object>();
		listaParametros.add(empresa.getId());

		List<Object> lista = new ArrayList<Object>();
		List<CtaCteEmpresaMovimiento> list = new ArrayList<CtaCteEmpresaMovimiento>();
		String whereQuery = "";

		if (caracter.getSigla().compareTo(
				Configuracion.SIGLA_CTA_CTE_CARACTER_MOV_CLIENTE) == 0
				|| caracter.getSigla().compareTo(
						Configuracion.SIGLA_CTA_CTE_CARACTER_MOV_PROVEEDOR) == 0) {
			whereQuery += " and m.tipoCaracterMovimiento.id = ? ";
			listaParametros.add(caracter.getId());
		}

		query += whereQuery;
		query += orderQuery;

		Object[] parametros = new Object[listaParametros.size()];
		for (int i = 0; i < listaParametros.size(); i++) {
			parametros[i] = listaParametros.get(i);
		}

		lista = this.hql(query, parametros);
		for (Object o : lista) {
			list.add((CtaCteEmpresaMovimiento) o);
		}
		return list;
	}

	/**
	 * Obtiene los movimientos correspondientes solo a Facturas contado o
	 * Credito de una empresa.
	 * 
	 * @param empresa
	 *            Empresa de la cual se quiere obtener los movimientos
	 * @param caracterMovimiento
	 *            El caracter del movimiento de las operaciones de la empresa
	 * @return Lista con los movimientos correspondientes a facturas al contado
	 *         o a credito de la empresa de acuerdo al caracter seleccionado
	 * @throws Exception
	 */
	public List<CtaCteEmpresaMovimiento> getMovimientosFacturas(IiD empresa,
			Tipo caracterMovimiento) throws Exception {
		String queryMovimientos = "select m from CtaCteEmpresaMovimiento m  where m.idEmpresa = ? and (m.tipoMovimiento.tipoDocumento.sigla like '"
				+ Configuracion.SIGLA_DOC_FAC_CONTADO
				+ "' or m.tipoMovimiento.tipoDocumento.sigla like '"
				+ Configuracion.SIGLA_DOC_FAC_CREDITO + "') ";
		String orderQuery = " order by m.fechaEmision asc";

		List<Object> listaParametros = new ArrayList<Object>();
		listaParametros.add(empresa.getId());

		List<Object> lista = new ArrayList<Object>();
		List<CtaCteEmpresaMovimiento> listaMovimientosFacturas = new ArrayList<CtaCteEmpresaMovimiento>();
		String whereQuery = "";

		if (caracterMovimiento.getSigla().compareTo(
				Configuracion.SIGLA_CTA_CTE_CARACTER_MOV_CLIENTE) == 0
				|| caracterMovimiento.getSigla().compareTo(
						Configuracion.SIGLA_CTA_CTE_CARACTER_MOV_PROVEEDOR) == 0) {
			whereQuery += " and m.tipoCaracterMovimiento.id = ? ";
			listaParametros.add(caracterMovimiento.getId());
		}

		queryMovimientos += whereQuery;
		queryMovimientos += orderQuery;

		Object[] parametros = new Object[listaParametros.size()];
		for (int i = 0; i < listaParametros.size(); i++) {
			parametros[i] = listaParametros.get(i);
		}

		lista = this.hql(queryMovimientos, parametros);
		for (Object o : lista) {
			listaMovimientosFacturas.add((CtaCteEmpresaMovimiento) o);
		}
		return listaMovimientosFacturas;
	}

	/**
	 * 
	 * Devuelve los movimientos de la CtaCte de una empresa de acuerdo a los
	 * parametros. Ejemplo de uso: getCtaCteEmpresaMovimientos(empresa,
	 * Configuracion.CTA_CTE_TIPO_CATEGORIA_MOV_PROVEEDOR, null, fechaHasta,
	 * true) esto devolvera todos los movimientos pendientes de "empresa" como
	 * proveedor desde el inicio de sus operaciones hasta la fecha del
	 * parametro. Para devolver todos los movimientos usar null para fechaDesde
	 * y fechaHasta.
	 * 
	 * @param idEmpresa
	 *            Empresa de la cual se quiere recuperar sus movimientos.
	 * @param caracterMovimientos
	 *            Para seleccionar los movimientos (Todos, como Cliente o como
	 *            Proveedor).
	 * @param fechaDesde
	 *            Rango para la busqueda desde esa fecha, si es null busca desde
	 *            el "Inicio de los tiempos :)".
	 * @param fechaHasta
	 *            Rango para la busqueda hasta esa fecha, si es null busca desde
	 *            la fecha de inicio hasta el "Final de los tiempos :)" Si tanto
	 *            la fecha de inicio como la fecha de fin son nulos busca todos
	 *            los movimientos.
	 * @param pendiente
	 *            true = solo movimientos pendientes false = todos los
	 *            movimientos.
	 * @param vencidos
	 *            true = todos los movimientos vencidos
	 * @return Las posiciones son: id = id del movimiento pos1 =
	 *         idMovimientoOriginal pos2 = nroComprobante pos3 = fechaEmision
	 *         pos4 = m.fechaVencimiento pos5 = importeOriginal pos6 = saldo
	 *         pos7 = moneda pos8 = tipoMovimiento pos9 = tipoCaracterMovimiento
	 *         pos10 = usuarioMod pos11 = sucursal
	 * 
	 */
	public List<Object[]> getCtaCteEmpresaMovimientos(IiD empresa,
			Tipo caracterMovimiento, Tipo sucursal, Date fechaDesde,
			Date fechaHasta, boolean pendientes, boolean vencidos)
			throws Exception {
		String queryMovimientos = "select m.id, m.idMovimientoOriginal, m.nroComprobante,"
				+ " m.fechaEmision, m.fechaVencimiento, m.importeOriginal, m.saldo, m.moneda, "
				+ " m.tipoMovimiento.descripcion, m.tipoCaracterMovimiento.sigla,"
				+ " m.usuarioMod, m.sucursal.descripcion from CtaCteEmpresaMovimiento m"
				+ " where m.idEmpresa = ?";
		String orderQuery = " order by m.fechaEmision asc, m.nroComprobante asc, m.fechaVencimiento asc";

		List<Object> listaParametros = new ArrayList<Object>();
		listaParametros.add(empresa.getId());
		List<Object[]> lista = new ArrayList<Object[]>();
		String whereQuery = "";

		/*
		 * Pueden ocurrir cuatro casos para ello son estos if's Caso 1:
		 * (fechaDesde == null && fechaHasta == null) en ese caso se desean
		 * devolver todos los movimientos sin importar la fecha para ello se
		 * utilizan los valores por defecto de "desde" y "hasta". Caso 2:
		 * (fechaDesde == null && fechaHasta != null) en ese caso se desea
		 * devolver todos los movimientos entre el inicio de las operaciones
		 * hasta la "fechaHasta" Caso 3: (fechaDesde != null && fechaHasta ==
		 * null) en ese caso se desea devolver todos los movimientos entre la
		 * "fechaDesde" hasta los movimientos mas recientes. Caso 4: (fechaDesde
		 * != null && fechaHasta != null) en ese caso se desea devolver todos
		 * los movimientos entre "fechaDesde" y "fechaHasta"
		 */

		if (sucursal.getSigla().compareTo(
				Configuracion.SIGLA_SUCURSAL_APP_TODAS) != 0) {
			whereQuery += " and m.sucursal.id = ? ";
			listaParametros.add(sucursal.getId());
		}

		/*
		 * Usar siempre como parametro CTA_CTE_TIPO_CARACTER_MOV de la clase
		 * configuracion
		 */
		if (caracterMovimiento.getSigla().compareTo(
				Configuracion.SIGLA_CTA_CTE_CARACTER_MOV_CLIENTE) == 0
				|| caracterMovimiento.getSigla().compareTo(
						Configuracion.SIGLA_CTA_CTE_CARACTER_MOV_PROVEEDOR) == 0) {
			whereQuery += " and m.tipoCaracterMovimiento.id = ? ";
			listaParametros.add(caracterMovimiento.getId());
		}

		/*
		 * Ocurrencia del caso 2 explicado mas arriba Para devolver todos los
		 * movimientos entre el inicio de las operaciones hasta la "fechaHasta".
		 */
		if (fechaDesde == null && !(fechaHasta == null)) {
			whereQuery += " and m.fechaEmision  <= ? ";
			listaParametros.add(fechaHasta);
		}

		/*
		 * Ocurrencia del caso 3 explicado mas arriba Para devolver todos los
		 * movimientos entre la "fechaDesde" hasta los movimientos mas
		 * recientes.
		 */
		if (!(fechaDesde == null) && fechaHasta == null) {
			whereQuery += " and m.fechaEmision  >= ? ";
			listaParametros.add(fechaDesde);
		}

		/*
		 * Caso 4: Para devolver todos los movimientos entre "fechaDesde" y
		 * "fechaHasta"
		 */
		if ((!(fechaDesde == null) && !(fechaHasta == null))
				&& (fechaDesde.compareTo(fechaHasta) <= 0)) {
			whereQuery += " and m.fechaEmision between ? and ? ";
			listaParametros.add(fechaDesde);
			listaParametros.add(fechaHasta);
		}

		/*
		 * TRUE si se desean ver los movimientos pendientes FALSE si se desean
		 * ver todos los movimientos
		 */
		if (pendientes) {
			whereQuery += " and ( m.saldo > " + 0.001 + " or m.saldo < "
					+ -0.001 + " )";
		}

		/*
		 * TRUE si se desean ver los movimientos vencidos
		 */
		if (vencidos) {
			Date fechaHoy = this.misc.getFechaHoy00();

			if (!pendientes)
				whereQuery += " and ( m.saldo > " + 0.001 + " or m.saldo < "
						+ -0.001 + " )" + " and m.fechaVencimiento < ?";
			else
				whereQuery += " and m.fechaVencimiento < ?";

			listaParametros.add(fechaHoy);
		}

		whereQuery += " and m.anulado is false";
		queryMovimientos += whereQuery;
		queryMovimientos += orderQuery;
		Object[] parametros = new Object[listaParametros.size()];
		for (int i = 0; i < listaParametros.size(); i++) {
			parametros[i] = listaParametros.get(i);
		}

		lista = this.hql(queryMovimientos, parametros);
		return lista;
	}

	/**
	 * Devuelve una lista de los cobros hechos al cliente o anticipos hechos por
	 * el mismo con saldo a favor de dicho cliente. Los cobros con pendiente a
	 * favor tienen saldo de valor negativo. Ej.: -200. Es un cobro con saldo a
	 * favor de 200.
	 * 
	 * @param idEmpresa
	 *            El id de la empresa de la cual se quiere saber sus cobros o
	 *            anticipos con saldo a favor.
	 * @return Lista de los movimientos con saldo a favor del cliente.
	 */
	public List<CtaCteEmpresaMovimiento> getMovimientosCobroConSaldoFavor(
			long idEmpresa) throws Exception {
		String query = " select m from CtaCteEmpresaMovimiento m "
				+ " where m.idEmpresa = ? "
				+ " and (m.tipoMovimiento.sigla like '"
				+ Configuracion.SIGLA_TM_RECIBO_COBRO + "' "
				+ " or  m.tipoMovimiento.sigla like '"
				+ Configuracion.SIGLA_TM_ANTICIPO_COBRO + "'  )"
				+ " and m.saldo < " + -0.001
				+ " order by m.fechaEmision asc limit 0, 1";
		List<Object> lista = new ArrayList<Object>();
		List<CtaCteEmpresaMovimiento> listaMovimientosCobro = new ArrayList<CtaCteEmpresaMovimiento>();
		lista = this.hql(query, idEmpresa);
		for (Object o : lista) {
			listaMovimientosCobro.add((CtaCteEmpresaMovimiento) o);
		}
		return listaMovimientosCobro;
	}

	public List<CtaCteEmpresaMovimiento> getMovimientosPagoConSaldoFavor(
			long idEmpresa) throws Exception {
		String query = " select m from CtaCteEmpresaMovimiento m "
				+ " where m.idEmpresa = ? "
				+ " and (m.tipoMovimiento.sigla like '"
				+ Configuracion.SIGLA_TM_ANTICIPO_PAGO + "' "
				+ " or  m.tipoMovimiento.sigla like '"
				+ Configuracion.SIGLA_TM_RECIBO_PAGO + "'  )"
				+ " and m.saldo < " + -0.001
				+ " order by m.fechaEmision asc limit 0, 1";
		List<Object> lista = new ArrayList<Object>();
		List<CtaCteEmpresaMovimiento> listaMovimientosCobro = new ArrayList<CtaCteEmpresaMovimiento>();
		lista = this.hql(query, idEmpresa);
		for (Object o : lista) {
			listaMovimientosCobro.add((CtaCteEmpresaMovimiento) o);
		}
		return listaMovimientosCobro;
	}

	/**
	 * Devuelve el movimiento de la Cta Cte de acuerdo a los parametros. Obs.:
	 * Retorna una lista ya que algunos movimientos como por ejemplo las ventas
	 * a credito se dividen de acuerdo a su vencimiento pero siguen representado
	 * un mismo movimiento.
	 * 
	 * @param idMovimientoOriginal
	 * @param iDtipoMovimiento
	 * @return
	 * @throws Exception
	 */
	public List<CtaCteEmpresaMovimiento> getCtaCteEmpresaMovimientoPorMovimientoOriginal(
			long idMovimientoOriginal, long iDtipoMovimiento) throws Exception {
		String query = "select m from CtaCteEmpresaMovimiento m "
				+ " where m.idMovimientoOriginal = ? "
				+ " and m.tipoMovimiento.id = ? order by m.fechaEmision asc";
		Object[] parametros = new Object[2];
		parametros[0] = idMovimientoOriginal;
		parametros[1] = iDtipoMovimiento;
		List<Object> lista = this.hql(query, parametros);
		List<CtaCteEmpresaMovimiento> movimientos = new ArrayList<CtaCteEmpresaMovimiento>();
		for (Object o : lista) {
			movimientos.add((CtaCteEmpresaMovimiento) o);
		}
		return movimientos;
	}

	/**
	 * Devuelve los movimientos relacionados con una imputacion
	 * 
	 * @param imputacion
	 *            La imputacion que se desea revertir
	 * @return La lista de los movimientos relacionados con la imputacion
	 * 
	 */
	public List<CtaCteEmpresaMovimiento> getCtaCteEmpresaMovimientosPorImputacion(
			IiD imputacion) throws Exception {

		String query = "select m from CtaCteEmpresaMovimiento m  join m.imputaciones i where i in elements(m.imputaciones) and i.id = ?";
		List<Object> lista = new ArrayList<Object>();
		lista = this.hql(query, imputacion.getId());
		List<CtaCteEmpresaMovimiento> listaMovimientos = new ArrayList<CtaCteEmpresaMovimiento>();

		for (Object o : lista) {
			listaMovimientos.add((CtaCteEmpresaMovimiento) o);
		}
		return listaMovimientos;
	}

	/**
	 * Retorna los Presupuestos con estado 'Solo Presupuesto' de la sucursal y
	 * el deposito que recibe como parametro..
	 * 
	 * @return List<Object[]> [0]: id - [1]: fecha - [2]: numero [3]:
	 *         razonSocial Cliente [4]: atendido nombre - [5]: vendedor nombre -
	 *         [6] deposito [7]: sucursal
	 */
	public List<Object[]> getPresupuestosPendientes(IiD sucursal, IiD deposito)
			throws Exception {
		String sigla = Configuracion.SIGLA_VENTA_ESTADO_SOLO_PRESUPUESTO;
		long idSuc = sucursal.getId();
		long idDep = deposito.getId();

		String query = "select v.id, v.fecha, v.numero, v.cliente.empresa.razonSocial,"
				+ " v.atendido.empresa.razonSocial, v.vendedor.empresa.razonSocial,"
				+ " v.deposito.descripcion, v.sucursal.descripcion"
				+ " from Venta v"
				+ " where v.estado.sigla like '"
				+ sigla
				+ "'"
				+ " and v.sucursal.id = "
				+ idSuc
				+ " and v.deposito.id = " + idDep;
		List<Object[]> list = this.hql(query);
		return list;
	}

	/**
	 * Obtiene el estado de cuenta de las empresas que posean movimientos en su
	 * Cta Cte
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<CtaCteVisCliente> getListadoClienteCuentasX() throws Exception {
		String query = "select c from CtaCteVisCliente c";
		List<Object> lista = this.hql(query);
		List<CtaCteVisCliente> cuentasClientes = new ArrayList<CtaCteVisCliente>();
		for (Object o : lista) {
			cuentasClientes.add((CtaCteVisCliente) o);
		}
		return cuentasClientes;
	}

	/**
	 * Obtiene listado de cuentas de cliente con la informacion de los
	 * pendientes por moneda.
	 * 
	 * Obs.: 1- El listado contiene la informacion de las empresas que tienen
	 * asignadas una Cta. Cte y cuyos estados como cliente en la Cta. Cte. sean
	 * distientos de NULL.
	 * 
	 * 2- El listado proporciona los pendientes por moneda.
	 * 
	 * 3- Solo se incluye en el listado las monedas con la que la empresa opero
	 * en sus movimientos.
	 * 
	 * @return
	 * @throws Exception
	 */
	public Map<Long, List<CtaCteVisCliente>> getListadoClienteCuentas()
			throws Exception {

		String query = "select c from CtaCteVisCliente c";
		query += " order by c.razonSocial asc";
		List<Object> lista = this.hql(query);
		Map<Long, List<CtaCteVisCliente>> cuentasClientes = new HashMap<Long, List<CtaCteVisCliente>>();

		for (Object o : lista) {

			CtaCteVisCliente cta = (CtaCteVisCliente) o;

			if (cuentasClientes.get(cta.getIdEmpresa()) == null) {

				cuentasClientes.put(cta.getIdEmpresa(), new ArrayList());
				cuentasClientes.get(cta.getIdEmpresa()).add(cta);

			} else {

				cuentasClientes.get(cta.getIdEmpresa()).add(cta);

			}
		}
		return cuentasClientes;
	}

	/**
	 * Obtiene el estado de cuenta de las empresas que posean movimientos en su
	 * Cta Cte
	 * 
	 * @return
	 * @throws Exception
	 */
	public Map<Long, List<CtaCteVisProveedor>> getListadoProveedorCuentas()
			throws Exception {

		String query = "select c from CtaCteVisProveedor c";
		List<Object> lista = this.hql(query);
		Map<Long, List<CtaCteVisProveedor>> cuentasProveedores = new HashMap<Long, List<CtaCteVisProveedor>>();
		for (Object o : lista) {
			CtaCteVisProveedor cta = (CtaCteVisProveedor) o;

			if (cuentasProveedores.get(cta.getIdEmpresa()) == null) {

				cuentasProveedores.put(cta.getIdEmpresa(), new ArrayList());
				cuentasProveedores.get(cta.getIdEmpresa()).add(cta);

			} else {

				cuentasProveedores.get(cta.getIdEmpresa()).add(cta);

			}
		}
		return cuentasProveedores;
	}

	/**
	 * Retorna la lista de cobros un cliente especifico y una fecha de cobro
	 * 
	 * @param empresa
	 * @param fechaCobro
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public List<CtaCteEmpresaMovimiento> getCobrosByCliente(IiD empresa,
			Date fechaCobro) throws Exception {

		String query = "selec m from CtaCteEmpresaMovimiento m where m.idEmpresa = ? and and m.fechaEmision between ? and ? and m.tipoMovimiento.sigla = "
				+ Configuracion.SIGLA_TM_COBRO;
		List<Object> lista = new ArrayList<Object>();
		Object[] parametros = new Object[2];

		Date fechaCobroDesde = fechaCobro;
		fechaCobroDesde.setHours(00);
		fechaCobroDesde.setMinutes(00);
		fechaCobroDesde.setSeconds(00);

		Date fechaCobroHasta = fechaCobro;
		fechaCobroHasta.setHours(53);
		fechaCobroHasta.setMinutes(59);
		fechaCobroHasta.setSeconds(59);

		parametros[0] = empresa.getId();
		parametros[1] = fechaCobroDesde;
		parametros[2] = fechaCobroHasta;

		lista = this.hql(query, parametros);
		List<CtaCteEmpresaMovimiento> movimientos = new ArrayList<CtaCteEmpresaMovimiento>();
		for (Object o : lista) {
			movimientos.add((CtaCteEmpresaMovimiento) o);
		}
		return movimientos;

	}

	/**
	 * Retorna la lista de pagos un proveedor especifico y una fecha de pago
	 * 
	 * @param empresa
	 * @param fechaCobro
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public List<CtaCteEmpresaMovimiento> getPagosByProveedor(IiD empresa,
			Date fechaPago) throws Exception {

		String query = "selec m from CtaCteEmpresaMovimiento m where m.idEmpresa = ? and and m.fechaEmision between ? and ? and m.tipoMovimiento.sigla = "
				+ Configuracion.SIGLA_TM_PAGO;
		List<Object> lista = new ArrayList<Object>();
		Object[] parametros = new Object[2];

		Date fechaPagoDesde = fechaPago;
		fechaPagoDesde.setHours(00);
		fechaPagoDesde.setMinutes(00);
		fechaPagoDesde.setSeconds(00);

		Date fechaPagoHasta = fechaPago;
		fechaPagoHasta.setHours(53);
		fechaPagoHasta.setMinutes(59);
		fechaPagoHasta.setSeconds(59);

		parametros[0] = empresa.getId();
		parametros[1] = fechaPagoDesde;
		parametros[2] = fechaPagoHasta;

		lista = this.hql(query, parametros);
		List<CtaCteEmpresaMovimiento> movimientos = new ArrayList<CtaCteEmpresaMovimiento>();
		for (Object o : lista) {
			movimientos.add((CtaCteEmpresaMovimiento) o);
		}
		return movimientos;

	}

	/*************************************** CtaCteFin ************************************************/

	/**
	 * Retorna el Articulo Presentacion por Defecto
	 * 
	 * @throws Exception
	 */
	public ArticuloPresentacion getArticuloPresentacionDefault()
			throws Exception {
		String desc = Configuracion.ID_ARTICULO_PRESENTACION_DEFAULT;
		String query = "select a from ArticuloPresentacion a"
				+ " where a.descripcion like '" + desc + "'";
		ArticuloPresentacion out = (ArticuloPresentacion) this
				.hqlToObject(query);
		return out;
	}

	/**
	 * Retorna el Plan de Cuenta a partir del codigo..
	 */
	public PlanDeCuenta getPlanDeCuentaByCodigo(String codigo) throws Exception {
		String query = "select p from PlanDeCuenta p " + " where p.codigo = '"
				+ codigo + "'";
		PlanDeCuenta out = (PlanDeCuenta) this.hqlToObject(query);
		return out;
	}

	/**
	 * Retorna la lista de items de una factura de Compra..
	 */
	public List<CompraLocalFacturaDetalle> getDetallesCompra(long idCompra)
			throws Exception {
		String query = "select c.detalles from CompraLocalFactura c where c.id = "
				+ idCompra;
		List<CompraLocalFacturaDetalle> out = this.hql(query);
		return out;
	}

	/**
	 * Retorna la lista de items de una factura de Venta..
	 */
	public List<VentaDetalle> getDetallesVenta(long idVenta) throws Exception {
		String query = "select v.detalles from Venta v where v.id = " + idVenta;
		List<VentaDetalle> out = this.hql(query);
		return out;
	}

	/**
	 * Retorna un movimiento de tipo Venta null si no lo encuentra..
	 */
	public Venta getMovimientoVenta(long idTipoMovimiento, long idMovimiento)
			throws Exception {
		Venta out = null;
		String query = "select v from Venta v where v.tipoMovimiento.id = "
				+ idTipoMovimiento + " and v.id = " + idMovimiento;
		List<Venta> list = this.hql(query);
		if (list.size() == 1) {
			return list.get(0);
		}
		return out;
	}

	/**
	 * Retorna una lista de Articulos Gatos
	 * 
	 * @return
	 * @throws Exception
	 */

	public List<ArticuloGasto> getArticulosGastosRG() throws Exception {
		String query = "select ag from ArticuloGasto ag";
		List<ArticuloGasto> out = this.hql(query);
		return out;
	}

	/**
	 * Retorna una lista de tipos de cambios 0 si no las hay
	 * 
	 * @param idMoneda
	 * @return
	 * @throws Exception
	 */

	public List<TipoCambio> getListaTipoDeCambioByMoneda(long idMoneda)
			throws Exception {
		String query = "select tc from TipoCambio tc where tc.moneda.id = "
				+ idMoneda; // ver
							// atributos
							// de
							// moneda
		List<TipoCambio> out = this.hql(query);
		return out;
	}

	/*
	 * retorna tipo según id
	 */
	public Tipo getTipoById(long id) throws Exception {
		String query = "select t from Tipo t where t.id=" + id;
		List<Tipo> list = this.hql(query);
		if (list.size() == 1) {
			return list.get(0);
		}
		return null;
	}

	/*
	 * Persiste tipo de cambio si no existe; actualiza en caso contrario.
	 */

	public void saveTipoCambio(TipoCambio tipoCambio) throws Exception {

		RegisterDomain rr = RegisterDomain.getInstance();
		String query = "select tc from TipoCambio tc where tc.fechaString = '"
				+ tipoCambio.getFechaString() + "' and tc.moneda.id = "
				+ tipoCambio.getMoneda().getId() + " and tc.tipoCambio.id = "
				+ tipoCambio.getTipoCambio().getId();
		List<TipoCambio> list = this.hql(query);
		if (list.size() == 0) {
			rr.saveObject(tipoCambio, null);
		} else {
			for (TipoCambio tc : list) {
				tc.setCompra(tipoCambio.getCompra());
				tc.setVenta(tipoCambio.getVenta());
				tc.setFecha(tipoCambio.getFecha());
				rr.saveObject(tc, null);
			}
		}

	}

	/**
	 * persiste el nuevo articulo gasto
	 * 
	 * @throws Exception
	 * 
	 */

	public void saveArticuloGastoRG(ArticuloGasto articuloGastoRG, String user)
			throws Exception {
		RegisterDomain rr = RegisterDomain.getInstance();
		rr.saveObject(articuloGastoRG, user);
	}

	/**
	 * Retorna la lista completa de Cuentas Contables..
	 */
	public List<CuentaContable> getCuentasContables() throws Exception {
		String query = "select c from CuentaContable c";
		List<CuentaContable> list = this.hql(query);
		return list;
	}

	/**
	 * @return el Plan de Cuentas..
	 * @throws Exception
	 */
	public List<PlanDeCuenta> getPlanDeCuentas() throws Exception {
		String query = "select p from PlanDeCuenta p order by p.codigo";
		List<PlanDeCuenta> list = this.hql(query);
		return list;
	}
	
	/**
	 * @return el Plan de Cuentas..
	 * @throws Exception
	 */
	public List<PlanCuenta> getPlanCuentas() throws Exception {
		String query = "select p from PlanCuenta p order by p.nro, p.codigo";
		List<PlanCuenta> list = this.hql(query);
		return list;
	}

	/**
	 * @return el Plan de Cuentas Para reporte..
	 * @throws Exception
	 */
	public List<Object[]> getPlanDeCuentasReporte() throws Exception {

		String query = "Select pc.codigo, pc.descripcion, t.descripcion"
				+ " from PlanDeCuenta pc inner join pc.tipoCuenta t ";
		List<Object[]> obj = this.hql(query);
		return obj;
	}

	/**
	 * @return los Centros de Costos..
	 * @throws Exception
	 */
	public List<CentroCosto> getCentrosDeCosto() throws Exception {
		String query = "select c from CentroCosto c";
		List<CentroCosto> list = this.hql(query);
		return list;
	}

	/**
	 * @param idDepartamento
	 * @return los centros de costo que no estan asignados al departamento..
	 */
	public List<CentroCosto> getCentrosDeCostoNotIn(long idDepartamento)
			throws Exception {
		String query = "select c from CentroCosto c where c not in "
				+ "(select c from DepartamentoApp as d inner join d.centroCostos as c"
				+ " where d.id = " + idDepartamento + ")";
		List<CentroCosto> list = this.hql(query);
		return list;
	}

	/**
	 * @param idDepartamento
	 * @return las cuentas que no estan asignadas al departamento..
	 */
	public List<CuentaContable> getCuentasContablesNotIn(long idDepartamento)
			throws Exception {
		String query = "select c from CuentaContable c where c not in "
				+ "(select c from DepartamentoApp as d inner join d.cuentas as c"
				+ " where d.id = " + idDepartamento + ")";
		List<CuentaContable> list = this.hql(query);
		return list;

	}

	/**
	 * Este metodo actualiza el atributo "depositado" de BancoChequeTercero de
	 * la lista IiD.
	 */
	public void updateChequesDepositados(List<IiD> cheques, String numeroDeposito, Date fechaDeposito) throws Exception {

		if (cheques.size() == 0) {
			return;
		}

		String query = "select c from BancoChequeTercero c where";

		// Para armar el query
		for (int i = 0; i < cheques.size(); i++) {
			IiD d = cheques.get(i);
			query += " c.id = " + d.getId();

			if (i < (cheques.size() - 1)) {
				query += " or ";
			}

		}

		List<BancoChequeTercero> list = this.hql(query);

		// Para asignar que ya esta depositado y guardar
		for (BancoChequeTercero cheque : list) {
			cheque.setDepositado(true);
			cheque.setNumeroDeposito(numeroDeposito);
			cheque.setFechaDeposito(fechaDeposito);
			this.saveObject(cheque, "Sis");
		}

	}

	public void updateChequesDescontados(List<IiD> cheques, String numeroDescuento, Date fechaDescuento, boolean prestamo) throws Exception {

		if (cheques.size() == 0) {
			return;
		}

		String query = "select c from BancoChequeTercero c where";

		// Para armar el query
		for (int i = 0; i < cheques.size(); i++) {
			IiD d = cheques.get(i);
			query += " c.id = " + d.getId();

			if (i < (cheques.size() - 1)) {
				query += " or ";
			}

		}

		List<BancoChequeTercero> list = this.hql(query);

		// Para asignar que ya esta depositado y guardar
		for (BancoChequeTercero cheque : list) {
			cheque.setDescontado(true);
			cheque.setNumeroDescuento(numeroDescuento);
			cheque.setFechaDescuento(fechaDescuento);
			if (prestamo) cheque.setObservacion(BancoChequeTercero.PRESTAMO_CC);
			this.saveObject(cheque, "sys");
		}

	}

	/**
	 * Según la lista de cajas principal, recupera las cajas periodos que NO
	 * están procesadas, es decir, que no hay una caja de tesorería que esté
	 * apuntando a ellas.
	 * 
	 * @param idCajasPrincipal
	 * @param estadoProcesado
	 * @return
	 * @throws Exception
	 */
	public List<CajaPeriodo> getCajaPeriodoSinProcesar(
			Collection<IiD> idCajasPrincipal, IiD estadoProcesado)
			throws Exception {
		ArrayList<CajaPeriodo> out = new ArrayList<>();

		// generar el where
		// generar el falso para que dependa del los valores de los id de caja
		String where = " 1 = 2 ";
		for (Iterator iterator = idCajasPrincipal.iterator(); iterator
				.hasNext();) {
			IiD iid = (IiD) iterator.next();
			where += " or   cp.caja.id = " + iid.getId();
		}
		where = "(" + where + ")";

		// filtrar las cajas periodos de esas principal, que no están procesadas
		String q = "" + "select cp " + "from CajaPeriodo cp "
				+ "where cp.estado.id != " + estadoProcesado.getId() + " and "
				+ where;

		// Hashtable<String, Object> params = new Hashtable<>();
		// params.put(":vFalso", false);
		// out = (ArrayList<CajaPeriodo>) this.hql(q, params);

		out = (ArrayList<CajaPeriodo>) this.hql(q);
		return out;
	}

	/**
	 * Para la migracion desde el Carlos Rivas. Obtiene la empresa de acuerdo al
	 * codigo de cliente en formato -CL00-
	 * 
	 * @param codigo
	 * @return
	 * @throws Exception
	 */
	public IiD getEmpresaByCodigoCliente(String codigoCliente) throws Exception {
		String query = "select c from Cliente c where c.empresa.codigoEmpresa like '%"
				+ codigoCliente + "%'";
		List<Cliente> list = this.hql(query);
		if (list.size() == 1) {
			return list.get(0).getEmpresa();
		}
		return null;

	}

	/**
	 * Para la migracion desde el Carlos Rivas. Obtiene la empresa de acuerdo al
	 * codigo de proveedor en formato -PR00-
	 * 
	 * @param codigo
	 * @return
	 * @throws Exception
	 */
	public IiD getEmpresaByCodigoProveedor(String codigoProveedor)
			throws Exception {
		String query = "select p from Proveedor p where p.empresa.codigoEmpresa like '%"
				+ codigoProveedor + "%'";
		List<Proveedor> list = this.hql(query);
		if (list.size() == 1) {
			return list.get(0).getEmpresa();
		}
		return null;
	}

	/**
	 * @return el Cliente segun el codigo para implementacion MRA..
	 */
	public Cliente getClienteByCodigoMRA(String codigo) throws Exception {
		String query = "Select c from Cliente c where c.empresa.codigoEmpresa like '%-CL"
				+ codigo + "-%'";
		List<Cliente> out = this.hql(query);
		if (out.size() == 1) {
			return out.get(0);
		}
		return null;
	}
	
	/**
	 * @return el Cliente segun el ruc..
	 */
	public Cliente getClienteByRuc(String ruc) throws Exception {
		String query = "Select c from Cliente c where c.empresa.ruc = '"
				+ ruc + "'";
		List<Cliente> out = this.hql(query);
		if (out.size() > 0) {
			return out.get(0);
		}
		return null;
	}
	
	/**
	 * @return el Cliente segun razonsocial..
	 */
	public Cliente getClienteByRazonSocial(String razonsocial) throws Exception {
		String query = "Select c from Cliente c where c.empresa.razonSocial = '"
				+ razonsocial + "'";
		List<Cliente> out = this.hql(query);
		if (out.size() > 0) {
			return out.get(0);
		}
		return null;
	}
	
	/**
	 * @return el Cliente segun idpersona..
	 */
	public Cliente getClienteByIdpersona(String idpersona) throws Exception {
		String query = "Select c from Cliente c where c.idPersonaJedi = '" + idpersona + "'";
		List<Cliente> out = this.hql(query);
		if (out.size() > 0) {
			return out.get(0);
		}
		return null;
	}

	public Date getFechaUltimaMigracion(IiD caja) throws Exception {
		String query = "select max(m.fechaHasta) from VentaMigracion m where m.idCaja = "
				+ caja.getId();
		List<Date> out = this.hql(query);
		if (out.size() == 1) {
			System.out.println(out.get(0));
			return out.get(0);
		}
		return null;
	}

	public List<BancoTarjeta> getBancoTarjetasPendientesByProcesadora(
			IiD procesadora) throws Exception {

		String query = "select bt from BancoTarjeta bt where bt.procesadora.id = "
				+ procesadora.getId() + " and bt.saldo > 0.01";
		List<BancoTarjeta> out = this.hql(query);
		return out;

	}

	public List<Vehiculo> getVehiculosSucursal(long sucursal) throws Exception {
		String query = "select v from Vehiculo v where v.sucursal.id = "
				+ sucursal;
		List<Vehiculo> vehiculosDomain = this.hql(query);

		return vehiculosDomain;
	}

	/**
	 * @return las ventas que fueron marcadas para reparto..
	 */
	public List<Venta> getVentasParaReparto(String numero, long idSucursal) throws Exception {
		String query = " select v from Venta v where v.numero like '%" + numero + "%' "
				+ "AND v.estadoComprobante IS NULL AND (v.tipoMovimiento.sigla = '"
				+ Configuracion.SIGLA_TM_FAC_VENTA_CREDITO
				+ "' or v.tipoMovimiento.sigla = '"
				+ Configuracion.SIGLA_TM_FAC_VENTA_CONTADO
				+ "') AND (v.reparto = 'true') AND (v.estado.sigla = '"
				+ Configuracion.SIGLA_VENTA_ESTADO_FACTURADO + "')"
				+ " AND v.sucursal.id = " + idSucursal;

		List<Venta> out = this.hql(query);
		return out;
	}

	/**
	 * @return las ventas que fueron marcadas para reparto para baterias..
	 */
	public List<Venta> getVentasParaRepartoBaterias(String numero) throws Exception {

		String query = " select v from Venta v where v.numero like '%" + numero + "%' "
				+ "AND v.estadoComprobante IS NULL AND (v.tipoMovimiento.sigla = '"
				+ Configuracion.SIGLA_TM_FAC_VENTA_CREDITO
				+ "' or v.tipoMovimiento.sigla = '"
				+ Configuracion.SIGLA_TM_FAC_VENTA_CONTADO
				+ "') AND (v.reparto = 'true') AND (v.estado.sigla = '"
				+ Configuracion.SIGLA_VENTA_ESTADO_FACTURADO + "')";

		List<Venta> out = this.hql(query);
		return out;
	}
	
	/**
	 * @return las ventas que fueron marcadas para reparto para baterias..
	 * [0]:fecha
	 * [1]:numero
	 * [2]:tipoMovimiento.descripcion
	 * [3]:razonSocial
	 * [4]:totalImporteGs
	 */
	public List<Object[]> getVentasParaRepartoBaterias_(String fecha, String numero, String razonSocial) throws Exception {

		String query = " select v.fecha, v.numero, v.tipoMovimiento.descripcion, v.cliente.empresa.razonSocial, v.totalImporteGs"
				+ " from Venta v where v.numero like '%" + numero + "%'"
				+ " and cast (v.fecha as string) like '%" + fecha + "%'"
				+ " and upper(v.cliente.empresa.razonSocial) like '%" + razonSocial.toUpperCase() + "%'"
				+ " and v.estadoComprobante IS NULL and (v.tipoMovimiento.sigla = '"
				+ Configuracion.SIGLA_TM_FAC_VENTA_CREDITO
				+ "' or v.tipoMovimiento.sigla = '"
				+ Configuracion.SIGLA_TM_FAC_VENTA_CONTADO
				+ "') and (v.reparto = 'true') and (v.estado.sigla = '"
				+ Configuracion.SIGLA_VENTA_ESTADO_FACTURADO + "')";

		List<Object[]> out = this.hql(query);
		return out;
	}

	/**
	 * @return las transferencias externas pendientes de reparto..
	 */
	public List<Transferencia> getTransferenciasParaReparto(long idTipoTransf, long idEstadoTransf, long idSucursal) throws Exception {
		String query = " select t from Transferencia t where t.transferenciaTipo.id = "
				+ idTipoTransf
				+ " and t.transferenciaEstado.id = " + idEstadoTransf
				+ " and t.sucursal.id = " + idSucursal;
		List<Transferencia> out = this.hql(query);
		return out;
	}

	/**
	 * @return las listas de precio activas..
	 */
	public List<ArticuloListaPrecio> getListasDePrecio() throws Exception {
		String query = "select l from ArticuloListaPrecio l where l.dbEstado != 'D' and l.activo = 'TRUE' order by l.id";
		return this.hql(query);
	}
	
	/**
	 * @return la lista de precio segun id..
	 */
	public ArticuloListaPrecio getListaDePrecio(long id) throws Exception {
		String query = "select l from ArticuloListaPrecio l where l.id = " + id + "";
		List<ArticuloListaPrecio> list = this.hql(query);
		return list.size() > 0 ? list.get(0) : null;
	}

	/**
	 * @return la ubicacion segun el estante, fila, columna
	 */
	public List<ArticuloUbicacion> getUbicacion(String estante, String fila,
			String columna) throws Exception {
		String query = "select u from ArticuloUbicacion u where u.estante = '"
				+ estante + "' and u.fila = '" + fila + "' and u.columna = '"
				+ columna + "'";
		return this.hql(query);
	}
	
	/**
	 * @return la ubicacion segun el articulo
	 */
	public List<ArticuloUbicacion> getUbicacion(long idArticulo) throws Exception {
		String query = "select a.ubicaciones from Articulo a where a.id = " + idArticulo;
		return this.hql(query);
	}

	/**
	 * @return la Nota de Credito segun el nro y el timbrado..
	 */
	public NotaCredito getNotaCreditoByNumero(String numero, String timbrado)
			throws Exception {
		String query = "select nc from NotaCredito nc where nc.numero = '"
				+ numero + "' and nc.timbrado.numero = '" + timbrado + "'";
		List<NotaCredito> list = this.hql(query);
		return list.size() > 0 ? list.get(0) : null;
	}
	
	/**
	 * @return la Nota de Debito segun el nro y el timbrado..
	 */
	public NotaDebito getNotaDebitoByNumero(String numero, String timbrado)
			throws Exception {
		String query = "select nd from NotaDebito nd where nd.numero = '"
				+ numero + "' and nd.timbrado = '" + timbrado + "'";
		List<NotaDebito> list = this.hql(query);
		return list.size() > 0 ? list.get(0) : null;
	}

	/**
	 * @return la Nota de Credito segun el nro y el timbrado..
	 */
	public Gasto getGastoByNumero(String numero, String timbrado)
			throws Exception {
		String query = "select g from Gasto g where g.numeroFactura = '"
				+ numero + "' and g.timbrado.numero = '" + timbrado + "'";
		List<Gasto> list = this.hql(query);
		return list.size() > 0 ? list.get(0) : null;
	}

	/**
	 * @return el deposito de la compra..
	 */
	public Deposito getDepositoByFacturaCompraLocal(long idFactura)
			throws Exception {
		String query = "select oc.deposito from CompraLocalOrden oc where oc.factura.id = "+ idFactura;
		List<Deposito> list = this.hql(query);
		return list.size() > 0 ? list.get(0) : null;
	}
	
	/**
	 * @return el deposito de la importacion..
	 */
	public Deposito getDepositoByFacturaImportacion(long idFactura) throws Exception {
		String query = "select oc.deposito from ImportacionPedidoCompra oc join oc.importacionFactura f where f.id = " + idFactura;
		List<Deposito> list = this.hql(query);
		return list.size() > 0 ? list.get(0) : null;
	}

	/**
	 * @return TipoTipo por descripción
	 */
	public TipoTipo getTipoTipoPorDescripcion(String descripcion)
			throws Exception {
		String queryTipo = "select tt from TipoTipo tt where tt.descripcion='"
				+ descripcion + "'";
		TipoTipo out = (TipoTipo) this.hqlToObject(queryTipo);
		return out;
	}

	/**
	 * @return los talonarios..
	 */
	public List<Talonario> getTalonarios() throws Exception {
		return this.getObjects(Talonario.class.getName());
	}

	/**
	 * @return los perfiles de una operacion
	 */
	public List<Perfil> getPerfilesToOperacion(long operacionId)
			throws Exception {
		List<Perfil> p = null;
		String queryPerfil = "" + " select p.perfil " + " from Permiso p"
				+ " where p.operacion.id = " + operacionId + " ";
		// hql(queryPerfil,operacion.getId())

		p = this.hql(queryPerfil);

		return p;
	}

	/**
	 * resta la unidad al saldo del talonario..
	 */
	public void updateSaldoTalonario(long idTalonario) throws Exception {
		Talonario tal = (Talonario) this.getObject(Talonario.class.getName(),
				idTalonario);
		tal.setSaldo(tal.getSaldo() - 1);
		this.saveObject(tal, "sys");
	}

	/**
	 * @return si existe el tipo segun la sigla..
	 */
	public boolean existeTipoBySigla(String sigla) throws Exception {

		Boolean out = false;
		List<Tipo> t = new ArrayList<Tipo>();
		String queryTipo = "select t from Tipo t where t.sigla='" + sigla + "'";
		t = this.hql(queryTipo);
		if (t.size() > 0)
			out = true;

		return out;
	}

	/**
	 * @return reportes..
	 */
	public List<Reporte> getReportes() throws Exception {
		String query = "select r from Reporte r order by r.auxi, r.codigo";
		return this.hql(query);
	}

	public List<BancoChequeTercero> getChequesTercerosReporte(Date fechaDesde,
			Date fechaHasta, String libradoPor, Boolean soloPendientes)
			throws Exception {
		return this.getChequesTercerosReporte(fechaDesde, fechaHasta,
				libradoPor, soloPendientes, false, "", false);

	}

	public List<BancoChequeTercero> getChequesTercerosReporte(Date fechaDesde,
			Date fechaHasta, String libradoPor, Boolean soloPendientes,
			Boolean buscarPorComprobante, String nroComprobante)
			throws Exception {
		return this.getChequesTercerosReporte(fechaDesde, fechaHasta,
				libradoPor, soloPendientes, buscarPorComprobante,
				nroComprobante, false);

	}

	/**
	 * @return cheques de terceros..
	 */
	public List<BancoChequeTercero> getChequesTercerosReporte(Date fechaDesde,
			Date fechaHasta, String libradoPor, Boolean soloPendientes,
			Boolean buscarPorComprobante, String nroComprobante,
			Boolean buscarPorChequesDescontados) throws Exception {
		List<BancoChequeTercero> out = new ArrayList<BancoChequeTercero>();
		String query = "select bct from BancoChequeTercero bct";
		String whereQuery = " where ";
		String orderQuery = " order by bct.fecha asc";
		String queryFinal = "";
		List<Object> listaParametros = new ArrayList<Object>();

		/*
		 * Casi uno fecha desde y fecha hasta null, se devuelven todos los
		 * movimientos Ocurrencia del caso 2 explicado mas arriba Para devolver
		 * todos los movimientos entre el inicio de las operaciones hasta la
		 * "fechaHasta".
		 */
		if (fechaDesde == null && !(fechaHasta == null)) {
			whereQuery += " bct.fecha  <= ? ";
			listaParametros.add(fechaHasta);
		}

		/*
		 * Ocurrencia del caso 3 explicado mas arriba Para devolver todos los
		 * movimientos entre la "fechaDesde" hasta los movimientos mas
		 * recientes.
		 */
		if (!(fechaDesde == null) && fechaHasta == null) {
			whereQuery += "  bct.fecha  >= ? ";
			listaParametros.add(fechaDesde);
		}

		/*
		 * Caso 4: Para devolver todos los movimientos entre "fechaDesde" y
		 * "fechaHasta"
		 */
		if ((!(fechaDesde == null) && !(fechaHasta == null))
				&& (fechaDesde.compareTo(fechaHasta) <= 0)) {
			whereQuery += " bct.fecha between ? and ? ";
			listaParametros.add(fechaDesde);
			listaParametros.add(fechaHasta);
		}

		if (fechaDesde == null && fechaHasta == null) {
			whereQuery += " bct.librado like '%" + libradoPor + "%'";
		} else {
			whereQuery += " and bct.librado like '%" + libradoPor + "%'";
		}

		if (soloPendientes == true) {
			whereQuery += " and bct.depositado is " + false;
		}

		if (buscarPorComprobante == true && nroComprobante.trim().length() > 0) {
			whereQuery += " and bct.reciboFormaPago.auxi like '%"
					+ nroComprobante + "%'";
		}

		if (buscarPorChequesDescontados == true) {
			whereQuery += " and bct.descontado is " + true;
		}

		queryFinal += query;
		queryFinal += whereQuery;
		queryFinal += orderQuery;
		Object[] parametros = new Object[listaParametros.size()];
		for (int i = 0; i < listaParametros.size(); i++) {
			parametros[i] = listaParametros.get(i);
		}

		out = this.hql(queryFinal, parametros);

		return out;
	}

	/**
	 * @return los movimientos del funcionario..
	 */
	public List<FuncionarioCtaCteDetalle> getMovimientosFuncionario(
			long idFuncionario, Date desde, Date hasta) throws Exception {
		Date hasta_ = this.misc.agregarDias(hasta, 1);

		String query = "select d from FuncionarioCtaCteDetalle d where d.funcionario.id = ?"
				+ " and d.fecha between ? and ?" + " order by d.fecha";

		List<Object> listParams = new ArrayList<Object>();
		listParams.add(idFuncionario);
		listParams.add(desde);
		listParams.add(hasta_);

		Object[] params = new Object[listParams.size()];
		for (int i = 0; i < listParams.size(); i++) {
			params[i] = listParams.get(i);
		}
		return this.hql(query, params);
	}

	/**
	 * Agrega un movimiento de funcionario en su cta. cte.
	 */
	public void addMovimientoFuncionario(long idFuncionario, Date fecha,
			String comprobante, String descripcion, double montoGs)
			throws Exception {
		String clase = Funcionario.class.getName();
		Funcionario fun = (Funcionario) this.getObject(clase, idFuncionario);
		FuncionarioCtaCteDetalle det = new FuncionarioCtaCteDetalle();
		det.setFuncionario(fun);
		det.setFecha(fecha);
		det.setNroComprobante(comprobante);
		det.setDescripcion(descripcion);
		det.setMontoGs(montoGs);
		this.saveObject(det, "sys");
	}

	/**
	 * @return los articulos segun sus codigos y descripcion..
	 */
	public List<Articulo> getArticulos(String codigoInterno,
			String codigoOriginal, String codigoProveedor, String descripcion)
			throws Exception {
		String query = "select a from Articulo a where a.articuloEstado.sigla = '" + Configuracion.SIGLA_ARTICULO_ESTADO_ACTIVO + "'"
				+ " and lower(a.codigoInterno) like '%"
				+ codigoInterno.toLowerCase()
				+ "%' and lower(a.codigoOriginal) like '%"
				+ codigoOriginal.toLowerCase()
				+ "%' and lower(a.codigoProveedor) like'%"
				+ codigoProveedor.toLowerCase()
				+ "%' and lower(a.descripcion) like '%"
				+ descripcion.toLowerCase() + "%' order by codigoInterno";
		return this.hqlLimit(query, 50);
	}

	/**
	 * @return el cheque propio..
	 */
	public BancoCheque getChequePropio(long idFormaPago) throws Exception {
		String query = "select b from BancoCheque b where b.reciboFormaPago.id = "
				+ idFormaPago;
		List<BancoCheque> list = this.hql(query);
		return list.size() > 0 ? list.get(0) : null;
	}

	/**
	 * @return el cheque de tercero..
	 */
	public BancoChequeTercero getChequeTercero(long idFormaPago)
			throws Exception {
		String query = "select c from BancoChequeTercero c where c.reciboFormaPago.id = "
				+ idFormaPago;
		List<BancoChequeTercero> list = this.hql(query);
		return list.size() > 0 ? list.get(0) : null;
	}

	/**
	 * @return el movimiento de tarjeta..
	 */
	public BancoTarjeta getTarjeta(long idFormaPago) throws Exception {
		String query = "select t from BancoTarjeta t where t.reciboFormaPago.id = "
				+ idFormaPago;
		List<BancoTarjeta> list = this.hql(query);
		return list.size() > 0 ? list.get(0) : null;
	}

	/**
	 * @return el movimiento de banco..
	 */
	public BancoMovimiento getBancoMovimiento(long idFormaPago)
			throws Exception {
		String query = "select b from BancoMovimiento b where b.auxi = 'RFP "
				+ idFormaPago + "'";
		List<BancoMovimiento> list = this.hql(query);
		return list.size() > 0 ? list.get(0) : null;
	}

	/**
	 * @return el movimiento de banco..
	 */
	public RetencionIva getRetencion(long idRetencion) throws Exception {
		String query = "select r from RetencionIva r where r.id = "
				+ idRetencion;
		List<RetencionIva> list = this.hql(query);
		return list.size() > 0 ? list.get(0) : null;
	}

	/**
	 * @return el movimiento de funcionario..
	 */
	public FuncionarioCtaCteDetalle getFuncionarioMovimiento(
			String nroComprobante) throws Exception {
		String query = "select f from FuncionarioCtaCteDetalle f where f.nroComprobante = '"
				+ nroComprobante + "'";
		List<FuncionarioCtaCteDetalle> list = this.hql(query);
		return list.size() > 0 ? list.get(0) : null;
	}

	/**
	 * @return la venta segun el nro..
	 */
	public Venta getVenta(String numero) throws Exception {
		String query = "select v from Venta v where v.numero = '" + numero
				+ "'";
		List<Venta> list = this.hql(query);
		return list.size() > 0 ? list.get(0) : null;
	}

	/**
	 * @return los movimientos de stock..
	 */
	public List<ArticuloStock> getStockMovimientos(long idMovimiento,
			long idTipoMovimiento) throws Exception {
		String query = "select a from ArticuloStock a where a.idMovimiento = "
				+ idMovimiento + " and a.tipoMovimiento.id = "
				+ idTipoMovimiento;
		List<ArticuloStock> list = this.hql(query);
		return list;
	}

	/**
	 * @return las transferencias externas segun fecha..
	 */
	public List<Transferencia> getTransferenciasExternas(Date desde,
			Date hasta, long idOrigen, long idDestino) throws Exception {

		String query = "select t from Transferencia t where t.dbEstado != 'D'"
				+ " and (t.transferenciaTipo.sigla = ? and t.transferenciaEstado.sigla = ?)"
				+ " and t.fechaCreacion between ? and ?"
				+ " and t.sucursal.id = " + idOrigen
				+ " and t.sucursalDestino.id = " + idDestino
				+ " order by t.fechaCreacion";

		List<Object> listParams = new ArrayList<Object>();
		listParams.add(Configuracion.SIGLA_TM_TRANSF_EXTERNA);
		listParams.add(Configuracion.SIGLA_ESTADO_TRANSF_CONFIRMADA);
		listParams.add(desde);
		listParams.add(hasta);

		Object[] params = new Object[listParams.size()];
		for (int i = 0; i < listParams.size(); i++) {
			params[i] = listParams.get(i);
		}
		return this.hql(query, params);
	}

	/**
	 * @return las compras locales segun fecha de factura
	 */
	public List<CompraLocalFactura> getComprasLocales(Date desde, Date hasta)
			throws Exception {
		String query = "select c from CompraLocalFactura c where c.dbEstado != 'D' and (c.tipoMovimiento.sigla = ? or c.tipoMovimiento.sigla = ?)"
				+ " and c.fechaOriginal between ? and ?"
				+ " order by c.fechaOriginal";

		List<Object> listParams = new ArrayList<Object>();
		listParams.add(Configuracion.SIGLA_TM_FAC_COMPRA_CONTADO);
		listParams.add(Configuracion.SIGLA_TM_FAC_COMPRA_CREDITO);
		listParams.add(desde);
		listParams.add(hasta);

		Object[] params = new Object[listParams.size()];
		for (int i = 0; i < listParams.size(); i++) {
			params[i] = listParams.get(i);
		}
		return this.hql(query, params);
	}

	/**
	 * @return las ventas segun fecha
	 */
	public List<Venta> getVentas(Date desde, Date hasta, long idCliente)
			throws Exception {

		String query = "select v from Venta v where v.dbEstado != 'D' and (v.tipoMovimiento.sigla = ? or v.tipoMovimiento.sigla = ?)"
				+ " and v.fecha between ? and ?";
		if (idCliente != 0) {
			query += " and v.cliente.id = ?";
		}
		query += " order by v.numero";

		List<Object> listParams = new ArrayList<Object>();
		listParams.add(Configuracion.SIGLA_TM_FAC_VENTA_CONTADO);
		listParams.add(Configuracion.SIGLA_TM_FAC_VENTA_CREDITO);
		listParams.add(desde);
		listParams.add(hasta);
		if (idCliente != 0) {
			listParams.add(idCliente);
		}

		Object[] params = new Object[listParams.size()];
		for (int i = 0; i < listParams.size(); i++) {
			params[i] = listParams.get(i);
		}

		return this.hql(query, params);
	}
	
	/**
	 * @return las ventas segun fecha..
	 * [0]:id
	 * [1]:fecha
	 * [2]:idCliente
	 * [3]:razonSocial
	 * [4]:vendedor
	 * [5]:rubro
	 * [6]:totalImporteGs
	 */
	public List<Object[]> get_Ventas(Date desde, Date hasta, long idCliente) throws Exception {		
		String query = "select v.id, v.fecha, v.cliente.id, v.cliente.empresa.razonSocial, v.vendedor.empresa.razonSocial, '', v.totalImporteGs"
				+ " from Venta v where v.dbEstado != 'D' and v.estadoComprobante is null"
				+ " and (v.tipoMovimiento.sigla = ? or v.tipoMovimiento.sigla = ?)"
				+ " and v.fecha between ? and ?";
		if (idCliente != 0) {
			query += " and v.cliente.id = ?";
		}
		query += " order by v.numero";

		List<Object> listParams = new ArrayList<Object>();
		listParams.add(Configuracion.SIGLA_TM_FAC_VENTA_CONTADO);
		listParams.add(Configuracion.SIGLA_TM_FAC_VENTA_CREDITO);
		listParams.add(desde);
		listParams.add(hasta);
		if (idCliente != 0) {
			listParams.add(idCliente);
		}

		Object[] params = new Object[listParams.size()];
		for (int i = 0; i < listParams.size(); i++) {
			params[i] = listParams.get(i);
		}

		return this.hql(query, params);
	}
	
	/**
	 * @return las ventas segun fecha..
	 * [0]:id
	 * [1]:fecha
	 * [2]:idCliente
	 * [3]:razonSocial
	 * [4]:vendedor
	 * [5]:rubro
	 * [6]:totalImporteGs
	 */
	public List<Object[]> get_Ventas(Date desde, Date hasta, long idCliente, long idVendedor) throws Exception {		
		String query = "select v.id, v.fecha, v.cliente.id, v.cliente.empresa.razonSocial, v.vendedor.empresa.razonSocial, '', v.totalImporteGs"
				+ " from Venta v where v.dbEstado != 'D' and v.estadoComprobante is null"
				+ " and (v.tipoMovimiento.sigla = ? or v.tipoMovimiento.sigla = ?)"
				+ " and v.fecha between ? and ?";
		if (idCliente != 0) {
			query += " and v.cliente.id = ?";
		}		
		if (idVendedor != 0) {
			query += " and v.vendedor.id = ?";
		}
		query += " order by v.numero";

		List<Object> listParams = new ArrayList<Object>();
		listParams.add(Configuracion.SIGLA_TM_FAC_VENTA_CONTADO);
		listParams.add(Configuracion.SIGLA_TM_FAC_VENTA_CREDITO);
		listParams.add(desde);
		listParams.add(hasta);
		if (idCliente != 0) {
			listParams.add(idCliente);
		}
		if (idVendedor != 0) {
			listParams.add(idVendedor);
		}

		Object[] params = new Object[listParams.size()];
		for (int i = 0; i < listParams.size(); i++) {
			params[i] = listParams.get(i);
		}

		return this.hql(query, params);
	}

	/**
	 * @return las ventas segun fecha y vendedor..
	 */
	public List<Venta> getVentasPorVendedor(long idVendedor, Date desde,
			Date hasta) throws Exception {

		String query = "select v from Venta v where v.dbEstado != 'D' and v.vendedor.id = "
				+ idVendedor
				+ " and (v.tipoMovimiento.sigla = ? or v.tipoMovimiento.sigla = ?)"
				+ " and v.fecha between ? and ?" + " order by v.numero";

		List<Object> listParams = new ArrayList<Object>();
		listParams.add(Configuracion.SIGLA_TM_FAC_VENTA_CONTADO);
		listParams.add(Configuracion.SIGLA_TM_FAC_VENTA_CREDITO);
		listParams.add(desde);
		listParams.add(hasta);

		Object[] params = new Object[listParams.size()];
		for (int i = 0; i < listParams.size(); i++) {
			params[i] = listParams.get(i);
		}
		return this.hql(query, params);
	}

	/**
	 * @return las ventas segun fecha y vendedor..
	 */
	public List<Venta> getVentasPorCliente(long idCliente, Date desde,
			Date hasta) throws Exception {

		String query = "select v from Venta v where v.dbEstado != 'D'"
				+ " and (v.tipoMovimiento.sigla = ? or v.tipoMovimiento.sigla = ?)"
				+ " and v.fecha between ? and ?";
		
		if (idCliente != 0) {
			query += " and v.cliente.id = " + idCliente;
		}
		query += " order by v.numero";

		List<Object> listParams = new ArrayList<Object>();
		listParams.add(Configuracion.SIGLA_TM_FAC_VENTA_CONTADO);
		listParams.add(Configuracion.SIGLA_TM_FAC_VENTA_CREDITO);
		listParams.add(desde);
		listParams.add(hasta);

		Object[] params = new Object[listParams.size()];
		for (int i = 0; i < listParams.size(); i++) {
			params[i] = listParams.get(i);
		}

		return this.hql(query, params);
	}

	/**
	 * @return las ventas segun fecha
	 */
	public List<Venta> getVentasContado(Date desde, Date hasta, long idCliente)
			throws Exception {

		String query = "select v from Venta v where v.dbEstado != 'D' and v.tipoMovimiento.sigla = ?"
				+ " and v.fecha between ? and ?";
		if (idCliente != 0) {
			query += " and v.cliente.id = ?";
		}
		query += " order by v.numero";

		List<Object> listParams = new ArrayList<Object>();
		listParams.add(Configuracion.SIGLA_TM_FAC_VENTA_CONTADO);
		listParams.add(desde);
		listParams.add(hasta);
		if (idCliente != 0) {
			listParams.add(idCliente);
		}

		Object[] params = new Object[listParams.size()];
		for (int i = 0; i < listParams.size(); i++) {
			params[i] = listParams.get(i);
		}

		return this.hql(query, params);
	}
	
	/**
	 * @return las ventas anuladas segun fecha
	 */
	public List<Venta> getVentasContadoAnuladas(Date desde, Date hasta) throws Exception {

		String query = "select v from Venta v where v.dbEstado != 'D' and v.tipoMovimiento.sigla = ?"
				+ " and v.estadoComprobante.sigla = '" + Configuracion.SIGLA_ESTADO_COMPROBANTE_ANULADO + "'"
				+ " and v.fecha between ? and ?";
		query += " order by v.numero";

		List<Object> listParams = new ArrayList<Object>();
		listParams.add(Configuracion.SIGLA_TM_FAC_VENTA_CONTADO);
		listParams.add(desde);
		listParams.add(hasta);

		Object[] params = new Object[listParams.size()];
		for (int i = 0; i < listParams.size(); i++) {
			params[i] = listParams.get(i);
		}

		return this.hql(query, params);
	}

	/**
	 * @return las ventas segun fecha
	 */
	public List<Venta> getVentasCredito(Date desde, Date hasta, long idCliente)
			throws Exception {

		String query = "select v from Venta v where v.dbEstado != 'D' and v.tipoMovimiento.sigla = ?"
				+ " and v.fecha between ? and ?";
		if (idCliente != 0) {
			query += " and v.cliente.id = ?";
		}
		query += " order by v.numero";

		List<Object> listParams = new ArrayList<Object>();
		listParams.add(Configuracion.SIGLA_TM_FAC_VENTA_CREDITO);
		listParams.add(desde);
		listParams.add(hasta);
		if (idCliente != 0) {
			listParams.add(idCliente);
		}

		Object[] params = new Object[listParams.size()];
		for (int i = 0; i < listParams.size(); i++) {
			params[i] = listParams.get(i);
		}

		return this.hql(query, params);
	}
	
	/**
	 * @return las ventas credito anuladas segun fecha
	 */
	public List<Venta> getVentasCreditoAnuladas(Date desde, Date hasta) throws Exception {
		String query = "select v from Venta v where v.dbEstado != 'D' and v.tipoMovimiento.sigla = ?"
				+ " and v.estadoComprobante.sigla = '" + Configuracion.SIGLA_ESTADO_COMPROBANTE_ANULADO + "'"
				+ " and v.fecha between ? and ?";
		query += " order by v.numero";

		List<Object> listParams = new ArrayList<Object>();
		listParams.add(Configuracion.SIGLA_TM_FAC_VENTA_CREDITO);
		listParams.add(desde);
		listParams.add(hasta);

		Object[] params = new Object[listParams.size()];
		for (int i = 0; i < listParams.size(); i++) {
			params[i] = listParams.get(i);
		}
		return this.hql(query, params);
	}

	/**
	 * @return las notas de credito de venta segun fecha
	 */
	public List<NotaCredito> getNotasCreditoVenta(Date desde, Date hasta, long idCliente) throws Exception {
		String query = "select n from NotaCredito n where n.dbEstado != 'D' and n.tipoMovimiento.sigla = ?"
				+ " and (n.fechaEmision between ? and ?)";

		if (idCliente != 0) {
			query += " and n.cliente.id = ?";
		}
		query += " order by n.numero";

		List<Object> listParams = new ArrayList<Object>();
		listParams.add(Configuracion.SIGLA_TM_NOTA_CREDITO_VENTA);
		listParams.add(desde);
		listParams.add(hasta);
		if (idCliente != 0) {
			listParams.add(idCliente);
		}

		Object[] params = new Object[listParams.size()];
		for (int i = 0; i < listParams.size(); i++) {
			params[i] = listParams.get(i);
		}
		return this.hql(query, params);
	}
	
	/**
	 * @return las notas de credito de venta segun fecha
	 */
	public List<NotaCredito> getNotasCreditoVenta(Date desde, Date hasta, long idCliente, long idVendedor) throws Exception {
		String query = "select n from NotaCredito n where n.dbEstado != 'D' and n.tipoMovimiento.sigla = ?"
				+ " and (n.fechaEmision between ? and ?) and "
				+ " n.estadoComprobante.sigla != '" + Configuracion.SIGLA_ESTADO_COMPROBANTE_ANULADO + "'";

		if (idCliente != 0) {
			query += " and n.cliente.id = ?";
		}
		if (idVendedor != 0) {
			query += " and n.vendedor.id = ?";
		}		
		query += " order by n.numero";

		List<Object> listParams = new ArrayList<Object>();
		listParams.add(Configuracion.SIGLA_TM_NOTA_CREDITO_VENTA);
		listParams.add(desde);
		listParams.add(hasta);
		if (idCliente != 0) {
			listParams.add(idCliente);
		}
		if (idVendedor != 0) {
			listParams.add(idVendedor);
		}

		Object[] params = new Object[listParams.size()];
		for (int i = 0; i < listParams.size(); i++) {
			params[i] = listParams.get(i);
		}
		return this.hql(query, params);
	}
	
	/**
	 * @return las notas de credito de venta segun fecha
	 * [0]:id
	 * [1]:fecha
	 * [2]:idCliente
	 * [3]:razonSocial
	 * [4]:vendedor
	 * [5]:rubro
	 * [6]:totalImporteGs
	 */
	public List<Object[]> getNotasCredito_Venta(Date desde, Date hasta, long idCliente) throws Exception {
		String query = "select n.id, n.fechaEmision, n.cliente.id, n.cliente.empresa.razonSocial, '', '', n.importeGs"
				+ " from NotaCredito n where n.dbEstado != 'D' and n.estadoComprobante.sigla != '" + Configuracion.SIGLA_ESTADO_COMPROBANTE_ANULADO + "'"
				+ " and n.tipoMovimiento.sigla = ?"
				+ " and (n.fechaEmision between ? and ?)";

		if (idCliente != 0) {
			query += " and n.cliente.id = ?";
		}
		query += " order by n.numero";

		List<Object> listParams = new ArrayList<Object>();
		listParams.add(Configuracion.SIGLA_TM_NOTA_CREDITO_VENTA);
		listParams.add(desde);
		listParams.add(hasta);
		if (idCliente != 0) {
			listParams.add(idCliente);
		}

		Object[] params = new Object[listParams.size()];
		for (int i = 0; i < listParams.size(); i++) {
			params[i] = listParams.get(i);
		}
		return this.hql(query, params);
	}
	
	/**
	 * @return las notas de credito de venta segun fecha
	 * [0]:id
	 * [1]:fecha
	 * [2]:idCliente
	 * [3]:razonSocial
	 * [4]:totalImporteGs
	 */
	public List<Object[]> getNotasCredito_Venta_(Date desde, Date hasta, long idCliente, long idVendedor) throws Exception {
		String query = "select n.id, n.fechaEmision, n.cliente.id, n.cliente.empresa.razonSocial, n.importeGs"
				+ " from NotaCredito n where n.dbEstado != 'D' and n.estadoComprobante.sigla !="
				+ " '" + Configuracion.SIGLA_ESTADO_COMPROBANTE_ANULADO + "'"
				+ " and n.tipoMovimiento.sigla = ?"
				+ " and (n.fechaEmision between ? and ?)";
		if (idCliente != 0) {
			query += " and n.cliente.id = ?";
		}
		if (idVendedor != 0) {
			query += " and n.vendedor.id = ?";
		}
		query += " order by n.numero";

		List<Object> listParams = new ArrayList<Object>();
		listParams.add(Configuracion.SIGLA_TM_NOTA_CREDITO_VENTA);
		listParams.add(desde);
		listParams.add(hasta);
		if (idCliente != 0) {
			listParams.add(idCliente);
		}
		if (idVendedor != 0) {
			listParams.add(idVendedor);
		}

		Object[] params = new Object[listParams.size()];
		for (int i = 0; i < listParams.size(); i++) {
			params[i] = listParams.get(i);
		}
		return this.hql(query, params);
	}
	
	/**
	 * @return las notas de credito de venta segun fecha
	 * [0]:id
	 * [1]:numero
	 * [2]:fechaemision
	 * [3]:motivo.descripcion
	 * [4]:motivo.sigla
	 */
	public List<Object[]> getNotasCreditoVenta_(Date desde, Date hasta, long idCliente) throws Exception {
		String query = "select n.id, n.numero, n.fechaEmision, n.motivo.descripcion, n.motivo.sigla from"
				+ " NotaCredito n where n.dbEstado != 'D' and n.estadoComprobante.sigla != '" 
				+ Configuracion.SIGLA_ESTADO_COMPROBANTE_ANULADO + "'"
				+ " and n.tipoMovimiento.sigla = ?"
				+ " and (n.fechaEmision between ? and ?)";

		if (idCliente != 0) {
			query += " and n.cliente.id = ?";
		}
		query += " order by n.numero";

		List<Object> listParams = new ArrayList<Object>();
		listParams.add(Configuracion.SIGLA_TM_NOTA_CREDITO_VENTA);
		listParams.add(desde);
		listParams.add(hasta);
		if (idCliente != 0) {
			listParams.add(idCliente);
		}

		Object[] params = new Object[listParams.size()];
		for (int i = 0; i < listParams.size(); i++) {
			params[i] = listParams.get(i);
		}
		return this.hql(query, params);
	}
	
	/**
	 * @return las ventas segun fecha
	 * [0]:id
	 * [1]:numero
	 * [2]:fechaemision
	 * [3]:tipomovimiento.descripcion
	 * [4]:tipomovimiento.sigla
	 */
	public List<Object[]> getVentas_(Date desde, Date hasta, long idCliente) throws Exception {
		String query = "select v.id, v.numero, v.fecha, v.tipoMovimiento.descripcion, v.tipoMovimiento.sigla from"
				+ " Venta v where v.dbEstado != 'D' and (v.tipoMovimiento.sigla = ? or v.tipoMovimiento.sigla = ?)"
				+ " and v.estadoComprobante is null"
				+ " and v.fecha between ? and ?";
		if (idCliente != 0) {
			query += " and v.cliente.id = ?";
		}
		query += " order by v.numero";

		List<Object> listParams = new ArrayList<Object>();
		listParams.add(Configuracion.SIGLA_TM_FAC_VENTA_CONTADO);
		listParams.add(Configuracion.SIGLA_TM_FAC_VENTA_CREDITO);
		listParams.add(desde);
		listParams.add(hasta);
		if (idCliente != 0) {
			listParams.add(idCliente);
		}
		Object[] params = new Object[listParams.size()];
		for (int i = 0; i < listParams.size(); i++) {
			params[i] = listParams.get(i);
		}
		return this.hql(query, params);
	}
	
	/**
	 * @return las notas de credito de venta segun fecha
	 */
	public List<NotaCredito> getNotasCreditoVentaAnuladas(Date desde, Date hasta) throws Exception {

		String query = "select n from NotaCredito n where n.dbEstado != 'D' and n.tipoMovimiento.sigla = ?"
				+ " and n.estadoComprobante.sigla = '" + Configuracion.SIGLA_ESTADO_COMPROBANTE_ANULADO + "'"
				+ " and (n.fechaEmision between ? and ?)";
		query += " order by n.numero";

		List<Object> listParams = new ArrayList<Object>();
		listParams.add(Configuracion.SIGLA_TM_NOTA_CREDITO_VENTA);
		listParams.add(desde);
		listParams.add(hasta);

		Object[] params = new Object[listParams.size()];
		for (int i = 0; i < listParams.size(); i++) {
			params[i] = listParams.get(i);
		}

		return this.hql(query, params);
	}

	/**
	 * @return los gastos segun fecha..
	 */
	public List<Gasto> getGastos(Date desde, Date hasta) throws Exception {
		String query = "select g from Gasto g where g.dbEstado != 'D'"
				+ " and g.estadoComprobante.sigla != '" + Configuracion.SIGLA_ESTADO_COMPROBANTE_ANULADO + "'"
				+ " and g.fecha between ? and ?" + " order by g.fecha";

		List<Object> listParams = new ArrayList<Object>();
		listParams.add(desde);
		listParams.add(hasta);

		Object[] params = new Object[listParams.size()];
		for (int i = 0; i < listParams.size(); i++) {
			params[i] = listParams.get(i);
		}
		return this.hql(query, params);
	}

	/**
	 * Agrega un registro de ArticuloPrecioMinimo..
	 */
	public void addArticuloPrecioMinimo(long idArticulo, double precioMinimo,
			String user) throws Exception {
		ArticuloPrecioMinimo art = new ArticuloPrecioMinimo();
		art.setFecha(new Date());
		art.setIdArticulo(idArticulo);
		art.setPrecioMinimo(precioMinimo);
		this.saveObject(art, user);
	}

	/**
	 * @return el precio minimo habilitado..
	 */
	public ArticuloPrecioMinimo getArticuloPrecioMinimo(long idArticulo) throws Exception {
		String query = "select a from ArticuloPrecioMinimo a where a.fecha = ?"
				+ " and a.idArticulo = ?" + " order by a.id";

		List<Object> listParams = new ArrayList<Object>();
		listParams.add(new Date());
		listParams.add(idArticulo);

		Object[] params = new Object[listParams.size()];
		for (int i = 0; i < listParams.size(); i++) {
			params[i] = listParams.get(i);
		}
		List<ArticuloPrecioMinimo> out = this.hql(query, params);
		if (out.size() > 0)
			return out.get(out.size() - 1);
		return null;
	}

	/**
	 * @return las ventas segun fecha
	 */
	public List<Venta> getVentasContadoPorVendedor(Date desde, Date hasta,
			long idVendedor) throws Exception {

		String query = "select v from Venta v where v.dbEstado != 'D' and v.tipoMovimiento.sigla = ? and v.vendedor.id = ?"
				+ " and v.fecha between ? and ?" + " order by v.numero";

		List<Object> listParams = new ArrayList<Object>();
		listParams.add(Configuracion.SIGLA_TM_FAC_VENTA_CONTADO);
		listParams.add(idVendedor);
		listParams.add(desde);
		listParams.add(hasta);

		Object[] params = new Object[listParams.size()];
		for (int i = 0; i < listParams.size(); i++) {
			params[i] = listParams.get(i);
		}

		return this.hql(query, params);
	}

	/**
	 * @return las ventas segun fecha
	 */
	public List<Venta> getVentasCreditoPorVendedor(Date desde, Date hasta,
			long idVendedor) throws Exception {

		String query = "select v from Venta v where v.dbEstado != 'D' and v.tipoMovimiento.sigla = ? and v.vendedor.id = ?"
				+ " and v.fecha between ? and ?" + " order by v.numero";

		List<Object> listParams = new ArrayList<Object>();
		listParams.add(Configuracion.SIGLA_TM_FAC_VENTA_CREDITO);
		listParams.add(idVendedor);
		listParams.add(desde);
		listParams.add(hasta);

		Object[] params = new Object[listParams.size()];
		for (int i = 0; i < listParams.size(); i++) {
			params[i] = listParams.get(i);
		}

		return this.hql(query, params);
	}

	/**
	 * @return las ventas segun fecha
	 */
	public List<NotaCredito> getNotasCreditoPorVendedor(Date desde, Date hasta,
			long idVendedor) throws Exception {

		String query = "select n from NotaCredito n where n.tipoMovimiento.sigla = ? and n.vendedor.id = ?"
				+ " and n.fecha between ? and ?" + " order by n.numero";

		List<Object> listParams = new ArrayList<Object>();
		listParams.add(Configuracion.SIGLA_TM_NOTA_CREDITO_VENTA);
		listParams.add(idVendedor);
		listParams.add(desde);
		listParams.add(hasta);

		Object[] params = new Object[listParams.size()];
		for (int i = 0; i < listParams.size(); i++) {
			params[i] = listParams.get(i);
		}

		return this.hql(query, params);
	}

	/**
	 * @return los pedidos pendientes de facturar..
	 */
	public List<Venta> getPedidosPendientes(long idSucursal, Date desde,
			Date hasta) throws Exception {
		String query = "select v from Venta v where (v.fecha between ? and ?)"
				+ " and v.estado.sigla = ?"
				+ " and v.sucursal.id = ?";

		List<Object> listParams = new ArrayList<Object>();
		listParams.add(desde);
		listParams.add(hasta);
		listParams.add(Configuracion.SIGLA_VENTA_ESTADO_CERRADO);
		listParams.add(idSucursal);

		Object[] params = new Object[listParams.size()];
		for (int i = 0; i < listParams.size(); i++) {
			params[i] = listParams.get(i);
		}

		return this.hql(query, params);
	}

	/**
	 * @return los pedidos pendientes de facturar para baterias..
	 */
	public List<Venta> getPedidosPendientesBaterias(long idSucursal,
			Date desde, Date hasta) throws Exception {
		String query = "select v from Venta v where (v.fecha between ? and ?)"
				+ " and v.estado.sigla = ?" + " and v.sucursal.id = ?";

		List<Object> listParams = new ArrayList<Object>();
		listParams.add(desde);
		listParams.add(hasta);
		listParams.add(Configuracion.SIGLA_VENTA_ESTADO_CERRADO);
		listParams.add(idSucursal);

		Object[] params = new Object[listParams.size()];
		for (int i = 0; i < listParams.size(); i++) {
			params[i] = listParams.get(i);
		}
		return this.hql(query, params);
	}

	/**
	 * @return los pedidos pendientes de aprobar para baterias..
	 */
	public List<Venta> getPedidosPendientesAprobacion(Date desde, Date hasta, long idSucursal) throws Exception {
		String query = "select v from Venta v where (v.fecha between ? and ?)"
				+ " and (v.estado.sigla = ? or v.estado.sigla = ?)"
				+ " and v.tipoMovimiento.sigla = ?"
				+ " and v.sucursal.id = " + idSucursal 
				+ " order by v.fecha";

		List<Object> listParams = new ArrayList<Object>();
		listParams.add(desde);
		listParams.add(hasta);
		listParams.add(Configuracion.SIGLA_VENTA_ESTADO_SOLO_PEDIDO);
		listParams.add(Configuracion.SIGLA_VENTA_ESTADO_PASADO_A_PEDIDO);
		listParams.add(Configuracion.SIGLA_TM_PEDIDO_VENTA);

		Object[] params = new Object[listParams.size()];
		for (int i = 0; i < listParams.size(); i++) {
			params[i] = listParams.get(i);
		}
		return this.hql(query, params);
	}
	
	/**
	 * @return los pedidos pendientes de preparacion..
	 */
	public List<Venta> getPedidosPendientesPreparacion() throws Exception {
		String query = "select v from Venta v where v.auxi = 'PENDIENTE'";
		return this.hql(query);
	}

	
	/**
	 * @return los pedidos sin facturar..
	 */
	public List<Venta> getPedidosSinFacturar(long idSucursal,
			Date desde, Date hasta) throws Exception {
		String query = "select v from Venta v where (v.fecha between ? and ?)"
				+ " and (v.estado.sigla = ? or v.estado.sigla = ?)" + " and v.sucursal.id = ?";

		List<Object> listParams = new ArrayList<Object>();
		listParams.add(desde);
		listParams.add(hasta);
		listParams.add(Configuracion.SIGLA_VENTA_ESTADO_CERRADO);
		listParams.add(Configuracion.SIGLA_VENTA_ESTADO_SOLO_PEDIDO);
		listParams.add(idSucursal);

		Object[] params = new Object[listParams.size()];
		for (int i = 0; i < listParams.size(); i++) {
			params[i] = listParams.get(i);
		}
		return this.hql(query, params);
	}
	
	/**
	 * @return los presupuestos sin aprobar..
	 */
	public List<Venta> getPresupuestosSinAprobar(long idSucursal, Date desde, Date hasta) throws Exception {
		String query = "select v from Venta v where (v.fecha between ? and ?)"
				+ " and (v.estado.sigla = ?)" + " and v.sucursal.id = ?";

		List<Object> listParams = new ArrayList<Object>();
		listParams.add(desde);
		listParams.add(hasta);
		listParams.add(Configuracion.SIGLA_VENTA_ESTADO_SOLO_PRESUPUESTO);
		listParams.add(idSucursal);

		Object[] params = new Object[listParams.size()];
		for (int i = 0; i < listParams.size(); i++) {
			params[i] = listParams.get(i);
		}
		return this.hql(query, params);
	}

	/**
	 * @return las ventas donde esta contenida el articulo.. [0]:concepto
	 * [1]:fecha 
	 * [2]:numero 
	 * [3]:cantidad 
	 * [4]:precio 
	 * [5]:cliente 
	 * [6]:deposito
	 * [7]:articulo.codigoInterno
	 * [8]:articulo.descripcion
	 * [9]:articulo.marca
	 * [10]:articulo.proveedor
	 * [11]:articulo.id
	 */
	public List<Object[]> getVentasPorArticulo(long idArticulo, Date desde, Date hasta) throws Exception {
		String desde_ = misc.dateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = misc.dateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select v.tipoMovimiento.descripcion, v.fecha, v.numero, d.cantidad, d.precioGs, v.cliente.empresa.razonSocial,"
				+ " v.deposito.descripcion, d.articulo.codigoInterno, d.articulo.descripcion, d.articulo.articuloMarca.descripcion,"
				+ " (select p.proveedor.empresa.razonSocial from Articulo a join a.proveedorArticulos p where a.id = d.articulo.id ),"
				+ " d.articulo.id"
				+ " from Venta v join v.detalles d where d.articulo.id = "
				+ idArticulo
				+ " and (v.tipoMovimiento.sigla = '"
				+ Configuracion.SIGLA_TM_FAC_VENTA_CONTADO
				+ "' or "
				+ " v.tipoMovimiento.sigla = '"
				+ Configuracion.SIGLA_TM_FAC_VENTA_CREDITO
				+ "') and v.dbEstado != 'D' and v.estadoComprobante IS NULL"
				+ " and (v.fecha >= '"
				+ desde_
				+ "' and v.fecha <= '"
				+ hasta_
				+ "')" + " order by v.fecha desc";
		return this.hql(query);
	}
	
	/**
	 * @return las ventas donde esta contenida el articulo.. [0]:concepto
	 *         [1]:fecha [2]:numero [3]:cantidad [4]:precio [5]:cliente [6]:deposito
	 */
	public List<Object[]> getVentasPorArticulo(long idArticulo, long idDeposito, Date desde, Date hasta) throws Exception {
		String desde_ = misc.dateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = misc.dateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select v.tipoMovimiento.descripcion, v.fecha, v.numero, d.cantidad, d.precioGs, v.cliente.empresa.razonSocial, v.deposito.descripcion"
				+ " from Venta v join v.detalles d where d.articulo.id = "
				+ idArticulo
				+ " and (v.tipoMovimiento.sigla = '"
				+ Configuracion.SIGLA_TM_FAC_VENTA_CONTADO
				+ "' or "
				+ " v.tipoMovimiento.sigla = '"
				+ Configuracion.SIGLA_TM_FAC_VENTA_CREDITO
				+ "') and v.dbEstado != 'D' and v.estadoComprobante IS NULL";
		if (idDeposito != 0) {
				query += " and v.deposito.id = " + idDeposito;
		}
				query += " and (v.fecha >= '"
				+ desde_
				+ "' and v.fecha <= '"
				+ hasta_
				+ "')" + " order by v.fecha desc";
		return this.hql(query);
	}
	
	/**
	 * @return las ventas segun cliente.. 
	 * [0]:concepto
	 * [1]:fecha 
	 * [2]:numero 
	 * [3]:totalImporteGs 
	 * [4]:cliente 
	 */
	public List<Object[]> getVentasPorCliente_(long idCliente, Date desde, Date hasta) throws Exception {
		String desde_ = misc.dateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = misc.dateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select v.tipoMovimiento.descripcion, v.fecha, v.numero, v.totalImporteGs, v.cliente.empresa.razonSocial"
				+ " from Venta v where"
				+ " v.tipoMovimiento.sigla = '"
				+ Configuracion.SIGLA_TM_FAC_VENTA_CREDITO
				+ "' and v.dbEstado != 'D' and v.estadoComprobante IS NULL";
		if (idCliente != 0) {
				query += " and v.cliente.id = " + idCliente;
		}
				query += " and (v.fecha >= '"
				+ desde_
				+ "' and v.fecha <= '"
				+ hasta_
				+ "')" + " order by v.fecha desc";
		return this.hql(query);
	}
	
	/**
	 * @return las compras segun proveedor.. 
	 * [0]:concepto
	 * [1]:fecha 
	 * [2]:numero 
	 * [3]:totalImporteGs 
	 * [4]:proveedor 
	 */
	public List<Object[]> getComprasPorProveedor_(long idProveedor, Date desde, Date hasta) throws Exception {
		String desde_ = misc.dateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = misc.dateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select c.tipoMovimiento.descripcion, c.fechaOriginal, c.numero, c.importeGs, c.proveedor.empresa.razonSocial"
				+ " from CompraLocalFactura c where"
				+ " (c.tipoMovimiento.sigla = '"
				+ Configuracion.SIGLA_TM_FAC_COMPRA_CREDITO + "' or c.tipoMovimiento.sigla = '" + Configuracion.SIGLA_TM_FAC_COMPRA_CONTADO + "')"
				+ " and c.dbEstado != 'D'";
		if (idProveedor != 0) {
				query += " and c.proveedor.id = " + idProveedor;
		}
				query += " and (c.fechaOriginal >= '"
				+ desde_
				+ "' and c.fechaOriginal <= '"
				+ hasta_
				+ "')" + " order by c.fechaOriginal desc";
		return this.hql(query);
	}
	
	/**
	 * @return las importaciones segun proveedor.. 
	 * [0]:concepto
	 * [1]:fecha 
	 * [2]:numero 
	 * [3]:totalImporteGs 
	 * [4]:proveedor 
	 */
	public List<Object[]> getImportacionesPorProveedor_(long idProveedor, Date desde, Date hasta) throws Exception {
		String desde_ = misc.dateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = misc.dateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select i.tipoMovimiento.descripcion, i.fechaFactura, concat(i.numeroPedidoCompra, '/', i.numeroFactura), i.totalImporteDs, i.proveedor.empresa.razonSocial"
				+ " from ImportacionPedidoCompra i where"
				+ " i.dbEstado != 'D'";
		if (idProveedor != 0) {
				query += " and i.proveedor.id = " + idProveedor;
		}
				query += " and (i.fechaFactura >= '"
				+ desde_
				+ "' and i.fechaFactura <= '"
				+ hasta_
				+ "')" + " order by i.fechaFactura desc";
		return this.hql(query);
	}
	
	/**
	 * @return los gastos segun proveedor (no devuelve gastos de caja chica).. 
	 * [0]:concepto
	 * [1]:fecha 
	 * [2]:numero 
	 * [3]:totalImporteGs 
	 * [4]:proveedor 
	 */
	public List<Object[]> getGastosPorProveedor_(long idProveedor, Date desde, Date hasta) throws Exception {
		String desde_ = misc.dateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = misc.dateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select g.tipoMovimiento.descripcion, g.fecha, g.numeroFactura, g.importeGs, g.proveedor.empresa.razonSocial"
				+ " from Gasto g where"
				+ " g.dbEstado != 'D'"
				+ " and ((g.cajaPagoNumero != '- - -' and g.numeroOrdenPago != '- - -') or (g.cajaPagoNumero = '- - -'))"
				+ " and g.no_generar_saldo = 'FALSE'";
		if (idProveedor != 0) {
				query += " and g.proveedor.id = " + idProveedor;
		}
				query += " and (g.fecha >= '"
				+ desde_
				+ "' and g.fecha <= '"
				+ hasta_
				+ "')" + " order by g.fecha desc";
		return this.hql(query);
	}
	
	/**
	 * @return los gastos segun proveedor (no devuelve gastos de caja chica).. 
	 * [0]:concepto
	 * [1]:fecha 
	 * [2]:numero 
	 * [3]:totalImporteGs 
	 * [4]:proveedor 
	 */
	public List<Object[]> getGastosPorProveedorExterior_(long idEmpresa, Date desde, Date hasta) throws Exception {
		String desde_ = misc.dateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = misc.dateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select g.tipoMovimiento.descripcion, g.fecha, g.numeroFactura, d.importeOriginal, (select razonSocial from Empresa e where e.id = d.idEmpresa )"
				+ " from Gasto g join g.aplicacionesDebitoBancario d where"
				+ " g.dbEstado != 'D' and d != null";
		if (idEmpresa != 0) {
				query += " and d.idEmpresa = " + idEmpresa;
		}
				query += " and (g.fecha >= '"
				+ desde_
				+ "' and g.fecha <= '"
				+ hasta_
				+ "')" + " order by g.fecha desc";
		return this.hql(query);
	}
	
	/**
	 * @return los cheques rechazados segun cliente.. 
	 * [0]:concepto
	 * [1]:fecha 
	 * [2]:numero 
	 * [3]:totalImporteGs 
	 * [4]:cliente 
	 */
	public List<Object[]> getChequesRechazadosPorCliente(long idCliente, Date desde, Date hasta) throws Exception {
		String desde_ = misc.dateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = misc.dateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select (select descripcion from TipoMovimiento where sigla = '" + Configuracion.SIGLA_TM_CHEQUE_RECHAZADO + "'), "
				+ " b.emision, b.numero, b.monto, b.cliente.empresa.razonSocial"
				+ " from BancoChequeTercero b where"
				+ " b.rechazado = 'true' and b.dbEstado != 'D'";
		if (idCliente != 0) {
				query += " and b.cliente.id = " + idCliente;
		}
				query += " and (b.emision >= '"
				+ desde_
				+ "' and b.emision <= '"
				+ hasta_
				+ "')" + " order by b.emision desc";
		return this.hql(query);
	}
	
	/**
	 * @return los prestamos cc.. 
	 * [0]:concepto
	 * [1]:fecha 
	 * [2]:numero 
	 * [3]:totalImporteGs 
	 * [4]:cliente 
	 */
	public List<Object[]> getPrestamosCC(long idCliente, Date desde, Date hasta) throws Exception {
		if (idCliente != 0 && idCliente != Configuracion.ID_CLIENTE_YHAGUY_CENTRAL) {
			return new ArrayList<Object[]>();
		}
		String desde_ = misc.dateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = misc.dateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select (select descripcion from TipoMovimiento where sigla = '" + Configuracion.SIGLA_TM_PRESTAMO_CASA_CENTRAL + "'), "
				+ " b.fecha, cast(b.id as string), b.totalChequesDescontado, (select razonSocial from Empresa where id = " + Configuracion.ID_EMPRESA_YHAGUY_CENTRAL + " )"
				+ " from BancoDescuentoCheque b where"
				+ " b.auxi = '" + BancoDescuentoCheque.PRESTAMO + "' and b.dbEstado != 'D'"
				+ " and (b.fecha >= '"
				+ desde_
				+ "' and b.fecha <= '"
				+ hasta_
				+ "')" + " order by b.fecha desc";
		return this.hql(query);
	}

	/**
	 * @return las notasCredito donde esta contenida el articulo.. [0]:concepto
	 *         [1]:fecha [2]:numero [3]:cantidad [4]:precio [5]:cliente [6]:deposito
	 */
	public List<Object[]> getNotasCreditoVtaPorArticulo(long idArticulo,
			Date desde, Date hasta) throws Exception {
		String desde_ = misc.dateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = misc.dateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select n.tipoMovimiento.descripcion, n.fechaEmision, n.numero, d.cantidad, d.montoGs, n.cliente.empresa.razonSocial,"
				+ " (select descripcion from Deposito where id = " + Deposito.ID_DEPOSITO_PRINCIPAL + ")"
				+ " from NotaCredito n join n.detalles d where n.dbEstado != 'D' and d.articulo.id = "
				+ idArticulo
				+ " and (n.tipoMovimiento.sigla = '"
				+ Configuracion.SIGLA_TM_NOTA_CREDITO_VENTA
				+ "') and n.estadoComprobante.sigla != '"
				+ Configuracion.SIGLA_ESTADO_COMPROBANTE_ANULADO
				+ "'"
				+ " and (n.fechaEmision >= '"
				+ desde_
				+ "' and n.fechaEmision <= '" + hasta_ + "')"
				+ " and n.motivo.sigla = '" + Configuracion.SIGLA_TIPO_NC_MOTIVO_DEVOLUCION + "'";
		return this.hql(query);
	}
	
	/**
	 * @return las notasCredito donde esta contenida el articulo.. [0]:concepto
	 *         [1]:fecha [2]:numero [3]:cantidad [4]:precio [5]:cliente [6]:deposito
	 */
	public List<Object[]> getNotasCreditoVtaPorArticulo(long idArticulo, long idDeposito,
			Date desde, Date hasta) throws Exception {
		if (idDeposito != 0 && idDeposito != Deposito.ID_DEPOSITO_PRINCIPAL) {
			return new ArrayList<Object[]>();
		}
		String desde_ = misc.dateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = misc.dateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select n.tipoMovimiento.descripcion, n.fechaEmision, n.numero, d.cantidad, d.montoGs, n.cliente.empresa.razonSocial,"
				+ " (select descripcion from Deposito where id = " + Deposito.ID_DEPOSITO_PRINCIPAL + ")"
				+ " from NotaCredito n join n.detalles d where n.dbEstado != 'D' and d.articulo.id = "
				+ idArticulo
				+ " and (n.tipoMovimiento.sigla = '"
				+ Configuracion.SIGLA_TM_NOTA_CREDITO_VENTA
				+ "') and n.estadoComprobante.sigla != '"
				+ Configuracion.SIGLA_ESTADO_COMPROBANTE_ANULADO
				+ "'"
				+ " and (n.fechaEmision >= '"
				+ desde_
				+ "' and n.fechaEmision <= '" + hasta_ + "')"
				+ " and n.motivo.sigla = '" + Configuracion.SIGLA_TIPO_NC_MOTIVO_DEVOLUCION + "'";
		return this.hql(query);
	}
	
	/**
	 * @return las notasCredito segun cliente.. 
	 * [0]:concepto
	 * [1]:fecha 
	 * [2]:numero 
	 * [3]:totalImporteGs 
	 * [4]:cliente
	 */
	public List<Object[]> getNotasCreditoPorCliente(long idCliente, Date desde, Date hasta) throws Exception {
		String desde_ = misc.dateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = misc.dateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select n.tipoMovimiento.descripcion, n.fechaEmision, n.numero, n.importeGs, n.cliente.empresa.razonSocial"
				+ " from NotaCredito n where n.dbEstado != 'D'"
				+ " and n.auxi = '"+ TipoMovimiento.NC_CREDITO +"'"
				+ " and n.tipoMovimiento.sigla = '"
				+ Configuracion.SIGLA_TM_NOTA_CREDITO_VENTA
				+ "' and n.estadoComprobante.sigla != '"
				+ Configuracion.SIGLA_ESTADO_COMPROBANTE_ANULADO
				+ "'";
				if (idCliente != 0) {
					query += " and n.cliente.id = " + idCliente;
				}
				query += " and (n.fechaEmision >= '"
				+ desde_
				+ "' and n.fechaEmision <= '" + hasta_ + "')";
		return this.hql(query);
	}
	
	/**
	 * @return las notasCredito segun proveedor.. 
	 * [0]:concepto
	 * [1]:fecha 
	 * [2]:numero 
	 * [3]:totalImporteGs 
	 * [4]:cliente
	 */
	public List<Object[]> getNotasCreditoPorProveedor(long idProveedor, Date desde, Date hasta, boolean gs) throws Exception {
		String desde_ = misc.dateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = misc.dateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select n.tipoMovimiento.descripcion, n.fechaEmision, n.numero, " + (gs ? "n.importeGs" : "n.importeDs" ) + ", n.proveedor.empresa.razonSocial"
				+ " from NotaCredito n where n.dbEstado != 'D'"
				+ " and (n.tipoMovimiento.sigla = '"
				+ Configuracion.SIGLA_TM_NOTA_CREDITO_COMPRA
				+ "') and n.estadoComprobante.sigla != '"
				+ Configuracion.SIGLA_ESTADO_COMPROBANTE_ANULADO
				+ "'";
				if (idProveedor != 0) {
					query += " and n.proveedor.id = " + idProveedor;
				}
				query += " and (n.fechaEmision >= '"
				+ desde_
				+ "' and n.fechaEmision <= '" + hasta_ + "')";
		return this.hql(query);
	}

	/**
	 * @return las notasCredito donde esta contenida el articulo.. [0]:concepto
	 *         [1]:fecha [2]:numero [3]:cantidad [4]:precio [5]:proveedor [6]:deposito
	 */
	public List<Object[]> getNotasCreditoCompraPorArticulo(long idArticulo, long idDeposito,
			Date desde, Date hasta) throws Exception {
		if (idDeposito != 0 && idDeposito != Deposito.ID_DEPOSITO_PRINCIPAL) {
			return new ArrayList<Object[]>();
		}
		String desde_ = misc.dateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = misc.dateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select n.tipoMovimiento.descripcion, n.fechaEmision, n.numero, d.cantidad, d.montoGs, n.proveedor.empresa.razonSocial,"
				+ " (select descripcion from Deposito where id = " + Deposito.ID_DEPOSITO_PRINCIPAL + ")"
				+ " from NotaCredito n join n.detalles d where d.articulo.id = "
				+ idArticulo
				+ " and (n.tipoMovimiento.sigla = '"
				+ Configuracion.SIGLA_TM_NOTA_CREDITO_COMPRA
				+ "') and n.estadoComprobante.sigla != '"
				+ Configuracion.SIGLA_ESTADO_COMPROBANTE_ANULADO
				+ "'"
				+ " and (n.fechaEmision >= '"
				+ desde_
				+ "' and n.fechaEmision <= '" + hasta_ + "')";
		return this.hql(query);
	}
	
	/**
	 * @return las notasCredito donde esta contenida el articulo.. [0]:concepto
	 *         [1]:fecha [2]:numero [3]:cantidad [4]:precio [5]:proveedor [6]:deposito
	 */
	public List<Object[]> getNotasCreditoCompraPorArticulo(long idArticulo,
			Date desde, Date hasta) throws Exception {
		String desde_ = misc.dateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = misc.dateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select n.tipoMovimiento.descripcion, n.fechaEmision, n.numero, d.cantidad, d.montoGs, n.proveedor.empresa.razonSocial,"
				+ " (select descripcion from Deposito where id = " + Deposito.ID_DEPOSITO_PRINCIPAL + ")"
				+ " from NotaCredito n join n.detalles d where d.articulo.id = "
				+ idArticulo
				+ " and (n.tipoMovimiento.sigla = '"
				+ Configuracion.SIGLA_TM_NOTA_CREDITO_COMPRA
				+ "') and n.estadoComprobante.sigla != '"
				+ Configuracion.SIGLA_ESTADO_COMPROBANTE_ANULADO
				+ "'"
				+ " and (n.fechaEmision >= '"
				+ desde_
				+ "' and n.fechaEmision <= '" + hasta_ + "')";
		return this.hql(query);
	}

	/**
	 * @return las transferencias donde esta contenida el articulo..
	 *         [0]:concepto [1]:fecha [2]:numero [3]:cantidad [4]:costo
	 *         [5]:origen [6]:idsuc [7]:id salida [8]:id entrada
	 */
	public List<Object[]> getTransferenciasPorArticulo(long idArticulo,
			Date desde, Date hasta) throws Exception {
		String desde_ = misc.dateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = misc.dateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select t.transferenciaTipo.descripcion, t.fechaCreacion, t.observacion, d.cantidad, d.costo, t.sucursal.descripcion, t.sucursal.id,"
				+ " t.depositoSalida.id, t.depositoEntrada.id"
				+ " from Transferencia t join t.detalles d where t.dbEstado != 'D' and d.dbEstado != 'D' and d.articulo.id = "
				+ idArticulo
				+ " and (t.transferenciaTipo.sigla = '"
				+ Configuracion.ID_TIPO_TRANSFERENCIA_EXTERNA
				+ "')"
				+ " and t.transferenciaEstado.sigla = '"
				+ Configuracion.SIGLA_ESTADO_TRANSF_CONFIRMADA
				+ "'"
				+ " and (t.fechaCreacion >= '"
				+ desde_
				+ "' and t.fechaCreacion <= '"
				+ hasta_
				+ "')"
				+ " order by t.fechaCreacion desc";
		return this.hql(query);
	}
	
	/**
	 * @return las transferencias donde esta contenida el articulo..
	 *         [0]:concepto [1]:fecha [2]:numero [3]:cantidad [4]:costo
	 *         [5]:origen [6]:idsuc [7]:id salida [8]:id entrada
	 */
	public List<Object[]> getTransferenciasPorArticulo(long idArticulo, long idDeposito,
			Date desde, Date hasta, boolean entrada) throws Exception {
		String desde_ = misc.dateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = misc.dateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String deposito = entrada ? "t.depositoEntrada.descripcion" : "t.depositoSalida.descripcion";
		String query = "select t.transferenciaTipo.descripcion, t.fechaCreacion, t.numero, d.cantidad, d.costo, t.sucursal.descripcion, " + deposito
				+ " from Transferencia t join t.detalles d where t.dbEstado != 'D' and d.dbEstado != 'D' and d.articulo.id = "
				+ idArticulo
				+ " and t.transferenciaEstado.sigla = '"
				+ Configuracion.SIGLA_ESTADO_TRANSF_CONFIRMADA
				+ "'";
		if (idDeposito != 0) {
			query += " and " + (entrada ? "t.depositoEntrada.id" : "t.depositoSalida.id") + " = " + idDeposito;
		}
		query += " and (t.fechaCreacion >= '"
				+ desde_
				+ "' and t.fechaCreacion <= '"
				+ hasta_
				+ "')"
				+ " order by t.fechaCreacion desc";
		return this.hql(query);
	}

	/**
	 * @return las compras donde esta contenida el articulo.. [0]:concepto
	 *         [1]:fecha [2]:numero [3]:cantidad [4]:precio [5]:proveedor [6]:id
	 */
	public List<Object[]> getComprasLocalesPorArticulo(long idArticulo,
			Date desde, Date hasta) throws Exception {
		String desde_ = misc.dateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = misc.dateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select c.tipoMovimiento.descripcion, c.fechaOriginal, c.numero, d.cantidad, d.costoGs, "
				+ " c.proveedor.empresa.razonSocial, c.id"
				+ " from CompraLocalFactura c join c.detalles d where c.dbEstado != 'D' and d.articulo.id = "
				+ idArticulo
				+ " and (c.tipoMovimiento.sigla = '"
				+ Configuracion.SIGLA_TM_FAC_COMPRA_CONTADO
				+ "' or "
				+ " c.tipoMovimiento.sigla = '"
				+ Configuracion.SIGLA_TM_FAC_COMPRA_CREDITO
				+ "')"
				+ " and c.dbEstado = 'R'"
				+ " and (c.fechaCreacion >= '"
				+ desde_
				+ "' and c.fechaCreacion <= '"
				+ hasta_
				+ "')"
				+ " order by c.fechaCreacion desc";
		List<Object[]> list = this.hql(query);
		return list;
	}
	
	/**
	 * @return las compras donde esta contenida el articulo.. [0]:concepto
	 *         [1]:fecha [2]:numero [3]:cantidad [4]:precio [5]:proveedor [6]:deposito [7]:id
	 */
	public List<Object[]> getComprasLocalesPorArticulo_(long idArticulo, long idDeposito,
			Date desde, Date hasta) throws Exception {
		String desde_ = misc.dateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = misc.dateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select c.tipoMovimiento.descripcion, c.fechaOriginal, c.numero, d.cantidad, d.costoGs, "
				+ " c.proveedor.empresa.razonSocial,"
				+ " (select oc.deposito.descripcion from CompraLocalOrden oc where oc.factura.id = c.id";
		if (idDeposito != 0) {
			query += " and oc.deposito.id = " + idDeposito;
		}
		query +=" ), c.id"
				+ " from CompraLocalFactura c join c.detalles d where c.dbEstado != 'D' and d.articulo.id = "
				+ idArticulo
				+ " and (c.tipoMovimiento.sigla = '"
				+ Configuracion.SIGLA_TM_FAC_COMPRA_CONTADO
				+ "' or "
				+ " c.tipoMovimiento.sigla = '"
				+ Configuracion.SIGLA_TM_FAC_COMPRA_CREDITO
				+ "')"
				+ " and c.dbEstado = 'R'"
				+ " and (c.fechaCreacion >= '"
				+ desde_
				+ "' and c.fechaCreacion <= '"
				+ hasta_
				+ "')"
				+ " order by c.fechaCreacion desc";
		List<Object[]> list = this.hql(query);
		List<Object[]> out = new ArrayList<Object[]>();
		for (Object[] obj : list) {
			if (obj[6] != null) {
				out.add(obj);
			}
		}
		return out;
	}
	
	/**
	 * @return las compras importacion donde esta contenida el articulo.. [0]:concepto
	 *         [1]:fecha [2]:numero [3]:cantidad [4]:precio [5]:proveedor [6]:id
	 */
	public List<Object[]> getComprasImportacionPorArticulo(long idArticulo,
			Date desde, Date hasta) throws Exception {
		String desde_ = misc.dateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = misc.dateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select c.tipoMovimiento.descripcion, c.fechaCreacion, c.numero, d.cantidad, d.costoGs, "
				+ " c.proveedor.empresa.razonSocial, c.id"
				+ " from ImportacionFactura c join c.detalles d where c.dbEstado != 'D' and d.articulo.id = "
				+ idArticulo
				+ " and (c.tipoMovimiento.sigla = '"
				+ Configuracion.SIGLA_TM_FAC_IMPORT_CONTADO
				+ "' or "
				+ " c.tipoMovimiento.sigla = '"
				+ Configuracion.SIGLA_TM_FAC_IMPORT_CREDITO
				+ "')"
				+ " and c.dbEstado = 'R'"
				+ " and (c.fechaCreacion >= '"
				+ desde_
				+ "' and c.fechaCreacion <= '"
				+ hasta_
				+ "')"
				+ " order by c.fechaCreacion desc";
		List<Object[]> list = this.hql(query);
		return list;
	}
	
	/**
	 * @return las compras importacion donde esta contenida el articulo.. [0]:concepto
	 *         [1]:fecha [2]:numero [3]:cantidad [4]:precio [5]:proveedor [6]:deposito
	 */
	public List<Object[]> getComprasImportacionPorArticulo(long idArticulo, long idDeposito,
			Date desde, Date hasta) throws Exception {
		String desde_ = misc.dateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = misc.dateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select c.tipoMovimiento.descripcion, c.fechaCreacion, c.numero, d.cantidad, d.costoGs, "
				+ " c.proveedor.empresa.razonSocial,"
				+ " (select oc.deposito.descripcion from ImportacionPedidoCompra oc join oc.importacionFactura f where f.id = c.id";
		if (idDeposito != 0) {
			query += " and oc.deposito.id = " + idDeposito;
		}
		query +=" ) "
				+ " from ImportacionFactura c join c.detalles d where c.dbEstado != 'D' and d.articulo.id = "
				+ idArticulo
				+ " and (c.tipoMovimiento.sigla = '"
				+ Configuracion.SIGLA_TM_FAC_IMPORT_CONTADO
				+ "' or "
				+ " c.tipoMovimiento.sigla = '"
				+ Configuracion.SIGLA_TM_FAC_IMPORT_CREDITO
				+ "')"
				+ " and c.dbEstado = 'R'"
				+ " and (c.fechaCreacion >= '"
				+ desde_
				+ "' and c.fechaCreacion <= '"
				+ hasta_
				+ "')"
				+ " order by c.fechaCreacion desc";
		List<Object[]> list = this.hql(query);
		List<Object[]> out = new ArrayList<Object[]>();
		for (Object[] obj : list) {
			if (obj[6] != null) {
				out.add(obj);
			}
		}
		return out;
	}

	/**
	 * @return los ajustes donde esta contenida el articulo..
	 */
	public List<Object[]> getAjustesPorArticulo(long idArticulo, Date desde,
			Date hasta, long idSucursal, String tipo) throws Exception {
		String desde_ = misc.dateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = misc.dateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "";
		if (tipo.equals(Configuracion.SIGLA_TM_AJUSTE_POSITIVO)) {
			query = "select a.tipoMovimiento.descripcion, a.fecha, a.numero, d.cantidad, a.sucursal.descripcion, d.costoGs, a.deposito.descripcion"
					+ " from AjusteStock a join a.detalles d where a.dbEstado != 'D' and d.articulo.id = "
					+ idArticulo
					+ " and a.tipoMovimiento.sigla = '"
					+ tipo
					+ "' and a.sucursal.id = "
					+ idSucursal
					+ " and a.estadoComprobante.sigla = '"
					+ Configuracion.SIGLA_ESTADO_COMPROBANTE_CERRADO
					+ "'"
					+ " and (a.fecha >= '"
					+ desde_
					+ "' and a.fecha <= '"
					+ hasta_ + "')" + " order by a.fecha desc";
		} else if (tipo.equals(Configuracion.SIGLA_TM_AJUSTE_NEGATIVO)) {
			query = "select a.tipoMovimiento.descripcion, a.fecha, a.numero, d.cantidad, a.sucursal.descripcion, d.costoGs, a.deposito.descripcion"
					+ " from AjusteStock a join a.detalles d where a.dbEstado != 'D' and d.articulo.id = "
					+ idArticulo
					+ " and a.tipoMovimiento.sigla = '"
					+ tipo
					+ "' and a.sucursal.id = "
					+ idSucursal
					+ " and a.estadoComprobante.sigla = '"
					+ Configuracion.SIGLA_ESTADO_COMPROBANTE_CERRADO
					+ "'"
					+ " and (a.fecha >= '"
					+ desde_
					+ "' and a.fecha <= '"
					+ hasta_ + "')" + "order by a.fecha desc";
		}
		List<Object[]> out = this.hql(query);
		List<Object[]> out2 = new ArrayList<Object[]>();
		for (Object[] obj : out) {
			Object[] item = new Object[] { obj[0], obj[1], obj[2], obj[3],
					obj[5] == null ? 0.0 : obj[5], obj[4], obj[6] };
			out2.add(item);
		}
		return out2;
	}
	
	/**
	 * @return los ajustes donde esta contenida el articulo..
	 */
	public List<Object[]> getAjustesPorArticulo(long idArticulo, long idDeposito, Date desde,
			Date hasta, long idSucursal, String tipo) throws Exception {
		String desde_ = misc.dateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = misc.dateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "";
		if (tipo.equals(Configuracion.SIGLA_TM_AJUSTE_POSITIVO)) {
			query = "select a.tipoMovimiento.descripcion, a.fecha, a.numero, d.cantidad, a.sucursal.descripcion, d.costoGs, a.deposito.descripcion"
					+ " from AjusteStock a join a.detalles d where a.dbEstado != 'D' and d.articulo.id = "
					+ idArticulo
					+ " and a.tipoMovimiento.sigla = '"
					+ tipo
					+ "' and a.sucursal.id = "
					+ idSucursal
					+ " and a.estadoComprobante.sigla = '"
					+ Configuracion.SIGLA_ESTADO_COMPROBANTE_CERRADO
					+ "'";
			if (idDeposito != 0) {
				query += " and a.deposito.id = " + idDeposito;
			}
			query += " and (a.fecha >= '"
					+ desde_
					+ "' and a.fecha <= '"
					+ hasta_ + "')" + " order by a.fecha desc";
		} else if (tipo.equals(Configuracion.SIGLA_TM_AJUSTE_NEGATIVO)) {
			query = "select a.tipoMovimiento.descripcion, a.fecha, a.numero, d.cantidad, a.sucursal.descripcion, d.costoGs, a.deposito.descripcion"
					+ " from AjusteStock a join a.detalles d where a.dbEstado != 'D' and d.articulo.id = "
					+ idArticulo
					+ " and a.tipoMovimiento.sigla = '"
					+ tipo
					+ "' and a.sucursal.id = "
					+ idSucursal
					+ " and a.estadoComprobante.sigla = '"
					+ Configuracion.SIGLA_ESTADO_COMPROBANTE_CERRADO
					+ "'";
			if (idDeposito != 0) {
				query += " and a.deposito.id = " + idDeposito;
			}
			query += " and (a.fecha >= '"
					+ desde_
					+ "' and a.fecha <= '"
					+ hasta_ + "')" + "order by a.fecha desc";
		}
		List<Object[]> out = this.hql(query);
		List<Object[]> out2 = new ArrayList<Object[]>();
		for (Object[] obj : out) {
			Object[] item = new Object[] { obj[0], obj[1], obj[2], obj[3],
					obj[5] == null ? 0.0 : obj[5], obj[4], obj[6] };
			out2.add(item);
		}
		return out2;
	}

	public List<Object[]> getArticulosStock(long idDeposito) throws Exception {
		String query = "select a.id, a.codigoInterno, ad.stock"
				+ " from ArticuloDeposito ad join ad.articulo a where ad.deposito.id = "
				+ idDeposito;
		return this.hql(query);
	}

	/**
	 * migracion del articulo
	 */
	public List<Object[]> getMigracionPorArticulo(String codigoArticulo,
			Date desde, Date hasta, long idSucursal) throws Exception {
		String desde_ = misc.dateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = misc.dateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select a.fechaAlta, a.stock, a.sucursal.descripcion, a.costo"
				+ " from ArticuloHistorialMigracion a where a.dbEstado != 'D' and a.codigoInterno like '"
				+ codigoArticulo + "' and a.sucursal.id = " + idSucursal;
		if (desde != null) {
			query += " and (a.fechaAlta >= '" + desde_
					+ "' and a.fechaAlta <= '" + hasta_ + "')";
		}
		List<Object[]> out = this.hql(query);
		List<Object[]> out2 = new ArrayList<Object[]>();
		for (Object[] obj : out) {
			Object[] item = new Object[] { "MIGRACIÓN", obj[0], "MIGRACIÓN",
					obj[1], obj[3], obj[2], "S1-D1" };
			out2.add(item);
		}
		return out2;
	}

	/**
	 * @return el ultimo costo del articulo.. [0]: fecha de la compra [1]:
	 *         proveedor [2]: costo final gs [3]: concepto [4]: numero 
	 */
	public Object[] getUltimoCosto(long idArticulo) throws Exception {
		String query = "select c from ArticuloCosto c where c.articulo.id = "
				+ idArticulo + " order by c.id desc";
		List<ArticuloCosto> out = this.hql(query);

		if (out.size() > 0) {
			ArticuloCosto costo = out.get(0);
			CompraLocalOrden compra = null;
			CompraLocalFactura compraFac = null;
			Transferencia transf = null;
			ImportacionPedidoCompra impor = null;
			String proveedor = null;
			String sigla = costo.getTipoMovimiento().getSigla();
			String numero = "";

			if (sigla.equals(Configuracion.SIGLA_TM_ORDEN_COMPRA)) {
				compra = this.getOrdenCompra(costo.getIdMovimiento().longValue());
				proveedor = compra.getProveedor().getRazonSocial();
				numero = compra.getNumero();
			} else if (sigla.equals(Configuracion.SIGLA_TM_FAC_COMPRA_CONTADO) 
					|| sigla.equals(Configuracion.SIGLA_TM_FAC_COMPRA_CREDITO)) {
				compraFac = this.getCompraLocalFactura(costo.getIdMovimiento().longValue());
				proveedor = compraFac.getProveedor().getRazonSocial();
				numero = compraFac.getNumero();
			} else if (sigla.equals(Configuracion.SIGLA_TM_TRANS_MERCADERIA)) {
				transf = this.getTransferencia(costo.getIdMovimiento()
						.longValue());
				proveedor = transf.getSucursal().getDescripcion();
				numero = transf.getNumero();
			} else if (sigla.equals(Configuracion.SIGLA_TM_ORDEN_COMPRA_IMPOR)) {
				impor = this.getOrdenCompraImportacion(costo.getIdMovimiento().longValue());
				proveedor = impor.getProveedor().getRazonSocial();
				numero = impor.getNumeroPedidoCompra();
			}

			return new Object[] { costo.getFechaCompra(), proveedor,
					costo.getCostoFinalGs(), costo.getTipoMovimiento().getDescripcion(), numero };
		}
		return null;
	}
	
	/**
	 * @return los recibos segun cliente.. 
	 * [0]:concepto
	 * [1]:fecha 
	 * [2]:numero 
	 * [3]:totalImporteGs ]
	 */
	public List<Object[]> getRecibosPorCliente(long idCliente, Date desde, Date hasta) throws Exception {
		String desde_ = misc.dateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = misc.dateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select r.tipoMovimiento.descripcion, r.fechaEmision, r.numero, r.totalImporteGs, r.cliente.empresa.razonSocial"
				+ " from Recibo r where r.dbEstado != 'D'"
				+ " and (r.tipoMovimiento.sigla = '"
				+ Configuracion.SIGLA_TM_RECIBO_COBRO
				+ "') and r.estadoComprobante.sigla != '"
				+ Configuracion.SIGLA_ESTADO_COMPROBANTE_ANULADO
				+ "'";
				if (idCliente != 0) {
					query += " and r.cliente.id = " + idCliente;
				}
				query += " and (r.fechaEmision >= '"
				+ desde_
				+ "' and r.fechaEmision <= '" + hasta_ + "')";
		return this.hql(query);
	}
	
	/**
	 * @return los pagos segun proveedor.. 
	 * [0]:concepto
	 * [1]:fecha 
	 * [2]:numero 
	 * [3]:totalImporteGs ]
	 */
	public List<Object[]> getPagosPorProveedor(long idProveedor, Date desde, Date hasta, boolean gs) throws Exception {
		String desde_ = misc.dateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = misc.dateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select r.tipoMovimiento.descripcion, r.fechaEmision, r.numero, " 
				+ (gs ? "r.totalImporteGs" : "r.totalImporteDs") + ", r.proveedor.empresa.razonSocial"
				+ " from Recibo r where r.dbEstado != 'D'"
				+ " and (r.tipoMovimiento.sigla = '"
				+ Configuracion.SIGLA_TM_RECIBO_PAGO
				+ "') and r.estadoComprobante.sigla != '"
				+ Configuracion.SIGLA_ESTADO_COMPROBANTE_ANULADO
				+ "'";
				if (idProveedor != 0) {
					query += " and r.proveedor.id = " + idProveedor;
				}
				query += " and (r.fechaEmision >= '"
				+ desde_
				+ "' and r.fechaEmision <= '" + hasta_ + "')";
		return this.hql(query);
	}
	
	/**
	 * @return los pagos segun proveedor exterior.. 
	 * [0]:concepto
	 * [1]:fecha 
	 * [2]:numero 
	 * [3]:totalImporteGs ]
	 */
	public List<Object[]> getPagosPorProveedorExterior(long idProveedor, Date desde, Date hasta, boolean gs) throws Exception {
		String desde_ = misc.dateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = misc.dateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select r.tipoMovimiento.descripcion, r.fechaEmision, r.numero, " 
				+ (gs ? "r.totalImporteGs" : "r.totalImporteDs") + ", r.proveedor.empresa.razonSocial"
				+ " from Recibo r where r.dbEstado != 'D'"
				+ " and (r.tipoMovimiento.sigla = '"
				+ Configuracion.SIGLA_TM_RECIBO_PAGO
				+ "') and r.estadoComprobante.sigla != '"
				+ Configuracion.SIGLA_ESTADO_COMPROBANTE_ANULADO
				+ "'";
				if (idProveedor != 0) {
					query += " and r.proveedor.id = " + idProveedor;
				}
				query += " and (r.fechaEmision >= '"
				+ desde_
				+ "' and r.fechaEmision <= '" + hasta_ + "') and r.proveedor.tipoProveedor.sigla = '" + Configuracion.SIGLA_PROVEEDOR_TIPO_EXTERIOR + "'";
		return this.hql(query);
	}
	
	/**
	 * @return los pagos anticipados segun proveedor exterior.. 
	 * [0]:concepto
	 * [1]:fecha 
	 * [2]:numero 
	 * [3]:totalImporteGs ]
	 */
	public List<Object[]> getPagosAnticipadosPorProveedorExterior(long idProveedor, Date desde, Date hasta, boolean gs) throws Exception {
		String desde_ = misc.dateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = misc.dateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select r.tipoMovimiento.descripcion, r.fechaEmision, r.numero, " 
				+ (gs ? "r.totalImporteGs" : "r.totalImporteDs") + ", r.proveedor.empresa.razonSocial"
				+ " from Recibo r where r.dbEstado != 'D'"
				+ " and (r.tipoMovimiento.sigla = '"
				+ Configuracion.SIGLA_TM_ANTICIPO_PAGO
				+ "') and r.estadoComprobante.sigla != '"
				+ Configuracion.SIGLA_ESTADO_COMPROBANTE_ANULADO
				+ "'";
				if (idProveedor != 0) {
					query += " and r.proveedor.id = " + idProveedor;
				}
				query += " and (r.fechaEmision >= '"
				+ desde_
				+ "' and r.fechaEmision <= '" + hasta_ + "') and r.proveedor.tipoProveedor.sigla = '" + Configuracion.SIGLA_PROVEEDOR_TIPO_EXTERIOR + "'";
		return this.hql(query);
	}
	
	/**
	 * @return los recibos segun cliente.. 
	 * [0]:concepto
	 * [1]:fecha 
	 * [2]:numero 
	 * [3]:totalImporteGs 
	 * [4]:cliente
	 * [5]:d.nroComprobante
	 * [6]:d.emision
	 * [7]:d.vencimiento
	 */
	public List<Object[]> getRecibosPorCliente_(long idCliente, Date desde, Date hasta) throws Exception {
		String desde_ = misc.dateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = misc.dateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select r.tipoMovimiento.descripcion, r.fechaEmision, r.numero, r.totalImporteGs, r.cliente.empresa.razonSocial,"
				+ " d.movimiento.nroComprobante, d.movimiento.fechaEmision, d.movimiento.fechaVencimiento"
				+ " from Recibo r join r.detalles d where r.dbEstado != 'D'"
				+ " and (r.tipoMovimiento.sigla = '"
				+ Configuracion.SIGLA_TM_RECIBO_COBRO
				+ "') and r.estadoComprobante.sigla != '"
				+ Configuracion.SIGLA_ESTADO_COMPROBANTE_ANULADO
				+ "'";
				if (idCliente != 0) {
					query += " and r.cliente.id = " + idCliente;
				}
				query += " and (r.fechaEmision >= '"
				+ desde_
				+ "' and r.fechaEmision <= '" + hasta_ + "')";
		return this.hql(query);
	}
	
	/**
	 * @return los recibos segun cliente.. 
	 * [0]:concepto
	 * [1]:fecha 
	 * [2]:numero 
	 * [3]:totalImporteGs 
	 * [4]:cliente
	 */
	public List<Object[]> getReembolsosChequesRechazadosPorCliente(long idCliente, Date desde, Date hasta) throws Exception {
		String desde_ = misc.dateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = misc.dateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select r.tipoMovimiento.descripcion, r.fechaEmision, r.numero, r.totalImporteGs, r.cliente.empresa.razonSocial"
				+ " from Recibo r where r.dbEstado != 'D'"
				+ " and (r.tipoMovimiento.sigla = '"
				+ Configuracion.SIGLA_TM_CANCELACION_CHEQ_RECHAZADO
				+ "') and r.estadoComprobante.sigla != '"
				+ Configuracion.SIGLA_ESTADO_COMPROBANTE_ANULADO
				+ "'";
				if (idCliente != 0) {
					query += " and r.cliente.id = " + idCliente;
				}
				query += " and (r.fechaEmision >= '"
				+ desde_
				+ "' and r.fechaEmision <= '" + hasta_ + "')";
		return this.hql(query);
	}
	
	/**
	 * @return los reembolsos de prestamos cc.. 
	 * [0]:concepto
	 * [1]:fecha 
	 * [2]:numero 
	 * [3]:totalImporteGs 
	 * [4]:cliente
	 */
	public List<Object[]> getReembolsosPrestamosCC(long idCliente, Date desde, Date hasta) throws Exception {
		String desde_ = misc.dateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = misc.dateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select r.tipoMovimiento.descripcion, r.fechaEmision, r.numero, r.totalImporteGs, r.cliente.empresa.razonSocial"
				+ " from Recibo r where r.dbEstado != 'D'"
				+ " and (r.tipoMovimiento.sigla = '"
				+ Configuracion.SIGLA_TM_REEMBOLSO_PRESTAMO
				+ "') and r.estadoComprobante.sigla != '"
				+ Configuracion.SIGLA_ESTADO_COMPROBANTE_ANULADO
				+ "'";
				if (idCliente != 0) {
					query += " and r.cliente.id = " + idCliente;
				}
				query += " and (r.fechaEmision >= '"
				+ desde_
				+ "' and r.fechaEmision <= '" + hasta_ + "')";
		return this.hql(query);
	}

	/**
	 * @return el costo del articulo segun la compra..
	 */
	public ArticuloCosto getCostoByCompra(long idFactura, long idArticulo)
			throws Exception {
		CompraLocalOrden ordCom = this.getOrdenCompraByFactura(idFactura);
		String query = "select c from ArticuloCosto c where c.articulo.id = "
				+ idArticulo
				+ " and c.idMovimiento = "
				+ ordCom.getId()
				+ " and c.tipoMovimiento.sigla = '"
				+ Configuracion.SIGLA_TM_ORDEN_COMPRA + "'";
		List<ArticuloCosto> out = this.hql(query);
		return out.size() > 0 ? out.get(0) : null;
	}
	
	/**
	 * @return el costo del articulo segun la importacion..
	 */
	public ArticuloCosto getCostoByImportacion(long idFactura, long idArticulo)
			throws Exception {
		ImportacionPedidoCompra ordCom = this.getImportacionByFactura(idFactura);
		String query = "select c from ArticuloCosto c where c.articulo.id = "
				+ idArticulo
				+ " and c.idMovimiento = "
				+ ordCom.getId()
				+ " and c.tipoMovimiento.sigla = '"
				+ Configuracion.SIGLA_TM_ORDEN_COMPRA_IMPOR + "'";
		List<ArticuloCosto> out = this.hql(query);
		return out.size() > 0 ? out.get(0) : null;
	}

	/**
	 * @return la orden de compra a partir de una factura..
	 */
	public CompraLocalOrden getOrdenCompraByFactura(long idFactura)
			throws Exception {
		String query = "select c from CompraLocalOrden c join c.facturas f where f in elements(c.facturas) and f.id = "
				+ idFactura;
		List<CompraLocalOrden> list = this.hql(query);
		return list.size() > 0 ? list.get(0) : null;
	}
	
	/**
	 * @return la orden de compra de importacion a partir de una factura..
	 */
	public ImportacionPedidoCompra getImportacionByFactura(long idFactura)
			throws Exception {
		String query = "select i from ImportacionPedidoCompra i join i.importacionFactura f where f in elements(i.importacionFactura) and f.id = "
				+ idFactura;
		List<ImportacionPedidoCompra> list = this.hql(query);
		return list.size() > 0 ? list.get(0) : null;
	}

	/**
	 * @return la orden de compra local..
	 */
	public CompraLocalOrden getOrdenCompra(long id) throws Exception {
		String query = "select c from CompraLocalOrden c where c.id = " + id;
		List<CompraLocalOrden> out = this.hql(query);
		return out.size() > 0 ? out.get(0) : null;
	}
	
	/**
	 * @return la factura de compra..
	 */
	public CompraLocalFactura getCompraLocalFactura(long id) throws Exception {
		String query = "select c from CompraLocalFactura c where c.id = " + id;
		List<CompraLocalFactura> out = this.hql(query);
		return out.size() > 0 ? out.get(0) : null;
	}
	
	/**
	 * @return la orden de compra importacion..
	 */
	public ImportacionPedidoCompra getOrdenCompraImportacion(long id) throws Exception {
		String query = "select i from ImportacionPedidoCompra i where i.id = " + id;
		List<ImportacionPedidoCompra> out = this.hql(query);
		return out.size() > 0 ? out.get(0) : null;
	}

	/**
	 * @return la transferencia segun el id..
	 */
	public Transferencia getTransferencia(long id) throws Exception {
		String query = "select t from Transferencia t where t.id = " + id;
		List<Transferencia> out = this.hql(query);
		return out.size() > 0 ? out.get(0) : null;
	}

	/**
	 * @return los datos de la ultima compra del articulo..
	 */
	public Object[] getUltimaCompra(long idArticulo) throws Exception {
		List<Object[]> transfs = this.getTransferenciasPorArticulo(idArticulo,
				null, null);
		List<Object[]> compras = this.getComprasLocalesPorArticulo(idArticulo,
				null, null);
		Object[] compra = compras.size() > 0 ? compras.get(0) : null;
		Object[] transf = transfs.size() > 0 ? transfs.get(0) : null;
		Date fcompra = (Date) (compra == null ? null : compra[1]);
		Date ftransf = (Date) (transf == null ? null : transf[1]);

		if (fcompra == null) {
			return transf;
		} else if (transf == null) {
			return compra;
		} else if (fcompra.compareTo(ftransf) > 0) {
			return compra;
		}
		return transf;
	}

	/**
	 * @return el precio de jedisoft..
	 */
	public ArticuloPrecioJedisoft getPrecioJedisoft(String codigoInterno)
			throws Exception {
		String query = "select a from ArticuloPrecioJedisoft a where a.codigoInterno = '"
				+ codigoInterno + "'";
		List<ArticuloPrecioJedisoft> list = this.hql(query);
		return list.size() > 0 ? list.get(0) : null;
	}
	
	/**
	 * @return los recibos segun parametros..
	 */
	public List<Recibo> getRecibos(String fecha, String numero, String razonSocial, String ruc, String caja, String cobrador) throws Exception {
		String query = "select r from Recibo r where (r.tipoMovimiento.sigla = '" + Configuracion.SIGLA_TM_RECIBO_COBRO 
				+ "' or r.tipoMovimiento.sigla = '" + Configuracion.SIGLA_TM_ANTICIPO_COBRO + "')"
				+ " and cast (r.fechaEmision as string) like '%" + fecha + "%'"
				+ " and r.numero like '%" + numero + "%'"
				+ " and upper(r.cliente.empresa.razonSocial) like '%" + razonSocial.toUpperCase() + "%'"
				+ " and r.cliente.empresa.ruc like '%" + ruc + "%'"
				+ " and r.numeroPlanilla like '%" + caja + "%'"
				+ " and upper(r.cobrador) like '%" + cobrador.toUpperCase() + "%'"
				+ " order by r.fechaEmision, r.numero";
		return this.hqlLimit(query, 200);
	}
	
	/**
	 * @return los reembolsos de prestamos segun parametros..
	 */
	public List<Recibo> getReembolsosPrestamosCC(String fecha, String numero, String razonSocial, String ruc, String caja, String cobrador) throws Exception {
		String query = "select r from Recibo r where (r.tipoMovimiento.sigla = '" + Configuracion.SIGLA_TM_REEMBOLSO_PRESTAMO + "')"
				+ " and cast (r.fechaEmision as string) like '%" + fecha + "%'"
				+ " and r.numero like '%" + numero + "%'"
				+ " and upper(r.cliente.empresa.razonSocial) like '%" + razonSocial.toUpperCase() + "%'"
				+ " and r.cliente.empresa.ruc like '%" + ruc + "%'"
				+ " and r.numeroPlanilla like '%" + caja + "%'"
				+ " and upper(r.cobrador) like '%" + cobrador.toUpperCase() + "%'"
				+ " order by r.fechaEmision, r.numero";
		return this.hqlLimit(query, 200);
	}
	
	/**
	 * @return los reembolsos de cheques rechazados segun parametros..
	 */
	public List<Recibo> getReembolsosChequesRechazados(String fecha, String numero, String razonSocial, String ruc, String caja, String cobrador) throws Exception {
		String query = "select r from Recibo r where (r.tipoMovimiento.sigla = '" + Configuracion.SIGLA_TM_CANCELACION_CHEQ_RECHAZADO + "')"
				+ " and cast (r.fechaEmision as string) like '%" + fecha + "%'"
				+ " and r.numero like '%" + numero + "%'"
				+ " and upper(r.cliente.empresa.razonSocial) like '%" + razonSocial.toUpperCase() + "%'"
				+ " and r.cliente.empresa.ruc like '%" + ruc + "%'"
				+ " and r.numeroPlanilla like '%" + caja + "%'"
				+ " and upper(r.cobrador) like '%" + cobrador.toUpperCase() + "%'"
				+ " order by r.fechaEmision, r.numero";
		return this.hqlLimit(query, 200);
	}

	/**
	 * @return los recibos segun parametros..
	 */
	public List<Recibo> getPagos(String fecha, String numero, String razonSocial, String ruc, String caja, String numeroRecibo) throws Exception {
		String query = "select r from Recibo r where (r.tipoMovimiento.sigla = '" + Configuracion.SIGLA_TM_RECIBO_PAGO 
				+ "' or r.tipoMovimiento.sigla = '" + Configuracion.SIGLA_TM_ANTICIPO_PAGO + "')"
				+ " and cast (r.fechaEmision as string) like '%" +fecha + "%'"
				+ " and r.numero like '%" + numero + "%'"
				+ " and upper(r.proveedor.empresa.razonSocial) like '%" + razonSocial.toUpperCase() + "%'"
				+ " and r.proveedor.empresa.ruc like '%" + ruc + "%'"
				+ " and r.numeroPlanilla like '%" + caja + "%'"
				+ " and r.numeroRecibo like '%" + numeroRecibo + "%'"
				+ " order by r.fechaEmision, r.numero";
		return this.hqlLimit(query, 200);
	}

	
	/**
	 * @return los pagos segun fecha
	 */
	public List<Recibo> getPagos(Date desde, Date hasta) throws Exception {

		String query = "select r from Recibo r where r.dbEstado != 'D' and (r.tipoMovimiento.sigla = ? or r.tipoMovimiento.sigla = ?)"
				+ " and r.fechaEmision between ? and ?"
				+ " order by r.fechaEmision, r.numero";

		List<Object> listParams = new ArrayList<Object>();
		listParams.add(Configuracion.SIGLA_TM_RECIBO_PAGO);
		listParams.add(Configuracion.SIGLA_TM_ANTICIPO_PAGO);
		listParams.add(desde);
		listParams.add(hasta);

		Object[] params = new Object[listParams.size()];
		for (int i = 0; i < listParams.size(); i++) {
			params[i] = listParams.get(i);
		}
		return this.hql(query, params);
	}

	/**
	 * @return las cobranzas segun fecha
	 */
	public List<Recibo> getCobranzas(Date desde, Date hasta, long idSucursal)
			throws Exception {

		String query = "select r from Recibo r where r.dbEstado != 'D' and (r.tipoMovimiento.sigla = ? or r.tipoMovimiento.sigla = ?)"
				+ " and (r.fechaEmision between ? and ?)";

		if (idSucursal != 0) {
			query += " and r.sucursal.id = ?";
		}
		query += " order by r.fechaEmision, r.numero";

		List<Object> listParams = new ArrayList<Object>();
		listParams.add(Configuracion.SIGLA_TM_RECIBO_COBRO);
		listParams.add(Configuracion.SIGLA_TM_ANTICIPO_COBRO);
		listParams.add(desde);
		listParams.add(hasta);
		if (idSucursal != 0) {
			listParams.add(idSucursal);
		}

		Object[] params = new Object[listParams.size()];
		for (int i = 0; i < listParams.size(); i++) {
			params[i] = listParams.get(i);
		}
		return this.hql(query, params);
	}
	
	/**
	 * @return los reembolsos de cheques rechazados..
	 */
	public List<Recibo> getReembolsosCheques(Date desde, Date hasta, long idSucursal)
			throws Exception {

		String query = "select r from Recibo r where r.dbEstado != 'D' and r.tipoMovimiento.sigla = ?"
				+ " and (r.fechaEmision between ? and ?)";

		if (idSucursal != 0) {
			query += " and r.sucursal.id = ?";
		}
		query += " order by r.fechaEmision, r.numero";

		List<Object> listParams = new ArrayList<Object>();
		listParams.add(Configuracion.SIGLA_TM_CANCELACION_CHEQ_RECHAZADO);
		listParams.add(desde);
		listParams.add(hasta);
		if (idSucursal != 0) {
			listParams.add(idSucursal);
		}

		Object[] params = new Object[listParams.size()];
		for (int i = 0; i < listParams.size(); i++) {
			params[i] = listParams.get(i);
		}
		return this.hql(query, params);
	}
	
	/**
	 * @return las cobranzas segun numero
	 */
	public List<Recibo> getCobranzas(long desde, long hasta, long idSucursal)
			throws Exception {

		String query = "select r from Recibo r where r.dbEstado != 'D' and (r.tipoMovimiento.sigla = ? or r.tipoMovimiento.sigla = ?)"
				+ " and (r.nro >= ? and r.nro <= ?)";

		if (idSucursal != 0) {
			query += " and r.sucursal.id = ?";
		}
		query += " order by r.nro";

		List<Object> listParams = new ArrayList<Object>();
		listParams.add(Configuracion.SIGLA_TM_RECIBO_COBRO);
		listParams.add(Configuracion.SIGLA_TM_ANTICIPO_COBRO);
		listParams.add(desde);
		listParams.add(hasta);
		if (idSucursal != 0) {
			listParams.add(idSucursal);
		}

		Object[] params = new Object[listParams.size()];
		for (int i = 0; i < listParams.size(); i++) {
			params[i] = listParams.get(i);
		}
		return this.hql(query, params);
	}

	/**
	 * @return las cobranzas por vendedor..
	 */
	public List<Object[]> getCobranzasPorVendedor(Date desde, Date hasta,
			long idVendedor, long idSucursal) throws Exception {
		List<Recibo> cobros = this.getCobranzas(desde, hasta, idSucursal);
		List<Object[]> out = new ArrayList<Object[]>();

		for (Recibo recibo : cobros) {
			for (ReciboDetalle det : recibo.getDetalles()) {
				long idVendedor_ = det.getMovimiento().getIdVendedor();
				if (idVendedor != 0) {
					if (idVendedor == idVendedor_)
						out.add(new Object[] { recibo,
								det.getMovimiento().getNroComprobante(),
								det.getMontoGs(), det, idVendedor_ });
				} else {
					out.add(new Object[] { recibo,
							det.getMovimiento().getNroComprobante(),
							det.getMontoGs(), det, idVendedor_ });
				}				
			}
		}
		return out;
	}

	/**
	 * @return las empresas segun parametros..
	 */
	public List<Empresa> getEmpresas(String ruc, String cedula,
			String razonSocial, String nombreFantasia) throws Exception {
		String query = "select e from Empresa e where lower(e.ruc) like '%"
				+ ruc.toLowerCase() + "%' and lower(e.ci) like '%"
				+ cedula.toLowerCase() + "%' and lower(e.razonSocial) like'%"
				+ razonSocial.toLowerCase() + "%' and lower(e.nombre) like '%"
				+ nombreFantasia.toLowerCase() + "%' order by e.razonSocial";
		return this.hqlLimit(query, 50);
	}
	
	/**
	 * @return las empresas segun parametros..
	 */
	public List<Empresa> getEmpresas(String siglaRubro) throws Exception {
		String query = "select e from Empresa e join e.rubroEmpresas r where r in elements(e.rubroEmpresas) and r.sigla = '"
				+ siglaRubro + "'";
		return this.hql(query);
	}

	/**
	 * @return los movimientos de cta cte. de una empresa
	 */
	public List<CtaCteEmpresaMovimiento> getCtaCteMovimientos(long idEmpresa,
			Date desde, Date hasta, String caracter, long idMoneda) throws Exception {
		String desde_ = misc.dateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = misc.dateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select c from CtaCteEmpresaMovimiento c where c.idEmpresa = "
				+ idEmpresa
				+ " and c.tipoCaracterMovimiento.sigla = '"
				+ caracter
				+ "'"
				+ " and c.anulado != 'TRUE'"
				+ " and c.fechaEmision >= '"
				+ desde_
				+ "'"
				+ " and c.fechaEmision <= '"
				+ hasta_
				+ "'"
				+ " and c.moneda.id = " + idMoneda
				+ " and c.tipoMovimiento.sigla != '" + Configuracion.SIGLA_TM_FAC_VENTA_CONTADO + "'"
				+ " order by c.fechaEmision, c.fechaVencimiento asc";
		List<CtaCteEmpresaMovimiento> list = this.hql(query);
		return list;
	}
	
	/**
	 * @return los movimientos de cta cte.
	 */
	public List<Object[]> getCtaCteMovimientos(Date desde, Date hasta, String caracter, long idMoneda, String siglaTm) throws Exception {
		String desde_ = misc.dateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = misc.dateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select c.id, c.idEmpresa, c.fechaVencimiento, c.importeOriginal from CtaCteEmpresaMovimiento c where"
				+ " c.tipoCaracterMovimiento.sigla = '"
				+ caracter
				+ "'"
				+ " and c.anulado != 'TRUE'"
				+ " and c.fechaEmision >= '"
				+ desde_
				+ "'"
				+ " and c.fechaEmision <= '"
				+ hasta_
				+ "'"
				+ " and c.moneda.id = " + idMoneda
				+ " and c.tipoMovimiento.sigla = '" + siglaTm + "'"
				+ " order by c.fechaEmision, c.fechaVencimiento asc";
		List<Object[]> list = this.hql(query);
		return list;
	}
	
	/**
	 * @return los movimientos de saldo a favor de cta cte. de una empresa
	 */
	public List<CtaCteEmpresaMovimiento> getCtaCteSaldosFavor(long idEmpresa) throws Exception {
		String query = "select c from CtaCteEmpresaMovimiento c where c.idEmpresa = "
				+ idEmpresa
				+ " and c.tipoMovimiento.sigla = '"
				+ Configuracion.SIGLA_TM_CTA_CTE_SALDO_FAVOR
				+ "'"
				+ " and c.anulado != 'TRUE'"
				+ " order by c.fechaEmision asc";
		List<CtaCteEmpresaMovimiento> list = this.hql(query);
		return list;
	}

	/**
	 * @return los funcionarios segun razonSocial..
	 */
	public List<Funcionario> getFuncionarios(String razonSocial)
			throws Exception {
		String query = "select f from Funcionario f where lower(f.empresa.razonSocial) like '%"
				+ razonSocial.toLowerCase() + "%'";
		return this.hql(query);
	}
	
	/**
	 * @return los funcionarios segun razonSocial..
	 */
	public Funcionario getFuncionario(String razonSocial)
			throws Exception {
		String query = "select f from Funcionario f where lower(f.empresa.razonSocial) = '"
				+ razonSocial.toLowerCase() + "'";
		List<Funcionario> list = this.hql(query);
		return list.size() > 0 ? list.get(0) : null;
	}
	
	/**
	 * @return los funcionarios tele-cobradores..
	 */
	public List<Funcionario> getTeleCobradores() throws Exception {
		String query = "select f from Funcionario f where f.telecobrador = 'TRUE' order by f.empresa.razonSocial";
		return this.hql(query);
	}
	
	/**
	 * @return los ids de funcionarios..
	 */
	public List<Object[]> getDatosFuncionarios() throws Exception {
		String query = "select f.id, f.empresa.razonSocial from Funcionario f";
		return this.hql(query);
	}

	/**
	 * @return el movimiento de cta cte a partir del nro de comp.
	 */
	public CtaCteEmpresaMovimiento getCtaCteMovimientoByNumero(String numero)
			throws Exception {
		String query = "select c from CtaCteEmpresaMovimiento c where c.nroComprobante = '"
				+ numero + "'";
		List<CtaCteEmpresaMovimiento> list = this.hql(query);
		return list.size() > 0 ? list.get(0) : null;
	}

	/**
	 * @return el movimiento de cta cte a partir del id del movimiento.
	 */
	public CtaCteEmpresaMovimiento getCtaCteMovimientoByIdMovimiento(
			long idMovimiento, String siglaTm) throws Exception {
		String query = "select c from CtaCteEmpresaMovimiento c where c.idMovimientoOriginal = "
				+ idMovimiento
				+ " and c.tipoMovimiento.sigla = '"
				+ siglaTm
				+ "'";
		List<CtaCteEmpresaMovimiento> list = this.hql(query);
		return list.size() > 0 ? list.get(0) : null;
	}
	
	/**
	 * @return los movimientos de cta cte a partir del id del movimiento.
	 */
	public List<CtaCteEmpresaMovimiento> getCtaCteMovimientosByIdMovimiento(
			long idMovimiento, String siglaTm) throws Exception {
		String query = "select c from CtaCteEmpresaMovimiento c where c.idMovimientoOriginal = "
				+ idMovimiento
				+ " and c.tipoMovimiento.sigla = '"
				+ siglaTm
				+ "'";
		List<CtaCteEmpresaMovimiento> list = this.hql(query);
		return list;
	}

	/**
	 * @return los cheques segun parametros..
	 */
	public List<BancoCheque> getCheques(String numero, String banco,
			String cuenta, String beneficiario, String numeroCaja, String numeroOrdenPago,
			boolean aVencer, boolean pendienteCobro, Date desde, Date hasta, String vencimiento) throws Exception {
		String query = "select c from BancoCheque c where cast (c.numero as string) like '%"
				+ numero.toLowerCase()
				+ "%'"
				+ " and c.numeroCaja like '%" + numeroCaja + "%'"
				+ " and c.numeroOrdenPago like '%" + numeroOrdenPago + "%'"
				+ " and lower(c.banco.banco.descripcion) like '%"
				+ banco.toLowerCase()
				+ "%'"
				+ " and lower(c.banco.nroCuenta) like '%"
				+ cuenta.toLowerCase()
				+ "%'"
				+ " and lower(c.beneficiario) like '%"
				+ beneficiario.toLowerCase() + "%'"
				+ " and cast (c.fechaVencimiento as string) like '%" + vencimiento + "%'";
		if (aVencer) {
			query += " and c.fechaVencimiento > '" + Utiles.getDateToString(new Date(), Utiles.DD_MM_YYYY_HH_MM_SS) + "'";
		}
		if (pendienteCobro) {
			query += " and c.cobrado = 'FALSE'";
		}
		if (desde != null && hasta != null) {
			String desde_ = misc.dateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
			String hasta_ = misc.dateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
			query += " and (c.fechaEmision >= '" + desde_ + "' and c.fechaEmision <= '" + hasta_ + "')";
		}
		query += " order by c.numero";
		return this.hqlLimit(query, 300);
	}
	
	/**
	 * @return los cheques segun parametros (fecha vencimiento)..
	 */
	public List<BancoCheque> getCheques_(String numero, String banco,
			String cuenta, String beneficiario, String numeroCaja, String numeroOrdenPago,
			boolean aVencer, boolean pendienteCobro, Date desde, Date hasta) throws Exception {
		String query = "select c from BancoCheque c where cast (c.numero as string) like '%"
				+ numero.toLowerCase()
				+ "%'"
				+ " and c.numeroCaja like '%" + numeroCaja + "%'"
				+ " and c.numeroOrdenPago like '%" + numeroOrdenPago + "%'"
				+ " and lower(c.banco.banco.descripcion) like '%"
				+ banco.toLowerCase()
				+ "%'"
				+ " and lower(c.banco.nroCuenta) like '%"
				+ cuenta.toLowerCase()
				+ "%'"
				+ " and lower(c.beneficiario) like '%"
				+ beneficiario.toLowerCase() + "%'";
		if (aVencer) {
			query += " and c.fechaVencimiento > '" + Utiles.getDateToString(new Date(), Utiles.DD_MM_YYYY_HH_MM_SS) + "'";
		}
		if (pendienteCobro) {
			query += " and c.cobrado = 'FALSE'";
		}
		if (desde != null && hasta != null) {
			String desde_ = misc.dateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
			String hasta_ = misc.dateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
			query += " and (c.fechaVencimiento >= '" + desde_ + "' and c.fechaVencimiento <= '" + hasta_ + "')";
		}
		query += " order by c.numero";
		return this.hqlLimit(query, 300);
	}
	
	/**
	 * @return los cheques segun parametros..
	 */
	public List<BancoCheque> getCheques(Date desde, Date hasta, long idBancoCta) throws Exception {
		String desde_ = Utiles.getDateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = Utiles.getDateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select c from BancoCheque c where"
				+ " (c.fechaEmision >= '" + desde_ + "' and c.fechaEmision <= '" + hasta_
				+ "')";
		if (idBancoCta != 0) {
			query += " and c.banco.id = " + idBancoCta;
		}
		query += " order by c.fechaEmision";
		return this.hql(query);
	}

	/**
	 * @return las chequeras..
	 */
	public List<Object[]> getChequeras() throws Exception {
		String query = "select bc.banco.descripcion, ch from BancoCta bc join bc.chequeras ch order by ch.numero";
		return this.hql(query);
	}

	/**
	 * @return el historial de venta del articulo segun el cliente.. [0]: Venta
	 *         [1]: VentaDetalle
	 */
	public List<Object[]> getHistorialVentaArticulo(long idArticulo,
			long idCliente) throws Exception {
		String query = "select v, d from Venta v join v.detalles d where v.cliente.id = "
				+ idCliente
				+ " and d.articulo.id = "
				+ idArticulo
				+ " and (v.tipoMovimiento.sigla = '"
				+ Configuracion.SIGLA_TM_FAC_VENTA_CONTADO
				+ "' or v.tipoMovimiento.sigla = '"
				+ Configuracion.SIGLA_TM_FAC_VENTA_CREDITO
				+ "' )"
				+ " order by v.fecha desc";
		return this.hqlLimit(query, 50);
	}

	/**
	 * @return las cobranzas detalladas segun fecha
	 */
	public List<Recibo> getCobranzasDetallado(Date desde, Date hasta,
			long idCliente, boolean todos) throws Exception {

		String query = "select r from Recibo r where r.dbEstado != 'D' and (r.tipoMovimiento.sigla = ? or r.tipoMovimiento.sigla = ?)"
				+ " and (r.fechaEmision between ? and ?)";

		if (todos == false) {
			query += " and r.cliente.id = " + idCliente;
		}
		query += " order by r.cliente.id";

		List<Object> listParams = new ArrayList<Object>();
		listParams.add(Configuracion.SIGLA_TM_RECIBO_COBRO);
		listParams.add(Configuracion.SIGLA_TM_ANTICIPO_COBRO);
		listParams.add(desde);
		listParams.add(hasta);

		Object[] params = new Object[listParams.size()];
		for (int i = 0; i < listParams.size(); i++) {
			params[i] = listParams.get(i);
		}
		return this.hql(query, params);
	}

	/**
	 * @return la lista de clientes segun razonsocial..
	 */
	public List<Cliente> getClientes(String razonSocial) throws Exception {
		String query = "select c from Cliente c where lower(c.empresa.razonSocial) like '%"
				+ razonSocial.toLowerCase() + "%' order by c.empresa.razonSocial";
		return this.hqlLimit(query, 30);
	}
	
	/**
	 * @return la lista de clientes segun razonsocial y ruc..
	 */
	public List<Cliente> getClientes(String razonSocial, String ruc) throws Exception {
		String query = "select c from Cliente c where lower(c.empresa.razonSocial) like '%"
				+ razonSocial.toLowerCase() + "%' "
				+ " and lower(c.empresa.ruc) like '%" + ruc.toLowerCase() + "%'"
				+ " order by c.empresa.razonSocial";
		return this.hqlLimit(query, 30);
	}
	
	/**
	 * @return la lista de clientes segun ruc..
	 */
	public List<Cliente> getClientesByRuc(String ruc) throws Exception {
		String query = "select c from Cliente c where c.empresa.ruc like '%"
				+ ruc + "%'";
		return this.hqlLimit(query, 100);
	}

	/**
	 * @return la lista de proveedores segun razonsocial..
	 */
	public List<Proveedor> getProveedores(String razonSocial) throws Exception {
		String query = "select p from Proveedor p where lower(p.empresa.razonSocial) like '%"
				+ razonSocial.toLowerCase() + "%'";
		return this.hqlLimit(query, 30);
	}
	
	/**
	 * @return la lista de proveedores segun razonsocial y ruc..
	 */
	public List<Proveedor> getProveedores(String razonSocial, String ruc) throws Exception {
		String query = "select p from Proveedor p where lower(p.empresa.razonSocial) like '%"
				+ razonSocial.toLowerCase() + "%' "
				+ " and lower(p.empresa.ruc) like '%" + ruc.toLowerCase() + "%'"
				+ " order by p.empresa.razonSocial";
		return this.hqlLimit(query, 30);
	}
	
	/**
	 * @return la lista de proveedores segun razonsocial..
	 */
	public List<Proveedor> getProveedoresExterior(String razonSocial) throws Exception {
		String query = "select p from Proveedor p where lower(p.empresa.razonSocial) like '%"
				+ razonSocial.toLowerCase() + "%'"
				+ " and p.tipoProveedor.sigla = '"+ Configuracion.SIGLA_PROVEEDOR_TIPO_EXTERIOR +"'";
		return this.hqlLimit(query, 30);
	}

	/**
	 * @return la lista de articulos segun codigo..
	 */
	public List<Articulo> getArticulos(String codigoInterno, int limit) throws Exception {
		String query = "select a from Articulo a where lower(a.codigoInterno) like '%"
				+ codigoInterno.toLowerCase() + "%' and a.codigoInterno not like '%@%'"
				+ " order by a.codigoInterno";
		return this.hqlLimit(query, limit);
	}
	
	/**
	 * @return la lista de articulos segun codigo..
	 */
	public List<Articulo> getArticulos_(String codigoInterno, int limit) throws Exception {
		String query = "select a from Articulo a where lower(a.codigoInterno) like '%"
				+ codigoInterno.toLowerCase() + "%'"
				+ " order by a.codigoInterno";
		return this.hqlLimit(query, limit);
	}

	/**
	 * @return los bancos..
	 */
	public List<BancoCta> getBancosCta() throws Exception {
		String query = "select b from BancoCta b";
		return this.hql(query);
	}

	/**
	 * @return los clientes con facturas vencidas ordenado alfabeticamente..
	 */
	public List<Empresa> getClientesConFacturasVencidas() throws Exception {
		String hoy = misc.dateToString(new Date(), Misc.YYYY_MM_DD)
				+ " 00:00:00";
		String query = "select c from CtaCteEmpresaMovimiento c where c.dbEstado != 'D'"
				+ " and c.anulado = 'FALSE' and c.tipoMovimiento.sigla = '"
				+ Configuracion.SIGLA_TM_FAC_VENTA_CREDITO
				+ "' and c.saldo > 0 and c.fechaVencimiento <= '" + hoy + "'";

		List<Empresa> out = new ArrayList<Empresa>();
		Map<Long, Empresa> emps = new HashMap<Long, Empresa>();
		List<CtaCteEmpresaMovimiento> movs = this.hql(query);

		for (CtaCteEmpresaMovimiento item : movs) {
			Empresa emp = this.getEmpresaById(item.getIdEmpresa());
			emps.put(emp.getId(), emp);
		}
		for (Long idEmp : emps.keySet()) {
			out.add(emps.get(idEmp));
		}
		Collections.sort(out, new Comparator<Empresa>() {
			@Override
			public int compare(Empresa o1, Empresa o2) {
				return o1.getRazonSocial().compareTo(o2.getRazonSocial());
			}
		});
		return out;
	}
	
	/**
	 * @return los clientes con facturas vencidas ordenado alfabeticamente..
	 */
	public List<Object[]> getClientesConFacturasVencidas_() throws Exception {
		String hoy = Utiles.getDateToString(new Date(), Misc.YYYY_MM_DD) + " 00:00:00";
		String query = "select c.id, c.idEmpresa from CtaCteEmpresaMovimiento c where c.dbEstado != 'D'"
				+ " and c.anulado = 'FALSE' and c.tipoMovimiento.sigla = '"
				+ Configuracion.SIGLA_TM_FAC_VENTA_CREDITO
				+ "' and c.saldo > 0 and c.fechaVencimiento <= '" + hoy + "'";

		List<Object[]> out = new ArrayList<Object[]>();
		Map<Long, Object[]> emps = new HashMap<Long, Object[]>();
		List<Object[]> movs = this.hql(query);

		for (Object[] item : movs) {
			Object[] emp = this.getEmpresa((long) item[1]);
			emps.put((long) item[1], emp);
		}
		for (Long idEmp : emps.keySet()) {
			out.add(emps.get(idEmp));
		}
		Collections.sort(out, new Comparator<Object[]>() {
			@Override
			public int compare(Object[] o1, Object[] o2) {
				String rs1 = (String) o1[2];
				String rs2 = (String) o2[2];
				return rs1.compareTo(rs2);
			}
		});
		return out;
	}

	/**
	 * @return las facturas vencidas segun el cliente..
	 */
	public List<CtaCteEmpresaMovimiento> getFacturasVencidas(long idEmpresa)
			throws Exception {
		String hoy = misc.dateToString(new Date(), Misc.YYYY_MM_DD)
				+ " 00:00:00";
		String query = "select c from CtaCteEmpresaMovimiento c where c.dbEstado != 'D'"
				+ " and c.anulado = 'FALSE' and c.tipoMovimiento.sigla = '"
				+ Configuracion.SIGLA_TM_FAC_VENTA_CREDITO
				+ "' and c.saldo > 0 and c.fechaVencimiento <= '"
				+ hoy
				+ "'"
				+ " and c.idEmpresa = " + idEmpresa;
		return this.hql(query);
	}

	/**
	 * @return las facturas vencidas..
	 */
	public List<CtaCteEmpresaMovimiento> getFacturasVencidas() throws Exception {
		String hoy = misc.dateToString(new Date(), Misc.YYYY_MM_DD)
				+ " 00:00:00";
		String query = "select c from CtaCteEmpresaMovimiento c where c.dbEstado != 'D'"
				+ " and c.anulado = 'FALSE' and c.tipoMovimiento.sigla = '"
				+ Configuracion.SIGLA_TM_FAC_VENTA_CREDITO
				+ "' and c.saldo > 0 and c.fechaVencimiento <= '"
				+ hoy
				+ "' order by c.idEmpresa";
		return this.hql(query);
	}
	
	/**
	 * @return las facturas credito con saldo a vencer..
	 */
	public List<CtaCteEmpresaMovimiento> getFacturasAvencer() throws Exception {
		String hoy = misc.dateToString(new Date(), Misc.YYYY_MM_DD)
				+ " 00:00:00";
		String query = "select c from CtaCteEmpresaMovimiento c where c.dbEstado != 'D'"
				+ " and c.anulado = 'FALSE' and c.tipoMovimiento.sigla = '"
				+ Configuracion.SIGLA_TM_FAC_VENTA_CREDITO
				+ "' and c.saldo > 0 and c.fechaVencimiento > '"
				+ hoy
				+ "' order by c.idEmpresa";
		return this.hql(query);
	}

	/**
	 * @return las llamdas de cobranza al cliente..
	 */
	public List<LlamadaCobranza> getLlamadasCobranza(long idEmpresa)
			throws Exception {
		String query = "select c from LlamadaCobranza c where c.empresa.id = "
				+ idEmpresa + "" + " order by c.fecha";
		return this.hql(query);
	}

	/**
	 * @return las tareas programadas pendientes de un cliente..
	 */
	public List<TareaProgramada> getTareasProgramadasPendientes(long idEmpresa)
			throws Exception {
		String query = "select t from TareaProgramada t where t.empresa.id = "
				+ idEmpresa + " and t.realizado = false " + " order by t.fecha";
		return this.hql(query);
	}
	
	/**
	 * @return las tareas programadas de un cliente..
	 */
	public List<TareaProgramada> getTareasProgramadas(long idEmpresa)
			throws Exception {
		String query = "select t from TareaProgramada t where t.empresa.id = "
				+ idEmpresa + "" + " order by t.fecha";
		return this.hql(query);
	}
	
	/**
	 * @return la tarea programada..
	 */
	public TareaProgramada getTareaProgramada(String observacion, Date fecha) throws Exception {
		String desde_ = Utiles.getDateToString(fecha, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = Utiles.getDateToString(fecha, Misc.YYYY_MM_DD) + " 23:00:00";
		String query = "select t from TareaProgramada t where t.observacion = '"
				+ observacion + "' "
						+ "and (t.fecha >= '" + desde_ + "' and t.fecha <= '" + hasta_ + "')";
		List<TareaProgramada> list = this.hql(query);
		return list.size() > 0 ? list.get(0) : null;
	}
	
	/**
	 * @return la tarea programada de procesos..
	 */
	public Tarea_Programada getTarea_Programada(String descripcion, Date fecha) throws Exception {
		String desde_ = Utiles.getDateToString(fecha, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = Utiles.getDateToString(fecha, Misc.YYYY_MM_DD) + " 23:00:00";
		String query = "select t from Tarea_Programada t where t.descripcion = '"
				+ descripcion + "' "
						+ "and (t.fecha >= '" + desde_ + "' and t.fecha <= '" + hasta_ + "')";
		List<Tarea_Programada> list = this.hql(query);
		return list.size() > 0 ? list.get(0) : null;
	}

	/**
	 * @return las cobranzas detalladas segun fecha
	 */
	public List<TareaProgramada> getTareasProgramadas(Date desde, Date hasta)
			throws Exception {
		String desde_ = misc.dateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = misc.dateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select t from TareaProgramada t where (t.fecha >= '"
				+ desde_ + "' and t.fecha <= '" + hasta_
				+ "') order by fecha desc";
		return this.hql(query);
	}

	/**
	 * @return los saldos de clientes/proveedores..
	 */
	public List<CtaCteEmpresaMovimiento> getMovimientosConSaldo(Date desde,
			Date hasta, String caracter, long idVendedor) throws Exception {
		String desde_ = Utiles.getDateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = Utiles.getDateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select c from CtaCteEmpresaMovimiento c where c.anulado = 'FALSE' and c.saldo > 0 and"
				+ " c.tipoCaracterMovimiento.sigla = '"
				+ caracter
				+ "'"
				+ " and (c.fechaEmision >= '"
				+ desde_
				+ "' and c.fechaEmision <= '" + hasta_ + "')";
		if (idVendedor != 0) {
			query += " and c.idVendedor = " + idVendedor;
		}
		query += " order by c.idEmpresa, c.fechaEmision";
		return this.hql(query);
	}
	
	/**
	 * @return los saldos de clientes/proveedores..
	 */
	public List<CtaCteEmpresaMovimiento> getMovimientosConSaldoSegunVencimiento(Date desde,
			Date hasta, String caracter, long idVendedor) throws Exception {
		String desde_ = Utiles.getDateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = Utiles.getDateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select c from CtaCteEmpresaMovimiento c where c.anulado = 'FALSE' and c.saldo > 0 and"
				+ " c.tipoCaracterMovimiento.sigla = '"
				+ caracter
				+ "'"
				+ " and (c.fechaVencimiento >= '"
				+ desde_
				+ "' and c.fechaVencimiento <= '" + hasta_ + "')";
		if (idVendedor != 0) {
			query += " and c.idVendedor = " + idVendedor;
		}
		query += " order by c.idEmpresa, c.fechaVencimiento";
		return this.hql(query);
	}
	
	/**
	 * @return los saldos de clientes/proveedores..
	 */
	public List<CtaCteEmpresaMovimiento> getMovimientosConSaldo(String caracter) throws Exception {
		String query = "select c from CtaCteEmpresaMovimiento c where c.anulado = 'FALSE' and c.saldo > 0 and"
				+ " c.tipoCaracterMovimiento.sigla = '" + caracter + "'";
		query += " order by c.idEmpresa, c.fechaEmision";
		return this.hql(query);
	}
	
	/**
	 * @return los saldos de clientes/proveedores..
	 */
	public List<CtaCteEmpresaMovimiento> getMovimientosConSaldo(String caracter, String siglaTm) throws Exception {
		String query = "select c from CtaCteEmpresaMovimiento c where c.anulado = 'FALSE' and c.saldo > 0"
				+ " and c.tipoCaracterMovimiento.sigla = '" + caracter + "'"
				+ " and c.tipoMovimiento.sigla = '" + siglaTm + "'";
		query += " order by c.idEmpresa, c.fechaEmision";
		return this.hql(query);
	}
	
	/**
	 * @return los saldos de clientes/proveedores..
	 */
	public List<CtaCteEmpresaMovimiento> getMovimientosConSaldo(Date desde,
			Date hasta, String caracter, long idVendedor, long idEmpresa, long idMoneda) throws Exception {
		String desde_ = Utiles.getDateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = Utiles.getDateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select c from CtaCteEmpresaMovimiento c where c.anulado = 'FALSE' and c.saldo > 0 and"
				+ " c.tipoCaracterMovimiento.sigla = '"
				+ caracter
				+ "'"
				+ " and c.moneda.id = " + idMoneda
				+ " and (c.fechaEmision >= '"
				+ desde_
				+ "' and c.fechaEmision <= '" + hasta_ + "')";
		if (idVendedor != 0) {
			query += " and c.idVendedor = " + idVendedor;
		}
		if (idEmpresa != 0) {
			query += " and c.idEmpresa = " + idEmpresa;
		}
		query += " order by c.idEmpresa, c.fechaEmision";
		return this.hql(query);
	}
	
	/**
	 * @return los saldos de clientes/proveedores..
	 */
	public List<CtaCteEmpresaMovimiento> getPagosAnticipadosConSaldo(Date desde,
			Date hasta, long idEmpresa, long idMoneda) throws Exception {
		String desde_ = Utiles.getDateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = Utiles.getDateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select c from CtaCteEmpresaMovimiento c where c.anulado = 'FALSE' and c.saldo != 0"
				+ " and c.moneda.id = " + idMoneda
				+ " and c.tipoMovimiento.sigla = '" + Configuracion.SIGLA_TM_ANTICIPO_PAGO + "'"
				+ " and (c.fechaEmision >= '"
				+ desde_
				+ "' and c.fechaEmision <= '" + hasta_ + "')";
		if (idEmpresa != 0) {
			query += " and c.idEmpresa = " + idEmpresa;
		}
		query += " order by c.idEmpresa, c.fechaEmision";
		return this.hql(query);
	}
	
	/**
	 * @return los rubroempresas segun idempresa..
	 */
	public List<Tipo> getRubroEmpresas(long idEmpresa) throws Exception {
		String query = "select r from Empresa e join e.rubroEmpresas r where e.id = " + idEmpresa;
		return this.hql(query);
	}
	
	/**
	 * @return los movimientos con saldo acumulado el saldo..
	 * [0]:idMovimientoOriginal [1]:tipoMovimiento.id
	 * [2]:nrocomprobante [3]:tipoMovimiento.descripcion
	 * [4]:telefono [5]:direccion
	 * [6]:emision [7]:vencimiento
	 * [8]:importe [9]:saldo acum
	 * [10]:razonSocial [11]:ruc
	 * [12]:saldo [13]:siglaTipomovimiento
	 * [14]:idempresa
	 * [15]:id
	 */
	public List<Object[]> getSaldosByVencimiento(Date desde, Date hasta, String caracter, long idVendedor, long idEmpresa, long idMoneda) throws Exception {
		String desde_ = Utiles.getDateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = Utiles.getDateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select c.idMovimientoOriginal, c.tipoMovimiento.id, c.nroComprobante, c.tipoMovimiento.descripcion, e.telefono_, e.direccion_, c.fechaEmision, c.fechaVencimiento, c.importeOriginal, "
				+ " (select sum(saldo) from CtaCteEmpresaMovimiento m where m.idMovimientoOriginal = c.idMovimientoOriginal and m.tipoMovimiento.id = c.tipoMovimiento.id ),"
				+ " e.razonSocial, e.ruc, c.saldo, c.tipoMovimiento.sigla, e.id, c.id"
				+ " from CtaCteEmpresaMovimiento c, Empresa e"
				+ " where c.idEmpresa = e.id and c.anulado = 'FALSE' and c.saldo > 0 and"
				+ " c.tipoCaracterMovimiento.sigla = '"
				+ caracter
				+ "'"
				+ " and c.moneda.id = " + idMoneda
				+ " and (c.fechaVencimiento >= '"
				+ desde_
				+ "' and c.fechaVencimiento <= '" + hasta_ + "')";
		if (idVendedor != 0) {
			query += " and c.idVendedor = " + idVendedor;
		}
		if (idEmpresa != 0) {
			query += " and c.idEmpresa = " + idEmpresa;
		}
		query += " order by c.fechaEmision";
		List<Object[]> saldos = this.hql(query);
		return saldos;
	}
	
	/**
	 * @return los movimientos con saldo acumulado el saldo..
	 * [0]:idMovimientoOriginal [1]:tipoMovimiento.id
	 * [2]:nrocomprobante [3]:tipoMovimiento.descripcion
	 * [4]:telefono [5]:direccion
	 * [6]:emision [7]:vencimiento
	 * [8]:importe [9]:saldo acum
	 * [10]:razonSocial [11]:ruc
	 * [12]:saldo [13]:siglaTipomovimiento
	 * [14]:idempresa
	 */
	public List<Object[]> getSaldos(Date desde, Date hasta, String caracter, long idVendedor, long idEmpresa, long idMoneda) throws Exception {
		String desde_ = Utiles.getDateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = Utiles.getDateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select c.idMovimientoOriginal, c.tipoMovimiento.id, c.nroComprobante, c.tipoMovimiento.descripcion, e.telefono_, e.direccion_, c.fechaEmision, c.fechaVencimiento, c.importeOriginal, "
				+ " (select sum(saldo) from CtaCteEmpresaMovimiento m where m.idMovimientoOriginal = c.idMovimientoOriginal and m.tipoMovimiento.id = c.tipoMovimiento.id ),"
				+ " e.razonSocial, e.ruc, c.saldo, c.tipoMovimiento.sigla, e.id"
				+ " from CtaCteEmpresaMovimiento c, Empresa e"
				+ " where c.idEmpresa = e.id and c.anulado = 'FALSE' and c.saldo > 0 and"
				+ " c.tipoCaracterMovimiento.sigla = '"
				+ caracter
				+ "'"
				+ " and c.moneda.id = " + idMoneda
				+ " and (c.fechaEmision >= '"
				+ desde_
				+ "' and c.fechaEmision <= '" + hasta_ + "')";
		if (idVendedor != 0) {
			query += " and c.idVendedor = " + idVendedor;
		}
		if (idEmpresa != 0) {
			query += " and c.idEmpresa = " + idEmpresa;
		}
		query += " order by c.fechaEmision";
		List<Object[]> saldos = this.hql(query);
		return saldos;
	}
	
	/**
	 * @return los movimientos con saldo acumulado el saldo..
	 * [0]:idMovimientoOriginal [1]:tipoMovimiento.id
	 * [2]:nrocomprobante [3]:tipoMovimiento.descripcion
	 * [4]:telefono [5]:direccion
	 * [6]:emision [7]:vencimiento
	 * [8]:importe [9]:saldo acum
	 * [10]:razonSocial [11]:ruc
	 * [12]:saldo [13]:siglaTipomovimiento
	 * [14]:idempresa
	 */
	public List<Object[]> getSaldos(Date desde, Date hasta, String caracter, long idVendedor, long idEmpresa, 
			long idMoneda, boolean incluirChequesrechazados, boolean incluirPrestamos) throws Exception {
		String desde_ = Utiles.getDateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = Utiles.getDateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select c.idMovimientoOriginal, c.tipoMovimiento.id, c.nroComprobante, c.tipoMovimiento.descripcion, e.telefono_, e.direccion_, c.fechaEmision, c.fechaVencimiento, c.importeOriginal, "
				+ " (select sum(saldo) from CtaCteEmpresaMovimiento m where m.idMovimientoOriginal = c.idMovimientoOriginal and m.tipoMovimiento.id = c.tipoMovimiento.id ),"
				+ " e.razonSocial, e.ruc, c.saldo, c.tipoMovimiento.sigla, e.id"
				+ " from CtaCteEmpresaMovimiento c, Empresa e"
				+ " where c.idEmpresa = e.id and c.anulado = 'FALSE' and c.saldo > 0 and"
				+ " c.tipoCaracterMovimiento.sigla = '"
				+ caracter
				+ "'"
				+ " and c.moneda.id = " + idMoneda;
				if (!incluirChequesrechazados) {
					query += " and c.tipoMovimiento.sigla != '" + Configuracion.SIGLA_TM_CHEQUE_RECHAZADO + "'";
				}
				if (!incluirPrestamos) {
					query += " and c.tipoMovimiento.sigla != '" + Configuracion.SIGLA_TM_PRESTAMO_CASA_CENTRAL + "'";
				}
				query += " and (c.fechaEmision >= '"
				+ desde_
				+ "' and c.fechaEmision <= '" + hasta_ + "')";
		if (idVendedor != 0) {
			query += " and c.idVendedor = " + idVendedor;
		}
		if (idEmpresa != 0) {
			query += " and c.idEmpresa = " + idEmpresa;
		}
		query += " order by c.fechaEmision";
		List<Object[]> saldos = this.hql(query);
		return saldos;
	}
	
	/**
	 * @return los movimientos con saldo acumulado el saldo..
	 * [0]:idMovimientoOriginal [1]:tipoMovimiento.id
	 * [2]:nrocomprobante [3]:tipoMovimiento.descripcion
	 * [4]:telefono [5]:direccion
	 * [6]:emision [7]:vencimiento
	 * [8]:importe [9]:saldo acum
	 * [10]:razonSocial [11]:ruc
	 * [12]:saldo [13]:siglaTipomovimiento
	 * [14]:idempresa
	 */
	public List<Object[]> getSaldos2016(Date desde, Date hasta, String caracter, long idVendedor, long idEmpresa, long idMoneda) throws Exception {
		String desde_ = Utiles.getDateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = Utiles.getDateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select c.idMovimientoOriginal, c.tipoMovimiento.id, c.nroComprobante, c.tipoMovimiento.descripcion, e.telefono_, e.direccion_, c.fechaEmision, c.fechaVencimiento, c.importeOriginal, "
				+ " (select sum(saldo) from CtaCteEmpresaMovimiento_2016 m where m.idMovimientoOriginal = c.idMovimientoOriginal and m.tipoMovimiento.id = c.tipoMovimiento.id ),"
				+ " e.razonSocial, e.ruc, c.saldo, c.tipoMovimiento.sigla, e.id"
				+ " from CtaCteEmpresaMovimiento_2016 c, Empresa e"
				+ " where c.idEmpresa = e.id and c.anulado = 'FALSE' and c.saldo > 0 and"
				+ " c.tipoCaracterMovimiento.sigla = '"
				+ caracter
				+ "'"
				+ " and c.moneda.id = " + idMoneda
				+ " and (c.fechaEmision >= '"
				+ desde_
				+ "' and c.fechaEmision <= '" + hasta_ + "')";
		if (idVendedor != 0) {
			query += " and c.idVendedor = " + idVendedor;
		}
		if (idEmpresa != 0) {
			query += " and c.idEmpresa = " + idEmpresa;
		}
		query += " order by c.fechaEmision";
		List<Object[]> saldos = this.hql(query);
		return saldos;
	}
	
	/**
	 * @return los movimientos con saldo acumulado el saldo..
	 * [0]:idMovimientoOriginal [1]:tipoMovimiento.id
	 * [2]:nrocomprobante [3]:tipoMovimiento.descripcion
	 * [4]:telefono [5]:direccion
	 * [6]:emision [7]:vencimiento
	 * [8]:importe [9]:saldo acum
	 * [10]:razonSocial [11]:ruc
	 * [12]:saldo [13]:siglaTipomovimiento
	 * [14]:idempresa
	 */
	public List<Object[]> getSaldos2017(Date desde, Date hasta, String caracter, long idVendedor, long idEmpresa, long idMoneda) throws Exception {
		String desde_ = Utiles.getDateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = Utiles.getDateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select c.idMovimientoOriginal, c.tipoMovimiento.id, c.nroComprobante, c.tipoMovimiento.descripcion, e.telefono_, e.direccion_, c.fechaEmision, c.fechaVencimiento, c.importeOriginal, "
				+ " (select sum(saldo) from CtaCteEmpresaMovimiento_2017 m where m.idMovimientoOriginal = c.idMovimientoOriginal and m.tipoMovimiento.id = c.tipoMovimiento.id ),"
				+ " e.razonSocial, e.ruc, c.saldo, c.tipoMovimiento.sigla, e.id"
				+ " from CtaCteEmpresaMovimiento_2017 c, Empresa e"
				+ " where c.idEmpresa = e.id and c.anulado = 'FALSE' and c.saldo > 0 and"
				+ " c.tipoCaracterMovimiento.sigla = '"
				+ caracter
				+ "'"
				+ " and c.moneda.id = " + idMoneda
				+ " and (c.fechaEmision >= '"
				+ desde_
				+ "' and c.fechaEmision <= '" + hasta_ + "')";
		if (idVendedor != 0) {
			query += " and c.idVendedor = " + idVendedor;
		}
		if (idEmpresa != 0) {
			query += " and c.idEmpresa = " + idEmpresa;
		}
		query += " order by c.fechaEmision";
		List<Object[]> saldos = this.hql(query);
		return saldos;
	}
	
	/**
	 * @return los saldos de clientes/proveedores periodo 2016..
	 */
	public List<CtaCteEmpresaMovimiento_2016> getMovimientosConSaldo2016(Date desde,
			Date hasta, String caracter, long idVendedor) throws Exception {
		String desde_ = Utiles.getDateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = Utiles.getDateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select c from CtaCteEmpresaMovimiento_2016 c where c.anulado = 'FALSE' and c.saldo > 0 and"
				+ " c.tipoCaracterMovimiento.sigla = '"
				+ caracter
				+ "'"
				+ " and (c.fechaEmision >= '"
				+ desde_
				+ "' and c.fechaEmision <= '" + hasta_ + "')";
		if (idVendedor != 0) {
			query += " and c.idVendedor = " + idVendedor;
		}
		query += " order by c.idEmpresa, c.fechaEmision";
		return this.hql(query);
	}

	/**
	 * @return las retenciones segun periodo..
	 */
	public List<Retencion> getRetenciones(int periodo, String tipoRetencion)
			throws Exception {
		String query = "select r from Retencion r where r.periodo = " + periodo
				+ " and r.tipoRetencion = '" + tipoRetencion + "'";
		return this.hql(query);
	}

	/**
	 * @return la orden de pago a partir de un nro de factura..
	 */
	public Recibo getOrdenPago(String numeroFactura) throws Exception {
		String query = "select op from Recibo op join op.detalles d where d.movimiento.nroComprobante like '"
				+ numeroFactura + "%'";
		List<Recibo> list = this.hql(query);
		return list.size() == 0 ? null : list.get(0);

	}

	/**
	 * @return los usuarios segun parametros..
	 */
	public List<Usuario> getUsuarios(String login, String nombre)
			throws Exception {
		String query = "select u from Usuario u where u.dbEstado != 'D' and cast (u.login as string) like '%"
				+ login.toLowerCase()
				+ "%'"
				+ " and lower(u.nombre) like '%"
				+ nombre.toLowerCase() + "%' order by u.nombre";
		return this.hqlLimit(query, 50);
	}

	/**
	 * @return los perfiles segun parametros..
	 */
	public List<Perfil> getPerfiles(String nombre, String descripcion)
			throws Exception {
		String query = "select p from Perfil p where p.dbEstado != 'D' and lower(p.nombre) like '%"
				+ nombre.toLowerCase()
				+ "%' and lower(p.descripcion) like '%"
				+ descripcion.toLowerCase() + "%' order by p.nombre";
		return this.hqlLimit(query, 50);
	}

	/**
	 * @return los modulos segun parametros..
	 */
	public List<Modulo> getModulos(String nombre, String descripcion)
			throws Exception {
		String query = "select m from Modulo m where m.dbEstado != 'D' and lower(m.nombre) like '%"
				+ nombre.toLowerCase()
				+ "%' and lower(m.descripcion) like '%"
				+ descripcion.toLowerCase() + "%' order by m.nombre";
		return this.hqlLimit(query, 50);
	}

	/**
	 * @return los formularios segun parametros..
	 */
	public List<Formulario> getFormularios(String label, String alias)
			throws Exception {
		String query = "select f from Formulario f where f.dbEstado != 'D' and lower(f.label) like '%"
				+ label.toLowerCase()
				+ "%' and lower(f.alias) like '%"
				+ alias.toLowerCase() + "%' order by f.label";
		return this.hqlLimit(query, 50);
	}

	/**
	 * @return las operaciones segun parametros..
	 */
	public List<Operacion> getOperaciones(String alias, String nombre,
			String descripcion) throws Exception {
		String query = "select o from Operacion o where o.dbEstado != 'D' and lower(o.alias) like '%"
				+ alias.toLowerCase()
				+ "%' and lower(o.nombre) like '%"
				+ nombre.toLowerCase()
				+ "%' and lower(o.descripcion) like '%"
				+ descripcion.toLowerCase() + "%' order by o.alias";
		return this.hqlLimit(query, 50);
	}

	/**
	 * obtener el usuario
	 */
	public Usuario getUsuario(long idUsuario) throws Exception {
		String query = "select u from Usuario u where u.dbEstado != 'D' and u.id = "
				+ idUsuario;
		return (Usuario) (this.hql(query)).get(0);
	}

	/**
	 * obtener las propiedades del usuario
	 */
	public UsuarioPropiedades getUsuarioPropiedades(long idUsuario) throws Exception {
		String query = "select up from UsuarioPropiedades up where up.dbEstado != 'D' and up.usuario.id = "
				+ idUsuario;
		return (UsuarioPropiedades) (this.hql(query)).get(0);
	}
	
	/**
	 * obtener depositos
	 */
	public List<Deposito> getDepositos() throws Exception {
		String query = "select d from Deposito d where d.dbEstado != 'D' order by d.orden";
		return (List<Deposito>) this.hql(query);
	}


	/**
	 * Obtener el perfil
	 */
	public Perfil getPerfil(long idPerfil) throws Exception {
		String query = "select p from Perfil p where p.dbEstado != 'D' and p.id = "
				+ idPerfil;
		return (Perfil) (this.hql(query)).get(0);
	}

	/**
	 * Obtener el permiso
	 */
	public Permiso getPermiso(long idPermiso) throws Exception {
		String query = "select p from Permiso p where p.dbEstado != 'D' and p.id = "
				+ idPermiso;
		return (Permiso) (this.hql(query)).get(0);
	}

	/**
	 * Obtener el modulo
	 */
	public Modulo getModulo(long idModulo) throws Exception {
		String query = "select m from Modulo m where m.dbEstado != 'D' and m.id = "
				+ idModulo;
		return (Modulo) (this.hql(query)).get(0);
	}

	/**
	 * Obtener la operacion
	 */
	public Operacion getOperacion(long idOperacion) throws Exception {
		String query = "select o from Operacion o where o.dbEstado != 'D' and o.id = "
				+ idOperacion;
		List<Operacion> list = this.hql(query);
		return list.size() > 0 ? list.get(0) : null;
	}

	/**
	 * obtener el formulario
	 */
	public Formulario getFormulario(long idFormulario) throws Exception {
		String query = "select f from Formulario f where f.dbEstado != 'D' and f.id = "
				+ idFormulario;
		return (Formulario) (this.hql(query)).get(0);
	}

	/**
	 * verifica si existe el login
	 */
	public boolean getExisteLogin(String login) throws Exception {
		boolean out = true;
		String query = "select u from Usuario u where u.login like '" + login
				+ "'";
		if (this.hql(query).size() > 0)
			out = true;
		else
			out = false;
		return out;
	}

	/**
	 * @return los tipos de movimiento que son comprobante legal..
	 */
	public List<TipoMovimiento> getTiposMovimientosLegales() throws Exception {
		String query = "select t from TipoMovimiento t where t.tipoComprobante.sigla = '"
				+ Configuracion.SIGLA_TIPO_COMPROBANTE_LEGAL + "'";
		return this.hql(query);
	}

	/**
	 * @return los ajustes de stock segun fecha
	 */
	public List<AjusteStock> getAjustesStock(Date desde, Date hasta,
			long idTipoMovimiento, long idDeposito) throws Exception {
		String query = "select a from AjusteStock a where a.dbEstado != 'D' and a.estadoComprobante.sigla = '"
				+ Configuracion.SIGLA_ESTADO_COMPROBANTE_CERRADO
				+ "' and a.tipoMovimiento.id = "
				+ idTipoMovimiento
				+ " and a.deposito.id = "
				+ idDeposito
				+ " and a.fecha between ? and ?" + " order by a.fecha";

		List<Object> listParams = new ArrayList<Object>();
		listParams.add(desde);
		listParams.add(hasta);

		Object[] params = new Object[listParams.size()];
		for (int i = 0; i < listParams.size(); i++) {
			params[i] = listParams.get(i);
		}
		return this.hql(query, params);
	}

	/**
	 * @return los articulos migrados..
	 */
	public List<ArticuloHistorialMigracion> getMigracion() throws Exception {
		String query = "select a from ArticuloHistorialMigracion a";
		return this.hql(query);
	}

	/**
	 * @return los articulos segun familia..
	 */
	public List<Articulo> getArticulos(long idFamilia, long idMarca, long idArticulo) throws Exception {
		String query = "select a from Articulo a where a.dbEstado != 'D' and a.codigoInterno not like '@%'";
		if (idFamilia != 0) {
			query += " and a.articuloFamilia.id = " + idFamilia;
		}
		if (idMarca != 0) {
			query += " and a.articuloMarca.id = " + idMarca;
		}
		if (idArticulo != 0) {
			query += " and a.id = " + idArticulo;
		}
		query += " order by a.codigoInterno";
		return this.hql(query);
	}

	/**
	 * @return la lista de planillas de caja segun numero..
	 */
	public List<CajaPeriodo> getCajaPlanillas(String nroPlanilla)
			throws Exception {
		if (nroPlanilla.isEmpty()) {
			return new ArrayList<CajaPeriodo>();
		}
		String query = "select c from CajaPeriodo c where lower(c.numero) like '%"
				+ nroPlanilla.toLowerCase() + "%'";
		return this.hqlLimit(query, 1);
	}
	
	/**
	 * @return la lista de planillas de caja segun numero y fecha..
	 */
	public List<CajaPeriodo> getCajaPlanillas(String nroPlanilla, String fecha) throws Exception {
		String query = "select c from CajaPeriodo c where"
				+ " lower(c.numero) like '%" + nroPlanilla.toLowerCase() + "%'"
				+ " and cast (c.cierre as string) like '%" + fecha + "%'";
		return this.hqlLimit(query, 10);
	}
	
	/**
	 * @return la lista de planillas de caja segun numero y fecha de apertura..
	 */
	public List<CajaPeriodo> getCajaPlanillas_(String nroPlanilla, String fechaApertura) throws Exception {
		String query = "select c from CajaPeriodo c where"
				+ " lower(c.numero) like '%" + nroPlanilla.toLowerCase() + "%'"
				+ " and cast (c.apertura as string) like '%" + fechaApertura + "%'";
		return this.hqlLimit(query, 10);
	}
	
	/**
	 * @return la lista de planillas de caja segun numero..
	 */
	public List<String> getCajaPlanillasNumeros(String nroPlanilla) throws Exception {
		if (nroPlanilla.isEmpty()) {
			return new ArrayList<String>();
		}
		String query = "select c.numero from CajaPeriodo c where lower(c.numero) like '%"
				+ nroPlanilla.toLowerCase() + "%'";
		return this.hqlLimit(query, 20);
	}
	
	/**
	 * @return las planillas de caja segun fecha..
	 */
	public List<CajaPeriodo> getCajaPlanillas(Date desde, Date hasta) throws Exception {
		String query = "select c from CajaPeriodo c where"
				+ " c.apertura between ? and ?" + " order by c.numero";

		List<Object> listParams = new ArrayList<Object>();
		listParams.add(desde);
		listParams.add(hasta);

		Object[] params = new Object[listParams.size()];
		for (int i = 0; i < listParams.size(); i++) {
			params[i] = listParams.get(i);
		}
		return this.hql(query, params);
	}

	/**
	 * @return las autorizaciones de precio segun fecha..
	 */
	public List<ArticuloPrecioMinimo> getAutorizacionesPrecios(Date desde,
			Date hasta) throws Exception {

		String query = "select a from ArticuloPrecioMinimo a where"
				+ " a.fecha between ? and ?" + " order by a.fecha";

		List<Object> listParams = new ArrayList<Object>();
		listParams.add(desde);
		listParams.add(hasta);

		Object[] params = new Object[listParams.size()];
		for (int i = 0; i < listParams.size(); i++) {
			params[i] = listParams.get(i);
		}
		return this.hql(query, params);
	}

	/**
	 * @return las ventas donde esta contenida el articulo de la familia..
	 *         [0]:concepto [1]:fecha [2]:numero [3]:cantidad [4]:precio [5]:cod
	 *         art [6]:desc art [7]: id art
	 */
	public List<Object[]> getVentasPorFamilia(long idFamilia, Date desde,
			Date hasta) throws Exception {
		String desde_ = misc.dateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = misc.dateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select v.tipoMovimiento.descripcion, v.fecha, v.numero, d.cantidad, d.precioGs, d.articulo.codigoInterno,"
				+ " d.articulo.descripcion, d.articulo.id from Venta v join v.detalles d where"
				+ " (v.tipoMovimiento.sigla = '"
				+ Configuracion.SIGLA_TM_FAC_VENTA_CONTADO
				+ "' or "
				+ " v.tipoMovimiento.sigla = '"
				+ Configuracion.SIGLA_TM_FAC_VENTA_CREDITO
				+ "') and v.dbEstado != 'D' and v.estadoComprobante IS NULL"
				+ " and (v.fecha >= '"
				+ desde_
				+ "' and v.fecha <= '"
				+ hasta_
				+ "')"; 
				if (idFamilia != 0) {
					query += " and d.articulo.articuloFamilia.id = " + idFamilia;
				}
				query += " order by d.articulo.id, v.fecha desc";

		return this.hql(query);
	}

	/**
	 * @return las notas de credito donde esta contenida el articulo de la
	 *         familia.. [0]:concepto [1]:fecha [2]:numero [3]:cantidad
	 *         [4]:precio [5]:cod art [6]:desc art [7]: id art
	 */
	public List<Object[]> getNotasDeCreditoPorFamilia(long idFamilia,
			Date desde, Date hasta) throws Exception {
		String desde_ = misc.dateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = misc.dateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select n.tipoMovimiento.descripcion, n.fechaEmision, n.numero, d.cantidad, d.montoGs, d.articulo.codigoInterno,"
				+ " d.articulo.descripcion, d.articulo.id from NotaCredito n join n.detalles d where d.articulo.articuloFamilia.id = "
				+ idFamilia
				+ " and (n.tipoMovimiento.sigla = '"
				+ Configuracion.SIGLA_TM_NOTA_CREDITO_VENTA
				+ "')"
				+ " and n.dbEstado != 'D' and n.estadoComprobante.id != 218"
				+ " and (n.fechaEmision >= '"
				+ desde_
				+ "' and n.fechaEmision <= '"
				+ hasta_
				+ "')"
				+ " order by d.articulo.id, n.fechaEmision desc";

		return this.hql(query);
	}

	/**
	 * @return las ventas segun fecha
	 */
	public List<Venta> getVentasAnuladas(Date desde, Date hasta)
			throws Exception {
		String query = "select v from Venta v where v.dbEstado != 'D'"
				+ " and v.estadoComprobante.sigla = '"
				+ Configuracion.SIGLA_ESTADO_COMPROBANTE_ANULADO
				+ "'"
				+ " and (v.tipoMovimiento.sigla = ? or v.tipoMovimiento.sigla = ?)"
				+ " and v.fecha between ? and ?" + " order by v.numero";

		List<Object> listParams = new ArrayList<Object>();
		listParams.add(Configuracion.SIGLA_TM_FAC_VENTA_CONTADO);
		listParams.add(Configuracion.SIGLA_TM_FAC_VENTA_CREDITO);
		listParams.add(desde);
		listParams.add(hasta);

		Object[] params = new Object[listParams.size()];
		for (int i = 0; i < listParams.size(); i++) {
			params[i] = listParams.get(i);
		}

		return this.hql(query, params);
	}

	/**
	 * @return los cheques de terceros..
	 */
	public List<BancoChequeTercero> getChequesTercero(Date desde, Date hasta,
			Tipo banco, long idCliente) throws Exception {
		String query = "select c from BancoChequeTercero c where c.dbEstado != 'D'"
				+ " and c.anulado = 'FALSE'" + " and c.fecha between ? and ?";

		if (banco != null) {
			query += " and c.banco.id = ?";
		}
		if (idCliente != 0) {
			query += " and c.cliente.id = ?";
		}
		query += " order by c.fecha";

		List<Object> listParams = new ArrayList<Object>();
		listParams.add(desde);
		listParams.add(hasta);
		if (banco != null) {
			listParams.add(banco.getId());
		}
		if (idCliente != 0) {
			listParams.add(idCliente);
		}
		Object[] params = new Object[listParams.size()];
		for (int i = 0; i < listParams.size(); i++) {
			params[i] = listParams.get(i);
		}
		return this.hql(query, params);
	}

	/**
	 * @return los cheques de terceros..
	 */
	public List<BancoChequeTercero> getChequesTercero(String numero)
			throws Exception {
		String query = "select c from BancoChequeTercero c where c.dbEstado != 'D'"
				+ " and c.anulado = 'FALSE'"
				+ " and upper(c.numero) like '%"
				+ numero.toUpperCase() + "%'";
		return this.hqlLimit(query, 50);
	}
	
	/**
	 * @return los cheques diferidos a depositar..
	 */
	public List<BancoChequeTercero> getChequesTerceroDiferidosAdepositar(
			Date vencimiento) throws Exception {
		String vto_ = Utiles.getDateToString(vencimiento, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select c from BancoChequeTercero c where c.dbEstado != 'D'"
				+ " and c.anulado = 'FALSE'"
				+ " and c.depositado = 'FALSE'"
				+ " and c.descontado = 'FALSE'"
				+ " and c.rechazado = 'FALSE'"
				+ " and c.fecha <= '" + vto_ + "'";
				query += " order by c.fecha";
		return this.hql(query);
	}
	
	/**
	 * @return los cheques de terceros..
	 */
	public List<BancoChequeTercero> getChequesTercero(String planilla,
			String recibo, String venta, String reembolso, String deposito, String descuento,
			String razonSocial, String ruc, String banco, String numero,
			String librador, String vendedor, String depositado, String descontado, 
			String rechazado, String rechazoInterno, Date desde, Date hasta, Date emisionDesde, Date emisionHasta, 
			String fecha, String importeGs, boolean limit)
			throws Exception {
		String query = "select c from BancoChequeTercero c where c.dbEstado != 'D'"
				+ " and c.anulado = 'FALSE'"
				+ " and upper(c.numeroPlanilla) like '%" + planilla.toUpperCase() + "%'"
				+ " and upper(c.numeroRecibo) like '%" + recibo.toUpperCase() + "%'"
				+ " and upper(c.numeroVenta) like '%" + venta.toUpperCase() + "%'"
				+ " and upper(c.numeroReembolso) like '%" + reembolso.toUpperCase() + "%'"
				+ " and upper(c.numeroDeposito) like '%" + deposito.toUpperCase() + "%'"
				+ " and upper(c.numeroDescuento) like '%" + descuento.toUpperCase() + "%'"
				+ " and upper(c.cliente.empresa.razonSocial) like '%" + razonSocial.toUpperCase() + "%'"
				+ " and upper(c.cliente.empresa.ruc) like '%" + ruc.toUpperCase() + "%'"
				+ " and upper(c.banco.descripcion) like '%" + banco.toUpperCase() + "%'"
				+ " and upper(c.numero) like '%" + numero.toUpperCase() + "%'"
				+ " and cast (c.fecha as string) like '%" + fecha + "%'"
				+ " and cast (c.monto as string) like '%" + importeGs + "%'"
				+ " and upper(c.librado) like '%" + librador.toUpperCase() + "%'"
				+ " and upper(c.vendedor) like '%" + vendedor.toUpperCase() + "%'";
				if (depositado != null) {
					query += " and c.depositado = '" + depositado + "'";
				}
				if (descontado != null) {
					query += " and c.descontado = '" + descontado + "'";
				}
				if (rechazado != null) {
					query += " and c.rechazado = '" + rechazado + "'";
				}
				if (rechazoInterno != null) {
					query += " and c.rechazoInterno = '" + rechazoInterno + "'";
				}
				if (desde != null && hasta != null) {
					String desde_ = Utiles.getDateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
					String hasta_ = Utiles.getDateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
					query += " and (c.fecha >= '" + desde_ + "' and c.fecha <= '" + hasta_ + "')";
				}
				if (emisionDesde != null && emisionHasta != null) {
					String emiDesde_ = Utiles.getDateToString(emisionDesde, Misc.YYYY_MM_DD) + " 00:00:00";
					String emiHasta_ = Utiles.getDateToString(emisionHasta, Misc.YYYY_MM_DD) + " 23:59:00";
					query += " and (c.emision >= '" + emiDesde_ + "' and c.emision <= '" + emiHasta_ + "')";
				}
				query += " order by c.fecha, c.emision";
		return limit ? this.hqlLimit(query, 300) : this.hql(query);
	}
	
	/**
	 * @return los cheques de terceros..
	 * [0]:id
	 * [1]:numero
	 * [2]:emision
	 * [3]:vencimiento
	 * [4]:importe
	 */
	public List<Object[]> getChequesTercero_(String planilla,
			String recibo, String venta, String reembolso, String deposito, String descuento,
			String razonSocial, String ruc, String banco, String numero,
			String librador, String vendedor, String depositado, String descontado, 
			String rechazado, Date desde, Date hasta, Date emisionDesde, Date emisionHasta, 
			String fecha, String importeGs, boolean limit)
			throws Exception {
		String query = "select c.id, c.numero, c.emision, c.fecha, c.monto from BancoChequeTercero c where c.dbEstado != 'D'"
				+ " and c.anulado = 'FALSE'"
				+ " and upper(c.numeroPlanilla) like '%" + planilla.toUpperCase() + "%'"
				+ " and upper(c.numeroRecibo) like '%" + recibo.toUpperCase() + "%'"
				+ " and upper(c.numeroVenta) like '%" + venta.toUpperCase() + "%'"
				+ " and upper(c.numeroReembolso) like '%" + reembolso.toUpperCase() + "%'"
				+ " and upper(c.numeroDeposito) like '%" + deposito.toUpperCase() + "%'"
				+ " and upper(c.numeroDescuento) like '%" + descuento.toUpperCase() + "%'"
				+ " and upper(c.cliente.empresa.razonSocial) like '%" + razonSocial.toUpperCase() + "%'"
				+ " and upper(c.cliente.empresa.ruc) like '%" + ruc.toUpperCase() + "%'"
				+ " and upper(c.banco.descripcion) like '%" + banco.toUpperCase() + "%'"
				+ " and upper(c.numero) like '%" + numero.toUpperCase() + "%'"
				+ " and cast (c.fecha as string) like '%" + fecha + "%'"
				+ " and cast (c.monto as string) like '%" + importeGs + "%'"
				+ " and upper(c.librado) like '%" + librador.toUpperCase() + "%'"
				+ " and upper(c.vendedor) like '%" + vendedor.toUpperCase() + "%'";
				if (depositado != null) {
					query += " and c.depositado = '" + depositado + "'";
				}
				if (descontado != null) {
					query += " and c.descontado = '" + descontado + "'";
				}
				if (rechazado != null) {
					query += " and c.rechazado = '" + rechazado + "'";
				}
				if (desde != null && hasta != null) {
					String desde_ = Utiles.getDateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
					String hasta_ = Utiles.getDateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
					query += " and (c.fecha >= '" + desde_ + "' and c.fecha <= '" + hasta_ + "')";
				}
				if (emisionDesde != null && emisionHasta != null) {
					String emiDesde_ = Utiles.getDateToString(emisionDesde, Misc.YYYY_MM_DD) + " 00:00:00";
					String emiHasta_ = Utiles.getDateToString(emisionHasta, Misc.YYYY_MM_DD) + " 23:59:00";
					query += " and (c.emision >= '" + emiDesde_ + "' and c.emision <= '" + emiHasta_ + "')";
				}
				query += " order by c.fecha, c.emision";
		return limit ? this.hqlLimit(query, 300) : this.hql(query);
	}
	
	/**
	 * @return los cheques de terceros..
	 */
	public List<BancoChequeTercero> getChequesTercero(String razonSocial, String ruc, 
			String banco, String numero, String librador, boolean descontado, boolean depositado)
			throws Exception {
		String query = "select c from BancoChequeTercero c where c.dbEstado != 'D'"
				+ " and c.anulado = 'FALSE'"
				+ " and upper(c.cliente.empresa.razonSocial) like '%" + razonSocial.toUpperCase() + "%'"
				+ " and upper(c.cliente.empresa.ruc) like '%" + ruc.toUpperCase() + "%'"
				+ " and upper(c.banco.descripcion) like '%" + banco.toUpperCase() + "%'"
				+ " and upper(c.numero) like '%" + numero.toUpperCase() + "%'"
				+ " and upper(c.librado) like '%" + librador.toUpperCase() + "%'"
				+ " and c.descontado = '" + descontado + "'"
				+ " and c.depositado = '" + depositado + "'"
				+ " order by c.fecha";
		return this.hqlLimit(query, 50);
	}

	/**
	 * @return un recibo a partir de una forma de pago..
	 */
	public Recibo getRecibo(long idFormaPago) throws Exception {
		String query = "select r from Recibo r join r.formasPago f where f in elements(r.formasPago) and f.id = "
				+ idFormaPago;
		List<Recibo> list = this.hql(query);
		return list.size() == 0 ? null : list.get(0);
	}

	/**
	 * @return una venta a partir de una forma de pago..
	 */
	public Venta getVenta(long idFormaPago) throws Exception {
		String query = "select v from Venta v join v.formasPago f where f in elements(v.formasPago) and f.id = "
				+ idFormaPago;
		List<Venta> list = this.hql(query);
		return list.size() == 0 ? null : list.get(0);
	}

	/**
	 * @return los usuarios segun estado..
	 */
	public List<Usuario> getUsuarios(boolean activos, boolean inactivos)
			throws Exception {
		String query = "";
		List<Usuario> out = new ArrayList<Usuario>();
		if (activos && inactivos) {
			query = "select u from Usuario u where u.dbEstado != 'D' order by u.nombre";
			out = this.hqlLimit(query, 50);
		} else if (activos && !inactivos) {
			query = "select u from Usuario u where u.dbEstado != 'D' and u.activo = 'TRUE' order by u.nombre";
			out = this.hqlLimit(query, 50);
		} else if (!activos && inactivos) {
			query = "select u from Usuario u where u.dbEstado != 'D' and u.activo = 'FALSE' order by u.nombre";
			out = this.hqlLimit(query, 50);
		}
		return out;
	}
	
	/**
	 * @return la lista de precio detalle..
	 */
	public ArticuloListaPrecioDetalle getListaPrecioDetalle(long idListaPrecio,
			String codArticulo) throws Exception {
		String query = "Select l from ArticuloListaPrecio l where l.id = "
				+ idListaPrecio;
		List<ArticuloListaPrecio> list = this.hql(query);
		ArticuloListaPrecio listaPrecio = list.size() == 0 ? null : list.get(0);
		if (listaPrecio != null) {
			for (ArticuloListaPrecioDetalle item : listaPrecio.getDetalles()) {
				if (item.getArticulo().getCodigoInterno().equals(codArticulo)) {
					return item;
				}
			}
		}
		return null;
	}
	
	/**
	 * @return el cliente a partir de la empresa..
	 */
	public Cliente getClienteByEmpresa(long idEmpresa) throws Exception {
		String query = "select c from Cliente c where c.empresa.id = " + idEmpresa;
		List<Cliente> list = this.hql(query);
		return list.size() == 0 ? null : list.get(0);
	}
	
	/**
	 * @return el proveedor a partir de la empresa..
	 */
	public Proveedor getProveedorByEmpresa(long idEmpresa) throws Exception {
		String query = "select p from Proveedor p where p.empresa.id = " + idEmpresa;
		List<Proveedor> list = this.hql(query);
		return list.size() == 0 ? null : list.get(0);
	}
	
	/**
	 * @return true si es una operacion habilitada..
	 */
	public boolean isOperacionHabilitada(String user, String operacion) throws Exception {
		Usuario usuario = this.getUsuario(user);
		for (Perfil perfil : usuario.getPerfiles()) {
			for (Permiso permiso : perfil.getPermisos()) {
				if (permiso.getOperacion().getAlias().equals(operacion)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * @return el costo promedio del articulo..
	 */
	public double getCostoPromedio(long idArticulo) throws Exception {
		Articulo art = this.getArticuloById(idArticulo);
		String query = "select a from ArticuloCosto a where a.articulo.id = " + idArticulo;
		List<ArticuloCosto> list = this.hql(query);
		if (list.size() > 0) {
			double out = 0;
			for (ArticuloCosto costo : list) {
				out += costo.getCostoFinalGs();
			}
			return Utiles.getRedondeo(out / list.size());
		
		} else {
			return art.getCostoGs();
		}
	}
	
	/**
	 * @return el costo del articulo..
	 */
	public double getCostoGs(long idarticulo) throws Exception {
		Articulo art = this.getArticuloById(idarticulo);
		return art.getCostoGs();
	}
	
	/**
	 * @return la nota de credito segun la venta aplicada..
	 */
	public NotaCredito getNotaCreditoVenta(long idVenta) throws Exception {
		String query = "select n from NotaCredito n join n.detalles d"
				+ " where n.dbEstado != 'D' and n.estadoComprobante != 218 and d.venta.id = " + idVenta;
		List<NotaCredito> list = this.hql(query);
		return list.size() > 0 ? list.get(0) : null;
	}	
	
	/**
	 * @return el cliente by el id empresa..
	 */
	public Cliente getClienteByIdEmpresa(long idEmpresa) throws Exception {
		String query = "select c from Cliente c where c.empresa.id = " + idEmpresa;
		List<Cliente> list = this.hql(query);
		return list.size() > 0 ? list.get(0) : null;
	}
	
	/**
	 * @return los clientes credito..
	 */
	public List<Cliente> getClientesCredito() throws Exception {
		String query = "select c from Cliente c where c.ventaCredito = 'true' ";
		return this.hql(query);
	}
	
	/**
	 * @return los clientes segun cobrador..
	 */
	public List<Cliente> getClientesByCobrador(long idCobrador) throws Exception {
		String query = "select c from Cliente c where c.cobrador.id = " + idCobrador;
		return this.hql(query);
	}
	
	/**
	 * @return los clientes segun rubro..
	 */
	public List<Object[]> getClientesByRubro(long idRubro) throws Exception {
		String query = "select c.empresa.razonSocial, c.empresa.ruc, c.empresa.direccion_, c.empresa.telefono_"
				+ " from Cliente c join c.empresa.rubroEmpresas r";
		if (idRubro != 0) {
			query += " where r.id = " + idRubro;
		}
		query += " order by c.empresa.razonSocial";
		return this.hql(query);
	}
	
	/**
	 * @return los cheques de terceros rechazados segun el cliente..
	 */
	public List<BancoChequeTercero> getChequesRechazados(long idCliente)
			throws Exception {
		String query = "select c from BancoChequeTercero c where c.dbEstado != 'D'"
				+ " and c.anulado = 'FALSE'"
				+ " and c.cliente.id = " + idCliente
				+ " and c.rechazado = 'TRUE'";
		return this.hql(query);
	}
	
	/**
	 * @return lista de recibos a partir del numero..
	 */
	public List<Recibo> getRecibos(String numero) throws Exception {
		String query = "select r from Recibo r where r.numero like '%" + numero + "%'"
				+ "	order by r.numero";
		List<Recibo> list = this.hqlLimit(query, 50);
		return list;
	}
	
	/**
	 * @return el recibo a partir del numero..
	 */
	public Recibo getRecibo(String numero) throws Exception {
		String query = "select r from Recibo r where r.numero = '" + numero + "'";
		List<Recibo> list = this.hql(query);
		return list.size() > 0 ? list.get(0) : null;
	}
	
	/**
	 * @return el stock total del articulo..
	 */
	public long getStock(long idarticulo) throws Exception {
		long out = 0;
		List<Deposito> deps = this.getDepositos();
		for (long i = 2; i <= deps.size(); i++) {
			ArticuloDeposito adp = this.getArticuloDeposito(idarticulo, i);
			if (adp != null) {
				out += adp.getStock();
			}			
		}
		return out;
	}
	
	/**
	 * @return el stock y el costo del articulo..
	 */
	public Object[] getStockCosto(long idarticulo) throws Exception {
		long stock = 0;
		Articulo art = this.getArticuloById(idarticulo);
		List<Deposito> deps = this.getDepositos();
		for (long i = 2; i <= deps.size(); i++) {
			ArticuloDeposito adp = this.getArticuloDeposito(idarticulo, i);
			if (adp != null) {
				stock += adp.getStock();				
			}			
		}
		return new Object[]{ stock, art.getCostoGs() };
	}
	
	/**
	 * @return el saldo en cta cte..
	 */
	public double getSaldoCtaCte(long idEmpresa) throws Exception {
		return this.getSaldoCtaCte(idEmpresa, Configuracion.SIGLA_CTA_CTE_CARACTER_MOV_CLIENTE);
	}
	
	/**
	 * @return el saldo en cta cte..
	 */
	public double getSaldoCtaCte(long idEmpresa, String caracter) throws Exception {
		double out = 0;
		String query = "select e from CtaCteEmpresaMovimiento e where e.anulado = 'FALSE'"
				+ " and e.tipoCaracterMovimiento.sigla = '" + caracter + "'"
				+ " and e.saldo > 0 and e.idEmpresa = " + idEmpresa;
		List<CtaCteEmpresaMovimiento> list = this.hql(query);
		for (CtaCteEmpresaMovimiento item : list) {
			out += item.getSaldoFinal();
		}
		return out;
	}
	
	/**
	 * @return el saldo en cta cte..
	 */
	public List<CtaCteEmpresaMovimiento> getSaldosCtaCte(long idEmpresa, String caracter) throws Exception {
		String query = "select e from CtaCteEmpresaMovimiento e where e.anulado = 'FALSE'"
				+ " and e.tipoCaracterMovimiento.sigla = '" + caracter + "'"
				+ " and e.saldo > 0 and e.idEmpresa = " + idEmpresa;
		List<CtaCteEmpresaMovimiento> list = this.hql(query);
		return list;
	}
	
	/**
	 * @return el banco deposito segun numero..
	 */
	public BancoBoletaDeposito getBancoDeposito(String numero) throws Exception {
		String query = "select b from BancoBoletaDeposito b where b.numeroBoleta = '" + numero + "'";
		List<BancoBoletaDeposito> list = this.hql(query);
		return list.size() > 0 ? list.get(0) : null;
	}
	
	/**
	 * @return los depositos segun numero..
	 */
	public List<BancoBoletaDeposito> getBancoDepositos(String numero) throws Exception {
		String query = "select b from BancoBoletaDeposito b where b.numeroBoleta like '%" + numero + "%'";
		List<BancoBoletaDeposito> list = this.hqlLimit(query, 50);
		return list;
	}
	
	/**
	 * @return el banco descuento segun numero..
	 */
	public BancoDescuentoCheque getBancoDescuento(String numero) throws Exception {
		String query = "select b from BancoDescuentoCheque b where b.id = " + numero + "";
		List<BancoDescuentoCheque> list = this.hql(query);
		return list.size() > 0 ? list.get(0) : null;
	}
	
	/**
	 * @return el banco descuento segun el cheque..
	 */
	public Object[] getBancoDescuento(long idCheque) throws Exception {
		String query = "select b.fecha, b.id from BancoDescuentoCheque b join b.cheques c where c.id = " + idCheque;
		List<Object[]> list = this.hql(query);
		return list.size() > 0 ? list.get(0) : null;
	}
	
	/**
	 * @return los descuentos de cheques..
	 */
	public List<BancoDescuentoCheque> getBancoDescuentos(Date desde, Date hasta, long idBancoCta) throws Exception {
		String desde_ = Utiles.getDateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = Utiles.getDateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select d from BancoDescuentoCheque d where"
				+ " d.auxi != '" + BancoDescuentoCheque.PRESTAMO + "' and"
				+ " d.auxi != '" + BancoDescuentoCheque.ANTICIPO_UTILIDAD+ "' and" 
				+ " (d.fecha >= '" + desde_ + "' and d.fecha <= '" + hasta_+ "')";
		if (idBancoCta != 0) {
			query += " and d.banco.id = " + idBancoCta;
		}
		query += " order by d.fecha";
		return this.hql(query);
	
	}
	
	/**
	 * @return los depositos bancarios..
	 */
	public List<BancoBoletaDeposito> getBancoDepositos(Date desde, Date hasta, long idBancoCta) throws Exception {
		String desde_ = Utiles.getDateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = Utiles.getDateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select d from BancoBoletaDeposito d where" 
				+ " (d.fecha >= '" + desde_ + "' and d.fecha <= '" + hasta_
				+ "')";
		if (idBancoCta != 0) {
			query += " and d.nroCuenta.id = " + idBancoCta;
		}
		query += " order by d.fecha";
		return this.hql(query);
	
	}
	
	/**
	 * @return la fecha de la planilla..
	 */
	public Date getFechaPlanilla(String numeroPlanilla) throws Exception {
		String query = "select p.apertura, p.cierre from CajaPeriodo p where p.numero = '" + numeroPlanilla + "'";
		List<Object[]> list = this.hql(query);
		return list.size() > 0? (Date) list.get(0)[0] : new Date();
	}
	
	/**
	 * @return los movimientos de banco..
	 */
	public List<BancoMovimiento> getBancoMovimientos(Date desde, Date hasta, long idBancoCta) throws Exception {
		String query = "select b from BancoMovimiento b where b.nroCuenta.id = " + idBancoCta
				+ " and b.fecha between ? and ? order by b.fecha";
		List<Object> listParams = new ArrayList<Object>();
		listParams.add(desde);
		listParams.add(hasta);
		
		Object[] params = new Object[listParams.size()];
		for (int i = 0; i < listParams.size(); i++) {
			params[i] = listParams.get(i);
		}
		
		return this.hql(query, params);
	}
	
	/**
	 * @return los movimientos de banco pendientes de conciliar..
	 */
	public List<BancoMovimiento> getBancoMovimientosNoConciliados(Date desde, Date hasta, long idBancoCta) throws Exception {
		String query = "select b from BancoMovimiento b where b.nroCuenta.id = " + idBancoCta
				+ " and b.conciliado = 'FALSE'"
				+ " and b.fecha between ? and ? order by b.fecha";
		List<Object> listParams = new ArrayList<Object>();
		listParams.add(desde);
		listParams.add(hasta);
		
		Object[] params = new Object[listParams.size()];
		for (int i = 0; i < listParams.size(); i++) {
			params[i] = listParams.get(i);
		}
		
		return this.hql(query, params);
	}
	
	/**
	 * @return las recaudaciones central con saldo..
	 */
	public List<RecaudacionCentral> getRecaudacionesCentralPendientes() throws Exception {
		String query = "select r from RecaudacionCentral r where r.saldoGs > 0";
		return this.hql(query);
	}
	
	/**
	 * @return las recaudaciones central..
	 */
	public List<RecaudacionCentral> getRecaudacionesCentral() throws Exception {
		String query = "select r from RecaudacionCentral r where r.dbEstado != 'D' order by r.razonSocial";
		return this.hql(query);
	}
	
	/**
	 * @return los funcionarios marcados como vendedor..
	 */
	public List<Funcionario> getVendedores() throws Exception {
		String query = "select f from Funcionario f where f.vendedor = 'TRUE' order by f.empresa.razonSocial";
		return this.hql(query);
	}
	
	/**
	 * @return las importaciones marcadas como en curso..
	 */
	public List<ImportacionPedidoCompra> getImportacionesPendientes() throws Exception {
		String query = "select i from ImportacionPedidoCompra i where i.dbEstado != 'R'";
		return this.hql(query);
	}
	
	/**
	 * @return las importaciones marcadas como en curso..
	 */
	public List<ImportacionPedidoCompra> getImportacionesEnCurso() throws Exception {
		String query = "select i from ImportacionPedidoCompra i where i.auxi = '" + Configuracion.SIGLA_IMPORT_EN_CURSO + "'";
		return this.hql(query);
	}
	
	/**
	 * @return las importaciones segun numero..
	 */
	public List<ImportacionPedidoCompra> getImportaciones_(String numero) throws Exception {
		String query = "select i from ImportacionPedidoCompra i where i.numeroPedidoCompra like '%" + numero.toUpperCase() + "%'";
		return this.hql(query);
	}
	
	/**
	 * @return los articulos ordenados por codigo..
	 */
	public List<Articulo> getArticulos_() throws Exception {
		String query = "select a from Articulo a order by a.codigoInterno";
		return this.hql(query);
	}
	
	/**
	 * @return el recibo de la venta..
	 */
	public Recibo getReciboByVenta(long idVenta, long idTipoMovimiento) throws Exception {
		String query = "select r from Recibo r join r.detalles d where d.movimiento.idMovimientoOriginal = " + idVenta
				+ " and d.movimiento.tipoMovimiento.id = " + idTipoMovimiento;
		List<Recibo> list = this.hql(query);
		return list.size() > 0 ? list.get(0) : null;
	}
	
	/**
	 * @return el recibo de la venta ..
	 */
	public Object[] getReciboByIdMovimiento(long idMovimiento, String fechaMayor) throws Exception {
		String query = "select r.fechaEmision, d.montoGs from Recibo r join r.detalles d where d.movimiento.id = " + idMovimiento + " and r.fechaEmision <= '" + fechaMayor + "'" ;
		List<Object[]> list = this.hql(query);
		return list.size() > 0 ? list.get(0) : null;
	}
	
	/**
	 * @return los recibos de la venta..
	 */
	public List<Object[]> getRecibosByVenta(long idVenta, long idTipoMovimiento) throws Exception {
		String query = "select r, d from Recibo r join r.detalles d where d.movimiento.idMovimientoOriginal = " + idVenta
				+ " and d.movimiento.tipoMovimiento.id = " + idTipoMovimiento;
		List<Object[]> list = this.hql(query);
		return list;
	}
	
	/**
	 * @return la nota de credito de la venta..
	 */
	public NotaCredito getNotaCreditoByVenta(long idVenta) throws Exception {
		String query = "select n from NotaCredito n join n.detalles d where d.venta.id = " + idVenta
				+ " and n.tipoMovimiento.sigla = '"+ Configuracion.SIGLA_TM_NOTA_CREDITO_VENTA +"'";
		List<NotaCredito> list = this.hql(query);
		return list.size() > 0 ? list.get(0) : null;
	}
	
	/**
	 * @return las notas de credito de la venta..
	 */
	public List<NotaCredito> getNotaCreditosByVenta(long idVenta) throws Exception {
		String query = "select n from NotaCredito n join n.detalles d where d.venta.id = " + idVenta
				+ " and n.tipoMovimiento.sigla = '"+ Configuracion.SIGLA_TM_NOTA_CREDITO_VENTA +"'"
				+ " and d.articulo is null";
		List<NotaCredito> list = this.hql(query);
		return list;
	}
	
	/**
	 * @return los anticipos de utilidad..
	 */
	public List<BancoDescuentoCheque> getAnticiposUtilidad() throws Exception {
		String query = "select a from BancoDescuentoCheque a where a.dbEstado = 'R' and a.auxi = 'anticipo'"
				+ "	order by a.fecha";
		return this.hql(query);
	}
	
	/**
	 * @return los prestamos casa central..
	 */
	public List<BancoDescuentoCheque> getPrestamosCasaCentral() throws Exception {
		String query = "select a from BancoDescuentoCheque a where a.dbEstado = 'R' and a.auxi = 'prestamo'"
				+ "	order by a.fecha";
		return this.hql(query);
	}
	
	/**
	 * @return los prestamos casa central..
	 */
	public List<BancoDescuentoCheque> getPrestamosCasaCentral(Date desde, Date hasta) throws Exception {
		String query = "select a from BancoDescuentoCheque a where (a.fecha between ? and ?) and a.dbEstado = 'R' and a.auxi = 'prestamo'"
				+ "	order by a.fecha";
		List<Object> listParams = new ArrayList<Object>();
		listParams.add(desde);
		listParams.add(hasta);

		Object[] params = new Object[listParams.size()];
		for (int i = 0; i < listParams.size(); i++) {
			params[i] = listParams.get(i);
		}
		return this.hql(query, params);
	}
	
	/**
	 * @return los reembolsos de prestamos casa central..
	 */
	public List<Recibo> getReembolsosPrestamosCasaCentral() throws Exception {
		String query = "select r from Recibo r where r.tipoMovimiento.sigla = '" + Configuracion.SIGLA_TM_REEMBOLSO_PRESTAMO + "'"
				+ "	order by r.fechaEmision";
		return this.hql(query);
	}
	
	/**
	 * @return los reembolsos de prestamos casa central..
	 */
	public List<Recibo> getReembolsosPrestamosCasaCentral(Date desde, Date hasta) throws Exception {
		String query = "select r from Recibo r where (r.fechaEmision between ? and ?) and r.tipoMovimiento.sigla = '" + Configuracion.SIGLA_TM_REEMBOLSO_PRESTAMO + "'"
				+ "	order by r.fechaEmision";
		List<Object> listParams = new ArrayList<Object>();
		listParams.add(desde);
		listParams.add(hasta);

		Object[] params = new Object[listParams.size()];
		for (int i = 0; i < listParams.size(); i++) {
			params[i] = listParams.get(i);
		}
		return this.hql(query, params);
	}
	
	/**
	 * @return RecaudacionCentral by nro recibo..
	 */
	public RecaudacionCentral getRecaudacionCentral(String numeroRecibo) throws Exception {
		String query = "select r from RecaudacionCentral r where r.dbEstado != 'D' and r.numeroRecibo = '" + numeroRecibo + "'"
				+ "	order by r.numeroRecibo";
		List<RecaudacionCentral> list = this.hql(query);
		return list.size() > 0 ? list.get(0) : null;
	}
	
	/**
	 * @return las conciliaciones de banco segun fecha..
	 */
	public List<BancoExtracto> getConciliacionesBanco(Date desde, Date hasta) throws Exception {
		String desde_ = Utiles.getDateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = Utiles.getDateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select c from BancoExtracto c where" 
				+ " (c.desde >= '" + desde_ + "' and c.hasta <= '" + hasta_ + "')";
		query += " order by c.numero";
		return this.hql(query);
	}
	
	/**
	 * @return el costo promedio del articulo a una fecha..
	 */
	public double getCostoPromedio(long idArticulo, Date fecha) throws Exception {
		String fecha_ = Utiles.getDateToString(fecha, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select h.costoPromedioGs from HistoricoArticuloStock h where h.idArticulo = " + idArticulo 
				+" and h.fecha <= '" + fecha_ + "' order by h.fecha desc";
		List<Double> list = this.hql(query);
		return list.size() > 0 ? list.get(0) : 0;
	}
	
	/**
	 * @return la lista de costos de la tabla ArticuloCosto..
	 */
	public List<ArticuloCosto> getArticuloCostos(long idArticulo, Date fecha) throws Exception {
		String fecha_ = Utiles.getDateToString(fecha, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select a from ArticuloCosto a where a.articulo.id = " + idArticulo
				+ " and a.fechaCompra <= '" + fecha_ + "' order by a.fechaCompra";
		List<ArticuloCosto> list = this.hql(query);
		return list;
	}
	
	/**
	 * @return el id, el nro de importacion segun parametro..
	 */
	public List<Object[]> getImportaciones(String numero) throws Exception {
		String query = "select i.id, i.numeroPedidoCompra, i.resumenGastosDespacho.despachante.empresa.razonSocial"
				+ " from ImportacionPedidoCompra i where"
				+ " i.numeroPedidoCompra like '%"+ numero +"%'"
				+ " order by i.numeroPedidoCompra";
		List<Object[]> list = this.hql(query);
		return list;		
	}
	
	/**
	 * @return controles de combustible..
	 */
	public List<ControlCombustible> getControlCombustibles(String fecha, String factura,
			String chofer, String vehiculo, String tipoCombustible, String nroOrden)
			throws Exception {
		String query = "select c from ControlCombustible c where upper(c.numeroFactura) like '%" + factura.toUpperCase() + "%'"
				+ " and cast (c.fecha as string) like '%" + fecha + "%'"
				+ " and upper(c.numeroChapa) like '%" + vehiculo.toUpperCase() + "%'"
				+ " and upper(c.chofer) like '%" + chofer.toUpperCase() + "%'"
				+ " and upper(c.combustible.descripcion) like '%" + tipoCombustible.toUpperCase() + "%'"
				+ " and c.numeroOrdenCompra like '%" + nroOrden + "%'"
				+ "	order by fecha";
		List<ControlCombustible> list = this.hql(query);
		return list;
	}
	
	/**
	 * @return acuses de documentos..
	 */
	public List<AcuseDocumento> getAcusesDocumentos() throws Exception {
		String query = "select a from AcuseDocumento a order by a.numero";
		List<AcuseDocumento> list = this.hql(query);
		return list;
	}
	
	/**
	 * @return el historico de comisiones segun mes..
	 */
	public List<HistoricoComisiones> getHistoricoComisiones(int mes, String anho) throws Exception {
		String query = "select c from HistoricoComisiones c where c.mes = " + mes + ""
				+ " and c.anho = '" + anho + "'";
		List<HistoricoComisiones> list = this.hql(query);
		return list;
	}
	
	/**
	 * @return los datos de empresa segun id..
	 */
	public Object[] getDatosEmpresa(long idEmpresa) throws Exception {
		String query = "select e.razonSocial, e.ruc from Empresa e where e.id = " + idEmpresa;
		List<Object[]> list = this.hql(query);
		return list.size() > 0 ? list.get(0) : new Object[]{"", ""};
	}
	
	/**
	 * @return el inventario segun parametro..
	 */
	public List<AjusteStock> getInventarios(String numero) throws Exception {
		String query = "select i from AjusteStock i where"
				+ " i.tipoMovimiento.sigla = '" + Configuracion.SIGLA_TM_INVENTARIO_MERCADERIAS + "'"
				+ " and i.numero like '%" + numero + "%'"
				+ " order by i.numero";
		List<AjusteStock> list = this.hql(query);
		return list;		
	}
	
	/**
	 * @return los inventarios pendientes segun parametro..
	 */
	public List<AjusteStock> getInventariosPendientes(String contador) throws Exception {
		String query = "select i from AjusteStock i where"
				+ " i.tipoMovimiento.sigla = '" + Configuracion.SIGLA_TM_INVENTARIO_MERCADERIAS + "'"
				+ " and i.estadoComprobante.sigla = '" + Configuracion.SIGLA_ESTADO_COMPROBANTE_PENDIENTE + "'"
				+ " and upper(i.orden) = '" + contador.toUpperCase() + "'"
				+ " order by i.numero";
		List<AjusteStock> list = this.hql(query);
		return list;		
	}
	
	/**
	 * @return los tipos de movimientos..
	 */
	public List<TipoMovimiento> getTiposDeMovimientos() throws Exception {
		String query = "select t from TipoMovimiento t order by t.descripcion";
		List<TipoMovimiento> list = this.hql(query);
		return list;
	}
	
	/**
	 * @return el tipo por descripcion..
	 */
	public Tipo getTipoPorDescripcion(String descripcion) throws Exception {
		String query = "select t from Tipo t where t.descripcion='"
				+ descripcion + "'";
		List<Tipo> list = this.hql(query);
		return list.size() > 0 ? list.get(0) : null;
	}
	
	/**
	 * 
	 * @return los funcionarios de deposito..
	 */
	public List<Funcionario> getFuncionariosDeposito(long idSucursal) throws Exception {
		String query = "select f from Funcionario f where f.deposito = 'TRUE' and f.sucursal.id = " + idSucursal
				+ " order by f.empresa.razonSocial";
		List<Funcionario> list = this.hql(query);
		return list;
	}
	
	/**
	 * @return los funcionarios cobradores..
	 */
	public List<Funcionario> getFuncionariosCobradores() throws Exception {
		String query = "select f from Funcionario f where f.cobrador = 'TRUE'"
				+ " order by f.empresa.razonSocial";
		List<Funcionario> list = this.hql(query);
		return list;
	}
	
	/**
	 * 
	 * @return los funcionarios tecnicos..
	 */
	public List<Funcionario> getFuncionariosTecnicos() throws Exception {
		String query = "select f from Funcionario f where f.tecnico = 'TRUE'"
				+ " order by f.empresa.razonSocial";
		List<Funcionario> list = this.hql(query);
		return list;
	}
	
	/**
	 * @return la lista de ventas segun numero y cliente..
	 */
	public List<Venta> getVentas(String numero, long idCliente) throws Exception {
		String query = "select v from Venta v where v.numero like '%"
				+ numero + "%' "
				+ " and v.cliente.id = " + idCliente
				+ " and (v.tipoMovimiento.sigla = '" + Configuracion.SIGLA_TM_FAC_VENTA_CONTADO + "'"
				+ " or v.tipoMovimiento.sigla = '" + Configuracion.SIGLA_TM_FAC_VENTA_CREDITO + "')"
				+ " order by v.numero";
		return this.hqlLimit(query, 200);
	}
	
	/**
	 * @return la lista de ventas segun numero y cliente..
	 * [0]:id
	 * [1]:numero
	 */
	public List<Object[]> getVentas_(String numero, long idCliente) throws Exception {
		String query = "select v.id, v.numero from Venta v where v.numero like '%"
				+ numero + "%' "
				+ " and v.cliente.id = " + idCliente
				+ " and (v.tipoMovimiento.sigla = '" + Configuracion.SIGLA_TM_FAC_VENTA_CONTADO + "'"
				+ " or v.tipoMovimiento.sigla = '" + Configuracion.SIGLA_TM_FAC_VENTA_CREDITO + "')"
				+ " order by v.numero";
		return this.hqlLimit(query, 200);
	}
	
	/**
	 * @return los servicios tecnicos segun parametros..
	 */
	public List<ServicioTecnico> getServiciosTecnicos(String fecha, String numero, String razonSocial, 
			String receptor, String tecnico, String entregado) throws Exception {
		boolean entregado_ = entregado.equals("S") ? true : false;
		String query = "select s from ServicioTecnico s where "
				+ " s.numero like '%" + numero + "%'"
				+ " and cast (s.fecha as string) like '%" + fecha + "%'"
				+ " and upper(s.receptor) like '%" + receptor.toUpperCase() + "%'"
				+ " and upper(s.tecnico) like '%" + tecnico.toUpperCase() + "%'"
				+ " and upper(s.cliente.empresa.razonSocial) like '%" + razonSocial.toUpperCase() + "%'";
				if (entregado.equals("S") || entregado.equals("N")) {
					query += " and s.entregado = " + entregado_;
				}
				query += " order by s.fecha, s.numero";
		return this.hqlLimit(query, 200);
	}
	
	/**
	 * @return los servicios tecnicos segun parametros..
	 * [0]:id
	 * [1]:numero
	 * [2]:fecha
	 * [3]:numeroReclamo
	 * [4]:numeroReparto
	 * [5]:receptor
	 * [6]:tecnico
	 * [7]:razonSocial
	 * [8]:observaciones
	 * [9]:diagnostico
	 * [10]:entregado
	 */
	public List<Object[]> getServiciosTecnicos_(String fecha, String numero, String razonSocial, 
			String receptor, String tecnico, String entregado) throws Exception {
		boolean entregado_ = entregado.equals("S") ? true : false;
		String query = "select s.id, s.numero, s.fecha, COALESCE(s.numeroReclamo, '- - -'), COALESCE(s.numeroReparto, '- - -'),"
				+ " s.receptor, s.tecnico, s.cliente.empresa.razonSocial, d.observacion, d.diagnostico, s.entregado from ServicioTecnico s join s.detalles d where "
				+ " s.numero like '%" + numero + "%'"
				+ " and cast (s.fecha as string) like '%" + fecha + "%'"
				+ " and upper(s.receptor) like '%" + receptor.toUpperCase() + "%'"
				+ " and upper(s.tecnico) like '%" + tecnico.toUpperCase() + "%'"
				+ " and upper(s.cliente.empresa.razonSocial) like '%" + razonSocial.toUpperCase() + "%'";
				if (entregado.equals("S") || entregado.equals("N")) {
					query += " and s.entregado = " + entregado_;
				}
				query += " order by s.fecha, s.numero";
		return this.hqlLimit(query, 100);
	}
	
	/**
	 * @return los servicios tecnicos segun parametros..
	 * [0]:id
	 * [1]:numero
	 * [2]:fecha
	 * [3]:numeroReclamo
	 * [4]:numeroReparto
	 * [5]:receptor
	 * [6]:tecnico
	 * [7]:razonSocial
	 * [8]:observaciones
	 * [9]:diagnostico
	 * [10]:entregado
	 */
	public List<Object[]> getServiciosTecnicosAutocentros(String fecha, String numero, String razonSocial, 
			String receptor, String tecnico, String entregado) throws Exception {
		boolean entregado_ = entregado.equals("S") ? true : false;
		String query = "select s.id, s.numero, s.fecha, COALESCE(s.numeroReclamo, '- - -'), COALESCE(s.numeroReparto, '- - -'),"
				+ " s.receptor, s.tecnico, s.cliente_, d.observacion, d.diagnostico, s.entregado from ServicioTecnico s join s.detalles d where "
				+ " s.numero like '%" + numero + "%'"
				+ " and cast (s.fecha as string) like '%" + fecha + "%'"
				+ " and upper(s.receptor) like '%" + receptor.toUpperCase() + "%'"
				+ " and upper(s.tecnico) like '%" + tecnico.toUpperCase() + "%'"
				+ " and upper(s.cliente_) like '%" + razonSocial.toUpperCase() + "%'";
				if (entregado.equals("S") || entregado.equals("N")) {
					query += " and s.entregado = " + entregado_;
				}
				query += " order by s.fecha, s.numero";
		return this.hqlLimit(query, 100);
	}
	
	/**
	 * @return el historico de movimientos..
	 */
	public List<HistoricoMovimientos> getHistoricoMovimientos() throws Exception {
		String query = "select h from HistoricoMovimientos h order by h.razonSocial";
		return this.hql(query);
	}
	
	/**
	 * @return el historico de recalculo de stock..
	 */
	public List<HistoricoRecalculoStock> getHistoricoRecalculos() throws Exception {
		String query = "select h from HistoricoRecalculoStock h order by h.fecha desc";
		return this.hql(query);
	}
	
	/**
	 * @return los cheques a ingresar de clientes sin considerar los cheques descontados..
	 */
	public List<BancoChequeTercero> getChequesPendientesClientes(long idCliente) 
		throws Exception {		
		String razonSocial = "";
		if (idCliente != 0) {
			Cliente cli = this.getClienteById(idCliente);
			razonSocial = cli.getRazonSocial();
		}
		List<BancoChequeTercero> cheques = this.getChequesTercero("",
				"", "", "", "", "",
				razonSocial, "", "", "", "",
				"", "FALSE", null, "FALSE", "FALSE", null, null, null, null, "", "", false);
		List<BancoChequeTercero> out = new ArrayList<BancoChequeTercero>();
		for (BancoChequeTercero cheque : cheques) {
			if (cheque.getFecha().compareTo(new Date()) > 0) {
				out.add(cheque);
			}
		}
		return out;
	}
	
	/**
	 * @return los cheques a ingresar de clientes sin considerar los cheques descontados..
	 * [0]:id
	 * [1]:numero
	 * [2]:emision
	 * [3]:vencimiento
	 * [4]:importe 
	 */
	public List<Object[]> getChequesPendientesClientes_(long idCliente) throws Exception {		
		String razonSocial = "";
		if (idCliente != 0) {
			Cliente cli = this.getClienteById(idCliente);
			razonSocial = cli.getRazonSocial();
		}
		List<Object[]> cheques = this.getChequesTercero_("",
				"", "", "", "", "",
				razonSocial, "", "", "", "",
				"", "FALSE", null, "FALSE", null, null, null, null, "", "", false);
		List<Object[]> out = new ArrayList<Object[]>();
		for (Object[] cheque : cheques) {
			Date fecha = (Date) cheque[3];
			if (fecha.compareTo(new Date()) > 0) {
				out.add(cheque);
			}
		}
		return out;
	}
	
	/**
	 * @return los gastos segun parametros..
	 */
	public List<Gasto> getGastos(String fecha, String numero, String razonSocial, 
			String ruc, String caja, String pago, String importacion, String descripcion) throws Exception {
		String query = "select g from Gasto g where "
				+ " cast (g.fecha as string) like '%" + fecha + "%'"
				+ " and g.numeroFactura like '%" + numero + "%'"
				+ " and upper(g.proveedor.empresa.razonSocial) like '%" + razonSocial.toUpperCase() + "%'"
				+ " and g.proveedor.empresa.ruc like '%" + ruc + "%'"
				+ " and g.cajaPagoNumero like '%" + caja + "%'"
				+ " and upper(g.numeroImportacion) like '%" + importacion.toUpperCase() + "%'"
				+ " and upper(g.numeroOrdenPago) like '%" + pago.toUpperCase() + "%'"
				+ " and upper(g.observacion) like '%" + descripcion.toUpperCase() + "%'"
				+ " order by g.fecha";
		return this.hqlLimit(query, 200);
	}
	
	/**
	 * @return las ordenes de compra segun parametros..
	 */
	public List<OrdenCompra> getOrdenesCompra(String fecha, String numero, String razonSocial, 
			String autorizadoPor, String solicitadoPor) throws Exception {
		String query = "select o from OrdenCompra o where "
				+ " o.numero like '%" + numero + "%'"
				+ " and cast (o.fecha as string) like '%" + fecha + "%'"
				+ " and upper(o.autorizadoPor) like '%" + autorizadoPor.toUpperCase() + "%'"
				+ " and upper(o.solicitadoPor) like '%" + solicitadoPor.toUpperCase() + "%'"
				+ " and upper(o.proveedor.empresa.razonSocial) like '%" + razonSocial.toUpperCase() + "%'"
				+ " order by o.fecha, o.numero";
		return this.hqlLimit(query, 200);
	}
	
	/**
	 * @return los resumenes de caja segun parametros..
	 */
	public List<CajaPlanillaResumen> getCajaPlanillaResumenes(String fecha, String numero, String planillas) throws Exception {
		String query = "select c from CajaPlanillaResumen c where "
				+ " c.numero like '%" + numero + "%'"
				+ " and cast (c.fecha as string) like '%" + fecha + "%'"
				+ " and upper(c.numeroPlanillas) like '%" + planillas.toUpperCase() + "%'"
				+ " order by c.fecha, c.numero";
		return this.hqlLimit(query, 200);
	}
	
	/**
	 * @return los resumenes de caja segun parametros..
	 */
	public List<Object[]> getCajaPlanillaResumenes_(String fecha, String numero, String planillas) throws Exception {
		String query = "select c.fecha, c.numero, c.numeroPlanillas, c.id from CajaPlanillaResumen c where "
				+ " c.numero like '%" + numero + "%'"
				+ " and cast (c.fecha as string) like '%" + fecha + "%'"
				+ " and upper(c.numeroPlanillas) like '%" + planillas.toUpperCase() + "%'"
				+ " order by c.fecha, c.numero";
		return this.hqlLimit(query, 200);
	}
	
	/**
	 * @return los resumenes de caja segun parametros..
	 * [0]:fecha - [1]:numero - [2]:numeroPlanillas - [3]:obs_efe - [4]: obs_che 
	 * [5]:efectivoNoDep - [6]:chequeNodep - 
	 */
	public List<Object[]> getCajaPlanillaResumenes(Date desde, Date hasta) throws Exception {
		String query = "select c.fecha, c.numero, c.obs_efectivo_no_depositado, c.obs_cheque_no_depositado,"
				+ " c.efectivoNoDepositado, c.chequeNoDepositado from CajaPlanillaResumen c where c.fecha between ? and ?"
				+ " order by c.fecha, c.numero";
		List<Object> listParams = new ArrayList<Object>();
		listParams.add(desde);
		listParams.add(hasta);

		Object[] params = new Object[listParams.size()];
		for (int i = 0; i < listParams.size(); i++) {
			params[i] = listParams.get(i);
		}
		return this.hql(query, params);
	}
	
	/**
	 * @return el registro de ventas perdidas..
	 */
	public List<VentaPerdida> getVentasPerdidas(Date desde, Date hasta) throws Exception {
		String query = "select v from VentaPerdida v where v.fecha between ? and ?";		
		query += " order by v.fecha";

		List<Object> listParams = new ArrayList<Object>();
		listParams.add(desde);
		listParams.add(hasta);

		Object[] params = new Object[listParams.size()];
		for (int i = 0; i < listParams.size(); i++) {
			params[i] = listParams.get(i);
		}
		return this.hql(query, params);
	}
	
	/**
	 * @return los movimientos de cta cte. de una empresa
	 */
	public List<CtaCteEmpresaMovimiento> getCtaCteMovimientosVencidos() throws Exception {
		String vto = misc.dateToString(new Date(), Misc.YYYY_MM_DD) + " 00:00:00";
		String query = "select c from CtaCteEmpresaMovimiento c where"
				+ " c.anulado != 'TRUE'"
				+ " and c.saldo > 500"
				+ " and c.fechaVencimiento <= '" + vto + "'"
				+ " and c.tipoMovimiento.sigla = '" + Configuracion.SIGLA_TM_FAC_VENTA_CREDITO + "'"
				+ " order by c.fechaVencimiento asc";
		List<CtaCteEmpresaMovimiento> list = this.hql(query);
		return list;
	}
	
	/**
	 * @return el registro de bloqueo de clientes..
	 */
	public List<HistoricoBloqueoClientes> getHistoricoBloqueoClientes(Date desde, Date hasta) throws Exception {
		String query = "select h from HistoricoBloqueoClientes h where h.fecha between ? and ?";		
		query += " order by h.cliente, h.diasVencimiento desc";

		List<Object> listParams = new ArrayList<Object>();
		listParams.add(desde);
		listParams.add(hasta);

		Object[] params = new Object[listParams.size()];
		for (int i = 0; i < listParams.size(); i++) {
			params[i] = listParams.get(i);
		}
		return this.hql(query, params);
	}
	
	/**
	 * @return las importaciones segun fecha
	 */
	public List<ImportacionPedidoCompra> getImportaciones(Date desde, Date hasta, long idProveedor) throws Exception {

		String query = "select i from ImportacionPedidoCompra i where i.dbEstado != 'D' and i.proveedor.id = ?"
				+ " and i.fechaCreacion between ? and ?";
		query += " order by i.numeroPedidoCompra";

		List<Object> listParams = new ArrayList<Object>();
		listParams.add(idProveedor);
		listParams.add(desde);
		listParams.add(hasta);

		Object[] params = new Object[listParams.size()];
		for (int i = 0; i < listParams.size(); i++) {
			params[i] = listParams.get(i);
		}		
		return this.hql(query, params);
	}
	
	/**
	 * @return las importaciones segun fecha
	 */
	public List<ImportacionPedidoCompra> getImportaciones_(Date desde, Date hasta, long idProveedor) 
			throws Exception {

		String query = "select i from ImportacionPedidoCompra i where i.dbEstado != 'D' and i.proveedor.id = ?"
				+ " and i.fechaCreacion between ? and ?";
		query += " order by i.numeroPedidoCompra";

		List<Object> listParams = new ArrayList<Object>();
		listParams.add(idProveedor);
		listParams.add(desde);
		listParams.add(hasta);

		Object[] params = new Object[listParams.size()];
		for (int i = 0; i < listParams.size(); i++) {
			params[i] = listParams.get(i);
		}		
		return this.hql(query, params);
	}
	
	/**
	 * @return las empresas
	 */
	public List<Empresa> getEmpresas() throws Exception {
		String query = "select e from Empresa e order by e.razonSocial";
		return this.hql(query);
	}
	
	/**
	 * @return las empresas
	 */
	public List<Empresa> getEmpresasGeolocalizadas(String razonSocial, int limit) throws Exception {
		String query = "select e from Empresa e where e.latitud != '' and upper(e.razonSocial)"
				+ " like '%" + razonSocial.toUpperCase() + "%' order by e.razonSocial";
		return this.hqlLimit(query, limit);
	}
	
	/**
	 * @return la lista de articulos gastos segun descripcion..
	 */
	public List<ArticuloGasto> getArticulosGastos(String descripcion, int limit) throws Exception {
		String query = "select a from ArticuloGasto a where lower(a.descripcion) like '%"
				+ descripcion.toLowerCase() + "%'"
				+ " order by a.descripcion";
		return this.hqlLimit(query, limit);
	}
	
	/**
	 * @return las empresas segun zona..
	 */
	public List<Empresa> getEmpresasGeolocalizadasPorZona(String zona) throws Exception {
		String query = "select e from Empresa e where e.latitud != '' and e.zona = '" + zona + "'"
				+ " order by e.razonSocial";
		return this.hql(query);
	}
	
	/**
	 * @return las empresas segun vendedor..
	 */
	public List<Empresa> getEmpresasGeocalizadasPorVendedor(long idVendedor) throws Exception {
		String query = "select e from Empresa e where e.latitud != '' and e.vendedor.id = " + idVendedor + ""
				+ " order by e.razonSocial";
		return this.hql(query);
	}
	
	/**
	 * @return las empresas segun cobrador..
	 */
	public List<Empresa> getEmpresasGeocalizadasPorCobrador(long idCobrador) throws Exception {
		String query = "select e from Empresa e where e.latitud != '' and e.cobrador.id = " + idCobrador + ""
				+ " order by e.razonSocial";
		return this.hql(query);
	}
	
	/**
	 * @return los clientes sin geolocalizacion..
	 * [0]: razonSocial, [1]: direccion
	 */
	public List<Object[]> getClientesNoGeolocalizados() throws Exception {
		String query = "select c.empresa.razonSocial, c.empresa.direccion_"
				+ " from Cliente c where c.empresa.latitud = '' order by c.empresa.razonSocial";
		return this.hql(query);
	}
	
	/**
	 * @return la definicion de comision..
	 */
	public VendedorComision getVendedorComision(long idProveedor, long idVendedor) throws Exception {
		String query = "select v from VendedorComision v where v.id_proveedor = "+ idProveedor 
				+ " and v.id_funcionario = " + idVendedor;
		List<VendedorComision> list = this.hql(query);
		return list.size() > 0 ? list.get(0) : null;	
	}
	
	/**
	 * @return el historico venta / meta..
	 */
	public HistoricoVentaVendedor getHistoricoVentaVendedor(int anho, int mes, long idVendedor) throws Exception {
		String query = "select h from HistoricoVentaVendedor h where h.anho = " + anho 
				+ " and h.mes = " + mes + " and h.id_funcionario = " + idVendedor;
		List<HistoricoVentaVendedor> list = this.hql(query);
		return list.size() > 0 ? list.get(0) : null;	
	}
	
	/**
	 * @return el historico venta diaria..
	 */
	public HistoricoVentaDiaria getHistoricoVentaDiaria(Date fecha, long idVendedor) throws Exception {
		String fecha_ = Utiles.getDateToString(fecha, Misc.YYYY_MM_DD) + " 00:00:00";
		String query = "select h from HistoricoVentaDiaria h where "
				+ " cast (h.fecha as string) like '%" + fecha_ + "%'"
				+ " and h.id_funcionario = " + idVendedor;
		List<HistoricoVentaDiaria> list = this.hql(query);
		return list.size() > 0 ? list.get(0) : null;	
	}
	
	/**
	 * @return los clientes segun vendedor..
	 */
	public List<Object[]> getClientesPorVendedor(long idVendedor) throws Exception {
		String query = "select e.ruc, e.razonSocial, e.direccion_, e.telefono_, e.vendedor.empresa.razonSocial from Empresa e where e.vendedor.id = " + idVendedor
				+ " order by e.razonSocial";
		return this.hql(query);
	}
	
	/**
	 * @return el plan de cuenta segun codigo..
	 */
	public PlanCuenta getPlanCuenta(String codigo) throws Exception {
		String query = "select p from PlanCuenta p where p.codigo = '"+ codigo + "'";
		List<PlanCuenta> list = this.hql(query);
		return list.size() > 0 ? list.get(0) : null;	
	}
	
	/**
	 * @return los controles de talonario activos..
	 */
	public List<ControlTalonario> getControlTalonarioActivos() throws Exception {
		String query = "select c from ControlTalonario c where c.activo = true order by c.fecha asc";
		return this.hql(query);
	}
	
	/**
	 * @return el historico venta / meta..
	 */
	public HistoricoCobranzaVendedor getHistoricoCobranzaVendedor(int anho, int mes, long idVendedor) throws Exception {
		String query = "select h from HistoricoCobranzaVendedor h where h.anho = " + anho 
				+ " and h.mes = " + mes + " and h.id_funcionario = " + idVendedor;
		List<HistoricoCobranzaVendedor> list = this.hql(query);
		return list.size() > 0 ? list.get(0) : null;	
	}
	
	/**
	 * @return el historico cobranza diaria..
	 */
	public HistoricoCobranzaDiaria getHistoricoCobranzaDiaria(Date fecha, long idVendedor) throws Exception {
		String fecha_ = Utiles.getDateToString(fecha, Misc.YYYY_MM_DD) + " 00:00:00";
		String query = "select h from HistoricoCobranzaDiaria h where "
				+ " cast (h.fecha as string) like '%" + fecha_ + "%'"
				+ " and h.id_funcionario = " + idVendedor;
		List<HistoricoCobranzaDiaria> list = this.hql(query);
		return list.size() > 0 ? list.get(0) : null;	
	}
	
	/**
	 * @return el cliente segun id..
	 * [0]:id
	 * [1]:razonSocial
	 * [2]:limiteCredito
	 * [3]:ventaCredito
	 * [4]:descuentoRepuestos
	 */
	public Object[] getCliente(long idCliente) throws Exception {
		String query = "select c.id, c.empresa.razonSocial, c.limiteCredito, c.ventaCredito, c.descuentoRepuestos"
				+ " from Cliente c where c.id = "
				+ idCliente;
		List<Object[]> list = this.hql(query);
		return list.size() > 0 ? list.get(0) : null;
	}
	
	/**
	 * @return el cliente segun id..
	 * [0]:id
	 * [1]:vendedor.id
	 * [2]:vendedor.nombre
	 * [3]:vendedor.dependencia
	 */
	public Object[] getClienteVendedor(long idCliente) throws Exception {
		String query = "select c.id, c.vendedor.id, c.vendedor.nombre, c.vendedor.dependencia"
				+ " from Cliente c where c.id = "
				+ idCliente;
		List<Object[]> list = this.hql(query);
		return list.size() > 0 ? list.get(0) : null;
	}
	
	/**
	 * @return las ventas donde esta contenida el articulo.. 
	 * [0]:concepto
	 * [1]:fecha 
	 * [2]:numero 
	 * [3]:importe
	 * [4]:importe
	 */
	public List<Object[]> getVentasCreditoPorCliente(long idCliente, Date desde, Date hasta) throws Exception {
		String desde_ = Utiles.getDateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = Utiles.getDateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select v.tipoMovimiento.descripcion, v.fecha, v.numero, v.totalImporteGs, v.id"
				+ " from Venta v where (v.tipoMovimiento.sigla = '"
				+ Configuracion.SIGLA_TM_FAC_VENTA_CREDITO
				+ "') and v.dbEstado != 'D' and v.estadoComprobante IS NULL";
				query += " and (v.fecha >= '"
				+ desde_
				+ "' and v.fecha <= '"
				+ hasta_
				+ "')";
				if (idCliente != 0) {
					query += " and v.cliente.id = " + idCliente;
				}
				query += " order by v.fecha desc";
		return this.hql(query);
	}
	
	/**
	 * @return empresa por id 
	 * [0]:id
	 * [1]:ruc
	 * [2]:razonsocial
	 * [3]:telefono
	 * [4]:cuentabloqueada
	 */
	public Object[] getEmpresa(long idEmpresa) throws Exception {
		String query = "select e.id, e.ruc, e.razonSocial, e.telefono_, e.cuentaBloqueada from Empresa e where e.id = " + idEmpresa;
		List<Object[]> list = this.hql(query);
		return list.size() > 0 ? list.get(0) : null;	
	} 
	
	/**
	 * @return los detalles de la nota de credito..
	 * [0]:costoGs
	 * [1]:cantidad
	 * [2]:articulo.id
	 */
	public List<Object[]> getNotaCreditoDetalles(long idNotaCredito) throws Exception {
		String query = "select d.costoGs, d.cantidad, d.articulo.id from NotaCredito n join n.detalles d where n.id = " + idNotaCredito
				+ " and d.articulo is not null ";
		return this.hql(query);
	}
	
	/**
	 * @return los detalles de la nota de credito..
	 * [0]:costoUnitarioGs
	 * [1]:cantidad
	 * [2]:articulo.id
	 */
	public List<Object[]> getVentaDetalles(long idVenta) throws Exception {
		String query = "select d.costoUnitarioGs, d.cantidad, d.articulo.id from Venta v join v.detalles d where v.id = " + idVenta;
		return this.hql(query);
	}
	
	/**
	 * @return los articulos
	 * [0]:id
	 * [1]:codigo
	 */
	public List<Object[]> get_articulos() throws Exception {
		String query = "select a.id, a.codigoInterno from Articulo a where a.codigoInterno not like '@%'";
		return this.hql(query);
	}
	
	/**
	 * @return los prestamos bancarios segun parametros..
	 */
	public List<BancoPrestamo> getBancoPrestamos(String fecha, String banco, String numero) throws Exception {
		String query = "select b from BancoPrestamo b where "
				+ " b.numero like '%" + numero + "%'"
				+ " and cast (b.fecha as string) like '%" + fecha + "%'"
				+ " and upper(b.banco.banco.descripcion) like '%" + banco.toUpperCase() + "%'"
				+ " order by b.fecha";
		return this.hqlLimit(query, 200);
	}
	
	/**
	 * @return las transferencias bancarias..
	 */
	public List<BancoTransferencia> getBancoTransferencias(String fecha, String origen, String destino, String numero) throws Exception {
		String query = "select b from BancoTransferencia b where "
				+ " upper(b.numero) like '%" + numero.toUpperCase() + "%'"
				+ " and cast (b.fecha as string) like '%" + fecha + "%'"
				+ " and upper(b.origen.banco.descripcion) like '%" + origen.toUpperCase() + "%'"
				+ " and upper(b.destino.banco.descripcion) like '%" + destino.toUpperCase() + "%'"
				+ " order by b.fecha";
		return this.hqlLimit(query, 200);
	}
	
	/**
	 * @return empresas segun ruc..
	 */
	public List<Empresa> getEmpresasByRuc(String ruc) throws Exception {
		String query = "select e from Empresa e where e.ruc = '" + ruc + "'";
		return this.hql(query);
	}
	
	/**
	 * @return los registros de venta promo 1..
	 */
	public List<VentaPromo1> getVentaPromo1Registros(String ruc) throws Exception {
		String query = "select v from VentaPromo1 v where v.empresa.ruc = '" + ruc + "'";
		return this.hql(query);
	}
	
	/**
	 * @return los saldos iniciales segun banco.. 
	 * [0]:concepto
	 * [1]:fecha 
	 * [2]:numero 
	 * [3]:totalImporteGs 
	 * [4]:banco 
	 */
	public List<Object[]> getSaldosInicialesPorBanco(long idBanco, Date desde, Date hasta) throws Exception {
		String desde_ = Utiles.getDateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = Utiles.getDateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select (select descripcion from TipoMovimiento where sigla = '" + Configuracion.SIGLA_TM_SALDO_INICIAL_BANCO + "'), "
				+ " b.fecha, ('- - -'), b.monto, b.nroCuenta.banco.descripcion, b.descripcion"
				+ " from BancoMovimiento b where"
				+ " b.tipoMovimiento.sigla = '" + Configuracion.SIGLA_TM_SALDO_INICIAL_BANCO + "'"
				+ " and b.dbEstado != 'D'"
				+ " and b.nroCuenta.id = " + idBanco
				+ " and (b.fecha >= '"
				+ desde_
				+ "' and b.fecha <= '"
				+ hasta_
				+ "')" + " order by b.fecha desc";
		return this.hql(query);
	}
	
	/**
	 * @return los depositos segun banco.. 
	 * [0]:concepto
	 * [1]:fecha 
	 * [2]:numero 
	 * [3]:totalImporteGs 
	 * [4]:banco 
	 */
	public List<Object[]> getDepositosPorBanco(long idBanco, Date desde, Date hasta) throws Exception {
		String desde_ = Utiles.getDateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = Utiles.getDateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select (select descripcion from TipoMovimiento where sigla = '" + Configuracion.SIGLA_TM_DEPOSITO_BANCARIO + "'), "
				+ " b.fecha, b.numeroBoleta, b.totalImporte_gs, b.nroCuenta.banco.descripcion, b.observacion"
				+ " from BancoBoletaDeposito b where"
				+ " b.dbEstado != 'D'"
				+ " and b.nroCuenta.id = " + idBanco
				+ " and (b.fecha >= '"
				+ desde_
				+ "' and b.fecha <= '"
				+ hasta_
				+ "')" + " order by b.fecha desc";
		return this.hql(query);
	}
	
	/**
	 * @return los descuentos segun banco.. 
	 * [0]:concepto
	 * [1]:fecha 
	 * [2]:numero 
	 * [3]:totalImporteGs 
	 * [4]:banco 
	 */
	public List<Object[]> getDescuentosPorBanco(long idBanco, Date desde, Date hasta) throws Exception {
		String desde_ = Utiles.getDateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = Utiles.getDateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select ('DESCUENTO DE CHEQUES'), " //CASE WHEN B.marker = 1 THEN 1 ELSE NULL END
				+ " b.fecha, cast(b.id as string), (CASE WHEN (b.liq_neto_aldia + b.liq_neto_diferidos) = 0 THEN b.totalImporte_gs"
				+ " ELSE (b.liq_neto_aldia + b.liq_neto_diferidos) END) , b.banco.banco.descripcion, b.observacion"
				+ " from BancoDescuentoCheque b where"
				+ " b.dbEstado = 'R'"
				+ " and b.banco.id = " + idBanco
				+ " and auxi != '" + BancoDescuentoCheque.PRESTAMO + "'"
				+ " and auxi != '" + BancoDescuentoCheque.ANTICIPO_UTILIDAD + "'"
				+ " and (b.fecha >= '"
				+ desde_
				+ "' and b.fecha <= '"
				+ hasta_
				+ "')" + " order by b.fecha desc";
		return this.hql(query);
	}
	
	/**
	 * @return los descuentos que son prestamos internos segun banco.. 
	 * [0]:concepto
	 * [1]:fecha 
	 * [2]:numero 
	 * [3]:totalImporteGs 
	 * [4]:banco 
	 */
	public List<Object[]> getPrestamosInternosPorBanco(long idBanco, Date desde, Date hasta) throws Exception {
		String desde_ = Utiles.getDateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = Utiles.getDateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select ('PRESTAMOS INTERNOS'), "
				+ " b.fecha, cast(b.id as string), b.totalImporte_gs, b.banco.banco.descripcion, b.observacion"
				+ " from BancoDescuentoCheque b join b.formasPago f where"
				+ " b.dbEstado = 'R'"
				+ " and b.banco.id = " + idBanco
				+ " and b.auxi = '" + BancoDescuentoCheque.PRESTAMO + "'"
				+ " and f.tipo.sigla = '" + Configuracion.SIGLA_FORMA_PAGO_DEBITO_CTA_BANCARIA + "'"
				+ " and (b.fecha >= '"
				+ desde_
				+ "' and b.fecha <= '"
				+ hasta_
				+ "')" + " order by b.fecha desc";
		return this.hql(query);
	}
	
	/**
	 * @return las transferencias (banco origen) segun banco.. 
	 * [0]:concepto
	 * [1]:fecha 
	 * [2]:numero 
	 * [3]:totalImporteGs 
	 * [4]:banco 
	 */
	public List<Object[]> getTransferenciasOrigenPorBanco(long idBanco, Date desde, Date hasta) throws Exception {
		String desde_ = Utiles.getDateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = Utiles.getDateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select ('TRANSFERENCIA BANCARIA'), "
				+ " b.fecha, b.numero, b.importe, b.origen.banco.descripcion, concat('TRANSFERENCIA ENVIADA A BANCO: ', b.destino.banco.descripcion)"
				+ " from BancoTransferencia b where"
				+ " b.origen.id = " + idBanco
				+ " and (b.fecha >= '"
				+ desde_
				+ "' and b.fecha <= '"
				+ hasta_
				+ "')" + " order by b.fecha desc";
		return this.hql(query);
	}
	
	/**
	 * @return las transferencias (banco destino) segun banco.. 
	 * [0]:concepto
	 * [1]:fecha 
	 * [2]:numero 
	 * [3]:totalImporteGs 
	 * [4]:banco 
	 */
	public List<Object[]> getTransferenciasDestinoPorBanco(long idBanco, Date desde, Date hasta) throws Exception {
		String desde_ = Utiles.getDateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = Utiles.getDateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select ('TRANSFERENCIA BANCARIA'), "
				+ " b.fecha, b.numero, b.importe, b.destino.banco.descripcion, concat('TRANSFERENCIA RECIBIDA DE BANCO: ', b.origen.banco.descripcion)"
				+ " from BancoTransferencia b where"
				+ " b.destino.id = " + idBanco
				+ " and (b.fecha >= '"
				+ desde_
				+ "' and b.fecha <= '"
				+ hasta_
				+ "')" + " order by b.fecha desc";
		return this.hql(query);
	}
	
	/**
	 * @return los prestamos bancarios segun banco.. 
	 * [0]:concepto
	 * [1]:fecha 
	 * [2]:numero 
	 * [3]:totalImporteGs 
	 * [4]:banco 
	 */
	public List<Object[]> getPrestamosBancariosPorBanco(long idBanco, Date desde, Date hasta) throws Exception {
		String desde_ = Utiles.getDateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = Utiles.getDateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select ('PRESTAMO BANCARIO'), "
				+ " b.fecha, b.numero, (b.capital - b.impuestos - b.gastosAdministrativos), b.banco.banco.descripcion, concat('PRESTAMO BANCARIO ', b.tipoVencimiento, ' ',  b.tipoCuotas)"
				+ " from BancoPrestamo b where"
				+ " b.banco.id = " + idBanco
				+ " and (b.fecha >= '"
				+ desde_
				+ "' and b.fecha <= '"
				+ hasta_
				+ "')" + " order by b.fecha desc";
		return this.hql(query);
	}
	
	/**
	 * @return los gastos bancarios segun banco.. 
	 * [0]:concepto
	 * [1]:fecha 
	 * [2]:numero 
	 * [3]:totalImporteGs 
	 * [4]:banco 
	 */
	public List<Object[]> getGastosBancariosPorBanco(long idBanco, Date desde, Date hasta) throws Exception {
		String desde_ = Utiles.getDateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = Utiles.getDateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select ('DEBITO BANCARIO'), "
				+ " g.fecha, g.numeroFactura, g.importeGs, g.banco.banco.descripcion, g.observacion"
				+ " from Gasto g where g.debitoBancario = 'TRUE'"
				+ " and g.banco.id = " + idBanco
				+ " and (g.fecha >= '"
				+ desde_
				+ "' and g.fecha <= '"
				+ hasta_
				+ "')" + " order by g.fecha desc";
		return this.hql(query);
	}
	
	/**
	 * @return las formas de pago debito en cta. segun banco.. 
	 * [0]:concepto
	 * [1]:fecha 
	 * [2]:numero 
	 * [3]:totalImporteGs 
	 * [4]:banco 
	 */
	public List<Object[]> getFormasPagoDebitoBancarioPorBanco(long idBanco, Date desde, Date hasta) throws Exception {
		String desde_ = Utiles.getDateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = Utiles.getDateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select ('DEBITO CTA. BANCARIA'), "
				+ " r.fechaEmision, r.numero, f.montoGs, f.depositoBancoCta.banco.descripcion, concat(r.numero, ' - ', r.proveedor.empresa.razonSocial)"
				+ " from Recibo r join r.formasPago f where f.tipo.sigla = '" + Configuracion.SIGLA_FORMA_PAGO_DEBITO_CTA_BANCARIA + "'"
				+ " and f.depositoBancoCta.id = " + idBanco
				+ " and (r.fechaEmision >= '"
				+ desde_
				+ "' and r.fechaEmision <= '"
				+ hasta_
				+ "')" + " order by r.fechaEmision desc";
		return this.hql(query);
	}
	
	/**
	 * @return las formas de pago deposito en cta. segun banco.. 
	 * [0]:concepto
	 * [1]:fecha 
	 * [2]:numero 
	 * [3]:totalImporteGs 
	 * [4]:banco 
	 */
	public List<Object[]> getFormasPagoDepositoBancarioEnRecibosPorBanco(long idBanco, Date desde, Date hasta) throws Exception {
		String desde_ = Utiles.getDateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = Utiles.getDateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select ('DEPOSITO CTA. BANCARIA'), "
				+ " r.fechaEmision, f.depositoNroReferencia, f.montoGs, f.depositoBancoCta.banco.descripcion, concat('RECIBO NRO. ', r.numero, ' - ', r.cliente.empresa.razonSocial)"
				+ " from Recibo r join r.formasPago f where f.tipo.sigla = '" + Configuracion.SIGLA_FORMA_PAGO_DEPOSITO_BANCARIO + "'"
				+ " and f.depositoBancoCta.id = " + idBanco
				+ " and (r.fechaEmision >= '"
				+ desde_
				+ "' and r.fechaEmision <= '"
				+ hasta_
				+ "')" + " order by r.fechaEmision desc";
		return this.hql(query);
	}
	
	/**
	 * @return las formas de pago deposito en cta. segun banco.. 
	 * [0]:concepto
	 * [1]:fecha 
	 * [2]:numero 
	 * [3]:totalImporteGs 
	 * [4]:banco 
	 */
	public List<Object[]> getFormasPagoDepositoBancarioEnVentasPorBanco(long idBanco, Date desde, Date hasta) throws Exception {
		String desde_ = Utiles.getDateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = Utiles.getDateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select ('DEPOSITO CTA. BANCARIA'), "
				+ " v.fecha, f.depositoNroReferencia, f.montoGs, f.depositoBancoCta.banco.descripcion, concat('VENTA NRO. ', v.numero, ' - ', v.cliente.empresa.razonSocial)"
				+ " from Venta v join v.formasPago f where f.tipo.sigla = '" + Configuracion.SIGLA_FORMA_PAGO_DEPOSITO_BANCARIO + "'"
				+ " and v.estadoComprobante is null"
				+ " and f.depositoBancoCta.id = " + idBanco
				+ " and (v.fecha >= '"
				+ desde_
				+ "' and v.fecha <= '"
				+ hasta_
				+ "')" + " order by v.fecha desc";
		return this.hql(query);
	}
	
	/**
	 * @return los cheques emitidos segun banco.. 
	 * [0]:concepto
	 * [1]:fecha 
	 * [2]:numero 
	 * [3]:totalImporteGs 
	 * [4]:banco 
	 * [5]:origen
	 */
	public List<Object[]> getChequesPropiosPorBanco(long idBanco, Date desde, Date hasta) throws Exception {
		String desde_ = Utiles.getDateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = Utiles.getDateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select (select descripcion from TipoMovimiento where sigla = '" + Configuracion.SIGLA_TM_EMISION_CHEQUE + "'),"
				+ " COALESCE(b.fechaCobro, b.fechaVencimiento), b.numero, b.monto, b.banco.banco.descripcion, concat(b.numeroCaja, ' - ', b.numeroOrdenPago, ' - ', b.beneficiario)"
				+ " from BancoCheque b where"
				+ " b.dbEstado != 'D' and b.estadoComprobante.sigla != '" + Configuracion.SIGLA_ESTADO_COMPROBANTE_ANULADO + "'"
				+ " and b.anulado = 'FALSE'"
				+ " and b.banco.id = " + idBanco
				+ " and ((b.fechaCobro >= '" + desde_ + "' and b.fechaCobro <= '" + hasta_ + "' and b.cobrado = 'TRUE')"
				+ " or (b.fechaVencimiento >= '" + desde_ + "' and b.fechaVencimiento <= '" + hasta_ + "' and b.cobrado = 'FALSE'))" 
				+ " order by b.fechaCobro desc";
		return this.hql(query);
	}
	
	/**
	 * @return los cheques rechazados segun banco.. 
	 * [0]:concepto
	 * [1]:fecha 
	 * [2]:numero 
	 * [3]:totalImporteGs 
	 * [4]:banco deposito 
	 * [5]:observacion
	 */
	public List<Object[]> getChequesRechazadosPorBancoPorDeposito(long idBanco, Date desde, Date hasta) throws Exception {
		String desde_ = Utiles.getDateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = Utiles.getDateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select (select descripcion from TipoMovimiento where sigla = '" + Configuracion.SIGLA_TM_CHEQUE_RECHAZADO + "'), "
				+ " b.fechaRechazo, b.numero, b.monto, d.nroCuenta.banco.descripcion, concat(b.observacion, ' - DEPOSITO: ', b.numeroDeposito, ' - ', b.cliente.empresa.razonSocial)"
				+ " from BancoChequeTercero b, BancoBoletaDeposito d where"
				+ " b.numeroDeposito = d.numeroBoleta and"
				+ " b.rechazado = 'true' and b.dbEstado != 'D'"
				+ " and d.nroCuenta.id = " + idBanco
				+ " and (b.fechaRechazo >= '"
				+ desde_
				+ "' and b.fechaRechazo <= '"
				+ hasta_
				+ "')" + " order by b.fechaRechazo desc";
		return this.hql(query);
	}
	
	/**
	 * @return los cheques rechazados segun banco.. 
	 * [0]:concepto
	 * [1]:fecha 
	 * [2]:numero 
	 * [3]:totalImporteGs 
	 * [4]:banco deposito 
	 * [5]:observacion
	 */
	public List<Object[]> getChequesRechazadosPorBancoPorDescuento(long idBanco, Date desde, Date hasta) throws Exception {
		String desde_ = Utiles.getDateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = Utiles.getDateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select (select descripcion from TipoMovimiento where sigla = '" + Configuracion.SIGLA_TM_CHEQUE_RECHAZADO + "'), "
				+ " b.fechaRechazo, b.numero, b.monto, d.banco.banco.descripcion, concat(b.observacion, ' - DESCUENTO: ', b.numeroDescuento, ' - ', b.cliente.empresa.razonSocial)"
				+ " from BancoChequeTercero b, BancoDescuentoCheque d where"
				+ " replace(b.numeroDescuento, 'PRESTAMO ', '') = cast (d.id as string) and"
				+ " b.rechazado = 'true' and b.dbEstado != 'D'"
				+ " and d.banco.id = " + idBanco
				+ " and (b.fechaRechazo >= '"
				+ desde_
				+ "' and b.fechaRechazo <= '"
				+ hasta_
				+ "')" + " order by b.fechaRechazo desc";
		return this.hql(query);
	}
	
	/**
	 * @return los articulos sin movimiento..
	 * [0]: idarticulo
	 * [1]: codigointerno
	 * [2]: descripcion
	 */
	public List<Object[]> getArticulosSinMovimiento(Date desde, Date hasta) throws Exception {
		String desde_ = Utiles.getDateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = Utiles.getDateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select a.id, a.codigoInterno, a.descripcion from Articulo a where a.id NOT IN"
				+ " (select d.articulo.id from Venta v join v.detalles d where"
				+ " d.articulo.servicio = 'FALSE' and"
				+ " v.estadoComprobante is null and"
				+ " (v.fecha > '" + desde_ + "' and v.fecha < '" + hasta_ + "'))";
		return this.hql(query);
	}
	
	/**
	 * @return el ruteo de vendedores..
	 * [0]:vendedor
	 * [1]:cliente
	 * [2]:latitud
	 * [3]:longitud
	 */
	public List<Object[]> getRuteoVendedores(Date desde, Date hasta) throws Exception {
		String desde_ = Utiles.getDateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = Utiles.getDateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select r.usuarioMod, r.auxi, r.orden, r.echo from Ping r where"
				+ " (r.modificado > '" + desde_ + "' and r.modificado < '" + hasta_ + "')";
		return this.hql(query);
	}
	
	/**
	 * @return los saldos de prestamos a bancos..
	 */
	public List<CtaCteEmpresaMovimiento> getPrestamosBancariosConSaldo(Date desde, Date hasta) throws Exception {
		String desde_ = Utiles.getDateToString(desde, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta_ = Utiles.getDateToString(hasta, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select c from CtaCteEmpresaMovimiento c where"
				+ " c.saldo > 0 and c.anulado = 'FALSE' and"
				+ " c.tipoMovimiento.sigla = '" + Configuracion.SIGLA_TM_PRESTAMO_BANCARIO + "' and"
				+ " (c.fechaVencimiento > '" + desde_ + "' and c.fechaVencimiento < '" + hasta_ + "')";
		return this.hql(query);
	}
	
	/**
	 * @return la fecha de la ultima venta del cliente..
	 */
	public List<Date> getUltimaVenta(long idCliente) throws Exception {
		String query = "select max(v.fecha) from Venta v where v.dbEstado != 'D' and (v.tipoMovimiento.sigla = ? or v.tipoMovimiento.sigla = ?)"
				+ " and v.cliente.id = ?";
		
		List<Object> listParams = new ArrayList<Object>();
		listParams.add(Configuracion.SIGLA_TM_FAC_VENTA_CONTADO);
		listParams.add(Configuracion.SIGLA_TM_FAC_VENTA_CREDITO);
		listParams.add(idCliente);

		Object[] params = new Object[listParams.size()];
		for (int i = 0; i < listParams.size(); i++) {
			params[i] = listParams.get(i);
		}

		return this.hql(query, params);
	}
	
	/**
	 * @return los registros promo 1..
	 */
	public List<VentaPromo1> getVentasPromo1() throws Exception {
		String query = "select v from VentaPromo1 v order by v.nombreApellido";
		return this.hql(query);
	}
	
	/**
	 * @return los reportes favoritos por usuario..
	 */
	public List<ReporteFavoritos> getReporteFavoritos(String usuario) throws Exception {
		String query = "select r from ReporteFavoritos r where r.usuario = '" + usuario + "'";
		return this.hql(query);	
	}
	
	/**
	 * @return reporte favorito por usuario..
	 */
	public ReporteFavoritos getReporteFavorito(String usuario, String codigo) throws Exception {
		String query = "select r from ReporteFavoritos r where r.usuario = '" + usuario + "'";
		List<ReporteFavoritos> list = this.hql(query);
		return list.size() > 0 ? list.get(0) : null;
	}
	
	/**
	 * @return los gastos segun importacion.. 
	 * [0]:id
	 * [1]:fecha 
	 * [2]:vencimiento 
	 * [3]:numeroFactura 
	 * [4]:concepto 
	 * [5]:proveedor
	 * [6]:importe
	 */
	public List<Object[]> getGastosDeImportacion(long idImportacion) throws Exception {
		if (idImportacion < 0) {
			return new ArrayList<Object[]>();
		}
		String query = "select g.id, g.fecha, g.vencimiento, g.numeroFactura, g.tipoMovimiento.descripcion, g.proveedor.empresa.razonSocial, g.importeGs"
				+ " from Gasto g where g.idImportacion = " + idImportacion
				+ " order by g.fecha desc";
		return this.hql(query);
	}
	
	/**
	 * @return los gastos segun importacion.. 
	 * [0]:id
	 * [1]:numeroFactura 
	 * [2]:d.cuenta 
	 * [3]:d.montoGs
	 */
	public List<Object[]> getGastosDeImportacionDetallado(long idImportacion) throws Exception {
		if (idImportacion < 0) {
			return new ArrayList<Object[]>();
		}
		String query = "select g.id, g.numeroFactura, d.articuloGasto.cuentaContable.descripcion, d.montoGs"
				+ " from Gasto g join g.detalles d where g.idImportacion = " + idImportacion
				+ " order by d.articuloGasto.cuentaContable.descripcion desc";
		return this.hql(query);
	}
	
	/**
	 * @return la linea de credito temporal..
	 */
	public List<HistoricoLineaCredito> getHistoricoLineaCredito(long idCliente, boolean temporal) throws Exception {
		Date fecha = new Date();
		String desde = Utiles.getDateToString(fecha, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta = Utiles.getDateToString(fecha, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select l from HistoricoLineaCredito l where l.idCliente = " + idCliente
				+ " and l.activo = true"
				+ " and l.temporal = " + temporal
				+ " and (l.fecha > '" + desde + "' and l.fecha < '" + hasta + "') order by l.fecha asc";
		return this.hql(query);
	}
	
	/**
	 * @return la linea de credito..
	 */
	public List<HistoricoLineaCredito> getHistoricoLineaCredito(long idCliente) throws Exception {
		Date fecha = new Date();
		String desde = Utiles.getDateToString(fecha, Misc.YYYY_MM_DD) + " 00:00:00";
		String hasta = Utiles.getDateToString(fecha, Misc.YYYY_MM_DD) + " 23:59:00";
		String query = "select l from HistoricoLineaCredito l where l.idCliente = " + idCliente
				+ " and l.activo = true"
				+ " and (l.fecha > '" + desde + "' and l.fecha < '" + hasta + "') order by l.fecha asc";
		return this.hql(query);
	}
	
	/**
	 * @return los datos de articulo.. 
	 * [0]:id
	 * [1]:codigoInterno
	 * [2]:descripcion
	 * [3]:costoGs
	 * [4]:costoDs
	 * [5]:precioGs
	 */
	public Object[] getArticulo(long idArticulo) throws Exception {
		if (idArticulo < 0) {
			return null;
		}
		String query = "select a.id, a.codigoInterno, a.descripcion, a.costoGs, a.costoDs, a.precioGs"
				+ " from Articulo a where a.id = " + idArticulo;
		List<Object[]> list = this.hql(query);
		return list.size() > 0 ? list.get(0) : null;
	}
	
	/**
	 * @return la ultima cotizacion..
	 */
	public double getTipoCambioVenta() throws Exception {
		String query = "select t.id, t.venta from TipoCambio t where t.id = (select max(id) from TipoCambio)";
		List<Object[]> list = this.hql(query);
		return (double) list.get(0)[1];
	}
	
	/**
	 * @return las chequeras..
	 */
	public List<BancoChequera> getChequeras(long idBanco) throws Exception {
		String query = "select b from BancoChequera b where b.banco.id = " + idBanco;
		return this.hql(query);
	}
	
	/**
	 * @return el rubro empresa..
	 */
	public EmpresaRubro getEmpresaRubro(String descripcion) throws Exception {
		String query = "select r from EmpresaRubro r where r.descripcion = '" + descripcion + "'";
		List<EmpresaRubro> list = this.hql(query);
		return list.size() > 0 ? list.get(0) : null;
	}
	
	/**
	 * @return ArticuloFamilia..
	 */
	public ArticuloFamilia getArticuloFamilia(String descripcion) throws Exception {
		String query = "select f from ArticuloFamilia f where upper(f.descripcion) = '" + descripcion.toUpperCase() + "'";
		List<ArticuloFamilia> list = this.hql(query);
		return list.size() > 0 ? list.get(0) : null;
	}
	
	/**
	 * @return ArticuloGrupo..
	 */
	public ArticuloGrupo getArticuloGrupo(String descripcion) throws Exception {
		String query = "select f from ArticuloGrupo f where upper(f.descripcion) = '" + descripcion.toUpperCase() + "'";
		List<ArticuloGrupo> list = this.hql(query);
		return list.size() > 0 ? list.get(0) : null;
	}

	/**
	 * @return ArticuloMarca..
	 */
	public ArticuloMarca getArticuloMarca(String descripcion) throws Exception {
		String query = "select f from ArticuloMarca f where upper(f.descripcion) = '" + descripcion.toUpperCase() + "'";
		List<ArticuloMarca> list = this.hql(query);
		return list.size() > 0 ? list.get(0) : null;
	}
	
	/**
	 * @return ArticuloAplicacion..
	 */
	public ArticuloAplicacion getArticuloAplicacion(String descripcion) throws Exception {
		String query = "select f from ArticuloAplicacion f where upper(f.descripcion) = '" + descripcion.toUpperCase() + "'";
		List<ArticuloAplicacion> list = this.hql(query);
		return list.size() > 0 ? list.get(0) : null;
	}
	
	/**
	 * @return ArticuloModelo..
	 */
	public ArticuloModelo getArticuloModelo(String descripcion) throws Exception {
		String query = "select f from ArticuloModelo f where upper(f.descripcion) = '" + descripcion.toUpperCase() + "'";
		List<ArticuloModelo> list = this.hql(query);
		return list.size() > 0 ? list.get(0) : null;
	}
	
	/**
	 * @return ArticuloLinea..
	 */
	public ArticuloLinea getArticuloLinea(String descripcion) throws Exception {
		String query = "select f from ArticuloLinea f where upper(f.descripcion) = '" + descripcion.toUpperCase() + "'";
		List<ArticuloLinea> list = this.hql(query);
		return list.size() > 0 ? list.get(0) : null;
	}
	
	/**
	 * @return ArticuloSubLinea..
	 */
	public ArticuloSubLinea getArticuloSubLinea(String descripcion) throws Exception {
		String query = "select f from ArticuloSubLinea f where upper(f.descripcion) = '" + descripcion.toUpperCase() + "'";
		List<ArticuloSubLinea> list = this.hql(query);
		return list.size() > 0 ? list.get(0) : null;
	}
	
	/**
	 * @return ArticuloSubGrupo..
	 */
	public ArticuloSubGrupo getArticuloSubGrupo(String descripcion) throws Exception {
		String query = "select f from ArticuloSubGrupo f where upper(f.descripcion) = '" + descripcion.toUpperCase() + "'";
		List<ArticuloSubGrupo> list = this.hql(query);
		return list.size() > 0 ? list.get(0) : null;
	}
	
	/**
	 * @return ArticuloAPI..
	 */
	public ArticuloAPI getArticuloAPI(String descripcion) throws Exception {
		String query = "select f from ArticuloAPI f where upper(f.descripcion) = '" + descripcion.toUpperCase() + "'";
		List<ArticuloAPI> list = this.hql(query);
		return list.size() > 0 ? list.get(0) : null;
	}
	
	/**
	 * @return ArticuloProcedencia..
	 */
	public ArticuloProcedencia getArticuloProcedencia(String descripcion) throws Exception {
		String query = "select f from ArticuloProcedencia f where upper(f.descripcion) = '" + descripcion.toUpperCase() + "'";
		List<ArticuloProcedencia> list = this.hql(query);
		return list.size() > 0 ? list.get(0) : null;
	}
	
	/**
	 * @return ArticuloMarca segun familia..
	 */
	public List<ArticuloMarca> getArticuloMarcas(String familia) throws Exception {
		String query = "select f from ArticuloMarca f where upper(f.familia) = '" + familia.toUpperCase() + "'";
		List<ArticuloMarca> list = this.hql(query);
		return list;
	}
	
	/**
	 * @return los rucs..
	 */
	public RucSet getRucValido(String ruc) throws Exception {
		String query = "select r from RucSet r where r.ruc like '" + ruc + "%";
		List<RucSet> list = this.hql(query);
		return list.size() > 0 ? list.get(0) : null;
	}
	
	/**
	 * @return ArticuloFamilias..
	 */
	public List<ArticuloFamilia> getArticuloFamilias() throws Exception {
		String query = "select f from ArticuloFamilia f order by f.descripcion";
		List<ArticuloFamilia> list = this.hql(query);
		return list;
	}
	
	/**
	 * @return EmpresaRubros..
	 */
	public List<EmpresaRubro> getEmpresaRubros() throws Exception {
		String query = "select r from EmpresaRubro r order by r.descripcion";
		List<EmpresaRubro> list = this.hql(query);
		return list;
	}
	
	/**
	 * @return los clientes..
	 */
	public List<Cliente> getClientesPorRubro(String rubro, String ruc, String razonsocial) throws Exception {
		String query = "select c from Cliente c where lower(c.empresa.razonSocial) like '%" 
				+ razonsocial.toLowerCase() + "%'"
				+ " and c.empresa.ruc like '%" + ruc + "%'"
				+ " and c.empresa.rubro.descripcion = '" + rubro + "'"
				+ " order by c.empresa.razonSocial";
		return this.hqlLimit(query, 100);
	}
	
	/**
	 * @return los vendedores..
	 */
	public List<Vendedor> getVendedores_() throws Exception {
		String query = "select v from Vendedor v order by v.nombre";
		return this.hql(query);
	}
	
	/**
	 * @return el vendedor..
	 */
	public Vendedor getVendedor_(String nombreFuncionario) throws Exception {
		String query = "select v from Vendedor v where v.funcionario = '" + nombreFuncionario + "'";
		List<Vendedor> list = this.hql(query);
		return list.size() > 0 ? list.get(0) : null;
	}
	
	/**
	 * @return el vendedor..
	 */
	public Vendedor getVendedor(String nombre) throws Exception {
		String query = "select v from Vendedor v where v.nombre = '" + nombre + "'";
		List<Vendedor> list = this.hql(query);
		return list.size() > 0 ? list.get(0) : null;
	}
	
	/**
	 * @return el ArticuloListaPrecio..
	 */
	public ArticuloListaPrecio getArticuloListaPrecio(String nombre) throws Exception {
		String query = "select a from ArticuloListaPrecio a where a.descripcion = '" + nombre + "'";
		List<ArticuloListaPrecio> list = this.hql(query);
		return list.size() > 0 ? list.get(0) : null;
	}
	
	/**
	 * @return el deposito..
	 */
	public Deposito getDepositoById(long id) throws Exception {
		return (Deposito) getObject(com.yhaguy.domain.Deposito.class.getName(), id);
	}
	
	/**
	 * @return la empresa..
	 */
	public Empresa getEmpresa(String razonsocial) throws Exception {
		String query = "select e from Empresa e where e.razonSocial = '" + razonsocial + "'";
		List<Empresa> list = this.hql(query);
		return list.size() > 0 ? list.get(0) : null;
	}
	
	public static void main(String[] args) {
		try {
			RegisterDomain rr = RegisterDomain.getInstance();
			String src =  "./WEB-INF/docs/migracion/central/PRECIOS_.csv";
			
			String[][] cab = { { "Empresa", CSV.STRING } };			
			String[][] det = { { "CODIGOINTERNO", CSV.STRING }, { "LISTA", CSV.STRING }, { "TEST", CSV.STRING } };
			
			CSV csv = new CSV(cab, det, src);
			csv.start();
			while (csv.hashNext()) {
				String codigo = csv.getDetalleString("CODIGOINTERNO");
				String lista = csv.getDetalleString("LISTA");
				
				Articulo art = rr.getArticulo(codigo);
				art.setPrecioListaGs(Double.parseDouble(lista));
				rr.saveObject(art, "sys");
				
				System.out.println(art.getPrecioListaGs());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
