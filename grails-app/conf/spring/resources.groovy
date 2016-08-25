import test.TestA
import test.TestObject

// Place your Spring DSL code here
beans = {

    list(ArrayList, ["list1BeanFromResource.groovy"]) { bean ->
        bean.scope = "prototype"
    }

    map(HashMap, ["key1BeanFromResource.groovy": "val1BeanFromResource.groovy"]) { bean ->
        bean.scope = "prototype"
    }

    testA(TestA) { bean ->
        bean.scope = "prototype"
        name = "nameBeanFromResource.groovy"
    }

    testObject(TestObject) { bean ->
        bean.scope = "prototype"
        a = "aFromResource.groovy"
        mapIssue1 = ["key1FromResource.groovy": "value1FromResource.groovy"]
        mapIssue2 = new HashMap(["key2FromResource.groovy": "value2FromResource.groovy"])//["key": "value"]
        mapIssue3 = new HashMap() {{put("key1FromResource.groovy", "value3FromResource.groovy")}}   //Java way
        listIssue = ["el1"]
        //If we use the bean of map we get desired result i.e. Map will not be singleton
        mapFix = ref("map")
        listFix = ref("list")
        testA1 = new TestA(name: "name1FromResource.groovy")
        testA2 = ref(testA)
    }
}
