package com.daviddam.modelviewmodelalumnesnotesllista

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.daviddam.modelviewmodelalumnesnotesllista.databinding.FragmentLlistatBinding
import viewmodel.LlistatViewModel
import viewmodel.SharedViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentLlistat.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentLlistat : Fragment() {
    // TODO: Rename and change types of parameters
    private val viewModel: LlistatViewModel by activityViewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var binding: FragmentLlistatBinding
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
        binding = FragmentLlistatBinding.inflate(inflater, container, false)
        val adapter = NotaAdapter()

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        sharedViewModel.alumneSeleccionat.observe(viewLifecycleOwner) { alumne ->
            alumne?.let {
                viewModel.establecerAlumne(it)
                binding.tvNomAlumne.text = getString(R.string.alumne_format, it.nom, it.grup)
            }
        }

        sharedViewModel.notes.observe(viewLifecycleOwner) { notes ->
            viewModel.establecerNotes(notes)
        }

        binding.btnCercar.setOnClickListener {
            viewModel.filtrarNotesAlumne()
        }

        viewModel.notesFiltrades.observe(viewLifecycleOwner) { notesFiltradas ->
            adapter.submitList(notesFiltradas)
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
         * @return A new instance of fragment FragmentLlistat.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentLlistat().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}