// locations to search for config files that get merged into the main config;
// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [
    all:           '*/*',
    atom:          'application/atom+xml',
    css:           'text/css',
    csv:           'text/csv',
    form:          'application/x-www-form-urlencoded',
    html:          ['text/html','application/xhtml+xml'],
    js:            'text/javascript',
    json:          ['application/json', 'text/json'],
    multipartForm: 'multipart/form-data',
    rss:           'application/rss+xml',
    text:          'text/plain',
    xml:           ['text/xml', 'application/xml']
]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// What URL patterns should be processed by the resources plugin
grails.resources.adhoc.patterns = ['/images/*', '/css/*', '/js/*', '/plugins/*']

// The default codec used to encode data with ${}
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"
// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// configure auto-caching of queries by default (if false you can cache individual queries with 'cache: true')
grails.hibernate.cache.queries = false

grails {
    mail {
        host = "smtp.gmail.com"
        port = 465
        username = "noreplay.maxibandas@gmail.com"
        password = "maxi*123456"
        props = ["mail.smtp.auth":"true",
                 "mail.smtp.socketFactory.port":"465",
                 "mail.smtp.socketFactory.class":"javax.net.ssl.SSLSocketFactory",
                 "mail.smtp.socketFactory.fallback":"false"]
    }
}

environments {
    development {
        grails.logging.jul.usebridge = true
        domainMainUsers = 'http://localhost:8888'
        domainMainBands = 'http://localhost:8888'


    }
    production {
        grails.logging.jul.usebridge = false
        // TODO: grails.serverURL = "http://www.changeme.com"
        //domainMainUsers         = 'http://ec2-54-148-189-131.us-west-2.compute.amazonaws.com'
        //domainMainBands         = 'http://ec2-52-10-76-170.us-west-2.compute.amazonaws.com'
        domainMainUsers         = 'http://api.maxibanda.com.mx'
        domainMainBands         = 'http://api.maxibanda.com.mx'
    }
}

// templates  para loe emails

emailFrom = "noreplay.maxibandas@gmail.com"

newUser.subject = "Maxibandas:Bienvenido"
newUser.body    = "<div style='border:1px solid #e1e1e1;  width: 700px; height:auto;'>\n" +
        "\t\t<div style='height: 40px; background-color:#322117;background-image:-webkit-linear-gradient(top, #322117, #322117); \n" +
        "\t\tfont-family: Tahoma, Arial; color:#FFF; font-size: 30px; font-weight: bold;\n" +
        "\t\tpadding-left: 10px; padding-top: 5px;' >\n" +
        "\t\t\tmaxibandas\n" +
        "\t\t</div>\n" +
        "\t\t<div style=' font-family: Arial; font-size: 12px; padding: 10px; '>\n" +
        "\t\t\t<p style='font-family: Arial;font-size: 20px;font-weight: bold;width: auto;color: #DF6810;\n" +
        "\t\t\tmargin-left: 15px;'>\n" +
        "\t\t\t\tBienvenido <<--name-->> </p>\n" +
        "\t\t\t<p>\n" +
        "\t\t\t\tUna vez activada tu cuenta estara todo listo para que puedas comenzar a difrutar de los beneficios \n" +
        "        \t\t\t\tque <a   style='color:#2a2a2a;'href='http://www.maxibandas.com.mx'>maxibandas.com.mx</a> tiene para ti.<br><br><br>\n" +
        "        \t\tPara activar tu cuenta da <a  style='color:#DF6810; font-size: 13px;' href=''>clik aqui [ACTIVAR CUENTA]</a>  \n" +
        "\t\t\t</p>\n" +
        "\t\t </div>\n" +
        "\t\t<div style='height:30px; border-top:1px solid #E4E4E4; background: #E4E4E4; text-align: center;\n" +
        "\t\tfont-family: Arial; font-size: 10px; color:#555; '>\n" +
        "\t\t\t<a style='color:#DF6810;' href=''>www.maxibandas.com.mx</a> <br> Todos los derechos reservados\n" +
        "\t\t</div>\t\t\n" +
        "\t</div>"

