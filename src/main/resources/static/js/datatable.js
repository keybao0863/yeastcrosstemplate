$(document).ready( function () {
	 var table = $('#yeastTable').DataTable({
			"sAjaxSource": "/yeasts",
			"sAjaxDataProp": "",
			"order": [[ 0, "asc" ]],
			"aoColumns": [
			    { "mData": "id"},
		      {"mData": "name"},
				  { "mData": "mat" },
				  { "mData": "leu1" },
				  { "mData": "his2" },
				  { "mData": "ura4" },
				  { "mData": "ade6" },
				  { "mData": "additionalGenotype" }
			]
	 })
});