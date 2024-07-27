package LeonardoMonterrosa.SamuelSanchez.myapplication.ui.dashboard

import LeonardoMonterrosa.SamuelSanchez.myapplication.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import LeonardoMonterrosa.SamuelSanchez.myapplication.databinding.FragmentDashboardBinding
import RecycleViewHelpers.AdaptadorPaciente
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import modelo.ClaseConexion
import modelo.tbPacientes

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val txtNombres = root.findViewById<EditText>(R.id.txtNombres)
        val txtApellidos = root.findViewById<EditText>(R.id.txtApellidos)
        val txtEdad = root.findViewById<EditText>(R.id.txtEdad)
        val txtEnfermedad = root.findViewById<EditText>(R.id.txtEnfermedad)
        val txtNumHabitacion = root.findViewById<EditText>(R.id.txtNumHabitacion)
        val txtNumCama = root.findViewById<EditText>(R.id.txtNumCama)
        val txtMedicamentoAsigando = root.findViewById<EditText>(R.id.txtMedicamentos)
        val txtFechaNacimiento = root.findViewById<EditText>(R.id.txtFechaNacimiento)
        val txtHoraDeMedicamento = root.findViewById<EditText>(R.id.txtHoraDeMedicamentos)
        val btnGuardarPacientes = root.findViewById<Button>(R.id.btnAgregarPacientes)







        btnGuardarPacientes.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val claseC = ClaseConexion().cadenaConexion()
                val addPacientes = claseC?.prepareStatement("insert into tbPacientes(nombre, apellido, edad, enfermedad, numero_habitacion, numero_cama, medicamentos_asignados, fecha_nacimiento, hora_aplicacion_medicamento) values(?,?,?,?,?,?,?,?,?)")!!
                addPacientes.setString(1, txtNombres.text.toString())
                addPacientes.setString(2, txtApellidos.text.toString())
                addPacientes.setInt(3, txtEdad.text.toString().toInt())
                addPacientes.setString(4, txtEnfermedad.text.toString())
                addPacientes.setInt(5, txtNumHabitacion.text.toString().toInt())
                addPacientes.setInt(6, txtNumCama.text.toString().toInt())
                addPacientes.setString(7, txtMedicamentoAsigando.text.toString())
                addPacientes.setString(8, txtFechaNacimiento.text.toString())
                addPacientes.setString(9, txtHoraDeMedicamento.text.toString())
                addPacientes.executeUpdate()



            }
        }

        val textView: TextView = binding.textView
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
