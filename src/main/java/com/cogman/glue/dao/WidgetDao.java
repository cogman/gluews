package com.cogman.glue.dao;

import autovalue.shaded.com.google.common.common.collect.Iterators;
import com.cogman.glue.model.Widget;
import com.google.common.collect.ImmutableSet;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

@Singleton
public class WidgetDao
{
	private final JdbcTemplate jdbcTemplate;
	private final RowMapper<Widget> widgetMapper = (rs, rowNum) ->
		{
			return Widget.builder()
				.id(rs.getInt("id"))
				.name(rs.getString("name"))
				.cat(rs.getString("cat"))
				.build();
		};

	@Inject WidgetDao(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * Grabs and individual widgets
	 *
	 * @param id - widget id
	 * @return Widget of the id or null if not found or deleted
	 */
	@Nullable
	public Widget getWidget(int id)
	{
		Set<Widget> widgets = getWidgets(Collections.singleton(id));
		if (widgets.isEmpty())
			return null;
		else
			return Iterators.getOnlyElement(widgets.iterator());
	}

	/**
	 * Loads a Widgets up from the database based on the Database ID
	 *
	 * @param widgetIds - Ids of the widgets to load. Should not contain nulls
	 * @return Immutable Set of the Widgets
	 */
	@Nonnull
	public Set<Widget> getWidgets(Collection<Integer> widgetIds)
	{
		// This will probably not work, I'm just being lazy and giving an example
		return ImmutableSet.copyOf(jdbcTemplate.query("SELECT id, name, cat FROM Widget where widgetId IN (?) AND deleted=0", widgetMapper, widgetIds));
	}

	/**
	 * Deletes a widget based on the ID
	 *
	 * @param widgetId
	 * @return true if the widget was deleted, false if it has already been deleted or does not exist
	 */
	public boolean deleteWidget(int widgetId)
	{
		int rowsEdited = jdbcTemplate.update("UPDATE Widget SET deleted=1 WHERE widgetId=? AND deleted=0", widgetId);
		return rowsEdited == 1;
	}

	/**
	 * Updates the widget based on the incoming widget.
	 *
	 * @param widget - Must contain the id of the widget to update
	 * @param widgetId - Id of the widget being updated
	 * @return true if the widget was updated
	 */
	public boolean updateWidget(Widget widget, int widgetId)
	{
		int rowsUpdated = jdbcTemplate.update("UPDATE Widget SET name=?, cat=? WHERE widgetId=?", widget.name(), widget.cat(), widgetId);
		return rowsUpdated == 1;
	}

	/**
	 * Creates a widget based on the input widget
	 *
	 * @param widget - Should have a negative id
	 */
	public void createWidget(Widget widget)
	{
		jdbcTemplate.update("INSERT INTO Widget VALUES (?, ?)", widget.name(), widget.cat());
	}
}
