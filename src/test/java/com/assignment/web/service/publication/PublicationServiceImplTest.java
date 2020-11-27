package com.assignment.web.service.publication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.assignment.web.dto.CommonDTO;
import com.assignment.web.dto.PublicationDTO;
import com.assignment.web.dto.SearchDTO;
import com.assignment.web.exception.ErrorCodeHelper;
import com.assignment.web.exception.ServiceException;
import com.assignment.web.model.Author;
import com.assignment.web.model.EnumItem;
import com.assignment.web.model.Publication;
import com.assignment.web.model.PublicationAuthor;
import com.assignment.web.repository.AuthorRepository;
import com.assignment.web.repository.EnumItemRepository;
import com.assignment.web.repository.PublicationRepository;
import com.assignment.web.repository.SearchDAO;
import com.assignment.web.service.PublicationServiceImpl;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.integration.junit4.JMockit;

@RunWith(JMockit.class)
public class PublicationServiceImplTest {

	@Tested
	private PublicationServiceImpl publicationServiceImpl;

	@Injectable
	private EnumItemRepository enumItemRepository;

	@Injectable
	private PublicationRepository publicationRepository;

	@Injectable
	private SearchDAO searchDAO;

	@Injectable
	private ErrorCodeHelper errorCodeHelper;

	@Injectable
	private AuthorRepository authorRepository;

	private PublicationDTO publicationDTO;

	private List<CommonDTO> authorList;

	private CommonDTO authorDto;

	private EnumItem enumItem;

	private Publication publication;

	private PublicationAuthor publicationAuthor;

	private Author author;

	private Author author2;

	@Before
	public void setUp() {
		// Creating publicationDTO instance
		publicationDTO = new PublicationDTO();

		// Creating and initializing authorDto instance
		authorDto = new CommonDTO();
		authorDto.setId(1L);

		// Creating and initializing authorList instance
		authorList = new ArrayList<>();
		authorList.add(authorDto);

		// Initializing publicationDTO instance
		publicationDTO.setId(1L);
		publicationDTO.setAuthors(authorList);
		publicationDTO.setPublicationEnumId(1L);
		publicationDTO.setYear("2020");

		// Creating and initializing enumItem instance
		enumItem = new EnumItem();
		enumItem.setId(1L);
		enumItem.setEnamItemName("test");

		// Creating and initializing author instance
		author = new Author();
		author.setId(1L);

		// Creating and initializing author2 instance
		author2 = new Author();
		author2.setId(2L);

		// Creating and initializing publicationAuthor instance
		publicationAuthor = new PublicationAuthor();
		publicationAuthor.setId(1L);
		publicationAuthor.setAuthor(author);

		// Creating and initializing publicationAuthorList instance
		List<PublicationAuthor> publicationAuthorList = new ArrayList<>();
		publicationAuthorList.add(publicationAuthor);

		// Creating and initializing enumItem instance
		publication = new Publication();
		publication.setId(1L);
		publication.setPublicationEnum(enumItem);
		publication.setPublicationAuthors(publicationAuthorList);
		publication.setYear(new Date());
		publication.setPublicationAuthors(publicationAuthorList);

	}

	@Test(expected = ServiceException.class)
	public void testAddPublicationForEmptyAuthorException() {
		publicationDTO.setAuthors(new ArrayList<>());
		publicationServiceImpl.addPublication(publicationDTO);
	}

	@Test(expected = ServiceException.class)
	public void testAddPublicationForInvalidAuthorIdCondException() {
		authorDto.setId(-1L);
		publicationServiceImpl.addPublication(publicationDTO);
	}

	@Test(expected = ServiceException.class)
	public void testAddPublicationForEnumException() {
		new Expectations() {
			{
				enumItemRepository.findById(publicationDTO.getPublicationEnumId());
				result = Optional.empty();
			}
		};
		publicationServiceImpl.addPublication(publicationDTO);
	}

	@Test()
	public void testAddPublicationForMagazine() {
		publicationDTO.setMagazineEnumId(1L);
		new Expectations() {
			{
				enumItemRepository.findById(publicationDTO.getPublicationEnumId());
				result = Optional.of(enumItem);

				publicationRepository.save((Publication) any);
				result = publication;
			}
		};
		assertEquals(new Long(1L), publicationServiceImpl.addPublication(publicationDTO));
	}

