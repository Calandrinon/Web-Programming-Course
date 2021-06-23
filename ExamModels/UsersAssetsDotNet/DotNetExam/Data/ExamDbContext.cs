using Microsoft.EntityFrameworkCore;

namespace DotNetExam.Data
{
    public class ExamDbContext: DbContext
    {
        public ExamDbContext(DbContextOptions<ExamDbContext> options): base(options) {}        
        
        public DbSet<Models.User> Users { get; set; }
        public DbSet<Models.Asset> Assets { get; set; }
    }
}