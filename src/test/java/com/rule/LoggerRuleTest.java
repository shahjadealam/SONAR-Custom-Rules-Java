package com.rule;

import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

import com.check.SymIsDebugEnableCheckRule;

public class LoggerRuleTest {

	@Test
	public void detected() {

		JavaCheckVerifier.verify("src/test/java/com/rule/LoggerRuleCheckClient.java", new SymIsDebugEnableCheckRule());
	}
}


/* OUTPUT
Scanning the variable : args
Scanning the variable : y
Rule Issue for variable : y
Scanning the variable : n
Rule Issue for variable : n
Scanning the variable : z
Rule Issue for variable : z
Scanning the variable : aaa
Scanning the variable : DAAAAAAAAAAAAAAAAAAAAA
Scanning the variable : fz
Rule Issue for variable : fz
Scanning the variable : faaa
Scanning the variable : FFFFFFFFFFFFFFFFFFFF
14:51:31.144 [Report about progress of Java AST analyzer] INFO  org.sonar.squidbridge.ProgressReport - 1/1 source files have been analyzed
[ERROR] Tests run: 1, Failures: 1, Errors: 0, Skipped: 0, Time elapsed: 2.366 s <<< FAILURE! - in com.symantec.ecom.test.VariableLengthTest
[ERROR] detected(com.symantec.ecom.test.VariableLengthTest)  Time elapsed: 2.265 s  <<< FAILURE!
java.lang.AssertionError: Unexpected at [7, 8, 9, 21]
        at com.symantec.ecom.test.VariableLengthTest.detected(VariableLengthTest.java:15)

[INFO]
[INFO] Results:
[INFO]
[ERROR] Failures:
[ERROR]   VariableLengthTest.detected:15 Unexpected at [7, 8, 9, 21]
[INFO]
[ERROR] Tests run: 1, Failures: 1, Errors: 0, Skipped: 0

*/