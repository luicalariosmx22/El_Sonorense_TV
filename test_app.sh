#!/bin/bash

echo "🎯 SCRIPT DE PRUEBAS - EL SONORENSE TV APP"
echo "=========================================="

# Verificar dispositivos conectados
echo "📱 Dispositivos Android conectados:"
adb devices

echo ""
echo "🔧 Opciones disponibles:"
echo "1. Instalar en dispositivo conectado"
echo "2. Probar URLs del stream directamente"
echo "3. Lanzar nuevo emulador Android TV"
echo "4. Construir nueva versión del APK"

read -p "Selecciona una opción (1-4): " option

case $option in
    1)
        echo "📦 Instalando El Sonorense TV..."
        adb install -r app/build/outputs/apk/debug/app-debug.apk
        if [ $? -eq 0 ]; then
            echo "✅ App instalada correctamente"
            echo "🚀 Iniciando app..."
            adb shell am start -n mx.elsonorense.tv/.MainActivity
        else
            echo "❌ Error instalando la app"
        fi
        ;;
    2)
        echo "🌐 Probando URLs del stream..."
        echo "📡 URL Principal: https://s5.mexside.net:2020/public/elsonorense"
        curl -I "https://s5.mexside.net:2020/public/elsonorense" 2>/dev/null | head -3 || echo "❌ No se puede conectar"
        echo ""
        echo "📡 URL Alternativa: https://s5.mexside.net:2020/controller/MediaService/12/index/12"
        curl -I "https://s5.mexside.net:2020/controller/MediaService/12/index/12" 2>/dev/null | head -3 || echo "❌ No se puede conectar"
        ;;
    3)
        echo "📺 Lanzando emulador Android TV..."
        export ANDROID_SDK_ROOT=/home/luica-larios/Android/Sdk
        export PATH=$ANDROID_SDK_ROOT/cmdline-tools/latest/bin:$ANDROID_SDK_ROOT/platform-tools:$ANDROID_SDK_ROOT/emulator:$PATH
        emulator -avd El_Sonorense_TV -no-audio -no-boot-anim &
        echo "⏳ Emulador iniciando... (puede tardar 2-3 minutos)"
        ;;
    4)
        echo "🔨 Construyendo nueva versión..."
        ./gradlew clean assembleDebug
        echo "✅ APK actualizado en: app/build/outputs/apk/debug/app-debug.apk"
        ;;
    *)
        echo "❌ Opción no válida"
        ;;
esac

echo ""
echo "📺 Para usar la app manualmente:"
echo "   - Navega con las flechas del teclado/D-pad"
echo "   - Enter para seleccionar"
echo "   - Selecciona '🔴 EN VIVO - El Sonorense' para el stream"