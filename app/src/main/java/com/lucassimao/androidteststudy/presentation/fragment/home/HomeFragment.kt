package com.lucassimao.androidteststudy.presentation.fragment.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.lucassimao.androidteststudy.R
import com.lucassimao.androidteststudy.databinding.FragmentHomeBinding
import com.lucassimao.androidteststudy.domain.entity.PlaylistModel
import com.lucassimao.androidteststudy.presentation.fragment.PlaylistViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<PlaylistViewModel>()
    private lateinit var adapter: PlaylistAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        setupLoader()
        setupRecyclerView()

        return binding.root
    }

    private fun setupLoader() {
        viewModel.loader.observe(viewLifecycleOwner) { loading ->
            when (loading) {
                true -> binding.pbLoader.visibility = View.VISIBLE
                else -> binding.pbLoader.visibility = View.INVISIBLE
            }
        }
    }

    private fun setupRecyclerView() {
        adapter = PlaylistAdapter(onItemClick = {
            findNavController().navigate(R.id.action_homeFragment_to_detailFragment)
        })

        binding.rvPlaylistList.adapter = adapter
        viewModel.playlist.observe(viewLifecycleOwner) {
            adapter.submitList(it.getOrNull())
        }
    }

}