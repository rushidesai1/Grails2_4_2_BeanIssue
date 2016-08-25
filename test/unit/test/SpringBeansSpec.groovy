package test

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import grails.util.Holders
import org.codehaus.groovy.grails.commons.GrailsApplication
import spock.lang.Specification

/**
 * Created by desair4 on 8/24/2016.
 */
@TestMixin(GrailsUnitTestMixin)
class SpringBeansSpec extends Specification {
    static loadExternalBeans = true //this loads beans present in resources.groovy

    GrailsApplication grailsApplication
    TestObject testObject1
    TestObject testObject2
    Map mapIssue1_1
    Map mapIssue1_2
    Map mapIssue1_3
    Map mapIssue2_1
    Map mapIssue2_2
    Map mapIssue2_3
    Map mapFix1
    Map mapFix2

    TestA testAIssue1
    TestA testAIssue2
    TestA testAFix1
    TestA testAFix2


    void setup() {
        grailsApplication = Holders.grailsApplication.mainContext.getBean("grailsApplication")
        testObject1 = grailsApplication.mainContext.getBean("testObject", TestObject) //Doing fresh getBean
        testObject2 = grailsApplication.mainContext.getBean("testObject", TestObject) //Doing fresh getBean
        mapIssue1_1 = testObject1.mapIssue1
        mapIssue1_2 = testObject1.mapIssue2
        mapIssue1_3 = testObject1.mapIssue3
        mapIssue2_1 = testObject2.mapIssue1
        mapIssue2_2 = testObject2.mapIssue2
        mapIssue2_3 = testObject2.mapIssue3
        mapFix1 = testObject1.mapFix
        mapFix2 = testObject2.mapFix
        testAIssue1 = testObject1.testA1
        testAIssue2 = testObject2.testA1
        testAFix1 = testObject1.testA2
        testAFix2 = testObject2.testA2
    }

    void cleanup() {

    }

    def "test Test"() {
        when:
        println ""

        println testObject1
        println testObject2
        println "Working as expected : Both objects are different since they were declared prototype"

        println ""

        println "Map 1"
        mapIssue1_1.put("key1Overridden", "value1Overridden")       //We are only adding additional value to testObject1.mapIssue1 but testObject2.mapIssue1 will also get the same value
        println mapIssue1_1 //different way of instantiation check resources.groovy
        println mapIssue2_1 //different way of instantiation check resources.groovy
        println "NOT Working as expected : Both maps got the updated value. This means both maps have same instance in two different objects"

        println ""

        println "Map 2"
        mapIssue1_2.put("key2Overridden", "value2Overridden")   //We are only adding additional value to testObject1.mapIssue2 but testObject2.mapIssue2 will also get the same value
        println mapIssue1_2 //different way of instantiation check resources.groovy
        println mapIssue2_2 //different way of instantiation check resources.groovy
        println "NOT Working as expected : Both maps got the updated value. This means both maps have same instance in two different objects"

        println ""

        println "Map 3"
        mapIssue1_3.put("key3Overridden", "value3Overridden")   //We are only adding additional value to testObject1.mapIssue3 but testObject2.mapIssue3 will also get the same value
        println mapIssue1_3 //different way of instantiation check resources.groovy
        println mapIssue2_3 //different way of instantiation check resources.groovy
        println "NOT Working as expected : Both maps got the updated value. This means both maps have same instance in two different objects"

        println ""
        println "None of three methods of declaring map allow us to get prototype map"
        println ""

        println "Objects : "
        mapFix1.put("keyFixOverridden", "valueFixOverridden")
        println mapFix1
        println mapFix2
        println "Working as expected : Both maps DID NOT get the updated value. This means both maps are NOT same instance in two different objects"

        TestA testAIssue1 = testObject1.testA1
        TestA testAIssue2 = testObject2.testA1
        TestA testAFix1 = testObject1.testA2
        TestA testAFix2 = testObject2.testA2

        testAIssue1.name = "nameOverridden"

        println ""

        println "Class TestA"
        println testAIssue1
        println testAIssue2
        println "NOT Working as expected : Both Objects got the updated value. This means both Objects are instance. This is not what we intended when we specified new TestA() in resources.groovy"

        println ""

        println testAFix1
        println testAFix2
        println "Working as expected : Both objects DID NOT get the updated value. This means both objects are NOT same instances."

        println ""

        then:
        testObject1 != testObject2
        mapIssue1_1 == mapIssue2_1      //I expect this condition shouldn't be true. I expect both the maps should be different instances.
        mapIssue1_2 == mapIssue2_2      //I expect this condition shouldn't be true. I expect both the maps should be different instances.
        mapIssue1_3 == mapIssue2_3      //I expect this condition shouldn't be true. I expect both the maps should be different instances.

        testAIssue1 == testAIssue2  //I expect this condition shouldn't be true. I expect both the Objects should be different instances since I am specifying new TestA().

        mapFix1 != mapFix2      //Only declaring Map this way results in
        testAFix1 != testAFix2   //Only declaring it as a bean with prototype results in bean being prototype
    }
}
