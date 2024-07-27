package RecycleViewHelpers

import LeonardoMonterrosa.SamuelSanchez.myapplication.R
import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import modelo.ClaseConexion
import modelo.tbPacientes

class AdaptadorPaciente(
    var Datos: List<tbPacientes>,
    private val onItemClicked: (tbPacientes) -> Unit
) : RecyclerView.Adapter<ViewHolderPaciente>() {
    // Método para actualizar los datos
    fun updateData(nuevosDatos: List<tbPacientes>) {
        Datos = nuevosDatos
        notifyDataSetChanged() // Notifica al RecyclerView que los datos han cambiado
    }

    fun eliminarRegistro(nombre: String, posicion: Int){
        //Notificar al adaptador
        val listaDatos = Datos.toMutableList()
        listaDatos.removeAt(posicion)

        //Quitar de la base de datos
        GlobalScope.launch(Dispatchers.IO){
            //Dos pasos para eliminar de la base de datos

            //1- Crear un objeto de la clase conexion
            val objConexion = ClaseConexion().cadenaConexion()

            //2- Creo una variable que contenga un PrepareStatement
            val deletePacientes = objConexion?.prepareStatement("delete tbPacientes where nombre = ?")!!
            deletePacientes.setString(1, nombre)
            deletePacientes.executeUpdate()

            val commit = objConexion.prepareStatement("commit")
            commit.executeUpdate()
        }

        //Notificamos el cambio para que refresque la lista
        Datos = listaDatos.toList()
        //Quito los datos de la lista
        notifyItemRemoved(posicion)
        notifyDataSetChanged()
    }

    /*fun actualizarListadoDespuesDeEditar(id: Int, nuevoNombre: String, nuevoApellido: String, nuevaEdad: Int, nuevaEnfermedad: String, nuevaNumHabitacion: Int, nuevaNumCama: Int, nuevosMedicamentos: String, nuevaFechaNacimiento: String, nuevaHoraMedicamento: String){
        //Obtener el UUID
        val identificador = Datos.indexOfFirst { it.id == id }
        //Asigno el nuevo nombre
        Datos[identificador].nombre = nuevoNombre
        Datos[identificador].apellido = nuevoApellido
        Datos[identificador].edad = nuevaEdad
        Datos[identificador].enfermedad = nuevaEnfermedad
        Datos[identificador].numero_habitacion = nuevaNumHabitacion
        Datos[identificador].numero_cama = nuevaNumCama
        Datos[identificador].medicamentos_asignados = nuevosMedicamentos
        Datos[identificador].fecha_nacimiento = nuevaFechaNacimiento
        Datos[identificador].hora_aplicacion_medicamento = nuevaHoraMedicamento

        //Notifico que los cambios han sido realizados
        notifyItemChanged(identificador)
    }*/


    //Creamos la funcion de editar o actualizar en la base de datos
   /* fun editarProducto(nombrePaciente: String, apellidoPaciente: String, edadPaciente: Int, enfermedadPaciente: String, numHabitacionPaciente: Int, numCamaPaciente: Int, medicamentosPaciente: String, fechaNacimientoPaciente: String, horaMedicamentoPaciente: String, id: Int){
        //-Creo una corrutina
        GlobalScope.launch(Dispatchers.IO){
            //1- Creo un objeto de la clase conexion
            val objConexion = ClaseConexion().cadenaConexion()

            //2- Creo una variable que contenga un PrepareStatement
            val updatePacientes = objConexion?.prepareStatement("update tbPacientes set nombre = ?, apellido = ?, edad = ?, enfermedad = ?, numero_habitacion = ?, numero_cama = ?, medicamentos_asignados = ?, fecha_nacimiento = ?, hora_aplicacion_medicamento = ? where id = ?")!!
            updatePacientes.setString(1, nombrePaciente)
            updatePacientes.setString(2, apellidoPaciente)
            updatePacientes.setInt(3, edadPaciente)
            updatePacientes.setString(4, enfermedadPaciente)
            updatePacientes.setInt(5, numHabitacionPaciente)
            updatePacientes.setInt(6, numCamaPaciente)
            updatePacientes.setString(7, medicamentosPaciente)
            updatePacientes.setString(8, fechaNacimientoPaciente)
            updatePacientes.setString(9, horaMedicamentoPaciente)
            updatePacientes.setInt(10, id)
            updatePacientes.executeUpdate()

            val commit = objConexion.prepareStatement("commit")
            commit.executeUpdate()
        }
    }*/


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPaciente {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_paciente, parent, false)
        return ViewHolderPaciente(vista)
    }



    override fun getItemCount() = Datos.size

    override fun onBindViewHolder(holder: ViewHolderPaciente, position: Int) {
        val item = Datos[position]

        holder.txtNombrePacientexd.text = item.nombre
        holder.txtEdadPaciente.text = item.edad.toString()
        holder.txtEnfermedadPaciente.text = item.enfermedad

        holder.itemView.setOnClickListener {
            onItemClicked(item)
        }

        holder.imgEliminarPaciente.setOnClickListener {

            //Crear una alerta de confirmacion para que se borre
            val context = holder.txtNombrePacientexd.context

            val builder = AlertDialog.Builder(context)
            builder.setTitle("Eliminar")
            builder.setMessage("¿Estas seguro que deseas eliminar?")

            //botones de mi alerta
            builder.setPositiveButton("Si"){
                    dialog, wich ->
                eliminarRegistro(item.nombre, position)
            }

            builder.setNegativeButton("No"){
                    dialog, wich ->
                //Si doy en clic en "No" se cierra la alerta
                dialog.dismiss()
            }

            //Para mostrar la alerta
            val dialog = builder.create()
            dialog.show()
        }
    }



}