package com.movtery.zalimalauncher.feature.download

import androidx.lifecycle.ViewModel
import com.movtery.zalimalauncher.feature.download.item.InfoItem
import com.movtery.zalimalauncher.feature.download.platform.AbstractPlatformHelper

class InfoViewModel : ViewModel() {
    var platformHelper: AbstractPlatformHelper? = null
    var infoItem: InfoItem? = null
}