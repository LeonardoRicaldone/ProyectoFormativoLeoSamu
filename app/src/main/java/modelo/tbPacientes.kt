package modelo

data class tbPacientes(
    val id: Int,
    val nombre: String,
    val apellido: String,
    val edad: Int,
    val enfermedad: String,
    val numero_habitacion: Int,
    val numero_cama: Int,
    val medicamentos_asignados: String,
    val fecha_nacimiento: String,
    val hora_aplicacion_medicamento: String
)
