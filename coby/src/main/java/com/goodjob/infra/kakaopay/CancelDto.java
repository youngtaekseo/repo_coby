package com.goodjob.infra.kakaopay;

// 결제 취소 요청 시 사용

public class CancelDto {
	private String aid; // 요청 고유 번호
    private String tid; // 결제 고유 번호
    private String cid; // 가맹점 코드
    private String status; // 결제 상태
    private String partner_order_id; // 가맹점 주문 번호
    private String partner_user_id; // 가맹점 회원 ID
    private String payment_method_type; // 결제 수단
    private AmountDto amount; // 결제 금액 정보, 결제 요청 구현할때 이미 구현해놓음
    private CancelAmountDto approved_cancel_amount; // 이번 요청으로 취소된 금액
    private CanceledAmountDto canceled_amount; // 누계 취소 금액
    private CancelAvailableAmountDto cancel_available_amount; // 남은 취소 금액
    private String item_name; // 상품 이름
    private String item_code; // 상품 코드
    private int quantity; // 상품 수량
    private String created_at; // 결제 준비 요청 시각
    private String approved_at; // 결제 승인 시각
    private String canceled_at; // 결제 취소 시각
    private String payload; // 취소 요청 시 전달한 값
    
    private String resultInfo;
    
	public String getResultInfo() {
		return resultInfo;
	}
	public void setResultInfo(String resultInfo) {
		this.resultInfo = resultInfo;
	}
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPartner_order_id() {
		return partner_order_id;
	}
	public void setPartner_order_id(String partner_order_id) {
		this.partner_order_id = partner_order_id;
	}
	public String getPartner_user_id() {
		return partner_user_id;
	}
	public void setPartner_user_id(String partner_user_id) {
		this.partner_user_id = partner_user_id;
	}
	public String getPayment_method_type() {
		return payment_method_type;
	}
	public void setPayment_method_type(String payment_method_type) {
		this.payment_method_type = payment_method_type;
	}
	public AmountDto getAmount() {
		return amount;
	}
	public void setAmount(AmountDto amount) {
		this.amount = amount;
	}
	public CancelAmountDto getApproved_cancel_amount() {
		return approved_cancel_amount;
	}
	public void setApproved_cancel_amount(CancelAmountDto approved_cancel_amount) {
		this.approved_cancel_amount = approved_cancel_amount;
	}
	public CanceledAmountDto getCanceled_amount() {
		return canceled_amount;
	}
	public void setCanceled_amount(CanceledAmountDto canceled_amount) {
		this.canceled_amount = canceled_amount;
	}
	public CancelAvailableAmountDto getCancel_available_amount() {
		return cancel_available_amount;
	}
	public void setCancel_available_amount(CancelAvailableAmountDto cancel_available_amount) {
		this.cancel_available_amount = cancel_available_amount;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public String getItem_code() {
		return item_code;
	}
	public void setItem_code(String item_code) {
		this.item_code = item_code;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getApproved_at() {
		return approved_at;
	}
	public void setApproved_at(String approved_at) {
		this.approved_at = approved_at;
	}
	public String getCanceled_at() {
		return canceled_at;
	}
	public void setCanceled_at(String canceled_at) {
		this.canceled_at = canceled_at;
	}
	public String getPayload() {
		return payload;
	}
	public void setPayload(String payload) {
		this.payload = payload;
	}
	@Override
	public String toString() {
		String result = "결제취소요청(KakaoPayCancelDto) [요청 고유 번호(aid): " + getAid()
				+ ", 결제 고유 번호(tid): " + getTid()
				+ ", 가맹점 코드(cid): " + getCid()
				+ ", 결제 상태(status): " + getStatus()
				+ ", 가맹점 주문 번호(partner_order_id): " + getPartner_order_id()
				+ ", 가맹점 회원 ID(partner_user_id): " + getPartner_user_id()
				+ ", 결제 수단(payment_method_type): " + getPayment_method_type()
				+ ", 상품 이름(item_name): " + getItem_name()
				+ ", 상품 코드(item_code): " + getItem_code()
				+ ", 상품 수량(quantity): " + getQuantity()
				+ ", 결제 준비 요청 시각(created_at): " + getCreated_at()
				+ ", 결제 승인 시각(approved_at): " + getApproved_at()
				+ ", 결제 취소 시각(canceled_at): " + getCanceled_at()
				+ ", 취소 요청 시 전달한 값(payload): " + getPayload() + "]"
				+ ", " + getAmount().toString()
				+ ", " + getApproved_cancel_amount().toString()
				+ ", " + getCanceled_amount().toString()
				+ ", " + getCancel_available_amount().toString()
				; 
				
		return result;
	}
        
}