newBand.subject = "Maxibandas: Felicidades tu banda se público"
newBand.body    = "<div style=\"border:1px solid #e1e1e1;  width: 700px; height:auto;\">\n" +
        "\t\t<div style=\"height: 40px; background-color:#322117;background-image:-webkit-linear-gradient(top, #322117, #322117); \n" +
        "\t\tfont-family: Tahoma, Arial; color:#FFF; font-size: 30px; font-weight: bold;\n" +
        "\t\tpadding-left: 10px; padding-top: 5px;\" >\n" +
        "\t\t\tmaxibandas\n" +
        "\t\t</div>\n" +
        "\t\t<div style=\" font-family: Arial; font-size: 12px; padding: 10px; \">\n" +
        "\t\t\t<p style=\"font-family: Arial;font-size: 20px;font-weight: bold;width: auto;color: #DF6810;\n" +
        "\t\t\tmargin-left: 15px;\">\n" +
        "\t\t\t\tHola, <<--title-->> </p>\n" +
        "\t\t\t<p>\n" +
        "\t\t\t\tTu banda quedo registrada y publicada en \n" +
        "        \t\t\t\t <a   style=\"color:#2a2a2a;\"href=\"http://www.maxibandas.com.mx\">maxibandas.com.mx</a>  <br><br><br>\n" +
        "        \t\tPara ver tu publicación <a  style=\"color:#DF6810; font-size: 13px;\" href=\"\">clik aqui <<--title-->></a>  \n" +
        "\t\t\t</p>\n" +
        "\t\t </div>\n" +
        "\t\t<div style=\"height:30px; border-top:1px solid #E4E4E4; background: #E4E4E4; text-align: center;\n" +
        "\t\tfont-family: Arial; font-size: 10px; color:#555; \">\n" +
        "\t\t\t<a style=\"color:#DF6810;\" href=\"\">www.maxibandas.com.mx</a> <br> Todos los derechos reservados\n" +
        "\t\t</div>\t\t\n" +
        "\t</div>"

contact.subject = "Maxibandas: Preguntaron por tu banda"
contact.body    = "<div style=\"border:1px solid #e1e1e1;  width: 700px; height:auto;\">\n" +
        "\t\t<div style=\"height: 40px; background-color:#322117;background-image:-webkit-linear-gradient(top, #322117, #322117); \n" +
        "\t\tfont-family: Tahoma, Arial; color:#FFF; font-size: 30px; font-weight: bold;\n" +
        "\t\tpadding-left: 10px; padding-top: 5px;\" >\n" +
        "\t\t\tmaxibandas\n" +
        "\t\t</div>\n" +
        "\t\t<div style=\" font-family: Arial; font-size: 12px; padding: 10px; \">\n" +
        "\t\t\t<p style=\"font-family: Arial;font-size: 20px;font-weight: bold;width: auto;color: #DF6810;\n" +
        "\t\t\tmargin-left: 15px;\">\n" +
        "\t\t\t\tHola, <<--name-->> </p>\n" +
        "\t\t\t<p>\n" +
        "\t\t\t\tContacataron a tu banda <a   style=\"color:#2a2a2a;\"href=\"http://www.maxibandas.com.mx\"><<--title-->></a> con los siguientes datos: <br>\n" +
        "\n" +
        "\t\t\t\t<ul>\n" +
        "\t\t\t\t\t<li>Email:<<--email-->></li>\n" +
        "\t\t\t\t\t<li>Telefono:<<--telefono-->></li>\n" +
        "\t\t\t\t\t<li>Message:<<--message-->></li>\n" +
        "\t\t\t\t</ul>\n" +
        " \n" +
        "\t\t\t</p>\n" +
        "\t\t </div>\n" +
        "\t\t<div style=\"height:30px; border-top:1px solid #E4E4E4; background: #E4E4E4; text-align: center;\n" +
        "\t\tfont-family: Arial; font-size: 10px; color:#555; \">\n" +
        "\t\t\t<a style=\"color:#DF6810;\" href=\"\">www.maxibandas.com.mx</a> <br> Todos los derechos reservados\n" +
        "\t\t</div>\t\t\n" +
        "\t</div>"

expirationService.subject   = "Maxibandas: Renueva tu anuncio"
expirationService.body      = "<div style=\"border:1px solid #e1e1e1;  width: 700px; height:auto;\">\n" +
        "\t\t<div style=\"height: 40px; background-color:#322117;background-image:-webkit-linear-gradient(top, #322117, #322117); \n" +
        "\t\tfont-family: Tahoma, Arial; color:#FFF; font-size: 30px; font-weight: bold;\n" +
        "\t\tpadding-left: 10px; padding-top: 5px;\" >\n" +
        "\t\t\tmaxibandas\n" +
        "\t\t</div>\n" +
        "\t\t<div style=\" font-family: Arial; font-size: 12px; padding: 10px; \">\n" +
        "\t\t\t<p style=\"font-family: Arial;font-size: 20px;font-weight: bold;width: auto;color: #DF6810;\n" +
        "\t\t\tmargin-left: 15px;\">\n" +
        "\t\t\t\tHola, <<--name-->> </p>\n" +
        "\t\t\t<p>\n" +
        "\t\t\t\tTe avisamos que tu anuncio  <a   style=\"color:#2a2a2a;\"href=\"http://www.maxibandas.com.mx\"><<--title-->></a> esta proximo a expirar. <br>\n" +
        "\n" +
        "\t\t\t\tPara renovar <a   style=\"color:#2a2a2a;\"href=\"http://www.maxibandas.com.mx\">da click aqui</a> \n" +
        " \n" +
        "\t\t\t</p>\n" +
        "\t\t </div>\n" +
        "\t\t<div style=\"height:30px; border-top:1px solid #E4E4E4; background: #E4E4E4; text-align: center;\n" +
        "\t\tfont-family: Arial; font-size: 10px; color:#555; \">\n" +
        "\t\t\t<a style=\"color:#DF6810;\" href=\"\">www.maxibandas.com.mx</a> <br> Todos los derechos reservados\n" +
        "\t\t</div>\t\t\n" +
        "\t</div>"

