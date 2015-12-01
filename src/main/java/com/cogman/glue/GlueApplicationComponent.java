package com.cogman.glue;

import com.cogman.glue.module.DataSourceModule;
import com.cogman.glue.module.JsonModule;
import com.cogman.glue.resource.WidgetResource;
import dagger.Component;
import javax.inject.Singleton;

@Component(modules = {DataSourceModule.class, JsonModule.class})
@Singleton
public interface GlueApplicationComponent
{
	WidgetResource getWidgetResource();
	JerseyObjectMapperProvider getJerseyOjbectMapperProvider();
}
