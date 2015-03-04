<!DOCTYPE html>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<html:html lang="es">
	<head>
		<title>Hamburguesas</title>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="<c:url value='http://cdn.datatables.net/1.10.5/css/jquery.dataTables.css'/>">
		<link rel="stylesheet" href="<c:url value='http://code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css'/>">
		<link rel="stylesheet" href="<c:url value='css/style.css'/>">
		<script src="<c:url value='http://code.jquery.com/jquery-1.11.1.min.js'/>"></script>
		<%-- <script src="<c:url value='http://cdn.datatables.net/1.10.5/js/jquery.dataTables.min.js'/>"></script> --%>
		<script src="<c:url value='js/dataTables.js'/>"></script>
		<script src="<c:url value='http://code.jquery.com/ui/1.11.2/jquery-ui.js'/>"></script>
		<script src="<c:url value='js/validate.js'/>"></script>
		<script src="<c:url value='js/functions.js'/>"></script>
		
	</head>
	<%
		String userr = (String) session.getAttribute("USER");
		if (userr==null) { %>
			<jsp:forward page="error.jsp"/>
	<%	} else { %>
			<h1>
				Bienvenido: <%= userr %>
			</h1>
		<%
		}
	%>
	<%-- 
	<c:set var="user" scope="session" value="<%= userr %>" />
	
	<c:choose>
		<c:when test="${user}">
			<h1>
				<c:out value="${user}" />
			</h1>
		</c:when>
		<c:otherwise>
			<c:redirect url="http://localhost:8080/strutsTest/error.jsp"/>
		</c:otherwise>
	</c:choose>
	
	--%>
	
	<div id="dialogConfirm" style="display: none;" title="Eliminar Registro">
		<p>Esta seguro de eliminar el registro?</p>
	</div>
	
	<div id="dialogForm" style="display: none;" title="Agregar Registro">
		<%-- enctype="multipart/form-data" --%>
		<form id="frmDatos" action="formulario" method="post">
			<input type="text" id="id" name="id" placeholder="Teclee Id"/>
			<input type="text" id="reporte" name="reporte" placeholder="Teclee Reporte"/>
			<input type="text" id="ruta" name="ruta" placeholder="Teclee ruta"/>
			<input type="hidden" id="tipoMetodo" name="tipoMetodo">
			<%-- 
			<input type="file" id="archivo" name="archivo" />
			--%>
			
		</form>
	</div>
	<input type="button" id="showDialog" class="addButton" value="Agregar registro" />
	<%-- <input type="button" id="addRow" class="addButton" value="Agregar fila" /> --%>
	<input type="button" id="updateRow" class="addButton" value="Modificar registro" />
	<input type="button" id="deleteRow" class="deleteButton" value="Eliminar registro" />
	
	<input type="button" id="closeSession" class="logOff" value="Cerrar Sesion" />
	
	<table id="tblDatos" class="display" cellspacing="0" width="70%">
		<thead>
			<tr>
				<th>Id</th>
				<th>Reporte</th>
				<th>Ruta</th>
				<th>Imagen</th>
			</tr>
		</thead>
		<tfoot>
            <tr>
                <th>Id</th>
				<th>Reporte</th>
				<th>Ruta</th>
				<th>Imagen</th>
            </tr>
        </tfoot>
        <tbody>
        </tbody>
	</table>
</html:html>


