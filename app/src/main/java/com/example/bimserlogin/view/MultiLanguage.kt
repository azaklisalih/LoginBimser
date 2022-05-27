package com.example.bimserlogin.view

import android.content.Context
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.Alignment.Companion.TopStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.bimserlogin.model.LoginRequest
import com.example.bimserlogin.repository.LoginRepository
import com.example.bimserlogin.util.DataStorePreferenceRepository
import com.example.bimserlogin.util.DataStoreViewModelFactory
import com.example.bimserlogin.viewModel.LanguageViewModel
import com.example.bimserlogin.viewModel.LoginViewModel
import kotlinx.coroutines.launch
import java.util.*

private val language = listOf("Turkish", "English")
@Composable
fun MultiLanguage(
    viewModel : LanguageViewModel = viewModel(
        factory = DataStoreViewModelFactory(DataStorePreferenceRepository(LocalContext.current))
    )
){
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
                    viewModel.saveLanguage(selected,context)
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

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = com.example.bimserlogin.R.string.menulanguage),
                modifier = Modifier.fillMaxSize(),
                textAlign = TextAlign.Center
            )
        }
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