package LeonardoMonterrosa.SamuelSanchez.myapplication.ui.home

import LeonardoMonterrosa.SamuelSanchez.myapplication.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import LeonardoMonterrosa.SamuelSanchez.myapplication.databinding.FragmentHomeBinding
import RecycleViewHelpers.AdaptadorPaciente
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import modelo.ClaseConexion
import modelo.tbPacientes

class HomeFragment : Fragment() {




    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)


        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.tvBienvenido
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rcvPacientes = view.findViewById<RecyclerView>(R.id.rcvPacientes)

        rcvPacientes.layoutManager = LinearLayoutManager(requireContext())

        fun obtenerPacientes(): List<tbPacientes> {

            val objConexion = ClaseConexion().cadenaConexion()

            val statement = objConexion?.createStatement()
            val resulSet = statement?.executeQuery("SELECT * FROM tbPacientes")

            val listaPacientes = mutableListOf<tbPacientes>()
            while (resulSet?.next() == true) {
                val idPaciente = resulSet.getInt("idPaciente")
                val nombre = resulSet.getString("nombre")
                val apellido = resulSet.getString("apellido")
                val edad = resulSet.getInt("edad")
                val enfermadad = resulSet.getString("enfermedad")
                val numero_habitacion = resulSet.getInt("numero_habitacion")
                val numero_cama = resulSet.getInt("numero_cama")
                val medicamentos_asignados = resulSet.getString("medicamentos_asignados")
                val fecha_nacimiento = resulSet.getString("fecha_nacimiento")
                val hora_aplicacion_medicamento = resulSet.getString("hora_aplicacion_medicamento")

                val valoresJuntos = tbPacientes(idPaciente, nombre, apellido, edad, enfermadad, numero_habitacion, numero_cama, medicamentos_asignados, fecha_nacimiento, hora_aplicacion_medicamento)
                listaPacientes.add(valoresJuntos)
            }
            return listaPacientes
        }

        //probablemente halla error aqui
        CoroutineScope(Dispatchers.Main).launch {
            val pacientesDB = obtenerPacientes()
            withContext(Dispatchers.Main) {
                val adaptador = AdaptadorPaciente(pacientesDB)
                rcvPacientes.adapter = adaptador
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}