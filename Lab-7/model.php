<?php 
    require_once("db.php");
    require_once("user.php");

    class Model {
        private $database;         

        public function __construct() {
            $this->database = new Repository();
        }

        public function getUserByName($name) {
            $result = $this->database->selectByName($name);
            $users = [];

            foreach ($result as $index => $user) {
                $userAsObject = new User($user["Name"], $user["Username"], $user["Password"], $user["DateOfBirth"], $user["Role"], $user["Email"]);
                array_push($users, $userAsObject);
            }

            return $users;
        }

        public function getUserByRole($role) {
            $result = $this->database->selectByRole($role);
            $users = [];

            foreach ($result as $index => $user) {
                $userAsObject = new User($user["Name"], $user["Username"], $user["Password"], $user["DateOfBirth"], $user["Role"], $user["Email"]);
                array_push($users, $userAsObject);
            }

            return $users;
        }

        public function insertUser($user) {
            $this->database->insert($user->getName(), $user->getUsername(), $user->getPassword(), $user->getDateOfBirth(), $user->getRole(), $user->getEmail());
        }

        public function deleteUser($email) {
            $this->database->deleteByEmail($email);
        }

        public function updateUser($username, $password) {
            $this->database->updatePassword($username, $password);
        }
    }
?>