package com.wayne.algorithm.array

//O(2)
fun duplicateZerosO2(arr: IntArray): IntArray {
    var des = IntArray(size = arr.size)
    var desOffset = 0
    for(source in arr){
         if(source == 0){
             des[desOffset] = 0
             if(desOffset + 1 < des.size) {//protection for case of boundary
                 desOffset += 1
                 des[desOffset] = 0
             }
         }else{
             des[desOffset] =  source
         }
        if(desOffset + 1 >= des.size){
            //copying reaches the size of destination.
            return des
        }
        desOffset += 1
    }
    return des
}

//O(1)
fun duplicateZeros(arr: IntArray) {
    var possibleDups = 0
    var length_ = arr.size - 1

    // Find the number of zeros to be duplicated
    // Stopping when left points beyond the last element in the original array
    // which would be part of the modified array
    for (left in 0..length_ - possibleDups) {

        // Count the zeros
        if (arr[left] == 0) {

            // Edge case: This zero can't be duplicated. We have no more space,
            // as left is pointing to the last element which could be included
            if (left == length_ - possibleDups) {
                // For this zero we just copy it without duplication.
                arr[length_] = 0
                length_ -= 1
                break
            }
            possibleDups++
        }
    }

    // Start backwards from the last element which would be part of new array.
    val last = length_ - possibleDups

    // Copy zero twice, and non zero once.
    for (i in last downTo 0) {
        if (arr[i] == 0) {
            arr[i + possibleDups] = 0
            possibleDups--
            arr[i + possibleDups] = 0
        } else {
            arr[i + possibleDups] = arr[i]
        }
    }
}