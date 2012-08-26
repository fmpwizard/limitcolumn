package code.model

import net.liftweb.mapper._
/**
 * User: colt44
 * Date: 8/25/12
 * Time: 9:29 PM
 */
object MyModel extends MyModel with LongKeyedMetaMapper[MyModel] {
  override def dbTableName = "mymodel"
}

class MyModel extends LongKeyedMapper[MyModel] with ManyToMany {
  def getSingleton = MyModel

  def primaryKeyField = id

  object id extends MappedLongIndex(this)

  //this will cause an exception when trying to run against
  //Postgresql
  object limit extends MappedInt(this)
}
