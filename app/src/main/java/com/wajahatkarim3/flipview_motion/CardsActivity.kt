package com.wajahatkarim3.flipview_motion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.thetechnocafe.gurleensethi.liteutils.RecyclerAdapterUtil
import kotlinx.android.synthetic.main.activity_cards.*

class CardsActivity : AppCompatActivity() {

    lateinit var recyclerAdapter: RecyclerAdapterUtil<CardModel>
    val cardsList = arrayListOf<CardModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cards)

        setupViews()
        initData()
    }

    fun setupViews()
    {
        // RecyclerView
        recyclerAdapter = RecyclerAdapterUtil(this, cardsList, R.layout.card_item_layout)
        recyclerAdapter.addOnDataBindListener { itemView, item, position, _ ->
            var imgFront = itemView.findViewById<ImageView>(R.id.imgFront)
            imgFront.cameraDistance = 8000f
            imgFront.setImageResource(item.cardImage)

            var motionlayout = itemView.findViewById<MotionLayout>(R.id.cardMotionLayout)
            motionlayout.onTransitionCompleted { ml, currentId ->
                if (currentId == R.id.end)
                {
                    //motionlayout.setTransition(R.id.end, R.id.flip)
                }
            }

            imgFront.setOnClickListener {
                Log.e("MOTION", motionlayout.currentState.toString())
            }
        }
        recyclerCards.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerCards.adapter = recyclerAdapter
    }

    fun initData()
    {
        cardsList.add(CardModel(R.drawable.amazon))
        cardsList.add(CardModel(R.drawable.bmo))
        cardsList.add(CardModel(R.drawable.visa))
        cardsList.add(CardModel(R.drawable.marquis))
        cardsList.add(CardModel(R.drawable.paypal))
        recyclerAdapter.notifyDataSetChanged()
    }

    data class CardModel (
        var cardImage: Int = -1
    )
}

fun MotionLayout.onTransitionCompleted(callback: (motionLayout: MotionLayout?, currentId: Int) -> Unit)
{
    setTransitionListener(object : MotionLayout.TransitionListener{
        override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {

        }

        override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
        }

        override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {
        }

        override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
            callback.invoke(p0, p1)
        }
    })
}
