package com.gmail.notifytask3.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.gmail.notifytask3.R
import com.gmail.notifytask3.databinding.FragmentDetailsBinding
import com.gmail.notifytask3.util.Extensions.loadImage
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val args: DetailsFragmentArgs by navArgs()

    @Inject
    lateinit var viewModelFactory: DetailsViewModelFactory.Factory
    private val viewModel: DetailsViewModel by viewModels(factoryProducer = {
        viewModelFactory.create(
            args.email
        )
    })

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