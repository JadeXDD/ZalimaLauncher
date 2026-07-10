package com.movtery.zalimalauncher.feature.download.enums

import com.movtery.zalimalauncher.feature.download.platform.AbstractPlatformHelper
import com.movtery.zalimalauncher.feature.download.platform.curseforge.CurseForgeHelper
import com.movtery.zalimalauncher.feature.download.platform.modrinth.ModrinthHelper

enum class Platform(val pName: String, val helper: AbstractPlatformHelper) {
    MODRINTH("Modrinth", ModrinthHelper()),
    CURSEFORGE("CurseForge", CurseForgeHelper())
}