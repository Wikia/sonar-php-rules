package com.wikia.sonarphp.rules;

import com.wikia.sonarphp.rules.checks.ContextualWgVariableUsageCheck;
import com.wikia.sonarphp.rules.checks.LegacyControllerMethodDeclarationCheck;
import com.wikia.sonarphp.rules.checks.LegacyWfMsgUsageCheck;
import com.wikia.sonarphp.rules.checks.OnMakeGlobalVariablesScriptCheck;
import com.wikia.sonarphp.rules.checks.PlainFormatWfMessageCheck;
import com.wikia.sonarphp.rules.checks.WgParserUsageCheck;
import com.wikia.sonarphp.rules.checks.WikiaLogUsageCheck;
import com.wikia.sonarphp.rules.xml.RulesXmlInputStreamFactory;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableList;
import org.sonar.api.server.rule.RulesDefinitionXmlLoader;
import org.sonar.plugins.php.api.visitors.PHPCustomRulesDefinition;

import java.io.InputStreamReader;
import java.io.Reader;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

public class PHPRuleDefinitions extends PHPCustomRulesDefinition {

	private RulesXmlInputStreamFactory xmlFactory;

	public PHPRuleDefinitions() {
		xmlFactory = new RulesXmlInputStreamFactory();
	}

	@VisibleForTesting
	public PHPRuleDefinitions(RulesXmlInputStreamFactory xmlFactory) {
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
			Reader xmlStreamValidationReader = new InputStreamReader(xmlFactory.getRulesXmlStream());
			Reader xmlStreamRulesDefinitionReader = new InputStreamReader(xmlFactory.getRulesXmlStream());
			Reader xsdStreamReader = new InputStreamReader(xmlFactory.getRulesXsdStream())
		) {
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(new StreamSource(xsdStreamReader));
			Validator validator = schema.newValidator();

			validator.validate(new StreamSource(xmlStreamValidationReader));

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
			WgParserUsageCheck.class, PlainFormatWfMessageCheck.class, WikiaLogUsageCheck.class
		);
	}
}
