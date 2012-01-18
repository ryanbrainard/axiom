# Axiom
Axiom is a web-based suite of tools for learning, testing, and troubleshooting single sign-on solutions for Salesforce.com. The tools include:

 * SAML Identity Provider & Tester
 * Token-Based Authentication
 * Self Authentication Service
 * OAuth Tester

##Running Locally
To install and run Axiom locally with Jetty Runner:

    git clone git://github.com/rbrainard/axiom.git
    cd axiom
    mvn clean install
    java $JAVA_OPTS -jar target/dependency/jetty-runner.jar target/*.war

Then go to `http://localhost:8080` in your browser.

##Running on Heroku
Axiom runs natively on the [Heroku](http://heroku.com) platform.
The canonical instance is running at https://axiomsso.herokuapp.com,
but you can create your own instance if you'd like, which is helpful for forks or other experimentation.
To get started with Heroku, follow the [Heroku Quickstart](http://devcenter.heroku.com/articles/quickstart).
For _Step 4: Deploy An Application_, follow these steps:

1. Clone this repo:

    git clone git://github.com/rbrainard/axiom.git

2. Go into the directory:

    cd axiom

3. Create a new app on Heroku:

    heroku create --stack cedar

4. Push Axiom to Heroku:

    git push heroku master

5. View your new instance of Axiom in the browser:

    heroku open