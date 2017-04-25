/* LanguageTool, a natural language style checker 
 * Copyright (C) 2005-2015 Daniel Naber (http://www.danielnaber.de)
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301
 * USA
 */
package org.languagetool.rules.pt;

import org.languagetool.rules.AbstractSimpleReplaceRule;
import org.languagetool.rules.Example;
import org.languagetool.rules.Categories;
import org.languagetool.rules.ITSIssueType;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * A rule that matches words or phrases which should not be used and suggests
 * correct ones instead.
 *
 * @author Marco A.G.Pinto
 */
public class PortugueseAgreementReplaceRule extends AbstractSimpleReplaceRule {

  public static final String PORTUGUESE_AGREEMENT_REPLACE_RULE = "PT_AGREEMENT_REPLACE";

  private static final Map<String, List<String>> wrongWords = load("/pt/AOreplace.txt");
  private static final Locale PT_LOCALE = new Locale("pt");

  @Override
  protected Map<String, List<String>> getWrongWords() {
    return wrongWords;
  }

  public PortugueseAgreementReplaceRule(ResourceBundle messages) throws IOException {
    super(messages);
    super.setCategory(Categories.TYPOS.getCategory(messages));
    setLocQualityIssueType(ITSIssueType.Misspelling);
    addExamplePair(Example.wrong("<marker>abstracto</marker>"),
                   Example.fixed("<marker>abstrato</marker>"));
  }

  @Override
  public final String getId() {
    return PORTUGUESE_AGREEMENT_REPLACE_RULE;
  }

  @Override
  public String getDescription() {
    return "Palavras alteradas pelo Acordo Ortográfico de 90";
  }

  @Override
  public String getShort() {
    return "Forma do Acordo Ortográfico de 45";
  }
  
  @Override
  public String getMessage(String tokenStr, List<String> replacements) {
    return tokenStr + " é uma forma do antigo Acordo Ortográfico. No novo acordo ortográfico, a palavra escreve-se: "
        + String.join(", ", replacements) + ".";
  }

  @Override
  public boolean isCaseSensitive() {
    return false;
  }

  @Override
  public Locale getLocale() {
    return PT_LOCALE;
  }

}