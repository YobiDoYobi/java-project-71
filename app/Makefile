.DEFAULT_GOAL := build-run

run-dist:
	./build/install/app/bin/app

build:
	gradle checkstyleMain
	gradle checkstyleTest
	gradle test
	gradle installDist

.PHONY: build

run:
	./build/install/app/bin/app ./src/test/resources/file1.json ./src/test/resources/file2.json

report:
	gradle jacocoTestReport