package com.wikia.sonarphp.rules.xml;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

import java.io.Reader;
import org.junit.Test;

import java.io.IOException;

public class RulesXmlReaderFactoryTest {
	@Test
	public void inputStreamsAreNotReused() {
		RulesXmlReaderFactory xmlInputStreamFactory = new RulesXmlReaderFactory();

		try (
			Reader firstXmlReader = xmlInputStreamFactory.newRulesXmlReader();
			Reader secondXmlReader = xmlInputStreamFactory.newRulesXmlReader();
			Reader firstXsdReader = xmlInputStreamFactory.newRulesXsdReader();
			Reader secondXsdReader = xmlInputStreamFactory.newRulesXsdReader()
		) {
			assertThat(firstXmlReader, not(sameInstance(secondXmlReader)));
			assertThat(firstXsdReader, not(sameInstance(secondXsdReader)));
		} catch (IOException e) {}
	}
}
