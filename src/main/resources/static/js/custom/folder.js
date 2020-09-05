$(document).ready(function() {
	
	$('#folderForm').submit(function(e) {
		e.preventDefault();
		var data = {};
		var formData = $(this).serializeArray();
		$.each(formData, function(i, v) {
			data[""+v.name+""] = v.value;
		});	
		
		$.ajax({
			url: '/user/save/folder',
			type: 'POST',
			contentType: 'application/json',
			data: JSON.stringify(data),
			dataType: 'json',
			success: function (result) {			
				window.location.href = '/user/my-drive';
				console.log(result);
			},
			error: function (error) {			
				window.location.href = '/user/my-drive';
				console.log(error);
			}
		});
	});
	
	// Delete folder
	$('.delFolderBtn').click(function(e) {
		e.preventDefault();
		var href = $(this).attr('href');
		$('#delFolderRef').attr('href', href);
		$('#deleteFolderModal').modal();
	});
	
	// Delete content
	$('.delContentBtn').click(function(e) {
		e.preventDefault();
		var href = $(this).attr('href');
		$('#delContentRef').attr('href', href);
		$('#deleteContentModal').modal();
	});
	
});
