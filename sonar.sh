cd com.sistema-escolar2
mvn verify org.sonarsource.scanner.maven:sonar-maven-plugin:sonar \
	-Dsonar.projectKey=ufba-poo-2022-1_equipe8 \
	-Dmaven.test.skip=true \
	-Dsonar.organization=ufba-poo-2022-1 \
	-Dsonar.host.url=https://sonarcloud.io \
	-Dmaven.compiler.source=1.8 \
	-Dmaven.compiler.target=1.8
cd ..
