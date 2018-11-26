Feature: user can list books in database

Scenario: User can navigate to the books listing page
  Given the user is at the main page
  When the "Kirjat" link is clicked
  Then the page should have a title "Kirjat"

Scenario: User can navigate to the new book page
  Given the user is at the main page
  When the "Kirjat" link is clicked
  When the "uusi kirja" link is clicked
  Then the page should have a title "Uusi kirjavinkki"

Scenario: User can add book to database
  Given the user is at the new book page
  When book "TestiKirja" is created
  Then the page should list book "TestiKirja"