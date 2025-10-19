package com.felfel.springdatarestjopapp.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.List;

@Converter
/**
 * JPA AttributeConverter that serializes a List<String> to JSON for storage in a
 * single TEXT column and deserializes JSON text back into a List<String>.
 *
 * <p>This converter uses Jackson's ObjectMapper. convertToDatabaseColumn returns
 * null when the attribute is null. convertToEntityAttribute returns an empty list
 * when the database value is null or cannot be parsed to avoid propagation of
 * parsing exceptions into repository reads.</p>
 */
public class StringListConverter implements AttributeConverter<List<String>, String> {
    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * Convert a List<String> attribute to its JSON representation for DB storage.
     *
     * @param attribute the List<String> to convert; may be null
     * @return JSON string or null when attribute is null
     * @throws IllegalArgumentException when serialization fails
     */
    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        if (attribute == null) return null;
        try {
            return mapper.writeValueAsString(attribute);
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not convert list to JSON", e);
        }
    }

    /**
     * Convert a JSON string from the DB into a List<String>.
     *
     * @param dbData JSON text stored in the column; may be null
     * @return parsed list or an empty list on null or parse error
     */
    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        if (dbData == null) return List.of();
        try {
            return mapper.readValue(dbData, mapper.getTypeFactory().constructCollectionType(List.class, String.class));
        } catch (Exception e) {
            // on parse error return empty list to avoid deserialization exceptions at runtime
            return List.of();
        }
    }
}
