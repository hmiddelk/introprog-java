@echo off
setlocal

set LIB=lib\introprog_3-1.4.0.jar;lib\scala3-library_3-3.3.3.jar;lib\scala-library-2.13.12.jar
set FAT_JAR=build\introprog-examples-fat.jar

if "%1"=="clean"       goto clean
if "%1"=="run-pixel"   goto run-pixel
if "%1"=="run-io"      goto run-io
if "%1"=="run-blockgame" goto run-blockgame

:: Default: build fat jar
:fat
echo Compiling...
if not exist build\classes mkdir build\classes
javac -cp "%LIB%" -d build\classes ^
    src\main\java\introprog\examples\TestPixelWindow.java ^
    src\main\java\introprog\examples\TestIO.java ^
    src\main\java\introprog\examples\TestBlockGame.java
if errorlevel 1 goto error

echo Building fat jar...
if not exist build\fat mkdir build\fat
cd build\fat
jar xf ..\..\lib\introprog_3-1.4.0.jar
jar xf ..\..\lib\scala3-library_3-3.3.3.jar
jar xf ..\..\lib\scala-library-2.13.12.jar
cd ..\..
xcopy /e /q /y build\classes\introprog build\fat\introprog\ >nul
jar cfe %FAT_JAR% introprog.examples.TestPixelWindow -C build\fat .
echo Fat jar: %FAT_JAR%
goto end

:run-pixel
call "%~f0"
java -jar %FAT_JAR%
goto end

:run-io
call "%~f0"
java -cp %FAT_JAR% introprog.examples.TestIO
goto end

:run-blockgame
call "%~f0"
java -cp %FAT_JAR% introprog.examples.TestBlockGame
goto end

:clean
echo Cleaning...
if exist build rmdir /s /q build
echo Done.
goto end

:error
echo Build failed.
exit /b 1

:end
endlocal
