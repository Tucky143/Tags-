package net.mcreator.tags;

import net.mcreator.plugin.JavaPlugin;
import net.mcreator.plugin.Plugin;
import net.mcreator.plugin.events.workspace.MCreatorLoadedEvent;
import net.mcreator.ui.MCreator;
import net.mcreator.ui.action.BasicAction;
import net.mcreator.ui.init.L10N;
import net.mcreator.ui.init.UIRES;

import javax.swing.*;

import static com.github.weisj.jsvg.attributes.text.Side.Left;
import static javax.swing.SwingConstants.LEFT;

public class Launcher extends JavaPlugin {

    public Launcher(Plugin plugin) {
        super(plugin);

        // Add a listener for when MCreator is fully loaded
        addListener(MCreatorLoadedEvent.class, event -> SwingUtilities.invokeLater(() -> {
            MCreator mcreator = event.getMCreator();
            if (mcreator != null) { // Ensure MCreator is not null
                // Add an action for opening the custom Tags+ dialog (AddOtherTagsDialog)
                BasicAction openTagsDialogAction = new BasicAction(
                        mcreator.getActionRegistry(),
                        L10N.t("menu.tags.open_dialog"), // Localized menu item name
                        e -> net.mcreator.tags.ui.dialogs.AddOtherTagsDialog.open(mcreator) // Open the AddOtherTagsDialog
                );
                openTagsDialogAction.setIcon(UIRES.get("16px.injecttags"));

                // Add the "Tags+" menu to the main menu bar
                JMenuBar menuBar = mcreator.getMainMenuBar();
                if (menuBar != null) {
                    mcreator.getToolBar().addToLeftToolbar(openTagsDialogAction);
                    openTagsDialogAction.setTooltip("Add Additional Tags...");
                    menuBar.revalidate();
                    menuBar.repaint();
                }
            }
        }));
    }
}
