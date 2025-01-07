package de.jplag.tokentypes;

import de.jplag.TokenAttribute;

public enum ExceptionHandlingTokenTypes implements TokenAttribute {
    THROW("THROW"),
    TRY("TRY"),
    TRY_END("TRY_END"),
    CATCH("CATCH"),
    FINALLY("FINALLY");

    private String description;

    ExceptionHandlingTokenTypes(String description) {
        this.description = description;
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
