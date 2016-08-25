# Grails2_4_2_BeanIssue

in resources.groovy if we declare a bean like this 

```
beans = {
  testObject(TestObject){
    map = new HashMap()  // or [:]
    //And also if we declare any object like this
    testA = new TestA()
  }
}
```

and Now if we DI testObject bean or do Holders.grailsApplication.mainContext.getBean("testObject"), then the bean we get will have singleton 'map' and singelton 'testA' object. 

I want to know if this is a bug or it is working as designed. It is completely counter intuitive that it would work like this since we are specifically doing new and so we expect a new bean being injected everytime.


Use the Unit test case to see more detailed version of my question.

Thanks in advance for clarification !!!
