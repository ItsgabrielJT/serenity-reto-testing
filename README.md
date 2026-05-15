# Serenity BDD — Demoblaze Enterprise Automation Framework

Framework de automatizacion QA enfocado en el flujo web de compra de Demoblaze (https://www.demoblaze.com/), construido con **Java + Serenity BDD + Screenplay Pattern**.

---

## Estructura del proyecto

```text
src/test/java/com/demoblaze/automation/
├── models/             # POJOs de datos de negocio (ej. PurchaseOrder)
├── tasks/
│   └── web/            # Tasks de interfaz web (Screenplay)
├── questions/
│   └── web/            # Questions UI
├── ui/
│   └── pages/          # UI Targets por página
├── stepdefinitions/    # Hooks y WebPurchaseStepDefinitions
└── runners/            # JUnit Runner web (WebUIRunner)

src/test/resources/
├── features/
│   └── web/            # ui_purchase.feature
├── serenity.conf       # Configuración Serenity
└── cucumber.properties # Configuración Cucumber
```

---

## Requisitos

### 1. Prerequisitos

A continuación se describen las versiones de las dependencias y tecnologías necesarias para configurar y ejecutar el proyecto en su máquina local:

- **Máquina local** con sistema operativo macOS (o Windows 10/Linux).
- **Java JDK**: versión 17 o superior (recomendado 17.0.6 LTS o posterior).
- **IDE**: Visual Studio Code (con extensión de Java) o IntelliJ IDEA 2023.1+.
- **Git**: versión 2.30 o superior (para clonar el repositorio).
- **Gradle**: versión 8.5 o superior (incluido en el proyecto mediante `gradlew`).
- **Chrome o Chromium**: última versión (requerido para WebDriver automation).
- **RAM mínima**: 2GB disponible para ejecutar tests.

### 2. Comandos de instalación

Comandos básicos para descargar, configurar y preparar el proyecto en su máquina local:

- `git clone <url-del-repositorio>` (clona el proyecto desde el repositorio remoto).
- `cd serenity-opencart-testing` (navega al directorio del proyecto).
- `java -version` (verifica que Java 17 esté instalado y configurado correctamente).
- `./gradlew clean` (limpia compilaciones previas y descargas las dependencias de Gradle por primera vez).
- `./gradlew build` (descarga todas las dependencias definidas en `build.gradle`: Serenity, Cucumber, Selenium, etc.).
- `./gradlew test` (ejecuta la suite de tests una vez que todas las dependencias estén disponibles).

---

## Ejecución

### Instalación de dependencias (primera vez)
```bash
./gradlew clean build
```

Este comando descarga todas las dependencias (Serenity, Cucumber, Selenium, etc.) y compila el proyecto.

### Ejecutar todos los tests
```bash
./gradlew test
```

Este comando ejecuta toda la suite configurada por defecto. Genera automáticamente reportes en `target/site/serenity/`.

### Ejecutar con reporte agregado
```bash
./gradlew clean test aggregate
```

Limpia compilaciones previas, ejecuta los tests y genera el reporte final consolidado de Serenity en `target/site/serenity/index.html`.

### Solo UI (Web)
```bash
./gradlew test --tags "@ui"
```

Ejecuta únicamente los escenarios etiquetados con `@ui` (flujo de compra web).

### Por runner específico
```bash
./gradlew test --tests "com.demoblaze.automation.runners.WebUIRunner"
```

Ejecuta solo el runner especificado para pruebas web.

### Modo Headless (para CI/CD)
```bash
./gradlew test \
  -Dheadless=true \
  -Dchrome.switches="--no-sandbox,--disable-dev-shm-usage,--disable-gpu,--headless=new"
```

Ejecuta los tests sin interfaz gráfica, ideal para pipelines de CI/CD.

### Limpiar y resetear
```bash
./gradlew clean
```

Elimina compilaciones previas y artefactos generados (carpeta `target/`).

---

## Tags disponibles

| Tag          | Descripción                              |
|--------------|------------------------------------------|
| `@ui`        | Pruebas de interfaz web (E2E)            |

*(Nota: los tags de api, db, smoke, regression se añadirán conforme se expandan las pruebas de esos bloques).*

Actualmente el proyecto implementa el flujo E2E de compra en Demoblaze: agregar dos productos al carrito, visualizar el carrito, completar el formulario de compra y finalizar la compra.

---

## Reportes

El proyecto genera únicamente reportes Serenity. Los reportes HTML de Cucumber fueron eliminados para evitar salidas duplicadas.

Después de ejecutar `./gradlew test aggregate`, el reporte que debes abrir es:

`target/site/serenity/index.html`

### Para abrir el reporte en macOS:
```bash
open target/site/serenity/index.html
```

### Contenido del reporte:
- `target/site/serenity/` contiene el reporte HTML final consolidado de Serenity con:
  - Resumen de ejecución (escenarios exitosos/fallidos)
  - Detalles de cada escenario
  - Screenshots de cada paso
  - Logs de ejecución y errores (si aplica)

---


## Principios aplicados

- **Screenplay Pattern**: Actor → Task → Interaction → Question
- **Resiliencia de UI (Scroll)**: Se utiliza inyección `JavaScriptClick.on()` y sincronización basada en el Viewport físico (`WaitUntil.isVisible()`) sorteando limitantes dinámicas.
- **Single Responsibility**: cada clase hace una sola cosa
- **Open/Closed**: extensible sin modificar código existente
- **Dependency Inversion**: Tasks dependen de Models concretos (`Customer`), aislándolas de estructuras lógicas de test y diccionarios (`Map`).
- **DRY**: el proyecto conserva solo las clases necesarias para la suite web actual
- **High Cohesion**: packages agrupados por responsabilidad funcional
- **Low Coupling**: Abilities, Tasks y Questions son independientes entre sí
