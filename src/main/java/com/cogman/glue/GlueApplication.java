package com.cogman.glue;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class GlueApplication extends ResourceConfig
{
	public GlueApplication()
	{
		GlueApplicationComponent appComponent = DaggerGlueApplicationComponent.create();
		register(appComponent.getWidgetResource());
		register(appComponent.getJerseyOjbectMapperProvider());
		register(JacksonFeature.class);
	}
}
