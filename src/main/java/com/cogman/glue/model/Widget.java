package com.cogman.glue.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.xml.bind.annotation.XmlRootElement;

@AutoValue
@JsonDeserialize(builder = AutoValue_Widget.Builder.class)
@Immutable
@XmlRootElement
public abstract class Widget
{
	/**
	 * Id of the widget.  Null if the widget hasn't been created or is being updated
	 * @return
	 */
	@JsonProperty("id") @Nullable public abstract Integer id();
	@JsonProperty("name") @Nonnull public abstract String name();
	@JsonProperty("cat") @Nullable public abstract String cat();

	public static Builder builder()
	{
		return new AutoValue_Widget.Builder();
	}

	public static Builder builder(Widget source)
	{
		return new AutoValue_Widget.Builder(source);
	}

	@AutoValue.Builder
	public static abstract class Builder
	{
		@JsonProperty("id") public abstract Builder id(@Nullable Integer id);
		@JsonProperty("name") public abstract Builder name(@Nonnull String name);
		@JsonProperty("cat") public abstract Builder cat(@Nullable String cat);
		public abstract Widget build();
	}
}
