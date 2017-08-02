package com.wikia.sonarphp.rules;

import com.wikia.sonarphp.rules.checks.*;
import com.wikia.sonarphp.rules.xml.RulesXmlReaderFactory;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableList;
import org.sonar.api.server.rule.RulesDefinitionXmlLoader;
import org.sonar.plugins.php.api.visitors.PHPCustomRulesDefinition;

import java.io.Reader;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

public class PHPRuleDefinitions extends PHPCustomRulesDefinition {

	private RulesXmlReaderFactory xmlFactory;

	public PHPRuleDefinitions() {
		xmlFactory = new RulesXmlReaderFactory();
	}

	@VisibleForTesting
	public PHPRuleDefinitions(RulesXmlReaderFactory xmlFactory) {
		this.xmlFactory = xmlFactory;
	}

	@Override
	public String repositoryName() {
		return "Wikia PHP Rules";
	}

	@Override
	public String repositoryKey() {
		return "wikia-php-rules";
	}

	@Override
	public void define(Context context) {
		try (
			Reader xmlStreamValidationReader = xmlFactory.newRulesXmlReader();
			Reader xmlStreamRulesDefinitionReader = xmlFactory.newRulesXmlReader();
			Reader xsdStreamReader = xmlFactory.newRulesXsdReader()
		) {
			StreamSource xsdStreamSource = new StreamSource(xsdStreamReader);
			StreamSource xmlStreamSource = new StreamSource(xmlStreamValidationReader);

			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(xsdStreamSource);
			Validator validator = schema.newValidator();

			validator.validate(xmlStreamSource);

			NewRepository repo = context.createRepository(repositoryKey(), "php").setName(repositoryName());
			RulesDefinitionXmlLoader xmlLoader = new RulesDefinitionXmlLoader();

			xmlLoader.load(repo, xmlStreamRulesDefinitionReader);
			repo.done();
		} catch (Exception e) {
			throw new IllegalStateException("rules.xml not found or invalid", e);
		}
	}

	@Override
	public ImmutableList<Class> checkClasses() {
		return ImmutableList.of(
			ContextualWgVariableUsageCheck.class, LegacyWfMsgUsageCheck.class,
			OnMakeGlobalVariablesScriptCheck.class, LegacyControllerMethodDeclarationCheck.class,
			WgParserUsageCheck.class, PlainFormatWfMessageCheck.class, ThisPassedByReferenceCheck.class,
			WikiaLogUsageCheck.class
		);
	}
}
