package com.example.surfacevcollisions

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.graphics.Paint
import android.graphics.Rect
import android.icu.text.Transliterator.Position
import android.view.MotionEvent
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

class GameView(context: Context) : SurfaceView(context), SurfaceHolder.Callback, Runnable {
    var thread: Thread? = null
    var running = false
    lateinit var canvas: Canvas
    lateinit var ball1: Ball
    lateinit var ball2: Ball
    lateinit var posX: Position
    lateinit var posY: Position

    //    lateinit var posX: Position
//    lateinit var posY: Position
    var bounds = Rect() //for att kunna studsa m vaggarna
    var mHolder: SurfaceHolder? = holder
    //var holder: SurfaceHolder? = getHolder()

    init {
        if (mHolder != null) {
            mHolder?.addCallback(this)
        }
        // bounds = holder.surfaceFrame //kan fa problem med fragment med denna, denna maste skrivas in i fun surfaceCreated
        setup()
    }

    private fun setup() {
        ball1 = Ball(this.context, 100f, 100f, 30f, 7f, 7f, Color.RED)
        ball2 = Ball(this.context, 400f, 100f, 30f, 0f, 0f, Color.GREEN)
        //ball2 = PlayerBall(this.context, 400f, 100f, 20f, 0f, 0f, Color.GREEN) //Gor sa att vi and bilden fran playerBall
    }

    //gor att bollen studsar:
    fun onBallCollision(b1: Ball, b2: Ball) {
        if (b1.posX < b2.posX && b1.posY < b2.posY){
            b1.speedX = Math.abs(b1.speedX) * -1
        //=sa har gor man om man alltid vill ha negativt tal: absolutvardet av -20  ar 20
            b1.speedY = Math.abs(b1.speedY) * -1
        }
        if(b1.posX < b2.posX && b1.posY > b2.posY){
            b1.speedX = abs(b1.speedX) * -1
            b1.speedY = abs(b1.speedY)
        }
        if(b1.posX > b2.posX && b1.posY < b2.posY){
            b1.speedX = abs(b1.speedX)
            b1.speedY = abs(b1.speedY) * -1
        }
        if(b1.posX > b2.posX && b1.posY < b2.posY){
            b1.speedX = abs(b1.speedX)
            b1.speedY = abs(b1.speedY)
        }

//        b1.speedX *= -1 //denna gor att bollen vander
//        b1.speedY *= -1
//        ball2.paint.color = Color.YELLOW // gor att bollen ska byta farg till gul vid nudd
    }

    //Behovs oxa for att kollision ska ske, pyhtagoras sats: hur langt ifran ar bollarna ifran varandra:
    fun ballIntersects(b1: Ball, b2: Ball) {
        if (sqrt((b1.posX - b2.posX.toDouble()).pow(2.0) + (b1.posY - b2.posY.toDouble()).pow(2.0)) <= b1.size + b2.size) {
            onBallCollision(b1, b2)
        }
    }

    fun start() {
        running = true
        thread =
            Thread(this) //en trad har en konstruktor som tar in en runnable, vilket sker i denna klass ovan
        thread?.start()
    }

    fun stop() {
        running = false
        thread?.join()
//        try {
//            thread?.join() //join betyder att huvudtraden komemr vanta in att traden dor ut av sig sjalv
//        } catch (e: InterruptedException) {
//            e.printStackTrace()
//        }
    }

    fun update() {
        ball1.update()
        ball2.update()
    }

    //med denna kod kan jag rora pa boll2 som star stilla annars
    override fun onTouchEvent(event: MotionEvent?): Boolean {

//        ball2.posX = event!!.x
//        ball2.posY = event!!.y
//        return true
//    }

    val vX =
        event?.x.toString() //ett satt att hantera att en vill ha null och en nonnull versioner av datatyperna
    val vY = event?.y.toString()
    ball2.posX = vX.toFloat()
    ball2.posY = vY.toFloat()
    return true
//    return super.onTouchEvent(event)

}


    fun draw() {
        canvas = holder!!.lockCanvas()
        canvas.drawColor(Color.BLUE)
        ball1.draw(canvas)
        ball2.draw(canvas)
        holder!!.unlockCanvasAndPost(canvas)
    }

    //dessa startar och stoppar min thread:
    override fun surfaceCreated(holder: SurfaceHolder) {

    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        bounds = Rect(0, 0, width, height)
        start()
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        stop()
    }

    //run/metoden ar en metod som vi fick fran interface Runnable och ar kopplat till dess Thread.Run anropas nar vi kor Thread.start()
    //den kor en while loop med var running variable pch anropar update och draw:
    override fun run() {
        while (running) {
            update()
            draw()
            ball1.checkBounds(bounds)
            ballIntersects(ball1, ball2)
        }
    }
}

//Bill: Om man vill forfina studsen sa kan man anvanda trogometri och rakna ut vinkeln pa hastighetsvektorn
// och anvanda den och byta ut studsen i y komponenten


//if(Math.sqrt(Math.pow(b1.posX).toDouble()).pow(2.0) + (b1.posY - b2.posY.toDouble()).pow(2.0)) <=b1.size+b2.size){

//if (Math.sqrt(
//Math.pow(b1.posX - b2.posX.toDouble()).pow(2.0) + Math.pow( //roten ur
//b1.posY - b2.posY.toDouble(),
//Math.pow(2.0, 2.0)
//)
//<= (b1.size + b2.size) {
//
//    onBallCollision(b1, b2)
//}
//}