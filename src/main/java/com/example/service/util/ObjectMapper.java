package com.example.service.util;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;

public class ObjectMapper {

	public static <T, U> List<U> map(final Mapper mapper, final List<T> source, final Class<U> destType) {
        final List<U> dest = new ArrayList<U>();
        for (T element : source) {
            dest.add(mapper.map(element, destType));
        }
        return dest;
    }

    public static <T, U> U  mapObject(final Mapper mapper, final T source, final Class<U> destType) {
        return mapper.map(source, destType);
    }
    
}
