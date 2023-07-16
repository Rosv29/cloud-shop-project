/**
 * 
 */
//엔터 submit 방지
document.addEventListener('keydown', function(event) {
    if (event.keyCode === 13) {
        event.preventDefault();
    }
}, true);

$(function() {
	$("input[type=text]").change(function(){
		$(this).siblings(".checkInput").prop("checked",false)
	})
	
	$("#password").change(function(){
		$("#password").siblings(".checkInput").prop("checked",false)
		$("#confirm-password").siblings(".checkInput").prop("checked",false)
	})
	$("#confirm-password").change(function(){
		$("#confirm-password").siblings(".checkInput").prop("disabled",true)
	})

	
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
	var passwordRegex = /^(?!((?:[A-Za-z]+)|(?:[~!@#$%^&*()_+=]+)|(?:[0-9]+))$)[A-Za-z\d~!@#$%^&*()_+=]{6,16}$/;
	if(passwordRegex.test(passwordVal)){
		$("#password").siblings(".msg").text("사용 가능한 비밀번호입니다.").css("color", "green");
		$("#password").siblings(".checkInput").prop("checked",true)
	}else{
		$("#password").siblings(".msg").text("비밀번호는 6~16자의 영어, 숫자, 특수문자(@#$%^&*)로 입력해주세요").css("color", "red");
	}
}

//비밀번호 확인
function confirmPassword(){
	var passwordVal=$("#password").val();
	var confirmVal=$("#confirm-password").val().trim();
	if(confirmVal==passwordVal&&!confirmVal==""){
		$("#confirm-password").siblings(".msg").text("비밀번호가 일치합니다.").css("color", "green");
		$("#confirm-password").siblings(".checkInput").prop("checked",true)
	}else{
		$("#confirm-password").siblings(".msg").text("비밀번호가 일치하지 않습니다.").css("color", "red");
	}
}

//이름 정규식 검사
function regexName(){
	var nameVal=$("#name").val();
	var nameRegex = /^[가-힣]{2,10}|[a-zA-Z]{2,20}\s[a-zA-Z]{2,20}$/
	if(nameRegex.test(nameVal)){
		$("#name").siblings(".checkInput").prop("checked",true)
		$("#name").siblings(".msg").text("").css("color", "green");
	}else{
		$("#name").siblings(".msg").text("올바른 이름이 아닙니다.").css("color", "red");
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
		$(".email-wrap > button").prop("disabled",true)
	}
}
function regexPhone(){
	var phoneRegex=/^0[0-9]{9,10}$/
	var phoneVal=$("#phone").val();
	if(phoneRegex.test(phoneVal)){
		$("#phone").siblings(".msg").text("사용가능한 핸드폰번호입니다").css("color", "green");
		$("#phone").siblings(".checkInput").prop("checked",true)
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
				$("#id").siblings(".checkInput").prop("checked",true)
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
				$(".email-wrap > button").prop("disabled",false)
			} else {
				$("#email").siblings(".msg").text("이미 존재하는 이메일입니다.").css("color", "red");
				$(".email-wrap > button").prop("disabled",true)
			}
		}
	})
	
}
// 인증번호 전송
function verifyEmail(){
	var emailVal = $("#email").val();
	// CSRF 토큰 가져오기
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$.ajax({
		url: "/member/verifyEmail",
		type: "post",
		data: { email: emailVal },
		beforeSend: function(xhr) {
			// AJAX 요청 헤더에 CSRF 토큰 추가
			xhr.setRequestHeader(header, token);
		},
		success() {
			$("#email").siblings(".checkInput").prop("checked",true)
			$("#email").prop("readonly",true)
			$(".email-wrap > button").prop("disabled",true)
			$(".emailCheck-wrap").show()
			$(".emailCheck-wrap").find(".msg").text("인증번호는 3분동안 유효합니다.").css("color","green")
			$(".emailCheck-wrap > button").prop("disabled",false)
		}
	})
	
}
//인증번호확인
function verifyCode(){
	var emailVal = $("#email").val();
	var codeVal = $("#emailCheck").val();
	// CSRF 토큰 가져오기
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$.ajax({
		url: "/member/verifyCode",
		type: "post",
		data: { email: emailVal },
		beforeSend: function(xhr) {
			// AJAX 요청 헤더에 CSRF 토큰 추가
			xhr.setRequestHeader(header, token);
		},
		success(result) {
			if(result==codeVal){
				$("#emailCheck").siblings(".msg").text("인증이 완료되었습니다.").css("color", "green");
				$("#email").removeAttr("onfocusout")
				$(".emailCheck-wrap > button").prop("disabled",true)
				$("#emailCheck").prop("readonly",true)
				$("#emailCheck").siblings(".checkInput").prop("checked",true)
			}else{
				$("#emailCheck").siblings(".msg").text("인증번호가 틀렸습니다").css("color", "red");				
				
			}
		}
	})
}
//submit 체크
function checkForm(){
	var count=$(".checkInput:checked").length
	if(count==7){
		return true;
	}else{
		$(".checkInput:not(:checked)").siblings("input").focus()
		$(".checkInput:not(:checked)").siblings("input").each(function(index) {
		if (index === 0) {
			$(this).focus();
		}
		});
		return false;
	}
}