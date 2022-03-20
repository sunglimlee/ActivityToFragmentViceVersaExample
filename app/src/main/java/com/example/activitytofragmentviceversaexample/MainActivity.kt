package com.example.activitytofragmentviceversaexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity(), BlankFragment.OnFramentInteractionListener {
    private lateinit var fragmentContainer : FrameLayout
    private lateinit var editText : EditText
    private lateinit var button : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragmentContainer = findViewById(R.id.fragment_container)
        editText = findViewById(R.id.edittext)
        button = findViewById(R.id.button)
        button.setOnClickListener {
            openFragment(editText.text.toString())
        }
    }

    override fun onFragmentInteraction(text: String) {
        super.onFragmentInteraction(text)
        editText.setText(text)
        onBackPressed() // 이걸 해줘야 Fragment가 없어진다. 알겠제? 알다시피 Activity는 Fragment뒤에 숨어있었거든...
    }

    private fun openFragment(text : String) {
        //이제까지 이해됐다. 이제부터가 아주 재미있는 파트이다.
        val blankFragment : BlankFragment = BlankFragment.newInstance(text)
        val fragmentManager : FragmentManager = supportFragmentManager
        val fragmentTransaction : FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.add(R.id.fragment_container, blankFragment, "BLANK_FRAGMENT").commit()

    }
}