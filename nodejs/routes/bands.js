exports.get = function (request, response){
	
	var bandId = request.params.bandId
	var json
	
    json = {
    	"band_id": bandId,
        "user_id":5,
        "title":"Banda los primos en México",
    	"status": "active"
    	
	}
	
	response.json(200,json)
}