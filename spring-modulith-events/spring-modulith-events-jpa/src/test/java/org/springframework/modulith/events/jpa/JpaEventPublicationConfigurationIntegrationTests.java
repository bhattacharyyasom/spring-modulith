/*
 * Copyright 2022-2023 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.modulith.events.jpa;

import static org.assertj.core.api.Assertions.*;

import example.ExampleApplication;
import lombok.RequiredArgsConstructor;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.modulith.events.EventPublicationRegistry;
import org.springframework.modulith.events.EventSerializer;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.TestConstructor.AutowireMode;

/**
 * @author Oliver Drotbohm
 * @author Dmitry Belyaev
 * @author Björn Kieling
 */
@SpringBootTest(classes = ExampleApplication.class)
@TestConstructor(autowireMode = AutowireMode.ALL)
@RequiredArgsConstructor
class JpaEventPublicationConfigurationIntegrationTests {

	private final ApplicationContext context;

	@MockBean EventSerializer serializer;

	@Test
	void bootstrapsApplicationComponents() {

		assertThat(context.getBean(EventPublicationRegistry.class)).isNotNull();
		assertThat(context.getBean(JpaEventPublicationRepository.class)).isNotNull();
	}
}
