


import appforliteracy.Learner
import appforliteracy.Researcher
import appforliteracy.Role
import appforliteracy.User
import appforliteracy.UserRole

import metafunctionality.Module

import firstexample.FirstExample
import metafunctionality.ModuleOutput

class BootStrap {

    def init = { servletContext ->

        Learner learner = new Learner('example2@gmail.com','password','Baratheon','Stannis')
        //learner.researcherID = researcher.id
        learner.disability = "Secondborn Son Syndrome"
        learner.dateOfBirth = new Date(1990, 6, 15)

//        firstExample.save(flush: true)
//        FirstExample firstExample = new FirstExample()
//        firstExample.type = "FirstExample"
//        firstExample.name = "Example1"
//        firstExample.word = "Hall"
//        firstExample.answer = "Ball"
//        firstExample.rhymingCandidates = ["Game", "Alligator", "Pinwheel"]
//
//        Module module = new Module()
//        module.inputID = firstExample.moduleDataID
//        module.type = firstExample.type
//        module.isCompleted = true
//
//        ModuleOutput output = new ModuleOutput()
//        output.headers = ["word", "accuracy"]
//        output.valueRows = ["Hello,100"]
//        output.type = firstExample.type

        //module.outputIDs = [output.moduleDataID]

        //learner.moduleIDs = [module.moduleId]

        learner.save(flush: true)



        Researcher researcher = new Researcher('example@gmail.com','password','Nye','Bill')
        researcher.position = "Boss"

        researcher.learnerIDs = [learner.getId()]
        println(learner.getId())



        researcher.save(flush: true)



//        module.save(flush: true)
//        output.save(flush: true)

        def adminRole = new Role('ROLE_ADMIN').save()
        def userRole = new Role('ROLE_USER').save()
        def researcherRole = new Role('ROLE_RESEARCHER').save()

        def testUser1 = new Learner('sam@uw.edu','password','Kinn','Sam').save()
        def testUser2 = new Learner('joe@uw.edu','password','Kesting','Joe').save()
        def testUser3 = new Learner('erin@uw.edu','password','Peach','Erin').save()
        def testUser4 = new Researcher('jason@uw.edu','password','Yeatman','Jason').save()
        def testUser5 = new Researcher('patrick@uw.edu','password','Donnelly','Patrick').save()
        def testUser6 = new Researcher('anat@uw.edu','password','Caspi','Anat').save()

        UserRole.create researcher, researcherRole
        UserRole.create learner, userRole
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

        assert Learner.count() == 4
        assert Researcher.count() == 4
        assert User.count() == 8
        assert Role.count() == 3
        assert UserRole.count() == 8

    }
    def destroy = {
    }
}