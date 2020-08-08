package top.vo;

public class SalesVO {

	String salesID;
	String salesRegDate;
	int totSales;
	String chainID;
	String chainName;
	String dailySales;
	String revenue;

	
	
	public SalesVO() {

	}

	
	public SalesVO(String salesID, String salesRegDate, int totSales, String chainID, String chainName) {
		super();
		this.salesID = salesID;
		this.salesRegDate = salesRegDate;
		this.totSales = totSales;
		this.chainID = chainID;
		this.chainName = chainName;
	}

	public SalesVO(String dailySales, String revenue) {
		this.dailySales = dailySales;
		this.revenue = revenue;

	}
	
	@Override
	public String toString() {
		return "SalesVO [salesID=" + salesID + ", salesRegDate=" + salesRegDate + ", totSales=" + totSales
				+ ", chainID=" + chainID + ", dailySales=" + dailySales + ", revenue=" + revenue + "]";
	}



	public String getDailySales() {
		return dailySales;
	}


	public void setDailySales(String dailySales) {
		this.dailySales = dailySales;
	}


	public String getRevenue() {
		return revenue;
	}


	public void setRevenue(String revenue) {
		this.revenue = revenue;
	}


	public String getSalesID() {
		return salesID;
	}

	public void setSalesID(String salesID) {
		this.salesID = salesID;
	}

	public String getSalesRegDate() {
		return salesRegDate;
	}

	public void setSalesRegDate(String salesRegDate) {
		this.salesRegDate = salesRegDate;
	}

	public int getTotSales() {
		return totSales;
	}

	public void setTotSales(int totSales) {
		this.totSales = totSales;
	}

	public String getChainID() {
		return chainID;
	}

	public void setChainID(String chainID) {
		this.chainID = chainID;
	}

	public String getChainName() {
		return chainName;
	}

	public void setChainName(String chainName) {
		this.chainName = chainName;
	}

	
}
