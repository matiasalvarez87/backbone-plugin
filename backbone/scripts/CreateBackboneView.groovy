import org.springframework.core.io.FileSystemResource

includeTargets << grailsScript("_GrailsInit")
includeTargets << new File("${backbonePluginDir}/scripts/_BackboneConfig.groovy")

target(main: "Creates a Backbone View based on a template!") {

	loadBackboneConfig()
	
	def args = argsMap["params"]
	
	if (args.size() < 1) {
		println 'ERROR: The name of the view must be specified!'
		
	} else {
		
		// Process the arguments	
		String path = config.backbone.views + '/'
		String viewName = (args[0]).toString().toLowerCase().capitalize() + 'View'
		if (args.size() > 1) {
			path += args[1] + '/'
		}
		
		// Generates the necessary directories and files
		ant.mkdir (dir: path)
		String artifactFile = path + viewName + '.js'
		
		if (!new File(artifactFile).exists()) {
		
			// Loads the template
			def templateFile = new FileSystemResource("${backbonePluginDir}/grails-app/templates/ViewTemplate.js")
			
			if (templateFile.exists()) {
			
				String logicalPath = path.replaceFirst(config.backbone.root, '')
				
				copyGrailsResource(artifactFile, templateFile)
				ant.replace(file: artifactFile, token: "@file.path@", value: "${logicalPath}")
				ant.replace(file: artifactFile, token: "@view.name@", value: "${viewName}")
				
				println 'View created in path: ' + artifactFile
					
			} else {
				println 'ERROR: The backbone view template do not exists!'
			}
				
		} else {
			println 'ERROR: The backbone view already exists!'
		}		
	}
}

setDefaultTarget(main)
