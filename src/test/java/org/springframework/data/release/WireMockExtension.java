/*
 * Copyright 2020-2022 the original author or authors.
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
package org.springframework.data.release;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

/**
 * @author Mark Paluch
 */
public class WireMockExtension extends WireMockServer implements BeforeEachCallback, AfterEachCallback {

	private final WireMockConfiguration configuration;

	public WireMockExtension(WireMockConfiguration configuration) {
		super(configuration);
		this.configuration = configuration;
	}

	@Override
	public void beforeEach(ExtensionContext context) {
		WireMock.configureFor(configuration.portNumber());
		this.start();
	}

	@Override
	public void afterEach(ExtensionContext context) {
		this.stop();
		this.resetAll();
	}
}
