using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using EnterpriseUserManagementSystem_ActualProject.Data;
using EnterpriseUserManagementSystem_ActualProject.Models;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System.Web;

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
            if (HttpContext.Session.GetString("authenticated") == "1")
                return Redirect("/Home");
            List<User> foundUsers = _usersContext.Users.Where(someUser =>
                someUser.Username.Equals(username) && someUser.Password.Equals(password)).ToList();
            
            if (foundUsers.Count == 0)
            {
                return View("Error"); 
            }

            HttpContext.Session.SetString("authenticated", "1");            
            HttpContext.Session.SetString("user", username);
            return Redirect("/Home");
        }
        
        [HttpGet]
        public IActionResult Logout()
        {
            HttpContext.Session.SetString("authenticated", "0");
            HttpContext.Session.SetString("user", "None");
            return Redirect("/Auth");
        }        
    }
}