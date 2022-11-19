package com.wayne.algorithm.ktxs

fun List<Int>.tryFold(): Int =
    fold(1){accumulator, element ->
        accumulator + element
    }

fun List<Int>.tryReduce():Int =
    reduce{ acc, i ->
        acc - i
    }
data class AssociateBean(val key:String, val value:String)
fun List<AssociateBean>.tryAssociateToFunc():Map<String,String> = associateBy({it.key}, {it.value})