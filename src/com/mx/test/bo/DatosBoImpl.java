package com.mx.test.bo;

import java.util.ArrayList;
import java.util.List;

import com.mx.test.bean.DatosBean;
import com.mx.test.dao.DatosDao;
import com.mx.test.dao.DatosDaoImpl;
import com.mx.test.form.DatosForm;

public class DatosBoImpl implements DatosBo {
	private DatosDao datosDao;
	
	public DatosBoImpl() {
		datosDao = new DatosDaoImpl();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<DatosForm> buscarTodos() {
		List<DatosForm> registrosForm = new ArrayList<DatosForm>();
		DatosForm datosform = null;
		
		List<DatosBean> registros = this.datosDao.buscarTodos();
		
		for (DatosBean lista : registros) {
			datosform = new DatosForm();
			datosform.setId(lista.getId());
			datosform.setReporte(lista.getReporte());
			datosform.setRuta(lista.getRuta());
			datosform.setFecha_generacion(lista.getFecha_generacion());
			datosform.setUsuario(lista.getUsuario_modifico());
			
			registrosForm.add(datosform);
		}
		return registrosForm;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean graba(DatosBean datosBean) {
		System.out.println("Entre al Bo");
		boolean respuesta = this.datosDao.graba(datosBean);
		return respuesta;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean modificar(DatosBean datos){
		boolean resultado = this.datosDao.modificar(datos);
		return resultado;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean eliminar(DatosBean datos) {
		boolean resultado = this.datosDao.eliminar(datos);
		return resultado;
	}
	
	public DatosForm buscarId(DatosBean datos){
		DatosBean registro = this.datosDao.buscarId(datos);
		
		// paso del bean al form
		DatosForm registroForm = new DatosForm();
		
		registroForm.setId(registro.getId());
		registroForm.setReporte(registro.getReporte());
		registroForm.setRuta(registro.getRuta());
		registroForm.setFecha_generacion(registro.getFecha_generacion());
		registroForm.setUsuario(registro.getUsuario_modifico());
		
		return registroForm;
	}
}
