#lang scribble/manual

@(require "../../utils/notes.rkt" scribble/core scriblib/footnote)

@assn-title[1]{IntelliJ Setup}

@hyperlink[(__DIR__ "code/code.zip")]{Starter code}. Use as per directions below. 

@section*{Summary}
This page covers the following topics:

@itemlist[
    @item{Download and install the appropriate JDK version}
    @item{Download and install IntelliJ}
    @item{Organize and work with IntelliJ projects}
    @item{Work with JUnit in IntelliJ}
    @item{Incorporate code styling in IntelliJ}
]

@section{Installing JDK on your computer}

In order to run/execute Java programs, the JRE (Java Runtime Environment) must be installed on your computer. In order to develop Java programs, the JDK (Java Development ToolKit) must be installed. 

We will use Java 11 JDK in this course (the JRE can be 11 or later). 

@subsection{Checking the installed version}

You can check the version installed on your machine as follows:

@itemlist[#:style 'ordered
    @item{Open the command prompt (on Windows) or the terminal (Mac/Linux).}
    @item{Type @code{javac -version} and press Enter. This checks the JDK version.}
    @item{Type @code{java -version} and press Enter. This checks the JRE version.}
]

@subsection{Download and install JDK 11}

Please follow these instructions:

@itemlist[#:style 'ordered 
    @item{Go to @hyperlink["https://www.oracle.com/java/technologies/javase-downloads.html"]{https://www.oracle.com/java/technologies/javase-downloads.html}. If the link does not work, do a web search for "Java JDK downloads" and go to the oracle.com page in the results.}
    @item{Download the JDK for your operating system.}
    @item{Run the installer to install JDK on your computer.}
    @item{Verify the installation by checking the JDK version as described in the previous section.}
]

@section{IDE Installation}

We will use Jetbrains' IntelliJ IDEA as our integrated development environment for this course. This IDE is available in two flavors: Community Edition and Ultimate Edition. Although the latter is not free, each Northeastern student can get the Ultimate Edition for free if they use their @"@"northeastern.edu or @"@"husky.neu.edu accounts to register on Jetbrains website. We recommend the Ultimate Edition as it has a few plugins that you may find useful (the Community Edition should still be enough for all the work in this course).

Follow these instructions to download IntelliJ:

@itemlist[#:style 'ordered
    @item{Go to @hyperlink["https://account.jetbrains.com/login"]{https://account.jetbrains.com/login}.}
    @item{If you have not registered yet, use your @"@"northeastern.edu or @"@"husky.neu.edu account to register for a Jetbrains account. Follow the instructions in subsequent emails to complete the account creation.}
    @item{Login to your account, and apply for a new student license.}
    @item{Once you see the license approved in your account, you should be able to download and install IntelliJ Ultimate Edition.}
]

@section{Working on projects in IntelliJ}

Before starting to work in IntelliJ, the following are highly recommended:

@itemlist[
    @item{Create a folder somewhere on your computer that will store all your IntelliJ project folders for this course. If you are planning to use IntelliJ for several courses, group projects by course into separate folders.
    
    Unlike Eclipse, IntelliJ does not really have the notion of a "Workspace".}
    @item{Avoid using spaces in the names of IntelliJ projects.}
    @item{At any point, if you encounter bizarre error messages in Intellij even after setting up everything (e.g. not being able to find something when it should), create a new project and transfer all your files over. This is usually much faster than trying to repair an existing project.}
]

@subsection{Creating a new project in IntelliJ}

You should generally create a new project in Intellij for every assignment. You may group assignments into a single project if they progressively build the same program. 

The instructions below create a simple project called @code{BooksPersons}.

@itemlist[
    @item{Start IntelliJ.}
    @item{If opening for the first time, click on @code{New Project}. If it opened the last project for you (default behavior), click on @code{File -> New.. -> Project}.}
    @item{
        In the left tab, select @code{Java}. On the top, select the Project SDK. 
    
        If you are using IntelliJ for the first time, you will not see any options in the dropdown for the Project SDK. In this case, select @code{Add JDK}, and point it to the "bin" folder of your JDK installation. You will have to do this once per JDK installed on your computer.
        
        @image[(__DIR__ "pics/new-project-1.png")]
    }
    
    @item{Skip the next screen by pressing @code{Next}
    
    @image[(__DIR__ "pics/new-project-2.png")]
    }
    
    @item{Under @code{Project Location} navigate to the folder that will store all your projects for this course. Create a new folder in it named @code{BooksPersons} (the name of your project). @bold{In general make sure the name does not have any spaces, dots and slashes in it!}
    
        @image[(__DIR__ "pics/new-project-3.png")]
    
        Make sure that the name of the new folder matches the Project name and module name on this screen. Press @code{Finish} to create the project.
    }
    
    @item{Using Explorer or Finder, navigate to the @code{BooksPersons} folder that you just created, and ensure that there is a "src" folder, as well as a "BooksPersons.iml" file in it. The latter is an IntelliJ-specific file, that you should not explicitly edit!}
]
    



@itemlist[

    @item{Manually copy the files @code{Person.java} and @code{Book.java} to the @code{src} folder in the above project, and then verify that IntelliJ shows these files in the Project Explorer pane.
    
    @image[(__DIR__ "pics/new-project-4.png")]
    }
    
    @item{Open the files in IntelliJ by double-clicking them. You will notice there are some building errors in one of the files. 
        @itemlist[#:style 'ordered
            @item{Hover your mouse over the error line and you should see a tooltip with an error message in it. Error messages are read better by clicking on the "Problems" tab at the bottom of the window.}
            @item{When you hover over an error in the source file, a red bulb pops up. Clicking on the red bulb provides suggestions by IntelliJ on how to correct this error. @bold{Read and understand each suggestion before clicking on one of them!}}
            @item{Correct the errors.}
        ]}
]

@subsection{JUnit Testing}

We will use the JUnit testing framework to write all our tests in this course. JUnit is not part of the JDK, and therefore is likely not installed on your computer. We will be downloading JUnit from Maven.

For each project, you should put all the tests in a separate folder. This allows you to cleanly separate your code from your tests.

@itemlist[
        @item{In IntelliJ, right click on the project name on the left and select @code{New -> Directory}
        
        @image[(__DIR__ "pics/junit-1.png")]
        }
        @item{Name the folder @code{test}. Verify that the folder is shown in the project.}
        @item{Right click on the @code{test} folder and select @code{Mark Directory As -> Test Sources Root}
        
        @image[(__DIR__ "pics/junit-2.png")]
        
        Verify that the folder icon for @code{test} has turned green.}
        @item{Open the @code{Person.java} file in IntelliJ.}
        @item{Hover or click on the name of the class. A yellow IntelliJ bulb will appear to give suggestions. Click on it and select @code{Create Test}.
        
        @image[(__DIR__ "pics/junit-3.png")]
        }
        @item{Select JUnit4 as the JUnit version to use. Leave all else to their default settings and press @code{OK}. A new file @code{PersonTest.java} will be created in the test/ folder.
        
        @image[(__DIR__ "pics/junit-4.png")]
        }
        @item{The import statement in @code{PersonTest.java} will produce an error, because the IntelliJ project cannot locate JUnit files. Hover on the error, and select "Add JUnit4 to classpath" as shown.
        
        @image[(__DIR__ "pics/junit-5.png")]
        }
        @item{In the pop it will ask permission to download a version of JUnit4 from the Maven repository. Press @code{OK}. IntelliJ will now download JUnit4, put it in a folder on your computer and link your project to it.
        
        @image[(__DIR__ "pics/junit-6.png")]
        }
        @item{Verify that the error in @code{PersonTest.java} is now gone. To further confirm that JUnit has been set up correctly, go to @code{File -> Project Structure} and go to the Modules tab, and then the Dependencies tab. You should see JUnit4 as one of the dependencies.
        
        @image[(__DIR__ "pics/junit-6.png")]
        }
        @item{Copy the contents of the provided @code{PersonTest.java} file to the file you created. Now run the tests using one of the following two ways:
        @itemlist[
            @item{Click on the green double triangles next to the class declaration in the test file.}
            @item{In the Project tab on the left, right-click on @code{PersonTest.java} and select @code{Run}}
        ]
        
        One of the tests will fail. @bold{This is expected.} Note how it shows you the test failure.}
        @item{Fix this test according to the comment next to the failure, and verify that all tests pass.}
]
   
@subsection{Code styling}
IntelliJ can help you style your code so that it adheres to a pre-defined standard. Follow these steps to set up and use code styling:

@itemlist[
    @item{Select @code{File -> Settings} and then on the left side, select @code{Editor -> Code Style}
    
    @image[(__DIR__ "pics/style-1.png")]
    }
    @item{In the gear dropdown next to "Scheme", select @code{Import Scheme -> IntelliJ IDEA code XML}

    @image[(__DIR__ "pics/style-2.png")]
    }
    @item{Navigate to the xml file provided in the starter code, and rename the style if you wish.
    
    @image[(__DIR__ "pics/style-3.png")]
    }
    @item{For each project, verify that this imported style is selected (you need to import only once).}
    @item{To enforce this style:
    @itemlist[
        @item{Open the @code{Person.java} file. It has a style mistake: the opening brace after the class declaration is on a new line.}
        @item{Select @code{Code -> Reformat Code} to fix this style error.}
        @item{Repeat the same for each file in your project.}
    ]}
]

Keep in mind that IntelliJ does not automatically reformat code according to the imported style file: you must manually reformat each file. Also, the imported style file does not fix all the errors flagged by the submission server.
    
    
    
    
   
        
        







