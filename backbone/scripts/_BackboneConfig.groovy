includeTargets << grailsScript("_GrailsInit")

target(loadBackboneConfig: "Loads the backbone plugin configuration.") {

//	if (!basedir) { // Useful to test the scripts running it from the plugin
//		basedir = backbonePluginDir
//	}
	
	String configPath = "./grails-app/conf/BackboneConfig.groovy".replaceAll("\\\\", "/")
	config = new ConfigSlurper().parse(new File(configPath).toURL())
	
	println "Backbone Plugin configuration loaded from path: ${configPath}"
}
