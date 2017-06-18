package com.gmf.ws.resources;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import com.gmf.ws.dao.CheckOutDAO;
import com.gmf.ws.dao.GetItemDataDAO;
import com.gmf.ws.dao.OrderConfirmationDAO;
import com.gmf.ws.helper.OrdersBean;
import com.gmf.ws.helper.UniqueKeyGenerator;
import com.sun.jmx.snmp.Timestamp;

@Path("/order")
public class PlaceOrder {
	@Path("/place")
	public String placeOrder(@FormParam("address") String address,
			@FormParam("des") String des, @Context HttpServletRequest req) {
		HashMap<String, HashMap<String, String>> cartContains = null;
		HttpSession session = req.getSession(true);
		Object userid = session.getAttribute("userid");
		try {
			cartContains = (HashMap<String, HashMap<String, String>>) session
					.getAttribute("cart");
			String restId = "";
			for (String keys : cartContains.keySet()) {
				HashMap<String, String> itemname = cartContains.get(keys);
				restId = itemname.get("restId");
			}

			CheckOutDAO data = new CheckOutDAO();
			HashMap<String, String> details = data.getFullDetails(restId);
			OrdersBean bean = new OrdersBean();
			bean.setOrderId(UniqueKeyGenerator.generateKey(userid.toString()
					+ address, restId + details.get("areacode")));
			bean.setOrderTime((new Timestamp((new Date()).getTime()))
					.getDate().toString());
			bean.setAreaCode(details.get("areacode"));
			bean.setAreaName(details.get("areaname"));
			bean.setCityCode(details.get("areacode"));
			if ("1".equals(des)) {
				bean.setDeliveryAddr(address);
			} else {
				Object addres=session.getAttribute("addr");
				bean.setDeliveryAddr(addres.toString());
			}
			bean.setCustId(userid.toString());
			bean.setRestId(restId);
			bean.setRestName(details.get("restname"));
			bean.setDeliveryAddr(address);
			bean.setStatus("INPROCESS");
			OrderConfirmationDAO dao = new OrderConfirmationDAO();
			String orderid = dao.storeOrder(bean, cartContains, des);
			session.setAttribute("Ordercon", orderid);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("sdfsd" + e.getMessage());
		}

		return "";
	}
}
