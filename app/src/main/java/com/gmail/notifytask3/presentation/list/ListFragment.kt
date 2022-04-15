package com.gmail.notifytask3.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.gmail.notifytask3.UsersApp
import com.gmail.notifytask3.data.User
import com.gmail.notifytask3.databinding.FragmentListBinding
import javax.inject.Inject

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private val adapter = UsersAdapter(
        onClick = ::adapterOnClick,
        usersFetchCallback = ::fetchUsers
    )

    @Inject
    lateinit var viewModelFactory: ListViewModelFactory
    private val viewModel: ListViewModel by viewModels(factoryProducer = { viewModelFactory })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireContext().applicationContext as UsersApp).appComponent.injectListFragment(this)
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