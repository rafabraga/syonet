app.service("visitaService", function($http, Visita) {

	this.listarSemana = function() {
		return $http.get(WS_URL + "visita/semana/");
	}

	this.salvar = function(visita) {
		return $http.put(WS_URL + "visita/", visita);
	}

});