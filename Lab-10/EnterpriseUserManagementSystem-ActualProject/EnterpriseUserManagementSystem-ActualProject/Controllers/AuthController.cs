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
            bool found = false;
            Console.WriteLine("Given username: " + username + "; Given password: " + password);
            foreach (var usersContextUser in _usersContext.Users)
            {
                Console.WriteLine("Some username: " + usersContextUser.Username + "; Some password: " + usersContextUser.Password);
                if (username == usersContextUser.Username && password == usersContextUser.Password)
                {
                    Console.WriteLine("Found the user!");
                    found = true;
                    break;
                }
            }
            
            if (!found)
            {
                return View("Error"); 
            }

            ViewData["User"] = username;
            return Redirect("/Home");
        }
    }
}