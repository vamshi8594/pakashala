package com.vamshi.pakashala.controller;
 

import java.lang.annotation.Repeatable;
import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap; 
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.vamshi.pakashala.EmailDetails;
import com.vamshi.pakashala.EmailService;
import com.vamshi.pakashala.dataconnection.GetDataUser;
import com.vamshi.pakashala.entity.ItemDetails;
import com.vamshi.pakashala.entity.Login;
import com.vamshi.pakashala.entity.MaterialUsage;
import com.vamshi.pakashala.entity.Meal;
import com.vamshi.pakashala.entity.Orders;
import com.vamshi.pakashala.entity.Plans;
import com.vamshi.pakashala.entity.Subs;
import com.vamshi.pakashala.entity.UserDetail;
import com.vamshi.pakashala.service.GetItems;
import com.vamshi.pakashala.service.GetOrders;
import com.vamshi.pakashala.service.GetPlans;
import com.vamshi.pakashala.service.RegisterUser; 
import com.vamshi.pakashala.service.Subscription; 

@Controller
@SessionAttributes("name")
public class MainController {
	@Autowired private EmailService emailService;
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String mainpage(ModelMap model, HttpServletRequest request){
		return showLoginPage(model,request);
	}
	@RequestMapping(value="/home", method = RequestMethod.GET) 
    public String showLoginPage(ModelMap model, HttpServletRequest request){
		HttpSession session = request.getSession(false);
		ArrayList<ItemDetails> cartit = new ArrayList<>();
		ArrayList<ItemDetails> itlist = new ArrayList<>();
		GetItems gi=new GetItems();
		Map<ItemDetails, Integer> hm = new HashMap<ItemDetails, Integer>();
		if(session==null){ 
			model.addAttribute("message", "failed");
			request.getSession().setAttribute("notes", "0");
			System.out.println("inside failed");
		}
		else {
			String s=(String)request.getSession().getAttribute("notes"); 
			if(s==null) {
				s="0";
			}
			if(s.equals("1")) {
				model.addAttribute("message", (String)request.getSession().getAttribute("firstname"));
				String str = (String) request.getSession().getAttribute("cart");
				String[] arrOfStr = null;
				if(str!=null)
				{	arrOfStr = str.split(","); 
		        	for (String a : arrOfStr)
		        	{    System.out.println(a);
		        	Integer j = hm.get(gi.getSingleItem(a));
		            hm.put(gi.getSingleItem(a), (j == null) ? 1 : j + 1); 
		        	}
		        }
				model.addAttribute("cartit", hm);
				model.addAttribute("cartcount", hm.size());
			}
			else {
				model.addAttribute("message", "failed");
			} 
		} 
		
		itlist=gi.getActiveItems(); 
		model.addAttribute("items", itlist);
        return "index";
    }
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logout(ModelMap model, HttpServletRequest request){ 
		request.getSession().invalidate();
		model.addAttribute("message", "failed");
		request.getSession().setAttribute("notes", "0"); 
		ArrayList<ItemDetails> itlist = new ArrayList<>();
		GetItems gi=new GetItems();
		itlist=gi.getActiveItems(); 
		model.addAttribute("items", itlist);
        return "index";
    }
	
	
	
