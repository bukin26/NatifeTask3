package com.gmail.notifytask3.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.gmail.notifytask3.data.User
import com.gmail.notifytask3.databinding.FragmentListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private val adapter = UsersAdapter(
        onClick = ::adapterOnClick,
        usersFetchCallback = ::fetchUsers
    )
    private val viewModel: ListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = adapter
        }
        viewModel.users.observe(viewLifecycleOwner) { newUsers ->
            adapter.submitList(newUsers)
        }
    }

    private fun fetchUsers() {
        viewModel.getUsers()
    }

    private fun adapterOnClick(user: User) {
        val direction = ListFragmentDirections.actionListFragmentToDetailsFragment(user.email)
        NavHostFragment.findNavController(this@ListFragment).navigate(direction)
    }
}