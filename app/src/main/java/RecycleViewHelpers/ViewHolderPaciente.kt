package RecycleViewHelpers

import LeonardoMonterrosa.SamuelSanchez.myapplication.R
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewHolderPaciente(view: View) : RecyclerView.ViewHolder(view) {
    val txtNombrePacientexd = view.findViewById<TextView>(R.id.txtNombrePaciente)
    val txtEdadPaciente = view.findViewById<TextView>(R.id.txtEdadPaciente)
    val txtEnfermedadPaciente = view.findViewById<TextView>(R.id.txtEnfermedadPaciente)
    val imgEliminarPaciente = view.findViewById<ImageView>(R.id.imgEliminarPaciente)
}