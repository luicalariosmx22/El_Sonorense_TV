package mx.elsonorense.tv.data

import mx.elsonorense.tv.model.Program
import mx.elsonorense.tv.model.ProgramCategory
import java.text.SimpleDateFormat
import java.util.*

object ProgrammingData {

    private val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    private val currentTime = Calendar.getInstance()

    fun getTodaysProgramming(): List<ProgramCategory> {
        return listOf(
            ProgramCategory(
                name = "Noticias",
                programs = getNewsPrograms()
            ),
            ProgramCategory(
                name = "Deportes",
                programs = getSportsPrograms()
            ),
            ProgramCategory(
                name = "Entretenimiento",
                programs = getEntertainmentPrograms()
            ),
            ProgramCategory(
                name = "Especiales",
                programs = getSpecialPrograms()
            )
        )
    }
    
    private fun getNewsPrograms(): List<Program> {
        return listOf(
            Program(
                id = "news_1",
                title = "El Sonorense Informa",
                description = "Noticias locales y nacionales más importantes del día",
                startTime = "06:00",
                endTime = "07:00",
                category = "Noticias",
                isLive = isCurrentTime("06:00", "07:00")
            ),
            Program(
                id = "news_2",
                title = "Panorama Sonorense",
                description = "Análisis profundo de los acontecimientos en Sonora",
                startTime = "13:00",
                endTime = "14:00",
                category = "Noticias",
                isLive = isCurrentTime("13:00", "14:00")
            ),
            Program(
                id = "news_3",
                title = "El Sonorense Nocturno",
                description = "Resumen del día y perspectivas para mañana",
                startTime = "21:00",
                endTime = "22:00",
                category = "Noticias",
                isLive = isCurrentTime("21:00", "22:00")
            )
        )
    }

    private fun getSportsPrograms(): List<Program> {
        return listOf(
            Program(
                id = "sports_1",
                title = "Deportes Sonora",
                description = "Noticias deportivas locales y regionales",
                startTime = "12:00",
                endTime = "13:00",
                category = "Deportes",
                isLive = isCurrentTime("12:00", "13:00")
            ),
            Program(
                id = "sports_2",
                title = "Fútbol en Vivo",
                description = "Partidos y análisis del fútbol mexicano",
                startTime = "20:00",
                endTime = "22:00",
                category = "Deportes",
                isLive = isCurrentTime("20:00", "22:00")
            ),
            Program(
                id = "sports_3",
                title = "Deportes Internacional",
                description = "Noticias deportivas del mundo",
                startTime = "23:00",
                endTime = "00:00",
                category = "Deportes",
                isLive = isCurrentTime("23:00", "00:00")
            )
        )
    }

    private fun getEntertainmentPrograms(): List<Program> {
        return listOf(
            Program(
                id = "ent_1",
                title = "Buenos Días Sonora",
                description = "Programa matutino con música, entrevistas y entretenimiento",
                startTime = "07:00",
                endTime = "09:00",
                category = "Entretenimiento",
                isLive = isCurrentTime("07:00", "09:00")
            ),
            Program(
                id = "ent_2",
                title = "Al Tingo al Tango con Doña Lupe",
                description = "El programa de juegos más divertido de Sonora con Doña Lupe",
                startTime = "10:00",
                endTime = "11:30",
                category = "Entretenimiento",
                isLive = isCurrentTime("10:00", "11:30")
            ),
            Program(
                id = "ent_3",
                title = "Música Regional",
                description = "Lo mejor de la música regional mexicana",
                startTime = "15:00",
                endTime = "16:00",
                category = "Entretenimiento",
                isLive = isCurrentTime("15:00", "16:00")
            ),
            Program(
                id = "ent_4",
                title = "Noches de Sonora",
                description = "Música, entrevistas y entretenimiento nocturno",
                startTime = "22:00",
                endTime = "23:00",
                category = "Entretenimiento",
                isLive = isCurrentTime("22:00", "23:00")
            ),
            Program(
                id = "ent_5",
                title = "Tradiciones Vivas",
                description = "Especiales sobre las tradiciones sonorenses",
                startTime = "14:00",
                endTime = "15:00",
                category = "Entretenimiento",
                isLive = isCurrentTime("14:00", "15:00")
            ),
            Program(
                id = "ent_6",
                title = "Mesa Redonda",
                description = "Debate y análisis político",
                startTime = "19:00",
                endTime = "20:00",
                category = "Entretenimiento",
                isLive = isCurrentTime("19:00", "20:00")
            )
        )
    }

    private fun getSpecialPrograms(): List<Program> {
        return listOf(
            Program(
                id = "special_1",
                title = "Sonora Tierra Querida",
                description = "Documentales y reportajes sobre la historia y cultura sonorense",
                startTime = "16:00",
                endTime = "17:00",
                category = "Especiales",
                isLive = isCurrentTime("16:00", "17:00")
            ),
            Program(
                id = "special_2",
                title = "El Sonorense Presenta",
                description = "Programación especial con invitados y eventos exclusivos",
                startTime = "18:00",
                endTime = "19:00",
                category = "Especiales",
                isLive = isCurrentTime("18:00", "19:00")
            )
        )
    }

    private fun isCurrentTime(startTime: String, endTime: String): Boolean {
        try {
            val start = dateFormat.parse(startTime)
            val end = dateFormat.parse(endTime)
            val now = dateFormat.parse(dateFormat.format(currentTime.time))
            
            return now != null && start != null && end != null && 
                   (now.after(start) || now == start) && now.before(end)
        } catch (e: Exception) {
            return false
        }
    }
}
