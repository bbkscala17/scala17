//import java.io.IOException
//import java.util.Scanner


object App extends App{
  println("Control unit started")
  val controlUnit: ControlUnit  = new ControlUnit ()
  val securityControlUnit: ControlUnit  = new SecurityControlUnit()
  for(x <- 1 to 10){
    println(x)
    controlUnit.pollSensors()
    securityControlUnit.pollSensors()
  }
}
//
//object App {
//  private val EXIT: String = "exit"
//  private val POLL: String = "poll"
//
//  @throws[IOException]
//  def main(args: Array[String]) {
//    val controlUnit: ControlUnit = new ControlUnit
//    val scanner: Scanner = new Scanner(System.in)
//    var input: String = ""
//    while (input != EXIT) {
//      println("Type \"poll\" to poll all sensors once or \"exit\" to exit")
//      input = scanner.nextLine
//      if (input == POLL) {
//        controlUnit.pollSensors()
//      }
//    }
//  }
//}
