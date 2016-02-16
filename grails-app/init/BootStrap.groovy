/*import appforliteracy.Learner
import appforliteracy.Module
import appforliteracy.Researcher
import appforliteracy.User
import appforliteracy.moduleInputDomains.FirstExample
import appforliteracy.moduleInputDomains.SecondExample
import appforliteracy.moduleInputDomains.ThirdExample*/

import appforliteracy.Role
import appforliteracy.User
import appforliteracy.UserRole

class BootStrap {

    def init = { /*servletContext ->

        Researcher researcher = new Researcher()
        researcher.position = "Boss"
        researcher.email = "example@gmail.com"
        researcher.password = "password"
        researcher.lastName = "Nye"
        researcher.firstName = "Bill"
        researcher.save()

        Learner learner = new Learner()
        learner.email = "example2@gmail.com"
        learner.password = "password"
        learner.lastName = "Baratheon"
        learner.firstName = "Stannis"
        learner.researcherID = researcher.id
        learner.disability = "Secondborn Son Syndrome"
        learner.dateOfBirth = new Date(1990, 6, 15)
        learner.save()

        FirstExample firstExample = new FirstExample()
        firstExample.type = "FirstExample"
        firstExample.name = "Example1"
        firstExample.words = ["Game", "of", "Thrones"]
        firstExample.save()

        SecondExample secondExample = new SecondExample()
        secondExample.type = "SecondExample"
        secondExample.name = "Example2"
        secondExample.length = 17
        secondExample.save()

        ThirdExample thirdExample = new ThirdExample()
        thirdExample.type = "ThirdExample"
        thirdExample.name = "Example3"
        thirdExample.favoriteLetter = "J"
        thirdExample.save()

        Module module = new Module()
        module.inputID = firstExample.moduleDataID
        module.type = firstExample.type
        module.save()*/
        
        def adminRole = new Role('ROLE_ADMIN').save()
        def userRole = new Role('ROLE_USER').save()
        def researcherRole = new Role('ROLE_RESEARCHER').save()
        
        def testUser1 = new User('sam@uw.edu','password').save()
        def testUser2 = new User('joe@uw.edu','password').save()
        def testUser3 = new User('erin@uw.edu','password').save()
        def testUser4 = new User('jason@uw.edu','password').save()
        def testUser5 = new User('patrick@uw.edu','password').save()
        def testUser6 = new User('anat@uw.edu','password').save()
        
        UserRole.create testUser1, userRole
        UserRole.create testUser2, userRole
        UserRole.create testUser3, userRole
        UserRole.create testUser4, researcherRole
        UserRole.create testUser5, researcherRole
        UserRole.create testUser6, adminRole
        
        
        UserRole.withSession {
            it.flush()
            it.clear()
        }
        
        assert User.count() == 6
        assert Role.count() == 3
        assert UserRole.count() == 6
        
    }
    def destroy = {
    }
}
