/**
 * 
 */

function tempUpload(img) {
	var fileData = $(img)[0].files[0];
	var formData = new FormData();
	formData.append("temp", fileData);

	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});


	$.ajax({
		url: "/admin/product/temp-img",
		type: "POST",
		contentType: false,
		processData: false,
		data: formData,
		success: function(result) {
			$(".temp-img").css("background-image",`url(${result.imgUrl})`)
		}
	})

}