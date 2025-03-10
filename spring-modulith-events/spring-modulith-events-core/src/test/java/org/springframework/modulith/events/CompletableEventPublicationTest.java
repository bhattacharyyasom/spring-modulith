/*
 * Copyright 2017-2023 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.modulith.events;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * @author Oliver Drotbohm
 * @author Björn Kieling
 * @author Dmitry Belyaev
 */
class CompletableEventPublicationUnitTests {

	@Test
	void rejectsNullEvent() {

		assertThatExceptionOfType(IllegalArgumentException.class)//
				.isThrownBy(() -> CompletableEventPublication.of(null, PublicationTargetIdentifier.of("foo")))//
				.withMessageContaining("Event");
	}

	@Test
	void rejectsNullTargetIdentifier() {

		assertThatExceptionOfType(IllegalArgumentException.class)//
				.isThrownBy(() -> CompletableEventPublication.of(new Object(), null))//
				.withMessageContaining("TargetIdentifier");
	}

	@Test
	void publicationIsIncompleteByDefault() {

		CompletableEventPublication publication = CompletableEventPublication.of(new Object(),
				PublicationTargetIdentifier.of("foo"));

		assertThat(publication.isPublicationCompleted()).isFalse();
		assertThat(publication.getCompletionDate()).isNotPresent();
	}

	@Test
	void completionCapturesDate() {

		CompletableEventPublication publication = CompletableEventPublication
				.of(new Object(), PublicationTargetIdentifier.of("foo")).markCompleted();

		assertThat(publication.isPublicationCompleted()).isTrue();
		assertThat(publication.getCompletionDate()).isPresent();
	}
}
