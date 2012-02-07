import org.springframework.core.io.FileSystemResource

includeTargets << grailsScript("_GrailsInit")
includeTargets << new File("${backbonePluginDir}/scripts/_BackboneConfig.groovy")

target(main: "Creates a Backbone Collection based on a template!") {

	loadBackboneConfig()
	
	def args = argsMap["params"]
	
	if (args.size() < 1) {
		println 'ERROR: The name of the collection must be specified!'
		
	} else {
		
		// Process the arguments	
		String path = config.backbone.collections + '/'
		String collectionName = (args[0]).toString().capitalize() + 'Collection'
		if (args.size() > 1) {
			path += args[1] + '/'
		}
		
		// Generates the necessary directories and files
		ant.mkdir (dir: path)
		String artifactFile = path + collectionName + '.js'
		
		if (!new File(artifactFile).exists()) {
		
			// Loads the template
			def templateFile = new FileSystemResource("${backbonePluginDir}/grails-app/templates/CollectionTemplate.js")
			
			if (templateFile.exists()) {
			
				String logicalPath = path.replaceFirst(config.backbone.root, '')
				
				copyGrailsResource(artifactFile, templateFile)
				ant.replace(file: artifactFile, token: "@file.path@", value: "${logicalPath}")
				ant.replace(file: artifactFile, token: "@collection.name@", value: "${collectionName}")
				
				println 'Collection created in path: ' + artifactFile
					
			} else {
				println 'ERROR: The backbone collection template do not exists!'
			}
				
		} else {
			println 'ERROR: The backbone collection already exists!'
		}		
	}
}

setDefaultTarget(main)
