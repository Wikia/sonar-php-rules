package com.wikia.sonarphp.rules.xml;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class RulesXmlInputStreamFactoryTest {
	@Test
	public void inputStreamsAreNotReused() {
		RulesXmlInputStreamFactory xmlInputStreamFactory = new RulesXmlInputStreamFactory();

		try (
			InputStream firstXmlStream = xmlInputStreamFactory.getRulesXmlStream();
			InputStream secondXmlStream = xmlInputStreamFactory.getRulesXmlStream();
			InputStream firstXsdStream = xmlInputStreamFactory.getRulesXsdStream();
			InputStream secondXsdStream = xmlInputStreamFactory.getRulesXsdStream()
		) {
			assertThat(firstXmlStream, not(sameInstance(secondXmlStream)));
			assertThat(firstXsdStream, not(sameInstance(secondXsdStream)));
		} catch (IOException e) {}
	}
}
