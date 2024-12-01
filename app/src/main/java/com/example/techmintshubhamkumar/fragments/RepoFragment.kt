package com.example.techmintshubhamkumar.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.techmintshubhamkumar.R
import com.example.techmintshubhamkumar.adapters.UserAdapter
import com.example.techmintshubhamkumar.databinding.FragmentRepoBinding
import com.example.techmintshubhamkumar.models.GitHubRepo
import com.example.techmintshubhamkumar.repositories.Repository
import com.example.techmintshubhamkumar.viewmodels.GitViewModel
import com.example.techmintshubhamkumar.viewmodels.GitViewModelFactory
import com.example.techmintshubhamkumar.webviews.ProjectWebView


class RepoFragment : Fragment() {
    private lateinit var binding:FragmentRepoBinding
    private val argument : RepoFragmentArgs by navArgs()
    private val viewMode : GitViewModel by viewModels {
        GitViewModelFactory(Repository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRepoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gitHubRepository = argument.repo
        bindData(gitHubRepository)
        binding.projectLink.setOnClickListener {
            val intent = Intent(context, ProjectWebView::class.java)
            intent.putExtra("url", gitHubRepository?.html_url)
            startActivity(intent)
        }



    }

    private fun bindData(gitHubRepository: GitHubRepo?) {
        //Owner image
        Glide.with(requireContext())
            .load(gitHubRepository?.owner?.avatar_url)
            .centerCrop()
            .into(binding.ownerImage)
        //Owner Name
        binding.ownerName.text = gitHubRepository?.owner?.login
        //Repo Description
        binding.repoDescription.text = gitHubRepository?.description

        val adapter = UserAdapter()
        viewMode.loadContributors(gitHubRepository?.contributors_url!!)
        viewMode.contributors.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
        binding.contributorsRv.adapter = adapter
        binding.contributorsRv.layoutManager = LinearLayoutManager(requireContext())



    }

}