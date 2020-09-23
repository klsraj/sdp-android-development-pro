1.	When the app is started, the user is presented with the main menu, which allows the user to 
(1) enter current job details, 
(2) enter job offers, 
(3) adjust the comparison settings, or (4) compare job offers (disabled if no job offers were entered yet ).  

To realize this requirement, Main menu consist of methods of enter current job details(), enter job offers(), 
adjust the coparison settings() and compare job offers are created(). Each method correspond a class.


2.	When choosing to enter current job details, a user will:
a.	Be shown a user interface to enter (if it is the first time) or edit all of the details of their current job, which consist of:
i.	Title
ii.	Company
iii.	Location (entered as city and state)
iv.	Overall cost of living in the location (expressed as an index)
v.	Commute time (round-trip and measured in hours or fraction thereof)
vi.	Yearly salary
vii.	Yearly bonus
viii.	Retirement benefits (as percentage matched)
ix.	Leave time (vacation days and holiday and/or sick leave, as a single overall number of days)
b.	Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.

To realize this requirement, current job details class was create with all the attributes above, and methods of edit(), save(),cancel() 
were created for the processing of the job details document. And return to the main menu() is for realize the function of going back to main menu.

3.	When choosing to enter job offers, a user will:
a.	Be shown a user interface to enter all of the details of the offer, which are the same ones listed above for the current job.
b.	Be able to either save the job offer details or cancel.
c.	Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the offer with the current job details (if present).


Job offer class was create to fulfill the requirement with all the attributes above. And methods of enterAnotherOffer(),compareCurrentJob() were 
created for comparing and edit new offer.

4.	When adjusting the comparison settings, the user can assign integer weights to:
a.	Commute time
b.	Yearly salary
c.	Yearly bonus
d.	Retirement benefits
e.	Leave time
If no weights are assigned, all factors are considered equal


Weight class was created with all the attributes above to meet that requirement.


5.	When choosing to compare job offers, a user will:
a.	Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated.
b.	Select two jobs to compare and trigger the comparison.
c.	Be shown a table comparing the two jobs, displaying, for each job:
i.	Title
ii.	Company
iii.	Location
iv.	Commute time
v.	Yearly salary adjusted for cost of living
vi.	Yearly bonus adjusted for cost of living
vii.	Retirement benefits (as percentage matched)
viii.	Leave time
d.	Be offered to perform another comparison or go back to the main menu.


Compare job offer class was built for comparing job offer with attributes of title and company. A method called compareJobOffer was set up to compare jobs.

6.	When ranking jobs, a job’s score is computed as the weighted sum of:

AYS + AYB + (RBP * AYS) + (LT * AYS / 260) - (CT * AYS / 8)

where:

AYS = yearly salary adjusted for cost of living
AYB = yearly bonus adjusted for cost of living
RBP = retirement benefits percentage
LT = leave time
CT = commute time
The rationale for the CT subformula is:
a.	value of an employee hour = (AYS / 260) / 8
b.	commute hours per year = CT * 260
c.	therefore commute-time cost = CT * 260 * (AYS / 260) / 8 = CT * AYS / 8

For example, if the weights are 2 for the yearly salary, 2 for the retirement benefits, and 1 for all other factors, the score would be computed as:
2/7 * AYS + 1/7 * AYB + 2/7 * (RBP * AYS) + 1/7 * (LT * AYS / 260) - 1/7 (CT * AYS / 8)

Rank class was created which use the weight class and the attribute of job offers to perform the ranking function. A method called rank() was 
built up for processing that.



7.	The user interface must be intuitive and responsive.
User can look at the main menu and choose the correspond funtion in the menu, and each method is responsive with editting and canneling method. 
And you can go back to the main menu whenver you want.

8.	The performance of the app should be such that users do not experience any considerable 
lag between their actions and the response of the app.

This is no lag between the acitions and response because once each method was called it will generate a response afterwards.

9.	For simplicity, you may assume there is a single system running the app (no communication or saving between devices is necessary).
There is only one system running the app, no need to build up relationship between devices

