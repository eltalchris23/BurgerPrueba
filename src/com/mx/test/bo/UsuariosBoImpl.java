package com.mx.test.bo;

import com.mx.test.dao.UsuariosDao;
import com.mx.test.dao.UsuariosDaoImpl;

public class UsuariosBoImpl implements UsuariosBo {
	private UsuariosDao usuarioDao;
	
	public UsuariosBoImpl(){
		usuarioDao = new UsuariosDaoImpl();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean buscarUsuario(String usuario, String clave){
		System.out.println("Entre al Bo");
		boolean resp = false;
		
		resp = this.usuarioDao.buscarUsuario(usuario, clave);
		
		
		return resp;
	}
}
