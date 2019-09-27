package com.assignment.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.assignment.web.model.EnumItem;
import com.assignment.web.utils.CommonConstant;

/**
 * 
 * EnumItem Repository
 *
 */
@Repository
public interface EnumItemRepository  extends JpaRepository<EnumItem, Long> {
	/**
	 * Find By EnumType
	 * @param enumType
	 * @return
	 */
	@Query("select en from EnumItem en where en.enumItemCode =:param1")
	List<EnumItem> findByEnumType(@Param(CommonConstant.PARAM1) String enumType);

}
