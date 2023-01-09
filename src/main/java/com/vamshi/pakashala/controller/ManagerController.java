package com.vamshi.pakashala.controller;
 
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList; 
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vamshi.pakashala.EmailDetails;
import com.vamshi.pakashala.EmailService;
import com.vamshi.pakashala.dataconnection.GetDataUser; 
import com.vamshi.pakashala.entity.ItemDetails;
import com.vamshi.pakashala.entity.Login;
import com.vamshi.pakashala.entity.Material;
import com.vamshi.pakashala.entity.MaterialUsage;
import com.vamshi.pakashala.entity.Orders;
import com.vamshi.pakashala.entity.Plans;
import com.vamshi.pakashala.service.GetItems;
import com.vamshi.pakashala.service.GetOrders;
import com.vamshi.pakashala.service.RegisterUser;
import com.vamshi.pakashala.service.SaveItem;
import com.vamshi.pakashala.service.SavePlan; 

@Controller
public class ManagerController {
	@Autowired private EmailService emailService;
	@GetMapping(value="/mlogin")
    public String loginManage(ModelMap model, HttpServletRequest request){
		
		return "mlogin"; 
	}
	
	@PostMapping(value="/manage")
	public String loginManage(@ModelAttribute("user")Login uld,ModelMap model,HttpServletRequest request){ 
		String uname=uld.getUname();
		String psswd=uld.getPassword();
		Login l=new Login(); 
		GetDataUser g=new GetDataUser(); 
		l.setUname(g.getmLoginData(uname,psswd).getUname()) ;
		if(l.getUname().equals("failed")) {
			model.addAttribute("message", "failed");
			return "mlogin";
		}
		else {
			ArrayList<ItemDetails> itlist = new ArrayList<>();
			GetItems gi=new GetItems();
			itlist=gi.getItemsList(); 
			model.addAttribute("items", itlist);
			request.getSession().setAttribute("notes", "1"); 
			request.getSession().setAttribute("firstname",uname);
			model.addAttribute("message", uname);
			return "manage";
		} 
    }
	
	@GetMapping(value="/manage")
	public String getLoginManage(ModelMap model,HttpServletRequest request){ 
		String t=(String)request.getSession().getAttribute("notes");
		if(t==null)
			t="0";
		if(t.equals("1")) {  
			ArrayList<ItemDetails> itlist = new ArrayList<>();
			GetItems gi=new GetItems();
			itlist=gi.getItemsList();
			model.addAttribute("message", request.getSession().getAttribute("firstname"));
			model.addAttribute("items", itlist);
			return "manage";
		}
		else { 
			return "mlogin"; 
		} 
    }
	
