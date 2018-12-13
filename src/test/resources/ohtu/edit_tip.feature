Feature: user can edit tips in database

Scenario: User can edit existing book
  Given book "Testikirja" exist in database
  When user is at all tips page
  Then the page should have link with text "Lisätietoja" in it
  Then the page should have link with href "/books/" in it



Scenario: User can edit existing blog
  Given blog with title "testblog1", Writer "testwriter" and description "testdesc" exists in database
  When user is at all tips page
  Then the page should have link with text "Lisätietoja" in it 
  Then the page should have link with href "/blogs/" in it
