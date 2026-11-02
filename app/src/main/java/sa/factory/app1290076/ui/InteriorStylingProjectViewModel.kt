package sa.factory.app1290076.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import sa.factory.app1290076.data.InteriorStylingProjectRepository
import sa.factory.app1290076.model.InteriorStylingProject
import sa.factory.app1290076.model.RoomStylingConsultation
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class InteriorStylingProjectViewModel(
    private val repository: InteriorStylingProjectRepository
) : ViewModel() {
    val catalog: StateFlow<List<RoomStylingConsultation>> = repository.getAvailableCatalog()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val activeRequest: StateFlow<InteriorStylingProject?> = repository.getActiveRequest()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    fun submitNewOrder(request: InteriorStylingProject) {
        viewModelScope.launch {
            repository.submitRequest(request)
        }
    }
}
