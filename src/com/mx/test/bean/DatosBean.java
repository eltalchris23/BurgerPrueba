package com.mx.test.bean;

import java.util.Date;

public class DatosBean {
	private long id;
	private String reporte;
	private String ruta;
	private Date fecha_generacion;
	private String usuario_modifico;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getReporte() {
		return reporte;
	}
	public void setReporte(String reporte) {
		this.reporte = reporte;
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
	public String getUsuario_modifico() {
		return usuario_modifico;
	}
	public void setUsuario_modifico(String usuario_modifico) {
		this.usuario_modifico = usuario_modifico;
	}
	
}
