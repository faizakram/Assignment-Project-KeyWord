package com.assignment.web.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.assignment.web.dto.CommonDTO;
import com.assignment.web.exception.ErrorCodeHelper;
import com.assignment.web.exception.ErrorInfo;
import com.assignment.web.exception.ServiceException;
import com.assignment.web.model.Author;
import com.assignment.web.repository.AuthorRepository;
import com.assignment.web.utils.CommonConstant;

@Service
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private ErrorCodeHelper errorCodeHelper;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long addAuthor(CommonDTO commonDTO) {
		return addOrUpdateAuthor(new Author(), commonDTO);
	}
	/**
	 * Add Or Update Author
	 * @param author
	 * @param commonDTO
	 * @return
	 */
	private Long addOrUpdateAuthor(Author author, CommonDTO commonDTO) {
		author.setName(commonDTO.getName());
		return authorRepository.save(author).getId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long updateAuthor(CommonDTO authordto) {
		return addOrUpdateAuthor(getAuthorById(authordto.getId()), authordto);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Author getAuthorById(Long id) {
		Optional<Author> author = authorRepository.findById(id);
		if (!author.isPresent()) {
			ErrorInfo errorInfo = errorCodeHelper.getErrorInfo(CommonConstant.E1007_ERROR_CODE,
					CommonConstant.E1007_ERROR_DESCRIPTION);
			throw new ServiceException(errorInfo, HttpStatus.NOT_FOUND);
		}
		return author.get();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<CommonDTO> authors() {
		return authorRepository.findAll().stream().map(m -> new CommonDTO(m.getId(), m.getName()))
				.collect(Collectors.toList());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long delete(Long id) {
		authorRepository.deleteById(id);
		return id;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CommonDTO getAuthor(Long id) {
		Author author = getAuthorById(id);
		return new CommonDTO(author.getId(), author.getName());
	}

}
