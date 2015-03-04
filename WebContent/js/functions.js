$(document).ready(function() {
	
	// esta validacion la hago para saber si esta la Datatable o no
	// y dependiendo del resultado ejecute algunas funciones o no
	if ($('#tblDatos').length){
		console.log("Existe");
		
		var t = $('#tblDatos').DataTable({
			"pagingType": "full_numbers",
			"aLengthMenu" : [[10], [10]]
		});
		var indice = 0;
		
		// carga todos los registros de la DB
			var queryString = crea_query_string("cargaRegistros");
			var urll = "http://localhost:8080/strutsTest/formulario.do?" + queryString;
			
			$.post(urll,function(resp){
				console.log(resp);
				
				// convertimos el Json a Obj de JavaScript
				var objScript = $.parseJSON(resp);
				numRegistros = objScript.length;
				
				var nuevaFila="<tr>";
				
				for (var i = 0; i < objScript.length; i++){
					// añadimos las columnas
		            t.row.add( [
			            "<a data-identificador='"+objScript[i].id+"' class='modificar'>"+objScript[i].id+"</a>",
			            objScript[i].reporte,
			            objScript[i].ruta,
			            "Imagen"
			        ] ).draw();
				}
			});
			
		
		// Diálogo insertar/modificar
		$('#dialogForm').dialog({
			autoOpen:false,
			modal:true,
			width:450,
			height:'auto',
			"iDisplayLength": 10,
			buttons: {
				"Save": function(){
					
					$("#frmDatos").submit();
				},
		        Cancel: function() {
		        	// limpia los inputs
		        	limpiarInputs();
		        	$( this ).dialog( "close" );
		        }
			}
		});
		
		// dialogo confirmacion para eliminar
		$('#dialogConfirm').dialog({
			autoOpen:false,
			modal:true,
			width:450,
			height:'auto',
			"iDisplayLength": 10,
			buttons: {
		        Cancel: function() {
		        	$( this ).dialog( "close" );
		        },
		        "Eliminar": function(){
		        	eliminarRegistro();
		        	$( this ).dialog( "close" );
				}
			}
		});
		
		var counter = 1;
		
		$('#addRow').on( 'click', function () {
			console.log("Entre " + counter);
	        t.row.add( [
	            "<a class='modificar' data-identificador='"+counter+"'>"+counter+"</a>"+'.1',
	            counter +'.2',
	            counter +'.3',
	            counter +'.4'
	        ] ).draw();
	 
	        counter++;
	    } );
	 
	    // Automatically add a first row of data
	    //$('#addRow').click();
		
		// desactiva el boton de eliminar registro
		$("#deleteRow").attr("disabled", true);
		
		// desactiva el boton de modificar registro
		$("#updateRow").attr("disabled", true);
		
		// esto es para seleccionar
		
		$('#tblDatos tbody').on( 'click', 'tr', function () {
	        if ( $(this).hasClass('selected') ) {
	            $(this).removeClass('selected');
	            $("#deleteRow").attr("disabled", true);
	            $("#updateRow").attr("disabled", true);
	        }
	        else {
	            t.$('tr.selected').removeClass('selected');
	            $(this).addClass('selected');
	            
	            $("#deleteRow").attr("disabled", false);
	            $("#updateRow").attr("disabled", false);
	            
	            
	            var dato = t.row(this).data();
	            
	            // obtengo el indice de la fila seleccionada
		        indice = t.row(this).index();
		        
	            console.log("holaS" + dato[2] + " Indice " + indice);
	        }
	    });
		
		$("#aceptar").on("click", function(){
			console.log("Di click en aceptar");
			$("#frmLogin").submit();
		});
		
		$("#closeSession").on("click", function(){
			var queryString = crea_query_string("cerrarSesion");
			var url = "http://localhost:8080/strutsTest/formulario.do?" + queryString;
			
			$.get(url,function(resp){
				$(window).attr('location', 'http://localhost:8080/strutsTest/');
			});
			
		});
	   
	 
	    $('#deleteRow').click( function () {
	    	$(dialogConfirm).dialog('open');
	    });
		
		$('#updateRow').on("click", function(){
			console.log("Modificar la fila " + indice);
			
			$("#tipoMetodo").val("modificar");
			
			// obtengo el id del registro
			var xid = $('.selected .modificar').data("identificador");
			var queryString = crea_query_string("buscarId");
			var url = "http://localhost:8080/strutsTest/formulario.do?" + queryString;
			
			$.get(url,{id:xid},function(resp){
				console.log(resp);
				// paso el Json a un obj de JavaScript
				var reg = $.parseJSON(resp);

				$("#id").val(reg["id"]);
				$("#reporte").val(reg["reporte"]);
				$("#ruta").val(reg["ruta"]);
			});
			$(dialogForm).dialog('open');
			$("#dialogForm").attr("title","Modificar Registro");
			
			// desactivo el boton eliminar registro
			$("#deleteRow").attr("disabled", true);
			
			// desactivo el boton modificar registro
	        $("#updateRow").attr("disabled", true);
		});
		
		$('#showDialog').on("click", function(){
			// le asgina insertar al campo tipoMetodo para que sea que va ser insert
			$("#tipoMetodo").val("insertar");
			
			$(dialogForm).dialog('open');
		});
		
		// validator
		$("#frmDatos").validate({
			submitHandler: function(form) {
				
				$("#dialogForm").dialog( "close" );
				var xmetodo = $("#tipoMetodo").val();
				
				var queryString = crea_query_string("insertarAndModificar");
				var url = "http://localhost:8080/strutsTest/formulario.do?" + queryString;
				var data = $("#frmDatos").serialize();
				
				$.post(url,data,function(resp){
					
					if (xmetodo == "insertar") {
						var xid = $("#id").val();
						var xreporte = $("#reporte").val();
						var xruta = $("#ruta").val();
						
						if (resp) {
							t.row.add( [
					            "<a data-identificador='"+xid+"' class='modificar'>"+xid+"</a>",
					            xreporte,
					            xruta,
					            "Imagen"
					        ] ).draw();
							
							// limpio los valores
							xid = parseInt(xid) + 1;
							$("#id").val(xid);
							limpiarInputs();
						}
					} else {
						console.log("Regrese de Modificar");
						
						// actualizo la fila del datatable
						t.row(indice).data([$("#id").val(), $("#reporte").val(), $("#ruta").val(), 'imagen']);
						limpiarInputs();
					}
				});
			},
			rules:{
				id: {required:true, number:true},
				reporte: {required:true, minlength:5, maxlength:30},
				ruta: {required:true, minlength:5, maxlength:30}//,
				//archivo: {required:true}
			},
			messages:{
				id: {required:"Este campo es requerido",number:"Debes de poner un dato numérico"},
				reporte: {required:"Este campo es requerido", minlength:"Mínimo 5 caracteres", maxlength:"Máximo 30 caracteres"},
				ruta: {required:"Este campo es requerido", minlength:"Mínimo 5 caracteres", maxlength:"Máximo 30 caracteres"}//,
				//archivo: {required:"Este campo es requerido"}
			},
			errorElement: "div"
			/*errorPlacement: function(error, element) {
				error.appendTo(element.parent());
			}*/
		});
	} else {
		
		// validator Login
		$("#frmLogin").validate({
			submitHandler: function(form) {
				console.log("Hola");
				//form.submit();
				
				var url = "http://localhost:8080/strutsTest/login.do";
				var data = $("#frmLogin").serialize();
				
				$.post(url,data,function(resp){
					console.log("Regrese con: " + resp);
					
					if (resp == 1) {
						//window.location.href = "http://localhost:8080/strutsTest/inicio.jsp";
						console.log("Mando al Datatable");
						$(location).attr('href','http://localhost:8080/strutsTest/inicio.jsp'); 
					} else {
						$("#respuesta").addClass("letraMediana error").text("Usuario o clave incorrectaa");
					}
					
				}); 
				
			},
			rules:{
				usuario: {required:true, minlength:5, maxlength:25},
				clave: {required:true, minlength:5, maxlength:30}//,
			},
			messages:{
				usuario: {required:"Este campo es requerido", minlength:"Mínimo 5 caracteres", maxlength:"Máximo 25 caracteres"},
				clave: {required:"Este campo es requerido", minlength:"Mínimo 5 caracteres", maxlength:"Máximo 30 caracteres"}//,
			},
			errorElement: "div"
			/*errorPlacement: function(error, element) {
				error.appendTo(element.parent());
			}*/
		});
		
	}
	
	
	
	
	function eliminarRegistro(){
		
		// obtengo el id del registro
		var ide = $('.selected .modificar').data("identificador");
		
		console.log("Registro a eliminar con el id attr(data): " + ide);
		
		var queryString = crea_query_string("eliminar");
		var url = "http://localhost:8080/strutsTest/formulario.do?" + queryString;
		
		// manda a eliminar el registro a la DB
		$.get(url,{id:ide},function(resp){
			// Respuesta
			console.log(resp);
		})
		
		// quita la fila de la datatable
        t.row('.selected').remove().draw( false );
        
		// desactivo el boton eliminar registro
		$("#deleteRow").attr("disabled", true);
		
		// desactivo el boton modificar registro
        $("#updateRow").attr("disabled", true);
	}
	
	function crea_query_string(xmetodo){
		// el parametro nocache le asigno un numero aleatorio con Math.random() para que haga direncias entre las peticiones
		var query = "metodo="+xmetodo+"&nocache="+ Math.random();
		return query;
	}
	
	function limpiarInputs(){
		$("#reporte").val("");
		$("#ruta").val("");
	}
	
});