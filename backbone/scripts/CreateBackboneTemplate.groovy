import org.springframework.core.io.FileSystemResource

includeTargets << grailsScript("_GrailsInit")
includeTargets << new File("${backbonePluginDir}/scripts/_BackboneConfig.groovy")

target(main: "Creates a Backbone Template based on a template!") {

	loadBackboneConfig()
	
	def args = argsMap["params"]
	
	if (args.size() < 1) {
		println 'ERROR: The name of the template must be specified!'
		
	} else {
		
		// Process the arguments	
		String path = config.backbone.templates + '/'
		String templateName = (args[0]).toString().toLowerCase()
		if (args.size() > 1) {
			path += args[1] + '/'
		}
		
		// Generates the necessary directories and files
		ant.mkdir (dir: path)
		String artifactFile = path + templateName + '.html'
		
		if (!new File(artifactFile).exists()) {
		
			// Loads the template
			def templateFile = new FileSystemResource("${backbonePluginDir}/grails-app/templates/BTemplateTemplate.html")
			
			if (templateFile.exists()) {
			
				String logicalPath = path.replaceFirst(config.backbone.root, '')
				
				copyGrailsResource(artifactFile, templateFile)
				ant.replace(file: artifactFile, token: "@file.path@", value: "${logicalPath}")
				ant.replace(file: artifactFile, token: "@template.name@", value: "${templateName}")
				
				println 'Backbone template created in path: ' + artifactFile
					
			} else {
				println 'ERROR: The template of backbone templates do not exists!'
			}
				
		} else {
			println 'ERROR: The backbone template already exists!'
		}		
	}
}

setDefaultTarget(main)