	@RequestMapping(value="/about", method = RequestMethod.GET)
    public String showAboutPage(ModelMap model, HttpServletRequest request){
	HttpSession session = request.getSession(false);
	ArrayList<ItemDetails> cartit = new ArrayList<>(); 
	GetItems gi=new GetItems();
	Map<ItemDetails, Integer> hm = new HashMap<ItemDetails, Integer>();
		if(session==null){ 
			model.addAttribute("message", "failed");
			request.getSession().setAttribute("notes", "0");
			System.out.println("inside failed");
			return "AboutUs";
		}
		else {
			String s=(String)request.getSession().getAttribute("notes");
			if(s==null) {
				s="0";
			}
			if(s.equals("1")) {
				model.addAttribute("message", (String)request.getSession().getAttribute("firstname"));
				String str = (String) request.getSession().getAttribute("cart");
				String[] arrOfStr = null;
				if(str!=null)
				{	arrOfStr = str.split(","); 
		        	for (String a : arrOfStr)
		        	{    System.out.println(a);
		        	Integer j = hm.get(gi.getSingleItem(a));
		            hm.put(gi.getSingleItem(a), (j == null) ? 1 : j + 1); 
		        	}
		        }
				model.addAttribute("cartit", hm);
				model.addAttribute("cartcount", hm.size());
			}
			else {
				model.addAttribute("message", "failed");
			} 
		} 
        return "AboutUs";
    }
	
	
	
	@RequestMapping(value="/plans", method = RequestMethod.GET)
    public String showPlansPage(ModelMap model, HttpServletRequest request){
		HttpSession session = request.getSession(false);
		ArrayList<ItemDetails> cartit = new ArrayList<>(); 
		GetItems gi=new GetItems();
		GetPlans gp=new GetPlans();
		GetOrders go=new GetOrders();
		String uname= (String)request.getSession().getAttribute("uname");
		System.out.println("plans"+uname);
		Map<ItemDetails, Integer> hm = new HashMap<ItemDetails, Integer>();
		ArrayList<Plans> plist = new ArrayList<>();
		ArrayList<Orders> olist = new ArrayList<>();
		ArrayList<Meal> mlist = new ArrayList<>();
		plist=gp.getPlanList();
		model.addAttribute("plans", plist);
		Subscription sb=new Subscription();
		Subs su;
		if(session==null){ 
			model.addAttribute("message", "failed");
			request.getSession().setAttribute("notes", "0"); 
			return "plans";
		}
		else {
			String s=(String)request.getSession().getAttribute("notes");
			if(s==null) {
				s="0";
			}
			if(s.equals("1")) {
				model.addAttribute("message", (String)request.getSession().getAttribute("firstname"));
				String str = (String) request.getSession().getAttribute("cart");
				String[] arrOfStr = null;
				if(str!=null)
				{	arrOfStr = str.split(","); 
		        	for (String a : arrOfStr)
		        	{     
		        	Integer j = hm.get(gi.getSingleItem(a));
		            hm.put(gi.getSingleItem(a), (j == null) ? 1 : j + 1); 
		        	}
		        }
				su=sb.getUserSubscription(uname);
				olist=go.getOrderHistory(uname);
				mlist=go.getMealist(uname);
				model.addAttribute("cartit", hm);
				model.addAttribute("cartcount", hm.size());
				model.addAttribute("currentplan", gp.getCurrentPlan(uname));
				model.addAttribute("orders", olist);
				model.addAttribute("subscription", su);
				model.addAttribute("meals", mlist); 
			}
			else {
				model.addAttribute("message", "failed");
			} 
		} 
        return "plans";
    }
	
	@PostMapping(value="/selectplan")
    public String savePlan(@ModelAttribute("plans") Plans pl,ModelMap model,HttpServletRequest request){
		
		String uname=(String)request.getSession().getAttribute("uname");
		String planid=pl.getPlanid();
		long subid; 
		Random ran = new Random();
		Subscription subs=new Subscription();
		subid=Long.parseLong(subs.getMaxSubId());
		subid+=ran.nextInt(10) + 1; 
		boolean b=subs.createSubscription(uname, planid,subid);
		if(b) {
            System.out.println("subscribed successfully"); }  
		return showPlansPage(model,request);
    }
	
