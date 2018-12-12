Feature: user can add blog into database

  Scenario: User can navigate to the blog listing page
    Given the user is at the main page
    When the "Blogit" link is clicked
    Then the page should have a title "Blogi-vinkit"

  Scenario: User can navigate to the new blog page
    Given the user is at the main page
    When the "Blogit" link is clicked
    When the "uusi blogi" link is clicked
    Then the page should have a title "Uusi blogivinkki"

  Scenario: User can add blog to database
    Given the user is at the new blog page
    When blog with title "testblog1", Writer "testwriter" and description "testdesc" is created
    Then the page should list book "testblog1"