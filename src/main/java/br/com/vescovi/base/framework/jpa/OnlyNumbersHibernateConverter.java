package br.com.vescovi.base.framework.jpa;

import br.com.vescovi.base.util.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class OnlyNumbersHibernateConverter  implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String field) {
        return StringUtils.onlyNumbers(field);
    }

    @Override
    public String convertToEntityAttribute(String field) {
        return field;
    }

}

