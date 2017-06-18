package com.gmf.ws.resources;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.gmf.ws.dao.GetItemDataDAO;
import com.gmf.ws.utils.GMFUtility;

@Path("/cart")
public class AddToCart {
	@GET
	@Path("/add/{restID}/{itemID}/{qty}")
	public String addToCArt(@Context HttpServletRequest req,
			@PathParam("restID") String restID,
			@PathParam("itemID") String itemID, @PathParam("qty") int quantity) {
		GMFUtility gmfUtility = new GMFUtility();
		ArrayList<HashMap<String, HashMap<String, String>>> jsonString = new ArrayList<>();

		HashMap<String, HashMap<String, String>> cartContains = null;
		HttpSession session = req.getSession(true);
		GetItemDataDAO getDetails = new GetItemDataDAO();
		try {
			cartContains = (HashMap<String, HashMap<String, String>>) session
					.getAttribute("cart");

			if (cartContains != null) {
				if (cartContains.keySet().contains(itemID)) {
					HashMap<String, String> tempz = cartContains.get(itemID);
					int temp = Integer.parseInt(tempz.get("itemQty")) + 1;
					tempz.put("itemQty", (temp) + "");
					cartContains.put(itemID, tempz);
					session.setAttribute("cart", cartContains);
				} else {
					cartContains.put(
							itemID,
							getDetails.getItemData(itemID, restID, quantity
									+ ""));
				}

				session.setAttribute("cart", cartContains);
			} else {
				cartContains = new HashMap<String, HashMap<String, String>>();
				cartContains.put(itemID,
						getDetails.getItemData(itemID, restID, quantity + ""));
				session.setAttribute("cart", cartContains);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		HashMap<String, ArrayList<HashMap<String, HashMap<String, String>>>> cartitems = new HashMap<>();
		jsonString.add(cartContains);
		cartitems.put("cartItems", jsonString);
		return gmfUtility.convertToJSON(cartitems);
	}

	@POST
	@Path("del")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteItem(@Context HttpServletRequest req,
			@FormParam("itemID") String itemID) {
		HashMap<String, HashMap<String, String>> cartContains;
		HttpSession session = req.getSession(true);
		cartContains = (HashMap<String, HashMap<String, String>>) session
				.getAttribute("cart");
		cartContains.remove(itemID);
		session.setAttribute("cart", cartContains);
		return cartContains.toString();
	}

	@POST
	@Path("increaseqty")
	@Produces(MediaType.APPLICATION_JSON)
	public String increaseQty(@Context HttpServletRequest req,
			@FormParam("itemID") String itemID) {
		ArrayList<HashMap<String, String>> cartContains;
		HashMap<String, Integer> cartContent;
		HttpSession session = req.getSession(true);
		cartContains = (ArrayList<HashMap<String, String>>) session
				.getAttribute("cart");
		for (HashMap<String, String> cartz : cartContains) {
			if (cartz.get("itemCode") == itemID) {
				int temp = Integer.parseInt(cartz.get("itemQty"));
				cartz.put("itemQty", (temp++) + "");
			}
		}
		session.setAttribute("cart", cartContains);
		return cartContains.toString();
	}

	@POST
	@Path("decreaseqty")
	@Produces(MediaType.APPLICATION_JSON)
	public String decreaseQty(@Context HttpServletRequest req,
			@FormParam("itemID") String itemID) {
		HashMap<String, HashMap<String, String>> cartContains;
		HttpSession session = req.getSession(true);
		cartContains = (HashMap<String, HashMap<String, String>>) session
				.getAttribute("cart");
		HashMap<String, String> tempz = cartContains.get(itemID);
		int temp = Integer.parseInt(tempz.get("itemQty")) - 1;
		tempz.put("itemQty", (temp) + "");
		cartContains.put(itemID, tempz);
		session.setAttribute("cart", cartContains);

		return cartContains.toString();
	}
}