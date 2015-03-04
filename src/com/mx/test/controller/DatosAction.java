package com.mx.test.controller;

//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
//import org.apache.struts.upload.FormFile;

import org.apache.struts.action.SessionActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.google.gson.Gson;
import com.mx.test.bean.DatosBean;
import com.mx.test.bo.DatosBo;
import com.mx.test.bo.DatosBoImpl;
import com.mx.test.form.DatosForm;

//import com.mx.test.form.FotoForm;

public class DatosAction extends DispatchAction {
	private DatosBo datosBo = new DatosBoImpl();
	
	public ActionForward cargaRegistros(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		
		Gson gson = new Gson();
		
		try {
			out = response.getWriter();
			List<DatosForm> lista = new ArrayList<DatosForm>();
			
			lista = datosBo.buscarTodos();
			
			if (lista.isEmpty()) {
				System.out.println("Lista vacia");
			} else {
				// convierto la lista a json
				String json = gson.toJson(lista);
				
				out.println(json);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.flush();
		out.close();
		
		return null;
	}
	
	public ActionForward insertarAndModificar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		boolean resp = false;
		
		System.out.println("Controller");
		
		// casteo el objeto form al tipo FotoForm
		DatosForm fileForm = (DatosForm)form;
		
		DatosBean datosPruebas = new DatosBean();
		
		// esta var es para saber que metodo del Dao se va a consumir
		String xtipoMetodo = fileForm.getTipoMetodo();
		
		datosPruebas.setId(fileForm.getId());
		datosPruebas.setReporte(fileForm.getReporte());
		datosPruebas.setRuta(fileForm.getRuta());
		datosPruebas.setFecha_generacion(new Date());
		datosPruebas.setUsuario_modifico("Christian");
		
		response.setContentType("text/html;charset=UTF-8");
		
		// con el getWriter escribo la respuesta de salida del response
		PrintWriter out = null;
		try {
			out = response.getWriter();
			
			if (xtipoMetodo.equals("insertar")) {
				resp = this.datosBo.graba(datosPruebas);
			} else {
				resp = this.datosBo.modificar(datosPruebas);
			}
			
			out.println(resp);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Regresé del Bo, con la respuesta" + resp + " del metodo " + xtipoMetodo);
		out.flush();
		out.close();
		return null;
		/*
		
		
		//Get the FormFile object from ActionForm.
		FormFile file = fileForm.getArchivo();
		 
		//Obtengo el nombre del archivo
		String fileName = file.getFileName();
		 
		//Obtengo el tamaño del archivo
		Integer fileSize = file.getFileSize();
		 
		//Obtengo el tipo de archivo que se subió
		String contentType = file.getContentType();
		 
		
			// Con InputStream devuelve el inputstream de un archivo descargado
			InputStream inputFile = file.getInputStream();
			
			System.out.println("Nombre: " + fileName);
			System.out.println("Tamanio: " + fileSize);
			System.out.println("Tipo: " + contentType);
		*/
		
	}
	
	public ActionForward eliminar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		boolean respuesta = false;
		String msj;
		int xid = Integer.parseInt(request.getParameter("id"));
		DatosBean datos = new DatosBean();
		
		System.out.println("Parametro id recibido con el valor: " + xid);
		
		PrintWriter out = null;
		try {
			datos.setId(xid);
			
			//consumo el Bo
			respuesta = this.datosBo.eliminar(datos);
			if (respuesta) {
				msj = "Registro Eliminado";
			} else {
				msj = "No se pudo eliminar el registro";
			}
			out = response.getWriter();
			out.println(msj);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.flush();
		out.close();
		return null;
	}
	
	public ActionForward buscarId(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		boolean respuesta = false;
		int xid = Integer.parseInt(request.getParameter("id"));
		DatosBean datos = new DatosBean();
		DatosForm datosForm = new DatosForm();
		
		System.out.println("Parametro id recibido con el valor (buscarId): " + xid);
		
		PrintWriter out = null;
		try {
			
			datos.setId(xid);
			
			//consumo el Bo
			datosForm = this.datosBo.buscarId(datos);
			
			Gson gson = new Gson();
			String json = gson.toJson(datosForm);
			out = response.getWriter();
			out.println(json);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.flush();
		out.close();
		
		return null;
	}
	
	public ActionForward cerrarSesion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		HttpSession sesionActual = request.getSession(false);
		sesionActual.invalidate();
		sesionActual = null;
		
		forward = mapping.findForward("logueo");
		
		return forward;
	}
	
}
