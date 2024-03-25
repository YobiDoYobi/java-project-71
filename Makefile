 .DEFAULT_GOAL := build-run

run-dist:
	make -C ./app run-dist

build:
	make -C ./app build

.PHONY: build

run:
	make -C ./app run

report:
	make -C ./app report