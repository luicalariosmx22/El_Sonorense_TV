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
                description = "Lo mejor del deporte local y regional",
                startTime = "18:00",
                endTime = "19:00",
                category = "Deportes",
                isLive = isCurrentTime("18:00", "19:00")
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
                title = "Música Regional",
                description = "Lo mejor de la música regional mexicana",
                startTime = "15:00", 
                endTime = "16:00",
                category = "Entretenimiento",
                isLive = isCurrentTime("15:00", "16:00")
            ),
            Program(
                id = "ent_3",
                title = "Noches de Sonora",
                description = "Música, entrevistas y entretenimiento nocturno",
                startTime = "22:00",
                endTime = "23:00",
                category = "Entretenimiento", 
                isLive = isCurrentTime("22:00", "23:00")
            )
        )
    }
    
    private fun getSpecialPrograms(): List<Program> {
        return listOf(
            Program(
                id = "special_1", 
                title = "Tradiciones Sonorenses",
                description = "Explorando la rica cultura y tradiciones de Sonora",
                startTime = "10:00",
                endTime = "11:00",
                category = "Especiales",
                isLive = isCurrentTime("10:00", "11:00")
            ),
            Program(
                id = "special_2",
                title = "Documentales",
                description = "Documentales sobre historia y cultura regional", 
                startTime = "16:00",
                endTime = "17:00",
                category = "Especiales",
                isLive = isCurrentTime("16:00", "17:00")
            ),
            Program(
                id = "special_3",
                title = "El Sonorense Especial",
                description = "Programa especial con invitados destacados",
                startTime = "19:00",
                endTime = "20:00",
                category = "Especiales",
                isLive = isCurrentTime("19:00", "20:00")
            )
        )
    }
    
    private fun isCurrentTime(startTime: String, endTime: String): Boolean {
        return try {
            val currentHour = currentTime.get(Calendar.HOUR_OF_DAY)
            val currentMinute = currentTime.get(Calendar.MINUTE)
            val currentTimeMinutes = currentHour * 60 + currentMinute
            
            val startParts = startTime.split(":")
            val startMinutes = startParts[0].toInt() * 60 + startParts[1].toInt()
            
            val endParts = endTime.split(":")
            val endMinutes = endParts[0].toInt() * 60 + endParts[1].toInt()
            
            currentTimeMinutes in startMinutes..endMinutes
        } catch (e: Exception) {
            false
        }
    }
}