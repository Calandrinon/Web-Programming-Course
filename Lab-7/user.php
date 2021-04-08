<?php 
    class User implements JsonSerializable {
        private $name;
        private $username;
        private $password;
        private $dateOfBirth;
        private $role;
        private $email;

        public function __construct($name, $username, $password, $dateOfBirth, $role, $email) {
            $this->name = $name;
            $this->username = $username;
            $this->password = $password;
            $this->dateOfBirth = $dateOfBirth;
            $this->role = $role;
            $this->email = $email;
        }


        public function getName() {
            return $this->name;
        }

        public function getUsername() {
            return $this->username;
        }

        public function getPassword() {
            return $this->password;
        }
        
        public function getDateOfBirth() {
            return $this->dateOfBirth;
        }

        public function getRole() {
            return $this->Role;
        }

        public function getEmail() {
            return $this->email;
        }


        public function setName($name) {
            $this->name = $name;
        }

        public function setUsername($username) {
            $this->username = $username;
        }

        public function setPassword($password) {
            $this->password = $password;
        }
        
        public function setDateOfBirth($dateOfBirth) {
            $this->dateOfBirth = $dateOfBirth;
        }

        public function setRole($role) {
            $this->Role = $role;
        }

        public function setEmail($email) {
            $this->email = $email;
        }

        public function jsonSerialize() {
            $user = get_object_vars($this);
            return $user;
        }
    }
?>
