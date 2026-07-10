package com.movtery.zalimalauncher.feature.unpack

import android.content.Context
import com.movtery.zalimalauncher.feature.log.Logging.e
import com.movtery.zalimalauncher.utils.CopyDefaultFromAssets.Companion.copyFromAssets
import com.movtery.zalimalauncher.utils.path.PathManager
import net.kdt.pojavlaunch.Tools

class UnpackSingleFilesTask(val context: Context) : AbstractUnpackTask() {
    override fun isNeedUnpack(): Boolean = true

    override fun run() {
        runCatching {
            copyFromAssets(context)
            Tools.copyAssetFile(context, "resolv.conf", PathManager.DIR_DATA, false)
        }.getOrElse { e("AsyncAssetManager", "Failed to unpack critical components !") }
    }
}