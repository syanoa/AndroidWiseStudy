package com.wayne.algorithm.ktxs

import java.util.*

/**
 * 非常规交换数据位置，仅适用于RecyclerView拖拽后同步RecyclerView中的List数据，切勿用于其他用途
 */
fun <E> MutableList<E>.dragSort(fromPosition: Int, toPosition: Int): MutableList<E> {
    if (fromPosition < toPosition) {
        //从上往下拖动，每滑动一个item，都将list中的item向下交换，向上滑同理。
        for (i in fromPosition until toPosition) {
            Collections.swap(this, i, i + 1)
        }
    } else {
        for (i in fromPosition downTo toPosition + 1) {
            Collections.swap(this, i, i - 1)
        }
    }
    return this
}