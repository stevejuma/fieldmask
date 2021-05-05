.PHONY: debug

debug:
	TEST_STDOUT=true ./gradlew clean ktlintFormat test --fail-fast

release:
	./gradlew clean assemble publish

publish
	./gradlew clean build publish closeAndReleaseRepository

grammar:
	./gradlew -p fieldmask-core clean generateGrammarSource