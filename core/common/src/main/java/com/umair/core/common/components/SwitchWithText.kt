package com.umair.core.common.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.umair.core.common.R
import com.umair.core.common.theme.darkGray

@Composable
fun SwitchWithText(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    val thumbOffset by animateDpAsState(
        targetValue = if (checked) 58.dp else 0.dp,
        label = "thumbOffset"
    )

    Box(
        modifier = Modifier
            .width(90.dp)
            .height(32.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(if (checked) Color(0xFFFFFFFF) else darkGray)
            .clickable { onCheckedChange(!checked) }
            .padding(horizontal = 4.dp, vertical = 4.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            if(checked) {
                Text(
                    text = "LIGHT MODE",
                    color = darkGray,
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterStart)
                        .padding(3.dp, 0.dp)
                )
            } else {
                Text(
                    text = "DARK MODE",
                    color = Color.LightGray,
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterEnd)
                        .padding(3.dp, 0.dp)
                )
            }
        }

        Box(
            modifier = Modifier
                .offset(x = thumbOffset)
                .size(24.dp)
                .background(Color.White, CircleShape)
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(24.dp)
                ).border(0.1.dp, darkGray, CircleShape)
        ) {
            if(checked) {
                Icon(
                    painter = painterResource(R.drawable.ic_light_mode),
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.Center).padding(3.dp),
                    tint = darkGray
                )
            } else {
                Icon(
                    painter = painterResource(R.drawable.ic_dark_mode),
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.Center).padding(3.dp),
                    tint = darkGray
                )
            }
        }
    }
}