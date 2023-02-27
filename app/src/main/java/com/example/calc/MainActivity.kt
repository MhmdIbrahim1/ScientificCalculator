package com.example.calc

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.calc.databinding.ActivityMainBinding
import kotlin.math.cos
import kotlin.math.ln
import kotlin.math.log10
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.math.tan

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @SuppressLint("UseCompatLoadingForDrawables", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        // initializing our variable for binding.
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // change color of toolbar to background color of black
        supportActionBar?.setBackgroundDrawable(getDrawable(R.color.black))
        addClickListenerToButtonsNumberAndDot()
        addClickListenerToOperators()
    }

    private fun addClickListenerToOperators() {
        binding.bplus.setOnClickListener {
            binding.idTVprimary.text = getString(R.string.number_format, binding.idTVprimary.text, "+")
        }

        binding.bdiv.setOnClickListener {
            binding.idTVprimary.text = getString(R.string.number_format, binding.idTVprimary.text, "/")
        }

        binding.bbrac1.setOnClickListener {
            binding.idTVprimary.text = getString(R.string.number_format, binding.idTVprimary.text, "(")
        }

        binding.bbrac2.setOnClickListener {
            binding.idTVprimary.text = getString(R.string.number_format, binding.idTVprimary.text, ")")
        }

        binding.bpi.setOnClickListener {
                    // on clicking on pi button we are adding
                    // pi value as 3.142 to our current value.
                    binding.idTVprimary.text = getString(R.string.number_format, binding.idTVprimary.text, "3.142")
                    binding.idTVSecondary.text = binding.bpi.text
       }

        binding.bsin.setOnClickListener {
            binding.idTVprimary.text = getString(R.string.number_format, binding.idTVprimary.text, "sin")
        }

        binding.bcos.setOnClickListener {
            binding.idTVprimary.text = getString(R.string.number_format, binding.idTVprimary.text, "cos")
        }

        binding.btan.setOnClickListener {
            binding.idTVprimary.text = getString(R.string.number_format, binding.idTVprimary.text, "tan")
        }

        binding.binv.setOnClickListener {
            binding.idTVprimary.text = getString(R.string.number_format, binding.idTVprimary.text, "^(-1)")
        }

        binding.bln.setOnClickListener {
            binding.idTVprimary.text = getString(R.string.number_format, binding.idTVprimary.text, "ln")
        }

        binding.blog.setOnClickListener {
            binding.idTVprimary.text = getString(R.string.number_format, binding.idTVprimary.text, "log")
        }

        binding.bminus.setOnClickListener {
            // Get the current text in the primary TextView and convert it to a string
            val str: String = binding.idTVprimary.text.toString()
            // Check if the string is empty and if the last character is not a minus sign
             if (str.isNotEmpty() &&str.last() != '-') {
                binding.idTVprimary.text = getString(R.string.number_format, binding.idTVprimary.text, "-")
                }
        }
        binding.bmul.setOnClickListener {
            // Get the current text in the primary TextView and convert it to a string
            val str: String = binding.idTVprimary.text.toString()
            // Check if the string is empty and if the last character is not a multiplication sign
            if (str.isNotEmpty() && str.last() != '*') {
                binding.idTVprimary.text = getString(R.string.number_format, binding.idTVprimary.text, "*")
            }
        }
        binding.bsqrt.setOnClickListener {
            if(binding.idTVprimary.text.toString().isEmpty()) {
                // if the entered number is empty we are displaying an error message.
                Toast.makeText(this, "Please enter a valid number..", Toast.LENGTH_SHORT).show()
            } else {
                val str: String = binding.idTVprimary.text.toString()
                // on below line we are calculation
                // square root of the given number.
                val r = sqrt(str.toDouble())
                // on below line we are converting our double
                // to string and then setting it to text view.
                val result = r.toString()
                binding.idTVprimary.text = result
            }
        }
        binding.bequal.setOnClickListener {
            val str: String = binding.idTVprimary.text.toString()
            // on below line we are calling an evaluate
            // method to calculate the value of expressions.
            val result: Double = evaluate(str)
            // on below line we are getting result
            // and setting it to text view.
            val r = result.toString()
            binding.idTVprimary.text = r
            binding.idTVSecondary.text = str
        }
        binding.bac.setOnClickListener {
            // on clicking on ac button we are clearing
            // our primary and secondary text view.
            binding.idTVprimary.text = ""
            binding.idTVSecondary.text = ""
        }
        binding.bc.setOnClickListener {
            // on clicking on c button we are clearing
            // the last character by checking the length.
            var str: String = binding.idTVprimary.text.toString()
            if (str != "") {
                str = str.substring(0, str.length - 1)
                binding.idTVprimary.text = str
            }
        }
        binding.bsquare.setOnClickListener {
            if (binding.idTVprimary.text.toString().isEmpty()) {
                // if the entered number is empty we are displaying an error message.
                Toast.makeText(this, "Please enter a valid number..", Toast.LENGTH_SHORT).show()
            } else {
                // on below line we are getting the expression and then calculating the square of the number
                val d: Double = binding.idTVprimary.text.toString().toDouble()
                // on below line we are calculating the square.
                val square = d * d
                // after calculating the square we
                // are setting it to text view.
                binding.idTVprimary.text = square.toString()
                // on below line we are setting
                // the d to secondary text view.
                binding.idTVSecondary.text = getString(R.string.square_result, d.toString(), "²")

            }
        }
        binding.bfact.setOnClickListener {
            if (binding.idTVprimary.text.toString().isEmpty()) {
                // if the entered number is empty we are displaying an error message.
                Toast.makeText(this, "Please enter a valid number..", Toast.LENGTH_SHORT).show()
            } else {
                // on below line we are getting int value
                // and calculating the factorial value of the entered number.
                val value: Int = binding.idTVprimary.text.toString().toInt()
                val fact: Int = factorial(value)
                binding.idTVprimary.text = fact.toString()
                binding.idTVSecondary.text = getString(R.string.factorial_result, value.toString(), "!")
            }

        }
    }

    private fun addClickListenerToButtonsNumberAndDot() {
        binding.b1.setOnClickListener {
            binding.idTVprimary.text = getString(R.string.number_format, binding.idTVprimary.text, "1")
        }

        binding.b2.setOnClickListener {
            binding.idTVprimary.text = getString(R.string.number_format, binding.idTVprimary.text, "2")
        }

        binding.b3.setOnClickListener {
            binding.idTVprimary.text = getString(R.string.number_format, binding.idTVprimary.text, "3")
        }

        binding.b4.setOnClickListener {
            binding.idTVprimary.text = getString(R.string.number_format, binding.idTVprimary.text, "4")
        }

        binding.b5.setOnClickListener {
            binding.idTVprimary.text = getString(R.string.number_format, binding.idTVprimary.text, "5")
        }

        binding.b6.setOnClickListener {
            binding.idTVprimary.text = getString(R.string.number_format, binding.idTVprimary.text, "6")
        }

        binding.b7.setOnClickListener {
            binding.idTVprimary.text = getString(R.string.number_format, binding.idTVprimary.text, "7")
        }

        binding.b8.setOnClickListener {
            binding.idTVprimary.text = getString(R.string.number_format, binding.idTVprimary.text, "8")
        }

        binding.b9.setOnClickListener {
            binding.idTVprimary.text = getString(R.string.number_format, binding.idTVprimary.text, "9")
        }

        binding.b0.setOnClickListener {
            binding.idTVprimary.text = getString(R.string.number_format, binding.idTVprimary.text, "0")
        }
    }

    private fun factorial(n: Int): Int {
        // this method is use to find factorial
        return if (n == 1 || n == 0) 1 else n * factorial(n - 1)
    }

    private fun evaluate(str: String): Double {
        return object : Any() {
            // on below line we ar creating variable
            // for tracking the position and char pos.
            var pos = -1
            var ch =   '\u0000'

            // below method is for moving to next character.
            fun nextChar() {
                // on below line we are incrementing our position
                // and moving it to next position.
                ch = if (++pos < str.length) str[pos] else '\u0000'
            }

            // this method is use to check the extra space
            // present int the expression and removing it.
            fun eat(charToEat: Char): Boolean {
                while (ch == ' ') nextChar()
                // on below line we are checking the char pos
                // if both is equal then we are returning it to true.
                if (ch == charToEat) {
                    nextChar()
                    return true
                }
                return false
            }

            // below method is to parse our
            // expression and to get the ans
            // in this we are calling a parse
            // expression method to calculate the value.
            fun parse(): Double {
                nextChar()
                val x = parseExpression()
                if (pos < str.length) throw RuntimeException("Unexpected: $ch" )
                return x
            }

            // in this method we will only perform addition and
            // subtraction operation on the expression.
            fun parseExpression(): Double {
                var x = parseTerm()
                while (true) {
                    if (eat('+')) x += parseTerm() // addition
                    else if (eat('-')) x -= parseTerm() // subtraction
                    else return x
                }
            }

            // in below method we will perform
            // only multiplication and division operation.
            fun parseTerm(): Double {
                var x = parseFactor()
                while (true) {
                    if (eat('*')) x *= parseFactor() // multiplication
                    else if (eat('/')) x /= parseFactor() // division
                    else return x
                }
            }

            // below method is use to parse the factor
            fun parseFactor(): Double {
                //on below line we are checking for addition
                // and subtraction and performing unary operations.
                if (eat('+')) return parseFactor() // unary plus
                if (eat('-')) return -parseFactor() // unary minus
                // creating a double variable for ans.
                var x: Double
                // on below line we are creating
                // a variable for position.
                val startPos = pos
                // on below line we are checking
                // for opening and closing parenthesis.
                if (eat('(')) { // parentheses
                    x = parseExpression()
                    eat(')')
                } else if (ch in '0'..'9' || ch == '.') {
                    // numbers
                    while (ch in '0'..'9' || ch == '.') nextChar()
                    // on below line we are getting sub string from our string using start and pos.
                    x = str.substring(startPos, pos).toDouble()
                } else if (ch in 'a'..'z') {
                    // on below function we are checking for the operator in our expression.
                    while (ch in 'a'..'z') nextChar()
                    val func = str.substring(startPos, pos)
                    // calling a method to parse our factor.
                    x = parseFactor()
                    // on below line we are checking for square root.
                    x =
                        when (func) {
                            "sqrt" -> sqrt(x)
                            // on below line we are checking for sin function
                            // and calculating sin function using Math class.
                            "sin" -> sin(
                                Math.toRadians(x)
                                // on below line we are calculating the cos value
                            )
                            "cos" -> cos(
                                Math.toRadians(x)
                                // on below line we are calculating
                                // the tan value of our expression.
                            )
                            "tan" -> tan(Math.toRadians(x))
                            // on below line we are calculating
                            // log value of the expression.
                            "log" -> log10(x)
                            // on below line we are calculating
                            // ln value of expression.
                            "ln" -> ln(x)
                            // f we get any error then
                            // we simply return the exception.
                            else -> throw RuntimeException(
                                "Unknown function: $func"
                            )
                        }
                } else {
                    // if the condition not satisfy then we are returning the exception
                    throw RuntimeException("Unexpected: $ch")
                }
                // on below line we are calculating the power of the expression.
                if (eat('^')) x = x.pow(parseFactor()) // exponentiation
                return x
            }
            // at last calling a parse for our expression.
        }.parse()
    }
}
