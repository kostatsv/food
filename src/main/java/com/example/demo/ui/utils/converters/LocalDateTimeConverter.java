package com.example.demo.ui.utils.converters;

import static com.example.demo.ui.utils.FormattingUtils.FULL_DATE_FORMATTER;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.vaadin.flow.templatemodel.ModelEncoder;

import javax.persistence.AttributeConverter;

public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

  @Override
  public Timestamp convertToDatabaseColumn(LocalDateTime localDateTime) {
    return localDateTime != null ? Timestamp.valueOf(localDateTime) : null;
  }

  @Override
  public LocalDateTime convertToEntityAttribute(Timestamp timestamp) {
    return timestamp != null ? timestamp.toLocalDateTime() : null;
  }
}
