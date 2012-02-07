package org.grails.plugins.backbone.utils

/**
 * Allows to load configuration files.
 * @author matias.alvarez
 */
class PluginConfiguration {

	/**
	 * Loads a configuration file.
	 * @param path
	 * @return The Map instance with the configurations.
	 */
	public static Map loadPluginConfig(String path) {

		println "Loading configuration file: ${path}"
		return new ConfigSlurper().parse(new File(path).toURL())
	}	
}
