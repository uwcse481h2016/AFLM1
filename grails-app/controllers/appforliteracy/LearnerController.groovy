package appforliteracy

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Transactional(readOnly = true)

class LearnerController extends grails.plugin.springsecurity.ui.UserController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured('ROLE_USER')
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Learner.list(params), model:[learnerCount: Learner.count()]
    }

    @Secured('ROLE_USER')
    def show(Learner learner) {
        respond learner
    }

    @Secured(['ROLE_RESEARCHER', 'ROLE_ADMIN'])
    def create() {
        respond new Learner(params)
    }

    @Secured('ROLE_USER')
    def home() {
        Learner r = Learner.user.findByEmail(params.email)
        //[fname:r.user.firstName, modules:r.getModules()]
        [fname:r.user.firstName]
    }

    @Secured('ROLE_USER')
    def startModule() {
        String controller = params.type
        String id = params.id
        println(controller)
        println(id)
        println(params.test)
        redirect(controller: controller, action: "start", id: id)
    }

    @Transactional
    def save(Learner learner) {
        if (learner == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (learner.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond learner.errors, view:'create'
            return
        }

        learner.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'learner.label', default: 'Learner'), learner.userID])
                redirect learner
            }
            '*' { respond learner, [status: CREATED] }
        }
    }

    @Secured('ROLE_USER')
    def edit(Learner learner) {
        respond learner
    }

    @Secured('ROLE_USER')
    @Transactional
    def update(Learner learner) {
        if (learner == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (learner.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond learner.errors, view:'edit'
            return
        }

        learner.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'learner.label', default: 'Learner'), learner.userID])
                redirect learner
            }
            '*'{ respond learner, [status: OK] }
        }
    }

    @Secured('ROLE_RESEARCHER')
    @Transactional
    def delete(Learner learner) {

        if (learner == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        learner.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'learner.label', default: 'Learner'), learner.userID])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'learner.label', default: 'Learner'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
