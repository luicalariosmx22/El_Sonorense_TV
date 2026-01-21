package mx.elsonorense.tv.model

data class Program(
    val id: String,
    val title: String,
    val description: String,
    val startTime: String,
    val endTime: String,
    val category: String,
    val isLive: Boolean = false
)

data class ProgramCategory(
    val name: String,
    val programs: List<Program>
)