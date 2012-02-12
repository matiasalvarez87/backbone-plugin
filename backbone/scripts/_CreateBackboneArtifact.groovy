import org.springframework.core.io.FileSystemResource

includeTargets << grailsScript("_GrailsInit")

/**
 * Process the arguments and create a Map with the artifact name and path.
 * */
Map processParamsToCreateArtifacts(List args) {
	def arguments = [:]
	if (args.size() > 0) {
		arguments.artifactName = args[0]
		arguments.path = (args.size() > 1) ? args[1] : ''
	} else {
		arguments.artifactName = grailsConsole.userInput("Artifact name not specified. Please enter:")
		arguments.path = grailsConsole.userInput("Path not specified, if you want to skip this step press enter. Please enter:")
	}
	return arguments
}

void createArtifact(Map args, String artifactType, String artifactSufix, String artifactPathRoot, String artifactFileSufix, String artifactTemplate) {
		
	if (args.size() < 1) {
		println "ERROR: The name of the ${artifactType} must be specified!"
	} else {
		def artifactName = args.artifactName.toString() + artifactSufix
		def artifactPath = args.path

		String packageName = ''
		if (artifactPath) {
			packageName += artifactPath + '/'
		}
		artifactPath = artifactPathRoot + packageName

		// Generates the necessary directories and files
		ant.mkdir (dir: artifactPath)
		String artifactFile = artifactPath + artifactName + artifactFileSufix
		
		if (!new File(artifactFile).exists()) {
		
			// Loads the template
			def templateFile = new FileSystemResource(artifactTemplate)
			
			if (templateFile.exists()) {
				
				String logicalPath = artifactPath.replaceFirst(config.backbone.root, '')
				
				// Proccess the package name
				packageName = packageName.replaceAll('/', '.')
				if (packageName != '') {
					packageName = packageName.substring (0, packageName.length() - 1)
				}
				packageName = (packageName == '') ? 'classes' : 'classes.' + packageName
				
				copyGrailsResource(artifactFile, templateFile)
				ant.replace(file: artifactFile, token: "@file.path@", value: "${logicalPath}")
				ant.replace(file: artifactFile, token: "@artifact.name@", value: "${artifactName}")
				ant.replace(file: artifactFile, token: "@package@", value: "${packageName}")
				
				println "${artifactType.capitalize()} created in path: " + artifactFile
			
			} else {
				println "ERROR: The backbone ${artifactType} template do not exists!"
			}
		
		} else {
			println "ERROR: The backbone ${artifactType} already exists!"
		}
	}
}

target(createBackboneView: "Creates a backbone view artifact.") {

	String artifactType = 'view'
	String artifactSufix = 'View'
	String artifactPathRoot = config.backbone.views + '/'
	String artifactFileSufix = '.js'
	String artifactTemplate = "${backbonePluginDir}/grails-app/templates/ViewTemplate.js"
	def args = processParamsToCreateArtifacts(argsMap["params"])
	args.artifactName = args.artifactName.capitalize()

	createArtifact(args, artifactType, artifactSufix, artifactPathRoot, artifactFileSufix, artifactTemplate)
}

target(createBackboneModel: "Creates a backbone model artifact.") {

	String artifactType = 'model'
	String artifactSufix = 'Model'
	String artifactPathRoot = config.backbone.models + '/'
	String artifactFileSufix = '.js'
	String artifactTemplate = "${backbonePluginDir}/grails-app/templates/ModelTemplate.js"
	def args = processParamsToCreateArtifacts(argsMap["params"])
	args.artifactName = args.artifactName.capitalize()

	createArtifact(args, artifactType, artifactSufix, artifactPathRoot, artifactFileSufix, artifactTemplate)
}

target(createBackboneCollection: "Creates a backbone collection artifact.") {

	String artifactType = 'collection'
	String artifactSufix = 'Collection'
	String artifactPathRoot = config.backbone.collections + '/'
	String artifactFileSufix = '.js'
	String artifactTemplate = "${backbonePluginDir}/grails-app/templates/CollectionTemplate.js"
	def args = processParamsToCreateArtifacts(argsMap["params"])
	args.artifactName = args.artifactName.capitalize()

	createArtifact(args, artifactType, artifactSufix, artifactPathRoot, artifactFileSufix, artifactTemplate)
}

target(createBackboneTemplate: "Creates a backbone template artifact.") {

	String artifactType = 'template'
	String artifactSufix = ''
	String artifactPathRoot = config.backbone.templates + '/'
	String artifactFileSufix = '.html'
	String artifactTemplate = "${backbonePluginDir}/grails-app/templates/BTemplateTemplate.html"
	def args = processParamsToCreateArtifacts(argsMap["params"])

	createArtifact(args, artifactType, artifactSufix, artifactPathRoot, artifactFileSufix, artifactTemplate)
}