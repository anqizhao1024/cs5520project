## Lesson 2_1

### Screenshot:

### What I Learned：

## Lesson 2_2

### Screenshot:

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

## Lesson 2_3

### Screenshot:

### What I Learned：
