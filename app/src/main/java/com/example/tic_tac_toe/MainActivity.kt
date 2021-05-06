package com.example.tic_tac_toe

import android.annotation.SuppressLint
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),View.OnClickListener {

    var men=true
    var turn=0
    var x=0
    var y=0
    var status=Array(3){IntArray(3)}

    lateinit var sujoy: Array<Array<Button>>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        displayTv
       //val win=MediaPlayer.create(this,R.raw.winner)
        sujoy= arrayOf(
            arrayOf(bt,bt1,bt2) ,
            arrayOf(bt3,bt4,bt5),
            arrayOf(bt6,bt7,bt8)
        )

        for(i in sujoy){
            for(x in i){
                x.setOnClickListener(this)
            }
        }
        reset.setOnClickListener{
            men=true
            sujoystatus()
            turn=0
            val click: MediaPlayer =MediaPlayer.create(this,R.raw.click)
            click.start()
        }
        sujoystatus()
        updatewin()

    }

    @SuppressLint("SetTextI18n")
    private fun updatewin() {
        play1.text="Player X Winning :- $x"
        play2.text="Player 0 Winning :- $y"
    }

    private fun sujoystatus() {
        for(i in 0..2){
            for(j in 0..2){
                status[i][j]=-1
                sujoy[i][j].isEnabled=true
                sujoy[i][j].text=""
            }
        }
    }

    override fun onClick(v: View) {
        val click: MediaPlayer =MediaPlayer.create(this,R.raw.click)
        click.start()
        when(v.id){
            R.id.bt->{
                updatev(row=0,col=0,player=men)

            }
            R.id.bt1->{
                updatev(row=0,col=1,player=men)

            }
            R.id.bt2->{
                updatev(row=0,col=2,player=men)

            }
            R.id.bt3->{
                updatev(row=1,col=0,player=men)

            }
            R.id.bt4->{
                updatev(row=1,col=1,player=men)

            }
            R.id.bt5->{
                updatev(row=1,col=2,player=men)

            }
            R.id.bt6->{
                updatev(row=2,col=0,player=men)

            }
            R.id.bt7->{
                updatev(row=2,col=1,player=men)

            }
            R.id.bt8->{
                updatev(row=2,col=2,player=men)

            }
        }
        turn++
        men=!men
        if(men){
            updatedisplay("Player X Turn")
        }else{
            updatedisplay("Player 0 Turn")
        }
        if (turn==9){
            updatedisplay("Game Draw")
        }
       winner()
    }

    private fun winner() {
        //horizontal
        val win=MediaPlayer.create(this,R.raw.winner)
        for (i in 0..2) {
            if (status[i][0] == status[i][1] && status[i][0] == status[i][2]) {
                if (status[i][0] == 1) {
                    updatedisplay("Player X winner")
                    win.start()
                    x++
                    updatewin()
                    break

                } else if (status[i][0] == 0) {
                    updatedisplay("Player 0 winner")
                    win.start()
                    y++
                    updatewin()
                    break

                }
            }
        }
        //vertical
        for (i in 0..2) {
            if (status[0][i] == status[1][i] && status[0][i] == status[2][i]) {
                if (status[0][i] == 1) {
                    updatedisplay("Player X winner")
                    win.start()
                    x++
                    updatewin()
                    break
                } else if (status[0][i] == 0) {
                    updatedisplay("Player 0 winner")
                    win.start()
                    y++
                    updatewin()
                    break
                }
            }
        }

        //first digonal
        if (status[0][0] == status[1][1] && status[0][0] == status[2][2]) {
            if (status[0][0] == 1) {
                updatedisplay("Player X winner")
                win.start()
                x++
                updatewin()
            } else if (status[0][0] == 0) {
                updatedisplay("Player 0 winner")
                win.start()
                y++
                updatewin()
            }
        }
        //second diagonal
        if (status[0][2] == status[1][1] && status[0][2] == status[2][0]) {
            if (status[0][2] == 1) {
                updatedisplay("Player X winner")
                win.start()
                updatewin()
                x++
            } else if (status[0][2] == 0) {
                updatedisplay("Player 0 winner")
                win.start()
                y++
                updatewin()
            }
        }
    }


    private fun disableButton(){
        for( i in sujoy){
            for(button in i){
                button.isEnabled=false
                button.setBackgroundColor(258745)
            }
        }

    }



    private fun updatedisplay(s: String) {
            displayTv.text=s

            if(s.contains("winner")){
                disableButton()
            }
    }

    private fun updatev(row: Int, col: Int, player: Boolean) {
      val d=if(men) "x" else "0"
        val m=if(men) 1 else 0
        sujoy[row][col].apply {
            isEnabled=false
            setText(d)
        }
        status[row][col]=m
    }

}