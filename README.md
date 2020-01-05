# Belatrix-Refactor-lchl
Refactorizacion del codigo


# RECOMENDACIONES PARA LA REFACTORIZACION

- No tiene interfaces.
- No exite separaciones de capas.
- Se definen variables que no se usan. (initialized)
- Los errores son muy genericos.
- Los nombres de algunos metodos comienzan con mayusculas.
- Se define un constructor de clase, algunos parametros se repiten por parametro en los metodos.
- Las variables son poco descriptivas.
- Valida si messageText es nulo pero no lanza ninguna excepcion.
- Para messageText le hace un trim() y luego valida si es nulo. Si la variable ya es nula al hacerle un trim() lanza una excepcion.
- No se tiene claro lo que se guarda en la base de datos , maneja una jerarquia distinta de mensajes.(Podria guardarse los numeros o manejar la misma jerarquia info, warn, error)
- Se guarda message , message es un boolean , deberia ir el messageText.
- Se deberia implementar en otro metodo los 3 if .
- No se maneja correctamente la conexion a base de datos , no se asegura de cerrar la conexion ni se captura errores.
- Al crear un File no se valida ni captura errores.
- Se crea un Strin l, se concatena pero nunca se usa.( Se deberia quitar este fragmento de codigo)
- Para el caso que el mensaje se escriba en consola o un File siempre va pintar un mensaje informativo, asi se haya seleccionado otro tipo.
- Se debe separar de la logica las conexiones en general.
- Demaciados parametros para un metodo.
- Se podria poner en una variable tipo enum los diferentes tipo de mensajes (INFO, WARN, ERROR), asi como tambien en donde se desea pintar el mensaje (CONSOLE,FILE,BD).
- Y estas varibales pasarlas por parametros al metodo.
