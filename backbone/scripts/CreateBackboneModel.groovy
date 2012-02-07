import org.springframework.core.io.FileSystemResource

includeTargets << grailsScript("_GrailsInit")
includeTargets << new File("${backbonePluginDir}/scripts/_BackboneConfig.groovy")

target(main: "Creates a Backbone Model based on a template!") {

	loadBackboneConfig()
	
	def args = argsMap["params"]
	
	if (args.size() < 1) {
		println 'ERROR: The name of the model must be specified!'
		
	} else {
		
		// Process the arguments	
		String path = config.backbone.models + '/'
		String modelName = (args[0]).toString().capitalize() + 'Model'
		if (args.size() > 1) {
			path += args[1] + '/'
		}
		
		// Generates the necessary directories and files
		ant.mkdir (dir: path)
		String artifactFile = path + modelName + '.js'
		
		if (!new File(artifactFile).exists()) {
		
			// Loads the template
			def templateFile = new FileSystemResource("${backbonePluginDir}/grails-app/templates/ModelTemplate.js")
			
			if (templateFile.exists()) {
			
				String logicalPath = path.replaceFirst(config.backbone.root, '')
				
				copyGrailsResource(artifactFile, templateFile)
				ant.replace(file: artifactFile, token: "@file.path@", value: "${logicalPath}")
				ant.replace(file: artifactFile, token: "@model.name@", value: "${modelName}")
				
				println 'Model created in path: ' + artifactFile
					
			} else {
				println 'ERROR: The backbone model template do not exists!'
			}
				
		} else {
			println 'ERROR: The backbone model already exists!'
		}		
	}
}

setDefaultTarget(main)
