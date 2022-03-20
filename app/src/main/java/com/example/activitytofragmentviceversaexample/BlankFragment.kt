package com.example.activitytofragmentviceversaexample

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import java.lang.RuntimeException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val TEXT = "text"

/**
 * A simple [Fragment] subclass.
 * Use the [BlankFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BlankFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var mText: String? = null
    private var mListener : OnFramentInteractionListener? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFramentInteractionListener) {
            mListener = context as OnFramentInteractionListener
        } else {
            throw RuntimeException("$context must implement onFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    private lateinit var editTextFragment : EditText
    private lateinit var buttonFragment : Button

    //onCreate는 어디서 사용되는 걸까? 어디가 연결되는 지점이 있어야 하는데...
    //이제 생각났다. 알다시피 외부에서 사용할 수 있도록 되어있잖아... 일단 계속 가자.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mText = it.getString(TEXT)
        }
    }
    //여기에서 view가 생성된다.
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //여기서 바로 리턴을 해버리는게 아니라. 일단 작업을 하고나서 리턴을 할거다.
        val view : View = inflater.inflate(R.layout.fragment_blank, container, false)
        buttonFragment = view.findViewById(R.id.button_fragment)
        editTextFragment = view.findViewById(R.id.edittext_fragment)
        editTextFragment.setText(mText)
        editTextFragment.requestFocus()
        buttonFragment.setOnClickListener {
            val sendBackText = editTextFragment.text.toString()
            sendBack(sendBackText)
        }
        return view

    }

    private fun sendBack(sendBackText: String) {
        if (mListener != null) {
            mListener!!.onFragmentInteraction(sendBackText)
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BlankFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(text: String) =
            BlankFragment().apply {
                arguments = Bundle().apply {
                    putString(TEXT, text)
                }
            }
    }

    interface OnFramentInteractionListener {
        fun onFragmentInteraction(text : String) : Unit {

        }
    }
}

