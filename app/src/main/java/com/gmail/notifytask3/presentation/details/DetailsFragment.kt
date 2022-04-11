package com.gmail.notifytask3.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.gmail.notifytask3.data.AppDatabase
import com.gmail.notifytask3.data.UsersService
import com.gmail.notifytask3.databinding.FragmentDetailsBinding
import com.gmail.notifytask3.repository.UsersRepository

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val args: DetailsFragmentArgs by navArgs()
    private val viewModel: DetailsViewModel by lazy {
        val service = UsersService.create()
        val db = AppDatabase.getDatabase(requireActivity().applicationContext)
        val dao = db.usersDao()
        val repository = UsersRepository(service, dao)
        val viewModelFactory = DetailsViewModelFactory(repository, args.id)
        ViewModelProvider(viewModelStore, viewModelFactory)
            .get(DetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.user.observe(viewLifecycleOwner) {
            with(binding) {
                textName.text = "${it.name?.first}  ${it.name?.last}"
                textAge.text = it.dob?.age.toString()
                textEmail.text = it.email
                textPhone.text = it.phone
                it.picture?.large?.let { picture ->
                    if (picture.isNotBlank()) {
                        Glide.with(requireContext())
                            .load(picture)
                            .into(image)
                    }
                }
            }
        }
    }
}