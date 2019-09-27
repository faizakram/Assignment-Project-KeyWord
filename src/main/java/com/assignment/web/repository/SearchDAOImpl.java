package com.assignment.web.repository;

import java.text.ParseException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.assignment.web.dto.SearchDTO;
import com.assignment.web.model.Publication;
import com.assignment.web.utils.CommonConstant;
import com.assignment.web.utils.DateUtils;

/**
 * 
 * Search DAOImpl
 *
 */
@Repository
public class SearchDAOImpl implements SearchDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings(CommonConstant.UNCHECKED)
	@Override
	public List<Publication> searchPublications(SearchDTO searchDTO) {

		Query query = entityManager.createQuery(queryBuilder(searchDTO));

		if (searchDTO.getBookEnumId() != null) {
			query.setParameter(CommonConstant.PARAM1, searchDTO.getBookEnumId());
		}
		if (searchDTO.getMagazineEnumId() != null) {
			query.setParameter(CommonConstant.PARAM2, searchDTO.getMagazineEnumId());
		}
		if (searchDTO.getPublicationEnumId() != null) {
			query.setParameter(CommonConstant.PARAM3, searchDTO.getPublicationEnumId());
		}
		if (!StringUtils.isEmpty(searchDTO.getAuthorName())) {
			query.setParameter(CommonConstant.PARAM4,
					CommonConstant.PERCENT_SYMBOLE + searchDTO.getAuthorName() + CommonConstant.PERCENT_SYMBOLE);
		} else if (searchDTO.getAuthorId() != null) {
			query.setParameter(CommonConstant.PARAM4, searchDTO.getAuthorId());
		}
		if (!StringUtils.isEmpty(searchDTO.getYear())) {
			try {
				query.setParameter(CommonConstant.PARAM5,
						DateUtils.convertStringToDate(searchDTO.getYear(), CommonConstant.DATE_FORMAT_YYYY));
			} catch (ParseException e) {
				throw new NullPointerException("Date Parse Exception");
			}
		}
		if (!StringUtils.isEmpty(searchDTO.getHero())) {
			query.setParameter(CommonConstant.PARAM6,
					CommonConstant.PERCENT_SYMBOLE + searchDTO.getHero() + CommonConstant.PERCENT_SYMBOLE);
		}
		if (!StringUtils.isEmpty(searchDTO.getTitle())) {
			query.setParameter(CommonConstant.PARAM7,
					CommonConstant.PERCENT_SYMBOLE + searchDTO.getTitle() + CommonConstant.PERCENT_SYMBOLE);
		}
		return query.getResultList();
	}

	/**
	 * Query Builder
	 * 
	 * @param searchDTO
	 * @return
	 */
	private String queryBuilder(SearchDTO searchDTO) {
		StringBuilder queryBuilder = new StringBuilder("select p from Publication p where 0=0");
		if (searchDTO.getBookEnumId() != null) {
			queryBuilder.append(" and p.bookEnum.id =:param1");
		}
		if (searchDTO.getMagazineEnumId() != null) {
			queryBuilder.append(" and p.magazineEnum.id =:param2");
		}
		if (searchDTO.getPublicationEnumId() != null) {
			queryBuilder.append(" and p.publicationEnum.id =:param3");
		}
		if (!StringUtils.isEmpty(searchDTO.getAuthorName())) {
			queryBuilder.append(" and p.author.name like:param4");
		} else if (searchDTO.getAuthorId() != null) {
			queryBuilder.append(" and p.author.id =:param4");
		}
		if (!StringUtils.isEmpty(searchDTO.getYear())) {
			queryBuilder.append(" and p.year =:param5");
		}
		if (!StringUtils.isEmpty(searchDTO.getHero())) {
			queryBuilder.append(" and p.hero like:param6");
		}
		if (!StringUtils.isEmpty(searchDTO.getTitle())) {
			queryBuilder.append(" and p.title like:param7");
		}
		return queryBuilder.toString();
	}

}
