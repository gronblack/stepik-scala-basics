package playground

class step31 {
  class Instructor(val id: Int, val name: String, val surName: String) {
    def fullName: String = s"${name.capitalize} ${surName.capitalize}"
  }

  class Course(val courseID: Int, val title: String, val releaseYear: String, val instructor: Instructor) {
    def getID: String =
      s"${courseID.toString}${instructor.id}"
    def isTaughtBy(instructor: Instructor): Boolean =
      this.instructor.equals(instructor)
    def copyCourse(newReleaseYear: String): Course =
      new Course(courseID, title, newReleaseYear, instructor)
  }
}
