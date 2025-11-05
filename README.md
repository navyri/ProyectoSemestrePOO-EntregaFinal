<p align='center'> 
  <img src="https://capsule-render.vercel.app/api?type=waving&height=200&color=80354A&text=Glow%20Up&fontColor=FFFFFF&desc=Proyecto%20de%20Programacion%20Orientada%20a%20Objetos%20(2025-2)&fontAlignY=30&descAlignY=54"/> 
</p>
<p align='center'>
  <img src="https://imgur.com/8wV4oxM.png" width="" height="" alt="Vista conceptual sistema Glow Up" />
</p>
<p align='center'>
    <code>Ejemplo de visualizacion de interfaz</code><br>
    <code>Inicio y Menu para Dueña, AdminUsuario, AdminContenido & Cliente</code>
</p>
<p align='center'>
    <img 
        src="https://capsule-render.vercel.app/api?type=rect&height=5&color=80354A&reversal=false&fontAlignY=40&fontColor=FFFFFF&fontSize=60"
    />
</p>

### Descripcion

Glow Up Manager es un sistema integral desarrollado con Java, Spring y JPA/Hibernate para la gestión de fábricas, grupos de trabajadores (esclavos), productos y usuarios con distintos roles. La consola permite a la dueña, administradores y usuarios gestionar y visualizar informacion de forma clara, humana y validada, protegiendo los datos y evitando errores comunes de persistencia. Incluye menúes personalizados, registro grupal, flujos editables y solución de problemas recurrentes de Java/Spring en aplicaciones basadas en relaciones y colecciones

***

### Objetivos y caso de uso

- Registrar y autenticar usuarios con roles diferenciados: dueña (Cabrita Sakura), administradores de usuarios y de contenido y clientes.
- Permitir a los administradores de usuarios suspender y activar cuenta, administradores de contenido crear y publicar productos y a los clientes realizar compras.
- Gestionar el historial confidencial de trabajadores esclavizados con acceso exclusivo para la dueña
- Persistir los datos en archivos o, preferiblemente, en una base de datos relacional.
- Aplicar buenas prácticas de POO: encapsulamiento, herencia, composicion y modularidad (paquetes por logica y ambito)

---

### Principales roles y entidades

- **Dueña (Cabrita Sakura):** Acceso absoluto, gestion de toda la informacion y control total del sistema
- **AdministradorUsuario / AdministradorContenido:** Permisos diferenciados para gestión de usuarios y publicación/edición de productos
- **Cliente:** Compra productos, gestiona su información y realiza transacciones
- **TrabajadorEsclavizado:** Vinculación secreta a fábricas, registro confidencial con relación al Dueña
- **Fabrica/Producto/Compra:** Subsistemas comerciales integrados para modelar ventas, inventarios y desarrollo

Detalles, atributos y relaciones están modelados con POO, separando claramente responsabilidades y accesos.

---

### Caracteristicas principales

- **Interfaz grafica avanzada:**  
  Desde v2.0.0, todas las operaciones principales se gestionan por menus visuales y mensajes de dialogo realizados en Swing/JOptionPane
- **Gestion y validacion por roles:**  
  Flujo robusto segun permisos (dueña, administradores, clientes)
- **Presentación clara y descriptiva:**  
  Informacion sobre entidades y relaciones, nunca expone identificadores internos, mostrando unicamente datos relevantes y humanos
- **Persistencia y seguridad:**  
  JPA/Hibernate para persistencia a base de datos relacional
- **Modularidad y extensibilidad:**  
  Estructura organizada para facilitar futuras integraciones del modelo y casos de negocio

---

### Ejemplo de menú de consola

```
BIENVENIDO AL MENU DE GLOW UP
1. Gestionar esclavos
2. Gestionar fabricas
3. Gestionar productos
4. Gestionar usuarios
0. Salir del programa
Ingrese un numero en base a lo que desee realizar:
```
Los datos siempre se presentan en formato descriptivo, agrupando por nombre, origen, salud o capacidad, nunca por identificadores internos

---

### Cómo ejecutar el proyecto

1. Clona el repositorio y asegúrate de tener Java 17+ y Maven instalados
2. Configura tu base de datos en `application.properties`

<p align='center'>
    <img 
        src="https://capsule-render.vercel.app/api?type=rect&height=5&color=B97375&reversal=false&fontAlignY=40&fontColor=FFFFFF&fontSize=60"
    />
</p>
