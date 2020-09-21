# Requirements
> When the app is started, the user is presented with the main menu, which allows the user to (1) enter current job details, (2) enter job offers, (3) adjust the comparison settings, or (4) compare job offers (disabled if no job offers were entered yet).  

This requirement is satisfied through the Menu and Job classes. The Menu class allows a user to add or modify an instance of a Job class and indicate whether it is the user's current job or a job offer through the addJob and editJob methods. The user can also adjust the comparison settings through the setWeights class. Lastly, the compareJobs function allows the user to compare job offers.  

> When choosing to enter current job details, a user will:  
>     Be shown a user interface to enter (if it is the first time) or edit all of the details of their current job, which consist of:  
>         Title  
>         Company  
>         Location (entered as city and state)  
>         Overall cost of living in the location (expressed as an index)  
>         Commute time (round-trip and measured in hours or fraction thereof)  
>         Yearly salary  
>         Yearly bonus  
>         Retirement benefits (as percentage matched)  
>         Leave time (vacation days and holiday and/or sick leave, as a single overall number of days)  
>     Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.  

This requirement is satisfied through the implementation of the Job and JobList classes. The Job class includes attributes that cover all of the details mentioned above as well as methods to access them. In the Menu class, the user can select to either save the class they have entered or to cancel it with the saveJob and cancelJob methods, which trace to the JobList class. Both methods live within the Menu class and will be implemented through GUI elements, so they will automatically return to the main menu.  

> When choosing to enter job offers, a user will:  
>     Be shown a user interface to enter all of the details of the offer, which are the same ones listed above for the current job.  
>     Be able to either save the job offer details or cancel.  
>     Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the offer with the current job details (if present).  

This requirement is satisfied through the implementation of the Job class. The Job class includes attributes that cover all of the details mentioned above as well as methods to access them. Specific to job offers is the current attribute that allows the user to indicate that the entered Job is a job offer by specifying "false" for the current attribute. Entering another offer or returning to the main menu will be satisfied through GUI elements that will either call another instance of the addJob method or simply end the job entry interface and return to the main menu. Lastly, comparing the offer with the current job details is satisfied through the compareCurrent method will automatically fetch the currently submitted job and compare it with the user's current job, if entered.  

> When adjusting the comparison settings, the user can assign integer weights to:  
>   Commute time  
>   Yearly salary  
>   Yearly bonus  
>   Retirement benefits  
>   Leave time  
> If no weights are assigned, all factors are considered equal.  

This requirement is satisfied through the implementation of the Weights class which includes the integer weights described above as attributes. The user can assign the weights by calling the setWeights function within the Menu class. If no weights are assigned, the default value of all factors is set to 0.2.  

> When choosing to compare job offers, a user will:  
>   Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated.  
>   Select two jobs to compare and trigger the comparison.  
>   Be shown a table comparing the two jobs, displaying, for each job:  
>       Title  
>       Company  
>       Location  
>       Commute time  
>       Yearly salary adjusted for cost of living  
>       Yearly bonus adjusted for cost of living  
>       Retirement benefits (as percentage matched)  
>       Leave time  
>   Be offered to perform another comparison or go back to the main menu.  

This requirement is satisfied through the implementation of the RankingList and JobList classes. The RankingList class holds an array of the submitted jobs, ranked from best to worst, and shows the user through the displayRanking function. The current job will be clearly indicated through the displayRanking function applying special formatting to the Job with its current attribute marked as "true." The JobList class holds an array of all the jobs entered. The user will specify the two jobs the user wishes to compare and the JobList class will output the attributes in a table for comparison with the displayComparison method. Both RankingList and JobList classes are called through the compareJobs method in the Menu class. A user will be able to perform another comparison or to return to the main menu through GUI elements that will either query another instance of compareJobs for performing another comparison or simply end the current displayRanking or displayComparison method to return to the menu.  

> When ranking jobs, a job’s score is computed as the weighted sum of:  
>   
> AYS + AYB + (RBP * AYS) + (LT * AYS / 260) - (CT * AYS / 8)  
>   
> where:  
>   
> AYS = yearly salary adjusted for cost of living  
> AYB = yearly bonus adjusted for cost of living  
> RBP = retirement benefits percentage  
> LT = leave time  
> CT = commute time  
> The rationale for the CT subformula is:  
>   value of an employee hour = (AYS / 260) / 8  
>   commute hours per year = CT * 260  
>   therefore commute-time cost = CT * 260 * (AYS / 260) / 8 = CT * AYS / 8  
>   
> For example, if the weights are 2 for the yearly salary, 2 for the retirement benefits, and 1 for all other factors, the score would be computed as:  
> 2/7 * AYS + 1/7 * AYB + 2/7 * (RBP * AYS) + 1/7 * (LT * AYS / 260) - 1/7 (CT * AYS / 8)  

This requirement is satisfied through the implementation of the JobRankingAlgorithm function. This function takes in the integer weights from the Weights class through the getWeights method and the jobs to be compared from the JobList class through the getJobs function. The JobRankingAlgorithm will perform the algorithm as described above and return an array of the jobs sorted in the appropriate ranking.  

> The user interface must be intuitive and responsive.  

This requirement is not addressed in the UML design document or in this design description, but will be handled in its entirety by the implementation of the GUI.  

> The performance of the app should be such that users do not experience any considerable lag between their actions and the response of the app.  

This requirement is partially addressed in the UML design document by having flexible, efficient software design that minimizes unnecessary bloat within the app. In addition, the implementation of the algorithm will be streamlined to reduce any unnecessary lag. The requirement will also be addressed by the implementation of the GUI and ensuring all components are responsive and high-performing.  

> For simplicity, you may assume there is a single system running the app (no communication or saving between devices is necessary).  

This requirement is addressed in the UML design document by the class, methods, operations, and relationships assuming that a single system is running the app. No communication to other devices or hardware components are included. This assumption is satisfied through the proposed implementation in the UML design document.