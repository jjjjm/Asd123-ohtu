Feature: user can search blogs in database

Scenario: User can search blog without keyword
  Given the user is at the main page
  And blog with title "testblog1", Writer "testwriter" and description "testdesc" exists in database
  When the "Blogit" link is clicked
  When user searches blogs by keyword ""
  Then the page should list blog "testblog1"

Scenario: User can search blog with keyword and get match
  Given the user is at the main page
  And blog with title "testblog1", Writer "testwriter" and description "testdesc" exists in database
  When the "Blogit" link is clicked
  When user searches blogs by keyword "test"
  Then the page should list blog "testblog1"

Scenario: User can search blog with keyword and find no match
  Given the user is at the main page
  And blog with title "testblog1", Writer "testwriter" and description "testdesc" exists in database
  When the "Blogit" link is clicked
  When user searches blogs by keyword "kirja"
  Then the page should not list book "testblog1"