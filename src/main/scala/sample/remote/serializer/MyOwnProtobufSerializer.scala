package sample.remote.serializer

import akka.serialization.SerializerWithStringManifest
import sample.remote.models._


class MyOwnProtobufSerializer extends SerializerWithStringManifest{

  def identifier: Int = 101110116

  override def manifest(o: AnyRef): String = o.getClass.getName
  final val AddedManifest = classOf[Added].getName
  final val SubtractedManifest = classOf[Subtracted].getName
  final val AddedResultManifest = classOf[AddedResult].getName
  final val SubtractedResultManifest = classOf[SubtractedResult].getName


  override def fromBinary(bytes: Array[Byte], manifest: String): AnyRef = {

    println("inside fromBinary"+manifest)

    manifest match {
      case AddedManifest => Added.parseFrom(bytes)
      case SubtractedManifest => Subtracted.parseFrom(bytes)
      case AddedResultManifest => AddedResult.parseFrom(bytes)
      case SubtractedResultManifest => SubtractedResult.parseFrom(bytes)
    }
  }

  override def toBinary(o: AnyRef): Array[Byte] = {

    println("inside toBinary ")
    o match {
      case a: Added => a.toByteArray
      case s :Subtracted => s.toByteArray
      case aR: AddedResult => aR.toByteArray
      case sR: SubtractedResult => sR.toByteArray
    }
  }
}