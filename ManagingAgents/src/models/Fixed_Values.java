package models;

public class Fixed_Values {
	private int Maximum_Agent;
	private java.math.BigDecimal Rate;
	private java.math.BigDecimal Maximum_IsOverPay;
	
	public Fixed_Values() {
		super();
	}
	
	public Fixed_Values(int maximum_Agent, java.math.BigDecimal rate, java.math.BigDecimal maximum_IsOverPay) {
		super();
		Maximum_Agent = maximum_Agent;
		Rate = rate;
		Maximum_IsOverPay = maximum_IsOverPay;
	}
	
	public int getMaximum_Agent() {
		return Maximum_Agent;
	}
	public void setMaximum_Agent(int maximum_Agent) {
		Maximum_Agent = maximum_Agent;
	}
	public java.math.BigDecimal getRate() {
		return Rate;
	}
	public void setRate(java.math.BigDecimal rate) {
		Rate = rate;
	}
	public java.math.BigDecimal getMaximum_IsOverPay() {
		return Maximum_IsOverPay;
	}
	public void setMaximum_IsOverPay(java.math.BigDecimal maximum_IsOverPay) {
		Maximum_IsOverPay = maximum_IsOverPay;
	}

	@Override
	public String toString() {
		return "Fixed_Values [Maximum_Agent=" + Maximum_Agent + ", Maximum_InDebt=" + Rate
				+ ", Maximum_IsOverPay=" + Maximum_IsOverPay + "]";
	}
	
}
