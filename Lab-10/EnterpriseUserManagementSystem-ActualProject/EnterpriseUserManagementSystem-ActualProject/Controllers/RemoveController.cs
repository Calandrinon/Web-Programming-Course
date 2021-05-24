using System;
using System.Linq;
using EnterpriseUserManagementSystem_ActualProject.Data;
using EnterpriseUserManagementSystem_ActualProject.Models;
using Microsoft.AspNetCore.Mvc;

namespace EnterpriseUserManagementSystem_ActualProject.Controllers
{
    public class RemoveController : Controller
    {
        private readonly UsersContext _usersContext;
        
        public RemoveController(UsersContext usersContext)
        {
            _usersContext = usersContext;
        } 
        
        
        // GET
        public IActionResult Index()
        {
            return View();
        }
        
        
        [HttpPost]
        public IActionResult RemoveUser(string username)
        {
            User user = _usersContext.Users.Single(someUser => someUser.Username.Equals(username));

            _usersContext.Users.Remove(user);
            _usersContext.SaveChanges();
            return RedirectToAction("GetUsers", "User");
        }
    }
}