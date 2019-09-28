package com.assignment.web.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.assignment.web.dto.CommonDTO;
import com.assignment.web.dto.PublicationDTO;
import com.assignment.web.dto.SearchDTO;
import com.assignment.web.exception.ErrorCodeHelper;
import com.assignment.web.exception.ErrorInfo;
import com.assignment.web.exception.ServiceException;
import com.assignment.web.model.Author;
import com.assignment.web.model.EnumItem;
import com.assignment.web.model.Publication;
import com.assignment.web.model.PublicationAuthor;
import com.assignment.web.repository.AuthorRepository;
import com.assignment.web.repository.EnumItemRepository;
import com.assignment.web.repository.PublicationRepository;
import com.assignment.web.repository.SearchDAO;
import com.assignment.web.utils.CommonConstant;
import com.assignment.web.utils.DateUtils;

/**
 * 
 * Publication ServiceImpl
 *
 */
@Service
public class PublicationServiceImpl implements PublicationService {

	@Autowired
	private EnumItemRepository enumItemRepository;

	@Autowired
	private PublicationRepository publicationRepository;
	@Autowired
	private SearchDAO searchDAO;
	@Autowired
	private ErrorCodeHelper errorCodeHelper;

	@Autowired
	private AuthorRepository authorRepository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long addPublication(PublicationDTO publicationDTO) {
		Publication publication = new Publication();
		publication.setPublicationAuthors(new ArrayList<>());
		return addOrUpdatePublication(publication, publicationDTO);
	}

	/**
	 * Add Or Update Publication
	 * 
	 * @param publication
	 * @param publicationDTO
	 * @return
	 */
	private Long addOrUpdatePublication(Publication publication, PublicationDTO publicationDTO) {
		addOrUpdateAuthors(publication, publicationDTO);
		publication.setPublicationEnum(getEnumItem(publicationDTO.getPublicationEnumId()));
		publication.setTitle(publicationDTO.getTitle());
		publication.setYear(setPublicationYear(publicationDTO.getYear()));
		if (publicationDTO.getMagazineEnumId() != null) {
			publication.setMagazineEnum(getEnumItem(publicationDTO.getMagazineEnumId()));
			publication.setBookEnum(null);
			publication.setHero(null);
		} else if (publicationDTO.getBookEnumId() != null) {
			publication.setBookEnum(getEnumItem(publicationDTO.getBookEnumId()));
			publication.setMagazineEnum(null);
			publication.setHero(null);
		} else if (!StringUtils.isEmpty(publicationDTO.getHero())) {
			publication.setHero(publicationDTO.getHero());
			publication.setBookEnum(null);
			publication.setMagazineEnum(null);
		} else {
			ErrorInfo errorInfo = errorCodeHelper.getErrorInfo(CommonConstant.E1004_ERROR_CODE,
					CommonConstant.E1004_ERROR_DESCRIPTION);
			throw new ServiceException(errorInfo, HttpStatus.NOT_FOUND);
		}
		return publicationRepository.save(publication).getId();
	}

	/**
	 * addOrUpdateAuthors
	 * 
	 * @param publication
	 * @param publicationDTO
	 */
	private void addOrUpdateAuthors(Publication publication, PublicationDTO publicationDTO) {
		if (CollectionUtils.isEmpty(publicationDTO.getAuthors())
				&& publicationDTO.getAuthors().stream().anyMatch(f -> f.getId() == null && f.getId() <= 0)) {
			ErrorInfo errorInfo = errorCodeHelper.getErrorInfo(CommonConstant.E1007_ERROR_CODE,
					CommonConstant.E1007_ERROR_DESCRIPTION);
			throw new ServiceException(errorInfo, HttpStatus.NOT_FOUND);
		}
		Map<Long, PublicationAuthor> map = new HashMap<>();
		publication.getPublicationAuthors().forEach(pa -> {
			map.put(pa.getAuthor().getId(), pa);
			pa.setDeleted(true);
		});
		Set<Long> authorsIds = publicationDTO.getAuthors().stream().map(CommonDTO::getId).collect(Collectors.toSet());
		List<Author> authors = authorRepository.findAllById(authorsIds);
		authors.forEach(author -> {
			PublicationAuthor publicationAuth = map.get(author.getId());
			if (publicationAuth != null)
				publicationAuth.setDeleted(false);
			else {
				publicationAuth = new PublicationAuthor();
				publicationAuth.setAuthor(author);
				publicationAuth.setPublication(publication);
				publication.addPublicationAuthor(publicationAuth);
			}
		});

	}

