package com.example.infoday

import DeptNav
import android.content.Intent
import android.icu.text.CaseMap.Title
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.infoday2.*
import com.example.infoday2.R

import com.example.infoday2.ui.theme.Infoday2Theme
import java.util.function.IntConsumer

@Composable
fun InfoGreeting() {
    val padding = 16.dp
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(Modifier.size(padding))
        Image(
            painter = painterResource(id = R.drawable.hkbu_logo),
            contentDescription = stringResource(id = R.string.hkbu_logo),
            modifier = Modifier.size(240.dp)
        )
        Spacer(Modifier.size(padding))
        Text(text = "HKBU InfoDay App", fontSize = 30.sp)
    }
}

data class Contact(val office: String, val tel: String) {
    companion object {
        val data = listOf(
            Contact(office = "Admission Office", tel = "3411-2200"),
            Contact(office = "Emergencies", tel = "3411-7777"),
            Contact(office = "Health Services Center", tel = "3411-7447")
        )
    }
}

@Composable
fun InfoScreen() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        InfoGreeting()
        PhoneList()
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhoneList() {
    val ctx = LocalContext.current

    Column {
        Contact.data.forEach { message ->
            var modifier = Modifier.clickable {
                val u = Uri.parse("tel:" + message.tel)
                val i = Intent(Intent.ACTION_DIAL, u)
                ctx.startActivity(i)
            }
            ListItem(
                headlineText = { Text(message.office) },
                leadingContent = {
                    Icon(
                        Icons.Filled.Call,
                        contentDescription = null
                    )
                },
                trailingContent = { Text(message.tel) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InfoPreview() {
    Infoday2Theme  {
        InfoScreen()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldScreen(){

    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("Home", "Event", "Itin", "Map", "Info")
    var navController = rememberNavController()

    val feeds = produceState(
        initialValue = listOf<Feed>(),
        producer = {
            value = getFeeds()
        }
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("HKBU InfoDay App") },
                navigationIcon = {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()

                    if (navBackStackEntry?.arguments?.getBoolean("topLevel") == false){
                        IconButton(
                            onClick = {navController.navigateUp()}
                        ) {
                            Icon( imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Back")
                        }
                    } else{
                        null
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                items.forEachIndexed{index, item ->
                    NavigationBarItem(
                        icon = { Icon(Icons.Filled.Favorite, contentDescription = item )},
                        label = {Text(item)},
                        selected = selectedItem == index,
                        onClick = { selectedItem = index }
                    )
                }
            }
        },
       content = { innerPadding ->
           Column(
               modifier = Modifier.padding(innerPadding),
           ) {
               when(selectedItem){
                   0 -> FeedScreen(feeds.value)
                   1 -> DeptNav(navController)
                   2 -> InfoScreen()
                   3 -> MapScreen()
                   4 -> InfoScreen()
               }
           }
       }
    )

}


