package de.jplag.rlang;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.ParserRuleContext;

import de.jplag.Language;
import de.jplag.LanguageLoader;
import de.jplag.antlr.AbstractAntlrListener;
import de.jplag.antlr.AbstractAntlrParserAdapter;
import de.jplag.rlang.grammar.RLexer;
import de.jplag.rlang.grammar.RParser;

/**
 * This class sets up the lexer and parser generated by ANTLR4, feeds the submissions through them and passes the
 * selected tokens on to the main program.
 */
public class RParserAdapter extends AbstractAntlrParserAdapter<RParser> {
    @Override
    protected Lexer createLexer(CharStream input) {
        return new RLexer(input);
    }

    @Override
    protected RParser createParser(CommonTokenStream tokenStream) {
        return new RParser(tokenStream);
    }

    @Override
    protected ParserRuleContext getEntryContext(RParser parser) {
        return parser.prog();
    }

    @Override
    protected AbstractAntlrListener getListener() {
        return new RListener();
    }

    @Override
    protected Language getLanguage() {
        return LanguageLoader.getLanguage(RLanguage.class).get();
    }
}
