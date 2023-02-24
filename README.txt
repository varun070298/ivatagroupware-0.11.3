                             ---- ---- ---- ----
                    ---- WELCOME TO ivata groupware ----
                             ---- ---- ---- ----
Welcome to ivata groupware - a great tool to communicate as a team.

ivata groupware is released as open source for everyone to share - see the file
LICENSE.txt for details.  It was been developed by the consulting team at ivata
- see http://www.ivata.net.

                             ---- ---- ---- ----
                             ---- BUILDING  ----
                             ---- ---- ---- ----
To build all subprojects in order of dependency, first make sure you have a
recent version of Java and Maven installed.

Of course you'll have to download and install Java and Maven first :-)
- see http://maven.apache.org/start/install.html
It has been tested with JDK 1.4.2 and Maven 1.0.2. (for Java JDK - go to
java.sun.com).

Then simply enter:
  <source>
    maven
  </source>

in the root directory (where this file is located).

You'll find the web application here:
  <source>
    package/war/target/ivatagroupware-war-{version}.war
  </source>


                             ---- ---- ---- ----
                   ---- GENERATING MORE DOCUMENTATION ----
                             ---- ---- ---- ----
ivata groupware uses Maven to generate documentation.
Follow these steps carefully:

  * install Maven v1.0.2 or higher
     - see http://maven.apache.org/start/install.html
  * go to the ivata groupware root directory (where this file is)

Then build the documentation, execute:
  <source>
    maven multiproject:site
  </source>

Now point your browser at ivatagroupware/target/docs.

