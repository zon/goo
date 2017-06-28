package org.haralovich.goo

import android.content.Context
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule

object Goo {

    val mapper = ObjectMapper(YAMLFactory()).registerModule(KotlinModule())

    fun load(context: Context, asset: String): JsonNode {
        return mapper.readTree(context.assets.open(asset + ".yaml"))
    }

}