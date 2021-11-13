## Lesson 2_1

### Screenshot:
<img src="https://raw.githubusercontent.com/anqizhao1024/cs5520project/gh-pages/_pics/lesson2_1_screenshot1.PNG" width="250"/>
<img src="https://raw.githubusercontent.com/anqizhao1024/cs5520project/gh-pages/_pics/lesson2_1_screenshot2.PNG" width="250"/>
<img src="https://raw.githubusercontent.com/anqizhao1024/cs5520project/gh-pages/_pics/lesson2_1_screenshot3.PNG" width="250"/>


### What I Learned：

How to use intent to start another activity from the current one.

How to use bundles to pass information between activities.

## Lesson 2_2

### Screenshot:
<img src="https://raw.githubusercontent.com/anqizhao1024/cs5520project/gh-pages/_pics/lesson2_2_screenshot1.PNG" width="250"/>
<img src="https://raw.githubusercontent.com/anqizhao1024/cs5520project/gh-pages/_pics/lesson2_2_screenshot2.PNG" width="400"/>


### Homework:

#### Q1: If you run the homework app before implementing onSaveInstanceState(), what happens if you rotate the device?

A1: The counter is reset to 0, but the contents of the EditText is preserved.

#### Q2: What Activity lifecycle methods are called when a device-configuration change (such as rotation) occurs?

A2: Android shuts down your Activity by calling onPause(), onStop(), and onDestroy(), and then starts it over again, calling onCreate(), onStart(), and onResume().

#### Q3: When in the Activity lifecycle is onSaveInstanceState() called?

A3: onSaveInstanceState() is called before the onDestroy() method.

#### Q4: Which Activity lifecycle methods are best to use for saving data before the Activity is finished or destroyed?

A4: onDestroy()

### What I Learned：

How to save data by using onSaveInstanceState in bundles. 

How do different lifecycles work in activities.

## Lesson 2_3

### Screenshot:
<img src="https://raw.githubusercontent.com/anqizhao1024/cs5520project/gh-pages/_pics/lesson2_3_screenshot1.PNG" width="250"/>
<img src="https://raw.githubusercontent.com/anqizhao1024/cs5520project/gh-pages/_pics/lesson2_3_screenshot2.PNG" width="250"/>
<img src="https://raw.githubusercontent.com/anqizhao1024/cs5520project/gh-pages/_pics/lesson2_3_screenshot3.PNG" width="250"/>
<img src="https://raw.githubusercontent.com/anqizhao1024/cs5520project/gh-pages/_pics/lesson2_3_screenshot4.PNG" width="250"/>

### What I Learned：

How to use intent to lead user to third-part apps from current app.

How to send/share current information to other apps.
