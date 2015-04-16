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

    def grailsApplication = new DefaultGrailsApplication()
    def validAccess = new ValidAccess()
    def emailFrom = grailsApplication.config.emailFrom

    def restService

    def TEMPLATE_MAP = [

            new_user            :"newUser",
            new_band            :"newBand",
            contact             :"contact",
            expiration_service  :"expirationService",
            recovery_password   :"recPass",
            end_services        :"endService"
    ]


    def send(def params,def jsonEmail){

        def jsonResult = [:]

        if (!params.access_token){

            throw new BadRequestException("You must provider access_token")
        }

        def access_token    = validAccess.validAccessToken(params.access_token)
        def userId          = params.access_token.split('_')[2]


        def template    = jsonEmail?.template

        if (!template){
            throw new BadRequestException("You must provider template property")
        }


        def templateProcess = getTemplate(searchTemplate(template), jsonEmail, userId, params.access_token)



        String sendTo       = templateProcess.to
        String sendSubject  = templateProcess.subject
        String sendHtml     = templateProcess.body
        String emailFrom    = templateProcess.email_from


        jsonResult.to       = sendTo
        jsonResult.from     = emailFrom
        jsonResult.subject  = sendSubject
        jsonResult.html     = sendHtml

        jsonResult
    }


    def searchUser (def senderId, def accessToken){

        def params = [
                access_token:accessToken
        ]

        restService.defineServiceType('users')

        def result = restService.getResource("/users/${senderId}", params)

        if (result.status != HttpServletResponse.SC_OK)
        {
            throw new BadRequestException("The sender_id is not valid")
        }

        Map userResult = [:]

        userResult.name_from    = result.data.name
        userResult.email_from   = result.data.email

        userResult
    }

    def searchBand(def bandId){

        restService.defineServiceType('bands')
        def result = restService.getResource("/bands/${bandId}")

        if (result.status != HttpServletResponse.SC_OK)
        {
            throw new BadRequestException("The sender_id is not valid")
        }

        Map bandResult = [:]

        bandResult.user_id  = result.data.user_id
        bandResult.status   = result.data.status
        bandResult.title    = result.data.title

        bandResult
    }

    def searchUserByEmail (def email){

        restService.defineServiceType('users')
        def result = restService.getResource("/users/searchByEmail/${email}")

        if (result.status != HttpServletResponse.SC_OK)
        {
            throw new BadRequestException("The sender_id is not valid")
        }

        Map userResult = [:]

        userResult.user_id      = result.data.user_id
        userResult.status       = result.data.status
        userResult.name_from    = result.data.name

        userResult
    }

    def searchTemplate(def templateId){

        def keyTemplate = TEMPLATE_MAP[templateId]

        if (!keyTemplate){

            throw new NotFoundException("The template not found")
        }

        keyTemplate

    }

    def getTemplate(def keyTemplate,  def jsonEmail, def userId, def accessToken){

        def template = [:]

        def subject
        def body

        def from        = jsonEmail?.from
        def to          = jsonEmail?.to
        def message     = jsonEmail?.message
        def senderId    = jsonEmail?.sender_id
        def bandId      = jsonEmail?.band_id


        switch (keyTemplate) {

            case "newUser":

                if (!senderId){
                    throw new BadRequestException("You must provider the sender_id")
                }

                def user = searchUser(senderId, accessToken)

                subject = grailsApplication.config.newUser.subject
                body    = grailsApplication.config.newUser.body
                body    = body.replace("<<--name-->>", user.name_from)

                template.to         = user.email_from
                template.subject    = subject
                template.body       = body
                template.message    = ""
                template.email_from = emailFrom

                break

            case "newBand":

                if (!bandId) {
                    throw new BadRequestException("You must provider the band_id")
                }


                def band = searchBand(bandId)

                if (band.status != 'active'){
                    throw new ConflictException("The band_id = "+bandId+" not active")
                }
                senderId = band.user_id

                if (!senderId){
                    throw new BadRequestException("You must provider the sender_id")
                }

                def user = searchUser(senderId)

                subject = grailsApplication.config.newBand.subject
                body    = grailsApplication.config.newBand.body
                body    = body.replace("<<--title-->>", band.title)

                template.to         = user.email_from
                template.subject    = subject
                template.body       = body
                template.message    = ""
                template.email_from = emailFrom

                break

            case "contact":

                if (!from){
                    throw new BadRequestException("You must provider the from_email param")
                }

                if (!message){
                    throw new BadRequestException("You must proveder the message param")
                }

                if (!bandId) {
                    throw new BadRequestException("You must provider the band_id")
                }


                def band = searchBand(bandId)

                if (band.status != 'active'){
                    throw new ConflictException("The band_id = "+bandId+" not active")
                }
                senderId = band.user_id


                if (!senderId){
                    throw new BadRequestException("You must provider the sender_id")
                }

                def user = searchUser(senderId)


                subject = grailsApplication.config.contact.subject
                body    = grailsApplication.config.contact.body


                body    = body.replace("<<--name-->>", user.name_from)
                body    = body.replace("<<--email-->>", user.email_from)
                body    = body.replace("<<--title-->>", band.title)
                body    = body.replace("<<--message-->>",message)


                template.to         = user.email_from
                template.subject    = subject
                template.body       = body
                template.message    = ""
                template.email_from = from

                break

            case "expirationService":

                if (!bandId) {
                    throw new BadRequestException("You must provider the band_id")
                }


                def band = searchBand(bandId)

                if (band.status != 'active'){
                    throw new ConflictException("The band_id = "+bandId+" not active")
                }
                senderId = band.user_id

                if (!senderId){
                    throw new BadRequestException("You must provider the sender_id")
                }

                def user = searchUser(senderId)

                subject = grailsApplication.config.expirationService.subject
                body    = grailsApplication.config.expirationService.body

                body    = body.replace("<<--name-->>", user.name_from)
                body    = body.replace("<<--title-->>", band.title)

                template.to         = user.email_from
                template.subject    = subject
                template.body       = body
                template.message    = ""
                template.email_from = emailFrom

                break

            case "recPass":

                if (!from){
                    throw new BadRequestException("You must provider de from (email recovery password)")
                }

                def user = searchUserByEmail(from)

                if (!user.user_id){
                    throw new NotFoundException("The user_id = "+user.user_id+" not found")
                }

                subject = grailsApplication.config.recPass.subject
                body    = grailsApplication.config.recPass.body
                body    = body.replace("<<--name-->>", user.name_from)

                template.to         = from
                template.subject    = subject
                template.body       = body
                template.message    = ""
                template.email_from = emailFrom

                break

            case "endService":

                if (!bandId) {
                    throw new BadRequestException("You must provider the band_id")
                }


                def band = searchBand(bandId)

                if (band.status == 'active'){
                    throw new ConflictException("The band_id = "+bandId+" is active")
                }
                senderId = band.user_id

                if (!senderId){
                    throw new BadRequestException("You must provider the sender_id")
                }

                def user = searchUser(senderId)
                subject = grailsApplication.config.endService.subject
                body    = grailsApplication.config.endService.body

                body    = body.replace("<<--name-->>", user.name_from)
                body    = body.replace("<<--title-->>", band.title)

                template.to         = user.email_from
                template.subject    = subject
                template.body       = body
                template.message    = ""
                template.email_from = emailFrom

                break

            default:
                throw new BadRequestException("The property template is not valid")

        }

        template
    }





}
