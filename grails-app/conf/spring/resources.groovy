// Place your Spring DSL code here
beans = {
	messageSource(org.springframework.context.support.ReloadableResourceBundleMessageSource) {
		basenames = ["classpath:grails-app/i18n/myApp", "file:grails-app/i18n/messages", "WEB-INF/grails-app/i18n/messages"]
	}
}
