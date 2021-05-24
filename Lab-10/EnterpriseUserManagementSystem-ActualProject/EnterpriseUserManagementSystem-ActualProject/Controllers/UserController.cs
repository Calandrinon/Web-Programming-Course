using System.Collections.Generic;
using System.Linq;
using EnterpriseUserManagementSystem_ActualProject.Data;
using EnterpriseUserManagementSystem_ActualProject.Models;
using Microsoft.AspNetCore.Mvc;

namespace EnterpriseUserManagementSystem_ActualProject.Controllers
{
    public class UserController : Controller
    {
        private readonly UsersContext _usersContext;
        
        public UserController(UsersContext usersContext)
        {
            _usersContext = usersContext;
        } 
        
        // GET
        public IActionResult Index()
        {
            return View();
        }
        
        public IActionResult GetUsers()
        {
            List<User> users = _usersContext.Users.ToList();
            ViewData["allUsers"] = users;
            return View("Index");
        }

        public string GetUsersAsHTML(string name)
        {
            List<User> users = _usersContext.Users.ToList();

            string result = "<table>" +
                            "<thead>" +
                            "<th>Id</th>" +
                            "<th>Name</th>" +
                            "<th>Username</th>" +
                            "<th>Password</th>" +
                            "<th>Date of birth</th>" +
                            "<th>Role</th>" +
                            "<th>E-mail</th>" +
                            "</thead>";

            foreach (User user in users)
            {
                if (user.Name.Contains(name))
                {
                    result += "<tr><td>" + user.Id + "</td><td>" + user.Name + "</td><td>" + user.Username+ "</td><td>" + user.Password + "</td><td>" + user.dateOfBirth+ "</td><td>" + user.Email + "</td><td></tr>";
                }
            }

            result += "</table>";
            return result;
        }
    }
}