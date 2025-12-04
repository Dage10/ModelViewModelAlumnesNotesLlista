package com.daviddam.modelviewmodelalumnesnotesllista

import androidx.fragment.app.activityViewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.daviddam.modelviewmodelalumnesnotesllista.databinding.FragmentAlumneBinding
import viewmodel.AlumneViewModel
import viewmodel.SharedViewModel
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentAlumne.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentAlumne : Fragment() {
    // TODO: Rename and change types of parameters
    private val viewModel: AlumneViewModel by activityViewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var binding: FragmentAlumneBinding
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAlumneBinding.inflate(inflater, container, false)

        val adapter = AlumneAdapter(emptyList()) { alumne ->
            viewModel.seleccionarAlumne(alumne)
            sharedViewModel.seleccionarAlumne(alumne)
            findNavController().navigate(R.id.action_fragmentAlumne_to_fragmentNotes)
        }

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.alumnesList.observe(viewLifecycleOwner) { alumnes ->
            adapter.updateList(alumnes)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentAlumne.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentAlumne().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}