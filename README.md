# GrabEverything
Universal Webpage Spider in Scala using Akka

# Feature
- Support unlimited number of targets, either in multiple threads or in multiple machines to grab if you set up akka actor properly
- Support various types of grabing rules because of the power of Guide
  
# Usage 
```scala
/**
 This is the guide that GrabGraph use to grab targets
 */
val guide = new PageGuide(
    // This is for sub page
    Some(new PageTarget("your css selector for next page","your attribute name in selected node", [Target type:Grab or Page] )), 
    
    // This is for next page
    Some( /* same thing */),
    
    // This is for target in the page, usually are files like image, video, etc.. 
    Some( /* same thing */)
  )

new GrabGraphBuilder()
  .setGuide(guide)
  .setRootUrl(/* url string for starting */)
  .setStoragePath(/* storage path */)
  .build
  .start()
```


