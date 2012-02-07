includeTargets << grailsScript("_GrailsInit")
includeTargets << new File("${backbonePluginDir}/scripts/_CompileBackboneTemplates.groovy")

target(main: "Run the compile templates task!") {
	
	compileTemplates()
}

setDefaultTarget(main)
