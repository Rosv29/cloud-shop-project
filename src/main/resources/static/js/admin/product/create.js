/**
 * 
 */
$(function(){
	pdCategory($("#category1"))
})
function submitCheck(){
	var cate=$("#category2").val()
	var name=$("#pName").val()
	var price=$("#pPrice").val()
	var stock=$("#pStock").val()
	var content=$("#pContent").val()
	if(cate==null||cate==""){
		alert("카테고리를 지정해주세요")
		return false;
	}else if(name==null||name==""){
		alert("상품명을 작성해주세요")
		return false;
	}else if(price==null||price==""){
		alert("상품가격을 지정해주세요")
		return false;
	}else if(stock==null||stock==""){
		alert("상품재고수량을 지정해주세요")
		return false;
	}else if(content==null||content==""){
		alert("상품설명을 작성해주세요")
		return false;
	}else{
		return true;
	}
}


function pdCategory(tag){
	var tagVal=$(tag).val();
	if(tagVal===null)tagVal=0;
	csrfSend();
	$.ajax({
		url:"/admin/product/category",
		type:"post",
		data:{tagVal:tagVal},
		success:function(result){
			$(tag).html(result)
		}
	})
}
function pdCategory2(tag){
	var tagVal=$(tag).val();
	if(tagVal===null)tagVal=0;
		csrfSend();
	$.ajax({
		url:"/admin/product/category2",
		type:"post",
		data:{tagVal:tagVal},
		success:function(result){
			$("#category2").html(result)
		}
	})
}

function delAdd(inputTag){
	var tag=`<button class="btn-del" type="button" onclick="delImg(this)">
			사진삭제
		</button>`
	var exbtn=$(inputTag).parent().siblings(".btn-del").length
	if(exbtn==0)$(inputTag).parents(".img-wrap").append(tag)
}


function delImg(deleteBtn){
	$(deleteBtn).siblings(".temp-img").css("background-image","url('')")
	$(deleteBtn).siblings("input[type=hidden]:not('.def')").val('')
	var count=$(".line-wrap>.img-wrap").length
	if(count>1 && $(deleteBtn).siblings(".def").val()=="false"){
		$(deleteBtn).parent().remove()
	}else{
		$(deleteBtn).remove()		
	}
}


function tempUpload(img) {
	if($(img).val()===""||$(img).val()===null)return;
	var fileData = $(img)[0].files[0];
	var formData = new FormData();
	formData.append("temp", fileData);
	
	csrfSend();
	$.ajax({
		url: "/admin/product/temp-img",
		type: "POST",
		contentType: false,
		processData: false,
		data: formData,
		success: function(result) {
			$(img).parent().css("background-image", `url(${result.imgUrl})`)
			$(img).parents(".img-wrap").find(".tempKey").val(result.tempKey);
			$(img).parents(".img-wrap").find(".newName").val(result.newName);

			delAdd(img)
			
			var def = $(img).parents(".img-wrap").find(".def").val();
			if (def == "true") return;

			var addLength = $(".add-img>.line-wrap>.img-wrap").length;
			console.log("추가이미지 개수:" + addLength);
			var targetIdx = $(img).parents(".img-wrap").index()+1;
			console.log("수정하는 이미지 위치:" + targetIdx);
			//추가하면 안되는경우 
			//1.태그가 5개가 완료된경우
			//2. 5개만들기전 이전이미지를 수정하면
			if (addLength >= 5 || targetIdx < addLength) return;

			var appendTag = `
			 <div class="img-wrap">
				<label class="temp-img"><input type="file" accept="image/*" onchange="tempUpload(this);"></label>
				<input type="hidden" class="tempKey" name="tempKey">
				<input type="hidden" class="newName" name="newName">
				<input type="hidden" class="def" name="def" value="false">
			</div>
			 `;
			$(".line-wrap").append(appendTag);
			
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