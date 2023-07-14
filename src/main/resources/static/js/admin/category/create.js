/**
 * 
 */
$(function(){
	categoryShow($("#category1"))
	
})
function categoryShow(cate){
	var cVal=$(cate).val()
	csrfSend()
	console.log(cVal)
	$.ajax({
		url:"/admin/category/show",
		type:"post",
		data:{cVal:cVal},
		success:function(result){
			$(cate).append(result)
		}
	})
}
function categoryShow2(cate){
	var cVal=$(cate).val()
	console.log(cVal)
	if(cVal==0||cVal=="")return;
	
	csrfSend()
	$.ajax({
		url:"/admin/category/show",
		type:"post",
		data:{cVal:cVal},
		success:function(result){
			$(cate).siblings("#category2").html(result)
		}
	})
}


function csrfSend(){
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});
}