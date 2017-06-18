package com.gmf.ws.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gmf.ws.bl.AreasBL;
import com.gmf.ws.dao.DAOException;
import com.gmf.ws.exceptions.GMFException;
import com.gmf.ws.exceptions.GMFExceptionTypes;

@Path("/areas")
public class AreasResource {
	private Logger logger = LoggerFactory.getLogger(AreasResource.class
			.getCanonicalName());

	@GET
	@Path("{cityName}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAreasOfCity(@PathParam("cityName") String cityName) {
		logger.debug("In Areas Resource");
		String areas = null;
		AreasBL areasBL = null;
		areasBL = new AreasBL();
		try {
			areas = areasBL.getAreasOfCity(cityName);
		} catch (GMFException gmfEx) {
			throw gmfEx;
		} catch (DAOException daoEx) {
			throw new GMFException(GMFExceptionTypes.GMF_DAOEXCE);
		} catch (Exception ex) {
			logger.error("Unexpected Exception araised.");
			throw new GMFException(GMFExceptionTypes.GMF_UNKNOWN);
		} finally {
			logger.debug("Areas resource completed.");
		}
		return areas;
	}
}
