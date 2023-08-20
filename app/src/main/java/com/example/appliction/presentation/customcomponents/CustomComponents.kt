package com.example.appliction.presentation.customcomponents

import android.content.Context
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun CustomOutlinedTextField(
    text: String,
    onTextChange: (String) -> Unit,
    enable: Boolean = true,
    readOnly: Boolean = false,
    label: String = "",
    placeholder: String = "",
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    prefix: String = "",
    suffix: String = "",
    supportingText: String = "",
    inValidData: Boolean = false,
    visible: Boolean = true,
    keyOptions: KeyboardOptions = KeyboardOptions.Default,
    keyActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = true,
    maxLines: Int = 1,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = text,
        onValueChange = { onTextChange(it) },
        enabled = enable,
        readOnly = readOnly,
        label = { Text(text = label) },
        placeholder = { Text(text = placeholder) },
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        prefix = { Text(text = prefix) },
        suffix = { Text(text = suffix) },
        supportingText = { Text(text = supportingText) },
        isError = inValidData,
        visualTransformation = if (visible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = keyOptions,
        keyboardActions = keyActions,
        singleLine = singleLine,
        maxLines = maxLines

    )
}


fun showDatePicker(
    selectedDate: MutableState<String>,
    year: Int,
    month: Int,
    day: Int,
    context: Context,
) {
    val datePicker = android.app.DatePickerDialog(
        context,
        { _, year, month, day ->
            selectedDate.value = "$day/${month + 1}/$year"
            //Toast.makeText(context, selectedDate.value, Toast.LENGTH_SHORT).show()
        },
        year,
        month,
        day
    )
    datePicker.show()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownList(
    expanded: Boolean,
    selectedItem: String,
    onExpandedChange: (Boolean) -> Unit,
    onDropDownMenuDismiss: () -> Unit,
    onDropDownItemClick: (String) -> Unit,
    dropDownList: List<String>,
    label: String,
) {
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { onExpandedChange(it) }) {
        CustomOutlinedTextField(
            text = selectedItem,
            onTextChange = {},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            modifier = Modifier.menuAnchor(),
            label = label
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { onDropDownMenuDismiss() }) {
            dropDownList.forEach {
                DropdownMenuItem(
                    text = { Text(text = it) },
                    onClick = { onDropDownItemClick(it) }
                )
            }

        }
    }
}





