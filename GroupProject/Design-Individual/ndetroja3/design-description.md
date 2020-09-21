The following section describes the design created based on the requirement given in assingment 5. Each requirement are shown below and has decription to explain how the design would cover the requirements. 

1.  When the app is started, the user is presented with the main menu, which allows the user to (1) enter current job details, (2) enter job offers, (3) adjust the comparison settings, or (4) compare job offers (disabled if no job offers were entered yet).
>This requirement is not demonstrated in design as it will be handled as part of GUI
    
2.  When choosing to enter current job details, a user will:
    
	a.  Be shown a user interface to enter (if it is the first time) or edit all of the details of their current job, which consist of:
  

	b.  Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.
    
 > UI will provide interface for user to enter job details and make decision if they want to save or cancel, if user decide to save the details "Job" class has all the attributes required to save the job details.

3.  When choosing to enter job offers, a user will:
   
	a.  Be shown a user interface to enter all of the details of the offer, which are the same ones listed above for the current job.
    
	b.  Be able to either save the job offer details or cancel.
    
	c.  Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the offer with the current job details (if present).

> Similar to above UI will provide interface to store job offers, and "offeredJobList" attribute of User class will store the details of all job offered.    

4.  When adjusting the comparison settings, the user can assign integer weights to:
   
If no weights are assigned, all factors are considered equal.
> "Weight" class has all the attributes to store comparison settings. When attribute of Weight class is created constructor will initialize all the integer weights with 1 value. 
5.  When choosing to compare job offers, a user will:
    

	a.  Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated.
    
	b.  Select two jobs to compare and trigger the comparison.
    
	c.  Be shown a table comparing the two jobs, displaying, for each job:
    
	d.  Be offered to perform another comparison or go back to the main menu.
    
 > Selection of which jobs to compare will be part of User's class attribute jobsToCompare. The function compareJobs() inside MainActivity class will perform comparision. 

6.  When ranking jobs, a job’s score is computed as the weighted sum of:  
      
    AYS + AYB + (RBP * AYS) + (LT * AYS / 260) - (CT * AYS / 8)  
      
    where:  
      
    AYS = yearly salary adjusted for cost of living  
    AYB = yearly bonus adjusted for cost of living  
    RBP = retirement benefits percentage  
    LT = leave time  
    CT = commute time  
    The rationale for the CT subformula is:
    
>The function compareJobs() inside MainActivity class will perform comparision.

7.  The user interface must be intuitive and responsive.
    
8.  The performance of the app should be such that users do not experience any considerable lag between their actions and the response of the app.
    
9.  For simplicity, you may assume there is a single system running the app (no communication or saving between devices is necessary).

> When application is loaded and user's performs any actions, it will be handled inside MainActivity class. It will also be the entry point for the system and will contain UI attributes as well.  