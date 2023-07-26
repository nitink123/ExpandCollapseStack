package com.example.expandcollapsestack
//import android.os.Bundle
//import android.view.View
//import android.widget.Button
//import android.widget.LinearLayout
//import androidx.appcompat.app.AppCompatActivity
//import com.example.expandcollapsestack.R
//
//class MainActivity : AppCompatActivity() {
//
//    private lateinit var stackLayout: LinearLayout
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        stackLayout = findViewById(R.id.stackLayout)
//        setupClickListeners()
//    }
//
//    private fun setupClickListeners() {
//        for (i in 0 until stackLayout.childCount) {
//            val view = stackLayout.getChildAt(i)
//            val button = view.findViewById<Button>(R.id.button1)
//            button.setOnClickListener { onButtonClick(view) }
//        }
//    }
//
//    private fun onButtonClick(clickedView: View) {
//        for (i in 0 until stackLayout.childCount) {
//            val view = stackLayout.getChildAt(i)
//            if (view == clickedView) {
//                if (view.isSelected) {
//                    // Collapsed state - If already expanded, collapse it
//                    view.isSelected = false
//                } else {
//                    // Expanded state - Expand the clicked view and collapse others
//                    for (j in 0 until stackLayout.childCount) {
//                        val otherView = stackLayout.getChildAt(j)
//                        otherView.isSelected = (otherView == clickedView)
//                    }
//                }
//            } else {
//                // Semi-Collapsed state - Reset other views to Semi-Collapsed state
//                view.isSelected = false
//            }
//        }
//    }
//}
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.expandcollapsestack.R

class MainActivity : AppCompatActivity() {

    private lateinit var stackLayout: LinearLayout
    private val itemContainers = mutableListOf<LinearLayout>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        stackLayout = findViewById(R.id.stackLayout)
        collectItemContainers()
    }

    private fun collectItemContainers() {
        for (i in 0 until stackLayout.childCount) {
            val view = stackLayout.getChildAt(i)
            if (view is LinearLayout) {
                itemContainers.add(view)
            }
        }
    }

    fun onButtonClick(view: View) {
        val index = itemContainers.indexOfFirst { it == view.parent } // Find the clicked view index

        if (index != -1) {
            for (i in itemContainers.indices) {
                val container = itemContainers[i]
                val item = container.getChildAt(0) as? TextView // Assuming the first child is the TextView
                val button = container.getChildAt(1) as? Button // Assuming the second child is the Button

                if (i == index) {
                    // Toggle the clicked view's visibility
                    if (item?.visibility == View.VISIBLE) {
                        item.visibility = View.GONE
                        button?.text = "Expand"
                    } else {
                        item?.visibility = View.VISIBLE
                        button?.text = "Collapse"
                    }
                } else {
                    // Collapse other views
                    item?.visibility = View.GONE
                    button?.text = "Expand"
                }
            }
        }
    }
}

