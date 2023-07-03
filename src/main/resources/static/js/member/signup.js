/**
 * 
 */
$(function() {

})


//id정규식검사 -> 성공하면 id중복체크
function regexId() {
	var idVal = $("#id").val()
	var idRegex = /^[a-z0-9]{6,16}$/;
	if (idRegex.test(idVal)) {
		checkId(idVal);
	} else {
		$("#id").siblings(".msg").text("아이디는 6~16자의 소문자와 숫자로 입력해주세요.").css("color", "red");
	}

}

//비밀번호 정규식검사
function regexPassword() {
	var passwordVal=$("#password").val();
	var passwordRegex = /^[A-Za-z\d!@#$%^&*]{6,20}$/;
	if(passwordRegex.test(passwordVal)){
		$("#password").siblings(".msg").text("사용 가능한 비밀번호입니다.").css("color", "green");
	}else{
		$("#password").siblings(".msg").text("비밀번호는 6~16자의 영어, 숫자, 특수문자(@#$%^&*)로 입력해주세요").css("color", "red");
	}
}

//비밀번호 확인
function confirmPassword(){
	var passwordVal=$("#password").val();
	var confirmVal=$("#confirm-password").val().trim();
	if(confirmVal==passwordVal){
		$("#confirm-password").siblings(".msg").text("비밀번호가 일치합니다.").css("color", "green");
	}else{
		$("#confirm-password").siblings(".msg").text("비밀번호가 일치하지 않습니다.").css("color", "red");
	}
}

//이메일 정규식 검사 -> 성공하면 이메일 중복체크
function regexEmail() {
	var emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
	var emailVal = $("#email").val();
	if (emailRegex.test(emailVal)) {
		checkEmail(emailVal);
	} else {
		$("#email").siblings(".msg").text("잘못된 이메일 형식입니다.").css("color", "red");
	}
}
function regexPhone(){
	var phoneRegex=/^0[0-9]{9,10}$/
	var phoneVal=$("#phone").val();
	if(phoneRegex.test(phoneVal)){
		$("#phone").siblings(".msg").text("사용가능한 핸드폰번호입니다").css("color", "green");
	}else{		
		$("#phone").siblings(".msg").text("잘못된 전화번호 형식입니다.").css("color", "red");
	}
}


//아이디 중복검사
function checkId(idVal) {
	// CSRF 토큰 가져오기
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$.ajax({
		url: "/member/checkId",
		type: "post",
		data: { id: idVal },
		beforeSend: function(xhr) {
			// AJAX 요청 헤더에 CSRF 토큰 추가
			xhr.setRequestHeader(header, token);
		},
		success(result) {
			if (result) {
				$("#id").siblings(".msg").text("사용 가능한 아이디입니다.").css("color", "green");
			} else {
				$("#id").siblings(".msg").text("이미 존재하는 아이디입니다.").css("color", "red");
			}
		}
	})
}

//이메일 중복검사
function checkEmail(emailVal){
	// CSRF 토큰 가져오기
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$.ajax({
		url: "/member/checkEmail",
		type: "post",
		data: { email: emailVal },
		beforeSend: function(xhr) {
			// AJAX 요청 헤더에 CSRF 토큰 추가
			xhr.setRequestHeader(header, token);
		},
		success(result) {
			if (result) {
				$("#email").siblings(".msg").text("사용 가능한 이메일입니다.").css("color", "green");
			} else {
				$("#email").siblings(".msg").text("이미 존재하는 이메일입니다.").css("color", "red");
			}
		}
	})
	
}