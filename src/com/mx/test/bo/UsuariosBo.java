package com.mx.test.bo;

import com.mx.test.form.LoginForm;

public interface UsuariosBo {
	
	/**
	 * Este metodo consume el Dao para buscar un registro
	 * @param usuario es de tipo String
	 * @param clave es de tipo String
	 * @return booleano
	 */
	boolean buscarUsuario(String usuario, String clave);
}
