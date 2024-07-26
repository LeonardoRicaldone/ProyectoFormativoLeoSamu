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

        val txtNombres = root.findViewById<EditText>(R.id.txtNombrePaciente)
        val txtApellidos = root.findViewById<EditText>(R.id.txtApellidos)
        val txtEdad = root.findViewById<EditText>(R.id.txtEdadPaciente)
        val txtEnfermedad = root.findViewById<EditText>(R.id.txtEnfermedadPaciente)
        val txtNumHabitacion = root.findViewById<EditText>(R.id.txtNumHabitacion)
        val txtNumCama = root.findViewById<EditText>(R.id.txtNumCama)
        val txtMedicamentoAsigando = root.findViewById<EditText>(R.id.txtMedicamentos)
        val txtFechaNacimiento = root.findViewById<EditText>(R.id.txtFechaNacimiento)
        val txtHoraDeMedicamento = root.findViewById<EditText>(R.id.txtHoraDeMedicamentos)
        val btnGuardarPacientes = root.findViewById<Button>(R.id.btnAgregarPacientes)

        val rcvPacientes = root.findViewById<RecyclerView>(R.id.rcvPacientes)
        rcvPacientes.layoutManager = LinearLayoutManager(requireContext())

        fun obtenerPacientes(): List<tbPacientes> {
            val objConexio = ClaseConexion().cadenaConexion()
            val statement = objConexio?.createStatement()
            val resultSet = statement?.executeQuery("select * from tbPacientes")!!

            val listaPacientes = mutableListOf<tbPacientes>()

            while (resultSet.next()) {

                val id = resultSet.getInt("id")
                val nombre = resultSet.getString("nombre")
                val apellido = resultSet.getString("apellido")
                val edad = resultSet.getInt("edad")
                val enfermedad = resultSet.getString("enfermedad")
                val numero_habitacion = resultSet.getInt("numero_habitacion")
                val numero_cama = resultSet.getInt("numero_cama")
                val medicamentos_asignados = resultSet.getString("medicamentos_asignados")
                val fecha_nacimiento = resultSet.getString("fecha_nacimiento")
                val hora_aplicacion_medicamento = resultSet.getString("hora_aplicacion_medicamento")

                val valoresJuntos = tbPacientes(id, nombre, apellido, edad, enfermedad, numero_habitacion, numero_cama, medicamentos_asignados, fecha_nacimiento, hora_aplicacion_medicamento)

                listaPacientes.add(valoresJuntos)

            }
            return listaPacientes
        }

        CoroutineScope(Dispatchers.IO).launch {
            val Pacientes = obtenerPacientes()
            withContext(Dispatchers.Main){
                val adapter = AdaptadorPaciente(Pacientes){ tbPacientes ->  }
                rcvPacientes.adapter = adapter

            }
        }

        btnGuardarPacientes.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val claseC = ClaseConexion().cadenaConexion()
                val addPacientes = claseC?.prepareStatement(
                    "insert into tbPacientes(nombre, apellido, edad, enfermedad, numero_habitacion, numero_cama, medicamento_asignados, fecha_nacimiento, hora_aplicacion_medicamentos) values(?,?,?,?,?,?,?,?,?)"
                )!!
                addPacientes.setString(1, txtNombres.text.toString())
                addPacientes.setString(2, txtApellidos.text.toString())
                addPacientes.setString(3, txtEdad.text.toString())
                addPacientes.setString(4, txtEnfermedad.text.toString())
                addPacientes.setString(5, txtNumHabitacion.text.toString())
                addPacientes.setString(6, txtNumCama.text.toString())
                addPacientes.setString(7, txtMedicamentoAsigando.text.toString())
                addPacientes.setString(8, txtFechaNacimiento.text.toString())
                addPacientes.setString(9, txtHoraDeMedicamento.text.toString())
                addPacientes.executeUpdate()

                val nuevosPacientes = obtenerPacientes()
                withContext(Dispatchers.Main) {
                    (rcvPacientes.adapter as? AdaptadorPaciente)?.updateData(nuevosPacientes)
                }
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
