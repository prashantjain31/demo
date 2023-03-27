package com.example.demo.converter;

import java.util.stream.*;

import com.example.demo.helpers.Role;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<Role, String> {

	@Override
	public String convertToDatabaseColumn(Role role) {
		if (role == null) {
            return null;
        }
        return role.getRole();
	}

	@Override
	public Role convertToEntityAttribute(String role) {
		if (role == null) {
            return null;
        }

        return Stream.of(Role.values())
          .filter(r -> r.getRole().equals(role))
          .findFirst()
          .orElseThrow(IllegalArgumentException::new);
	}

}
