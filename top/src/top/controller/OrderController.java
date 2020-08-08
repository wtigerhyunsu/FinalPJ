package top.controller;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import top.frame.Biz;
import top.vo.IngredientVO;
import top.vo.OrderDetailVO;
import top.vo.OrderVO;

//order�� orderdetail ó�� ��Ʈ�ѷ� 
@Controller
public class OrderController {
	private Logger logger = Logger.getLogger(OrderController.class);// �α� ó�� ����

	@Resource(name = "ingbiz")
	Biz<String, IngredientVO> ingbiz;

	@Resource(name = "orderbiz")
	Biz<String, OrderVO> orderbiz;

	@Resource(name = "orderdetailbiz")
	Biz<String, OrderDetailVO> orderdetailbiz;

	@RequestMapping("/sendToContainer.top")
	public String sendToContainer(HttpServletRequest req) throws Exception {
	//	System.out.println("hahah");
		return "fetchOrderData.top";
	}

	@RequestMapping("/sendDataToDB.top")
	public void sendDataToDB(HttpServletRequest req) throws Exception {
	//	System.out.println("sendDataToDB start");
		String json = req.getParameter("data1");
		String loadProds = req.getParameter("loadProds");
		int dataLength = Integer.parseInt(loadProds);
		ArrayList<String> orderDetailID = new ArrayList<>();
		ArrayList<OrderDetailVO> OrderDetailVO = new ArrayList<>();
		// logger.info("This is an info log entry");
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;

		try {
			jsonObject = (JSONObject) parser.parse(json);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		OrderDetailVO OrderDetailList = null;
		for (int i = 0; i < dataLength; i++) {
			OrderDetailVO mine = new OrderDetailVO();
			mine.setOrderDetailID((String) jsonObject.get(Integer.toString(i)));
		//	System.out.println("update data");
			// orderDetailID.add((String) jsonObject.get(Integer.toString(i)));
			orderdetailbiz.modify(mine);
			OrderDetailList = orderdetailbiz.get((String) jsonObject.get(Integer.toString(i)));
			logger.info("OrderDetail Log ," + OrderDetailList.getOrderDetailID() + ", " + OrderDetailList.getHqName()
					+ " " + OrderDetailList.getChainName() + " " + OrderDetailList.getIngPrice());
		}
		/// ���⼭ �ؽ�Ʈ ������ csv ���Ϸ� ��ȯ�Ѵ�.
	}

	/*
	 * @RequestMapping("/insertDataToDB.top") public void
	 * insertDataToDB(HttpServletRequest req) throws Exception{ String json =
	 * req.getParameter("data1"); String sortBy = req.getParameter("loadProds"); int
	 * dataLength = Integer.parseInt(sortBy); ArrayList<String> orderDetailID = new
	 * ArrayList<>();
	 * 
	 * JSONParser parser = new JSONParser(); JSONObject jsonObject = null; try {
	 * jsonObject = (JSONObject) parser.parse(json); } catch (ParseException e1) {
	 * // TODO Auto-generated catch block e1.printStackTrace(); } for(int i = 0 ;i <
	 * dataLength; i++) { orderDetailID.add((String)
	 * jsonObject.get(Integer.toString(i))); biz3.update(orderDetailID.get(i)); } }
	 */
	@RequestMapping("/orderStatusData.top")
	@ResponseBody
	public void orderStatusData(@RequestParam("chainOrHQ") String chainOrHQ, HttpServletResponse res) throws Exception {
		res.setContentType("text/json;charset=UTF-8");
		PrintWriter out = res.getWriter();
		JSONArray ja = new JSONArray();
		JSONObject jo = new JSONObject();
//		HttpSession session = req.getSession();
//		String u_id = (String) session.getAttribute("loginid");
//		System.out.println(u_id);
		ArrayList<OrderDetailVO> list = null;
		OrderDetailVO tmp = new OrderDetailVO();
		tmp.setHqID(chainOrHQ);
		if (chainOrHQ.contains("hq")) {
		//	System.out.println("hq print before");
			list = orderdetailbiz.gethq(chainOrHQ);//
		//	System.out.println("hq print");
		} else {
		//	System.out.println("user print");
			list = orderdetailbiz.getuser(chainOrHQ);//
		}

		for (OrderDetailVO u : list) {
			jo = new JSONObject();
			jo.put("orderDetailID", u.getOrderDetailID());
			jo.put("OrderID", u.getOrderID());
			System.out.println(u.getOrderID());
			jo.put("HqName", u.getHqName());
			jo.put("ChainName", u.getChainName());
			jo.put("UserName", u.getUserName());
			jo.put("IngName", u.getIngName());
			jo.put("IngPrice", u.getIngPrice());
			jo.put("IngUnit", u.getIngUnit());
			jo.put("TotWeight", u.getTotWeight());
			jo.put("ConID", u.getConID());
			System.out.println(u.getConID());
			jo.put("IngQuantity", u.getIngQuantity());
			jo.put("OrderState", u.getOrderState());
			System.out.println("HqName :" + u.getHqName());
			ja.add(jo);
		}
		out.print(ja.toJSONString());
		out.close();
	}

	@RequestMapping("/orderStatus.top")
	public ModelAndView main(ModelAndView mv, HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("text/json;charset=UTF-8");
		String u_id = req.getParameter("id");
		System.out.println("haha my id isdddddddddddddddddddddddddd" + u_id);
		mv.addObject("loginId", u_id);
		mv.addObject("AllChainsVisualAnalysis", "../main/orderStatus");
		mv.setViewName("main/main");
		res.setContentType("text/html; charset=UTF-8");
		return mv;
	}

	@RequestMapping("/orderData.top")
	@ResponseBody
	public void orderData(HttpServletResponse res) throws IOException {
	//	System.out.println("orderData Started");
//		HttpSession session = req.getSession();
//		String u_id = (String) session.getAttribute("loginid");
//		System.out.println(u_id);
		res.setContentType("text/json;charset=UTF-8");
		PrintWriter out = res.getWriter();
		ArrayList<IngredientVO> list = null;
		list = ingbiz.get();//
		JSONArray ja = new JSONArray();
		JSONObject jo = new JSONObject();
		/// select option�� default�� �ֱ� ���ؼ� �Ʒ��� ���� ����
		jo.put("IngName", "����");
		jo.put("IngPrice", 0);
		jo.put("IngUnit", "kg");
		jo.put("IngImgName", "dd");
		ja.add(jo);
		for (IngredientVO u : list) {
			System.out.println(u.getIngName());
			jo = new JSONObject();
			jo.put("IngName", u.getIngName());
			jo.put("IngPrice", u.getIngPrice());
			jo.put("IngUnit", u.getIngUnit());
			jo.put("IngImgName", u.getIngImgName());
			ja.add(jo);
		}
		out.print(ja.toJSONString());
		out.close();
		System.out.println("orderData success");
		return;
	}

	@RequestMapping("/popupOrder.top")
	@ResponseBody
	public ModelAndView popupOrder(ModelAndView mv, HttpServletRequest req) throws Exception {
		String u_id = req.getParameter("id");
		mv.addObject("loginId", u_id);
		mv.setViewName("main/popupOrder");// �˾� ����
		return mv;
	}

	// hyunchu
	@RequestMapping("/fetchOrderData.top")
	public void fetchOrderData(HttpServletRequest req) {
	//	System.out.println("fic");
		HttpSession session = req.getSession();
		String hqID = (String) session.getAttribute("loginId");

	//	System.out.println("hqID : " + hqID);
		OrderVO order_temp = new OrderVO();

		order_temp.setHqID(hqID);

		order_temp.setDeliveryState("ondelivery");
	//	System.out.println(order_temp);
		ArrayList<OrderVO> order_db = orderbiz.getForDelivery(order_temp);
	//	System.out.println("order_db : " + order_db);

//			
		//
//			System.out.println(hqID);
//			ArrayList<OrderDetailVO> ingList = biz3.getUserSpecific(hqID);
//			System.out.println(ingList);
		// send notification to manageApp regardless of the said conditions above
		URL url = null;
		try {
			url = new URL("https://fcm.googleapis.com/fcm/send");
	//		System.out.println("1");
		} catch (MalformedURLException e) {
	//		System.out.println("Error while creating Firebase URL | MalformedURLException");
			e.printStackTrace();
		}
		HttpURLConnection conn = null;
		try {
			conn = (HttpURLConnection) url.openConnection();
	//		System.out.println("2");
		} catch (IOException e) {
	//		System.out.println("Error while createing connection with Firebase URL | IOException");
			e.printStackTrace();
		}
		conn.setUseCaches(false);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestProperty("Content-Type", "application/json");
		// set my firebase server key
		conn.setRequestProperty("Authorization", "key="
				+ "AAAA8_VS6Js:APA91bEz-e849-hlprurjthpy2aw5Su1EJ3eIVp_Sfdja1F9UnjhuWlo7jMaog4uu4QaePwSf2pWT9AizDCrVJ4fIP_d9wcPmhvEn6Ocr7r9vBWoGNTmoj_IYScdpnI_5XaO7AWcJk77");
		// create notification message into JSON format
		JSONObject message = new JSONObject();
//				message.put("to",
//						"cmm9ME4d9Ss:APA91bGxP8xrtRCzEof13dArAAuJKGODYi7uejryVTxkdndEoUxC0NTw2LbNNhUizHS38syfGTmHRBRUzCXj5HLgkQcb2XYeE4eiyGG-kKHSU-OPbSet2AMU_yjv0gQMg0RDLhNy920d");
		message.put("to", "/topics/chainTablet");
		message.put("priority", "high");
		JSONObject notification = new JSONObject();
		notification.put("title", hqID);
		notification.put("body", "fetch");
		message.put("notification", notification);
		JSONArray conListArray = new JSONArray();
	//	System.out.println("3");
		for (OrderVO con : order_db) {
			System.out.println("4");
			JSONObject jo = new JSONObject();
			jo.put("orderID", con.getOrderID());
			jo.put("payment", con.getPayment());
			jo.put("deliveryDate", con.getDeliveryDate());
			jo.put("deliveryState", con.getDeliveryState());
			jo.put("orderPerson", con.getOrderPerson());
			jo.put("orderState", con.getOrderState());
			jo.put("hqID", con.getHqID());
			jo.put("chainID", con.getChainID());
			jo.put("userID", con.getUserID());

	//		System.out.println("5");
			conListArray.add(jo);
		}
		JSONObject list = new JSONObject();
		list.put("list", conListArray);
		message.put("data", list);
	//	System.out.println("6");
		// send data to firebase (http method)
		try {
			OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			out.write(message.toString());
			out.flush();
			conn.getInputStream();
		} catch (IOException e) {
			System.out.println("Error while writing outputstream to firebase sending to ManageApp | IOException");
			e.printStackTrace();
		}
	}

}