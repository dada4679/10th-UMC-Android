package com.example.week7.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week7.R

@Composable
fun BagScreen(onOrderClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier.fillMaxSize().padding(bottom = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Image(
                painter= painterResource(id= R.drawable.bagcircle),
                contentDescription = "빈 장바구니",
                modifier = Modifier.size(60.dp),
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text = "장바구니가 비어 있습니다.",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = "제품을 추가하면 여기에 표시됩니다.",
                color = Color.Gray,
                fontSize = 12.sp,
            )
        }

        Button(
            onClick = onOrderClick,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(20.dp)
                .height(60.dp),
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
        ) { Text("주문하기", color = Color.White) }
    }
}