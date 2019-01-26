package org.seasar.doma.extension.gen.internal.message;

import java.text.MessageFormat;
import java.util.ResourceBundle;
import org.seasar.doma.extension.gen.MessageResource;

/** @author taedium */
final class MessageFormatter {

  public static String getMessage(MessageResource messageResource, Object... args) {
    ResourceBundle bundle = ResourceBundle.getBundle(GenMessageResourceBundle.class.getName());
    String pattern = bundle.getString(messageResource.getCode());
    return MessageFormat.format("[" + messageResource.getCode() + "] " + pattern, args);
  }
}