	@RequestMapping(value="/mlogout", method = RequestMethod.GET)
    public String logout(ModelMap model, HttpServletRequest request){ 
		request.getSession().invalidate(); 
		request.getSession().setAttribute("notes", "0");
		
        return "mlogin";
    }
	
	
	@PostMapping(value="/saveItem")
	public String saveItem(@ModelAttribute("itemdetail")ItemDetails itd,ModelMap model,HttpServletRequest request){ 
		String t=(String)request.getSession().getAttribute("notes");
		if(t==null)
			t="0";
		if(!t.equals("1")) {  
			return "mlogin"; 
		}
		 long itid;
		 String uploadedFolder = "src/main/resources/static/";
		 Random ran = new Random();
		 SaveItem si=new SaveItem();
		 itid=si.getmaxItemId();
		 itid+=ran.nextInt(10) + 1;
		 String imgdt="";
		 String extension="";
         try {
        	 if(itd.getItemid().equals("new")) {
        		 byte[] bytes = itd.getFi().getBytes();
             	String[] fileFrags = itd.getFi().getOriginalFilename().split("\\.");
             	extension = fileFrags[fileFrags.length-1];
             	imgdt="img/"+itid+"."+extension;
             	boolean b=si.saveItemDetails(imgdt,itid,itd);
             	imgdt=uploadedFolder+"img/"+itid+"."+extension;
             	if(b) {
                 Path path = Paths.get(imgdt);
     			Files.write(path, bytes); } 
        	 }
        	 else {
        		 System.out.println("inside else save");
        		 if(!itd.getFi().isEmpty()) {
        			 System.out.println("inside else save not empty");
                  	String[] fileFrags = itd.getFi().getOriginalFilename().split("\\.");
                  	extension = fileFrags[fileFrags.length-1];
                  	System.out.println(extension);
                  	
        		 } 
        		 itid=Long.valueOf(itd.getItemid()); 
        		 if(!itd.getFi().isEmpty()) {
        			 
                    	imgdt="img/"+itid+"."+extension;
                 }
        		 else { 
            		 imgdt=itd.getFilepath(); 
        		 }
        		 
        		 
        		 boolean b=si.updateItemDetails(imgdt,itid,itd);
        		 
        		 if(!itd.getFi().isEmpty()) {
               	imgdt=uploadedFolder+"img/"+itid+"."+extension;
               	if(b) {
               		System.out.println("inside else save success query");
               	 byte[] bytes = itd.getFi().getBytes();
                   Path path = Paths.get(imgdt);
       			Files.write(path, bytes); }
               	}
        	 }
        	
		} catch (IOException e1) {
			e1.printStackTrace();
		}
         ArrayList<ItemDetails> itlist = new ArrayList<>();
			GetItems gi=new GetItems();
			itlist=gi.getItemsList();
			model.addAttribute("message", request.getSession().getAttribute("firstname"));
			model.addAttribute("items", itlist);
		return "manage";
    }
	@GetMapping(value="/mplan")
    public String planManage(ModelMap model, HttpServletRequest request){
		String t=(String)request.getSession().getAttribute("notes");
		if(t==null)
			t="0";
		if(!t.equals("1")) {  
			return "mlogin"; 
		}
		model.addAttribute("message", request.getSession().getAttribute("firstname"));
		ArrayList<Plans> plist = new ArrayList<>();
		GetItems gi=new GetItems();
		plist=gi.getPlansList();
		model.addAttribute("plans", plist);  
		return "mplans"; 
	}
	@GetMapping(value="/materials")
    public String materialManage(ModelMap model, HttpServletRequest request){
		String t=(String)request.getSession().getAttribute("notes");
		if(t==null)
			t="0";
		if(!t.equals("1")) {  
			return "mlogin"; 
		}
		model.addAttribute("message", request.getSession().getAttribute("firstname"));
		ArrayList<Material> mlist = new ArrayList<>();
		GetItems gi=new GetItems();
		mlist=gi.getMaterialList();
		model.addAttribute("materials", mlist);  
		return "material"; 
	}
	@PostMapping(value="/savePlan")
	public String savePlan(@ModelAttribute("plandetail")Plans pdt,ModelMap model,HttpServletRequest request){ 
		String t=(String)request.getSession().getAttribute("notes");
		if(t==null)
			t="0";
		if(!t.equals("1")) {  
			return "mlogin"; 
		}
		 long itid; 
		 Random ran = new Random();
		 SavePlan si=new SavePlan();
		 itid=si.getmaxPlanId();
		 itid+=ran.nextInt(10) + 1; 
         
        	 if(pdt.getPlanid().equals("new")) { 
             	boolean b=si.savePlanDetails(itid,pdt); 
             	if(b) {
                  System.out.println("saved successfully"); } 
        	   }
        	 else {
        		 System.out.println("inside else save"); 
        		 itid=Long.valueOf(pdt.getPlanid());   
        		 boolean b=si.updatePlanDetails(itid,pdt); 
               	if(b) {
               		System.out.println("inside else save success query"); 
               		}
               	}  
         ArrayList<Plans> plist = new ArrayList<>();
			GetItems gi=new GetItems();
			plist=gi.getPlansList();
			model.addAttribute("message", request.getSession().getAttribute("firstname"));
			model.addAttribute("plans", plist);
		return "mplans";
    }
	
