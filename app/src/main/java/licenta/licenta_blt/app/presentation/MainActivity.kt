package licenta.licenta_blt.app.presentation

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import licenta.licenta_blt.app.presentation.ui.screen.MainScreen
import licenta.licenta_blt.app.presentation.ui.theme.AppTheme

class MainActivity : ComponentActivity() {

    lateinit var bluetoothManager: BluetoothManager
    lateinit var bluetoothAdapter: BluetoothAdapter
    lateinit var takePermission: ActivityResultLauncher<String>
    lateinit var takeResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bluetoothManager = getSystemService(BLUETOOTH_SERVICE) as BluetoothManager
        bluetoothAdapter = bluetoothManager.adapter
        takePermission = registerForActivityResult(ActivityResultContracts.RequestPermission()){
            if(it){
                val intent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                takeResultLauncher.launch(intent)
            }
            else
            {
                Toast.makeText(applicationContext, "Bluetooth permission is not given", Toast.LENGTH_SHORT).show()
            }
        }

        takeResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(),
            ActivityResultCallback { result ->
                if(result.resultCode == RESULT_OK)
                {
                    Toast.makeText(applicationContext, "Bluetooth On", Toast.LENGTH_SHORT).show()
                }
                else
                {
                    Toast.makeText(applicationContext, "Bluetooth Off", Toast.LENGTH_SHORT).show()
                }
            }
            )

        setContent {
            AppTheme {
                MainScreen(
                    bluetoothOn = { turnBlOn() },
                    bluetoothOff = { turnBlOff() }
                )
            }
        }

    }

    private fun turnBlOn()
    {
        takePermission.launch(android.Manifest.permission.BLUETOOTH_CONNECT)
    }

    private fun turnBlOff()
    {
        bluetoothAdapter.disable()
    }
}