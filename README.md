# Blog-Spring-Informatorio2020
Proyecto de aprobación para tercera etapa (backend) del Informatorio, api de un blog sencilla con usuarios, posts y comentarios.

El mismo permite crar usuarios, post y comentarios, borrarlos, actualizar información de los mismmos y buscar.

Para este proyecto se utilizo el framework de Spring 5, mediante la herramienta SpringBoot 2, con dependencias como web-MVC, MySql connector, spring data jpa y spring validation.

No se tuvo encuenta la seguridad en el acceso a rutas, encriptación de las claves debido a que no eran modulos pertinentes al curso. Como medida de seguridad minima al obtener los datos de los usuarios la clave no es enviada en la respuesta de la petición.
