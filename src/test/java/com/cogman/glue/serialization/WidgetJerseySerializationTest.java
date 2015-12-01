package com.cogman.glue.serialization;

import com.cogman.glue.model.Widget;
import java.io.UnsupportedEncodingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;

public class WidgetJerseySerializationTest extends JerseyTest
{
	@Path("widget")
	@Produces(MediaType.APPLICATION_JSON)
	public static class Resource
	{
		@PUT
		@Consumes(MediaType.APPLICATION_JSON)
		public Response get(Widget widget)
		{
			return Response.ok(widget).build();
		}
	}

	@Override
	protected Application configure()
	{
		ResourceConfig resourceConfig = new ResourceConfig(Resource.class);

		resourceConfig.register(JacksonFeature.class);

		return resourceConfig;
	}

	@Override
	protected void configureClient(ClientConfig clientConfig)
	{
		clientConfig.register(JacksonFeature.class);
	}

	@Test
	public void test_WidgetSerialization() throws UnsupportedEncodingException
	{
		Widget widget = target("widget")
			.request()
			.put(Entity.entity("{\"id\":1,\"name\":\"bob\",\"cat\":\"jones\"}", MediaType.APPLICATION_JSON), Widget.class);
		Assert.assertEquals(Widget.builder().id(1).name("bob").cat("jones").build(), widget);
	}
}
