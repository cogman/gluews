package com.cogman.glue.serialization;

import com.cogman.glue.model.Widget;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;

public class WidgetSerializationTest
{
	@Test
	public void test_SerializationAndDeserialization() throws IOException
	{
		ObjectMapper mapper = new ObjectMapper();
		Widget widget = Widget.builder().id(1).name("bob").cat("jones").build();
		String widgetJson = mapper.writeValueAsString(widget);
		Widget deserializedWidget = mapper.readValue(widgetJson, Widget.class);
		Assert.assertEquals(widget, deserializedWidget);
	}
}
