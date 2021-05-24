using System;
using EnterpriseUserManagementSystem_ActualProject.Data;
using EnterpriseUserManagementSystem_ActualProject.Models;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace EnterpriseUserManagementSystem_ActualProject.Controllers
{
    public class AddController : Controller
    {
        private readonly UsersContext _usersContext;
        
        public AddController(UsersContext usersContext)
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
        public IActionResult SaveUser(string name, string username, string password, DateTime dateOfBirth, string role, string email)
        {
            User user = new User();
            user.Name = name;
            user.Username = username;
            user.Password = password;
            user.dateOfBirth = dateOfBirth;
            user.Role = role;
            user.Email = email;

            _usersContext.Add(user);
            _usersContext.SaveChanges();
            return RedirectToAction("GetUsers", "User");
        }
    }
}