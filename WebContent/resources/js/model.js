function updatePicture(){
	var pics = $(".profile-picture");
	for(var i = 0; i<pics.length;i++){
		var p = pics[i];
		if(p.src.indexOf("default.png")==-1){
			p.src += '&a='+new Date().getTime();
		} else {
			p.src = p.src.replace("default.png",$("#username").text()+".jpg");
		}
	}
}

function updateCover(){
	var p = document.getElementById("cover");
	if(p.src.indexOf("default.png")==-1){
		p.src += '&a='+new Date().getTime();
	} else {
		p.src = p.src.replace("default.png",$("#username").text()+".jpg");
	}
}

$(document).ready(function(){
	$("#picture-upload_input").appendTo($("#picture-upload"))
	.mouseenter(function(){
		$("#picture-upload-signal").css("opacity",0.5);
	}).mouseleave(function(){
		$("#picture-upload-signal").css("opacity",0)
	});
});

$(document).ready(function(){
	$("#cover-upload_input").appendTo($("#cover-upload"))
	.mouseenter(function(){
		$("#cover-upload-signal").css("opacity",0.5);
	}).mouseleave(function(){
		$("#cover-upload-signal").css("opacity",0)
	});
});