/*
 * Zalith Launcher 2
 * Copyright (C) 2025 MovTery <movtery228@qq.com> and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/gpl-3.0.txt>.
 */

package com.movtery.zalithlauncher.ui.vulkan_checker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.movtery.zalithlauncher.R
import com.movtery.zalithlauncher.utils.device.VulkanCapabilities

@Composable
fun VulkanChecker(
    operation: VCOperation,
    onChange: (VCOperation) -> Unit
) {
    when (operation) {
        is VCOperation.None -> {}
        is VCOperation.Result -> {
            val data = operation.data
            val useTurnip = operation.useTurnip
            val isUnsupp = data == null || !data.isAllSupported

            AlertDialog(
                onDismissRequest = {
                    onChange(VCOperation.None)
                },
                title = {
                    Text(text = stringResource(R.string.game_vulkan_check_title))
                },
                text = {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        CompositionLocalProvider(
                            LocalTextStyle provides MaterialTheme.typography.labelMedium
                        ) {

                            //设备不支持 Vulkan
                            if (isUnsupp) {
                                Text(stringResource(R.string.game_vulkan_check_unsupp))
                            } else {
                                Text(stringResource(R.string.game_vulkan_check_supp))
                            }

                            if (data != null) {
                                //版本号
                                Text(stringResource(R.string.game_vulkan_check_version, data.versionString))
                                //是否使用Turnip
                                Text(stringResource(R.string.game_vulkan_check_turnip, useTurnip))

                                //不支持时，显示缺失的扩展、功能
                                if (!data.isAllSupported) {
                                    TextGroup(
                                        text = stringResource(R.string.game_vulkan_check_missing_extensions),
                                        columns = data.missingExtensions
                                    )
                                    TextGroup(
                                        text = stringResource(R.string.game_vulkan_check_missing_features),
                                        columns = data.missingFeatures
                                    )
                                }

                                if (data.isAllSupported) {
                                    TextGroup(
                                        text = stringResource(R.string.game_vulkan_check_extensions),
                                        columns = VulkanCapabilities.REQUIRED_EXTENSIONS
                                    )
                                    TextGroup(
                                        text = stringResource(R.string.game_vulkan_check_features),
                                        columns = VulkanCapabilities.REQUIRED_FEATURES
                                    )
                                }
                            }
                        }
                    }
                },
                confirmButton = {
                    FilledTonalButton(
                        onClick = {
                            onChange(VCOperation.None)
                        }
                    ) {
                        Text(text = stringResource(R.string.generic_confirm))
                    }
                }
            )
        }
    }
}

@Composable
private fun TextGroup(
    text: String,
    columns: List<String>,
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.spacedBy(4.dp)
) {
    Row(
        modifier = modifier,
        horizontalArrangement = horizontalArrangement
    ) {
        Text(text)
        Column(modifier = Modifier.weight(1f)) {
            columns.forEach { string ->
                Text(string)
            }
        }
    }
}