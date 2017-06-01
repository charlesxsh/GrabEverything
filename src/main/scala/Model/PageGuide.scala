package Model

import Model.TargetType.TargetType

/**
  * Created by Shihao on 5/31/17.
  */
object TargetType extends Enumeration {
  type TargetType = Value
  val Page, Grab = Value
}

class PageTarget(val select:String, val attr:String, val targetType: TargetType)
class PageGuide(val nextLevel:Option[PageTarget], val nextPage:Option[PageTarget], val target: Option[PageTarget])

