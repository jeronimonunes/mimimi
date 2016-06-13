function updatePicture(){
	document.getElementById("picture").src += '&a='+new Date().getTime();
}

function updateCover(){
	document.getElementById("cover").src += '&a='+new Date().getTime();
}

$(document).ready(function(){
	$("#picture-upload_input").appendTo($("#picture-upload"));
});

$(document).ready(function(){
	$("#cover-upload_input").appendTo($("#cover-upload"));
});