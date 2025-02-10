package de.jplag.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import de.jplag.cli.picocli.CliInputHandler;
import de.jplag.cli.test.CliArgument;
import de.jplag.cli.test.CliTest;
import de.jplag.exceptions.ExitException;

class ModeTest extends CliTest {
    @Test
    void testViewWithPositionalFile() throws IOException, ExitException {
        CliInputHandler inputHandler = this
                .runCli(args -> args.with(CliArgument.MODE, "view").with(CliArgument.SUBMISSION_DIRECTORIES, new String[] {"result.zip"}))
                .inputHandler();
        assertEquals(new File("result.zip"), inputHandler.getFileForViewMode());
    }

    @Test
    void testViewWithOldFile() throws IOException, ExitException {
        CliInputHandler inputHandler = this
                .runCli(args -> args.with(CliArgument.MODE, "view").with(CliArgument.OLD_SUBMISSION_DIRECTORIES, new String[] {"result.zip"}))
                .inputHandler();
        assertEquals(new File("result.zip"), inputHandler.getFileForViewMode());
    }

    @Test
    void testViewWithNewFile() throws IOException, ExitException {
        CliInputHandler inputHandler = this
                .runCli(args -> args.with(CliArgument.MODE, "view").with(CliArgument.NEW_SUBMISSION_DIRECTORIES, new String[] {"result.zip"}))
                .inputHandler();
        assertEquals(new File("result.zip"), inputHandler.getFileForViewMode());
    }

    @Test
    void testViewWithResultFile() throws IOException, ExitException {
        CliInputHandler inputHandler = this.runCli(args -> args.with(CliArgument.MODE, "view").with(CliArgument.RESULT_FILE, "result.zip"))
                .inputHandler();
        assertEquals(new File("result.zip"), inputHandler.getFileForViewMode());
    }

    @Test
    void testViewWithMultipleFiles() {
        assertThrowsExactly(CliException.class, () -> {
            this.runCli(args -> args.with(CliArgument.MODE, "view").with(CliArgument.RESULT_FILE, "result.zip")
                    .with(CliArgument.NEW_SUBMISSION_DIRECTORIES, new String[] {"test.zip"})).inputHandler();
        });
    }

    @Override
    public void addDefaultParameters() {
        // prevents the submission directory from being added to the parameters automatically
    }
}
