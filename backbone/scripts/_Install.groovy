//
// This script is executed by Grails after plugin was installed to project.
// This script is a Gant script so you can use all special variables provided
// by Gant (such as 'baseDir' which points on project base dir). You can
// use 'ant' to access a global instance of AntBuilder
//
// For example you can create directory under project tree:
//
//    ant.mkdir(dir:"${basedir}/grails-app/jobs")
//
includeTargets << new File("${backbonePluginDir}/scripts/_CreateBackboneLayout.groovy")

ant.copy(file: "${backbonePluginDir}/grails-app/templates/BackboneConfig_groovy",
	tofile: "${basedir}/grails-app/conf/BackboneConfig.groovy")

createDirLayout()

String plgnBasePath = "${backbonePluginDir}/grails-app/templates/backbone-app"
String basePath = config.backbone.basePath

// Copies the js files
ant.copy(file: "${plgnBasePath}/main.js", tofile: "${basePath}/main.js")
ant.copy(file: "${plgnBasePath}/App.js", tofile: "${basePath}/App.js")
ant.copy(file: "${plgnBasePath}/AppRouter.js", tofile: "${basePath}/AppRouter.js")