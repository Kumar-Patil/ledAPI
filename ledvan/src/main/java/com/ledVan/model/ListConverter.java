/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ledVan.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.persistence.AttributeConverter;

/**
 *
 * @author santopat
 */
public class ListConverter implements AttributeConverter<List<Display>, String> {

    @Override
    public String convertToDatabaseColumn(List<Display> arg0) {
        ObjectMapper objectMapper = new ObjectMapper();
        String display = null;
        try {
            display = objectMapper.writeValueAsString(arg0);
        } catch (final JsonProcessingException e) {
            e.printStackTrace();
        }
        return display;
    }

    @Override
    public List<Display> convertToEntityAttribute(String dispaly) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Display> displays = null;
        try {
            displays = Arrays.asList(objectMapper.readValue(dispaly, Display[].class));
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return displays;
    }

}
