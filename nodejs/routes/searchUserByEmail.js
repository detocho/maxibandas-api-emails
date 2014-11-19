exports.get = function (request, response){
	
	var email = request.params.email

	var json

    if (email){

        json = {
           "email":email, 
           "user_id": 5,
           "status":"active",
           "name":"david mock"
        }
        response.json(200,json)


    }else{
	
        json ={
            "message":"not_found",
            "status":"400",
            "error":"invalid admin"
        }

        response.json(404, json)
       
    }
	
	
}