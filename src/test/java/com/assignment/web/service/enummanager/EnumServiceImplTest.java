package com.assignment.web.service.enummanager;

import static org.junit.Assert.assertNotNull;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.assignment.web.dto.enums.EnumItemDTO;
import com.assignment.web.model.EnumItem;
import com.assignment.web.repository.EnumItemRepository;
import com.assignment.web.service.EnumItemServiceImpl;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.integration.junit4.JMockit;

@RunWith(JMockit.class)
public class EnumServiceImplTest {
	
	@Tested
	private EnumItemServiceImpl enumItemServiceImpl;

	@Injectable
	private EnumItemRepository enumItemRepository;

	private EnumItem enumItem;
	
	@Before
	public void setUp() {
		// Creating and initializing enumItem instance
		enumItem = new EnumItem();
		enumItem.setId(1L);
		enumItem.setEnamItemName("test");
	}
	
	@Test
	public void testGetEnumItem() {
		new Expectations() {
			{
				enumItemRepository.findByEnumType(EnumItemDTO.BOOK.toString());
				result = Arrays.asList(enumItem);
			}
		};
		assertNotNull(enumItemServiceImpl.getEnumItem(EnumItemDTO.BOOK));
	}
}
