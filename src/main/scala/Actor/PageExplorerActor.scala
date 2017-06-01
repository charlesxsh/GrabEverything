package Actor

import java.net.URL
import java.util.concurrent.{ConcurrentHashMap, ConcurrentMap}

import Model.{PageGuide, PageTarget, TargetType}
import akka.actor.Actor
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import _root_.Actor.Task.{PageExplorerTasks, PageGrabTasks}

/**
  * Created by Shihao on 5/31/17.
  */
class PageExplorerActor extends Actor {
  var guide:PageGuide = _
  var cached:ConcurrentMap[String, Boolean] =  new ConcurrentHashMap[String, Boolean]()

  override def receive: Receive = {
    case PageExplorerTasks.Explore(url) =>
      println(s"Explore [$url]")
      if(! cached.containsKey(url) ){
        cached.put(url, true)
        val page = Jsoup.connect(url).get()
        handlePageTarget(page, guide.nextPage)
        handlePageTarget(page, guide.nextLevel)
        handlePageTarget(page, guide.target)
      }

    case PageExplorerTasks.Initialize(rootUrl, guide) =>
      println("Initialize Task")
      this.guide = guide
      self ! PageExplorerTasks.Explore(rootUrl)
  }

  def handlePageTarget(page:Document, pageTarget: Option[PageTarget]):Unit = {
    pageTarget match {
      case Some(target) =>
        page.select(target.select).forEach((e) => {
          target.targetType match {
            case TargetType.Page => self ! PageExplorerTasks.Explore(e.attr(target.attr))
            case TargetType.Grab => context.actorSelection("../target") ! PageGrabTasks.Grab(e.attr(target.attr))
          }

        })
      case None => {}
    }
  }
}
