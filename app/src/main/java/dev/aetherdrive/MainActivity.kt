package dev.aetherdrive

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.aetherdrive.ui.navigation.BottomBar
import dev.aetherdrive.ui.navigation.TopBar
import dev.aetherdrive.ui.theme.AetherDriveTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AetherDriveTheme {
                val sheetState = rememberModalBottomSheetState()
                var showBottomSheet by remember { mutableStateOf(false) }

                Scaffold(
                    topBar = { TopBar() },
                    bottomBar = { BottomBar() },
                    floatingActionButton = {
                        FloatingActionButton(onClick = { showBottomSheet = !showBottomSheet }) {
                            Icon(Icons.Default.Add, contentDescription = "Add")
                        }
                    }
                ) { innerPadding ->
                    Text("temp", Modifier.padding(innerPadding))

                    BottomSheet(
                        showBottomSheet,
                        sheetState,
                        onShowBottomSheet = { showBottomSheet = false }
                    )
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun BottomSheet(
    showBottomSheet: Boolean,
    sheetState: SheetState,
    onShowBottomSheet: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                onShowBottomSheet()
            },
            sheetState = sheetState,
        ) {
            Text(
                "TODO: Make this bottom sheet show bunch of buttons in " +
                        "a grid with options for creating folders, uploads, etc...",
                modifier = Modifier.padding(12.dp)
            )
        }
    }
}