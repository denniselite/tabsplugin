//when dom document will ready...
AJS.$(document).ready(function(){

	//init restfull-table
	new AJS.RestfulTable({
		autoFocus: true,
		//id for table element
		el: jQuery("#restfull-table"),
		allowReorder: true,
		resources: {

			//for all rows
			all: "/rest/tabsrestresource/1.0/row/all",

			//CRUD methods for each item
			self: "/rest/tabsrestresource/1.0/row"
		},

		//simple columns description
		columns: [
		          {
		        	  id: "id",
		        	  header: "id",
		        	  allowEdit:false
		          },
		          {
		        	  id: "string",
		        	  header: "string"
		          },
		          {
		        	  id: "date",
		        	  header: "date"
		          }
		          ]
	});
});