LIB      = lib/introprog_3-1.4.0.jar:lib/scala3-library_3-3.3.3.jar:lib/scala-library-2.13.12.jar
SOURCES  = $(wildcard src/main/java/introprog/examples/*.java)
FAT_JAR  = build/introprog-examples-fat.jar

.PHONY: all compile fat run-pixel run-io run-blockgame clean

all: fat

compile:
	@mkdir -p build/classes
	javac -cp "$(LIB)" -d build/classes $(SOURCES)

fat: compile
	@mkdir -p build/fat
	@cd build/fat && jar xf ../../lib/introprog_3-1.4.0.jar
	@cd build/fat && jar xf ../../lib/scala3-library_3-3.3.3.jar
	@cd build/fat && jar xf ../../lib/scala-library-2.13.12.jar
	@cp -r build/classes/introprog build/fat/
	jar cfe $(FAT_JAR) introprog.examples.TestPixelWindow -C build/fat .
	@echo "Fat jar: $(FAT_JAR)"

run-pixel: fat
	java -jar $(FAT_JAR)

run-io: fat
	java -cp $(FAT_JAR) introprog.examples.TestIO

run-blockgame: fat
	java -cp $(FAT_JAR) introprog.examples.TestBlockGame

clean:
	rm -rf build
