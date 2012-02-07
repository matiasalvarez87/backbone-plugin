includeTargets << new File("${backbonePluginDir}/scripts/_CompileBackboneTemplates.groovy")

eventCompileStart = {
	compileTemplates()
}