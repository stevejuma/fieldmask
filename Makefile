.PHONY: debug

debug:
	TEST_STDOUT=true ./gradlew clean ktlintFormat test --fail-fast

release:
	./gradlew clean assemble publish

grammar:
	./gradlew -p fieldmask-core clean generateGrammarSource