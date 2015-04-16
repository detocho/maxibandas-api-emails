exports.get = function (request, response){
	
	var userId = request.params.userId
	var json
	
    json = {
    	"id": userId,
    	"name": "Test de modificion",
    	"email": "davidpazgtz764@gmail.com",
    	"password": "dtm123456",
    	"phone": "Casa (55) 55-2345-2534",
    	"location_id": "15",
    	"date_of_birth": null,
    	"registration_date": "Oct 26, 2014 12:35:31 AM",
    	"date_last_update": "Oct 26, 2014 12:37:36 AM",
    	"origin": "mobile",
    	"status": "active",
    	"user_type": "normal"
	}
	
	response.json(200,json)
}