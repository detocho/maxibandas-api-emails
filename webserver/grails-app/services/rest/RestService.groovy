package rest

import org.codehaus.groovy.grails.commons.DefaultGrailsApplication
import grails.converters.JSON
import javassist.NotFoundException
import net.sf.json.JSONNull
import org.codehaus.groovy.grails.web.json.JSONObject
import emails.exceptions.NotFoundException
import emails.exceptions.ConflictException
import emails.exceptions.BadRequestException
import groovyx.net.http.RESTClient

import javax.servlet.http.HttpServletResponse

class RestService {


    static transactional = false

    //def urlBase = "http://localhost:8888/" //"https://api.mercadolibre.com/"
    def grailsApplication = new DefaultGrailsApplication()
    def urlBase = grailsApplication.config.domainMainUsers


    def restClient  = new RESTClient(urlBase)

    def getResource(def resource){

        Map result = [:]

        def resp = restClient.get(path:resource)

        if(resp.status != HttpServletResponse.SC_OK){

            if (resp.status ==  HttpServletResponse.SC_NOT_FOUND){

                throw  new NotFoundException('Rest Service whit url ='+urlBase+resource+'not found')

            }
            if (resp.status ==  HttpServletResponse.SC_BAD_REQUEST){

                throw  new BadRequestException('Rest Service with url='+urlBase+resource+' bad request')
            }

        }

        result.status   = resp.status
        result.data     = resp.data

        result

    }


    def postResource(def resource, def body){

        Map result = [:]

        def resp = restClient.post(
                path : resource,
                body : body,
                requestContentType : 'application/json' )

        if(resp.status != HttpServletResponse.SC_CREATED){

            if (resp.status ==  HttpServletResponse.SC_NOT_FOUND){

                throw  new NotFoundException('Rest Service whit url ='+urlBase+resource+'not found')

            }
            if (resp.status ==  HttpServletResponse.SC_BAD_REQUEST){

                throw  new BadRequestException('Rest Service with url='+urlBase+resource+' bad request')
            }

            if (resp.status == HttpServletResponse.SC_CONFLICT){
                throw  new ConflictException ('Rest Service with url='+urlBase+resource+' conflict exception')
            }

        }

        result.status   = resp.status
        result.data     = resp.data

        result
    }

    def putResource(def resource, def body){
        Map result = [:]

        def resp = restClient.put(
                path : resource,
                body : body,
                requestContentType : 'application/json' )

        if(resp.status != HttpServletResponse.SC_OK){

            if (resp.status ==  HttpServletResponse.SC_NOT_FOUND){

                throw  new NotFoundException('Rest Service whit url ='+urlBase+resource+'not found')

            }
            if (resp.status ==  HttpServletResponse.SC_BAD_REQUEST){

                throw  new BadRequestException('Rest Service with url='+urlBase+resource+' bad request')
            }

            if (resp.status == HttpServletResponse.SC_CONFLICT){
                throw  new ConflictException ('Rest Service with url='+urlBase+resource+' conflict exception')
            }

        }

        result.status   = resp.status
        result.data     = resp.data

        result
    }


}
