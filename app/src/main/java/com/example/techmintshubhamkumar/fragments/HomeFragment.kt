package com.example.techmintshubhamkumar.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.techmintshubhamkumar.R
import com.example.techmintshubhamkumar.adapters.HomeAdapter
import com.example.techmintshubhamkumar.databinding.FragmentHomeBinding
import com.example.techmintshubhamkumar.repositories.Repository
import com.example.techmintshubhamkumar.viewmodels.GitViewModel
import com.example.techmintshubhamkumar.viewmodels.GitViewModelFactory


class HomeFragment : Fragment() {
    private lateinit var binding:FragmentHomeBinding
    private val viewmodel : GitViewModel by viewModels {
        GitViewModelFactory(Repository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = HomeAdapter({
            val action = HomeFragmentDirections.actionHomeFragmentToRepoFragment(it)
            findNavController().navigate(action)
        })

        viewmodel.gitRepositories.observe(viewLifecycleOwner) { result ->
            result.fold(
                onSuccess = { repoList ->
                    Log.d("repos",repoList.size.toString())
                    adapter.submitList(repoList)
                },
                onFailure = { error ->
                    Log.e("ErrorHomeFragment", "Error fetching repositories", error)
                }
            )
        }

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

    }




}