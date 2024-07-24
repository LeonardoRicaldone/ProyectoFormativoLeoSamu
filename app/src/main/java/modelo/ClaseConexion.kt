package modelo

import java.sql.Connection
import java.sql.DriverManager

class ClaseConexion {

    fun cadenaConexion(): Connection? {

        try {
            val ipLeo = "jdbc:oracle:thin:@192.168.0.11:1521:xe"
            val ipSamu = "jdbc:oracle:thin:@tuipsamu:1521:xe"
            val usuario = "system"
            val contrasena = "desarrollo"

            val connection = DriverManager.getConnection(ipLeo, usuario, contrasena)
            return connection
        }catch (e: Exception) {
            println("error: $e")
            return null
        }

    }

}