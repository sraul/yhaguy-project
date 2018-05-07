package com.yhaguy.gestion.bancos.conciliacion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.annotation.DependsOn;

import com.coreweb.dto.DTO;
import com.coreweb.util.MyPair;
import com.yhaguy.gestion.bancos.libro.BancoCtaDTO;

@SuppressWarnings("serial")
public class BancoExtractoDTO extends DTO {
	
	private String numero = "";
	private Date desde;
	private Date hasta;
	private boolean cerrado;
	
	private List<BancoExtractoDetalleDTO> detalles2 = new ArrayList<BancoExtractoDetalleDTO>();
	private MyPair sucursal;
	private BancoCtaDTO banco;
	
	/**
	 * @return en un map los numeros de movimientos..
	 */
	public Map<String, String> getNumeros() {
		Map<String, String> out = new HashMap<String, String>();
		for (BancoExtractoDetalleDTO item : this.detalles2) {
			out.put(item.getNumero(), item.getNumero());
			if (!item.getAuxi().isEmpty()) {
				out.put(item.getAuxi(), item.getAuxi());
			}
		}
		return out;
	}
	
	@DependsOn("detalles2")
	public double getTotalDebe() {
		double out = 0;
		for (BancoExtractoDetalleDTO item : this.detalles2) {
			out += item.getDebe();
		}
		return out;
	}
	
	@DependsOn("detalles2")
	public double getTotalHaber() {
		double out = 0;
		for (BancoExtractoDetalleDTO item : this.detalles2) {
			out += item.getHaber();
		}
		return out;
	}
	
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
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

	public boolean isCerrado() {
		return cerrado;
	}

	public void setCerrado(boolean cerrado) {
		this.cerrado = cerrado;
	}

	public MyPair getSucursal() {
		return sucursal;
	}

	public void setSucursal(MyPair sucursal) {
		this.sucursal = sucursal;
	}

	public BancoCtaDTO getBanco() {
		return banco;
	}

	public void setBanco(BancoCtaDTO banco) {
		this.banco = banco;
	}

	public List<BancoExtractoDetalleDTO> getDetalles2() {
		// ordena la lista segun fecha..
		Collections.sort(detalles2, new Comparator<BancoExtractoDetalleDTO>() {
			@Override
			public int compare(BancoExtractoDetalleDTO o1, BancoExtractoDetalleDTO o2) {
				String nro1 = o1.getNumero();
				String nro2 = o2.getNumero();
				int compare = nro1.compareTo(nro2);
				if (compare == 0) {
					Date fecha1 = o1.getFecha();
					Date fecha2 = o2.getFecha();
		            return fecha1.compareTo(fecha2);
		        }
		        else {
		            return compare;
		        }
			}
		});
		return detalles2;
	}

	public void setDetalles2(List<BancoExtractoDetalleDTO> detalles2) {
		this.detalles2 = detalles2;
	}
}
