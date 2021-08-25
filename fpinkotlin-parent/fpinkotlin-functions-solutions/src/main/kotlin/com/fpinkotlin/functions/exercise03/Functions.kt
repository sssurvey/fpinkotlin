package com.fpinkotlin.functions.exercise03


fun square(n: Int) = n * n

fun triple(n: Int) = n * 3

fun <T, U, V> compose(f: (U) -> V, g: (T) -> U): (T) -> V {
    return {
        f(g(it))
    }
}

// Same as the §compose()§ but verbose and clearer version
fun <T, U, V> composeEasy(f: (U) -> V, g: (T) -> U): (T) -> V {
    val function1 = f
    val function2 = g
    val returnFunction: (T) -> V = { T ->
        function1(
            function2(T)
        )
    }
    return returnFunction
}

val add: (Int) -> (Int) -> Int = { a -> { b -> a + b } }

fun main() {

    fun intToString(int: Int): String {
        return "$int"
    }

    fun stringToInt(string: String): Int {
        return string.toInt()
    }

    val composed = compose(
        ::stringToInt,
        ::intToString
    )

    val composed2 = composeEasy(
        ::stringToInt,
        ::intToString
    )

    composed(123).also {
        println(it)
    }

    composed2(321).also {
        println(it)
    }
}