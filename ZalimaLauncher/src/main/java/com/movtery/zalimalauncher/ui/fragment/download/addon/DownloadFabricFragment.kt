package com.movtery.zalimalauncher.ui.fragment.download.addon

import com.movtery.zalimalauncher.R
import com.movtery.zalimalauncher.feature.mod.modloader.FabricLikeUtils

class DownloadFabricFragment : DownloadFabricLikeFragment(FabricLikeUtils.FABRIC_UTILS, R.drawable.ic_fabric) {
    companion object {
        const val TAG: String = "DownloadFabricFragment"
    }
}