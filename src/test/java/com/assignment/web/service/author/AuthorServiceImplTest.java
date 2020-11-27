package com.assignment.web.service.author;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.assignment.web.dto.CommonDTO;
import com.assignment.web.exception.ErrorCodeHelper;
import com.assignment.web.exception.ServiceException;
import com.assignment.web.model.Author;
import com.assignment.web.repository.AuthorRepository;
import com.assignment.web.service.AuthorServiceImpl;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.integration.junit4.JMockit;

@RunWith(JMockit.class)
public class AuthorServiceImplTest {

	@Tested
	private AuthorServiceImpl authorServiceImpl;

	@Injectable
	private AuthorRepository authorRepository;

	@Injectable
	private ErrorCodeHelper errorCodeHelper;

	private CommonDTO authorDto;

	private Author author;

	@Before
	public void setUp() {
		// Creating and initializing authorDto instance
		authorDto = new CommonDTO();
		authorDto.setId(1L);
		authorDto.setName("test");

		// Creating and initializing author instance
		author = new Author();
		author.setId(1L);
	}

	@Test
	public void testAddAuthor() {
		new Expectations() {
			{
				authorRepository.save((Author) any);
				result = author;
			}
		};
		assertEquals(new Long(1L), authorServiceImpl.addAuthor(authorDto));
	}

	@Test(expected = ServiceException.class)
	public void testUpdateAuthorForNoAuthorFound() {
		new Expectations() {
			{
				authorRepository.findById(authorDto.getId());
				result = Optional.empty();

			}
		};
		authorServiceImpl.updateAuthor(authorDto);
	}

	@Test
	public void testUpdateAuthor() {
		new Expectations() {
			{
				authorRepository.findById(authorDto.getId());
				result = Optional.of(author);

				authorRepository.save((Author) any);
				result = author;
			}
		};
		assertEquals(new Long(1L), authorServiceImpl.updateAuthor(authorDto));
	}

	@Test
	public void testAuthors() {
		new Expectations() {
			{
				authorRepository.findAll();
				result = Arrays.asList(author);
			}
		};
		assertEquals(false, authorServiceImpl.authors().isEmpty());
	}

	@Test
	public void testDelete() {
		assertEquals(new Long(1L), authorServiceImpl.delete(1L));
	}

	@Test
	public void testGetAuthor() {
		new Expectations() {
			{
				authorRepository.findById(authorDto.getId());
				result = Optional.of(author);
			}
		};
		assertNotNull(authorServiceImpl.getAuthor(1L));
	}
}
