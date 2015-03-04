package com.mx.test.form;

import org.apache.struts.action.ActionForm;

public class LoginForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String usuario;
	private String clave;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	
	
	
}
