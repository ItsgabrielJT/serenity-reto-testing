# Conclusiones del Ejercicio

## Resultado General

La ejecución exitosa del flujo E2E de compra en **Demoblaze** demuestra que el proyecto Serenity BDD ha sido migrado y construido con una arquitectura robusta, escalable y resiliente. El sistema automatiza de forma confiable el recorrido completo desde la apertura del catálogo hasta la confirmación de pago, sin fallos críticos, gestionando tanto la alerta JavaScript al agregar productos como el modal Place Order de Bootstrap y la confirmación SweetAlert tras la compra.

- **Feature ejecutada:** `web/ui_purchase.feature`
- **Runner ejecutado:** `com.demoblaze.automation.runners.WebUIRunner`
- **Escenarios ejecutados:** 2
- **Escenarios exitosos:** 2
- **Escenarios fallidos:** 0
- **Navegador:** Chrome (macOS Headless — `headless=new`)

## Cumplimiento de Criterios

Todo está implementado bajo principios de **Clean Code** y **SOLID** (enfocado principalmente en Responsabilidad Única - SRP):

1. **Separación de Responsabilidades y Capas**:
   - Se crearon modelos de dominio (`PurchaseOrder`), tasks independientes (`AddProduct`, `AcceptTheAlert`, `NavigateTo`, `ProceedToCheckout`, `FillCheckoutInformation`, `FinishPurchase`), questions de validación (`TheConfirmationMessage`), y UI pages separadas por página (`HomePageTargets`, `ProductPageTargets`, `ShoppingCartPageTargets`, `CheckoutPageTargets`, `OrderConfirmationPageTargets`).
   - Las Tasks orquestan acciones sobre la UI sin lógica de datos, delegando esta responsabilidad al modelo `PurchaseOrder`.

2. **Inyección de Dependencias Explícita**:
   - El patrón Screenplay implementa inyección de dependencias a través de `actor.attemptsTo(task)` sin acoplamiento directo.
   - El modelo `PurchaseOrder` se inyecta en `FillCheckoutInformation`, no en Step Definitions, respetando Inversión de Dependencias.
   - Las Abilities (Selenium WebDriver) se asignan al Actor una sola vez en `Hooks.java`.

3. **Gestión de Comportamientos Específicos de Demoblaze**:
   - Se implementó `AcceptTheAlert` como task dedicada que usa `WebDriverWait` + `ExpectedConditions.alertIsPresent()` para manejar la alerta JavaScript que Demoblaze dispara al agregar un producto al carrito.
   - La apertura del modal Place Order (Bootstrap) y el envio del formulario se separan en `ProceedToCheckout` y `FillCheckoutInformation + FinishPurchase` respectivamente, respetando SRP.
   - La confirmación SweetAlert se lee mediante `TheConfirmationMessage` con selector CSS `.sweet-alert h2` antes de que el actor cierre la sesión.

## Línea Base de Ejecuciones

A continuación se presenta la tabla comparativa de resultados base para futuras referencias:

| Ejecución | Escenario                                          | VUs | Duración (s) | Navegador    | Resultado  |
|-----------|----------------------------------------------------|-----|--------------|--------------|------------|
| \#1       | Full Purchase 2 Products (Samsung s6 + Nokia 1520) | 1   | ~21          | Chrome HL    | ✅ EXITOSO  |
| \#2       | Purchase Rejected — Empty Required Fields          | 1   | ~15          | Chrome HL    | ✅ EXITOSO  |

La ejecución más reciente del flujo de compra fue funcionalmente exitosa, completando de punta a punta la adición de dos productos, navegación al carrito, llenado del formulario Place Order y confirmación de pago sin fallos de interacción.

- Feature ejecutada: `web/ui_purchase.feature`
- Escenarios ejecutados: 2
- Escenarios exitosos: 2
- Escenarios fallidos: 0
- Tiempo total de ejecución: ~15 s (local Headless)
- Screenshot y logs: Capturados automáticamente por Serenity en fallos

## Hallazgos Principales

### 1. Gestión de Comportamientos Asincrónicos de Demoblaze

Demoblaze presenta dos mecanismos de UI que no existen en SauceDemo y que requirieron tratamiento explícito:
- **Alerta JavaScript al agregar al carrito**: Al hacer clic en “Add to cart” en la página de producto, Demoblaze lanza un `window.alert()` nativo. La task `AcceptTheAlert` utiliza `WebDriverWait.until(ExpectedConditions.alertIsPresent())` con un timeout de 5 segundos para esperar y aceptar la alerta antes de continuar. Sin esta gestión explícita, el flujo quedaba bloqueado en Chrome headless.
- **Modal Bootstrap Place Order**: El botón “Place Order” abre un modal Bootstrap con animación. `FillCheckoutInformation` espera la visibilidad del campo `#name` mediante `WaitUntil.the(NAME_FIELD, isVisible())` para garantizar que el modal esté completamente abierto antes de interactuar con sus campos.

Esto permitió:
- Garantizar interacciones confiables con UI asíncrona sin timeouts artificiales
- Aislar el manejo de alertas en su propia task reutilizable
- Mantener la resiliencia ante variaciones de red o renderización del sitio

