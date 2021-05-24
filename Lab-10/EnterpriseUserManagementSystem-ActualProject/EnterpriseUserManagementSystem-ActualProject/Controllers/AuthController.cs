using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using EnterpriseUserManagementSystem_ActualProject.Data;
using EnterpriseUserManagementSystem_ActualProject.Models;
using Microsoft.AspNetCore.Mvc;

namespace EnterpriseUserManagementSystem_ActualProject.Controllers
{
    public class AuthController : Controller
    {
        private readonly UsersContext _usersContext;
        
        public AuthController(UsersContext usersContext)
        {
            _usersContext = usersContext;
        } 
        
        // GET
        public IActionResult Index()
        {
            return View();
        }
        

        [HttpPost]
        public IActionResult Login(string username, string password)
        {
            List<User> foundUsers = _usersContext.Users.Where(someUser =>
                someUser.Username.Equals(username) && someUser.Password.Equals(password)).ToList();
            
            if (foundUsers.Count == 0)
            {
                return View("Error"); 
            }

            ViewData["User"] = username;
            return Redirect("/Home");
        }
    }
}