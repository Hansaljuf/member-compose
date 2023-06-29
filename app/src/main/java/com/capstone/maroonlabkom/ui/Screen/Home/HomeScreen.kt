package com.capstone.maroonlabkom.ui.Screen.Home

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.capstone.maroonlabkom.di.Injection
import com.capstone.maroonlabkom.model.Member
import com.capstone.maroonlabkom.ui.ViewModelFactory
import com.capstone.maroonlabkom.ui.common.UiState
import com.capstone.maroonlabkom.ui.components.MemberItem

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository())),
    navigateToDetail:(Int)->Unit
){
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let {
        uiState -> when(uiState){
            is UiState.Loading->{
                viewModel.getAllMember()
            }
            is UiState.Success->{
                HomeContent(member=uiState.data,modifier=Modifier,navigateToDetail=navigateToDetail)
            }
            is UiState.Error->{
                Toast.makeText(LocalContext.current,"Gagal Menampilkan data!",Toast.LENGTH_SHORT).show()
            }
        }
    }

}

@Composable
fun HomeContent(member: List<Member>, modifier: Modifier,navigateToDetail: (Int) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(160.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
       items(member){ item: Member ->
           MemberItem(
               name = item.name,
               image = item.photo,
               modifier=modifier.clickable { navigateToDetail(item.id) }
           )
       }
    }
}