	/**
	 * Set Publication Year
	 * 
	 * @param year
	 * @return
	 */
	private Date setPublicationYear(String year) {
		try {
			return DateUtils.convertStringToDate(year, CommonConstant.DATE_FORMAT_YYYY);
		} catch (ParseException e) {
			ErrorInfo errorInfo = errorCodeHelper.getErrorInfo(CommonConstant.E1005_ERROR_CODE,
					CommonConstant.E1005_ERROR_DESCRIPTION);
			throw new ServiceException(errorInfo, HttpStatus.CONFLICT);
		}
	}

	/**
	 * Get EnumItem
	 * 
	 * @param enumItemId
	 * @return
	 */
	private EnumItem getEnumItem(Long enumItemId) {
		Optional<EnumItem> enumItem = enumItemRepository.findById(enumItemId);
		if (!enumItem.isPresent()) {
			ErrorInfo errorInfo = errorCodeHelper.getErrorInfo(CommonConstant.E1004_ERROR_CODE,
					CommonConstant.E1004_ERROR_DESCRIPTION);
			throw new ServiceException(errorInfo, HttpStatus.NOT_FOUND);
		}
		return enumItem.get();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long updatePublication(PublicationDTO publicationDTO) {
		return addOrUpdatePublication(getPublicationById(publicationDTO.getId()), publicationDTO);
	}

	/**
	 * 
	 * @param id
	 * @param isUpdate
	 * @return
	 */
	private Publication getPublicationById(Long id) {
		Optional<Publication> publication = publicationRepository.findById(id);
		if (!publication.isPresent()) {
			ErrorInfo errorInfo = errorCodeHelper.getErrorInfo(CommonConstant.E1006_ERROR_CODE,
					CommonConstant.E1006_ERROR_DESCRIPTION);
			throw new ServiceException(errorInfo, HttpStatus.NOT_FOUND);
		}
		return publication.get();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PublicationDTO getPublication(Long id) {
		return setPublicationDTO(getPublicationById(id), new PublicationDTO());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<PublicationDTO> getPublications() {
		return getPublications(new ArrayList<>(), publicationRepository.findAll());
	}

	/**
	 * GetPublications
	 * 
	 * @param publicationDTOs
	 * @param publications
	 * @return
	 */
	private List<PublicationDTO> getPublications(List<PublicationDTO> publicationDTOs, List<Publication> publications) {
		publications.forEach(publication -> publicationDTOs.add(setPublicationDTO(publication, new PublicationDTO())));
		return publicationDTOs;
	}

	/**
	 * Set Publication DTO
	 * 
	 * @param publication
	 * @param publicationDTO
	 * @return
	 */
	private PublicationDTO setPublicationDTO(Publication publication, PublicationDTO publicationDTO) {
		publicationDTO.setId(publication.getId());
		publicationDTO.setAuthors(publication.getPublicationAuthors().stream().filter(f->!f.isDeleted())
				.map(m -> new CommonDTO(m.getAuthor().getId(), m.getAuthor().getName())).collect(Collectors.toList()));
		publicationDTO.setTitle(publication.getTitle());
		publicationDTO.setYear(DateUtils.convertDateToString(CommonConstant.DATE_FORMAT_YYYY, publication.getYear()));
		publicationDTO.setPublicationEnumId(publication.getPublicationEnum().getId());
		if (publication.getMagazineEnum() != null) {
			publicationDTO.setMagazineEnumId(publication.getMagazineEnum().getId());
		} else if (publication.getBookEnum() != null) {
			publicationDTO.setBookEnumId(publication.getBookEnum().getId());
		} else if (!StringUtils.isEmpty(publication.getHero())) {
			publicationDTO.setHero(publication.getHero());
		}
		return publicationDTO;

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long delete(Long id) {
		publicationRepository.deleteById(id);
		return id;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<PublicationDTO> searchPublications(SearchDTO searchDTO) {
		return getPublications(new ArrayList<>(), searchDAO.searchPublications(searchDTO));
	}

}
