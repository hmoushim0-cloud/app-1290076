package sa.factory.app1290076.data

import sa.factory.app1290076.model.InteriorStylingProject
import sa.factory.app1290076.model.RoomStylingConsultation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

interface InteriorStylingProjectRepository {
    fun getAvailableCatalog(): Flow<List<RoomStylingConsultation>>
    fun getActiveRequest(): Flow<InteriorStylingProject?>
    suspend fun submitRequest(request: InteriorStylingProject): Result<String>
}

class InMemoryInteriorStylingProjectRepository : InteriorStylingProjectRepository {
    private val _catalog = MutableStateFlow<List<RoomStylingConsultation>>(listOf(
        RoomStylingConsultation("1", "باقة استشارة تصميم جدران الصالة والمعيشة", "دراسة تفصيلية لتكسيات الجدران والمواد وتنسيق الإضاءة الجدارية واللوحات الفنية للصالة", 5000L, "verified"),
        RoomStylingConsultation("2", "باقة تنسيق وتوزيع أثاث المجلس الفاخر", "مخطط توزيع أثاث ثنائي الأبعاد مع اختيار قطع الأثاث والأقمشة والألوان للمجالس الفخمة", 9500L, "star")
    ))
    private val _active = MutableStateFlow<InteriorStylingProject?>(null)

    override fun getAvailableCatalog(): Flow<List<RoomStylingConsultation>> = _catalog.asStateFlow()
    override fun getActiveRequest(): Flow<InteriorStylingProject?> = _active.asStateFlow()

    override suspend fun submitRequest(request: InteriorStylingProject): Result<String> {
        _active.value = request
        return Result.success(request.requestId)
    }
}
