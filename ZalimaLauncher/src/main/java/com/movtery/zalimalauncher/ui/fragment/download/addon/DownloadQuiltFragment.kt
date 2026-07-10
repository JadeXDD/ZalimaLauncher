package com.movtery.zalimalauncher.ui.fragment.download.addon

import com.movtery.zalimalauncher.R
import com.movtery.zalimalauncher.feature.mod.modloader.FabricLikeUtils

class DownloadQuiltFragment : DownloadFabricLikeFragment(FabricLikeUtils.QUILT_UTILS, R.drawable.ic_quilt) {
    companion object {
        const val TAG: String = "DownloadQuiltFragment"
    }
}