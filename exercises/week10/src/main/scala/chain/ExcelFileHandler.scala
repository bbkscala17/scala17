package chain

case class ExcelFileHandler(s: String) extends Handler {

  var nextHandler: Option[Handler] = None

  override def setHandler(handler: Handler): Unit = nextHandler = Some(handler)

  override def process(file: File): Unit = file.fileType match {
    case "excel" => println("Process and saving excel file by " + s)
    case _ => nextHandler.isEmpty match {
      case false => nextHandler.get match {
        case h: Handler => {
          println(s + " forwards request to " + h.getHandlerName())
          h.process(file)
        }
      }
      case true => println("File not supported type " + file.fileType)
    }
  }

  override def getHandlerName(): String = s
}