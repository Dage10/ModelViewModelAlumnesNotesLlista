package viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import model.Alumne
import model.Nota

class SharedViewModel : ViewModel() {
    private val _alumneSeleccionat = MutableLiveData<Alumne?>()
    val alumneSeleccionat: LiveData<Alumne?> get() = _alumneSeleccionat

    private val _notes = MutableLiveData<MutableList<Nota>>(mutableListOf())
    val notes: LiveData<MutableList<Nota>> get() = _notes

    fun seleccionarAlumne(alumne: Alumne) {
        _alumneSeleccionat.value = alumne
        _notes.value = mutableListOf()
    }

    fun afegirNota(modul: String, valor: Double) {
        val llista = _notes.value ?: mutableListOf()
        llista.add(Nota(modul, valor))
        _notes.value = llista
    }
}