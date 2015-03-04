package com.mx.test.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.mx.test.dao.DaoException;
import com.mx.test.bean.HibernateUtilss;
import com.mx.test.bean.DatosBean;

public class DatosDaoImpl implements DatosDao {
	
	/**
	 * {@inheritDoc}
	 */
	public List<DatosBean> buscarTodos() {
		List<DatosBean> registros = null; 
		try {
			Session session = HibernateUtilss.currentSession();
			registros = session.createCriteria(DatosBean.class).list();
				 
			HibernateUtilss.closeSession();
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return registros;
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	public boolean graba(DatosBean datosBean){
		System.out.println("Entre al Dao");
		Session session = HibernateUtilss.currentSession();
		Transaction tx = null;
		boolean rollback = true;
		boolean resultado = false;
		
		try {
			tx = session.beginTransaction();
			session.save(datosBean);
			tx.commit();
			rollback = false;
			resultado = true;
		} catch (Exception e) {
			// TODO: handle exception
			resultado = false;
			throw new DaoException(e);
		}finally {
			 if( rollback && tx != null){ 
		    	 tx.rollback();
			 }			 
			 HibernateUtilss.closeSession();
		}
		return resultado;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean modificar(DatosBean datos){
		Session session = HibernateUtilss.currentSession();
		Transaction tx = null;
		
		boolean resultado = false;
		boolean rollback = true;
		
		try {
			tx = session.beginTransaction();
			session.update(datos);
			tx.commit();
			rollback = false;
			resultado = true;
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if( rollback && tx != null){ 
		    	 tx.rollback();
			 }
			HibernateUtilss.closeSession();
		}
		return resultado;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean eliminar(DatosBean datos){
		Session session =HibernateUtilss.currentSession();
		Transaction tx = null;
		
		boolean resultado = false;
		boolean rollback = true;
		
		try {
			tx = session.beginTransaction();
			session.delete(datos);
			tx.commit();
			rollback = false;
			resultado = true;
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if( rollback && tx != null){ 
		    	 tx.rollback();
			 }
			HibernateUtilss.closeSession();
		}
		return resultado;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public DatosBean buscarId(DatosBean datos){
		DatosBean registro = null;
		
		try {
			Session session = HibernateUtilss.currentSession();
			registro = (DatosBean) session.createCriteria(DatosBean.class)
					.add(Restrictions.eq("id", datos.getId())).uniqueResult();
			
			HibernateUtilss.closeSession();
		} catch (Exception e) {
			// TODO: handle exception
			throw new DaoException(e);
		}
		return registro;
	}
}
