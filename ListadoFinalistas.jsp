<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id='datosFin1' scope='session' class='valueObjects.VOParticipanteFinalista' />
<jsp:useBean id='datosFin2' scope='session' class='valueObjects.VOParticipanteFinalista' />
<jsp:useBean id='datosFin3' scope='session' class='valueObjects.VOParticipanteFinalista' />
<jsp:useBean id='ganador' scope='session' class='valueObjects.VOParticipanteFinalista' />  

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
  height: 450px;
}


.row:after {
  content: "";
  display: table;
  clear: both;

</style>
<title> Talento Uruguayo </title>
</head>
<body>


<div id="wrapper">    
    <div id="yourdiv"  style="height:1000px;font-size:36px">
    	<img src="imagenes/talento_logo.png" width="500"/>
    	<h3>Listado de finalistas y ganador, vote</h3>
    	<p id="voto"></p>
    	<div class="row">
  		<div class="column" style="background-color:#aaa;">
    		<h2>${datosFin1.nomArtistico}</h2>
    		<img src="imagenes/Fin1.jpg" width="120"/>
    		<h3>Votos: ${datosFin1.cantVotos}</h3>
    		<c:if test="${datosFin1.nomArtistico == ganador.nomArtistico}">
    		<h2>Ganador</h2>
    		</c:if>
  		</div>
	  	<div class="column" style="background-color:#bbb;">
	   		<h2>${datosFin2.nomArtistico}</h2>
	   		<img src="imagenes/Fin2.jpg" width="120"/>
	   		<h3>Votos: ${datosFin2.cantVotos}</h3>
	  		<c:if test="${datosFin2.nomArtistico == ganador.nomArtistico}">
    	    <h2>Ganador</h2>
    		</c:if>
	 	</div>
	  <div class="column" style="background-color:#ccc;">
	    <h2>${datosFin3.nomArtistico}</h2>
	    <img src="imagenes/Fin3.jpg" width="120"/>
	    <h3>Votos: ${datosFin3.cantVotos}</h3>
	  	<c:if test="${datosFin3.nomArtistico == ganador.nomArtistico}">
    	<h2>Ganador</h2>
    	</c:if>
	  </div>
	</div>
	<p></p>
	<form action='VotoPublico' method='POST'>
	  <p> Participante: 	<select name='datosVot'>
							<option value='${datosFin1.nomArtistico}'> ${datosFin1.nomArtistico}
							<option value='${datosFin2.nomArtistico}'> ${datosFin2.nomArtistico}
							<option value='${datosFin3.nomArtistico}'> ${datosFin3.nomArtistico}
						   </select> 
       <input type='submit' value='VOTAR'> </p>
    </form>
    </div>
</div>
</body>
</html>
