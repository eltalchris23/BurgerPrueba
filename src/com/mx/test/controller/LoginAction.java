package com.mx.test.controller;

//import java.io.PrintWriter;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.mx.test.bo.UsuariosBo;
import com.mx.test.bo.UsuariosBoImpl;
import com.mx.test.form.LoginForm;

public class LoginAction extends Action {
	private UsuariosBo usuariobo = new UsuariosBoImpl();
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		System.out.println("Entre al controller");
		String user;
		String pass;
		boolean resp = false;
		int valor = 0;
		
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = null;
		
		LoginForm frmLogin = (LoginForm) form;
		
		user = frmLogin.getUsuario();
		pass = frmLogin.getClave();
		
		try {
			// consumo el Bo
			resp = this.usuariobo.buscarUsuario(user, pass);
			
			out = response.getWriter();
			
			System.out.println("Respuesta del bo " + resp);
			
			if (resp) {
				System.out.println("Logueado");
				HttpSession sesionActual = request.getSession(true);
				sesionActual.setAttribute("USER", user);
				
				valor = 1;
				//forward = mapping.findForward("logueado");
			} else {
				System.out.println("No logueado");
				//forward = mapping.findForward("logueo");
			}
			out.println(valor);
		} catch (Exception e) {
			// TODO: handle exception
		}
		out.flush();
		out.close();
		return null;
	}
}
