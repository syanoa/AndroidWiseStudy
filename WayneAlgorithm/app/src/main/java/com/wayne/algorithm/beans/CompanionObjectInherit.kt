package com.wayne.algorithm.beans

import com.wayne.algorithm.R

fun test(){
    val white = WhiteCompanionObjectInherit.Factory
    val black = BlackCompanionObjectInherit.Factory
}
class WhiteCompanionObjectInherit(override val color: Int = R.color.white) : CustomTheme{

    companion object Factory:ThemeFactory(){
        override fun doFactory(): CustomTheme {
            return object :CustomTheme{
                override val color: Int
                    get() = R.color.white

                override fun render() {

                }

            }
        }

    }
    override fun render() {

    }

}
class BlackCompanionObjectInherit(override val color: Int = R.color.black) : CustomTheme{

    companion object Factory:ThemeFactory(){
        override fun doFactory(): CustomTheme {
            return object :CustomTheme{
                override val color: Int
                    get() = R.color.black

                override fun render() {

                }

            }
        }

    }
    override fun render() {

    }

}
interface CustomTheme{
    val color:Int
    fun render()
}

abstract class ThemeFactory{
    abstract fun doFactory():CustomTheme
}