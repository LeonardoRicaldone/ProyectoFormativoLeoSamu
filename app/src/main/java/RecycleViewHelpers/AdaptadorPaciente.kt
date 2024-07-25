package RecycleViewHelpers

import LeonardoMonterrosa.SamuelSanchez.myapplication.R
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import modelo.tbPacientes

class AdaptadorPaciente(var Datos: List<tbPacientes>): RecyclerView.Adapter<ViewHolderPaciente>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPaciente {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_paciente, parent, false)
        return ViewHolderPaciente(vista)
    }

    override fun getItemCount() = Datos.size


    override fun onBindViewHolder(holder: ViewHolderPaciente, position: Int) {
        val item = Datos[position]

        holder.txtNombrePaciente.text = item.nombre
        holder.txtEdadPaciente.text = item.edad.toString()
        holder.txtEnfermedadPaciente.text = item.enfermedad
    }
}