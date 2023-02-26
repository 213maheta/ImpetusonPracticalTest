package com.impetuson.practicaltest.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.aves.practicaltest.sealed.MainEvent
import com.impetuson.practicaltest.R
import com.impetuson.practicaltest.adapter.NewsAdapter
import com.impetuson.practicaltest.databinding.ActivityMainBinding
import com.impetuson.practicaltest.model.ResponseNews
import com.impetuson.practicaltest.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    @Inject lateinit var adapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.rcvNews.adapter = adapter

        mainViewModel.setObserver()
        lifecycleScope.launchWhenStarted {
            mainViewModel.event.collect{
                when(it)
                {
                    MainEvent.ShowProgressBar->{
                        binding.progressBar.visibility = View.VISIBLE

                    }
                    is MainEvent.ResponseList<*>->{
                        val list = it.data as List<ResponseNews>
                        if(list.size>0)
                        {
                            binding.tvNodataFound.visibility = View.INVISIBLE
                        }
                        else
                        {
                            binding.tvNodataFound.visibility = View.VISIBLE
                        }
                        adapter.newsList.clear()
                        adapter.newsList.addAll(list)
                        adapter.notifyDataSetChanged()
                        binding.progressBar.visibility = View.GONE
                        binding.serachBar.visibility = View.VISIBLE
                    }
                    is MainEvent.ShowToast<*>->{
                        binding.progressBar.visibility = View.GONE
                        binding.tvNodataFound.visibility = View.VISIBLE
                        val message = it.message
                        Toast.makeText(this@MainActivity, message, Toast.LENGTH_LONG).show()
                    }
                    else -> {
                    }
                }
            }
        }

        setSearchBar()
    }

    private fun setSearchBar() {
        binding.serachBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { mainViewModel.filterNewsList(it.trim()) }
                return false
            }
        })
    }

    override fun onResume() {
        super.onResume()
        mainViewModel.getNewsList()
    }
}