class UrlMappings {

	static mappings = {

        "/" {

            controller = "Email"
            action = [GET:"notAllowed", POST:"create", PUT:"notAllowed", DELETE:"notAllowed"]

        }
    }
}
