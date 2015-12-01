package com.cogman.glue.resource;

import com.cogman.glue.dao.WidgetDao;
import com.cogman.glue.model.Widget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.junit.Assert;
import org.junit.Test;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WidgetResourceTest
{
	private static final Widget DUMMY_WIDGET = Widget.builder().id(1).name("bob").cat("jones").build();

	@Test
	public void testGetWidget()
	{
		WidgetDao widgetDao = mock(WidgetDao.class);
		when(widgetDao.getWidget(1)).thenReturn(DUMMY_WIDGET);
		WidgetResource widgetResource = new WidgetResource(widgetDao);
		Response widgetResponse = widgetResource.getWidget(1);
		Assert.assertEquals(Status.OK.getStatusCode(), widgetResponse.getStatus());
		Assert.assertEquals(DUMMY_WIDGET, widgetResponse.getEntity());
	}

	@Test
	public void testGetWidget_missing()
	{
		WidgetDao widgetDao = mock(WidgetDao.class);
		WidgetResource widgetResource = new WidgetResource(widgetDao);
		Response widgetResponse = widgetResource.getWidget(1);
		Assert.assertEquals(Status.NOT_FOUND.getStatusCode(), widgetResponse.getStatus());
		Assert.assertEquals(null, widgetResponse.getEntity());
	}

	@Test
	public void testCreateWidget()
	{
		WidgetDao widgetDao = mock(WidgetDao.class);
		WidgetResource widgetResource = new WidgetResource(widgetDao);
		Response widgetResponse = widgetResource.createWidget(DUMMY_WIDGET);
		Assert.assertEquals(Status.CREATED.getStatusCode(), widgetResponse.getStatus());
	}

	@Test
	public void testUpdateWidget()
	{
		WidgetDao widgetDao = mock(WidgetDao.class);
		when(widgetDao.updateWidget(anyObject(), anyInt())).thenReturn(true);
		WidgetResource widgetResource = new WidgetResource(widgetDao);
		Response widgetResponse = widgetResource.updateWidget(DUMMY_WIDGET, 1);
		Assert.assertEquals(Status.NO_CONTENT.getStatusCode(), widgetResponse.getStatus());
	}

	@Test
	public void testUpdateWidget_noUpdate()
	{
		WidgetDao widgetDao = mock(WidgetDao.class);
		when(widgetDao.updateWidget(anyObject(), anyInt())).thenReturn(false);
		WidgetResource widgetResource = new WidgetResource(widgetDao);
		Response widgetResponse = widgetResource.updateWidget(DUMMY_WIDGET, 1);
		Assert.assertEquals(Status.NOT_MODIFIED.getStatusCode(), widgetResponse.getStatus());
	}

	@Test
	public void testDeleteWidget()
	{
		WidgetDao widgetDao = mock(WidgetDao.class);
		when(widgetDao.deleteWidget(anyInt())).thenReturn(true);
		WidgetResource widgetResource = new WidgetResource(widgetDao);
		Response widgetResponse = widgetResource.deleteWidget(1);
		Assert.assertEquals(Status.NO_CONTENT.getStatusCode(), widgetResponse.getStatus());
	}

	@Test
	public void testDeleteWidget_noDelete()
	{
		WidgetDao widgetDao = mock(WidgetDao.class);
		when(widgetDao.deleteWidget(anyInt())).thenReturn(false);
		WidgetResource widgetResource = new WidgetResource(widgetDao);
		Response widgetResponse = widgetResource.deleteWidget(1);
		Assert.assertEquals(Status.NOT_MODIFIED.getStatusCode(), widgetResponse.getStatus());
	}

}
