import test.TestObject

// Place your Spring DSL code here
beans = {
    testObject(TestObject) { bean ->
        bean.scope = "prototype"
        mapIssue1 = [:]
    }
}
