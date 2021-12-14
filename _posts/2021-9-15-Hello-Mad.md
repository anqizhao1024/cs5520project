# Lesson 1_1

### Github Link:
[Lesson 1_1](https://github.com/anqizhao1024/cs5520project/tree/main/lesson%201_1)

## Screenshot:
<img src="https://raw.githubusercontent.com/anqizhao1024/cs5520project/gh-pages/_pics/lesson1_1_screenshot.PNG" width="250"/>

## Homework:

### Q1: What is the name of the layout file for the main activity?
A1: activity_main.xml

### Q2: What is the name of the string resource that specifies the application's name?
A2: app_name

### Q3: Which tool do you use to create a new emulator?
A3: AVD Manager

### Q4: Assume that your app includes this logging statement: 
	Log.i("MainActivity", "MainActivity layout is complete");
	You see the statement "MainActivity layout is complete" in the Logcat pane if the Log level menu is set to which of the following? 
A4: Verbose, Debug

## What I Learned：

How to create a most basic Android app and how to use Android studio.

Searched a little bit of what is Kotlin but decided to use Java in my own work.

Learned how to insert pictures on pages and display as expected.

# Lesson 1_2A

### Github Link:
[Lesson 1_2A](https://github.com/anqizhao1024/cs5520project/tree/main/lesson%201_2A)

## Screenshot:
<img src="https://raw.githubusercontent.com/anqizhao1024/cs5520project/gh-pages/_pics/lesson1_2A_screenshot.PNG" width="250"/>

## What I Learned：

How to create a button and attach features on the button. 

How to change attributes of a component.

# Lesson 1_2B

### Github Link:
[Lesson 1_2B](https://github.com/anqizhao1024/cs5520project/tree/main/lesson%201_2B)

## Screenshot:
<img src="https://raw.githubusercontent.com/anqizhao1024/cs5520project/gh-pages/_pics/Lesson1_2B_screenshot_01.PNG" width="250"/>

<img src="https://raw.githubusercontent.com/anqizhao1024/cs5520project/gh-pages/_pics/Lesson1_2B_screenshot_03.PNG" width="400"/>

## Homework:

### Q1: Which two layout constraint attributes on the Zero Button position it vertically equal distance between the other two Button elements? 
A1: app:layout_constraintBottom_toTopOf="@+id/button_count"
    app:layout_constraintTop_toBottomOf="@+id/button_toast"
    
### Q2: Which layout constraint attribute on the Zero Button positions it horizontally in alignment with the other two Button elements?
A2: app:layout_constraintLeft_toLeftOf="parent"

### Q3: What is the correct signature for a method used with the android:onClick XML attribute?
A3: public void callMethod(View view)

### Q4: Which of the following techniques is more efficient to use within this handler to change the Button element's background color? 
A4: Use findViewById to find the Count Button. Assign the result to a View variable, and then use setBackgroundColor().
 
## What I Learned：

How to set the vertical and horizontal view of the app to be the same structure and won't mess up when change direction.

How to change the view directly in the XML code.

# Lesson 1_3

### Github Link:
[Lesson 1_3](https://github.com/anqizhao1024/cs5520project/tree/main/lesson%201_3)

## Screenshot:
<img src="https://raw.githubusercontent.com/anqizhao1024/cs5520project/gh-pages/_pics/Lesson1_3_screenshot.PNG" width="250"/>

## Homework:

### Q1: How many views can you use within a ScrollView?
A1: One view or one view group

### Q2: Which XML attribute do you use in a LinearLayout to show views side by side?
A2: android:orientation="horizontal"

### Q3: Which XML attribute do you use to define the width of the LinearLayout inside the scrolling view? 
A3: android:layout_width="match_parent"

## What I Learned：

How to add a scroll view and divide it to different sections allows different scroll contents.