	@PostMapping(value="/selectmeal")
    public String selectMeal(@ModelAttribute("mealpref") Meal ml,@RequestParam(name = "mealdate") String ss,ModelMap model,HttpServletRequest request){
		ml.setmDate(Date.valueOf(ss));
		String uname=(String)request.getSession().getAttribute("uname"); 
		long mid;
		Random ran = new Random();
		Subscription subs=new Subscription();
		mid=Long.parseLong(subs.getMaxMealId());
		mid+=ran.nextInt(10) + 1; 
		boolean b=subs.addMeal(uname, mid,ml);
		if(b) { System.out.println("added meal successfully"); }else {model.addAttribute("mealadderror", "failed");}  
		return showPlansPage(model,request);
    }
	
	@RequestMapping(value="/loginreg", method = RequestMethod.GET)
    public String loginRegister(ModelMap model, HttpServletRequest request){
//		model.addAttribute("message", "initial");
//		System.out.println(model.getAttribute("message"));
		HttpSession session = request.getSession(false);
		
		if(session==null){  
			request.getSession().setAttribute("notes", "0");  
		}
        return "login";
    }
	@RequestMapping(value="/login", method = RequestMethod.POST)
    public String loginsubmit(@ModelAttribute("user")Login uld,ModelMap model,HttpServletRequest request){ 
		String uname=uld.getUname();
		String psswd=uld.getPassword();
		Login l=new Login(); 
		GetDataUser g=new GetDataUser(); 
		l.setUname(g.getLoginData(uname,psswd).getUname()) ;
		if(l.getUname().equals("failed")) {
			model.addAttribute("message", "failed");
			return "login";
		}
		else { 
			request.getSession().setAttribute("notes", "1");
			RegisterUser  ru=new RegisterUser();
			UserDetail ud=ru.getUser(uname);
			String fname=ud.getFname();
			request.getSession().setAttribute("firstname",fname);
			System.out.println("during Login"+ud.getUname());
			request.getSession().setAttribute("uname",ud.getUname());
			model.addAttribute("message", fname);
			ArrayList<ItemDetails> itlist = new ArrayList<>();
			GetItems gi=new GetItems();
			itlist=gi.getActiveItems(); 
			model.addAttribute("items", itlist);
			return "index";
		} 
    }
	@PostMapping(value="/register")
    public String registersubmit(@ModelAttribute("ruserdetail") UserDetail usrd,ModelMap model,HttpServletRequest request){
		
		String uname=usrd.getUname();  
		GetDataUser g=new GetDataUser();
		boolean c=g.checkUser(uname); 
		if(c==true) {
			model.addAttribute("message", "user already exists");
			return "login";
		}
		else {
			RegisterUser  ru=new RegisterUser();
			ru.register(usrd);
			model.addAttribute("message", "successfully registered! login with new user");
			return "login";
		} 
    }
	
