includeTargets << grailsScript("_GrailsInit")
includeTargets << new File("${backbonePluginDir}/scripts/_CreateBackboneLayout.groovy")

target(main: "Creates the backbone directory structure.") {

	createDirLayout()
}

setDefaultTarget(main)