	@GetMapping(value="/orders")
    public String pendingOrders(ModelMap model, HttpServletRequest request){
		String t=(String)request.getSession().getAttribute("notes");
		if(t==null)
			t="0";
		if(!t.equals("1")) {  
			return "mlogin"; 
		}
		model.addAttribute("message", request.getSession().getAttribute("firstname"));
		ArrayList<Orders> olist = new ArrayList<>();
		GetOrders go=new GetOrders();
		olist=go.pendingOrders(); 
		
		model.addAttribute("orders", olist);  
		return "orderm"; 
	}
	
	@GetMapping(value="/musage")
    public String materialUsage(ModelMap model, HttpServletRequest request){
		String t=(String)request.getSession().getAttribute("notes");
		if(t==null)
			t="0";
		if(!t.equals("1")) {  
			return "mlogin"; 
		}
		model.addAttribute("message", request.getSession().getAttribute("firstname"));
		ArrayList<ItemDetails> ilist = new ArrayList<>();
		GetItems gi=new GetItems();
		ilist=gi.getActiveItems(); 
		model.addAttribute("items", ilist);  
		return "musage"; 
	}
	
	@PostMapping(value="/materials")
    public String getmaterials(ModelMap model, HttpServletRequest request) throws JsonProcessingException{
		String t=(String)request.getSession().getAttribute("notes");
		if(t==null)
			t="0";
		if(!t.equals("1")) {  
			return "mlogin"; 
		}
		model.addAttribute("message", request.getSession().getAttribute("firstname"));
		ArrayList<Material> mlist = new ArrayList<>();
		GetItems gi=new GetItems();
		mlist=gi.getMaterialList();
		ObjectMapper om = new ObjectMapper(); 
		model.addAttribute("result", om.writeValueAsString(mlist));  
		return "apiresult"; 
	}
	
	@PostMapping(value="/smaterial")
    public String getSinglematerial(@RequestParam("mid") String mid, ModelMap model, HttpServletRequest request) throws JsonProcessingException{
		String t=(String)request.getSession().getAttribute("notes");
		if(t==null)
			t="0";
		if(!t.equals("1")) {  
			return "mlogin"; 
		}
		model.addAttribute("message", request.getSession().getAttribute("firstname"));
		Material mat = new Material();
		GetItems gi=new GetItems();
		mat=gi.getMaterial(mid);
		ObjectMapper om = new ObjectMapper(); 
		model.addAttribute("result", om.writeValueAsString(mat));  
		return "apiresult"; 
	}
    
    @PostMapping(value="/musageitems")
    public String getmaterialUsage(@RequestParam("itemid") String itd, ModelMap model, HttpServletRequest request) throws JsonProcessingException{
		String t=(String)request.getSession().getAttribute("notes");
		if(t==null)
			t="0";
		if(!t.equals("1")) {  
			return "mlogin"; 
		} 
		ArrayList<MaterialUsage> mulist = new ArrayList<>();
		GetItems gi=new GetItems();
		mulist=gi.getMaterialUsage(itd) ;
		ObjectMapper om = new ObjectMapper(); 
		model.addAttribute("result", om.writeValueAsString(mulist));  
		return "apiresult"; 
	}
    
    
    @PostMapping(value="/addMaterialUsage")
    public String addMaterialUsage(@RequestParam("itemid") String itd,@RequestParam("mid") String mid,@RequestParam("portion") float portion,@RequestParam("units") String units, ModelMap model, HttpServletRequest request) throws JsonProcessingException{
		String t=(String)request.getSession().getAttribute("notes");
		if(t==null)
			t="0";
		if(!t.equals("1")) {  
			return "mlogin"; 
		}  
		GetItems gi=new GetItems(); 
		MaterialUsage mu = new MaterialUsage();
		mu.setItemId(itd);
		mu.setmId(mid);
		mu.setUnits(units);
		mu.setPortion(portion);
		mu.setIsEnabled("true");
		gi.insertMaterialUsage(mu);
		model.addAttribute("result","success");  
		return "apiresult"; 
	}
    
