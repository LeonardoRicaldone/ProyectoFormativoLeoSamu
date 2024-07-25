package modelo

import java.sql.Connection
import java.sql.DriverManager

class ClaseConexion {

    fun cadenaConexion(): Connection? {

        try {
            val ipLeo = "jdbc:oracle:thin:@192.168.0.11:1521:xe"
            val ipSamu = "jdbc:oracle:thin:@tuipsamu:1521:xe"
            val usuario = "SAMUELSS_DEVELOPER"
            val contrasena = "123456"

            val connection = DriverManager.getConnection(ipSamu, usuario, contrasena)
            return connection
        }catch (e: Exception) {
            println("error: $e")
            return null
        }

    }

}