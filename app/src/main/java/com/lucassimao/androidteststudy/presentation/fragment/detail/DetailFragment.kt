package com.lucassimao.androidteststudy.presentation.fragment.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.lucassimao.androidteststudy.databinding.FragmentDetailBinding
import com.lucassimao.androidteststudy.presentation.fragment.PlaylistViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private val viewModel by viewModels<PlaylistViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)

        setupLoader()
        setupUI()

        return binding.root
    }

    private fun setupLoader() {
        viewModel.loader.observe(viewLifecycleOwner) { loading ->
            when (loading) {
                true -> binding.pbDetailLoader.visibility = View.VISIBLE
                else -> binding.pbDetailLoader.visibility = View.INVISIBLE
            }
        }
    }

    private fun setupUI() {
        viewModel.getPlaylistDetail.observe(viewLifecycleOwner) {
            binding.apply {
                detailName.text = it.getOrNull()!!.name
                playlistDetail.text = it.getOrNull()!!.details
            }
        }
    }

}