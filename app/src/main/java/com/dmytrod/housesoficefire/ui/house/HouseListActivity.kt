package com.dmytrod.housesoficefire.ui.house

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dmytrod.housesoficefire.R
import com.dmytrod.housesoficefire.domain.entity.HouseEntity
import com.dmytrod.housesoficefire.presentation.viewmodel.HouseViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_item_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [HouseDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class HouseListActivity : AppCompatActivity() {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false
    private val houseViewModel by viewModel<HouseViewModel>()
    private val houseAdapter = SimpleItemRecyclerViewAdapter {
        if (twoPane) {
            val fragment = HouseDetailFragment()
                .apply {
                    arguments = bundleOf(HouseDetailFragment.ARG_ITEM_ID to it.url)
                }
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.item_detail_container, fragment)
                .commit()
        } else {
            val intent = Intent(this, HouseDetailActivity::class.java)
                .putExtra(HouseDetailFragment.ARG_ITEM_ID, it.url)
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.title = title

        if (findViewById<NestedScrollView>(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            twoPane = true
        }

        val list = findViewById<RecyclerView>(R.id.item_list)
        list.adapter = houseAdapter
        houseViewModel.houseList.observe(this, Observer {
            when (it) {
                is HouseViewModel.HouseListState.Success -> houseAdapter.submitList(it.list)
                is HouseViewModel.HouseListState.Error -> handleError(it)
            }
        })
        houseViewModel.isLoading.observe(this, Observer {
            list.visibility = if (it) View.GONE else View.VISIBLE
            findViewById<ProgressBar>(R.id.progress).visibility =
                if (it) View.VISIBLE else View.GONE
        })

    }

    private fun handleError(error: HouseViewModel.HouseListState.Error) {
        if (!error.isHandled) {
            Snackbar.make(root, error.errorMessageRes, Snackbar.LENGTH_SHORT)
                .show()
            error.isHandled = true
        }
    }

    class SimpleItemRecyclerViewAdapter(
        private val onItemClick: (item: HouseEntity) -> Unit
    ) : ListAdapter<HouseEntity, SimpleItemRecyclerViewAdapter.ViewHolder>(diffUtilCallback) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_house_list_content, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = getItem(position)
            holder.idView.text = item.name
            holder.contentView.text = item.name
            holder.itemView.setOnClickListener { onItemClick.invoke(item) }
        }

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val idView: TextView = view.findViewById(R.id.id_text)
            val contentView: TextView = view.findViewById(R.id.content)
        }

        companion object {
            private val diffUtilCallback = object : DiffUtil.ItemCallback<HouseEntity>() {
                override fun areItemsTheSame(oldItem: HouseEntity, newItem: HouseEntity) =
                    oldItem.url == newItem.url

                override fun areContentsTheSame(oldItem: HouseEntity, newItem: HouseEntity) =
                    oldItem == newItem
            }
        }
    }
}