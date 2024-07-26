package modelo

import java.sql.Connection
import java.sql.DriverManager

class ClaseConexion {

    fun cadenaConexion(): Connection? {

        try {
            val ipLeo = "jdbc:oracle:thin:@192.168.0.11:1521:xe"
            val ipSamu = "jdbc:oracle:thin:@tuipsamu:1521:xe"

            val usuarioSamuel = "SAMUELSS_DEVELOPER"
            val contrasenaSamuel = "123456"

            val usuarioLeonardo = "SYSTEM"
            val contrasenaLeonardo = "desarrollo"


            val connection = DriverManager.getConnection(ipLeo, usuarioLeonardo, contrasenaLeonardo)
            return connection
        }catch (e: Exception) {
            println("error: $e")
            return null
        }

    }

}