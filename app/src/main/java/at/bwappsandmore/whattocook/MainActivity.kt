package at.bwappsandmore.whattocook

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import at.bwappsandmore.whattocook.adapter.PagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager.adapter = PagerAdapter(supportFragmentManager, lifecycle)
    }
}