package de.jplag.golang;

import static de.jplag.golang.GoTokenAttribute.ARGUMENT;
import static de.jplag.golang.GoTokenAttribute.ASSIGNMENT;
import static de.jplag.golang.GoTokenAttribute.FUNCTION_BODY_BEGIN;
import static de.jplag.golang.GoTokenAttribute.FUNCTION_BODY_END;
import static de.jplag.golang.GoTokenAttribute.FUNCTION_DECLARATION;
import static de.jplag.golang.GoTokenAttribute.IMPORT_CLAUSE;
import static de.jplag.golang.GoTokenAttribute.IMPORT_CLAUSE_BEGIN;
import static de.jplag.golang.GoTokenAttribute.IMPORT_CLAUSE_END;
import static de.jplag.golang.GoTokenAttribute.IMPORT_DECLARATION;
import static de.jplag.golang.GoTokenAttribute.INVOCATION;
import static de.jplag.golang.GoTokenAttribute.MEMBER_DECLARATION;
import static de.jplag.golang.GoTokenAttribute.PACKAGE;
import static de.jplag.golang.GoTokenAttribute.STRUCT_BODY_BEGIN;
import static de.jplag.golang.GoTokenAttribute.STRUCT_BODY_END;
import static de.jplag.golang.GoTokenAttribute.STRUCT_DECLARATION;
import static de.jplag.golang.GoTokenAttribute.VARIABLE_DECLARATION;

import de.jplag.testutils.LanguageModuleTest;
import de.jplag.testutils.datacollector.TestDataCollector;
import de.jplag.testutils.datacollector.TestSourceIgnoredLinesCollector;

class GoLanguageTest extends LanguageModuleTest {
    private static final String COMPLETE_TEST_FILE = "Complete.go";
    // example files taken from antlr repo
    private static final String CONSTANTS_TEST_FILE = "Constants.go";
    private static final String ARRAY_ELLIPSIS_DECLS_FILE = "ArrayEllipsisDecls.go";

    public GoLanguageTest() {
        super(new GoLanguage(), GoTokenAttribute.class);
    }

    @Override
    protected void collectTestData(TestDataCollector collector) {
        collector.testFile(COMPLETE_TEST_FILE).testCoverages();

        // Some basic tests, so we have at least some idea if the listener was changed
        collector.testFile(CONSTANTS_TEST_FILE).testTokenSequence(PACKAGE, VARIABLE_DECLARATION, VARIABLE_DECLARATION);
        collector.testFile(ARRAY_ELLIPSIS_DECLS_FILE).testSourceCoverage().testTokenSequence(PACKAGE, IMPORT_DECLARATION, IMPORT_CLAUSE_BEGIN,
                IMPORT_CLAUSE, IMPORT_CLAUSE_END, STRUCT_DECLARATION, STRUCT_BODY_BEGIN, MEMBER_DECLARATION, STRUCT_BODY_END, FUNCTION_DECLARATION,
                FUNCTION_BODY_BEGIN, VARIABLE_DECLARATION, ASSIGNMENT, INVOCATION, ARGUMENT, ARGUMENT, FUNCTION_BODY_END);
    }

    @Override
    protected void configureIgnoredLines(TestSourceIgnoredLinesCollector collector) {
    }
}
