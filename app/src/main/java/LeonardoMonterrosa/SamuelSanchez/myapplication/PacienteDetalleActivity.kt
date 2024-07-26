package LeonardoMonterrosa.SamuelSanchez.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import modelo.tbPacientes

class PacienteDetalleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_paciente_detalle)


        val paciente = intent.getParcelableExtra<tbPacientes>("paciente")

        val txtNombre = findViewById<TextView>(R.id.txtNombre)
        val txtApellido = findViewById<TextView>(R.id.txtApellido)
        val txtEdad = findViewById<TextView>(R.id.txtEdad)
        val txtEnfermedad = findViewById<TextView>(R.id.txtEnfermedad)
        val txtNumeroHabitacion = findViewById<TextView>(R.id.txtNumeroHabitacion)
        val txtNumeroCama = findViewById<TextView>(R.id.txtNumeroCama)
        val txtMedicamentosAsignados = findViewById<TextView>(R.id.txtMedicamentosAsignados)
        val txtFechaNacimiento = findViewById<TextView>(R.id.txtFechaNacimiento)
        val txtHoraAplicacionMedicamento = findViewById<TextView>(R.id.txtHoraAplicacionMedicamento)

        paciente?.let {
            txtNombre.text = it.nombre
            txtApellido.text = it.apellido
            txtEdad.text = it.edad.toString()
            txtEnfermedad.text = it.enfermedad
            txtNumeroHabitacion.text = it.numero_habitacion.toString()
            txtNumeroCama.text = it.numero_cama.toString()
            txtMedicamentosAsignados.text = it.medicamentos_asignados
            txtFechaNacimiento.text = it.fecha_nacimiento
            txtHoraAplicacionMedicamento.text = it.hora_aplicacion_medicamento
        }
    }
}