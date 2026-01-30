@ECHO OFF

REM Set gradle path
SET ROOT_DIR=..
SET GRADLE_CMD=%ROOT_DIR%\gradlew

REM Clean and Compile using Gradle
REM 'classes' handles compilation; 'clean' ensures a fresh start
call %GRADLE_CMD% -p %ROOT_DIR% clean classes
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)

REM Run the program using Gradle's 'run' task
REM Pass the input.txt and redirect output to ACTUAL.TXT
REM This needs 'application' plugin applied in build.gradle
call %GRADLE_CMD% -p %ROOT_DIR% run --quiet --console=plain < input.txt > ACTUAL.TXT

REM Compare the output
FC /N ACTUAL.TXT EXPECTED.TXT