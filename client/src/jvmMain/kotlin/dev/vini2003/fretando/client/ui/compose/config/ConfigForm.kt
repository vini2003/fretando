package dev.vini2003.fretando.client.ui.compose.config

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.vini2003.fretando.client.properties.Properties
import dev.vini2003.fretando.client.ui.theme.paddings
import dev.vini2003.fretando.client.ui.theme.spacers

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalComposeUiApi
@Composable
@Preview
fun ConfigForm(
    data: MutableState<Properties> = remember { mutableStateOf(Properties) },
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .width(350.dp)
            .shadow(4.dp, MaterialTheme.shapes.medium)
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.surface)
            .verticalScroll(rememberScrollState())
            .padding(MaterialTheme.paddings.large)
    ) {
        Text(
            "Configuration",
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.75F), // TODO: Check this!
            modifier = Modifier.padding(MaterialTheme.paddings.small)
        )

        OutlinedTextField(value = data.value.addressRepositoryUrl, onValueChange = { data.value.addressRepositoryUrl = it }, label = { Text("Address Repository URL") }, modifier = Modifier.width(300.dp))
        Spacer(Modifier.height(MaterialTheme.spacers.medium))
        OutlinedTextField(value = data.value.bidRepositoryUrl, onValueChange = { data.value.bidRepositoryUrl = it }, label = { Text("Bid Repository URL") }, modifier = Modifier.width(300.dp))
        Spacer(Modifier.height(MaterialTheme.spacers.medium))
        OutlinedTextField(value = data.value.cargoRepositoryUrl, onValueChange = { data.value.cargoRepositoryUrl = it }, label = { Text("Cargo Repository URL") }, modifier = Modifier.width(300.dp))
        Spacer(Modifier.height(MaterialTheme.spacers.medium))
        OutlinedTextField(value = data.value.requestRepositoryUrl, onValueChange = { data.value.requestRepositoryUrl = it }, label = { Text("Request Repository URL") }, modifier = Modifier.width(300.dp))
        Spacer(Modifier.height(MaterialTheme.spacers.medium))
    }
}
