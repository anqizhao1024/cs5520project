## Assigment 4: Adding Persistence

### Github Link:
[A4 ToDoList (Version2, apk under the same folder)](https://github.com/anqizhao1024/cs5520project/tree/main/A4)

### Screenshot:
<img src="https://raw.githubusercontent.com/anqizhao1024/cs5520project/gh-pages/_pics/A4-1.PNG" width="250"/> <img src="https://raw.githubusercontent.com/anqizhao1024/cs5520project/gh-pages/_pics/A4-2.PNG" width="250"/> 


### What I Did：
I've added a database to persist the data by using Android native sqlite, and debug towns of issues when I first try to use a ViewModel to jump back and forth between two activities. I've also fixed display issues about components I initially used.

Finally, I gave up on fighting with Google Play Store and decided to include my APKs in my GitHub folder for each release.


### What I Learned：
It was such a pain in the ass when debugging with Android apps, and it will be wise to do yourself a favor -- use components as simple as possible.

I've also learned how to use an adapter, and data objects need to be serialized before they can be transferred.

Transferring value between pages, and jumping between pages, then destroying pages after being used are other important experiences I've gained in this release.

Besides, I've practiced more about how to use SQL to create a database then add, delete, modify and check data in the database. It has been a long time since the last time I deal with them, so it's definitely some great practices!
