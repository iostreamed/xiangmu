package com.ksd.oa.action;

import com.ksd.oa.entity.Address;
import com.ksd.oa.entity.User;
import com.ksd.oa.service.AddressService;
import com.ksd.oa.util.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AddressAction extends ActionSupport implements ModelDriven<Address>{
	
	private Address address = new Address();
	public Address getModel() {
		return address;
	}
	
	private AddressService addressService;
	public AddressService getAddressService() {
		return addressService;
	}

	public void setAddressService(AddressService addressService) {
		this.addressService = addressService;
	}
	private int page;
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
	public String addresslist(){
		User user = (User) ActionContext.getContext().getSession().get("user");
		PageBean<Address> addresslist = addressService.findByName(user.getUserName(), address.getName(), address.getEmail(), page);
		ActionContext.getContext().getSession().put("addresslist", addresslist);
		return "addresslist";
	}
	
	public String addAddress(){
		return"addAddress";
	}
	
	public String saveAddress(){
		User user = (User) ActionContext.getContext().getSession().get("user");
		//要改
		Address address1 = addressService.findByEmail(address.getEmail());
		if(address1!=null){
			this.addActionError("通讯录联系人已存在！");
			return "addAddress";
		}else{
			address1 = new Address(null,user.getUserName(), address.getName(), address.getSex(), address.getMobile(), address.getEmail(), address.getQq(), address.getAddress(), address.getPostcode());
			addressService.addAddress(address1);
			return "saveAddress";
		}
	}
	
	public String deleteAddress(){
		Address address1 = addressService.findById(address.getId());
		if(address1 != null){
			addressService.deleteAddress(address1);
			return "deleteAddress";
		}
		return "deleteAddress";
	}
	
	public String updateAddress(){
		Address address1 = addressService.findById(address.getId());
		ActionContext.getContext().getSession().put("address", address1);
		return "updateAddress";
	}
	
	public String saveUpdate(){
		Address address1 = (Address) ActionContext.getContext().getSession().get("address");
		if(address1!=null){
			address1.setName(address.getName());
			address1.setSex(address.getSex());
			address1.setMobile(address.getMobile());
			address1.setEmail(address.getEmail());
			address1.setQq(address.getQq());
			address1.setAddress(address.getAddress());
			address1.setPostcode(address.getPostcode());
			addressService.updateAddress(address1);
			return "saveUpdate";
		}
		return "updateAddress";
	}
}