	@PostMapping(value="/viewItem")
	public String viewItem(@ModelAttribute("viewItem") ItemDetails vi,ModelMap model,HttpServletRequest request) {
		String item=vi.getItemid();
		ItemDetails si=new ItemDetails();  
		GetItems gi=new GetItems();
		Map<ItemDetails, Integer> hm = new HashMap<ItemDetails, Integer>();
		si=gi.getSingleItem(item);
		model.addAttribute("sitem", si);
		String s=(String)request.getSession().getAttribute("notes");
		if(s==null) {
			s="0";
		}
		if(s.equals("1")) {
			model.addAttribute("message", (String)request.getSession().getAttribute("firstname"));
			String str = (String) request.getSession().getAttribute("cart");
			String[] arrOfStr = null;
			if(str!=null)
			{	arrOfStr = str.split(","); 
	        	for (String a : arrOfStr)
	        	{    System.out.println(a);
	        	Integer j = hm.get(gi.getSingleItem(a));
	            hm.put(gi.getSingleItem(a), (j == null) ? 1 : j + 1); 
	        	}
	        }
			model.addAttribute("cartit", hm);
			model.addAttribute("cartcount", hm.size());
		}
		else {
			model.addAttribute("message", "failed");
		} 
		return "viewItem"; 
	}
	
	
	@PostMapping(value="/addcart")
	public String addCart(@ModelAttribute("itemDetail") ItemDetails vi,ModelMap model,HttpServletRequest request) {
		String item=vi.getItemid();
		 String cart="";
		String s=(String)request.getSession().getAttribute("notes");
		HttpSession session = request.getSession(false);
		ArrayList<ItemDetails> cartit = new ArrayList<>();
		Map<ItemDetails, Integer> hm = new HashMap<ItemDetails, Integer>();
		GetItems gi=new GetItems();
		if(session==null){ 
			model.addAttribute("message", "failed");
			request.getSession().setAttribute("notes", "0");  
		}
		else { 
			if(s==null) {
				s="0";
			}
			if(s.equals("1")) {
				cart=(String)request.getSession().getAttribute("cart");
				if(cart==null)
					cart=item;
				else
					cart+=","+item;
				request.getSession().setAttribute("cart", cart); 
				model.addAttribute("message", request.getSession().getAttribute("firstname"));
				System.out.println("cart: "+cart); 
				ItemDetails si=new ItemDetails();
				
				si=gi.getSingleItem(item);
				model.addAttribute("sitem", si);
				String str = (String) request.getSession().getAttribute("cart");
				String[] arrOfStr = null;
				if(str!=null)
				{	arrOfStr = str.split(","); 
		        	for (String a : arrOfStr)
		        	{    System.out.println(a);
		        	Integer j = hm.get(gi.getSingleItem(a));
		            hm.put(gi.getSingleItem(a), (j == null) ? 1 : j + 1); 
		        	}
		        }
				model.addAttribute("cartit", hm);
				model.addAttribute("cartcount", hm.size());
			}
			else {
				model.addAttribute("message", "please login to add item to cart");
				return "login";
			} 
		} 
		return "viewItem"; 
	}
	@PostMapping(value="/search")
	public String searchItems(@RequestParam(name = "search") String ss,ModelMap model,HttpServletRequest request) {
		System.out.println("in search");
		model.addAttribute("searchstr", ss);
		String s=(String)request.getSession().getAttribute("notes"); 
		ArrayList<ItemDetails> cartit = new ArrayList<>(); 
		GetItems gi=new GetItems();
		ArrayList<ItemDetails> itlist = new ArrayList<>();
		Map<ItemDetails, Integer> hm = new HashMap<ItemDetails, Integer>();
		 
		itlist=gi.getSearchRes(ss);
		if(s==null) {
			s="0";
		}
		if(s.equals("1")) {
			model.addAttribute("message", (String)request.getSession().getAttribute("firstname"));
			String str = (String) request.getSession().getAttribute("cart");
			String[] arrOfStr = null;
			if(str!=null)
			{	arrOfStr = str.split(","); 
	        	for (String a : arrOfStr)
	        	{    System.out.println(a);
	        	Integer j = hm.get(gi.getSingleItem(a));
	            hm.put(gi.getSingleItem(a), (j == null) ? 1 : j + 1); 
	        	}
	        }
			model.addAttribute("cartit", hm);
			model.addAttribute("cartcount", hm.size());
		}
		else {
			model.addAttribute("message", "failed");
		}
		model.addAttribute("sres", itlist);
		return "search"; 
	}
	@GetMapping(value="/clearcart")
	public String clearcart(@RequestParam(name = "dirpage") String dirpage,@RequestParam(name = "itemid") String itemid,ModelMap model,HttpServletRequest request) {
		System.out.println("in clear cart"); 
		String s=(String)request.getSession().getAttribute("notes");  
		GetItems gi=new GetItems(); 
		ItemDetails si=new ItemDetails();
		if(s==null) {
			s="0";
		}
		if(s.equals("1")) {
			model.addAttribute("message", (String)request.getSession().getAttribute("firstname"));
			request.getSession().removeAttribute("cart");
			request.getSession().removeAttribute("cartcount");
			if(dirpage.equals("index")) { 
				return showLoginPage(model,request);
			}
			if(dirpage.equals("viewItem")) {
				si=gi.getSingleItem(itemid);
				model.addAttribute("sitem", si);
				System.out.println(si.toString());
				return dirpage;
			}
			if(dirpage.equals("plans")) { 
				return showPlansPage(model,request);
			}
			if(dirpage.equals("AboutUs")) { 
				return showAboutPage(model,request);
			}
		}
		else {
			model.addAttribute("message", "failed");
			return "login";
		}

		return dirpage; 
	}
	
