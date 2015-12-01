package com.cogman.glue;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.ext.ContextResolver;

@Singleton
public class JerseyObjectMapperProvider implements ContextResolver<ObjectMapper>
{
	private final ObjectMapper objectMapper;
	
	@Inject JerseyObjectMapperProvider(ObjectMapper objectMapper)
	{
		this.objectMapper = objectMapper;
	}

	@Override
	public ObjectMapper getContext(Class<?> type)
	{
		return objectMapper;
	}
}
