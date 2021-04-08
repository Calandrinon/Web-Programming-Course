<?php 
    include "model.php";
    include "view.php";

    class Controller {
        private $model;
        private $view;        

        public function __construct() {
            $this->model = new Model();
            $this->view = new View();
        }

        public function getParameters() {
            if (isset($_GET["requestedAction"]) && !empty($_GET["requestedAction"])) {
                switch ($_GET["requestedAction"]) {
                    case "getUserByRole":
                        $this->{$_GET["requestedAction"]}($_GET["role"]); break;
                    case "getUserByName":
                        $this->{$_GET["requestedAction"]}($_GET["name"]); break;
                }
            }
        }

        public function getUserByRole($role) {
            $user = $this->model->getUserByRole($role);
            return $this->view->displayUser($user);
        }

        public function getUserByName($name) {
            $user = $this->model->getUserByName($name);
            return $this->view->displayUser($user);
        }
    }

    $controller = new Controller();
    $controller->getParameters();
?>