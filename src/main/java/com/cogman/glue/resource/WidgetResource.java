package com.cogman.glue.resource;

import com.cogman.glue.dao.WidgetDao;
import com.cogman.glue.model.Widget;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/widget")
@Produces(MediaType.APPLICATION_JSON)
@Singleton
public class WidgetResource
{
	private final WidgetDao widgetDao;

	@Inject WidgetResource(WidgetDao widgetDao)
	{
		this.widgetDao = widgetDao;
	}

	/**
	 * Gets a widget based on the ID
	 *
	 * @param widgetId
	 * @return
	 */
	@GET
	@Path("/{id}")
	public Response getWidget(@PathParam("id") int widgetId)
	{
		Widget widget = widgetDao.getWidget(widgetId);
		if (widget == null)
			return Response.status(Status.NOT_FOUND).build();
		else
			return Response.ok(widget).build();
	}

	/**
	 * Creates a widget based on the input widget. Widget should have a negative id
	 *
	 * @param widget
	 * @return Always returns 201 (created) if the widget is made.
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createWidget(Widget widget)
	{
		widgetDao.createWidget(widget);
		return Response.status(Status.CREATED).build();
	}

	/**
	 * Updates a widget based on the incoming JSON blob
	 *
	 * @param widget all the widget data
	 * @param widgetId Id of the widget being update
	 * @return NO_CONTENT (204) if there was an update, NOT_MODIFIED(304) if there were changes made.
	 */
	@POST
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateWidget(Widget widget, @PathParam("id") int widgetId)
	{
		boolean wasUpdated = widgetDao.updateWidget(widget, widgetId);
		if (wasUpdated)
			return Response.status(Status.NO_CONTENT).build();
		else
			return Response.status(Status.NOT_MODIFIED).build();
	}

	/**
	 * Deletes a widget based on the widget's ID
	 *
	 * @param widgetId
	 * @return NO_CONTENT (204) if deleted, NOT_MODIFIED(304) if already deleted/does not exist
	 */
	@DELETE
	@Path("/{id}")
	public Response deleteWidget(@PathParam("id") int widgetId)
	{
		boolean wasDeleted = widgetDao.deleteWidget(widgetId);
		if (wasDeleted)
			return Response.status(Status.NO_CONTENT).build();
		else
			return Response.status(Status.NOT_MODIFIED).build();
	}
}
