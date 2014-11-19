package emails

import javax.servlet.http.HttpServletResponse
import grails.converters.*
import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*
import grails.plugin.gson.converters.GSON
import emails.exceptions.NotFoundException
import emails.exceptions.ConflictException
import emails.exceptions.BadRequestException

class EmailController {

    def emailService



    def create(){

        def result

        setHeaders()

        try{

            result = emailService.send(params, request.JSON)

            sendMail {
                to result.to
                from result.from
                subject result.subject
                html result.html
            }
            response.setStatus(HttpServletResponse.SC_CREATED)

            def answer = [:]
            answer.message = 'The email send'

            render answer as GSON

        }catch(BadRequestException e){
            renderException(e)
        }catch(ConflictException e){
            renderException(e)
        }catch(Exception e){
            renderException(e)
        }

    }


    def notAllowed(){
        def method = request.method

        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED)
        setHeaders()

        def mapResult = [
                message: "Method "+method+" not allowed",
                status: HttpServletResponse.SC_METHOD_NOT_ALLOWED,
                error:"not_allowed"
        ]
        render mapResult as GSON
    }


    def setHeaders(){

        response.setContentType "application/json; charset=utf-8"
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE");
        response.setHeader("Access-Control-Max-Age", "86400");
        response.setHeader("Access-Control-Allow-Headers", "application/json;charset=UTF-8");
    }

    def renderException(def e){

        def statusCode
        def error

        try{
            statusCode  = e.status
            error       = e.error

        }catch(Exception ex){

            statusCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR
            error = "internal_server_error"
        }

        response.setStatus(statusCode)

        def mapExcepction = [
                message: e.getMessage(),
                status: statusCode,
                error: error
        ]

        render mapExcepction as GSON

    }


}
