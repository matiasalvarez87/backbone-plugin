/**
 * AppRouter.js
 * 
 * Class to define the Router configurations.
 */

var AppRouter = Backbone.Router.extend({

	routes : {
		// Default action
		'*actions' : 'defaultAction'
	},

	/**
	 * The constructor of the Router is used to load the master view.
	 */
	initialize : function() {
		App.namespace('collections');
		App.namespace('views');
	},

	/**
	 * Default handler, executed when there is no matching route.
	 */
	defaultAction : function(actions) {
		console.log('AppRouter.defaultAction()');
	}
});