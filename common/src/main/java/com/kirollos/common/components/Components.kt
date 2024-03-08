package com.kirollos.common.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import com.kirollos.common.size_16dp

@Composable
fun GetText(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle = MaterialTheme.typography.bodyMedium,
    color: Color = MaterialTheme.colorScheme.secondary
) {
    Text(
        modifier = modifier,
        text = text,
        style = style,
        color = color
    )
}

@Composable
fun ErrorContent(
    errorMessage: String,
    refreshIcon: Int,
    onRefreshIconClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(size_16dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GetText(
            text = errorMessage,
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(size_16dp))

        IconButton(onClick = { onRefreshIconClicked() }) {
            Icon(
                painter = painterResource(id = refreshIcon),
                contentDescription = null
            )
        }
    }
}

@Composable
fun LoadingContent() {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }
}
