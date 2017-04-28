package com.wikia.sonarphp.rules;

import org.junit.Test;
import org.sonar.api.Plugin.Context;
import org.sonar.api.SonarProduct;
import org.sonar.api.SonarQubeSide;
import org.sonar.api.SonarRuntime;
import org.sonar.api.utils.Version;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.hasItem;

import java.util.List;

public class PluginTest {
	@Test
	@SuppressWarnings("unchecked")
	public void pluginIsRegistered() {
		PHPRulesPlugin phpRulesPlugin = new PHPRulesPlugin();
		Context context = getMockContext();

		phpRulesPlugin.define(context);

		assertThat((List<Class>) context.getExtensions(), hasItem(PHPRuleDefinitions.class));
	}

	private Context getMockContext() {
		return new Context(new SonarRuntime() {
			@Override
			public Version getApiVersion() {
				return null;
			}

			@Override
			public SonarProduct getProduct() {
				return null;
			}

			@Override
			public SonarQubeSide getSonarQubeSide() {
				return null;
			}
		});
	}
}
