package mx.edu.ittepic.tap_u5_ejercicio2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main4.*
import java.io.OutputStreamWriter

class MainActivity4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        guardar.setOnClickListener {
            if(guardarEnMemoriaInterna(contenidoArchivo.text.toString())){
                contenidoArchivo.setText("")
                AlertDialog.Builder(this).setTitle("EXITO").setMessage("SE GUARDO EL ARCHIVO CORRECTAMENTE")
                    .setPositiveButton("OK", { d, i -> d.dismiss() }).show()
            }
        }

        regresarArchivos.setOnClickListener {
            finish()
        }

    }

    fun guardarEnMemoriaInterna(conten:String):Boolean{
        if(conten.isEmpty()){
            Toast.makeText(this, "ERROR, DEBES PONER UN ENUNCIADO A GUARDAR", Toast.LENGTH_LONG).show()
            return false
        }

        try {
            var flujoSalida = OutputStreamWriter( openFileOutput("almacen.txt", MODE_PRIVATE) )

            flujoSalida.write(conten) //Guardar
            flujoSalida.flush()  //Forza a que el Android guarde sobre el archivo.
            flujoSalida.close()  //Cerrando el archivo
            return true

        }catch (io:Exception){
            AlertDialog.Builder(this).setTitle("ERROR").setMessage("NO SE PUDO GUARDAR EN ARCHIVO").show()
        }

        return false

    }
}