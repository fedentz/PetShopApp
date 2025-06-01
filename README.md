# PetShopApp 🐾

Una aplicación móvil de ejemplo para Android que simula una tienda de mascotas completa, desarrollada con **Jetpack Compose** y **Kotlin**.

## 📱 Características

- **Autenticación**: Login, registro y recuperación de contraseña
- **Catálogo**: Exploración de productos con filtros
- **Carrito**: Gestión de productos y proceso de compra
- **Perfil**: Gestión de usuario y vendedor
- **Notificaciones**: Push notifications y notificaciones locales

## 🛠️ Tecnologías

- **Lenguaje**: Kotlin
- **UI**: Jetpack Compose + Material 3
- **Arquitectura**: MVVM
- **Inyección de Dependencias**: Hilt (Dagger-Hilt)
- **Red**: Retrofit2 + OkHttp
- **Base de Datos**: Room (opcional)
- **Navegación**: Navigation Compose
- **Imágenes**: Coil
- **Corrutinas**: Kotlin Coroutines + StateFlow

## 🏗️ Arquitectura

```
app/
├── core/                 # Utilidades compartidas
├── data/
│   ├── dto/             # Data Transfer Objects
│   ├── mapper/          # Mapeo DTO ↔ Models
│   ├── remote/          # API services y data sources
│   └── repository/      # Repositorios
├── domain/              # Casos de uso y modelos
├── presentation/        # UI (Screens, ViewModels, States)
│   ├── login/
│   ├── account/
│   ├── cart/
│   ├── profile/
│   └── components/
└── ui/                  # Tema y estilos
```

## 🚀 Instalación

1. **Clona el repositorio**
```bash
git clone https://github.com/tuUsuario/PetShopApp.git
cd PetShopApp
```

2. **Abre en Android Studio**
   - File → Open → Selecciona la carpeta del proyecto

3. **Configura el emulador**
   - AVD con API nivel ≥ 26 (Android 8.0+)

4. **Ejecuta la aplicación**
   - Presiona el botón ▶️ o `Shift + F10`

## 📋 Funcionalidades Principales

### 🔐 Autenticación
- **Login**: Email y contraseña
- **Registro**: Nombre, email, contraseña con validaciones
- **Recuperación**: Envío de email para resetear contraseña

### 🛍️ Tienda
- **Catálogo**: Lista de productos con imágenes y precios
- **Carrito**: Agregar, quitar y modificar cantidades
- **Checkout**: Proceso de compra simulado
- **Órdenes**: Confirmación y seguimiento

### 👤 Perfil
- **Usuario**: Información personal y configuración
- **Vendedor**: Gestión de productos y estadísticas

## 🔧 Dependencias Principales

```kotlin
// Retrofit & Networking
implementation("com.squareup.retrofit2:retrofit:2.9.0")
implementation("com.squareup.retrofit2:converter-gson:2.9.0")

// Hilt
implementation("com.google.dagger:hilt-android:2.44")

// Compose
implementation(platform("androidx.compose:compose-bom:2023.05.01"))
implementation("androidx.compose.ui:ui")
implementation("androidx.compose.material3:material3")

// Navigation
implementation("androidx.navigation:navigation-compose:2.5.3")

// Coil (Images)
implementation("io.coil-kt:coil-compose:2.3.0")
```

## 🌐 API

La aplicación utiliza [DummyJSON](https://dummyjson.com/) como backend simulado para:
- Autenticación de usuarios
- Gestión de productos
- Procesamiento de órdenes

## 📱 Capturas de Pantalla

*Próximamente: Agregar screenshots de las principales pantallas*

## 🤝 Contribución

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## 📄 Licencia


## 👨‍💻 Autores

[Federico Nintzel](https://github.com/fedentz)
[Juan Manuel Lurbe](https://github.com/juanlurbe)
[Paula Alvarez](https://github.com/paula-m-alvarez)
[Martin Cicinelli](https://github.com/martinchichi)

---

