
package com.check;

import java.util.Arrays;

import org.sonar.plugins.java.api.CheckRegistrar;
import org.sonar.plugins.java.api.JavaCheck;


public class CustomJavaFileCheckRegistrar implements CheckRegistrar {

  @Override
  public void register(RegistrarContext registrarContext) {
    registrarContext.registerClassesForRepository(CustomRulesDefinition.REPOSITORY_KEY, Arrays.asList(checkClasses()), Arrays.asList(testCheckClasses()));
  }


  public static Class<? extends JavaCheck>[] checkClasses() {
    return new Class[] { 
    		SymIsDebugEnableCheckRule.class,
    };
  }


  public static Class<? extends JavaCheck>[] testCheckClasses() {
    return new Class[] {};
  }
}