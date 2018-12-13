Feature: user can add a description for a book

Scenario: User can add a description for a book
  Given book "Testikirja" exist in database
  When requesting individual book page with id "1"
  When adding description "testikuvaus"
  Then the user should be at page "books" 
  Then "testikuvaus" is shown