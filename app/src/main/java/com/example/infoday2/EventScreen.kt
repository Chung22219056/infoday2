import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.infoday2.ui.theme.Infoday2Theme
import java.security.AccessController

data class Event(val title: String, val deptId: String, var saved: Boolean){
    companion object{
        val data = listOf(
            Event(title = "Career Talks", deptId = "COMS", saved = false),
            Event(title = "Guided Tour", deptId = "COMS", saved = true),
            Event(title = "MindDrived Demo", deptId = "COMP", saved = false),
            Event(title = "Project Demo", deptId = "COMP", saved = false)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventScreen(deptId: String?){
    LazyColumn{
        items(Event.data.filter { it.deptId == deptId }){event ->
            ListItem(
                headlineText = { Text(event.title)}
            )
            Divider()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EventPreview(){
    Infoday2Theme() {
        EventScreen("COMP")
    }
}

