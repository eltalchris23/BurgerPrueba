package com.mx.test.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.mx.test.bean.HibernateUtilss;
import com.mx.test.bean.UsuariosBean;

public class UsuariosDaoImpl implements UsuariosDao {
	
	/**
	 * {@inheritDoc}
	 */
	public boolean buscarUsuario(String usuario, String clave){
		boolean resp = false; 
		try {
			Criteria criteria = HibernateUtilss.currentSession().createCriteria(UsuariosBean.class);
			 // agrego el criterio de fechas
			 criteria.add(Restrictions.eq("usuario", usuario))
					.add(Restrictions.eq("clave", clave)).list();
			 
			if ( !criteria.list().isEmpty()) {
				resp = true;
			}
			 
			HibernateUtilss.closeSession();
						
		} catch (Exception e) {
			// TODO: handle exception
			throw new DaoException(e);
		}
		
		return resp;
	}
}
