package si.zpiz.sample.domain.persistence;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class GrantedAuthorityImplListConverter implements AttributeConverter<List<GrantedAuthorityImpl>, String> {

    @Override
    public String convertToDatabaseColumn(List<GrantedAuthorityImpl> instance) {
        if (instance == null) {
            return null;
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(instance);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error converting object to JSON string", e);
        }
    }

    @Override
    public List<GrantedAuthorityImpl> convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }

        ObjectMapper mapper = new ObjectMapper();

        @SuppressWarnings("unused")
        List<GrantedAuthorityImpl> result = new ArrayList<>();

        try {
            return mapper.readValue(dbData,
                    mapper.getTypeFactory().constructCollectionType(List.class, GrantedAuthorityImpl.class));
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error converting JSON string to object", e);
        }
    }
}