import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.infoday2.ui.theme.Infoday2Theme

data class Dept(val name:String, val id:String){
    companion object{
        val data = listOf(
            Dept("Computer Science", "COMP"),
            Dept("Communication Studies", "COMS")
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeptScreen(navController: NavHostController){
    LazyColumn{
        items(Dept.data) { dept ->
            ListItem(
                headlineText = { Text(dept.name)},
                modifier = Modifier.clickable {
                    navController.navigate("event/${dept.id}")
                },
                leadingContent = {
                    Icon(
                        Icons.Filled.ThumbUp,
                        contentDescription = null
                    )
                }
            )
            Divider()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DeptPreview(){
    Infoday2Theme() {
        DeptNav(rememberNavController())
    }
}

@Composable
fun DeptNav(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = "dept",
    ){
        composable("dept") { DeptScreen(navController)}
        composable("event/{deptId}"){backStackEntry ->
        EventScreen(backStackEntry.arguments?.getString("deptId"))
        }
    }
}


