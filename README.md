Hi,

Here is an example of clean architecture.

Please click the Floating Action Button to go to the Create Pinball Match screen, and then play around. See PinballMatchCalculatorTest, to see that it works.

Pros:
* Clean Architecture; presentation layer, domain layer, data layer, architectural reactive approach
* * My approach draws influence from Fernando Cejas.
* Testing
* * Testing pyramid. Tests for each layer. Mockito. 
* * Feel free to run all of my tests across the presentation, domain and data layer. They all pass.
* * I considered a number of edge cases missed in the specification, for example: missing an entire frame, etc.
* RxJava (I used a flatmap) to process the calculation of the pinball match point total
* gradle3
* Recyclerview / app compat. Coordinator layout to keep fab above keyboard
* I put Null pointer checks in places, and used isAdded() in the fragment/presenter interaction, to show I appreciate defensive coding practices. :)


Cons:
* UI does not look good. I spent very little time on it.
* Data layer not done. So everything is 'in memory'. I use mock objects as results from the data layer.
* * As the assignment had no network connectivity requirements, I could not show off mockwebserver; which I am a fan of.
* * There is some unused code in the data layer. Please ignore them ;) I didn't feel like refactoring it all out from the dependency injections, etc.
* I used arrays for PinballMatchCalculator, and that area of the code really needs a clean up (decomposed into methods etc)

Notes:
* Invalid pinball matches return -1.

Regards,
Bertrand
