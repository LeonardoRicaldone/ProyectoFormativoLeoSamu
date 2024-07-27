package RecycleViewHelpers

import LeonardoMonterrosa.SamuelSanchez.myapplication.R
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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
            // Eliminar paciente de la lista y notificar al adaptador
            (Datos as MutableList).removeAt(position)
            notifyItemRemoved(position)
        }
    }



}