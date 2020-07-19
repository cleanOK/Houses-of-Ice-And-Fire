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
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dmytrod.housesoficefire.R
import com.dmytrod.housesoficefire.domain.entity.HouseEntity
import com.dmytrod.housesoficefire.presentation.viewmodel.HouseListViewModel
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

    private val houseViewModel by viewModel<HouseListViewModel>()
    private val houseAdapter = SimpleItemRecyclerViewAdapter {
            val intent = Intent(this, HouseDetailActivity::class.java)
                .putExtra(HouseDetailActivity.ARG_HOUSE_URL, it.url)
            startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.title = title

        val list = findViewById<RecyclerView>(R.id.item_list)
        list.adapter = houseAdapter
        list.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        houseViewModel.houseList.observe(this, Observer {
            when (it) {
                is HouseListViewModel.HouseListState.Success -> houseAdapter.submitList(it.list)
                is HouseListViewModel.HouseListState.Error -> handleError(it)
            }
        })
        houseViewModel.isLoading.observe(this, Observer {
            list.visibility = if (it) View.GONE else View.VISIBLE
            findViewById<ProgressBar>(R.id.progress).visibility =
                if (it) View.VISIBLE else View.GONE
        })

    }

    private fun handleError(error: HouseListViewModel.HouseListState.Error) {
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
            holder.contentView.text = item.name
            holder.itemView.setOnClickListener { onItemClick.invoke(item) }
        }

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
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