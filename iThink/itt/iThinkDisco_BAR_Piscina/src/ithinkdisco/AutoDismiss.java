/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ithinkdisco;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 *
 * @author Fernando
 */
public class AutoDismiss implements ActionListener{
  
    
    private final JDialog dialog;

    private AutoDismiss(JDialog dialog)
    {
        this.dialog = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {
        dialog.dispose();
    }

    public static void showOptionDialog(Component parentComponent,
                                         String message, String title,
                                         int delayInMilliseconds)
    {
        
        final JOptionPane optionPane = new JOptionPane(message,JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
        
        final JDialog dialog = optionPane.createDialog(parentComponent, title);
        dialog.setTitle(title);
        Timer timer = new Timer(delayInMilliseconds, new AutoDismiss(dialog));
        timer.setRepeats(false);
        timer.start();
        if (dialog.isDisplayable())
        {
            dialog.setVisible(true);
        }
    }
   
}
