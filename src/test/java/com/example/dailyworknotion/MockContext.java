package com.example.dailyworknotion;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class MockContext {

    private final MockMvc mockMvc;

    public MockContext(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }
}
