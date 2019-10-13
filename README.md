# Assignment 2 SOFT2412

## Group Name:

Hand of the Fek

# Vending Machine

**Group assignment (4-5 students)**

**Weight: 25%**

**Due: Multiple deadlines (see submission requirements below) Deliverables: Multiple technical reports and project demonstrations**

## 1. Background
The goal of this assignment is to work as a team on developing a software product using the Scrum method and Agile development tools and practices. In this software development project, your team must apply Scrum practices and principles properly to build a software product in JAVA. Each team also has to use the Agile software tools they set-up during the first group project to following CI/CD practices in their development. Each team will have a stakeholder or client (your tutor) for their project. Your team will have the opportunity to elaborate on the software requirements from the project client during the lab/tutorial time.

As a team, you will get hands-on experience on how to organize and conduct Agile software development using the Scrum method and Agile tools. Specifically, you and your team will be able to:

* Experience the importance of Agile values, Understand and apply Scrum roles,
* Write and estimate User Stories,
* Plan and execute development Sprints, Conduct Scrum events,
* Setup and use Agile development tools and CI/CD practices,
* Conduct Sprint Retrospective to continuously improve the development process, 
* Produce various Scrum Artifacts.

## 2. Forming Scrum Teams
In this group project, you will continue to work with the team from Agile Development Tools project. The team structure must follow Scrum method and thus your team need to decide on the following roles:

* 1 Product Owner (PO),
* 1 Scrum Master (SM), 
* and 2-3 Team members (TM).

After team formation, each team will need to discuss and agree on the above Scrum roles. **Both SM and PO have also to contribute to development in all Sprints**, in which they need to develop parts of the software product. The following are the details/requirements of the software that you need to develop in this project.

## 3. Vending Machine (Snack & Co.) Software
Snack & Co. manage vending machines which sell different types of snacks. The snacks are maintained under different categories; drinks (water, soft drink, juice), chocolates (M&M, Bounty, Mars, Sneakers), Chips (original, chicken, BBQ, and sweet chillies), Lollies (sour worms, jellybeans, little bears, part mix). The vending machine can maintain up to 10 items of each product.

A customer can select an item from the list of available products and specify number of items they want to purchase. A customer can type the product name or a unique code to select an item. A customer can select one or more items at one go. A customer can cancel a transaction before confirming purchase.

A customer pays using coins (10c, 20c, 50c, $1, $2) and notes ($5, $10, $20). If the money provided by the customer is not enough to complete the transaction, then they should be prompted to enter the remaining mount or cancel the transaction. Upon successful transaction, a customer receives the select products and correct change. Product stock will be updated accordingly.

A staff can fill items in the vending machine to the maximum capacity. This should be implemented as Fill command along with a staff Id. Upon successful fill the product stock state should be updated correctly in the system, and a message should be displayed to the staff with date and time.

At any point of time, a staff can print one of the following reports:
* Daily transactions including transaction Id, date and time, items sold, amount of money paid, returned change
* Available stock (list of all products with correct amount)
* Cancelled transactions

The application design should include simple UIs for the functionality. You can decide on the application design/architecture; both in-memory database or persistent database are acceptable. The software must always produce correct output and maintain correct and consistent state of all included entities.

Like the first group project, the team’s development work must be under the SOFT2412 GitHub organization accounts. Your tutor must have access and can inspect the source code at any point of time.

## 4. Scrum – The Project Sprints
You have a total of 5 weeks to complete your project. The project development will be divided into project setup and group preparation. The actual development of the Vending Machine software must run over **3 Sprints (iterations)**. Each Sprint should last for one week. Each Team need to conduct all Scrum events during each Sprint. The first Sprint must start in week’s 10 tutorial. Each team must follow the Sprint schedule as described below.

* Preparation from week 8 and week 9 (and maybe StuVac week). Your team can use this period to decide team roles, get more understanding with the Scrum method and to make appropriate development setup (e.g., agile development tools) and to prepare initial user stories (including initial product backlog) based on the above described requirements.
* Sprint 1: starts week 10 and ends week 11 
* Sprint 2: starts week 11 and ends week 12 
* Sprint 3: starts week 12 and ends week 13

In week 10 tutorial, your team will have an opportunity to clarify and elaborate on the Vending Machine requirements. 

During each Sprint, your team must:
* Carry out the development work following Scrum practices and principles, and CI/CD practices using the agile development tools.
* Document all the Scrum events that happens, user stories, relevant team members interactions and resulted/updated artifacts. This also includes the tools used to implement Scrum.
* Document the key events that occurred during the development of the Vending Machine software. This includes the use of Agile development tools and practices (including CI/CD) by all team members.

At the end of each Sprint, your team must:
* Conduct the Sprint review during the tutorial and demonstrate the current version (increment) of the Vending Machine software to your client (tutor). The client will provide feedback about the product and make some observations about the demo and the interactions and the work progress during each tutorial.
* Submit a group Sprint report that document all the work done in that Sprint as per the project requirements. 
* Submit the version of the source code demonstrated to the client.

## 5. Building the Vending Machine Software using the Agile Tools
Each team must develop the Vending Machine application in **JAVA**. __All team members must collaboratively develop the application’s requirements__ using Agile development tools and practices. Each team must produce a version of the Vending Machine software at the end of each Sprint and ensure that:

1. The program must always produce correct output based on the requirements (based on this assignment description) and additional requirements from your client/tutor.
2. Each team must carry on the development using Scrum method and all the tools and practices (CI/CD) that have been used in the first group project. Each team is required to demonstrate the proper use of these tools. Automated build and unit test must be triggered successfully with appropriate test/code coverage and reporting. You must make sure your unit tests have a good code coverage (>75% code coverage).

## 6. Submission Requirements
### 6.1 Weekly Demo
Each team must demonstrate to their client a version of their Vending Machine application at the end of each Sprint in their tutorial. Besides the client demo, you each team member must demo their individual contributions to their tutor at the end of each Sprint. All team members must be present in the demos. In the GitHub account, your team must label the source code that will be demonstrated to the client with appropriate version number so your tutor can inspect the appropriate source code. In the last Sprint, each team must demonstrate the complete Vending Machine application. The Sprint’s demos are due as follows:
* Sprint 1 Demo: Week 11’s tutorials 
* Sprint 2 Demo: Week 12’s tutorials 
* Sprint 3 Demo: Week 13’s tutorials

### 6.2 Weekly Report and Source Code
Each team must submit a report at the end of each Sprint starting from end of Sprint 1 (week 11) through provided links on Canvas (one submission per group).

The report should include concise documentation of the team’s development of their Vending Machine software using Scrum and the Agile development tools and practices as detailed in sections 4 and 5. Note your team should provide evidences to support the description and justification of the development work presented in their report. The source code of each Sprint must be also submitted through the submission links to be provided on Canvas. The Sprint’s reports and source code are due as follows:
* Sprint 1 Report & Code: Week 11 your tutorial day at 23:59 
* Sprint 2 Report & Code: Week 12 your tutorial day at 23:59 
* Sprint 3 Report & Code: Week 13 your tutorial day at 23:59

