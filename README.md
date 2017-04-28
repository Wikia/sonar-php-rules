[![Build Status](https://api.travis-ci.org/Wikia/sonar-php-rules.svg?branch=master)](https://travis-ci.org/Wikia/sonar-php-rules)

Sonar PHP rules
======
Wikia's custom SonarQube rules for analyzing PHP files.

## Requirements
- JDK 8

## Getting started
Read the official SonarQube documentation about [creating a plugin](https://docs.sonarqube.org/display/DEV/Build+Plugin)
and [writing custom rules for PHP](https://docs.sonarqube.org/display/PLUG/Custom+Rules+for+PHP).

Each newly introduced rule should have a corresponding unit test with fixtures that tests registration and actual rule behavior.