	@GetMapping(value="/checkout")
	public String checkout(ModelMap model,HttpServletRequest request) { 
		String s=(String)request.getSession().getAttribute("notes");
		String uname=(String)request.getSession().getAttribute("uname"); 
		ArrayList<ItemDetails> itlist = new ArrayList<>();
		Map<ItemDetails, Integer> hm = new HashMap<ItemDetails, Integer>();
		GetItems gi=new GetItems(); 
		ItemDetails si=new ItemDetails();
		if(s==null) {
			s="0";
		}
		if(s.equals("1")) {
			model.addAttribute("message", (String)request.getSession().getAttribute("firstname")); 
			String str = (String) request.getSession().getAttribute("cart");
			String[] arrOfStr = null;
			if(str!=null)
			{	arrOfStr = str.split(","); 
	        	for (String a : arrOfStr)
	        	{    System.out.println(a);
	        	Integer j = hm.get(gi.getSingleItem(a));
	            hm.put(gi.getSingleItem(a), (j == null) ? 1 : j + 1); 
	        	}
	        }
			model.addAttribute("cartit", hm);
			model.addAttribute("cartcount", hm.size());
		}
		else {
			model.addAttribute("message", "failed");
			return "login";
		}

		return "checkout"; 
	}
	
	@PostMapping(value="/ReduceItem")
	public String redduceItem(@RequestParam("id") String itemId,ModelMap model,HttpServletRequest request) { 
		String s=(String)request.getSession().getAttribute("notes"); 
		ArrayList<ItemDetails> itlist = new ArrayList<>();
		System.out.println("item id check"+itemId);
		if(s==null) {
			s="0"; 
		}
		if(s.equals("1")) {
			model.addAttribute("message", (String)request.getSession().getAttribute("firstname")); 
			String str = (String) request.getSession().getAttribute("cart");
			String res="";
			String[] arrOfStr = null;
			if(str!=null)
			{
				int i=1;
				arrOfStr = str.split(","); 
	        	for (String a : arrOfStr)
	        	{
	        		if(i==1 && a.equals(itemId))
	        		{System.out.println(a);
	        		i--;
	        			continue;
	        		}
	        		if(res.equals(""))
	        			res=a;
	        		else
	        			res=res+","+a; 
	        	}
	        }  
			model.addAttribute("result",res);
			System.out.println("string left:"+res);
			request.getSession().removeAttribute("cart"); 
			if(res.equals(""))
				res=null;
			request.getSession().setAttribute("cart", res); 
		}
		else {
			model.addAttribute("message", "failed"); 
		} 
		return "apiresult";
	}
	
