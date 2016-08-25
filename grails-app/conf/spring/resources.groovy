import test.TestObject

// Place your Spring DSL code here
beans = {
    myMap(HashMap, ["key1BeanFromResource.groovy": "val1BeanFromResource.groovy"]) { bean ->
        bean.scope = "prototype"
    }

    testObject(TestObject) { bean ->
        bean.scope = "prototype"
        mapIssue1 = myMap
    }

    /*

    could also do something like this if you wanted an anonymous bean...

    testObject(TestObject) { bean ->
        bean.scope = "prototype"
        mapIssue1 = { LinkedHashMap m ->
            put 'someDefaultKey', 'Some Default Value'
        }
    }

     */
}
