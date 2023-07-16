/**
 * 
 */
function changeDef(liTag) {
	$(".img-area .img").removeClass("def");
	$(liTag).addClass("def");
}

function pay(btnTag) {
	var amount = parseInt($(btnTag).siblings(".amount").val());
	var name = $(btnTag).siblings(".name").val();
	var postM = parseInt($(".postM").val());

	if (amount < 50000) {
		amount = amount + postM
	}

	var IMP = window.IMP; // 생략 가능
	IMP.init("imp34346152");

	IMP.request_pay({
		pg: "kcp",
		pay_method: "card",
		merchant_uid: "ORD" + new Date().getTime(),   // 주문번호
		name: name,
		amount: amount,                         // 숫자 타입
		buyer_email: "gildong@gmail.com",
		buyer_name: "홍길동",
		buyer_tel: "010-4242-4242",
		buyer_addr: "서울특별시 강남구 신사동",
		buyer_postcode: "01181"
	}, function(rsp) { // callback
		//rsp.imp_uid 값으로 결제 단건조회 API를 호출하여 결제결과를 판단합니다.
		var msg = "";
		if (rsp.success) {
			msg = "결제완료";
		} else {
			msg = "결제실패";
		}
		alert(msg);
	});
}

function money(count) {
	var howmany = $(count).val()
	var price = $(".price").val()
	$(".amount").val(price * howmany)
	$("#purchase-amount").number(price * howmany);
}