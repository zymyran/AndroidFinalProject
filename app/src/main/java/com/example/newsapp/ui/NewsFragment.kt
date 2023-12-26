package com.example.newsapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.MainActivity
import com.example.newsapp.R
import com.example.newsapp.adapters.NewsAdapter
import com.example.newsapp.api.Resource
import com.example.newsapp.databinding.FragmentNewsBinding
import com.example.newsapp.mvvm.NewsViewModel

private const val TAG = "NewsFragment"
class NewsFragment : Fragment() {
    private  var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : NewsViewModel
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = (activity as MainActivity).viewModel
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        itemListener()

        viewModel.breakingNews.observe(viewLifecycleOwner, Observer { it->
            when(it){
                is Resource.Success ->{
                    binding.progressBar.visibility = View.INVISIBLE
                    it.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }
                is Resource.Error ->{
                    binding.progressBar.visibility = View.INVISIBLE
                    it.message?.let { message->
                        Log.e(TAG, "An error occured: $message")
                    }
                }
                is Resource.Loading ->{
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        })
    }
    private fun setUpRecyclerView(){
        newsAdapter = NewsAdapter()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = newsAdapter
        }
    }
    private fun itemListener(){
        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article",it)
            }
            findNavController().navigate(
                R.id.action_newsFragment_to_articleFragment,
                bundle
            )
        }
    }
}