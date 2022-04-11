package com.gmail.notifytask3.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.gmail.notifytask3.R
import com.gmail.notifytask3.data.AppDatabase
import com.gmail.notifytask3.data.UsersService
import com.gmail.notifytask3.databinding.FragmentDetailsBinding
import com.gmail.notifytask3.repository.UsersRepository
import com.gmail.notifytask3.util.Extensions.loadImage

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val args: DetailsFragmentArgs by navArgs()
    private val viewModel: DetailsViewModel by lazy {
        val service = UsersService.create()
        val db = AppDatabase.getDatabase(requireActivity().applicationContext)
        val dao = db.usersDao()
        val repository = UsersRepository(service, dao)
        val viewModelFactory = DetailsViewModelFactory(repository, args.email)
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
                textName.text = getString(R.string.user_name, it.firstName, it.lastName)
                textAge.text = getString(R.string.user_age, it.age.toString())
                textEmail.text = it.email
                textPhone.text = it.phone
                it.image.let { picture ->
                    image.loadImage(picture)
                }
            }
        }
    }
}