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
	
	println "Backbone Plugin directory structure created."
}
