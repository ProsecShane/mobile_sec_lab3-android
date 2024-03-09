package com.prosecshane.android_lab3.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.google.android.material.button.MaterialButton
import com.prosecshane.android_lab3.R
import com.prosecshane.android_lab3.domain.model.Feedback
import com.prosecshane.android_lab3.domain.network.CallStatus
import com.prosecshane.android_lab3.presentation.viewmodel.LabViewModel
import com.prosecshane.android_lab3.util.millisToString
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: LabViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val callButton = findViewById<MaterialButton>(R.id.call_button)
        val callText = findViewById<TextView>(R.id.call_text)

        val fbEditText = findViewById<EditText>(R.id.feedback_collect_text)
        val fbButton = findViewById<MaterialButton>(R.id.feedback_collect_button)

        val fbItemIds = listOf(
            R.id.feedback_date_one to R.id.feedback_content_one,
            R.id.feedback_date_two to R.id.feedback_content_two,
            R.id.feedback_date_three to R.id.feedback_content_three,
        )

        bindCallButton(callButton)
        bindFbButton(fbButton)

        fbEditText.setText(viewModel.feedback.value)
        fbEditText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.setFeedback((p0?: "").toString())
            }
            override fun afterTextChanged(p0: Editable?) {}
        })

        lifecycleScope.launch {
            viewModel.callStatus.collect {
                callText.text = when (it) {
                    CallStatus.Error -> getString(R.string.call_failure)
                    CallStatus.InProgress -> getString(R.string.call_getting)
                    CallStatus.None -> getString(R.string.call_none)
                    is CallStatus.Success -> getString(R.string.call_success, it.ip)
                }
            }
        }

        lifecycleScope.launch {
            viewModel.storedFeedback.collect {
                styleFeedbackItems(fbItemIds, it.takeLast(3).reversed())
            }
        }
    }

    private fun bindCallButton(button: MaterialButton) {
        button.setOnClickListener {
            viewModel.getIp()
        }
    }

    private fun bindFbButton(button: MaterialButton) {
        button.setOnClickListener {
            viewModel.addFeedback(Feedback(content = viewModel.feedback.value))
        }
    }

    private fun styleFeedbackItems(ids: List<Pair<Int, Int>>, fbItems: List<Feedback>) {
        var i = 0
        for ((dateId, contentId) in ids) {
            val date = findViewById<TextView>(dateId)
            val content = findViewById<TextView>(contentId)

            if (i < fbItems.size) {
                date.text = millisToString(fbItems[i].date)
                content.text = fbItems[i].content
            } else {
                date.text = ""
                content.text = ""
            }

            i += 1
        }
    }
}
