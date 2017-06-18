package com.gmf.ws.dao;

public class DBQueries {
	public final static String GET_CITIES = "Select distinct CITY_NAME from areas order by CITY_NAME";
	public final static String GET_AREAS_OF_CITY ="Select AREA_CODE, AREA_NAME from areas where CITY_NAME = ? order by AREA_NAME";
	public final static String GET_ITEM_TYPES_IN_RESTAURANT ="Select distinct `Item_Type` FROM `restaurant_menu` WHERE `Restaurant_Id`=?";
	public final static String VALIDATE_USER="Select count(*) from users where User_Id = ? and Password = ? ";
}
