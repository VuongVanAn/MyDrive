$(document).ready(function() {
	$('.btnEdit').click(function(e) {
		e.preventDefault();
		var href = $(this).attr('href');
		
		$.get(href, function(user, status) {
			$('#id').val(user.id);
			$('#userName').val(user.userName);
			$('#fullName').val(user.fullName);
			$('#password').val(user.password);
			$('#permission').val(user.permission);
		});	
		$('#modalUserForm').modal();
	});
	
	$('#btnSubmit').click(function(e) {
		e.preventDefault();
		var data = {};
		var formData = $('#formSubmit').serializeArray();
		$.each(formData, function(i, v) {
			data[""+v.name+""] = v.value;
		});		
		saveUser(data);
	});
	
	function saveUser(data) {
		$.ajax({
			url: '/users/save',
			type: 'POST',
			contentType: 'application/json; charset=utf-8',
			data: JSON.stringify(data),
			dataType: 'json',
			success: function (result) {			
				window.location.href = '/users';
				console.log(result);
			},
			error: function (error) {			
				window.location.href = '/users';
				console.log(error);
			}
		});
	}
	
	$('.delBtn').click(function(e) {
		e.preventDefault();
		var href = $(this).attr('href');
		$('.myModal #delRef').attr('href', href);
		$('.myModal #exampleModalLong').modal();
	});
});
