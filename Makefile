.PHONY: debug

debug:
	TEST_STDOUT=true ./gradlew clean ktlintFormat test --fail-fast

publish:
	./gradlew clean assemble publish

release:
	./gradlew publishToSonatype closeAndReleaseSonatypeStagingRepository

grammar:
	./gradlew -p fieldmask-core clean generateGrammarSource