package com.teamwizardry.librarianlib.client.newbook

import com.teamwizardry.librarianlib.client.newbook.editor.GuiBookLayoutEditor
import net.minecraft.client.Minecraft
import net.minecraft.command.CommandBase
import net.minecraft.command.ICommandSender
import net.minecraft.server.MinecraftServer
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.BlockPos

/**
 * Created by TheCodeWarrior
 */
class BookCommand : CommandBase() {

    companion object {
        val actions = arrayOf("edit", "editlayout", "open")
    }


    override fun execute(server: MinecraftServer?, sender: ICommandSender?, args: Array<out String>?) {
        if(args == null)
            return
        if(args.size < 2)
            return

        val list = args[0].split(":");
        if(list.size < 2)
            return
        val rl = ResourceLocation(list[0], list[1])

        val action = args[1]

        if(action == "editlayout") {
            server!!.addScheduledTask { Minecraft.getMinecraft().displayGuiScreen(GuiBookLayoutEditor()) }
        }
    }

    override fun getCommandName(): String {
        return "llbook"
    }

    override fun getCommandUsage(sender: ICommandSender?): String {
        return "/llbook <bookResourceLocation> <action>"
    }

    override fun getTabCompletionOptions(server: MinecraftServer?, sender: ICommandSender?, args: Array<out String>?, pos: BlockPos?): MutableList<String>? {
        if(args != null && args.size == 1)
            return mutableListOf(*actions)
        return super.getTabCompletionOptions(server, sender, args, pos)
    }
}