$(document).ready(function() {
	
	$('#formNoteFolder').keydown(function(e) {
		var name = $("#noteFolder").val();	
        var keycode = (e.keyCode ? e.keyCode : e.which);
        
        if(keycode == '13') {
        	if (name == "") {
        		alert("Input note is missing, please input again!");
        	} else {
        		$('#formNoteFolder').submit();
        	} 
        	return false;
        }     
    });
	
	$('#formNoteContent').keydown(function(e) {
		var name = $("#noteContent").val();	
        var keycode = (e.keyCode ? e.keyCode : e.which);
        
        if(keycode == '13') {
        	if (name == "") {
        		alert("Input note is missing, please input again!");
        	} else {
        		$('#formNoteContent').submit();
        	} 
        	return false;
        }     
    });
	
	// Delete folder sharing
	$('.sharingFolderBtn').click(function(e) {
		e.preventDefault();
		var href = $(this).attr('href');
		$('#delFolderRef').attr('href', href);
		$('#delFolderSharing').modal();
	});
	
	// Delete content sharing
	$('.sharingContentBtn').click(function(e) {
		e.preventDefault();
		var href = $(this).attr('href');
		$('#delContentRef').attr('href', href);
		$('#delContentSharing').modal();
	});
	
});