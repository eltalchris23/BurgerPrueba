package com.mx.test.bo;

import java.util.List;

import com.mx.test.bean.DatosBean;
import com.mx.test.form.DatosForm;

public interface DatosBo {
	
	/**
	 * Este metodo trae todos los registros de una tabla
	 * @return Regresa una lista de tipo DatosForm vacia o con los registros encontrados
	 */
	List<DatosForm> buscarTodos();
	
	/**
	 * Este metodo inserta un registro
	 * @param datosBean Recibe un obj de tipo Bean
	 * @return un booleano
	 */
	boolean graba(DatosBean datosBean);
	
	/**
	 * Este metodo elimina un registro
	 * @param datos Recibe un obj de tipo Bean
	 * @return un booleano
	 */
	boolean eliminar(DatosBean datos);
	
	/**
	 * Este metodo busca un registro
	 * @param datos Recibe un parametro de tipo Bean
	 * @return regresa un Form
	 */
	DatosForm buscarId(DatosBean datos);
	
	/**
	 * Este metodo actualiza un regitro
	 * @param datos Recie un parametro de tipo Bean
	 * @return regresa un booleano
	 */
	boolean modificar(DatosBean datos);
}
