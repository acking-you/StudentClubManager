package util;

import javax.swing.*;

public class MessageUtil {
    public static void Warning(String msg) {
        JOptionPane.showMessageDialog(null, msg, "警告",
                JOptionPane.WARNING_MESSAGE);
    }

    public static void Info(String msg) {
        JOptionPane.showMessageDialog(null, msg, "消息",
                JOptionPane.PLAIN_MESSAGE);
    }

    public static void QuestionMessage(String msg) {
        JOptionPane.showMessageDialog(null, msg, "注意",
                JOptionPane.QUESTION_MESSAGE);
    }
}
