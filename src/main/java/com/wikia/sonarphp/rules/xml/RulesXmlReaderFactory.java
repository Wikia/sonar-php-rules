package com.wikia.sonarphp.rules.xml;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

import javax.annotation.Nullable;

public class RulesXmlReaderFactory {
	@Nullable
	private URL rulesXmlUrl;

	@Nullable
	private URL rulesXsdUrl;

	public Reader newRulesXmlReader() throws IOException {
		if (rulesXmlUrl == null) {
			rulesXmlUrl = getClass().getResource("/com/wikia/sonarphp/rules/rules.xml");
		}

		InputStream rulesXmlStream = rulesXmlUrl.openStream();

		return new InputStreamReader(rulesXmlStream);
	}

	public Reader newRulesXsdReader() throws IOException {
		if (rulesXsdUrl == null) {
			rulesXsdUrl = getClass().getResource("/com/wikia/sonarphp/rules/rules.xsd");
		}

		InputStream rulesXsdStream = rulesXsdUrl.openStream();

		return new InputStreamReader(rulesXsdStream);
	}
}
