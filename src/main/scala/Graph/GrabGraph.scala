package Graph

import Actor.Task.{PageExplorerTasks, PageGrabTasks}
import Actor.{PageExplorerActor, PageGrabActor}
import Model.PageGuide
import akka.actor.{ActorSystem, Props}

/**
  * Created by Shihao on 5/31/17.
  */

class GrabGraph() {
  private[Graph] var rootUrl:String = _
  private[Graph] var guide:PageGuide = _
  private[Graph] var storagePath: String = _
  private var system:ActorSystem = _

  def start():Unit = {
    println("Start running...")
    system = ActorSystem("spider-system")
    val explorerRef = system.actorOf(Props[PageExplorerActor],"webpage")
    val targetRef = system.actorOf(Props[PageGrabActor],"target")
    explorerRef ! PageExplorerTasks.Initialize (rootUrl, guide)
    targetRef ! PageGrabTasks.Initialize("")
  }

}

class GrabGraphBuilder{
  private val newGraph = new GrabGraph
  def build:GrabGraph = newGraph
  def setRootUrl(rootUrl:String):GrabGraphBuilder = {
    this.newGraph.rootUrl = rootUrl
    this
  }
  def setGuide(guide: PageGuide):GrabGraphBuilder = {
    this.newGraph.guide = guide
    this
  }
  def setStoragePath(path: String):GrabGraphBuilder = {
    this.newGraph.storagePath = path
    this
  }

}