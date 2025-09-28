package com.nutrition.profile

import ContentWithMessageBar
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nutrition.shared.util.DisplayResult
import com.nutrition.profile.viewModel.ProfileViewModel
import com.nutrition.shared.BebasNeueFont
import com.nutrition.shared.FontSize
import com.nutrition.shared.IconPrimary
import com.nutrition.shared.Resources
import com.nutrition.shared.Surface
import com.nutrition.shared.SurfaceBrand
import com.nutrition.shared.SurfaceError
import com.nutrition.shared.TextPrimary
import com.nutrition.shared.TextWhite
import com.nutrition.shared.component.InfoCard
import com.nutrition.shared.component.LoadingCard
import com.nutrition.shared.component.PrimaryButton
import org.jetbrains.compose.resources.painterResource
import rememberMessageBarState
import org.koin.compose.viewmodel.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navigateBack: () -> Unit,
) {
    val viewModel = koinViewModel<ProfileViewModel>()
    val screenReady = viewModel.screenReady
    val screenState = viewModel.screenState
    val isFormValid = viewModel.isFormValid
    val messageBarState = rememberMessageBarState()

    Scaffold(
        containerColor = Surface,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "My Profile",
                        fontFamily = BebasNeueFont(),
                        fontSize = FontSize.LARGE,
                        color = TextPrimary
                    )
                },
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(
                            painter = painterResource(Resources.Icon.BackArrow),
                            contentDescription = "Back Arrow icon",
                            tint = IconPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Surface,
                    scrolledContainerColor = Surface,
                    navigationIconContentColor = IconPrimary,
                    titleContentColor = TextPrimary,
                    actionIconContentColor = IconPrimary
                )
            )
        }
    ) { padding ->
        ContentWithMessageBar(
            contentBackgroundColor = Surface,
            modifier = Modifier
                .padding(
                    top = padding.calculateTopPadding(),
                    bottom = padding.calculateBottomPadding()
                ),
            messageBarState = messageBarState,
            errorMaxLines = 2,
            errorContainerColor = SurfaceError,
            errorContentColor = TextWhite,
            successContainerColor = SurfaceBrand,
            successContentColor = TextPrimary
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
                    .padding(
                        top = 12.dp,
                        bottom = 24.dp
                    )
                    .imePadding()
            ) {
                screenReady.DisplayResult(
                    onLoading = { LoadingCard(modifier = Modifier.fillMaxSize()) },
                    onSuccess = {
                        Column(modifier = Modifier.fillMaxSize()) {
                            ProfileForm(
                                modifier = Modifier.weight(1f),
                                country = screenState.country,
                                onCountrySelect = viewModel::updateCountry,
                                firstName = screenState.firstName,
                                onFirstNameChange = viewModel::updateFirstName,
                                lastName = screenState.lastName,
                                onLastNameChange = viewModel::updateLastName,
                                email = screenState.email,
                                city = screenState.city,
                                onCityChange = viewModel::updateCity,
                                postalCode = screenState.postalCode,
                                onPostalCodeChange = viewModel::updatePostalCode,
                                address = screenState.address,
                                onAddressChange = viewModel::updateAddress,
                                phoneNumber = screenState.phoneNumber?.number,
                                onPhoneNumberChange = viewModel::updatePhoneNumber
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            PrimaryButton(
                                text = "Update",
                                icon = Resources.Icon.Checkmark,
                                enabled = isFormValid,
                                onClick = {
                                    viewModel.updateCustomer(
                                        onSuccess = {
                                            messageBarState.addSuccess("Successfully updated!")
                                        },
                                        onError = { message ->
                                            messageBarState.addError(message)
                                        }
                                    )
                                }
                            )
                        }
                    },
                    onError = { message ->
                        InfoCard(
                            image = Resources.Image.Cat,
                            title = "Oops!",
                            subtitle = message
                        )
                    }
                )
            }
        }
    }
}