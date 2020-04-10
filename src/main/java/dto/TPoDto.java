package dto;

import java.util.Date;
import java.util.List;


public class TPoDto {
	private String poNo;
	private Date poDate;
	private String supId;
	private String cityId;
	private String supName;
	private String cityName;
	private String provId;
	private String provName;
	private String poShipment;
	private String poNotes;
	private String poAddress;
	private String supAddress;

	private	Date poExpDate;
	private double discount;
	private double total;
	private long aging;
	private List<TPoDetailDto> details;
	
	
	
	
	public String getSupAddress() {
		return supAddress;
	}
	public void setSupAddress(String supAddress) {
		this.supAddress = supAddress;
	}
	public long getAging() {
		return aging;
	}
	public void setAging(long aging) {
		this.aging = aging;
	}
	public String getPoAddress() {
		return poAddress;
	}
	public void setPoAddress(String poAddress) {
		this.poAddress = poAddress;
	}
	public String getProvId() {
		return provId;
	}
	public void setProvId(String provId) {
		this.provId = provId;
	}
	public String getProvName() {
		return provName;
	}
	public void setProvName(String provName) {
		this.provName = provName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getPoNotes() {
		return poNotes;
	}
	public void setPoNotes(String poNotes) {
		this.poNotes = poNotes;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	public String getPoShipment() {
		return poShipment;
	}
	public void setPoShipment(String poShipment) {
		this.poShipment = poShipment;
	}
	public Date getPoExpDate() {
		return poExpDate;
	}
	public void setPoExpDate(Date poExpDate) {
		this.poExpDate = poExpDate;
	}
	
	public String getPoNo() {
		return poNo;
	}
	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}
	public Date getPoDate() {
		return poDate;
	}
	public void setPoDate(Date poDate) {
		this.poDate = poDate;
	}
	public String getSupId() {
		return supId;
	}
	public void setSupId(String supId) {
		this.supId = supId;
	}
	public String getSupName() {
		return supName;
	}
	public void setSupName(String supName) {
		this.supName = supName;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public List<TPoDetailDto> getDetails() {
		return details;
	}
	public void setDetails(List<TPoDetailDto> details) {
		this.details = details;
	}
	
	
	
}