	@PostMapping(value="/confirmorder")
    public String confirmOrder(@RequestParam("orderid") String oid,ModelMap model, HttpServletRequest request){
		String t=(String)request.getSession().getAttribute("notes");
		if(t==null)
			t="0";
		if(!t.equals("1")) {  
			return "mlogin"; 
		}
		 
		GetOrders go=new GetOrders();
		RegisterUser ru=new RegisterUser();
		Orders so=new Orders();
		so=go.getSingleOrder(oid);
		String firstname=ru.getUserFirstName(so.getUsername());
		go.confirmOrder(oid); 
		EmailDetails details=new EmailDetails();
		String msbody="Hi "+firstname+"\nyour Order with Order ID:"+oid+" has been accepted.\norder is under preparation.\n"
		+"\nRegards\nPakashala";
		String sub="Your Order is Confirmed: Pakashala";
		details.setMsgBody(msbody);
		details.setRecipient(so.getUsername());
		details.setSubject(sub);
		String status = emailService.sendSimpleMail(details);
		System.out.println(status); 
		return pendingOrders(model, request); 
	}
	
	@PostMapping(value="/saveMat")
	public String saveMaterial(@ModelAttribute("material")Material mi,ModelMap model,HttpServletRequest request){ 
		String t=(String)request.getSession().getAttribute("notes");
		if(t==null)
			t="0";
		if(!t.equals("1")) {  
			return "mlogin"; 
		}
		 long mid; 
		 Random ran = new Random();
		 SavePlan si=new SavePlan();
		 mid=si.getmaxMaterialId();
		 mid+=ran.nextInt(10) + 1; 
         
        	 if(mi.getmId().equals("new")) { 
             	boolean b=si.saveMaterials(mid,mi); 
             	if(b) {
                  System.out.println("saved successfully"); } 
        	   }
        	 else {
        		 System.out.println("inside else save"); 
        		 mid=Long.valueOf(mi.getmId());   
        		 boolean b=si.updateMaterials(mid,mi); 
               	if(b) {
               		System.out.println("inside else save success query"); 
               		}
               	}  
        	 ArrayList<Material> mlist = new ArrayList<>();
     		GetItems gi=new GetItems();
     		mlist=gi.getMaterialList();
     		model.addAttribute("materials", mlist);  
			model.addAttribute("message", request.getSession().getAttribute("firstname")); 
		return "material";
    }
	@PostMapping(value="/denyOrder")
    public String denyOrder(@RequestParam("orderid") String orderid,ModelMap model, HttpServletRequest request){
		String t=(String)request.getSession().getAttribute("notes");
		if(t==null)
			t="0";
		if(!t.equals("1")) {  
			return "mlogin"; 
		}
		model.addAttribute("message", request.getSession().getAttribute("firstname")); 
		GetOrders go=new GetOrders();
		go.cancelOrder(orderid);
		Orders od=new Orders();
		od=go.getSingleOrder(orderid);
		String items=od.getItem_id();
		String[] arrOfCart = null;
		arrOfCart = items.split(",");
		ArrayList<MaterialUsage> mulist = new ArrayList<>();
		GetItems gi=new GetItems();
		for (String a : arrOfCart)
    	{    
			mulist=gi.getMaterialUsage(a);
    		for (MaterialUsage mu : mulist) {
					gi.rollbackUpdateMaterial(mu); 
			}
    	}
		
		EmailDetails details=new EmailDetails();
		String msbody="Hi "+request.getSession().getAttribute("firstname")+"\nyour Order has been cancelled.\nyour refund is in process.\n"
		+"\n Regards\nPakashala";
		String sub="Order Cancelled: Pakashala order id:"+orderid;
		details.setMsgBody(msbody);
		details.setRecipient(od.getUsername());
		details.setSubject(sub);
		String status = emailService.sendSimpleMail(details);
		System.out.println(status);
		
		
		model.addAttribute("result", "denied");
		return "apiresult"; 
	}
}
