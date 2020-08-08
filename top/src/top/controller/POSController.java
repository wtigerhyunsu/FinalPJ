package top.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import top.frame.Biz;
import top.vo.SalesDetailVO;
import top.vo.SalesVO;

@Controller
public class POSController {

	@Resource(name = "salesbiz")
	Biz<String, SalesVO> salesbiz;

	@Resource(name = "salesdetailbiz")
	Biz<String, SalesDetailVO> salesdetailbiz;

	SalesDetailVO sd;
	SalesVO sales;

	String regdate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

	@RequestMapping(value = "/posorder.top", method = RequestMethod.POST)
	@ResponseBody
	public String orderData(HttpServletRequest request, HttpServletResponse response, @RequestBody String str) {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		System.out.println("getOrderData From Android. ---Start");

		System.out.println("getJsondata Posorder :" + str);
		SalesVO sales = new SalesVO();
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(str);
			JSONObject jsonObj = (JSONObject) obj;
	//		System.out.println("JSONPARSER posorder: " + obj);
			JSONObject realdata = (JSONObject) jsonObj.get("jsonData");
	//		System.out.println("JSONPARSER realdata : " + realdata);
			/*
			 * String salesID; String salesRegDate; int totSales; String chainID; String
			 * chainName;
			 */
			String salesID = "salesID";
			String salesRegDate = regdate;
			String totSales = (String) (realdata.get("orderCost") + "");
			String chainID = (String) realdata.get("chainID");
			String chainName = "카페 TOP(역삼 1호점)";

			sales.setChainID(chainID);
			sales.setChainName(chainName);
			sales.setTotSales((Integer.parseInt(totSales)));
			sales.setSalesRegDate(regdate);
			insertSaleData(sales);
//			System.out.println("SalesID : " + salesID);
//			System.out.println("Salesregdate : " + regdate);
//			System.out.println("totSales : " + totSales);
//			System.out.println("chainID : " + chainID);
//			System.out.println("chainName : " + chainName);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String result = "success";

		return result;
	}

	@RequestMapping(value = "/posorderdetail.top", method = RequestMethod.POST)
	@ResponseBody
	public String orderDetailData(HttpServletRequest request, HttpServletResponse response, @RequestBody String str) {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

	//	System.out.println("getOrderDetailData from Android. ---Start");

	//	System.out.println("getOrderDetail JSONDATA : " + str);

		JSONParser parser = new JSONParser();
		

		
		try {
			Object obj = parser.parse(str);
			JSONArray jarr = (JSONArray) obj;
	//		System.out.println("POSORDERDETAIL  : " + obj);
			for (int i = 0; i < jarr.size(); i++) {

				JSONObject jo = (JSONObject) jarr.get(i);

				String chainID = (String) jo.get("chainID");
				String salesID = (salesbiz.getbychain(chainID)).getSalesID();
				String menuID = (String) jo.get("menuID");
				String menuName = (String) jo.get("menuName");
				String menuPrice = (String) (jo.get("menuCost") + "");
				String menuCount = (String) (jo.get("menuCount") + "");

				SalesDetailVO sd = new SalesDetailVO();
				sd.setMenuName(menuName);
				sd.setMenuPrice(menuPrice);
				sd.setMenuID(menuID);
				sd.setSalesDetailRegDate(regdate);
				sd.setSalesDetailID("salesDetailID");
				sd.setSalesID(salesID);
				sd.setMenuName(menuName.replace(" ",""));
				sd.setMenuCount(menuCount);
		//		System.out.println("SALES DETAILDATA : " + sd);

				insertSalesDetailData(sd);
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}

		String result = "success";

		return result;
	}


	@RequestMapping(value = "/posgetdata.top", method = RequestMethod.GET)
	@ResponseBody
	public String getSalesData(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("String");
		response.setCharacterEncoding("UTF-8");
	//	System.out.println("getRequest From Android : get All Sales DATA  ---Start");

		ArrayList<SalesVO> salesList = new ArrayList<SalesVO>();
		
		JSONArray salesJA = new JSONArray();
		try {
			salesList = salesbiz.get();
		
			for (SalesVO data : salesList) {
				JSONObject jo = new JSONObject();
				jo.put("salesID", data.getSalesID());
				jo.put("salesRegDate", data.getSalesRegDate());
				jo.put("totSales", data.getTotSales());
				jo.put("chainID", data.getChainID());
				salesJA.add(jo);
			}
			
	//		System.out.println("sales selectAll Done");
		} catch (Exception e) {
			e.printStackTrace();
		}

	//	System.out.println("Send to Android salesJA" + salesJA.toString());

		return salesJA.toString();
	}
	
	@RequestMapping(value = "/posgetdailydata.top", method = RequestMethod.GET)
	@ResponseBody
	public String getDailyData(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("String");
		response.setCharacterEncoding("UTF-8");
	//	System.out.println("getRequest From Android : get Daily Sales DATA  ---Start");

		ArrayList<SalesVO> salesList2 = new ArrayList<SalesVO>();
		
		JSONArray salesJA2 = new JSONArray();
		try {
			salesList2 = salesbiz.getdailysales();
			for( SalesVO data2 : salesList2) {
				JSONObject jo2 = new JSONObject();
				jo2.put("dailySales",data2.getDailySales());
				jo2.put("revenue",data2.getRevenue());
				salesJA2.add(jo2);
			}
	
			System.out.println("DailySales selectAll Done");
		} catch (Exception e) {
			e.printStackTrace();
		}

	//	System.out.println("Send to Android salesJA2" + salesJA2.toString());

		return salesJA2.toString();
	}
	
	

	public void insertSaleData(SalesVO sales) {

		try {
			salesbiz.register(sales);
			System.out.println("salesData Inserted");
		} catch (Exception e) {
			System.out.println("salesData Inserting Failed");
			e.printStackTrace();
		}
	}

	public void insertSalesDetailData(SalesDetailVO sd) {
		try {
			salesdetailbiz.register(sd);
			System.out.println("salesDetailData Inserted");
		} catch (Exception e) {
			System.out.println("salesDetaildata Inserting Failed");
			e.printStackTrace();
		}

	}

}
