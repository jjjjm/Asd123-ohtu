Feature: user can edit blogs in database

Scenario: User can edit existing blog
  Given blog with title "testblog1", Writer "testwriter" and description "testdesc" exists in database
  When requesting individual blog page with id "1"
  When changing blog title to "Testiblog1"
  Then the page should list blog "Testiblog1"

Scenario: User can delete existing blog
  Given blog with title "testblog1", Writer "testwriter" and description "testdesc" exists in database
  When requesting individual blog page with id "1"
  When button "Poista" is pressed
  Then the page should not list book "testiblog1"