recPass.subject = "Maxibandas : Recuperación de password"
recPass.body    = "<div style=\"border:1px solid #e1e1e1;  width: 700px; height:auto;\">\n" +
        "\t\t<div style=\"height: 40px; background-color:#322117;background-image:-webkit-linear-gradient(top, #322117, #322117); \n" +
        "\t\tfont-family: Tahoma, Arial; color:#FFF; font-size: 30px; font-weight: bold;\n" +
        "\t\tpadding-left: 10px; padding-top: 5px;\" >\n" +
        "\t\t\tmaxibandas\n" +
        "\t\t</div>\n" +
        "\t\t<div style=\" font-family: Arial; font-size: 12px; padding: 10px; \">\n" +
        "\t\t\t<p style=\"font-family: Arial;font-size: 20px;font-weight: bold;width: auto;color: #DF6810;\n" +
        "\t\t\tmargin-left: 15px;\">\n" +
        "\t\t\t\tHola, <<--name-->> </p>\n" +
        "\t\t\t<p>\n" +
        "\t\t\t\tHaz elegido recuperar tu password. <br>\n" +
        "\n" +
        "\t\t\t\tPara recuperar tu password  <a   style=\"color:#2a2a2a;\"href=\"http://www.maxibandas.com.mx\">sigue aqui las instrucciones</a> \n" +
        " \n" +
        "\t\t\t</p>\n" +
        "\t\t </div>\n" +
        "\t\t<div style=\"height:30px; border-top:1px solid #E4E4E4; background: #E4E4E4; text-align: center;\n" +
        "\t\tfont-family: Arial; font-size: 10px; color:#555; \">\n" +
        "\t\t\t<a style=\"color:#DF6810;\" href=\"\">www.maxibandas.com.mx</a> <br> Todos los derechos reservados\n" +
        "\t\t</div>\t\t\n" +
        "\t</div>"

endService.subject  = "Maxibandas: Tu banda se dio de baja"
endService.body     = "<div style=\"border:1px solid #e1e1e1;  width: 700px; height:auto;\">\n" +
        "\t\t<div style=\"height: 40px; background-color:#322117;background-image:-webkit-linear-gradient(top, #322117, #322117); \n" +
        "\t\tfont-family: Tahoma, Arial; color:#FFF; font-size: 30px; font-weight: bold;\n" +
        "\t\tpadding-left: 10px; padding-top: 5px;\" >\n" +
        "\t\t\tmaxibandas\n" +
        "\t\t</div>\n" +
        "\t\t<div style=\" font-family: Arial; font-size: 12px; padding: 10px; \">\n" +
        "\t\t\t<p style=\"font-family: Arial;font-size: 20px;font-weight: bold;width: auto;color: #DF6810;\n" +
        "\t\t\tmargin-left: 15px;\">\n" +
        "\t\t\t\tHola, <<--name-->> </p>\n" +
        "\t\t\t<p>\n" +
        "\t\t\t\tTe avisamos que tu anuncio  <a   style=\"color:#2a2a2a;\"href=\"http://www.maxibandas.com.mx\"><<--title-->></a> fue dado de baja. <br>\n" +
        "\n" +
        "\t\t\t\tPara renovar <a   style=\"color:#2a2a2a;\"href=\"http://www.maxibandas.com.mx\"><<--title-->></a> \n" +
        " \n" +
        "\t\t\t</p>\n" +
        "\t\t </div>\n" +
        "\t\t<div style=\"height:30px; border-top:1px solid #E4E4E4; background: #E4E4E4; text-align: center;\n" +
        "\t\tfont-family: Arial; font-size: 10px; color:#555; \">\n" +
        "\t\t\t<a style=\"color:#DF6810;\" href=\"\">www.maxibandas.com.mx</a> <br> Todos los derechos reservados\n" +
        "\t\t</div>\t\t\n" +
        "\t</div>"




// log4j configuration
log4j = {
    // Example of changing the log pattern for the default console appender:
    //
    //appenders {
    //    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    //}

    error  'org.codehaus.groovy.grails.web.servlet',        // controllers
           'org.codehaus.groovy.grails.web.pages',          // GSP
           'org.codehaus.groovy.grails.web.sitemesh',       // layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping',        // URL mapping
           'org.codehaus.groovy.grails.commons',            // core / classloading
           'org.codehaus.groovy.grails.plugins',            // plugins
           'org.codehaus.groovy.grails.orm.hibernate',      // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate'
}
