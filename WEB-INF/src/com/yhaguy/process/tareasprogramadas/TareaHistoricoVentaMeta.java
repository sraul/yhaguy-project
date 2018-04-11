package com.yhaguy.process.tareasprogramadas;

import com.yhaguy.process.ProcesosHistoricos;

public class TareaHistoricoVentaMeta {


	public static void main(String[] args) {
		try {
			ProcesosHistoricos.addHistoricoVentaMetaSucursal(new Double(2700000000L));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
