package com.example.facebookcloneusingjetpackcompose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavGraphSealed(val title:String,val icon:ImageVector,val route:String){
    object Home:BottomNavGraphSealed("Home", icon =Icons.Default.Home,"home")
    object Friends:BottomNavGraphSealed("Friends",Icons.Default.Group,"friends")
    object Videos:BottomNavGraphSealed("Videos",Icons.Default.OndemandVideo,"video")
    object Notifications:BottomNavGraphSealed("Notifications",Icons.Default.Notifications,"notifications")
    object More:BottomNavGraphSealed("More",Icons.Default.MoreHoriz,"more")
}
