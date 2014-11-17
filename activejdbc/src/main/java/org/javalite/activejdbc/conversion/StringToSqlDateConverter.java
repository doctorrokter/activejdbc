/*
Copyright 2009-2014 Igor Polevoy

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package org.javalite.activejdbc.conversion;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class StringToSqlDateConverter implements Converter<String, java.sql.Date> {

    private final DateFormat format;

    public StringToSqlDateConverter(String pattern) {
        this(new SimpleDateFormat(pattern));
    }
    public StringToSqlDateConverter(DateFormat format) {
        this.format = format;
    }

    @Override
    public boolean canConvert(Class sourceClass, Class destinationClass) {
        return String.class.equals(sourceClass) && java.sql.Date.class.equals(destinationClass);
    }

    @Override
    public java.sql.Date convert(String source) {
        try {
            return new java.sql.Date(format.parse(source).getTime());
        } catch (ParseException e) {
            throw new ConversionException(e);
        }
    }
}