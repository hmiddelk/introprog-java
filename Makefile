LIB      = lib/introprog_3-1.4.0.jar:lib/scala3-library_3-3.3.3.jar:lib/scala-library-2.13.12.jar
SOURCES  = $(wildcard src/main/java/introprog/examples/*.java)
LIFE_SRC      = src/main/java/life/Matrix.java src/main/java/life/Life.java src/main/java/life/LifeWindow.java
WORKSHOP_SRC  = src/workshop/java/life/Matrix.java src/workshop/java/life/Life.java src/workshop/java/life/LifeWindow.java
FAT_JAR  = build/introprog-examples-fat.jar

.PHONY: all compile fat run-pixel run-io run-blockgame run-life compile-workshop run-workshop clean

all: fat

compile:
	@mkdir -p build/classes
	javac -cp "$(LIB)" -d build/classes $(SOURCES) $(LIFE_SRC)

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

run-life: compile
	java -cp "build/classes:$(LIB)" life.LifeWindow

compile-workshop:
	@mkdir -p build/workshop-classes
	javac -cp "$(LIB)" -d build/workshop-classes $(WORKSHOP_SRC)

run-workshop: compile-workshop
	java -cp "build/workshop-classes:$(LIB)" life.LifeWindow

clean:
	rm -rf build
