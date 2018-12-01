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

Scenario: User is redirected to main page when trying to view book with id that does not exist in database
  Given the user is at the main page
  When requesting individual book page with id "-1"
  Then the page should have a title "Kirjat"

Scenario: User can view individual book page
  Given book "Testikirja" exist in database
  When requesting individual book page with id "1"
  Then the user should be at page "books/1"