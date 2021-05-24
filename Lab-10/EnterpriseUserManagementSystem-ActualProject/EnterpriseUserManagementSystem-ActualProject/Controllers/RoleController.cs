using System.Collections.Generic;
using System.Linq;
using EnterpriseUserManagementSystem_ActualProject.Data;
using EnterpriseUserManagementSystem_ActualProject.Models;
using Microsoft.AspNetCore.Mvc;

namespace EnterpriseUserManagementSystem_ActualProject.Controllers
{
    public class RoleController : Controller
    {
        
        private readonly UsersContext _usersContext;
        
        public RoleController(UsersContext usersContext)
        {
            _usersContext = usersContext;
        } 
        
        
        // GET
        public IActionResult Index()
        {
            return View();
        }
        
        
        public string GetUsersAsHTML(string role)
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
                if (user.Role.Contains(role))
                {
                    result += "<tr><td>" + user.Id + "</td><td>" + user.Name + "</td><td>" + user.Username+ "</td><td>" + user.Password + "</td><td>" + user.dateOfBirth+ "</td><td>" + user.Role + "</td><td>" + user.Email + "</td><td></tr>";
                }
            }

            result += "</table>";
            return result;
        }
    }
}