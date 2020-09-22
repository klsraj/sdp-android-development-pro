# Assignment 5 UML Design Description
## 1. When the app is started, the user is presented with the main menu, which allows the user to (1) enter current job details, (2) enter job offers, (3) adjust the comparison settings, or (4) compare job offers (disabled if no job offers were entered yet1).
* The main menu has 4 arrows that allow users to enter current job details, job offers, adjust comparison settings, and compare offers. The diagram doesn't specify that offers must be entered before comparison but it's assumed that users will understand that if no data is entered, then the app will not function as intended.

## 2. When choosing to enter current job details, a user will: a. Be shown a user interface to enter (if it is the first time) or edit all of the details of their current job, which consist of: i. Title ii. Company iii. Location (entered as city and state) iv. Overall cost of living in the location (expressed as an index) v. Commute time (round-trip and measured in hours or fraction thereof) vi. Yearly salary vii. Yearly bonus viii. Retirement benefits (as percentage matched) ix. Leave time (vacation days and holiday and/or sick leave, as a single overall number of days) b. Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.
* Current job details are entered. There is an option to save the data and an option to return back to the main menu.

## 3. When choosing to enter job offers, a user will: a. Be shown a user interface to enter all of the details of the offer, which are the same ones listed above for the current job. b. Be able to either save the job offer details or cancel. c. Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the offer with the current job details (if present).
* Users can enter as many offers as they like, compare offers, check if current job info exists, and return to the main menu.

## 4. When adjusting the comparison settings, the user can assign integer weights to: a. Commute time b. Yearly salary c. Yearly bonus d. Retirement benefits e. Leave time 
* From the main menu, users can directly go and modify the comparison settings. They can also modify the comparison settings while they're comparing offers (no need to go back to main menu and lose track of the job offer data being entered).

## 5. When choosing to compare job offers, a user will: a. Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated. b. Select two jobs to compare and trigger the comparison. c. Be shown a table comparing the two jobs, displaying, for each job: i. Title ii. Company iii. Location iv. Commute time v. Yearly salary adjusted for cost of living vi. Yearly bonus adjusted for cost of living vii. Retirement benefits (as percentage matched) viii. Leave time d. Be offered to perform another comparison or go back to the main menu.
* If multiple offers exist, this class will sort them from best to worst. Users have the option to compare 2 jobs at a time if they desire to narrow down their choices and compare each attribute individually.

## 6. When ranking jobs, a job’s score is computed as the weighted sum of: AYS + AYB + (RBP * AYS) + (LT * AYS / 260) - (CT * AYS / 8) 
* The job score is calculated based on input given from the comparison settings. The class takes individual weights as input and returns a job score for each job offer based on the provided formula. 

## 7. The user interface must be intuitive and responsive.
* So far, the design of the interface seems to be simple and user-friendly. 

## 8. The performance of the app should be such that users do not experience any considerable lag between their actions and the response of the app.
* The classes are designed in a way that minimizes lags and clicks. For example, users don't have to return to main menu if they want to change comparison settings while they're entering or comparing offers. 

## 9. For simplicity, you may assume there is a single system running the app (no communication or saving between devices is necessary).
* No communication occurs with other systems. Everything is done within the app. 