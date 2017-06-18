/**
 * @author Siva_Varma
 * $Id: $
 * $Copyright: $
 */
package com.gmf.ws.exceptions;

/**
 * The DalExceptionTypes enum is a dictionary of all the Kinds of exceptions
 * both user defined and library exceptions. Each enum value denotes exception code and description.
 * 
 */
public enum GMFExceptionTypes {
	IC("GMF-004", "Invalid Credentials"), IP("GMF-017", "Invalid parameters"), SQLE(
			"GMF-016", "SQL Exception"), RE("GMF-011", "Remote Exception"), NE(
			"GMF-014", "Naming Exception"), PE("GMF-015", "Parse Exception"), ES(
			"GMF-012", "Error Schema"), SUE("GMF-005",
			"Service Unavailable Exception"), UA("GMF-013",
			"Unauthorized Access"), IOE("GMF-007", "IO Exception"), IAE(
			"GMF-008", "Illegal Access Exception"), IARE("GMF-010",
			"Illegal Argument Exception"), IE("GMF-009,",
			"Instantiation Exception"), GMF_UNKNOWN("GMF_001", "Unknown Exception"), GMF_DAOEXCE("GMF_002","DAO Exception"),CNFE(
			"GMF-001", "Class Not Found Exception"), RNF("GMF-018",
			"Resource Not Found"), WAE("GMF-020", "Web Application Exception");
	private String code;
	private String description;

	private GMFExceptionTypes(String code, String description) {
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