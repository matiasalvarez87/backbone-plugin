includeTargets << grailsScript("_GrailsInit")
includeTargets << new File("${backbonePluginDir}/scripts/_BackboneConfig.groovy")

target(createDirLayout: "Creates the backbone directory structure.") {

	loadBackboneConfig()
	
	ant.mkdir (dir: config.backbone.basePath)
	ant.mkdir (dir: config.backbone.libs)
	ant.mkdir (dir: config.backbone.collections)
	ant.mkdir (dir: config.backbone.models)
	ant.mkdir (dir: config.backbone.views)
	ant.mkdir (dir: config.backbone.templates)
	
	// Plugin and project paths
	String plgnBasePath = "${backbonePluginDir}/grails-app/templates/backbone-app"
	String basePath = config.backbone.basePath
	
	// Copies the js files
	ant.copy(file: "${plgnBasePath}/main.js", tofile: "${basePath}/main.js")
	ant.copy(file: "${plgnBasePath}/App.js", tofile: "${basePath}/App.js")
	ant.copy(file: "${plgnBasePath}/AppRouter.js", tofile: "${basePath}/AppRouter.js")
	
	println "Backbone Plugin directory structure created."
}
