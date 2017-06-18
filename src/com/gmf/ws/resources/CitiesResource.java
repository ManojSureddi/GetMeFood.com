package com.gmf.ws.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gmf.ws.bl.CitiesBL;
import com.gmf.ws.dao.DAOException;
import com.gmf.ws.exceptions.GMFException;
import com.gmf.ws.exceptions.GMFExceptionTypes;

@Path("/cities")
public class CitiesResource {
	private Logger logger = LoggerFactory.getLogger(CitiesResource.class
			.getCanonicalName());

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getCities() {
		logger.debug("Cities resource requested");
		String cities = null;
		CitiesBL areasBL = new CitiesBL();
		try {
			cities = areasBL.getCities();
		} catch (GMFException gmfEx) {
			throw gmfEx;
		} catch (DAOException daoEx) {
			throw new GMFException(GMFExceptionTypes.GMF_DAOEXCE);
		} catch (Exception ex) {
			logger.error("Unexpected Exception araised.");
			throw new GMFException(GMFExceptionTypes.GMF_UNKNOWN);
		} finally {
			logger.debug("Cities resource completed.");
		}
		return cities;
	}
}
