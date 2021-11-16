package com.felix.fonteneau.contentdrivenrest;

import com.felix.fonteneau.contentdrivenrest.dao.ContentableDAO;
import com.felix.fonteneau.contentdrivenrest.dao.FilterDAO;
import com.felix.fonteneau.contentdrivenrest.model.Alternative;
import com.felix.fonteneau.contentdrivenrest.model.Condition;
import com.felix.fonteneau.contentdrivenrest.model.Content;
import com.felix.fonteneau.contentdrivenrest.model.Link;
import com.felix.fonteneau.contentdrivenrest.util.Pair;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ContentDrivenRestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ContentDrivenRestApplicationTests {
    private final static Condition ALWAYS_TRUE = new Condition(List.of("alwaysTrue"), null, null);
    private final static Condition ALWAYS_FALSE = new Condition(List.of("alwaysFalse"), null, null);

    @Autowired
    private ContentableDAO contentableDAO;

    @Autowired
    private FilterDAO filterDAO;

    @Autowired
    private MockMvc mvc;


    @Test
    void contextLoads() {
    }



    @Test
    void testGetContent() throws Exception {
        contentableDAO.addOrReplace(new Content("id1", "text", null, null, "bla", null, null));

        System.out.println("test");

        mvc.perform(get("/content/")
                        .param("id", "id1")
                        .param("applicationData", ""))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value("id1"))
                .andExpect(jsonPath("$.text").value("bla"))
                .andExpect(jsonPath("$.type").value("text"));
    }


    @Test
    void testGetAlternative() throws Exception {
        Content c1 = new Content("id1", "text", null, null, "bla", null, null);
        Content c2 = new Content("id2", "text", null, new Link("id5"), "next", null, null);
        Content c3 = new Content("id3", "text", null, null, "book", null, null);

        contentableDAO.addOrReplace(
                new Alternative("alternative1",
                        List.of(Pair.of(c1, ALWAYS_FALSE),
                                Pair.of(c2, ALWAYS_FALSE),
                                Pair.of(c3, ALWAYS_TRUE)
                        )
                )
        );

        mvc.perform(get("/content/")
                        .param("id", "alternative1")
                        .param("applicationData", ""))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value("id3"))
                .andExpect(jsonPath("$.text").value("book"))
                .andExpect(jsonPath("$.type").value("text"));
    }


    @Test
    void testPutContent() throws Exception {

    }


}
