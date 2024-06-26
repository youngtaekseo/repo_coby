package com.goodjob.infra.kakaopay;

public class AmountDto {
	private Integer total, tax_free, vat, point, discount;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getTax_free() {
		return tax_free;
	}

	public void setTax_free(Integer tax_free) {
		this.tax_free = tax_free;
	}

	public Integer getVat() {
		return vat;
	}

	public void setVat(Integer vat) {
		this.vat = vat;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	@Override
	public String toString() {
		return "결제금액정보(AmountDto) [결제 금액(total): " + getTotal() + ", 비과세 금액(tax_free):" + getTax_free()
			+ ", 부가세 금액(vat): " + getVat() + ", 포인트 금액(point): " + getPoint() + ", 할인 금액(discount): " + getDiscount() + "]";
	}
	
}
