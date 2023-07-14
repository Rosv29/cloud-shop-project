/**
 * 
 */
$(function(){
	listCategory()
})

function change(changeBtn){
	var val=$(changeBtn).siblings(".hideInput").val()
	var val2=$(changeBtn).siblings("input[type=hidden]").val()
	csrfSend()
	$.ajax({
		url:"/admin/category/update",
		type:"post",
		data: {
			val:val,
			val2:val2
		},
		success: function(){
			listCategory()
		}
	})
}

function deleteCate(delBtn){
	var cateno=$(delBtn).siblings("input[type=hidden]").val()
	csrfSend();
	$.ajax({
		url:"/admin/category/delete",
		type:"post",
		data: {cateno:cateno},
		success: function(){
			listCategory()
		},error: function () {
        	alert("하위카테고리, 카테고리 연결된 상품을 제거해주세요")
    }
		
	})
}

function changeBtn(btn){
	$(btn).hide()
	$(btn).siblings(".vno,.hideInput,.hideBtn").show()
}




function listCategory(){
	$.ajax({
		url:"/admin/category/list/show",
		success:function(result){
			$("main> .wrap > .list").html(result)
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