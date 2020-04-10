package vmd;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Messagebox.Button;
import org.zkoss.zul.Messagebox.ClickEvent;

import dto.TPoDetailDto;
import dto.TPoDto;
import entity.MCity;
import entity.MItem;
import entity.MProvince;
import entity.MSupplier;
import service.MCitySvc;
import service.MItemSvc;
import service.MProvinceSvc;
import service.MSupplierSvc;
import service.TpoSvc;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class TPoDetailVmd {
	
	@WireVariable
	private MCitySvc citySvc;
	@WireVariable
	private MItemSvc itemSvc;
	@WireVariable
	private MProvinceSvc provSvc;
	@WireVariable
	private MSupplierSvc supSvc;
	@WireVariable
	private TpoSvc orderSvc;
	
	private MSupplier sup;
	private MItem item;
	private MProvince prov;
	private MCity city;
	
	private TPoDto dto;
	private TPoDetailDto detail;
	
	private List<TPoDetailDto> details = new ArrayList<>();
	private List<MSupplier> supplier = new ArrayList<>();
	private List<MItem> items = new ArrayList<>();
	private List<MProvince> province = new ArrayList<>();
	private List<MCity> cities = new ArrayList<>();
	
	private boolean statusModal = false;

	public MCitySvc getCitySvc() {
		return citySvc;
	}

	public void setCitySvc(MCitySvc citySvc) {
		this.citySvc = citySvc;
	}

	public MItemSvc getItemSvc() {
		return itemSvc;
	}

	public void setItemSvc(MItemSvc itemSvc) {
		this.itemSvc = itemSvc;
	}

	public MProvinceSvc getProvSvc() {
		return provSvc;
	}

	public void setProvSvc(MProvinceSvc provSvc) {
		this.provSvc = provSvc;
	}

	public MSupplierSvc getSupSvc() {
		return supSvc;
	}

	public void setSupSvc(MSupplierSvc supSvc) {
		this.supSvc = supSvc;
	}

	public TpoSvc getOrderSvc() {
		return orderSvc;
	}

	public void setOrderSvc(TpoSvc orderSvc) {
		this.orderSvc = orderSvc;
	}

	public MSupplier getSup() {
		return sup;
	}

	public void setSup(MSupplier sup) {
		this.sup = sup;
	}

	public MItem getItem() {
		return item;
	}

	public void setItem(MItem item) {
		this.item = item;
	}

	public MProvince getProv() {
		return prov;
	}

	public void setProv(MProvince prov) {
		this.prov = prov;
	}

	public MCity getCity() {
		return city;
	}

	public void setCity(MCity city) {
		this.city = city;
	}

	public TPoDto getDto() {
		return dto;
	}

	public void setDto(TPoDto dto) {
		this.dto = dto;
	}

	public TPoDetailDto getDetail() {
		return detail;
	}

	public void setDetail(TPoDetailDto detail) {
		this.detail = detail;
	}

	public List<TPoDetailDto> getDetails() {
		return details;
	}

	public void setDetails(List<TPoDetailDto> details) {
		this.details = details;
	}

	public List<MSupplier> getSupplier() {
		return supplier;
	}

	public void setSupplier(List<MSupplier> supplier) {
		this.supplier = supplier;
	}

	public List<MItem> getItems() {
		return items;
	}

	public void setItems(List<MItem> items) {
		this.items = items;
	}

	public List<MProvince> getProvince() {
		return province;
	}

	public void setProvince(List<MProvince> province) {
		this.province = province;
	}

	public List<MCity> getCities() {
		return cities;
	}

	public void setCities(List<MCity> cities) {
		this.cities = cities;
	}

	public boolean isStatusModal() {
		return statusModal;
	}

	public void setStatusModal(boolean statusModal) {
		this.statusModal = statusModal;
	}
	
	@Init
	public void load(){
		if(null!=Sessions.getCurrent().getAttribute("dto")){
			dto = (TPoDto)Sessions.getCurrent().getAttribute("dto");
			details = dto.getDetails();
			sup = supSvc.findOne(dto.getSupId());
			city = citySvc.findOne(dto.getCityId());
			prov = provSvc.findOne(dto.getProvId());
			getAgingVal();
		}else{
			dto = new TPoDto();
			details = new ArrayList<TPoDetailDto>();
		}
		supplier = supSvc.list();
		items = itemSvc.list();
		province = provSvc.list();
		cities = citySvc.list();
	}
	
	@NotifyChange({"statusModal","detail","item"})
	@Command
	public void showModal() {
		if(null==dto.getPoNo() || "".equals(dto.getPoNo()) || sup==null){
			Messagebox.show("Data Header Perlu Diisi!");
		}else{
			detail = new TPoDetailDto();
			item = null;
			statusModal = true;
		}
	}
	
	@NotifyChange({"statusModal","detail"})
	@Command
	public void backModal() {
		detail = null;
		statusModal = false;
	}
	
	@NotifyChange("detail")
	@Command
	public void back() {
		Executions.sendRedirect("order.zul");
		detail = new TPoDetailDto();
	}
	
	@NotifyChange("detail")
	@Command
	public void selectItem() {
		detail.setItemId(item.getItemId());
		detail.setItemName(item.getItemName());
		detail.setItemPrice(item.getItemPrice());
	}
	
	@NotifyChange("dto")
	@Command
	public void selectSup() {
		dto.setSupId(sup.getSupId());
		dto.setSupName(sup.getSupName());
		dto.setPoAddress(sup.getSupAddress());
	}
	
	@NotifyChange("detail")
	@Command
	public void countSub(){
		detail.setSubtotal(detail.getItemPrice()*detail.getItemQty());
	}
	
	@NotifyChange({"details","dto"})
	@Command
	public void deleteDetail() {
		if(detail!=null){
			details.remove(detail);
			int x = 0;
			for(TPoDetailDto o : details){
				x+=o.getSubtotal();
			}
			dto.setTotal(x);
		}else{
			Messagebox.show("Pilih detail!");
		}
	}
	
	@NotifyChange({"details","statusModal","dto","detail"})
	@Command
	public void saveDetail() {
		if(detail.getItemQty()>0){
			int x = 0;
			if(details.size()>0){
				int index=-1;
				for(int i=0;i<details.size() ;i++){
					System.out.println("ListSize : "+details.size());
					if(details.get(i).getItemId().equals(detail.getItemId())){
						index=i;
						break;
					}else{
						index=-1;
					}
				}
				System.out.println("Index : "+index);
				if(index<0){
					details.add(detail);
					x+=detail.getSubtotal();
				}else{
					details.get(index).setItemQty(details.get(index).getItemQty()+detail.getItemQty());
					details.get(index).setSubtotal(details.get(index).getSubtotal()+detail.getSubtotal());
				}
			}else{
				
				details.add(detail);
			}
			
			dto.setTotal(dto.getTotal()+detail.getSubtotal());
			backModal();
		}else{
			Messagebox.show("Masukkan jumlah Item!");
		}
	}
	
	@Command
	public void saveOrder() {
		if(details.size()<1){
			Messagebox.show("Item kosong, silahkan pilih item yang akan dibeli.");
		}else if(null==sup){
			Messagebox.show("Pilih Supplier!");
		}else{
			Messagebox.show("Simpan Data?","Konfirmasi", new Button[]{Button.YES, Button.NO},
					Messagebox.QUESTION, Button.NO, new EventListener<Messagebox.ClickEvent>() {
					public void onEvent(ClickEvent event) {
						if(Messagebox.ON_YES.equals(event.getName())){
							dto.setSupId(sup.getSupId());
							dto.setCityId(city.getCityId());
							dto.setPoAddress(sup.getSupAddress());
							dto.setDetails(details);
							orderSvc.update(dto);
							Messagebox.show("Data Telah Berhasil Disimpan");
							Executions.sendRedirect("order.zul");
						}
					}
				});
		}
	}
	
	@Command
	@NotifyChange("dto")
	public void getAgingVal(){
		long aging =(dto.getPoExpDate().getTime() - dto.getPoDate().getTime())/86400000;
		dto.setAging(aging);
	}
	
	@Command
	@NotifyChange("*")
	public void getAllSubtotal(){
		if(details.size() > 0){
			dto.setTotal(0);
			for (TPoDetailDto dtos: details){
				dto.setTotal(dto.getTotal() + dtos.getSubtotal());
			}
		}
	}
	
	@Command
	@NotifyChange("*")
	public void getChangeTotal(){
		if(dto.getDiscount() < 0 || dto.getDiscount() > 100){
			Messagebox.show("Diskon hanya bisa 1 - 100 %");
			dto.setDiscount(0);
		} else {
			getAllSubtotal();
			dto.setTotal(dto.getTotal() - (dto.getTotal() * dto.getDiscount()/100));
		}
	}
	
}
