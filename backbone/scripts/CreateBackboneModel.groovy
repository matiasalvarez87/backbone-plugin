includeTargets << grailsScript("_GrailsInit")
includeTargets << new File("${backbonePluginDir}/scripts/_BackboneConfig.groovy")
includeTargets << new File("${backbonePluginDir}/scripts/_CreateBackboneArtifact.groovy")

target(main: "Creates a Backbone Model based on a template!") {

	loadBackboneConfig()
	createBackboneModel()
}

setDefaultTarget(main)
