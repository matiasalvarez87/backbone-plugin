/**
 * App.js
 * 
 * Object that contains the application resources.
 */

var App = {

	/**
	 * Initializes the application and all of its resources.
	 */
	initialize : function() {
		this.router = new AppRouter();
		Backbone.history.start();
	},

	/**
	 * Redirects the application to some url.
	 * */
	redirect : function(url) {
		$(location).attr('href', url);
	},
	
	/**
	 * Allows to go back in the browser history.
	 * */
	goBack: function() {
		window.history.back();
	},
	
	/**
	 * Normalizes the element id selector to be used in jQuery.
	 * */
	normalizeElementSelector: function(elementId) {
		return (elementId.substr(0, 1) != '#') ? '#' + elementId : elementId;
	},


	/**
	 * Defines a package (or namespace) to hold the application objects.
	 */
	namespace : function(pckgStr) {
		if (pckgStr != '') {
			var pckgs = pckgStr.split('.');
			var pckg = '';
			$.each(pckgs, function(idx, value) {
				pckg += (pckg == '') ? value : '.' + value;
				if (eval('typeof App.' + pckg) == 'undefined') {
					eval('App.' + pckg + ' = {};');
				}
			});
		}
	}
};