exports.get = function (request, response){
	
	var admin = request.params.admin

	var json

    if (admin == 'MB-ADMIN_123456KKAADPZ'){

        json = {
           "user_id": 5,
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