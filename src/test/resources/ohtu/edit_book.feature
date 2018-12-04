Feature: user can edit books in database

Scenario: User can edit existing book
  Given book "Testikirja" exist in database
  When requesting individual book page with id "1"
  When changing book title to "Testikirja1"
  Then the page should list book "Testikirja1"

Scenario: User can delete existing book
  Given book "Testikirja" exists in database
  When requesting individual book page with id "1"
  When button "Poista" is pressed
  Then the page should not list book "Testikirja"