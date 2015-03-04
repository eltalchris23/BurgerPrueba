package com.mx.test.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class FotoForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected FormFile archivo;
	
	public FormFile getArchivo() {
		return archivo;
	}

	public void setArchivo(FormFile archivo) {
		this.archivo = archivo;
	}
}
