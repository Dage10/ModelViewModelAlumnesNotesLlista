package com.daviddam.modelviewmodelalumnesnotesllista

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.daviddam.modelviewmodelalumnesnotesllista.databinding.FragmentNotesBinding
import viewmodel.NotesViewModel
import viewmodel.SharedViewModel


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentNotes.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentNotes : Fragment() {
    // TODO: Rename and change types of parameters
    private val viewModel: NotesViewModel by activityViewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var binding: FragmentNotesBinding
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
        binding = FragmentNotesBinding.inflate(inflater, container, false)

        sharedViewModel.alumneSeleccionat.observe(viewLifecycleOwner) { alumne ->
            alumne?.let { viewModel.establecerAlumne(it) }
            binding.textNom.text = alumne?.nom
            binding.textGrup.text = alumne?.grup
        }

        binding.btnAfegir.setOnClickListener {
            val modul = binding.editModul.text.toString()
            val nota = binding.editNota.text.toString().toDoubleOrNull()
            if (modul.isNotEmpty() && nota != null) {
                viewModel.afegirNota(modul, nota)
                sharedViewModel.afegirNota(modul, nota)
                binding.editModul.text.clear()
                binding.editNota.text.clear()
            }
        }

        binding.btnLlistat.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentNotes_to_fragmentLlistat)
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
         * @return A new instance of fragment FragmentNotes.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentNotes().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}