package de.jplag.swift;

import java.io.File;
import java.util.List;

import de.jplag.Token;
import org.kohsuke.MetaInfServices;

/**
 * This represents the Swift language as a language supported by JPlag.
 */
@MetaInfServices(de.jplag.Language.class)
public class Language implements de.jplag.Language {

    private static final String NAME = "Swift Parser";
    private static final String IDENTIFIER = "swift";
    private static final int DEFAULT_MIN_TOKEN_MATCH = 8;
    private static final String[] FILE_EXTENSIONS = {".swift"};
    private final SwiftParserAdapter parserAdapter;

    public Language() {
        this.parserAdapter = new SwiftParserAdapter();
    }

    @Override
    public String[] suffixes() {
        return FILE_EXTENSIONS;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getIdentifier() {
        return IDENTIFIER;
    }

    @Override
    public int minimumTokenMatch() {
        return DEFAULT_MIN_TOKEN_MATCH;
    }

    @Override
    public List<Token> parse(File directory, String[] files) {
        return parserAdapter.parse(directory, files);
    }

    @Override
    public boolean hasErrors() {
        return parserAdapter.hasErrors();
    }
}
