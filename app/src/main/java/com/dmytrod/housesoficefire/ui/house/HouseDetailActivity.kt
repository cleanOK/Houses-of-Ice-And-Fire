package com.dmytrod.housesoficefire.ui.house

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.dmytrod.housesoficefire.R
import com.dmytrod.housesoficefire.domain.Result
import com.dmytrod.housesoficefire.presentation.viewmodel.HouseDetailViewModel
import com.dmytrod.housesoficefire.presentation.viewmodel.HouseListViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_item_detail.*
import kotlinx.android.synthetic.main.activity_item_detail.root
import kotlinx.android.synthetic.main.activity_item_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

/**
 * An activity representing a single Item detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a [HouseListActivity].
 */
class HouseDetailActivity : AppCompatActivity() {

    private val houseDetailViewModel by viewModel<HouseDetailViewModel> {
        parametersOf(intent.getStringExtra(ARG_HOUSE_URL))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)
        setSupportActionBar(detail_toolbar)

        // Show the Up button in the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        houseDetailViewModel.houseDetails.observe(this, Observer {
            when (it) {
                is Result.Success -> {
                    supportActionBar?.title = it.data.name
                    words.text = it.data.words
                    coatOfArms.text = it.data.coatOfArms
                    region.text = it.data.region
                }
                is Result.Failure -> {
                    Snackbar.make(root, it.errorMessageRes, Snackbar.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            android.R.id.home -> {

                // This ID represents the Home or Up button. In the case of this
                // activity, the Up button is shown. For
                // more details, see the Navigation pattern on Android Design:
                //
                // http://developer.android.com/design/patterns/navigation.html#up-vs-back

                navigateUpTo(Intent(this, HouseListActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    companion object {
        const val ARG_HOUSE_URL = "house_url"
    }
}