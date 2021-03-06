package com.computer_squad.command.fun.echo;

import com.computer_squad.ComputerSquadBot;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.exceptions.InsufficientPermissionException;

import java.util.regex.Pattern;

import static net.dv8tion.jda.api.Permission.ADMINISTRATOR;

/**
 * Echo command will make the bot talk. Your command is deleted.<br>
 * Syntax: ++echo [string to repeat]
 */
public class Echo extends Command {

	public Echo() {
		this.name = "echo";
		this.help = "Echos a phrase to be said by the bot";
		this.userPermissions = new Permission[]{ADMINISTRATOR};
	}

	@Override
	protected void execute(CommandEvent event) {
		Message message = event.getMessage();

		try {
			message.delete().queue();
		} catch (InsufficientPermissionException e) {
			e.printStackTrace();
			event.reply("Insufficient privileges. Debugging info:\n" + e);
		}

		String commandFirstPart = Pattern.quote(ComputerSquadBot.getPrefix() + getName());
		String reply = event.getMessage().getContentRaw().replaceFirst("(?i)" + commandFirstPart, "");
		event.reply(reply);
	}
}
