Feature: user can list blogs in database

  Scenario: User can navigate to the blog listing page
    Given the user is at the main page
    When the "Blogit" link is clicked
    Then the page should have a title "Blogi-vinkit"

  Scenario: User can navigate to the new blog page
    Given the user is at the main page
    When the "Blogit" link is clicked
    When the "uusi blogivinkki" link is clicked
    Then the page should have a title "Uusi blogivinkki"

  Scenario: User can add blog to database
    Given the user is at the new blog page
    When blog with title "testblog1", Writer "testwriter" and description "testdesc" is created
    Then the page should list book "testblog1"

  Scenario: User is redirected to main page when trying to view blog with id that does not exist in database
    Given the user is at the blog listing page
    When requesting individual blog page with id "-1"
    Then the page should have a title "Blogi-vinkit"

  Scenario: User can view individual blog page
    Given blog with title "testblog1", Writer "testwriter" and description "testdesc" exists in database
    And the user is at the blog listing page
    When the "Lisätietoja / muokkaus" link is clicked
    Then the page should have a title "Blogivinkki"
