
This is the program produced base on the requirement provided in IDENTITY-E2E–Test-4.docx.

Steps:
1) install a web driver in you pc. This program test was developed using :

Firefox driver:  https://en.wikipedia.org/wiki/Gecko_(software)
https://github.com/mozilla/geckodriver/releases

2) add the -D parameter with your webDriver location. Please complete the systemPropertyVariables section in the pom.xml

<systemPropertyVariables>
    <webdriverLocation>/home/skynet/mis_cosas/programs/java/seleniumDriver/geckodriver-v0.19.1-linux32/geckodriver</webdriverLocation>
</systemPropertyVariables>

3) to run all test use mvn clean install


4) Check the resultsImages folder.


