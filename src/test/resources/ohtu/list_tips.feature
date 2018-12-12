Feature: user can list tips in database

Scenario: User can navigate to the all listing page
  Given the user is at the main page
  When the "Kaikki vinkit" link is clicked
  Then the page should have a title "Kaikki vinkit"