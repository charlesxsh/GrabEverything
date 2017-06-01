package Actor.Task

/**
  * Created by Shihao on 5/31/17.
  */
object PageGrabTasks {

  case class Grab(url: String)
  case class Initialize(storagePath:String)
}
