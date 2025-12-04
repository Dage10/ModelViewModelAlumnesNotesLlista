package viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import model.Alumne

class AlumneViewModel : ViewModel() {
    private val _alumnesList = MutableLiveData<List<Alumne>>()
    val alumnesList: LiveData<List<Alumne>> get() = _alumnesList

    private val _alumneSeleccionat = MutableLiveData<Alumne?>()
    val alumneSeleccionat: LiveData<Alumne?> get() = _alumneSeleccionat

    init {
        cargarAlumnes()
    }

    private fun cargarAlumnes() {
        val alumnes = listOf(
            Alumne("Lucía", "DAM2"),
            Alumne("Luis", "DAM2"),
            Alumne("Manel", "DAM2"),
            Alumne("Guillem", "DAU1")
        )
        _alumnesList.value = alumnes
    }

    fun seleccionarAlumne(alumne: Alumne) {
        _alumneSeleccionat.value = alumne
    }
}
