# Student-DataBase_management

Database Design of Student Project :

tbl_student_profile (Table)
ID       => Int         => Primary Key Auto Increment
student_name -=> Varchar
Age => Int
AddressLine 1 =>varchar
AddressLine 2 =>varchar
AddressLine 3 =>varchar
dob           => varchar
State         => varchar
Pincode       => int
LandMark      => varchar
Mobile_No     => int

tbl_marks  (Table)
ID      =>  Int Primary key  (AUto Increment) 
Student_ID => Int Foreign Key (ID) in tbl_student_profile table
Mark1   => Int
Mark2   => Int
Mark3   => Int
Mark4   => Int
Result  => varchar

tbl_Admin  (Table)
ID        => Int
Name      => varchar 
UserName  => Varchar
Pwd       => Varchar

Screens:
Report:
Student Profile Report
Student Marks Report
search Report

* User must log into the application with username and pwd
* After Logging in user will get the menu like
  => Press 1 For Student Profile
  => Press 2 For Entering Marks
  => Press 3 For Admin operation
  => Press 4 for REport Viewing
   => If you Press 1 Menu should be displayed like Add Profile/Update Profile /Delete Profile/View profile
    => If you Press 2 Menu should be displayed like Add Marks / Update Marks / Delete Marks / View Marks
     => IF you Press 3 Menu should be displayed like Add Admin/Delete Admin
     => If you press 4 Menu should displayed like Student Profile Report,Student Marks Report,search Report
     example:
             If you choose Add profile => Application must get the values for  tbl_student_profile (Table) through
      Scanner Class in Java get the value for all the fields execpt auto increment field and Save it to Database
        * If you choose report search report user must enter the text or a number that text Should be search the database and bring the results
