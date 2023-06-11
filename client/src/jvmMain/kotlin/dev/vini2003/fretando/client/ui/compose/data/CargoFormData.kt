package dev.vini2003.fretando.client.ui.compose.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import dev.vini2003.fretando.common.entity.Cargo

data class CargoFormData(
    val length: MutableState<String> = mutableStateOf(""),
    val lengthError: MutableState<String?> = mutableStateOf(null),
    val lengthUnit: MutableState<String> = mutableStateOf(Cargo.DimensionUnit.METERS.asString()),
    val width: MutableState<String> = mutableStateOf(""),
    val widthError: MutableState<String?> = mutableStateOf(null),
    val widthUnit: MutableState<String> = mutableStateOf(Cargo.DimensionUnit.METERS.asString()),
    val height: MutableState<String> = mutableStateOf(""),
    val heightError: MutableState<String?> = mutableStateOf(null),
    val heightUnit: MutableState<String> = mutableStateOf(Cargo.DimensionUnit.METERS.asString()),
    val weight: MutableState<String> = mutableStateOf(""),
    val weightError: MutableState<String?> = mutableStateOf(null),
    val weightUnit: MutableState<String> = mutableStateOf(Cargo.WeightUnit.KILOGRAMS.asString()),
    val description: MutableState<String> = mutableStateOf(""),
    val descriptionError: MutableState<String?> = mutableStateOf(null),
) {
    constructor(cargo: Cargo) : this() {
        length.value = cargo.length.toString()
        lengthUnit.value = cargo.lengthUnit.asString()
        width.value = cargo.width.toString()
        widthUnit.value = cargo.widthUnit.asString()
        height.value = cargo.height.toString()
        heightUnit.value = cargo.heightUnit.asString()
        weight.value = cargo.weight.toString()
        weightUnit.value = cargo.weightUnit.asString()
        description.value = cargo.description
    }

    fun toCargo(): Cargo {
        return Cargo(
            length.value.toDouble(),
            Cargo.DimensionUnit.values().first { lengthUnit -> lengthUnit.asString() == this.lengthUnit.value },
            width.value.toDouble(),
            Cargo.DimensionUnit.values().first { lengthUnit -> lengthUnit.asString() == this.widthUnit.value },
            height.value.toDouble(),
            Cargo.DimensionUnit.values().first { lengthUnit -> lengthUnit.asString() == this.heightUnit.value },
            weight.value.toDouble(),
            Cargo.WeightUnit.values().first { lengthUnit -> lengthUnit.asString() == this.weightUnit.value },
            description.value
        )
    }

    fun validate(): Boolean {
        var valid = true

        if (length.value.isEmpty()) {
            lengthError.value = "Length is required"
            valid = false
        } else {
            lengthError.value = null
        }

        if (!Cargo.DimensionUnit.values().map { it.asString() }.contains(lengthUnit.value)) {
            lengthError.value = "Length unit is invalid"
            valid = false
        }

        if (width.value.isEmpty()) {
            widthError.value = "Width is required"
            valid = false
        } else {
            widthError.value = null
        }

        if (!Cargo.DimensionUnit.values().map { it.asString() }.contains(widthUnit.value)) {
            widthError.value = "Width unit is invalid"
            valid = false
        }

        if (height.value.isEmpty()) {
            heightError.value = "Height is required"
            valid = false
        } else {
            heightError.value = null
        }

        if (!Cargo.DimensionUnit.values().map { it.asString() }.contains(heightUnit.value)) {
            heightError.value = "Height unit is invalid"
            valid = false
        }

        if (weight.value.isEmpty()) {
            weightError.value = "Weight is required"
            valid = false
        } else {
            weightError.value = null
        }

        if (!Cargo.WeightUnit.values().map { it.asString() }.contains(weightUnit.value)) {
            weightError.value = "Weight unit is invalid"
            valid = false
        }

        if (description.value.isEmpty()) {
            descriptionError.value = "Description is required"
            valid = false
        } else {
            descriptionError.value = null
        }

        return valid
    }
}