package Actor

import java.io.FileOutputStream
import java.net.URL
import java.nio.channels.Channels

import akka.actor.Actor
import _root_.Actor.Task.PageGrabTasks

/**
  * Created by Shihao on 5/31/17.
  */
class PageGrabActor extends Actor {
  var storagePath:String = _

  override def receive: Receive =  {
    case PageGrabTasks.Grab(urlStr) =>
      println(s"Grab [$urlStr]")
      val url = new URL(urlStr)
      val rbc = Channels.newChannel(url.openStream)
      val filePath = s"$storagePath${urlStr.split("/").last}"
      val fos = new FileOutputStream(filePath, false)
      fos.getChannel.transferFrom(rbc, 0, Long.MaxValue)
      fos.close()

    case PageGrabTasks.Initialize(storagePath) =>
      this.storagePath = storagePath


  }
}