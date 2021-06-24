using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using DotNetExam.Data;
using DotNetExam.Models;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;

namespace DotNetExam.Controllers
{
    public class AssetsController: Controller
    {
        private readonly ExamDbContext examDbContext;
        
        public AssetsController(ExamDbContext examDbContext)
        {
            this.examDbContext = examDbContext;
        } 
        
        // GET
        public IActionResult Index()
        {
            Console.WriteLine("GetAssets: THIS IS THE SESSION STRING: {0}", HttpContext.Session.GetString("username"));
            if (HttpContext.Session.GetString("authenticated") == "0")
                return Redirect("/Authentication");
            return View();
        }
        
        
        [HttpGet]
        public ActionResult<List<Asset>> GetAssets()
        {
            Console.WriteLine("GetAssets: THIS IS THE SESSION STRING: {0}", HttpContext.Session.GetString("username"));
            if (HttpContext.Session.GetString("authenticated") == "0")
                return NotFound();

            string username = HttpContext.Session.GetString("user");
            User user = examDbContext.Users
                .Single(u => u.Username == username);
            return Ok(examDbContext.Assets.Where(a => a.UserId == user.Id).ToList());
        }


        [HttpPost]
        public ActionResult<string> AddAsset(string name, string description, int value)
        {
            Console.WriteLine("AddAsset: THIS IS THE SESSION STRING: {0}", HttpContext.Session.GetString("username"));
            if (HttpContext.Session.GetString("authenticated") == "0")
                return NotFound();

            string username = HttpContext.Session.GetString("user");
            Console.WriteLine("The users:");
            foreach (var userIt in examDbContext.Users)
            {
                Console.WriteLine("User {0} -> Username {1}; Password: {2}", userIt.Id, userIt.Username, userIt.Password);
            }

            User user = examDbContext.Users
                .Single(u => u.Username == username);
            Asset asset = new Asset();
            asset.UserId = user.Id;
            asset.Name = name;
            asset.Description = description;
            asset.Value = value;
            
            examDbContext.Assets.Add(asset);
            examDbContext.SaveChanges();
            asset = examDbContext.Assets.Single(a => a.Name == name && a.UserId == user.Id);
            return Ok(asset);
        }
    }
}