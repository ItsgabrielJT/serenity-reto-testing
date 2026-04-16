# Conclusiones del Ejercicio

## Resultado General

La ejecución más reciente del flujo E2E de compra en **SauceDemo** fue exitosa, demostrando que el recorrido principal de compra estándar puede completarse de extremo a extremo sin fallos tras la aplicación de refactorizaciones arquitectónicas e interacciones resilientes.

- **Feature ejecutada:** `web/ui_purchase.feature`
- **Runner ejecutado:** `com.company.automation.runners.WebUIRunner`
- **Escenarios ejecutados:** 1
- **Escenarios exitosos:** 1
- **Escenarios fallidos:** 0
- **Navegador:** Chrome (macOS)

## Hallazgos Principales y Refactorizaciones Aplicadas

### 1. Robustez frente a la renderización del Viewport (Scroll)
Durante las pruebas se identificó que el sistema fallaba intermitentemente en interacciones críticas como `ProceedToCheckout` y `FinishPurchase`. La causa raíz residía en que a medida que se agregaban productos, la UI hacía crecer la página verticalmente, desplazando los botones fuera del viewport. 

**Solución aplicada:**
- Se modificó la aserción estricta de `isClickable()` por `isVisible()`.
- Se implementó la interacción `JavaScriptClick.on()` que inyecta código JS en el navegador para disparar el evento directamente en el DOM, evadiendo la restricción del scroll nativo del WebDriver que lanzaba `ElementNotInteractableException`.

### 2. Adopción de Clean Code y Principios SOLID
El diseño anterior recibía los datos del formulario de compra utilizando colecciones genéricas (`Map<String, String>`). Esto generaba un fuerte acoplamiento y violaba el **Single Responsibility Principle (SRP)** al mezclar la lectura de datos con las acciones de la interfaz, lo que hacía propenso al código a errores por "magic strings".

**Solución aplicada:**
- Se implementó el Modelo de Dominio `Customer` bajo la carpeta `models`, encargado exclusivamente de almacenar y estructurar los datos del usuario.
- En la tarea `FillCheckoutInformation`, se inyectó el objeto `Customer`, dejando la clase de la tarea dedicada puramente a orquestar las interacciones Screenplay sobre la UI y delegando la organización de los datos al modelo.
- El Step Definition ahora actúa como puente, mapeando el `DataTable` de Cucumber hacia la instancia del modelo, respetando la **Inversión de Dependencias**.

### 3. El flujo crítico de compra se completó de punta a punta
El escenario `Customer completes a full purchase with two products` cubrió todos los puntos de integración de forma exitosa tras las refactorizaciones:
- Autenticación correcta con credenciales dinámicas.
- Adición exitosa de `"Sauce Labs Backpack"` y `"Sauce Labs Bike Light"`.
- Navegación al carrito e ingreso al proceso de Checkout superando las limitantes visuales.
- Llenado del formulario de información inyectando el objeto `Customer`.
- Culminación del proceso de pago hasta visualizar el mensaje "Thank you for your order!".

## Recomendaciones
1. **Modelado de Dominio Constante:** Mantener la política de encapsular datos paramétricos en Objetos (POJOs) para mantener centralizadas las variables. Evitar `Maps` siempre que los campos pertenezcan a una misma entidad de negocio (ej. Producto, Usuario, Tarjeta).
2. **Resiliencia de UI:** Seguir utilizando la directriz de `JavaScriptClick` y sincronizaciones por `WaitUntil.isVisible()` en flujos largos que tiendan a generar barras de scroll, especialmente en resoluciones de pantalla dinámicas (Headless CI vs UI local).
3. **Escalabilidad:** Agregar escenarios adicionales de Cucumber con nuevos sets de datos para garantizar que el modelo `Customer` y el carrito calculen correctamente los límites de la página con 3, 4 o más productos combinados.