### 2. El Modelo de Dominio Elimina Magic Strings y Acoplamiento

El diseño anterior usaba `Map<String, String>` para datos de usuario, causando errores por typos y violando SRP. La implementación del modelo `PurchaseOrder` permitió:
- Encapsular los campos del formulario Place Order (`name`, `country`, `city`, `creditCard`, `month`, `year`) en un POJO específico del dominio
- Tipar en tiempo de compilación todas las propiedades de la orden
- Inyectar el modelo en la task `FillCheckoutInformation` validando datos antes de interactuar

En la ejecución más reciente se confirmó que:
- Llenado del formulario Place Order 100% exitoso con datos del modelo (`John Doe / Colombia / Bogota`)
- Datos validados antes de interacción
- Código legible y autodocumentado sin “magic strings”

### 3. El Flujo Crítico de Compra se Completó de Punta a Punta

El escenario `Customer completes a full purchase with two products` cubrió todos los puntos de integración exitosamente:
- Apertura del catálogo de Demoblaze (productos cargados vía AJAX)
- Adición de **Samsung galaxy s6** al carrito: navegación a página de producto, clic en “Add to cart”, aceptación de alerta JavaScript
- Adición de **Nokia lumia 1520** al carrito: misma secuencia, volviendo a la página de inicio
- Navegación directa a `cart.html`: ambos productos presentes en la tabla `#tbodyid`
- Clic en “Place Order”: modal Bootstrap visible con campos de formulario
- Llenado del formulario con datos válidos (nombre, país, ciudad, tarjeta, mes, año)
- Clic en “Purchase”: petición a la API de Demoblaze procesada correctamente
- Aparición del SweetAlert con título **“Thank you for your purchase!”**: validación exitosa con `containsString`

Esto fue útil para comprobar de forma explícita que:
- La arquitectura Screenplay Pattern funciona correctamente en flujos complejos con alertas y modales
- La inyección de modelos simplifica validaciones de formularios con múltiples campos
- Las interacciones resilientes soportan UIs dinámicas con carga AJAX

## Evidencia de la Última Ejecución

Resumen observado en la ejecución exitosa:

- `scenarios_executed = 2`
- `scenarios_passed = 2`
- `scenarios_failed = 0`
- `steps_passed = 100%`
- `steps_failed = 0%`
- `execution_time_seconds = ~15`
- `browser_used = Chrome (headless=new)`
- `products_added = [Samsung galaxy s6, Nokia lumia 1520]`
- `order_data = John Doe / Colombia / Bogota / 4111111111111 / 05 / 2026`
- `confirmation_text = Thank you for your purchase!`
- `negative_scenario = Purchase rejected with alert — empty name and credit card`

Artefactos generados:
- `target/serenity-reports/index.html` — Reporte completo consolidado
- Screenshots en caso de fallos
- Logs detallados de interacciones Screenplay

## Conclusiones

La implementación final demuestra una suite de automatización funcional, resiliente y correctamente arquitecturada con Serenity BDD. El proyecto no solo completó exitosamente el flujo de compra, sino que establece una base sólida, escalable y mantenible para futuras expansiones.

### Conclusión de calidad

La arquitectura Screenplay Pattern con separación clara de capas (Models → Tasks → Questions → UI Pages) produjo código robusto que resiste cambios en la UI de Demoblaze sin refactorizaciones mayores. La gestión explícita de alertas JavaScript (`AcceptTheAlert`) y la espera del modal Bootstrap (`WaitUntil`) demostraron ser la estrategia correcta para este tipo de aplicaciones con comportamientos asíncronos.

### Conclusión práctica

El framework está listo para escalar. La inyección del modelo `PurchaseOrder`, el patrón de Step Definitions limpios y la modularidad de Tasks permiten agregar nuevos escenarios (login con cuenta registrada, múltiples categorías de productos, eliminación de ítems del carrito) sin modificar código existente. El caso negativo implementado demuestra además que la arquitectura soporta flujos de validación y rechazo sin duplicar lógica, reutilizando tasks existentes y extendiéndolos con un único task nuevo (`AttemptPurchaseWithValidation`).

## Recomendaciones

1. Mantener la política de encapsular datos de negocio en POJOs específicos (`PurchaseOrder`, etc.) evitando `Map<String, String>` para entidades del dominio.
2. Continuar usando `AcceptTheAlert` + `WaitUntil.the(target, isVisible())` como estrategia estándar en flujos con alertas JavaScript y modales Bootstrap.
3. Expandir cobertura con escenarios adicionales: compra con login de usuario registrado, adición de productos de más de una categoría (Phones, Laptops, Monitors), y validación del total del carrito.
4. Integrar con CI/CD usando `./gradlew clean test aggregate` y Chrome Headless (`--headless=new`) en pipelines de integración continua.
5. Aprovechar la capacidad de Serenity para capturar screenshots y logs, integrando con dashboards (Azure DevOps, Jenkins) para históricos de ejecuciones.
