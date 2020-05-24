package com.volmit.bile.commands;

import com.volmit.bile.BileTools;
import com.volmit.bile.commands.subcommands.*;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BileCommand implements CommandExecutor {

    private String tag = ChatColor.GREEN + "[" + ChatColor.DARK_GRAY + "Bile" + ChatColor.GREEN + "]: " + ChatColor.GRAY;

    private BileTools bileTools;

    public BileCommand(BileTools bileTools) {
        this.bileTools = bileTools;
        SubCommand[] subCommandArray = new SubCommand[] {new InstallCommand (bileTools), new LibraryCommand (bileTools),
        new LoadCommand (), new ReloadCommand (), new UNLoadCommand (), new UNInstallCommand ()};
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(command.getName().equals("biletools"))
        {
            if(!sender.hasPermission("bile.use"))
            {
                sender.sendMessage(tag + "You need bile.use or OP.");
                return true;
            }

            if(args.length == 0)
            {
                sender.sendMessage (new String[]{tag + "/// - Ingame dev mode toggle",
                        tag + "/bile load <plugin>",
                        tag + "/bile unload <plugin>",
                        tag + "/bile reload <plugin>",
                        tag + "/bile install <plugin> [version]",
                        tag + "/bile uninstall <plugin>",
                        tag + "/bile library [plugin]"});
            }

            else
            {
                for (String string : SubCommand.subCommandMap.keySet ())
                {
                    if(!string.equalsIgnoreCase (args[0]))
                    {
                        continue;
                    }
                    SubCommand.subCommandMap.get (string).execute (sender, args);
                    return false;
                }
                sender.sendMessage (tag + "Command not found");
            }

            return true;
        }

        return false;
    }
}