	@PostMapping(value="/IncreaseItem")
	public String increaseItem(@RequestParam("id") String itemId,ModelMap model,HttpServletRequest request) { 
		String s=(String)request.getSession().getAttribute("notes");  
		System.out.println("item id check"+itemId);
		String cart="";
		if(s==null) {
			s="0"; 
		}
		if(s.equals("1")) {
			cart=(String)request.getSession().getAttribute("cart");
			if(cart==null)
				cart=itemId;
			else
				cart+=","+itemId;
			request.getSession().setAttribute("cart", cart);  
			model.addAttribute("result",cart);
			System.out.println("string left:"+cart); 
		}
		else {
			model.addAttribute("message", "failed"); 
		} 
		return "apiresult";
	}
	@GetMapping(value="/payment")
	public String processPayment(ModelMap model,HttpServletRequest request) { 
		String s=(String)request.getSession().getAttribute("notes");
		Map<ItemDetails, Integer> hm = new HashMap<ItemDetails, Integer>();
		GetItems gi=new GetItems();
		String uname= (String)request.getSession().getAttribute("uname"); 
		ArrayList<Orders> olist = new ArrayList<>();
		GetOrders go=new GetOrders();
		float total=0;
		if(s==null) {
			s="0"; 
		}
		if(s.equals("1")) {
			String str = (String) request.getSession().getAttribute("cart");
			String[] arrOfStr = null;
			if(str!=null)
			{	arrOfStr = str.split(","); 
	        	for (String a : arrOfStr)
	        	{    System.out.println(a);
	        	total+=Float.parseFloat(gi.getSingleItem(a).getPrice()); 
	        	}
			}
	        DecimalFormat f = new DecimalFormat("##.##");
	        olist=go.getOrderHistory(uname);
	        model.addAttribute("orders", olist); 
	        model.addAttribute("cart", str); 
			model.addAttribute("Price", total); 
			model.addAttribute("tax", f.format(total*0.091));
			model.addAttribute("TotalPrice", f.format(total+total*0.091));
			model.addAttribute("message", uname);
			request.getSession().setAttribute("price", total);
			request.getSession().setAttribute("tax", f.format(total*0.091));
			request.getSession().setAttribute("totalprice", f.format(total+total*0.091));
			return "payment";
		}
		else {
			model.addAttribute("message", "failed"); 
		} 
		return showLoginPage(model,request);
	}
	
	@PostMapping(value="NoteAddress")
	public String noteAddressSession(@RequestParam("notes") String notes,@RequestParam("address") String address,ModelMap model,HttpServletRequest request) {
		String s=(String)request.getSession().getAttribute("notes");
		if(s==null) {
			s="0"; 
		}
		if(s.equals("1")) {
			request.getSession().setAttribute("order_address", address);
			request.getSession().setAttribute("order_notes", notes);
			model.addAttribute("result","added");
		}
		return "apiresult";
	}
	
	@GetMapping(value="findmaterials")
	public String is_raw_available(ModelMap model,HttpServletRequest request) {
		String s=(String)request.getSession().getAttribute("notes");
		ArrayList<MaterialUsage> mulist = new ArrayList<>();
		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject item = new JSONObject();
		//String jsonop="""{"object":{ """;
		if(s==null) {
			s="0"; 
		}
		if(s.equals("1")) { 
			String firstname= (String)request.getSession().getAttribute("firstname"); 
			Orders or=new Orders(); 
			GetItems gi=new GetItems();
			String cart = (String) request.getSession().getAttribute("cart");
			String[] arrOfCart = null;
			
			if(cart!=null)
			{	arrOfCart = cart.split(","); 
				boolean ch,rollback=false;
	        	for (String a : arrOfCart)
	        	{    
	        			mulist=gi.getMaterialUsage(a);
	        			for (MaterialUsage mu : mulist) {
							ch=gi.updateMaterial(mu);
							if(!ch) {
								rollback=true;
								ItemDetails sitem=new ItemDetails();
								sitem=gi.getSingleItem(mu.getItemId());
								//jsonop+="\""+mu.getItemId()+"\":\""+sitem.getName()+"\",";
								item.put(sitem.getItemid(), sitem.getName()); 
							}
						}
	        	}
	        	array.add(item);
	        	if(rollback) { 
	        		//jsonop = jsonop.substring(0, jsonop.length() - 1);
	        		//jsonop+="},\"isdone\":\"false\"";
	        		json.put("isdone", false);
	        		for (String a : arrOfCart)
		        	{    
		        			mulist=gi.getMaterialUsage(a);
		        			for (MaterialUsage mu : mulist) {
								gi.rollbackUpdateMaterial(mu); 
							}
		        	}
	        	}
	        	else {
	        		json.put("isdone", true);
	        	}
	        	json.put("object", array);
	        	//jsonop+="}";
			} 
			System.out.println(json);
			model.addAttribute("result",json);
		}
		return "apiresult";
	}
	
