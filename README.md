# CourseManagementSystem

@Author: RAHUL BAJAJ
Application is based on ROLE-Based Authorization.

Tried to implement the basic level requirement for application.

Before using application ROLE BASED:
It's mandatory to create ROLES-{USERS} and Operate CRUD Operation.

Here we are having 3 roles: ADMIN, STUDENT and INSTRUCTOR and following rights:

- System Admin 
		- Create/Update/Delete/List Users (Instructor, Student)
		- List all courses
		- List all Students 
		- List students enrolled in each course

	- Instructor 
		- Create Courses
		- Create Assignment for each course
		- List all courses
		- Access/Modify courses created by themselves
		- List the students enrolled in each course
		- Must not be able to access courses created by other instuctors

	- Student
		- Can see all the courses 
		- Can enroll in more than one course 
		- Can access only enrolled courses and assignments

Have used H2 database for quick operations and setup.

Spring JWT token/ security is also implemented in the code on basic level 
Now JWT is ready for all API endpoints hit !

JUNIT is written for Service layers as general reference.
