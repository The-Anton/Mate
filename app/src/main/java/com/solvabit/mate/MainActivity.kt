package com.solvabit.mate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val introsliderAdaptor = IntrosliderAdaptor(
        listOf(
            IntroSlide(
                "If opportunity doesn't knock, build a door!",
                "With MATE, register yourself for great events around you.",
                R.drawable.image1
            ),
            IntroSlide(
                "Live in the moment!",
                "Keep yourself up to date With MATE's realtime cloud servers .",
                R.drawable.image2
            ),
            IntroSlide(
                "I notice everything, I just don't speak on it!",
                "MATE will always make it's presence felt through timely notifications.",
                R.drawable.image3
            ),
            IntroSlide(
                "The comeback is always stronger than the setback!",
                "With MATE, track performance of your favourite teams.",
                R.drawable.image4
            )
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        IntroSliderViewPager.adapter = introsliderAdaptor
        setupIndicator()
        setCurrentIndicator(0)
        IntroSliderViewPager.registerOnPageChangeCallback(object :
        ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int){
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        }
        )
        buttonnext.setOnClickListener {
            if(IntroSliderViewPager.currentItem + 1 < introsliderAdaptor.itemCount){
                IntroSliderViewPager.currentItem += 1
            }else{
                Intent(applicationContext, welcomescreen::class.java).also {
                    startActivity(it)
                    finish()
                }

            }
        }
        textskipintro.setOnClickListener {
            Intent(applicationContext, welcomescreen::class.java).also {
                startActivity(it)
                finish()

            }
        }

        //if (auth.isSignInWithEmailLink(emailLink))







        val user= FirebaseAuth.getInstance().currentUser
        if(user!= null)
        {

                if (user.isEmailVerified) {

                    val intent = Intent(this, Leaderboard::class.java)
                    intent.flags =
                        Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)

                    //Toast.makeText(this, "Successfully Logged in", Toast.LENGTH_LONG).show()

                }


        }
    }




    private fun setupIndicator(){
        val indicators = arrayOfNulls<ImageView>(introsliderAdaptor.itemCount)
        val layoutParams: LinearLayout.LayoutParams=
            LinearLayout.LayoutParams(WRAP_CONTENT , WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(applicationContext)
            indicators[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
                this?.layoutParams = layoutParams
            }
            indicatorsContainer.addView(indicators[i])
        }

    }
    private fun setCurrentIndicator(index: Int){
        val childcount = indicatorsContainer.childCount
        for(i in 0 until childcount){
            val imageView = indicatorsContainer[i] as ImageView
            if(i == index){
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active
                    )
                )
            }else{
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive)
                )
            }
        }
    }
}
