using System;
using System.Collections.Generic;
using System.Linq;
using EnterpriseUserManagementSystem_ActualProject.Data;
using EnterpriseUserManagementSystem_ActualProject.Models;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace EnterpriseUserManagementSystem_ActualProject.Controllers
{
    public class UpdateController : Controller
    {
        private readonly UsersContext _usersContext;
        
        public UpdateController(UsersContext usersContext)
        {
            _usersContext = usersContext;
        } 
        
        // GET
        public IActionResult Index()
        {
            Console.WriteLine(HttpContext.Session.GetString("authenticated"));
            if (HttpContext.Session.GetString("authenticated") == "0")
                return Redirect("/Auth");
            return View();
        }
        
        
        [HttpPost]
        public IActionResult UpdateUser(string name, string username, string password, DateTime dateOfBirth, string role, string email)
        {
            User user = _usersContext.Users.Single(someUser => someUser.Username.Equals(username));

            user.Name = name;
            user.Password = password;
            user.dateOfBirth = dateOfBirth;
            user.Role = role;
            user.Email = email;
            _usersContext.SaveChanges();
            return RedirectToAction("GetUsers", "User");
        }
    }
}