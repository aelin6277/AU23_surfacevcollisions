package com.example.surfacevcollisions
//
//import android.content.Context
//import android.graphics.Bitmap
//import android.graphics.BitmapFactory
//import android.graphics.Canvas
//
//class PlayerBall( //en klass som 'rver fran var ballklass
//    context: Context,
//    posX: Float,
//    posY: Float,
//    size: Float,
//    speedX: Float,
//    speedY: Float,
//    color: Int,
//) : Ball(context, posX, posY, size, speedX, speedY, color) {
//
//
//    lateinit var bitmap: Bitmap
//
// //decordeRecourses ar for att hamta en bild
//    init {
//        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.pelota)
//    }
//    override fun draw(canvas: Canvas?) {
//        val centerX = posX - bitmap.width/2
//        val centerY = posY - bitmap.height/2
//        canvas?.drawBitmap(bitmap,centerX,centerY,null)
//        //maste ta in en paint i en bild for att kunna andra pa den fardiga bilden, gora
//    // halvgenomskilning eller fade:a ut, kan lagga pa fargfilter.
//    }
//}

//glom inte att ha open fore en class eller funktion som man vill arva ifran