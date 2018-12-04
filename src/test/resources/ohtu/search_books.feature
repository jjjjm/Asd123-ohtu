Feature: user can search books in database

Scenario: User can search book without keyword
  Given the user is at the main page
  And book "HackersCookBook" exist in database
  When the "Kirjat" link is clicked
  When user searches books by keyword ""
  Then the page should list book "HackersCookBook"

Scenario: User can search book with keyword and get match
  Given the user is at the main page
  And book "HackersCookBook" exist in database
  When the "Kirjat" link is clicked
  When user searches books by keyword "Hack"
  Then the page should list book "HackersCookBook"

Scenario: User can search book with keyword and find no match
  Given the user is at the main page
  And book "HackersCookBook" exist in database
  When the "Kirjat" link is clicked
  When user searches books by keyword "kirja"
  Then the page should not list book "HackersCookBook"