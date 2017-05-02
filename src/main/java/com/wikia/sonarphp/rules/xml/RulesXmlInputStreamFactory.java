package com.wikia.sonarphp.rules.xml;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.annotation.Nullable;

public class RulesXmlInputStreamFactory {
	@Nullable
	private URL rulesXmlUrl;

	@Nullable
	private URL rulesXsdUrl;

	public InputStream getRulesXmlStream() throws IOException {
		if (rulesXmlUrl == null) {
			rulesXmlUrl = getClass().getResource("/com/wikia/sonarphp/rules/rules.xml");
		}

		return rulesXmlUrl.openStream();
	}

	public InputStream getRulesXsdStream() throws IOException {
		if (rulesXsdUrl == null) {
			rulesXsdUrl = getClass().getResource("/com/wikia/sonarphp/rules/rules.xsd");
		}

		return rulesXsdUrl.openStream();
	}
}
