#!/bin/sh

if [ "${TRAVIS_PULL_REQUEST}" == "false" ] 
then
	echo "Running build and SonarQube analysis"
	mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent verify sonar:sonar \
		-Dsonar.host.url=${SONAR_HOST_URL} \
		-Dsonar.login=${SONAR_TOKEN}
else
	echo "Starting Pull Request analysis by SonarQube..."
	mvn clean verify sonar:sonar \
		-Dsonar.analysis.mode=preview \
		-Dsonar.host.url=${SONAR_HOST_URL} \
		-Dsonar.github.login=${GITHUB_LOGIN} \
		-Dsonar.github.oauth=${GITHUB_TOKEN} \
		-Dsonar.github.repository=QualInsight/qualinsight-plugins-sonarqube-smell \
		-Dsonar.github.pullRequest=${TRAVIS_PULL_REQUEST}
fi