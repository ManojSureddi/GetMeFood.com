/**
 * @author Siva_Varma
 * $Id: $
 * $Copyright: $
 */
package com.gmf.ws.dao;

/**
 * The DalExceptionTypes enum is a dictionary of all the Kinds of exceptions
 * both user defined and library exceptions. Each enum value denotes exception code and description.
 * 
 */
public enum DAOExceptionTypes {
	DAO_CONCLOSE("DAO_001", "Closing Connection"),
	DAO_SQLEX("DAO_002", "Unknown SQL Exception");
	private String code;
	private String description;

	private DAOExceptionTypes(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return code + ":" + description;
	}
}