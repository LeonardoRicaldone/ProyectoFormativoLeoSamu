package modelo

import android.os.Parcel
import android.os.Parcelable

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
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nombre)
        parcel.writeString(apellido)
        parcel.writeInt(edad)
        parcel.writeString(enfermedad)
        parcel.writeInt(numero_habitacion)
        parcel.writeInt(numero_cama)
        parcel.writeString(medicamentos_asignados)
        parcel.writeString(fecha_nacimiento)
        parcel.writeString(hora_aplicacion_medicamento)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<tbPacientes> {
        override fun createFromParcel(parcel: Parcel) = tbPacientes(parcel)
        override fun newArray(size: Int) = arrayOfNulls<tbPacientes>(size)
    }
}