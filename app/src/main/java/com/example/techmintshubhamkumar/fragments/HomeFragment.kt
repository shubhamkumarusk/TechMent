package com.example.techmintshubhamkumar.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
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

    }




}