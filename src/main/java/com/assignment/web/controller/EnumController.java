package com.assignment.web.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.web.dto.ResponseJson;
import com.assignment.web.dto.enums.EnumItemDTO;
import com.assignment.web.service.EnumItemService;
import com.assignment.web.utils.CommonConstant;
/**
 * 
 * EnumItem Master Data
 *
 */
@RestController
@RequestMapping(CommonConstant.AUTH_URL)
public class EnumController {

	@Autowired
	private EnumItemService enumItemService;

	@Autowired
	private ResponseJson response;

	@Resource(name = CommonConstant.COMMON_MAP_OBJECT)
	private Map<String, Object> model;
	/**
	 * Get EnumItem By EnumCode
	 * @param id
	 * @return
	 */
	@PostMapping(CommonConstant.COMMON_ID)
	public ResponseJson getEnumItem(@PathVariable EnumItemDTO id) {
		response.setResponseDescription(CommonConstant.S0002_SUCCESS_DESCRIPTION);
		model.put(CommonConstant.RESULTS, enumItemService.getEnumItem(id));
		response.setResponse(model);
		return response;
	}


}
