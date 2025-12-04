package viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import model.Alumne
import model.Nota

class LlistatViewModel : ViewModel() {
    private val _alumneSeleccionat = MutableLiveData<Alumne?>()
    val alumneSeleccionat: LiveData<Alumne?> get() = _alumneSeleccionat

    private val _notes = MutableLiveData<MutableList<Nota>>(mutableListOf())
    val notes: LiveData<MutableList<Nota>> get() = _notes

    private val _notesFiltrades = MutableLiveData<List<Nota>>()
    val notesFiltrades: LiveData<List<Nota>> get() = _notesFiltrades

    fun establecerAlumne(alumne: Alumne) {
        _alumneSeleccionat.value = alumne
    }

    fun establecerNotes(notas: List<Nota>) {
        _notes.value = notas.toMutableList()
    }

    fun filtrarNotesAlumne() {
        val alumneSeleccionat = _alumneSeleccionat.value ?: return
        val notesFiltradas = _notes.value?.filter { it.alumne.nom == alumneSeleccionat.nom } ?: emptyList()
        _notesFiltrades.value = notesFiltradas
    }
}
