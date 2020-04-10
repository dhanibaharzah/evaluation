package vmd;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.BindUtils;
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

import dto.TPoDto;
import service.TpoSvc;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class TPoVmd {

	@WireVariable
	private TpoSvc orderSvc;
	
	private String cari = "";
	
	public String getCari() {
		return cari;
	}
	public void setCari(String cari) {
		this.cari = cari;
	}

	private List<TPoDto> list = new ArrayList<>();
	private TPoDto dto;
	public TpoSvc getOrderSvc() {
		return orderSvc;
	}
	public void setOrderSvc(TpoSvc orderSvc) {
		this.orderSvc = orderSvc;
	}
	public List<TPoDto> getList() {
		return list;
	}
	public void setList(List<TPoDto> list) {
		this.list = list;
	}
	public TPoDto getDto() {
		return dto;
	}
	public void setDto(TPoDto dto) {
		this.dto = dto;
	}
	
	@Init
	public void loadData(){
		Sessions.getCurrent().removeAttribute("details");
		Sessions.getCurrent().removeAttribute("dto");
		list=orderSvc.list();
	}
	
	@Command
	public void add(){
		Executions.sendRedirect("orderdetail.zul");
	}
	
	@Command
	public void edit() {
		if(dto!=null){
			Sessions.getCurrent().setAttribute("dto", dto);
			Executions.sendRedirect("orderdetail.zul");
		}else{
			Messagebox.show("Silahkan Pilih Data");
		}
	}
	
	@Command
	public void delete(){
		if(dto!=null){
			Messagebox.show("Yakin ingin hapus?","Perhatian", new Button[]{Button.YES, Button.NO},
				Messagebox.QUESTION, Button.NO, new EventListener<Messagebox.ClickEvent>() {
				public void onEvent(ClickEvent event) {
					if(Messagebox.ON_YES.equals(event.getName())){
						orderSvc.delete(dto);
						list.remove(dto);
						BindUtils.postNotifyChange(null, null, TPoVmd.this, "list");
						Messagebox.show("Data Berhasil Dihapus!");
					}
					
				}
			});
		}
		else{
			Messagebox.show("Silahkan Pilih Data");
		}
	}
	
	@Command
	@NotifyChange("list")
	public void cari(){
		List<TPoDto> listCari = orderSvc.find(cari);
			if(listCari.size() > 0){
				list = listCari;
			} else {
				Messagebox.show("No Data");
				//list = employeeSvc.findAllKaryawan();
			}
	}
	
	
	
}
