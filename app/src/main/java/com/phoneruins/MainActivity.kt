package com.phoneruins

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Night = Color(0xFF10131A)
private val Stone = Color(0xFF343C49)
private val Floor = Color(0xFF1B2530)
private val Mint = Color(0xFF75F0B5)
private val Gold = Color(0xFFFFD166)
private val Danger = Color(0xFFFF6B6B)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val prefs = getSharedPreferences("phone_ruins", MODE_PRIVATE)
        setContent {
            MaterialTheme {
                var room by remember { mutableIntStateOf(prefs.getInt("room", 0).coerceIn(0, 4)) }
                var hasKey by remember { mutableStateOf(prefs.getBoolean("key", false)) }
                var message by remember { mutableStateOf("Find the key. Explore the ruins.") }

                Surface(modifier = Modifier.fillMaxSize(), color = Night) {
                    Column(
                        modifier = Modifier.fillMaxSize().padding(18.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("PHONE RUINS", color = Mint, fontSize = 25.sp, fontWeight = FontWeight.Black)
                        Text("v0.1.0  •  OFFLINE PIXEL ADVENTURE", color = Color.LightGray, fontSize = 10.sp)
                        Spacer(Modifier.height(16.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            repeat(5) { index ->
                                Box(
                                    modifier = Modifier.size(38.dp).background(
                                        if (index == room) Mint else Stone,
                                        RoundedCornerShape(6.dp)
                                    ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text("${index + 1}", color = if (index == room) Night else Color.White)
                                }
                            }
                        }
                        Spacer(Modifier.height(14.dp))
                        Canvas(modifier = Modifier.fillMaxWidth().weight(1f)) {
                            val cell = minOf(size.width / 11f, size.height / 13f)
                            val mapW = cell * 11f
                            val mapH = cell * 13f
                            val left = (size.width - mapW) / 2f
                            val top = (size.height - mapH) / 2f
                            drawRect(Floor, Offset(left, top), Size(mapW, mapH))
                            for (y in 0..12) for (x in 0..10) {
                                val edge = x == 0 || x == 10 || y == 0 || y == 12
                                val blocked = (x == 4 && y in 3..8 && !(y == 6 && room % 2 == 0)) ||
                                    (y == 4 && x in 6..8 && room % 2 == 1)
                                if (edge || blocked) {
                                    drawRect(Stone, Offset(left + x * cell, top + y * cell), Size(cell - 2f, cell - 2f))
                                } else {
                                    drawRect(Color(0xFF222D38), Offset(left + x * cell, top + y * cell), Size(cell - 2f, cell - 2f))
                                }
                            }
                            drawRect(Gold, Offset(left + cell * 5f, top + cell * 11f), Size(cell, cell))
                            if (!hasKey && room == 0) {
                                drawCircle(Gold, cell * .22f, Offset(left + cell * 8f, top + cell * 3f))
                            }
                            drawRect(Danger, Offset(left + cell * (2 + room % 5), top + cell * 7f), Size(cell * .75f, cell * .75f))
                            drawRect(Mint, Offset(left + cell * 5f, top + cell * 9f), Size(cell, cell))
                            drawRect(Color.White, Offset(left + cell * 5f, top + cell * 9f), Size(cell, cell), style = Stroke(2f))
                        }
                        Spacer(Modifier.height(12.dp))
                        Text("ROOM ${room + 1}/5     KEY: ${if (hasKey) "YES" else "NO"}", color = Gold, fontWeight = FontWeight.Bold)
                        Text(message, color = Color.LightGray, fontSize = 12.sp)
                        Spacer(Modifier.height(12.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                            Button(onClick = {
                                if (room > 0) {
                                    room--
                                    message = "Moved to room ${room + 1}."
                                    prefs.edit().putInt("room", room).apply()
                                } else message = "The passage ends here."
                            }) { Text("◀") }
                            Button(onClick = {
                                if (!hasKey && room == 0) {
                                    hasKey = true
                                    prefs.edit().putBoolean("key", true).apply()
                                    message = "Key collected!"
                                } else if (room < 4 && (room < 2 || hasKey)) {
                                    room++
                                    prefs.edit().putInt("room", room).apply()
                                    message = "Entered room ${room + 1}."
                                } else {
                                    message = "A locked door blocks the way. Find the key in room 1."
                                }
                            }) { Text("EXPLORE  ▶") }
                        }
                        Spacer(Modifier.height(8.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Button(onClick = {
                                room = 0
                                hasKey = false
                                prefs.edit().clear().apply()
                                message = "A new journey begins."
                            }) { Text("NEW WORLD") }
                        }
                    }
                }
            }
        }
    }
}
