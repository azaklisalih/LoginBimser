package com.example.bimserlogin.view

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bimserlogin.repository.DataStorePreferenceRepository
import com.example.bimserlogin.util.DataStoreViewModelFactory
import com.example.bimserlogin.viewModel.LanguageViewModel
import kotlinx.coroutines.launch
import java.util.*

private val language = listOf("Turkish", "English")

@Composable
fun MultiLanguage(
    viewModel: LanguageViewModel = viewModel(
        factory = DataStoreViewModelFactory(DataStorePreferenceRepository(LocalContext.current))
    )
) {
    val scope = rememberCoroutineScope()
    val currentLanguage = viewModel.language.observeAsState().value
    //val menuExpanded = remember { mutableStateOf(false) }
    val context = LocalContext.current

    SetLanguage(position = currentLanguage!!)

    Column {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState(0))
                .fillMaxSize()
                .background(Color.Transparent)
        ) {
            LanguagePicker(currentLanguage) { selected ->
                scope.launch {
                    viewModel.saveLanguage(selected, context)
                }
            }
        }
    }
}

@Composable
fun LanguagePicker(
    selectedPosition: Int,
    onLanguageSelected: (Int) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxSize(),
        shape = RoundedCornerShape(16.dp),
        elevation = 0.dp,
        backgroundColor = Color.Transparent
    ) {
        LanguageContentPortrait(selectedPosition, onLanguageSelected)
    }
}

@Composable
fun LanguageContentPortrait(
    selectedPosition: Int,
    onLanguageSelected: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ToggleGroup(selectedPosition = selectedPosition, onClick = onLanguageSelected)
    }
}

@Composable
fun SetLanguage(position: Int) {
    val locale = Locale(if (position == 1) "en" else "tr")
    val configuration = LocalConfiguration.current
    configuration.setLocale(locale)
    val resources = LocalContext.current.resources
    resources.updateConfiguration(configuration, resources.displayMetrics)
}

@Composable
private fun ToggleGroup(
    selectedPosition: Int,
    onClick: (Int) -> Unit
) {
    val shape = RoundedCornerShape(4.dp)
    Row(
        modifier = Modifier
            .padding(vertical = 0.dp)
            .clip(shape)
            .border(1.dp, Color(0xFFAAAAAA), shape)
    ) {
        language.forEachIndexed { index, element ->
            val verticalPadding = if (index == selectedPosition) 8.dp else 0.dp
            Text(
                text = element,
                color = if (index != selectedPosition) Color.Black else Color.White,
                modifier = Modifier
                    .align(CenterVertically)
                    .run {
                        if (index != selectedPosition) this
                        else background(MaterialTheme.colors.primary).border(1.dp, Color.Gray)
                    }
                    .clickable(
                        onClick = { onClick(index) },
                        role = Role.RadioButton
                    )
                    .padding(horizontal = 16.dp, vertical = verticalPadding)
            )
        }
    }
}