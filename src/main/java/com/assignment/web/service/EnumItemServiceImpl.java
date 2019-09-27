package com.assignment.web.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.web.dto.CommonDTO;
import com.assignment.web.dto.enums.EnumItemDTO;
import com.assignment.web.repository.EnumItemRepository;

@Service
public class EnumItemServiceImpl implements EnumItemService {

	@Autowired
	private EnumItemRepository enumItemRepository;

	@Override
	public List<CommonDTO> getEnumItem(EnumItemDTO type) {
		return enumItemRepository.findByEnumType(type.toString()).stream()
				.map(enumType -> new CommonDTO(enumType.getId(), enumType.getEnamItemName()))
				.collect(Collectors.toList());
	}

}
