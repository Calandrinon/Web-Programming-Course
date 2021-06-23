using System.Collections.Generic;
using System.Linq;
using DotNetExam.Data;
using DotNetExam.Models;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace DotNetExam.Controllers
{
    public class AuthenticationController : Controller
    {
        private readonly ExamDbContext examDbContext;
        
        public AuthenticationController(ExamDbContext examDbContext)
        {
             this.examDbContext = examDbContext;
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
            List<User> foundUsers = examDbContext.Users.Where(someUser =>
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
            return Redirect("/Authentication");
        }        
    }
}