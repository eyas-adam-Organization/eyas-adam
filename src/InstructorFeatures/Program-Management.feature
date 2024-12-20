Feature: Program Management
  Instructors can create and manage fitness programs.

  Scenario: Create a new fitness program
    Given the instructor is logged into the system
    And the instructor has chosen the Program Management option
    When the instructor creates a new program "program" "hard" "a b c d" "10" "C:\\Users\\Eyas\\Videos\\Icecream Screen Recorder\\Video_2024_01_05-6.webm" "C:\Users\Eyas\Pictures\Camera Roll\\WIN_20230926_09_34_09_Pro.jpg" "C:\\Users\\Eyas\\Desktop\\New_folder\\0-Mips Review.pdf" "20".
    Then the program should be added to the available programs list

  Scenario: Trying to Create existing an  fitness program
    Given the instructor is logged into the system
    And the instructor has chosen the Program Management option
    When the instructor creates an existing program "program1" "hard" "a b c d" "10" "C:\\Users\\Eyas\\Videos\\Icecream Screen Recorder\\Video_2024_01_05-6.webm" "C:\Users\Eyas\Pictures\Camera Roll\\WIN_20230926_09_34_09_Pro.jpg" "C:\\Users\\Eyas\\Desktop\\New_folder\\0-Mips Review.pdf" "20".
    Then the system should display an error message


  Scenario: Update an existing fitness program
    Given the instructor is logged into the system
    And the instructor has chosen the Program Management option
    And there is an existing program
    When the instructor updates the details of an existing program "program2" "hard" "a b c d" "10" "C:\\Users\\Eyas\\Videos\\Icecream Screen Recorder\\Video_2024_01_05-6.webm" "C:\Users\Eyas\Pictures\Camera Roll\\WIN_20230926_09_34_09_Pro.jpg" "C:\\Users\\Eyas\\Desktop\\New_folder\\0-Mips Review.pdf" "20".
    Then the changes should be saved successfully

  Scenario: Update a non-existent fitness program
    Given the instructor is logged into the system
    And the instructor has chosen the Program Management option
    When the instructor updates the details of program that does not existent "programNOT" "hard" "a b c d" "10" "C:\\Users\\Eyas\\Videos\\Icecream Screen Recorder\\Video_2024_01_05-6.webm" "C:\Users\Eyas\Pictures\Camera Roll\\WIN_20230926_09_34_09_Pro.jpg" "C:\\Users\\Eyas\\Desktop\\New_folder" "20".
    Then the system should display an error message

  Scenario: Delete an existing fitness program
    Given the instructor is logged into the system
    And the instructor has chosen the Program Management option
    And there is an existing program
    When the instructor deletes the details of an existing program  "program3".
    Then the program should be removed successfully

  Scenario: Delete a non-existent fitness program
    Given the instructor is logged into the system
    And the instructor has chosen the Program Management option
    When the instructor deletes the details of program that does not existent "programNOT".
    Then the system should display an error message

  Scenario Outline:  Create with errors based on which file has an issue
    Given I have a video file path "<videoPath>"
    And I have an image file path "<imagePath>"
    And I have a document file path "<documentPath>"
    When I pass these file paths to the create function "<title>"
    Then the function returns an error for the "<fileWithError>" file
    Examples: Errors for each file type
      | videoPath                                                 | imagePath                                   | documentPath                              | fileWithError                                                       | title       |
      | C:\\Users\\Eyas\\Desktop\\software\\L6.mp4                | C:\\Users\\Eyas\\Desktop\\810838.png        | C:\\Users\\Eyas\\Desktop\\OS.pdf          | video/wrong file                                                    | programNEW1 |
      | C:\Users\\Eyas\\Desktop\\New_folder\\0-Mips Review.pdf    | C:\\Users\\Eyas\\Desktop\\810838.png        | C:\\Users\\Eyas\\Desktop\\OS.pdf          | video/wrong extension                                               | programNEW2 |
      | C:\\Users\\Eyas\\Desktop\\software\\L1.mp4                | C:\\Users\\Eyas\\Desktop\\810835.png        | C:\\Users\\Eyas\\Desktop\\OS.pdf          | image/wrong file                                                    | programNEW3 |
      | C:\\Users\\Eyas\\Desktop\\software\\L2.mp4                | C:\\Users\\Eyas\\Desktop\\names.txt         | C:\\Users\\Eyas\\Desktop\\OS.pdf          | image/wrong extension                                               | programNEW4 |
      | C:\\Users\\Eyas\\Desktop\\software\\L3.mp4                | C:\\Users\\Eyas\\Desktop\\810838.png        | C:\\Users\\Eyas\\Desktop\\name.txt        | doc/wrong file                                                      | programNEW5 |
      | C:\\Users\\Eyas\\Desktop\\software\\L4.mp4                | C:\\Users\\Eyas\\Desktop\\810838.png        | C:\\Users\\Eyas\\Desktop                  | doc/wrong file                                                      | programNEW6 |
      | C:\\Users\\Eyas\\Desktop\\software\\L6.mp4                | C:\\Users\\Eyas\\Desktop\\810835.png        | C:\\Users\\Eyas\\Desktop\\OS.pdf          | video/wrong file & image/wrong file                                 | programNEW7 |
      | C:\\Users\\Eyas\\Desktop\\software\\L.mp4                 | C:\\Users\\Eyas\\Desktop\\names.txt         | C:\\Users\\Eyas\\Desktop\\name.txt        | video/wrong file & image/wrong extension & doc/wrong file      | programNEW8 |

  Scenario Outline:  update with errors based on which file has an issue
    Given I have a video file path "<videoPath>"
    And I have an image file path "<imagePath>"
    And I have a document file path "<documentPath>"
    When I pass these file paths to the update function "<title>"
    Then the function returns an error for the "<fileWithError>" file
    Examples: Errors for each file type
      | videoPath                                                 | imagePath                                   | documentPath                              | fileWithError                                                       | title       |
      | C:\\Users\\Eyas\\Desktop\\software\\L6.mp4                | C:\\Users\\Eyas\\Desktop\\810838.png        | C:\\Users\\Eyas\\Desktop\\OS.pdf          | video/wrong file                                                    | program4    |
      | C:\Users\\Eyas\\Desktop\\New_folder\\0-Mips Review.pdf    | C:\\Users\\Eyas\\Desktop\\810838.png        | C:\\Users\\Eyas\\Desktop\\OS.pdf          | video/wrong extension                                               | program4    |
      | C:\\Users\\Eyas\\Desktop\\software\\L1.mp4                | C:\\Users\\Eyas\\Desktop\\810835.png        | C:\\Users\\Eyas\\Desktop\\OS.pdf          | image/wrong file                                                    | program5    |
      | C:\\Users\\Eyas\\Desktop\\software\\L2.mp4                | C:\\Users\\Eyas\\Desktop\\names.txt         | C:\\Users\\Eyas\\Desktop\\OS.pdf          | image/wrong extension                                               | program5    |
      | C:\\Users\\Eyas\\Desktop\\software\\L3.mp4                | C:\\Users\\Eyas\\Desktop\\810838.png        | C:\\Users\\Eyas\\Desktop\\name.txt        | doc/wrong file                                                      | program5    |
      | C:\\Users\\Eyas\\Desktop\\software\\L4.mp4                | C:\\Users\\Eyas\\Desktop\\810838.png        | C:\\Users\\Eyas\\Desktop                  | doc/wrong file                                                      | program6    |
      | C:\\Users\\Eyas\\Desktop\\software\\L6.mp4                | C:\\Users\\Eyas\\Desktop\\810835.png        | C:\\Users\\Eyas\\Desktop\\OS.pdf          | video/wrong file & image/wrong file                                 | program6    |
      | C:\\Users\\Eyas\\Desktop\\software\\L.mp4                 | C:\\Users\\Eyas\\Desktop\\names.txt         | C:\\Users\\Eyas\\Desktop\\name.txt        | video/wrong file & image/wrong extension & doc/wrong file           | program7 |
