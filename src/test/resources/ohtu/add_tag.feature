Feature: user can add new tags to database

Scenario: User can navigate to new tag page
  Given the user is at the main page
  When requesting page "newtag"
  Then the page should have a title "Uusi tagi"

Scenario: User can add tag to database
  Given the user is at the main page
  When requesting page "newtag"
  When new tag with name "testitagi" is inserted
  Then the page should have a title "Uusi tagi"