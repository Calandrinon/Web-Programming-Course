using System;
using System.Collections.Generic;
using System.Linq;
using DotNetExam.Data;
using DotNetExam.Models;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

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
            return View();
        }
        
        
        [HttpPost]
        public IActionResult GetAssets()
        {
            Console.WriteLine("THIS IS THE SESSION STRING: {0}", HttpContext.Session.GetString("username"));     
            if (HttpContext.Session.GetString("authenticated") == "0")
                return Redirect("/Login");
            
            
            
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