
package com.check;

import java.util.Arrays;
import java.util.List;

import org.sonar.api.SonarPlugin;

public class CustomJavaRulesEntry extends SonarPlugin {

  @Override
  public List getExtensions() {
    return Arrays.asList(

      CustomRulesDefinition.class,


      CustomJavaFileCheckRegistrar.class);
  }
}