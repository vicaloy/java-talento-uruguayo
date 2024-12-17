<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">

<style>
#wrapper {
  text-align: center;
}
#yourdiv {
  display: inline-block;
}
* {
  box-sizing: border-box;
}

html {
  overflow-y: scroll;
min-width: 200px;
height: 200px;
}

.column {
  float: left;
  width: 33.33%;
  padding: 10px;
  height: 300px;
}


.row:after {
  content: "";
  display: table;
  clear: both;
  align:"center";
  
 

</style>
<title> Talento Uruguayo </title>
</head>
<body>



<div id="wrapper">    
    <div id="yourdiv"  style="height:1000px;font-size:36px">
    	<img src="imagenes/talento_logo.png" width="500"/>
    	<h3>Bienvenido a Talento Uruguayo</h3>
    	<p id="voto"></p>
    	<div class="row">
    	<center>
  		<div id="yourdiv" style="background-color:#aaa;" align="center">
    		<form action='Ingreso' method='POST'>
      			<p><input type='submit' value='INGRESAR'></p>
    		</form>
  		</div>
  		</center>
	</div>
	
	
    </div>
</div>
</body>
</html>