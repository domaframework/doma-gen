package org.seasar.doma.extension.gen;

import freemarker.template.TemplateDateModel;
import freemarker.template.TemplateModelException;
import java.util.Date;

/**
 * 必要とされるたびに{@link Date}を生成する{@link TemplateDateModel}の実装です。
 *
 * @author taedium
 */
public class OnDemandDateModel implements TemplateDateModel {

  @Override
  public Date getAsDate() throws TemplateModelException {
    return new Date();
  }

  @Override
  public int getDateType() {
    return UNKNOWN;
  }
}
