import java.util.concurrent.{ConcurrentHashMap, ConcurrentMap}

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

import scala.collection.mutable.ArrayBuffer
import scala.util.control.Breaks
import java.io.FileOutputStream
import java.net.URL
import java.nio.channels.Channels
import java.nio.channels.ReadableByteChannel
import java.nio.file.Files
import java.nio.file.attribute.FileAttribute

import Graph.{GrabGraph, GrabGraphBuilder}
import Model.{PageGuide, PageTarget, TargetType}


/**
  * Created by Shihao on 5/30/17.
  */

object Main {
  def main(args: Array[String]): Unit = {
    val guide = new PageGuide(
        None,
        Some(new PageTarget("a[href^=\"http://www.lesmao.com/thread\"]","href", TargetType.Page)),
        Some(new PageTarget("img[src^=\"http://x.yx\"]","src", TargetType.Grab))
      )

    new GrabGraphBuilder()
      .setGuide(guide)
      .setRootUrl("http://www.lesmao.com/thread-16133-1-1.html")
      .setStoragePath("/Users/Shihao/Desktop/images/")
      .build
      .start()
  }
}
