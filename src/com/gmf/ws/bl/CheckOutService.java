package com.gmf.ws.bl;

import java.util.Date;

import com.gmf.ws.dao.CheckOutDAO;
import com.gmf.ws.helper.OrdersBean;
import com.gmf.ws.helper.UniqueKeyGenerator;
import com.sun.jmx.snmp.Timestamp;

public class CheckOutService {
	public String placeOrder(OrdersBean bean) {
		String id ="O"+ UniqueKeyGenerator.generateKey(bean.getAreaCode(),
				bean.getDeliveryAddr());
		bean.setOrderId(id);
		Date date= new Date();
		bean.setOrderTime((new Timestamp(date.getTime())).toString());
		CheckOutDAO dao= new CheckOutDAO();
		
		return "";
	}
}
