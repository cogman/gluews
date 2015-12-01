package com.cogman.glue.module;

import com.fasterxml.jackson.databind.ObjectMapper;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class JsonModule
{
	@Provides @Singleton ObjectMapper provideObjectMapper()
	{
		return new ObjectMapper();
	}
}
