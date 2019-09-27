package com.assignment.web.service;

import java.util.List;

import com.assignment.web.dto.CommonDTO;
import com.assignment.web.dto.enums.EnumItemDTO;
/**
 * 
 * EnumItem Service
 *
 */
public interface EnumItemService {
	/**
	 * Get EnumItem By EnumCode
	 * @param id
	 * @return
	 */
	List<CommonDTO> getEnumItem(EnumItemDTO id);

}
