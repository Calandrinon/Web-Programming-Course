<?php 
    class View {
        public function __construct(){}
        
        public function displayUser($user) {
            echo json_encode($user);
        }
    }

?>