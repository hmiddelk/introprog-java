# introprog-java

Java translations of the three example programs from
[lunduniversity/introprog-scalalib](https://github.com/lunduniversity/introprog-scalalib)
(v1.4.0), compiled against the introprog library fat jar.

| Example | What it demonstrates |
|---|---|
| `TestPixelWindow` | Drawing lines, rectangles, text; keyboard and mouse events |
| `TestIO` | Object serialisation; image load, save, draw, and animate |
| `TestBlockGame` | Two games extending `BlockGame`: random blocks and a moving block |

---

## Requirements

| | Linux | Windows |
|---|---|---|
| Java (JDK) | 17 or newer | 17 or newer |
| Build tool | `make` (pre-installed on most distros) | none — use `build.bat` |
| Display | X11 (`$DISPLAY` must be set) | built-in |

All three examples open Swing windows, so a graphical display is mandatory.
Running on a headless server without a display will throw `java.awt.HeadlessException`.

The `lib/` directory in this repo already contains the three jars needed for
compilation — no internet access or package manager required after cloning.

---

## Linux

### 1. Clone the repository

```bash
git clone https://github.com/hmiddelk/introprog-java.git
cd introprog-java
```

### 2. Compile and build the fat jar

```bash
make
```

This compiles the Java sources and packages everything (introprog library +
Scala 3 runtime + compiled examples) into a single self-contained jar:
`build/introprog-examples-fat.jar`

### 3. Run the examples

```bash
make run-pixel      # TestPixelWindow — drawing and events
make run-io         # TestIO          — serialisation and images
make run-blockgame  # TestBlockGame   — two block games
```

### Other targets

```bash
make clean          # remove the build/ directory
```

### Notes

- Verify your display is available: `echo $DISPLAY` should print something like `:0` or `:1`.
- On WSL2 (Windows 11) a display is available automatically via WSLg.
- On WSL1 install an X server on Windows (e.g. VcXsrv) and set `export DISPLAY=:0`.
- On a headless server use a virtual framebuffer: `Xvfb :99 -screen 0 1280x720x24 &` then `export DISPLAY=:99`.

---

## Windows

### 1. Prerequisites

Install a JDK (17 or newer) from https://adoptium.net or https://jdk.java.net
and make sure `javac` and `java` are on your `PATH`:

```bat
javac -version
java -version
```

### 2. Clone the repository

```bat
git clone https://github.com/hmiddelk/introprog-java.git
cd introprog-java
```

Or download and unzip the repository from GitHub.

### 3. Compile and build the fat jar

```bat
build.bat
```

This compiles the Java sources and packages everything into:
`build\introprog-examples-fat.jar`

### 4. Run the examples

```bat
build.bat run-pixel       :: TestPixelWindow — drawing and events
build.bat run-io          :: TestIO          — serialisation and images
build.bat run-blockgame   :: TestBlockGame   — two block games
```

### Other targets

```bat
build.bat clean           :: remove the build\ directory
```

### Notes

- Windows has a built-in display — no extra setup needed.
- The examples have been tested on Windows 11 with JDK 21.
- If `build.bat` is blocked by execution policy, right-click → "Run as administrator"
  or run from a Developer Command Prompt.

---

## Repository layout

```
lib/                          dependency jars (included — no download needed)
  introprog_3-1.4.0.jar
  scala3-library_3-3.3.3.jar
  scala-library-2.13.12.jar

src/main/java/introprog/examples/
  TestPixelWindow.java
  TestIO.java
  TestBlockGame.java

Makefile                      Linux/macOS build and run targets
build.bat                     Windows build and run targets
build.gradle                  Gradle descriptor (optional, requires Gradle 8+)

build/                        generated — not committed
  introprog-examples-fat.jar  self-contained fat jar (7 MB)
```

---

## Background

The original examples are written in Scala 3 and live in the
[introprog-scalalib](https://github.com/lunduniversity/introprog-scalalib)
repository maintained by Lund University.  This repo provides equivalent Java
source so the library can be explored without a Scala toolchain.

Key translation notes:

- Scala singleton objects (`IO`, `PixelWindow` companion) are accessed in Java
  via `IO$.MODULE$.method(...)` or through static forwarders on the main class.
- Scala tuples `(Int, Int)` become `scala.Tuple2<Object, Object>` in bytecode;
  created with `new scala.Tuple2<>(x, y)`, elements read with `._1()` / `._2()`.
- By-name parameters (e.g. `gameLoop(stopWhen: => Boolean)`) become
  `scala.Function0<Object>` and are passed as anonymous `AbstractFunction0` instances.
