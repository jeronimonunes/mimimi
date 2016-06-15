function unfollow(id){
	$("div[user-id='"+id+"']").remove();
	toggleFollowS([{name:"user",value:id}]);
}

function toggleFollow(id){
	var b = $("div[user-id='"+id+"']").find(".follow-button");
	b.toggleClass("pressed");
	b.toggleClass("unpressed");
	toggleFollowS([{name:"user",value:id}]);
}

function likeMimimi(id){
	$("div[mimimi-id="+id+"]").find(".like-link").toggleClass("liked");
	likeMimimiS([{name:"mimimi",value:id}]);
}

function deleteMimimi(id){
	$("div[mimimi-id="+id+"]").remove();
	deleteMimimiS([{name:"mimimi",value:id}]);
}

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