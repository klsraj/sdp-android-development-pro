# Design Discussion  

## Design 1  
![Design 1](./design1.png)  
Pros  
- Appears to clearly cover all the requirements and needs of the app
- Clear and logical relationship flow from each class to other classes
- Covers all the UI interactions that would be expected from the app
- Very straightforward and simple design

Cons  
- Could add more clarity on what the attribute types are expected to be
- In the Job Score function, should clarify what the abbreviations mean
- Could list what parameters the Job Score class accepts
- Could add more relationships between the classes like association or aggregation

## Design 2  
![Design 1](./design2.png)  
Pros  
- All attributes for each class are covered in good detail 
- Good design that shows different kinds of relationships between classes
- Data types added behind each attribute

Cons  
- Don't necessarily need to show the operators that modify the attributes

## Design 3  
![Design 1](./design3.png)  
Pros  
- Good detail on specifying what data type is expected for each attribute
- Adds detail on whether attributes or operations should be public or private

Cons  
- A little unclear on where the job ranking algorithm lives (which operation?)
- On the UML diagram level, consider spelling out variable names so they are unambiguous

## Design 4  
![Design 1](./design4.png)  
Pros  
- Very extensive and detailed documentation of the relationships from one class to another
- Clearly defined operations like rankAllOffers() and selectTwo() that help me identify that this design satisfies the requirements of the app
- Shows the full implementation and is very detailed

Cons  
- Can the design be simplified by making generalizing the "Current Job Detail" class as an attribute or subclass of "Job Offer"?
- The "Job Score" class needs to grab information from the "Add Job Offers" and/or "Current Job Info" class right? Are we capturing that relationship?

## Team Design  
Commonalities  
- Text  

Differences  
- Text  

## Summary  
Summary of the lessons learned in the process of discussing the designs, in terms of design, team work, and any other aspect that the team members consider relevant.  