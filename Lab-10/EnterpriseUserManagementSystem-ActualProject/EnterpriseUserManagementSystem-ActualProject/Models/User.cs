using System;
using System.ComponentModel.DataAnnotations;

namespace EnterpriseUserManagementSystem_ActualProject.Models
{
    public class User
    {
        public int Id { get; set;  }
        public string Name { get; set; }
        public string Username { get; set; }
        public string Password { get; set; }
        public DateTime dateOfBirth { get; set; }
        public string Role { get; set; }
        public string Email { get; set; }
    }
}