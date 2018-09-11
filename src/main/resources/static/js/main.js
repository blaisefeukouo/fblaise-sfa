$(document).ready(function() {
    $('.js-example-basic-single').select2();
});

$(function() {
	$(".open-DeleteItemDialog").click(function() {
		$('#elementId').val($(this).data('id'));
		$("#deleteItemDialog").modal("show");
	});
});
