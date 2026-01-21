# 📺 El Sonorense TV - Android TV App

Una aplicación nativa de Android TV para **El Sonorense**, implementada con **Leanback UI Framework** para una experiencia óptima en televisores Android.

## 🚀 Características

- **📱 Android TV Nativo**: Interfaz optimizada con Leanback Framework
- **📺 Streaming en Vivo**: Transmisión HLS con ExoPlayer
- **📅 Programación**: Horarios con indicadores de contenido en vivo
- **🎨 Branding Personalizado**: Colores oficiales de El Sonorense
- **📱 Multi-dispositivo**: Compatible con Android TV, teléfonos y tablets
- **🔄 Navegación Intuitiva**: Controles de TV con soporte para control remoto

## 🎨 Diseño

### Colores Oficiales
- **Naranja**: `#e6442e` 
- **Amarillo**: `#fed658`
- **Negro**: `#191519`

### Interfaz
- **Layout Vertical**: Cards organizadas en columnas para fácil navegación
- **Iconos Personalizados**: Generados con IconKitchen
- **Temas Responsivos**: Diferentes temas para TV, Player y Programación

## 📋 Funcionalidades

### 🔴 Ver TV en Vivo
- Streaming HLS de alta calidad
- Player con controles completos
- Navegación de regreso al menú
- Auto-hide de controles

### 📅 Ver Programación  
- Horarios categorizados (Noticias, Deportes, Entretenimiento, Especiales)
- Indicadores de programas en vivo
- Navegación por categorías
- Simulación de programación dinámica

### 🔀 Stream Alternativo
- Opción de stream backup
- Misma calidad HLS
- Interfaz unificada

## 🛠 Tecnologías

- **Kotlin** + **Gradle Kotlin DSL**
- **AndroidX Media3** (ExoPlayer) 
- **Leanback Framework**
- **Material Design Components**
- **HLS Streaming Protocol**

## 📱 Compatibilidad

- **Android TV**: minSdk 21, targetSdk 34
- **Android Móvil**: Interfaz adaptativa
- **Orientación**: Landscape para TV, adaptativo para móvil

## 🏗 Arquitectura

```
app/
├── src/main/kotlin/mx/elsonorense/tv/
│   ├── MainActivity.kt              # Entry point
│   ├── MainBrowseFragment.kt        # Menu principal Leanback
│   ├── PlayerActivity.kt            # Reproductor HLS
│   ├── ProgrammingActivity.kt       # Programación
│   ├── CardPresenter.kt             # Presentador de cards
│   ├── StreamOption.kt              # Modelo de stream
│   └── adapters/                    # Adaptadores de programación
├── res/
│   ├── layout/                      # Layouts XML
│   ├── drawable/                    # Drawables y backgrounds
│   ├── mipmap/                      # Iconos adaptativos
│   └── values/                      # Colores, strings, themes
└── AndroidManifest.xml              # Configuración de app
```

## 🔧 Configuración

### Requisitos
- Android Studio Arctic Fox o superior
- SDK Android 34
- Kotlin 1.9.10+
- Gradle 8.1.4+

### Instalación
```bash
git clone https://github.com/luicalariosmx22/El_Sonorense_TV.git
cd El_Sonorense_TV
./gradlew assembleDebug
```

### Deployment en Android TV
```bash
# Conectar Android TV via ADB
adb connect [TV_IP_ADDRESS]:5555

# Instalar APK
adb install app/build/outputs/apk/debug/app-debug.apk

# Abrir app
adb shell am start -n mx.elsonorense.tv/.MainActivity
```

## 🎯 URLs de Streaming

- **Stream Principal**: `https://s5.mexside.net:1936/elsonorense/elsonorense/playlist.m3u8`
- **Stream Alternativo**: Misma URL (backup)

## 🐛 Resolución de Problemas

### ❌ CardPresenter Crash
**Problema**: `Binary XML file line #23: You must supply a layout_width attribute`
**Solución**: ✅ Resuelto usando layout personalizado en lugar de ImageCardView nativo

### 🎨 Overlay Naranja 
**Problema**: brandColor tapaba la interfaz
**Solución**: ✅ Removido brandColor del Fragment

### 📱 Layout Responsive
**Problema**: Cards muy pequeñas o texto ilegible  
**Solución**: ✅ Layout vertical con dimensiones optimizadas (400x80dp)

## 📄 Licencia

© 2026 El Sonorense TV - Todos los derechos reservados

## 👥 Contribuciones

Desarrollado por el equipo de **El Sonorense** con GitHub Copilot

---

### 🔗 Enlaces

- **GitHub**: [luicalariosmx22/El_Sonorense_TV](https://github.com/luicalariosmx22/El_Sonorense_TV)
- **El Sonorense**: [Sitio Web Oficial](https://elsonorense.com)

---

**⚡ Optimizado para Android TV • 🎨 Branding Personalizado • 📺 Streaming HLS**