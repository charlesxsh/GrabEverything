package Actor.Task

import Model.PageGuide

/**
  * Created by Shihao on 5/31/17.
  */

object PageExplorerTasks {
  case class Explore(url:String)
  case class Initialize(rootUrl:String, patterns:PageGuide)
}