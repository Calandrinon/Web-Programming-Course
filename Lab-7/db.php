<?php 
    class Repository {
        private $host = "127.0.0.1";
        private $databaseName = "EnterpriseUserManagement";
        private $phpDataObject;

        public function __construct() {
            $connectionString = "mysql:host=" . $this->host . ";dbname=" . $this->databaseName;
            try {
                $this->phpDataObject = new PDO($connectionString, "root", "");
            } catch (Exception $e) {
                echo "Database error: " . $e->getMessage();
            }
        }


        public function selectByName($name) {
            try {
                $pdoStatement = $this->phpDataObject->query("SELECT * FROM UserTable WHERE Name LIKE '%$name%'");
            } catch (Exception $e) {
                echo "Select error: " . $e->getMessage();
            }
            return $pdoStatement->fetchAll();
        }


        public function selectByRole($role) {
            try {
                $pdoStatement = $this->phpDataObject->query("SELECT * FROM UserTable WHERE Role LIKE '%$role%'");
            } catch (Exception $e) {
                echo "Select error: " . $e->getMessage();
            }
            return $pdoStatement->fetchAll();
        }


        public function insert($name, $username, $password, $dateOfBirth, $role, $email) {
            try {
                $pdoStatement = $this->phpDataObject->exec("INSERT INTO UserTable(Name, Username, Password, DateOfBirth, Role, Email) VALUES('$name', '$username', '$password', '$dateOfBirth', '$role', '$email');");
            } catch (Exception $e) {
                echo "Insert error: " . $e->getMessage();
            }
        }

        public function deleteByEmail($email) {
            try {
                $pdoStatement = $this->phpDataObject->exec("DELETE FROM UserTable WHERE Email='$email';");
            } catch (Exception $e) {
                echo "Delete error: " . $e->getMessage();
            }
        }

        public function updatePassword($username, $password) {
            try {
                $pdoStatement = $this->phpDataObject->exec("UPDATE UserTable SET Password='$password' WHERE Username='$username';");
            } catch (Exception $e) {
                echo "Update error: " . $e->getMessage();
            }
        }
    } 
?>
