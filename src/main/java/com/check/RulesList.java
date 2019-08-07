package com.check;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.sonar.plugins.java.api.JavaCheck;
import com.google.common.collect.ImmutableList;

public final class RulesList {

  private RulesList() {
  }

  public static List<Class> getChecks() {
    return ImmutableList.<Class>builder().addAll(getJavaChecks()).addAll(getJavaTestChecks()).build();
  }

  public static List<Class<SymIsDebugEnableCheckRule>> getJavaChecks() {
    
    return Collections.unmodifiableList(Arrays.asList(
    		SymIsDebugEnableCheckRule.class));
  }
  public static List<Class<? extends JavaCheck>> getJavaTestChecks() {
    return ImmutableList.<Class<? extends JavaCheck>>builder()
      .build();
  }
}
