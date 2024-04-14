import java.sql.*;
import java.util.Scanner;

public class StudentDB {
    public static void main(String[] args) throws SQLException {
        Login();
        performSearchReport();

    }

    public static void Login() {
        int attempts = 0;
        try {
            // Connect to the database
            String url = "jdbc:mysql://localhost:3306/StudentDB";
            String username = "root";
            String password = "mysqltest";
            Connection con = DriverManager.getConnection(url, username, password);

            Scanner scanner = new Scanner(System.in);

            System.out.println("Welcome Admin....Login\uD83D\uDD11");
            System.out.println("*************************");

            while (attempts < 3) {
                System.out.print("Enter username: ");
                String inputUsername = scanner.nextLine();
                System.out.print("Enter password: ");
                String inputPassword = scanner.nextLine();

                String query = "SELECT * FROM Admin WHERE username = ? AND password = ?";
                try (PreparedStatement pst = con.prepareStatement(query)) {
                    pst.setString(1, inputUsername);
                    pst.setString(2, inputPassword);

                    try (ResultSet resultSet = pst.executeQuery()) {
                        if (resultSet.next()) {
                            System.out.println("Login successful\uD83D\uDD13");
                            System.out.println("___*_____*_____*____");
                            Home();
                            return; // Exit method after successful login
                        } else {
                            System.out.println("Invalid username or password. Please try again\uD83D\uDD12.");
                            attempts++;
                            System.out.println("\uD83D\uDE14\uD83D\uDE14\uD83D\uDE14\uD83D\uDE14\uD83D\uDE14\uD83D\uDE14\uD83D\uDE14");
                        }
                    }
                }
            }

            if (attempts == 3) {
                System.out.println("Maximum login 3 attempts reached. Exiting...");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void Home() throws SQLException {

        String url = "jdbc:mysql://localhost:3306/StudentDB";
        String Username = "root";
        String Password = "mysqltest";
        Connection con = DriverManager.getConnection(url,Username,Password);

        Statement st = con.createStatement();

        Scanner scanner = new Scanner(System.in);

        try {

            int choice;
            do {
                System.out.println("Press 1 For Student Profile\uD83D\uDC68\u200D\uD83C\uDF93");
                System.out.println("Press 2 For Entering Marks\uD83D\uDCCA");
                System.out.println("Press 3 For Admin operation\uD83D\uDC68\u200D\uD83C\uDF93");
                System.out.println("Press 4 For Report Viewing\uD83D\uDCCA");
                System.out.println("Press 5 to Exit\uD83D\uDEAA");
                System.out.println("******************");

                System.out.print("Enter a option\uD83D\uDD22: ");
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        Student();
                        break;
                    case 2:
                        Marks();
                        break;
                    case 3:
                        Admin();
                        break;
                    case 4:
                        ReportViewing();
                        break;
                    case 5:
                        System.out.println("Exiting...\uD83D\uDEAA\uD83D\uDEAA");
                        System.out.println("____**______**______**____");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again\uD83D\uDD0E\uD83D\uDD0E.");
                }
            } while (choice != 5);

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) st.close();
            } catch (SQLException se2) {
            }
            try {
                if (con != null) con.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
    public static void Student() throws SQLException {
        int choice;
        do{
            System.out.println("_____________________________________");
            System.out.println("Select the type of report YOU WANT:");
            System.out.println("1. Add Profile\uD83D\uDC68\u200D\uD83C\uDF93");
            System.out.println("2. Update Profile\uD83D\uDCCA");
            System.out.println("3. Delete Profile\uD83D\uDDD1\uFE0F");
            System.out.println("4. View Profile\uD83D\uDCDA");
            System.out.println("5. Home\uD83C\uDFE0");
            System.out.println("6. Exit..\uD83D\uDEAA");
            System.out.println("_____****_____*****_____****_____");

            System.out.print("Please Enter Options\uD83D\uDD22: ");
            Scanner sc = new Scanner(System.in);

            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    addprofile();
                    break;
                case 2:
                    updateProfile();
                    break;
                case 3:
                    deleteProfile();
                    break;
                case 4:
                    viewProfile();
                    break;
                case 5:
                    Home();
                    break;
                case 6:
                    System.out.println("Exiting...\uD83D\uDEAA\uD83D\uDEAA");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice !=5);
    }
    public static void addprofile() throws SQLException {

        String url = "jdbc:mysql://localhost:3306/StudentDB";
        String Username = "root";
        String Password = "mysqltest";
        String query = "insert into student_profile (student_name, Age, Address1, Address2, Address3, DOB, State, Pincode, LandMark, Mobile_No) values(?,?,?,?,?,?,?,?,?,?);";

        Connection con = DriverManager.getConnection(url,Username,Password);
        PreparedStatement pst = con.prepareStatement(query);

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a Name: ");
        String Name = sc.next();
        pst.setString(1,Name);

        System.out.print("Enter a Age: ");
        int Age = sc.nextInt();
        pst.setInt(2,Age);

        System.out.print("Enter a Address_1: ");
        String Address_1 = sc.next();
        pst.setString(3,Address_1);

        System.out.print("Enter a Address_2: ");
        String Address_2 = sc.next();
        pst.setString(4,Address_2);

        System.out.print("Enter a Address_3: ");
        String Address_3 = sc.next();
        pst.setString(5,Address_3);

        System.out.print("Enter a DOB (YYYY-MM-DD): ");
        String DOB = sc.next();
        pst.setString(6,DOB);

        System.out.print("Enter a State: ");
        String State = sc.next();
        pst.setString(7,State);

        System.out.print("Enter a Pincode: ");
        int Pincode = sc.nextInt();
        pst.setInt(8,Pincode);

        System.out.print("Enter a LandMark: ");
        String LandMark = sc.next();
        pst.setString(9,LandMark);

        System.out.print("Enter a Mobile NO: ");
        long Mobile_No = sc.nextLong();
        pst.setLong(10,Mobile_No);

        int rows = pst.executeUpdate();
        System.out.println("Successfully added"+ rows);
        con.close();
        }

    public static void updateProfile() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/StudentDB";
        String username = "root";
        String password = "mysqltest";

        try (Connection con = DriverManager.getConnection(url, username, password);
             Scanner scanner = new Scanner(System.in)) {

            System.out.print("Enter the ID of the profile to update: ");
            int profileId = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            String checkQuery = "SELECT * FROM student_profile WHERE ID = ?";
            try (PreparedStatement st = con.prepareStatement(checkQuery)) {
                st.setInt(1, profileId);
                try (ResultSet checkResult = st.executeQuery()) {
                    if (!checkResult.next()) {
                        System.out.println("Profile with ID " + profileId + " does not exist.");
                        return;
                    }
                }
            }

            System.out.print("Enter updated name: ");
            String updatedName = scanner.nextLine();

            System.out.print("Enter updated age: ");
            int updatedAge = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            System.out.print("Enter updated address Line 1: ");
            String updatedAddress1 = scanner.nextLine();

            System.out.print("Enter updated address Line 2: ");
            String updatedAddress2 = scanner.nextLine();

            System.out.print("Enter updated address Line 3: ");
            String updatedAddress3 = scanner.nextLine();

            System.out.print("Enter updated DOB: ");
            String updatedDOB = scanner.nextLine();

            System.out.print("Enter updated state: ");
            String updatedState = scanner.nextLine();

            System.out.print("Enter updated pincode: ");
            int updatedPincode = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            System.out.print("Enter updated landmark: ");
            String updatedLandmark = scanner.nextLine();

            System.out.print("Enter updated mobile number: ");
            long updatedMobileNo = scanner.nextLong();

            String updateQuery = "UPDATE student_profile SET student_name=?, Age=?, Address1=?, Address2=?, Address3=?, DOB=?, State=?, Pincode=?, LandMark=?, Mobile_No=? WHERE ID=?";
            try (PreparedStatement updateStatement = con.prepareStatement(updateQuery)) {
                updateStatement.setString(1, updatedName);
                updateStatement.setInt(2, updatedAge);
                updateStatement.setString(3, updatedAddress1);
                updateStatement.setString(4, updatedAddress2);
                updateStatement.setString(5, updatedAddress3);
                updateStatement.setString(6, updatedDOB);
                updateStatement.setString(7, updatedState);
                updateStatement.setInt(8, updatedPincode);
                updateStatement.setString(9, updatedLandmark);
                updateStatement.setLong(10, updatedMobileNo);
                updateStatement.setInt(11, profileId);

                int rowsAffected = updateStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Profile updated successfully\uD83D\uDC4D\uD83D\uDC4D\uD83D\uDC4D.");
                    System.out.println("____**_____**_____**_____**_____");
                } else {
                    System.out.println("Failed to update profile.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteProfile() throws SQLException {

        String url = "jdbc:mysql://localhost:3306/StudentDB";
        String Username = "root";
        String Password = "mysqltest";

        Connection con = DriverManager.getConnection(url,Username,Password);

        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter the ID of the profile to delete: ");
            int profileId = scanner.nextInt();


            String checkQuery = "SELECT * FROM student_profile WHERE ID = ?";
            PreparedStatement checkStatement = con.prepareStatement(checkQuery);
            checkStatement.setInt(1, profileId);
            ResultSet checkResult = checkStatement.executeQuery();

            if (!checkResult.next()) {
                System.out.println("Profile with ID " + profileId + " does not exist.");
                return;
            }

            String deleteQuery = "DELETE FROM student_profile WHERE ID=?";
            PreparedStatement deleteStatement = con.prepareStatement(deleteQuery);
            deleteStatement.setInt(1, profileId);

            int rowsAffected = deleteStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Profile deleted successfully.");
            } else {
                System.out.println("Failed to delete profile.");
            }

            checkResult.close();
            checkStatement.close();
            deleteStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void viewProfile() {
        try {
           Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/StudentDB", "root", "mysqltest");

            Scanner scanner = new Scanner(System.in);
            // Construct the select query to retrieve marks for all students
            String selectQuery = "select * from student_profile where ID = ?";

            System.out.print("Enter Student ID whose marks you want : ");
            int searchText = scanner.nextInt();

            PreparedStatement pst = con.prepareStatement(selectQuery);
            pst.setInt(1,  searchText);
            ResultSet rs = pst.executeQuery();

            // Print the header
            System.out.println("______________________________________________________________________________________________________________________________________________________________________");
            System.out.println("ID   Name         Age     Address1            Address2            Address3              DOB              State            Pincode         LandMark         Mobile_No");
            System.out.println("______**_______**________****_______**________****_______**________****_______**________****_______**________****_______**________****_______**________***____________");

            // Check if there's at least one profile
            if (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("student_name");
                int age = rs.getInt("Age");
                String address1 = rs.getString("Address1");
                String address2 = rs.getString("Address2");
                String address3 = rs.getString("Address3");
                String dob = rs.getString("dob");
                String state = rs.getString("State");
                int pincode = rs.getInt("Pincode");
                String landmark = rs.getString("LandMark");
                long mobileNo = rs.getLong("Mobile_No");

                System.out.printf("%-4d %-12s %-7d %-19s %-19s %-18s %-19s %-16s %-15d %-16s %-25d\n", id, name, age, address1, address2, address3, dob, state, pincode, landmark, mobileNo);
                System.out.print("__________________________________________________________________________________________________________________________________");
            } else {
                System.out.println("No student found with ID: " + searchText);

            }


            rs.close();
            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void Marks() throws SQLException {
        int choice;
        do{
            System.out.println("_____________________________________");
            System.out.println("Select the type of report YOU WANT:");
            System.out.println("1. Add Mark\uD83D\uDCCA");
            System.out.println("2. Delete Mark\uD83D\uDDD1");
            System.out.println("3. Update Mark\uD83D\uDCCA");
            System.out.println("4. viewMarks\uD83D\uDCDA");
            System.out.println("5. Back\uD83D\uDEAA");
            System.out.println("_____****_____*****_____****_____");

            System.out.print("Please Enter Options: ");
            Scanner sc = new Scanner(System.in);

            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    AddMarks();
                    break;
                case 2:
                    deleteMarks();
                    break;
                case 3:
                    updateMarks();
                    break;
                case 4:
                    viewMarks();
                    break;
                case 5:
                    System.out.println("Back...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice !=5);
    }
    public static void AddMarks() throws SQLException {

        String url = "jdbc:mysql://localhost:3306/StudentDB";
        String Username = "root";
        String Password = "mysqltest";

        Connection con = DriverManager.getConnection(url,Username,Password);

        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter Student ID: ");
            int studentId = scanner.nextInt();

            System.out.print("Enter Mark 1: ");
            int mark1 = scanner.nextInt();

            System.out.print("Enter Mark 2: ");
            int mark2 = scanner.nextInt();

            System.out.print("Enter Mark 3: ");
            int mark3 = scanner.nextInt();

            System.out.print("Enter Mark 4: ");
            int mark4 = scanner.nextInt();


            String result = calculateResult(mark1, mark2, mark3, mark4);

            String Query = "INSERT INTO student_marks (Student_ID, Mark1, Mark2, Mark3, Mark4, Result) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement st = con.prepareStatement(Query);

            st.setInt(1, studentId);
            st.setInt(2, mark1);
            st.setInt(3, mark2);
            st.setInt(4, mark3);
            st.setInt(5, mark4);
            st.setString(6, result);

            int rows = st.executeUpdate();

            if (rows > 0) {
                System.out.println("Marks added successfully.");
            } else {
                System.out.println("Failed to add marks.");
            }
            viewMarks();

            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static String calculateResult(int mark1, int mark2, int mark3, int mark4) {
        int totalMarks = mark1 + mark2 + mark3 + mark4;

        if (totalMarks >= 120) {
            return "PASS";
        } else {
            return "FAIL";
        }
    }

    public static void updateMarks() {
        try {
           Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/StudentDB", "root", "mysqltest");

            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter Student ID whose marks you want to update: ");
            int studentId = scanner.nextInt();

            // Prompt the user to input the updated marks
            System.out.print("Enter updated Mark1: ");
            int updatedMark1 = scanner.nextInt();
            System.out.print("Enter updated Mark2: ");
            int updatedMark2 = scanner.nextInt();
            System.out.print("Enter updated Mark3: ");
            int updatedMark3 = scanner.nextInt();
            System.out.print("Enter updated Mark4: ");
            int updatedMark4 = scanner.nextInt();
            System.out.print("Enter updated Result: ");
            String updatedResult = scanner.next();

            // Construct the update query
            String updateQuery = "UPDATE student_marks SET Mark1 = ?, Mark2 = ?, Mark3 = ?, Mark4 = ?, Result = ? WHERE Student_ID = ?";
            PreparedStatement pst = con.prepareStatement(updateQuery);
            pst.setInt(1, updatedMark1);
            pst.setInt(2, updatedMark2);
            pst.setInt(3, updatedMark3);
            pst.setInt(4, updatedMark4);
            pst.setString(5, updatedResult);
            pst.setInt(6, studentId);

            // Execute the update query
            int rowsUpdated = pst.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Marks updated successfully for student ID: " + studentId);
            } else {
                System.out.println("No marks found for student ID: " + studentId);
            }

            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteMarks() {
        try {
           Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/StudentDB", "root", "mysqltest");

            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter Student ID whose marks you want to delete: ");
            int studentId = scanner.nextInt();

            String deleteQuery = "DELETE FROM student_marks WHERE Student_ID = ?";
            PreparedStatement pst = con.prepareStatement(deleteQuery);
            pst.setInt(1, studentId);

            int rowsDeleted = pst.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Marks deleted successfully for student ID: " + studentId);
            } else {
                System.out.println("No marks found for student ID: " + studentId);
            }

            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void viewMarks() {
        try {
           Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/StudentDB", "root", "mysqltest");

            Scanner scanner = new Scanner(System.in);
            // Construct the select query to retrieve marks for all students
            String selectQuery = "select * from student_marks where Student_ID = ?";

            System.out.print("Enter Student ID whose marks you want : ");
            int searchText = scanner.nextInt();

            PreparedStatement pst = con.prepareStatement(selectQuery);
            pst.setInt(1,  searchText);
            ResultSet rs = pst.executeQuery();

            System.out.println("***********************************");
            System.out.println("\nSearch Report - Student Marks:");
            System.out.println("______________________________________________________");
            System.out.println("ID   Student ID   Mark1   Mark2   Mark3   Mark4   Result");
            System.out.println("______**_______**________****_______**________****____");
            if (rs.next()) {
                int id = rs.getInt("ID");
                int studentId = rs.getInt("Student_ID");
                int mark1 = rs.getInt("Mark1");
                int mark2 = rs.getInt("Mark2");
                int mark3 = rs.getInt("Mark3");
                int mark4 = rs.getInt("Mark4");
                String result = rs.getString("Result");

                System.out.printf("%-10d%-9d%-9d%-9d%-7d%-5d%-10s\n",id, studentId, mark1, mark2, mark3, mark4, result);
            } else {
                System.out.println("No student found with ID: " + searchText);
            System.out.println("_____________________________________________________________________________");

            }
            System.out.println("_____________________________________________________________________________");

            rs.close();
            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void Admin() throws SQLException {
        int choice;
        do{
            System.out.println("_____________________________________");
            System.out.println("Select the type of report YOU WANT:");
            System.out.println("1. Add Admin\uD83D\uDC68\u200D\uD83C\uDF93");
            System.out.println("2. Delete Admin\uD83D\uDC68\u200D\uD83C\uDF93");
            System.out.println("3. Display All Admins\uD83D\uDD0D ");
            System.out.println("4. Go Back");
            System.out.println("_____****_____*****_____****_____");

            System.out.print("Please Enter Options\uD83D\uDD22: ");
            Scanner sc = new Scanner(System.in);

            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    Adminadd();
                    break;
                case 2:
                    DeleteAdmin();
                    break;
                case 3:
                    DisplayAllAdmins();
                    break;
                case 4:
                    Home();
                    break;
                case 5:
                    System.out.println("Back...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice !=5);
    }

    public static void Adminadd() throws SQLException {

        String url = "jdbc:mysql://localhost:3306/StudentDB";
        String Username = "root";
        String Password = "mysqltest";

        Connection con = DriverManager.getConnection(url,Username,Password);

        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter Admin Name: ");
            String adminName = scanner.nextLine();

            System.out.print("Enter Admin Username: ");
            String adminUsername = scanner.nextLine();

            System.out.print("Enter Admin Password: ");
            String adminPassword = scanner.nextLine();

            String insertQuery = "INSERT INTO Admin (Name, UserName, Password) VALUES (?, ?, ?)";
            PreparedStatement insertStatement = con.prepareStatement(insertQuery);
            insertStatement.setString(1, adminName);
            insertStatement.setString(2, adminUsername);
            insertStatement.setString(3, adminPassword);

            int rowsAffected = insertStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Admin added successfully.");
            } else {
                System.out.println("Failed to add admin.");
            }

            insertStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void DeleteAdmin() throws SQLException {

        String url = "jdbc:mysql://localhost:3306/StudentDB";
        String Username = "root";
        String Password = "mysqltest";

        Connection con = DriverManager.getConnection(url,Username,Password);

        try {
            Scanner scanner = new Scanner(System.in);

            // Prompt the user to enter the ID of the admin to delete
            System.out.print("Enter the ID of the admin to delete: ");
            int adminId = scanner.nextInt();

            String deleteQuery = "DELETE FROM Admin WHERE ID = ?";

            PreparedStatement pst = con.prepareStatement(deleteQuery);
            pst.setInt(1, adminId);

            int rowsDeleted = pst.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Admin with ID " + adminId + " deleted successfully.");
            } else {
                System.out.println("No admin found with ID " + adminId + ". Deletion failed.");
            }

            pst.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void DisplayAllAdmins() throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/StudentDB", "root", "mysqltest");

        try {
            String selectQuery = "SELECT ID, Name, UserName, Password FROM Admin";

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(selectQuery);

            System.out.println("Admin Data:");
            System.out.println("+----+----------------------+-----------------+----------------------+");
            System.out.println("| ID |         Name         |     Username    |       Password       |");
            System.out.println("+----+----------------------+-----------------+----------------------+");

            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("Name");
                String username = rs.getString("UserName");
                String password = rs.getString("Password");

                System.out.printf("| %-2d | %-20s | %-15s | %-20s |%n", id, name, username, password);
            }

            System.out.println("+----+----------------------+-----------------+----------------------+");

            rs.close();
            st.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*public static void DisplayAllAdmins() throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/StudentDB", "root", "mysqltest");
        try {
            String selectQuery = "SELECT * FROM Admin";

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(selectQuery);

            System.out.println("Admin Data:");
            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("Name");
                String username = rs.getString("UserName");

                System.out.println("ID: " + id + ", Name: " + name + ", Username: " + username);
            }

            rs.close();
            st.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    public static void ReportViewing() {
        int reportType;
        try {
            do{
                Scanner scanner = new Scanner(System.in);

                System.out.println("_____________________________________");
                System.out.println("Select the type of report YOU WANT:");
                System.out.println("1. Student Profile Report\uD83D\uDC68\u200D\uD83C\uDF93");
                System.out.println("2. Student Marks Report\uD83D\uDCCA");
                System.out.println("3. Search Report\uD83D\uDD0E\uD83D\uDD0D");
                System.out.println("4. Home Page\uD83C\uDFE0");
                System.out.println("4. Go Back\uD83D\uDEAA");
                System.out.println("****______***______***______***______***");

                System.out.print("Enter a YOUR Option\uD83D\uDD22: ");
                reportType = scanner.nextInt();

                switch (reportType) {
                    case 1:
                        StudentProfile();
                        break;
                    case 2:
                        displayMarksReport();
                        break;
                    case 3:
                        performSearchReport();
                    case 4:
                        Home();
                    case 5:
                        System.out.println("Back");
                        break;
                    default:
                        System.out.println("Invalid report type selected.");
                }
            } while (reportType !=5);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void StudentProfile() throws SQLException {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/StudentDB", "root", "mysqltest");

            // Construct the select query to retrieve profiles for all students
            String selectQuery = "SELECT * FROM student_profile";

            // Create a statement
            Statement st = con.createStatement();

            // Execute the query
            ResultSet rs = st.executeQuery(selectQuery);

            // Print the header
            System.out.println("______________________________________________________________________________________________________________________________________________________________________");
            System.out.println("ID   Name         Age     Address1            Address2            Address3              DOB              State            Pincode         LandMark         Mobile_No");
            System.out.println("______**_______**________****_______**________****_______**________****_______**________****_______**________****_______**________****_______**________***____________");

            // Check if there's at least one profile
            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("student_name");
                int age = rs.getInt("Age");
                String address1 = rs.getString("Address1");
                String address2 = rs.getString("Address2");
                String address3 = rs.getString("Address3");
                String dob = rs.getString("dob");
                String state = rs.getString("State");
                int pincode = rs.getInt("Pincode");
                String landmark = rs.getString("LandMark");
                long mobileNo = rs.getLong("Mobile_No");

                System.out.printf("%-4d %-12s %-7d %-19s %-19s %-18s %-19s %-16s %-15d %-16s %-25d\n", id, name, age, address1, address2, address3, dob, state, pincode, landmark, mobileNo);
            }
            System.out.print("__________________________________________________________________________________________________________________________________");

            rs.close();
            st.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void displayMarksReport() throws SQLException {

        String url = "jdbc:mysql://localhost:3306/StudentDB";
        String Username = "root";
        String Password = "mysqltest";

        Connection con = DriverManager.getConnection(url,Username,Password);

        String query = "SELECT * FROM student_marks";
        PreparedStatement st = con.prepareStatement(query);
        ResultSet resultSet = st.executeQuery();

        System.out.println("______________________________________________________");
        System.out.println("ID   Student ID   Mark1   Mark2   Mark3   Mark4   Result");
        System.out.println("______**_______**________****_______**________****____");

        while (resultSet.next()) {
            int id = resultSet.getInt("ID");
            int studentId = resultSet.getInt("Student_ID");
            int mark1 = resultSet.getInt("Mark1");
            int mark2 = resultSet.getInt("Mark2");
            int mark3 = resultSet.getInt("Mark3");
            int mark4 = resultSet.getInt("Mark4");
            String result = resultSet.getString("Result");

            System.out.printf("%-10d%-9d%-9d%-9d%-7d%-5d%-10s\n",id, studentId, mark1, mark2, mark3, mark4, result);
            //System.out.printf("%-13d%-13d%-7d%-8d%-8d%-5d%-10s\n",id, studentId, mark1, mark2, mark3, mark4, result);

        }
        System.out.println("_____________________________________________________________________________");

        resultSet.close();
        st.close();
    }

    public static void performSearchReport() {
        String url = "jdbc:mysql://localhost:3306/StudentDB";
        String username = "root";
        String password = "mysqltest";

        try (Connection con = DriverManager.getConnection(url, username, password);
             Scanner scanner = new Scanner(System.in)) {

            System.out.print("Enter text to search: ");
            String searchText = scanner.nextLine();

            // Search and display student profiles
            String profileQuery = "SELECT * FROM student_profile WHERE student_name LIKE ?";
            try (PreparedStatement profileStatement = con.prepareStatement(profileQuery)) {
                profileStatement.setString(1, "%" + searchText + "%");
                try (ResultSet profileResultSet = profileStatement.executeQuery()) {
                    // Print profile headers
                    System.out.println("***********************************");
                    System.out.println("Search Report - Student Profiles:");
                    System.out.println("ID   Name         Age     Address1            Address2            Address3              DOB              State            Pincode         LandMark         Mobile_No");
                    System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

                    // Print profile details
                    while (profileResultSet.next()) {
                        int id = profileResultSet.getInt("ID");
                        String name = profileResultSet.getString("student_name");
                        int age = profileResultSet.getInt("Age");
                        String address1 = profileResultSet.getString("Address1");
                        String address2 = profileResultSet.getString("Address2");
                        String address3 = profileResultSet.getString("Address3");
                        String dob = profileResultSet.getString("dob");
                        String state = profileResultSet.getString("State");
                        int pincode = profileResultSet.getInt("Pincode");
                        String landmark = profileResultSet.getString("LandMark");
                        long mobileNo = profileResultSet.getLong("Mobile_No");

                        // Print each profile row
                        System.out.printf("%-4d %-12s %-7d %-19s %-19s %-18s %-19s %-16s %-15d %-16s %-25d\n", id, name, age, address1, address2, address3, dob, state, pincode, landmark, mobileNo);
                    }
                    System.out.println("********************************************************************************************************************************************************************************");
                }
            }

            // Search and display student marks
            String marksQuery = "SELECT * FROM student_marks WHERE Student_ID IN (SELECT ID FROM student_profile WHERE student_name LIKE ?)";
            try (PreparedStatement marksStatement = con.prepareStatement(marksQuery)) {
                marksStatement.setString(1, "%" + searchText + "%");
                try (ResultSet marksResultSet = marksStatement.executeQuery()) {
                    // Print marks headers
                    System.out.println("***********************************");
                    System.out.println("\nSearch Report - Student Marks:");
                    System.out.println("ID   Student ID   Mark1   Mark2   Mark3   Mark4   Result");
                    System.out.println("----------------------------------------------------------------------------------------------------------------");

                    // Print marks details
                    while (marksResultSet.next()) {
                        int id = marksResultSet.getInt("ID");
                        int studentId = marksResultSet.getInt("Student_ID");
                        int mark1 = marksResultSet.getInt("Mark1");
                        int mark2 = marksResultSet.getInt("Mark2");
                        int mark3 = marksResultSet.getInt("Mark3");
                        int mark4 = marksResultSet.getInt("Mark4");
                        String result = marksResultSet.getString("Result");

                        // Print each marks row
                        System.out.printf("%-10d%-9d%-9d%-9d%-7d%-5d%-10s\n", id, studentId, mark1, mark2, mark3, mark4, result);
                    }
                    System.out.println("----------------------------------------------------------------------------------------------------------------");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error executing search report: " + e.getMessage());
        }
    }
}
