package com.example.surfacevcollisions
//color ar en int
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import com.example.surfacevcollisions.R.drawable

open class Ball(
    context: Context,
    var posX: Float,
    var posY: Float,
    var size: Float,
    var speedX: Float,
    var speedY: Float,
    var color: Int){

    var paint = Paint()


    init {
        paint.color = color
    }


    fun checkBounds(bounds: Rect){
//        if(posX-size < 0){
//            this.speedX *= -1
//            this.posX += speedX*2
//        }
//        if(posX+size > bounds.right){
//            speedX *= -1
//        }
//        if(posY-size < 0){
//            speedY *= -1
//        }
//        if(posY+size > bounds.bottom){
//            speedY *= -1
//        }
//    }
        if (posX-size < bounds.left || posX+size > bounds.right){
            speedX *= -1
            posX += speedX*1.2f
        }
        if(posY-size < bounds.top || posY+size > bounds.bottom){
            speedY *= -1
            posY += speedY*1.2f
        }
    }

    fun update() {
        posX += speedX
        posY += speedY

    }


    open fun draw(canvas: Canvas?) {
        canvas?.drawCircle(posX, posY, size, paint)
    }

}

//dessa gor att ena bollen studsar mot vaggarna
//fun checkBounds(bounds: Rect){
//    if (posX-size < bounds.left || posX+size > bounds.right){
//        speedX *= -1
//        posX += speedX*1.2f
//    }
//    if(posY-size < bounds.top || posY+size > bounds.bottom){
//        speedY *= -1
//        posY += speedY*1.2f
//    }

//lateinit var bitmap: Bitmap
//init {
//    bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.dude)
//}