
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:html>
<head>
<title><bean:message key="welcome.title"/></title>
<html:base/>
</head>
<body bgcolor="white">
<h1>Hola desde Struts 1!!! :) Welcome!!</h1>
<bean:write name="datosForm" property="id"/>
<bean:write name="datosForm" property="reporte" />
<bean:write name="datosForm" property="ruta"/>
<%-- 
<bean:write name="fotoBean" property="archivo"/> --%>
</body>
</html:html>
