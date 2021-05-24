using System;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace EnterpriseUserManagementSystem_ActualProject.Controllers
{
    public class NameController : Controller
    {
        // GET
        public IActionResult Index()
        {
            Console.WriteLine(HttpContext.Session.GetString("authenticated"));
            if (HttpContext.Session.GetString("authenticated") == "0")
                return Redirect("/Auth");
            return View();
        }
    }
}