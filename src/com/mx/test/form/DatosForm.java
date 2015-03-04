package com.mx.test.form;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class DatosForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String reporte;
	private String ruta;
	private Date fecha_generacion;
	private String usuario;
	private String tipoMetodo;
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.id = 0;
		this.reporte = "";
		this.ruta = "";
		this.usuario = "";
	}

	public String getReporte() {
		return reporte;
	}

	public void setReporte(String reporte) {
		this.reporte = reporte;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public Date getFecha_generacion() {
		return fecha_generacion;
	}

	public void setFecha_generacion(Date fecha_generacion) {
		this.fecha_generacion = fecha_generacion;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getTipoMetodo() {
		return tipoMetodo;
	}

	public void setTipoMetodo(String tipoMetodo) {
		this.tipoMetodo = tipoMetodo;
	}
}
