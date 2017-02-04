package sml

class SubInstruction(label: String, op: String, val result: Int, val op1: Int, val op2: Int)
  extends Instruction(label, op) {

  override def execute(m: Machine) {
    val value1 = op1
    val value2 = op2

    m.regs(result) = value1 - value2
    println("Result to register" + result +  " is " + m.regs(result) + " for values " + value1 + " " + value2)
  }

  override def toString(): String = {
    super.toString + " " + op1 + " + " + op2 + " to " + result
  }
}

object SubInstruction {
  def apply(label: String, result: Int, op1: Int, op2: Int) =
    new SubInstruction(label, "sub", result, op1, op2)
}