	@PostMapping(value="/ordercreate")
	public String createOrder(ModelMap model,HttpServletRequest request) { 
		String s=(String)request.getSession().getAttribute("notes");
		String firstname= (String)request.getSession().getAttribute("firstname"); 
		Orders or=new Orders(); 
		GetOrders go=new GetOrders();
		String str = (String) request.getSession().getAttribute("cart");
		String address=(String)request.getSession().getAttribute("order_address");
		String notes=(String)request.getSession().getAttribute("order_notes");
		String uname=(String)request.getSession().getAttribute("uname"); 
		String price= Float.toString((float)request.getSession().getAttribute("price"));
		String totalPrice=(String) request.getSession().getAttribute("totalprice");  
		Date date=new Date(System.currentTimeMillis()); 
		Random ran = new Random();
		long ordId; 
		 ordId=go.getmaxOrderId();
		 ordId+=ran.nextInt(10) + 1;
		if(s==null) {
			s="0"; 
		}
		if(s.equals("1")) { 
			or.setOrder_id(Long.toString(ordId));
			or.setItem_id(str);
			or.setDate( date);
			or.setNotes(notes);
			or.setLocation(address);
			or.setPrice(price);
			or.setPaidStatus("pending");
			or.setUsername(uname);
			or.setOrderType("individual");
			or.setTotalPrice(totalPrice); 
			go.addOrder(or);
			EmailDetails details=new EmailDetails();
			String msbody="Hi "+firstname+"\nyour Order has been created successfully.\n order is pending confirmation.\n"
			+"Total Price :"+or.getTotalPrice()+"$ \n Regards\nPakashala";
			String sub="Awaiting Order Confirmation: Pakashala";
			details.setMsgBody(msbody);
			details.setRecipient(uname);
			details.setSubject(sub);
			String status = emailService.sendSimpleMail(details);
			System.out.println(status); 
			request.getSession().removeAttribute("cart");
			request.getSession().removeAttribute("cartcount");
			request.getSession().removeAttribute("price");
			request.getSession().removeAttribute("totalprice"); 
			return "apiresult";
		}
		else {
			model.addAttribute("message", "failed"); 
		} 
		return showLoginPage(model,request);
	}
	@GetMapping(value="/uorders")
    public String getOrders(ModelMap model, HttpServletRequest request){
		String t=(String)request.getSession().getAttribute("notes");
		if(t==null)
			t="0";
		if(!t.equals("1")) {  
			return showLoginPage(model,request); 
		}
		model.addAttribute("message", request.getSession().getAttribute("firstname"));
		String uname=(String)request.getSession().getAttribute("uname"); 
		ArrayList<Orders> polist = new ArrayList<>();
		ArrayList<Orders> npolist = new ArrayList<>();
		GetOrders go=new GetOrders();
		polist=go.pendingOrders(uname); 
		npolist=go.nonPendingOrders(uname);
		model.addAttribute("porders", polist);
		model.addAttribute("nporders", npolist);
		return "orders"; 
	}
	
	@PostMapping(value="/cancelOrder")
    public String cancelOrder(@RequestParam("orderid") String orderid,ModelMap model, HttpServletRequest request){
		String t=(String)request.getSession().getAttribute("notes");
		if(t==null)
			t="0";
		if(!t.equals("1")) {  
			return showLoginPage(model,request); 
		}
		model.addAttribute("message", request.getSession().getAttribute("firstname"));
		String uname=(String)request.getSession().getAttribute("uname");  
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
		details.setRecipient(uname);
		details.setSubject(sub);
		String status = emailService.sendSimpleMail(details);
		System.out.println(status); 
		
		
		model.addAttribute("result", "cancelled");
		return "apiresult"; 
	}
}
