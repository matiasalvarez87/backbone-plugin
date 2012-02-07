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
		String viewName = (args[0]).toString().capitalize() + 'View'
		String packageName = ''
		if (args.size() > 1) {
			packageName += args[1] + '/'
		}
		path += packageName
		
		// Generates the necessary directories and files
		ant.mkdir (dir: path)
		String artifactFile = path + viewName + '.js'
		
		if (!new File(artifactFile).exists()) {
		
			// Loads the template
			def templateFile = new FileSystemResource("${backbonePluginDir}/grails-app/templates/ViewTemplate.js")
			
			if (templateFile.exists()) {
			
				String logicalPath = path.replaceFirst(config.backbone.root, '')
				
				// Proccess the package name
				packageName = packageName.replaceAll('/', '.')
				if (packageName != '') {
					packageName = packageName.substring (0, packageName.length() - 1)
				}
				packageName = (packageName == '') ? 'classes' : 'classes.' + packageName
				
				copyGrailsResource(artifactFile, templateFile)
				ant.replace(file: artifactFile, token: "@file.path@", value: "${logicalPath}")
				ant.replace(file: artifactFile, token: "@view.name@", value: "${viewName}")
				ant.replace(file: artifactFile, token: "@package@", value: "${packageName}")
				
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