	@Test()
	public void testAddPublicationForBook() {
		publicationDTO.setBookEnumId(1L);
		new Expectations() {
			{
				enumItemRepository.findById(publicationDTO.getPublicationEnumId());
				result = Optional.of(enumItem);

				publicationRepository.save((Publication) any);
				result = publication;
			}
		};
		assertEquals(new Long(1L), publicationServiceImpl.addPublication(publicationDTO));
	}

	@Test()
	public void testAddPublicationForHeroCondition() {
		publicationDTO.setHero("test");
		new Expectations() {
			{
				enumItemRepository.findById(publicationDTO.getPublicationEnumId());
				result = Optional.of(enumItem);

				publicationRepository.save((Publication) any);
				result = publication;
			}
		};
		assertEquals(new Long(1L), publicationServiceImpl.addPublication(publicationDTO));
	}

	@Test(expected = ServiceException.class)
	public void testAddPublicationForNoMatchCondition() {
		new Expectations() {
			{
				enumItemRepository.findById(publicationDTO.getPublicationEnumId());
				result = Optional.of(enumItem);
			}
		};
		publicationServiceImpl.addPublication(publicationDTO);
	}

	@Test(expected = ServiceException.class)
	public void testAddPublicationForInvalidYearException() {
		publicationDTO.setYear("Invalid");
		new Expectations() {
			{
				enumItemRepository.findById(publicationDTO.getPublicationEnumId());
				result = Optional.of(enumItem);
			}
		};
		publicationServiceImpl.addPublication(publicationDTO);
	}

	@Test(expected = ServiceException.class)
	public void testUpdatePublicationForNoPublicationException() {
		new Expectations() {
			{
				publicationRepository.findById(publicationDTO.getId());
				result = Optional.empty();
			}
		};
		assertEquals(new Long(1L), publicationServiceImpl.updatePublication(publicationDTO));
	}

	@Test()
	public void testUpdatePublication() {
		publicationDTO.setHero("test");
		new Expectations() {
			{
				publicationRepository.findById(publicationDTO.getId());
				result = Optional.of(publication);

				enumItemRepository.findById(publicationDTO.getPublicationEnumId());
				result = Optional.of(enumItem);

				publicationRepository.save((Publication) any);
				result = publication;

				authorRepository.findAllById(new HashSet<>(Arrays.asList(1L)));
				result = Arrays.asList(author, author2);
			}
		};
		assertEquals(new Long(1L), publicationServiceImpl.updatePublication(publicationDTO));
	}

	@Test()
	public void testGetPublicationForMagazine() {
		publication.setMagazineEnum(enumItem);
		new Expectations() {
			{
				publicationRepository.findById(1L);
				result = Optional.of(publication);
			}
		};
		assertNotNull(publicationServiceImpl.getPublication(1L));
	}

	@Test()
	public void testGetPublicationForBook() {
		publication.setBookEnum(enumItem);
		publicationAuthor.setDeleted(true);
		new Expectations() {
			{
				publicationRepository.findById(1L);
				result = Optional.of(publication);
			}
		};
		assertNotNull(publicationServiceImpl.getPublication(1L));
	}

	@Test()
	public void testGetPublications() {
		publication.setHero("test");
		new Expectations() {
			{
				publicationRepository.findAll();
				result = Arrays.asList(publication);
			}
		};
		assertNotNull(publicationServiceImpl.getPublications());
	}

	@Test()
	public void testGetPublicationsForCondition() {
		new Expectations() {
			{
				publicationRepository.findAll();
				result = Arrays.asList(publication);
			}
		};
		assertNotNull(publicationServiceImpl.getPublications());
	}

	@Test()
	public void testDelete() {
		assertNotNull(publicationServiceImpl.delete(1L));
	}

	@Test()
	public void testSearchPublications() {
		new Expectations() {
			{
				searchDAO.searchPublications((SearchDTO) any);
				result = Arrays.asList(publication);
			}
		};
		assertNotNull(publicationServiceImpl.searchPublications(new SearchDTO()));
	}

}
