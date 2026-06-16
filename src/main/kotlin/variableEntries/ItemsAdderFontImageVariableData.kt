package dev.bn2k9.variableEntries

import com.typewritermc.core.books.pages.Colors
import com.typewritermc.core.extension.annotations.Entry
import com.typewritermc.core.extension.annotations.VariableData
import com.typewritermc.engine.paper.entry.entries.VarContext
import com.typewritermc.engine.paper.entry.entries.VariableEntry
import com.typewritermc.engine.paper.entry.entries.cast
import com.typewritermc.engine.paper.entry.entries.getData
import dev.lone.itemsadder.api.FontImages.FontImageWrapper
import net.kyori.adventure.text.minimessage.MiniMessage

@Entry(
    "itemsadder_fontimage",
    "Get a ItemsAdder FontImage through the NameSpacedID.",
    Colors.GREEN,
    "material-symbols:description"
)
@VariableData(ItemsAdderFontImageIdData::class)
class ItemsAdderFontImageVariableData(
    override val id: String = "",
    override val name: String = "",

    val fontImageId: String = ""
) : VariableEntry {

    override fun <T : Any> get(context: VarContext<T>): T {

        val data = context.getData<ItemsAdderFontImageIdData>() ?: throw IllegalStateException("Could not find data for ${context.klass}, data: ${context.data}")

        val itemsAdderNamespaceID: String = data.fontImageIdOverride.ifEmpty {
            fontImageId
        }

        val fontImage = FontImageWrapper
            .instance(itemsAdderNamespaceID)
            ?: error("ItemsAdder item '${itemsAdderNamespaceID}' does not exist")

        return context.cast(MiniMessage.miniMessage().serialize(MiniMessage.miniMessage().deserialize(fontImage.string).color(fontImage.color)))
    }

}

class ItemsAdderFontImageIdData(
    val fontImageIdOverride: String = ""
)