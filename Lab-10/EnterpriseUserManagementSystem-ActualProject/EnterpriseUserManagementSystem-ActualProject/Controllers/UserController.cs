using Microsoft.AspNetCore.Mvc;

namespace EnterpriseUserManagementSystem_ActualProject.Controllers
{
    public class UserController : Controller
    {
        // GET
        public IActionResult Index()
        {
            return View();
        }
    }
}