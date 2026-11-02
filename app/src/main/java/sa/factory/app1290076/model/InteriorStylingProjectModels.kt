package sa.factory.app1290076.model

import kotlinx.serialization.Serializable

@Serializable
data class RoomStylingConsultation(
    val id: String,
    val title: String,
    val description: String,
    val priceMinorUnits: Long,
    val iconVector: String = "verified"
)

@Serializable
data class InteriorStylingProject(
    val requestId: String,
    val items: List<RoomStylingConsultation>,
    val customerPhone: String,
    val totalAmountMinorUnits: Long,
    val status: InteriorStylingProjectStatus = InteriorStylingProjectStatus.PENDING,
    val createdAtEpochMs: Long = System.currentTimeMillis()
)

enum class InteriorStylingProjectStatus {
    PENDING,
    ACCEPTED,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED
}
