package emails

import org.codehaus.groovy.grails.commons.DefaultGrailsApplication

import javax.servlet.http.HttpServletResponse
import java.text.MessageFormat
import org.apache.ivy.plugins.conflict.ConflictManager

import org.apache.http.HttpEntity
import org.apache.http.HttpResponse
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpPost
import org.apache.http.impl.client.DefaultHttpClient

import grails.converters.*
import emails.exceptions.NotFoundException
import emails.exceptions.ConflictException
import emails.exceptions.BadRequestException

class EmailService {


    def restService

    def TEMPLATE_MAP = [

            new_user    :"newUser",
            new_band    :"newBand",
            contact     :"contact",
            expiration  :"expiration",
            rec_pass    :"recPass"
    ]

    def send(def jsonEmail){

        def jsonResult = [:]

        def to          = jsonEmail?.to
        def message     = jsonEmail?.message
        def template    = jsonEmail?.template
        def senderId    = jsonEmail?.sender_id

        //TODO como validamos el email de accceso ?


        def result = restService.getResource("/users/${senderId}")

        if (result.status != HttpServletResponse.SC_OK)
        {
            throw new BadRequestException("The sender_id is not valid")
        }

        def emailFrom   = result.data.email
        def nameFrom    = result.data.name

        def templateProcess = searchTemplate(template)

        templateProcess.body = templateProcess.body.replace("<<nombre>>", nameFrom)

        String sendTo       = to
        String sendSubject  = templateProcess.subject
        String sendHtml     = templateProcess.body


        jsonResult.to       = sendTo
        jsonResult.from     = emailFrom
        jsonResult.subject  = sendSubject
        jsonResult.html     = sendHtml

        jsonResult
    }

    def searchTemplate(def templateId){

        def keyTemplate = TEMPLATE_MAP[templateId]

        if (!keyTemplate){

            throw new NotFoundException("The template not found")
        }

        getTemplate(keyTemplate)

    }

    def getTemplate(def keyTemplate){

        def template = [:]

        def subject
        def body

        switch (keyTemplate) {

            case "newUser":
                subject = "Bienvenido a maxibandas"
                body    = "<div>Hola <<nombre>>, Bienvenido a maxibandas completa tu registro...</div>"

                break

            case "newBand":
                subject = "Felicidades tu banda se público"
                body    = "<div>Hola <<nombre>>, Tu banda quedo publicada</div>"

                break

            case "contact":
                subject = "Tu banda fue contactada"
                body    = "<div>Hola <<nombre>>, Tu banda fue contactada</div>"

                break

            case "expiration":
                subject = "Renueva tu anuncio"
                body    = "<div>Hola <<nombre>>, tu anuncio esta a punto de expirar, no dejes que se pierda y renueva</div>"

                break

            case "rec_pass":
                subject = "Recuperacion de password"
                body    = "<div>Hola <<nombre>>, para recuperar tu password haz lo siguiente</div>"

                break

        }

        template.subject    = subject
        template.body       = body
        template.message    = ""

        template
    }
}
