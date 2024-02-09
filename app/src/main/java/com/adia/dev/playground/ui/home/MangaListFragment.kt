package com.adia.dev.playground.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.adia.dev.playground.adapters.MangaListAdapter
import com.adia.dev.playground.databinding.FragmentMangaListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class MangaListFragment : Fragment() {

    private var _binding: FragmentMangaListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MangaListViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMangaListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = MangaListAdapter(emptyList())
        binding.mangaListRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.mangaListRecyclerView.adapter = adapter

        viewModel.mangas.observe(viewLifecycleOwner) { mangas ->
            adapter.updateMangas(mangas)
        }

        viewModel.getMangas()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
