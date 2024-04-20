package com.goodjob.infra.kakaopay;

//취소 요청 시 전달한 값

public class CancelAvailableAmountDto {
	private int total; // 전체 취소 가능 금액
    private int tax_free; // 취소 가능 비과세 금액
    private int vat; // 취소 가능 부가세 금액
    private int point; // 취소 가능 포인트 금액
    private int discount; // 취소 가능 할인 금액
    private int green_deposit; // 컵 보증금
    
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getTax_free() {
		return tax_free;
	}
	public void setTax_free(int tax_free) {
		this.tax_free = tax_free;
	}
	public int getVat() {
		return vat;
	}
	public void setVat(int vat) {
		this.vat = vat;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public int getGreen_deposit() {
		return green_deposit;
	}
	public void setGreen_deposit(int green_deposit) {
		this.green_deposit = green_deposit;
	}
	@Override
	public String toString() {
		String result = "남은취소금액(CancelAvailableAmountDto) ["
				+ "전체 취소 가능 금액(total): " + getTotal()
				+ ", 취소 가능 비과세 금액(tax_free): " + getTax_free()
				+ ", 취소 가능 부가세 금액(vat): " + getVat()
				+ ", 취소 가능 포인트 금액(point): " + getPoint()
				+ ", 취소 가능 할인 금액(discount): " + getDiscount()
				+ ", 컵 보증금(green_deposit): " + getGreen_deposit()
				+ "]"
				;
		
		return result;
	}
    
}
