Feature: Approval of articles, tips, recipes from instructors
  Some instructors can share some wellness articles, health tips and some healthy recipes; the admin has first to approve them before publishing them.

  Scenario: Admin received an article that is not appropriate
    Given admin rejected
    When admin received "article" "How to skip some exercises: ..." from instructor "sameer_official"
    Then do not save the "article" "How to skip some exercises: ..." from instructor "sameer_official"

  Scenario: Admin received a bad health tip
    Given admin rejected
    When admin received "tip" "Never drink water after a set ..." from instructor "sameer_official"
    Then do not save the "tip" "Never drink water after a set ..." from instructor "sameer_official"

  Scenario: Admin received a not-so-healthy recipe
    Given admin rejected
    When admin received "recipe" "How to make 20K-calorie chocolate bar ..." from instructor "sameer_official"
    Then do not save the "recipe" "How to make 20K-calorie chocolate bar ..." from instructor "sameer_official"

  Scenario: Admin received an good article
    Given admin approved
    When admin received "article" "How to properly do squats with no pain: ..." from instructor "sameer_official"
    Then save the "article" "How to properly do squats with no pain: ..." from instructor "sameer_official"

  Scenario: Admin received a good health tip
    Given admin approved
    When admin received "tip" "Always bring your own water bottles (to save some cash)" from instructor "sameer_official"
    Then save the "tip" "Always bring your own water bottles (to save some cash)" from instructor "sameer_official"

  Scenario: Admin received a good recipe
    Given admin approved
    When admin received "recipe" "How to make a kito-friendly salad ... " from instructor "sameer_official"
    Then save the "recipe" "How to make a kito-friendly salad ... " from instructor "sameer_official"