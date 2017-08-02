package com.wikia.sonarphp;

import org.sonar.api.batch.fs.internal.DefaultInputFile;
import org.sonar.api.batch.sensor.internal.SensorContextTester;
import org.sonar.php.compat.CompatibilityHelper;
import org.sonar.plugins.php.api.visitors.PhpFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

public class TestHelper {
	public static PhpFile getUselessWrapperClassForFile(String fileName) {
		File file = new File(fileName);
		DefaultInputFile inputFile = new DefaultInputFile("moduleKey", file.getName())
			.setModuleBaseDir(file.getParentFile().toPath())
			.setCharset(Charset.defaultCharset());

		try {
			inputFile.initMetadata(new String(Files.readAllBytes(file.toPath()), Charset.defaultCharset()));
		} catch (IOException e) {
			throw new IllegalStateException("Failed to create test file from: " + file.getAbsolutePath());
		}

		return CompatibilityHelper.phpFile(inputFile, SensorContextTester.create(new File("")));
	}
}
