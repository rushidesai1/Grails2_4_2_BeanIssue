package test

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

@TestMixin(GrailsUnitTestMixin)
class SpringBeansSpec extends Specification {
    static loadExternalBeans = true //this loads beans present in resources.groovy

    void 'test bean properties'() {
        setup:
        def testObject1 = grailsApplication.mainContext.testObject
        def testObject2 = grailsApplication.mainContext.testObject

        expect: 'test TestObject beans are not the same instance'
        !testObject1.is(testObject2)

        and: 'the TestObject beans do not share values injected in to the bean'
        !testObject1.mapIssue1.is(testObject2.mapIssue1)
    }
}
