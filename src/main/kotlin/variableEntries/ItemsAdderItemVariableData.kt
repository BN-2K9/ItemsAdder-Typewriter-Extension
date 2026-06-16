package dev.bn2k9.variableEntries

import com.typewritermc.core.books.pages.Colors
import com.typewritermc.core.extension.annotations.Entry
import com.typewritermc.core.extension.annotations.VariableData
import com.typewritermc.engine.paper.entry.entries.VarContext
import com.typewritermc.engine.paper.entry.entries.VariableEntry
import com.typewritermc.engine.paper.entry.entries.cast
import com.typewritermc.engine.paper.entry.entries.getData
import com.typewritermc.engine.paper.utils.item.SerializedItem
import dev.lone.itemsadder.api.CustomStack

@Entry(
    "itemsadder_itemstack",
    "Get a itemStack from a itemsAdder NameSpacedID.",
    Colors.GREEN,
    "material-symbols:category-search"
)
@VariableData(ItemsAdderItemData::class)
class ItemsAdderItemVariableData(
    override val id: String = "",
    override val name: String = "",

    val itemId: String = ""
) : VariableEntry {

    override fun <T : Any> get(context: VarContext<T>): T {

        val data = context.getData<ItemsAdderItemData>() ?: throw IllegalStateException("Could not find data for ${context.klass}, data: ${context.data}")

        val itemsAdderNamespaceID: String = data.itemIdOverride.ifEmpty {
            itemId
        }

        val itemStack = CustomStack
            .getInstance(itemsAdderNamespaceID)
            ?.itemStack
            ?.clone()
            ?: error("ItemsAdder item '${itemsAdderNamespaceID}' does not exist")

        return context.cast(SerializedItem(itemStack))
    }

}

class ItemsAdderItemData(
    val itemIdOverride: String = ""
)