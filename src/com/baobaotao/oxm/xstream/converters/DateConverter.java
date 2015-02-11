package com.baobaotao.oxm.xstream.converters;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class DateConverter implements Converter {

	private Locale locale;

	public DateConverter(Locale locale) {
		super();
		this.locale = locale;
	}

	/**
	 * 实现该方法，判断要转换的类型
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public boolean canConvert(Class clazz) {
		return Date.class.isAssignableFrom(clazz);
	}

	/**
	 * 实现该方法，编写JAVA对象到XML转换逻辑
	 */
	@Override
	public void marshal(Object paramObject, HierarchicalStreamWriter writer,
			MarshallingContext value) {
		DateFormat formatter = DateFormat.getDateInstance(DateFormat.FULL,
				this.locale);
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		writer.setValue(formatter.format(value));
	}

	/**
	 * 实现该方法，编写XML到JAVA对象转换逻辑
	 */
	@Override
	public Object unmarshal(HierarchicalStreamReader reader,
			UnmarshallingContext context) {
		GregorianCalendar calendar = new GregorianCalendar();
		DateFormat formatter = DateFormat.getDateInstance(DateFormat.FULL,
				this.locale);
		try {
			calendar.setTime(formatter.parse(reader.getValue()));
		} catch (ParseException e) {
			throw new ConversionException(e.getMessage(), e);
		}
		return calendar.getGregorianChange();
	}

}
