package licenta.licenta_blt.app.presentation.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import licenta.licenta_blt.app.presentation.ui.theme.CustomText

@Composable
fun MainScreen(
    bluetoothOn: () -> Unit,
    bluetoothOff: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CustomText("Bluetooth On/Off")

        OutlinedButton(
            modifier = Modifier.padding(5.dp),
            onClick = { bluetoothOn() }) {
            CustomText(text = "On")
        }

        OutlinedButton(
            onClick = { bluetoothOff() }) {
            CustomText(text = "Off")
        }
    }
}
