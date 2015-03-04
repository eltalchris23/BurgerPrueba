package com.mx.test.dao;

import com.mx.test.bean.UsuariosBean;

public interface UsuariosDao {
	
	/**
	 * Este metodo busca un registro en la tabla de usuarios
	 * @param usuario es de tipo String
	 * @param clave es de tipo String
	 * @return un booleano
	 */
	boolean buscarUsuario(String usuario, String clave);
}
