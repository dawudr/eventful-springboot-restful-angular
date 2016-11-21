package com.dawud.events.repository;

import com.dawud.Application;
import com.dawud.events.domain.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class CategoryRepositoryTest {


    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void getCategories() {
        try {
            List<Category> categories= categoryRepository.getCategories();
            assertNotNull(categories);
            assertTrue(categories.size() > 0);
            assertNotNull(categories.get(0).getId());
            assertNotNull(categories.get(0).getName());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


