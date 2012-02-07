package org.grails.plugins.backbone.utils

import java.io.File
import java.util.List

/**
 * Contains useful methods to deal with files.
 * @author matias.alvarez
 */
class FileUtils {

	/**
	 * 
	 * @param relativePath
	 * @param logicalRootPath
	 * @return
	 */
	public static List getFileNamesFromDir(String relativePath, String logicalRootPath) {
		return getPathFilesFromDir(new File(relativePath), logicalRootPath)
	}

	/**
	 * Returns the File collection inside a directory (recursively).
	 * @param dir
	 * @param rootPath
	 * @return
	 */
	public static List getPathFilesFromDir(File dir, String rootPath) {

		List res = []


		dir.listFiles().each { File file ->

			if (file.isDirectory()) {
				res.addAll(getPathFilesFromDir(file, rootPath))
			} else {
				res.add(getBasePath(file.path, rootPath))
			}
		}
		return res
	}

	/**
	 * 
	 * @param fullPath
	 * @param rootPath
	 * @return
	 */
	public static String getBasePath(String fullPath, String rootPath) {

		fullPath = fullPath.replaceAll("\\\\", "/")
		return fullPath.replaceAll(fullPath.substring(0, fullPath.indexOf(rootPath)), "")
	}
}
