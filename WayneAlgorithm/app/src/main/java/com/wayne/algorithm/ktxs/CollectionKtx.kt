package com.wayne.algorithm.ktxs

fun List<Int>.tryFold(): Int =
    fold(1){accumulator, element ->
        accumulator + element
    }

fun List<Int>.tryReduce():Int =
    reduce{ acc, i ->
        acc - i
    }
