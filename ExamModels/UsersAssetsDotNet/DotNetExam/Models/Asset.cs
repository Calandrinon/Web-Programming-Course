using System.ComponentModel.DataAnnotations;

namespace DotNetExam.Models
{
    public class Asset
    {
        [Key] 
        public int Id { get; set; }
        public int UserId { get; set; }
        public string Name { get; set; }
        public string Description { get; set; }
        public int Value { get; set; }
    }
}