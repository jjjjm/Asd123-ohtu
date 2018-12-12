Feature: user can add tip of book

Scenario: User can give required information
  Given the user is at the main page
  When the "Kirjat" link is clicked
  When the "uusi kirja" link is clicked
  Then the page should have a title "Uusi kirjavinkki"

Scenario: User can add book to database
  Given the user is at the new book page
  When book "TestiKirja" is created
  Then the page should list book "TestiKirja"
    