package com.homeAutomation.resource;

import javax.annotation.security.PermitAll;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.hibernate.engine.spi.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.homeAutomation.model.User;
import com.homeAutomation.service.UserService;

@Path("/users")
@Component
public class UserResource {
	@Autowired
	private UserService userService;
	
	@Context
    protected UriInfo uriInfo;
	
	@PermitAll
	@Path("{userId}")
    @GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam("userId") long userId) {
		return Response.ok(userService.findById(userId)).build();
	}
	
	@PermitAll
    @POST
	public Response create(User user) {
		userService.save(user);		
		return Response.created(uriInfo.getAbsolutePath()).build();
	}
	
	@PermitAll
	@Path("{userId}")
    @PUT
	public Response update(@PathParam("userId") long userId, User user) {
		userService.update(userId, user);
		return Response.ok().build();
	}
	
	@PermitAll
    @Path("{userId}")
    @DELETE
	public Response delete(@PathParam("userId") long userId) {	
		userService.delete(userId);
		return Response.ok(Status.DELETED).build();
	}

	@PermitAll
	@Path("listdata")
    @GET
    public Response findAll(){
		return Response.ok(userService.findAll()).build();
	}

}
