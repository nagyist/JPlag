package de.jplag.python3;

import de.jplag.Token;

public class Python3Token extends Token implements Python3TokenConstants {

    private static final long serialVersionUID = 1485877548175917943L;
    
    private int line, column, length;

    public Python3Token(int type, String file, int line, int column, int length) {
        super(type, file, line, column, length);
    }

    @Override
    public int getLine() {
        return line;
    }

    @Override
    public int getColumn() {
        return column;
    }

    @Override
    public int getLength() {
        return length;
    }

    @Override
    public void setLine(int line) {
        this.line = line;
    }

    @Override
    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public void setLength(int length) {
        this.length = length;
    }

    public static String type2string(int type) {
        switch (type) {
            case Python3TokenConstants.FILE_END:
                return "********";
            case Python3TokenConstants.SEPARATOR_TOKEN:
                return "METHOD_SEPARATOR";

            case IMPORT:
                return "IMPORT  ";
            case CLASS_BEGIN:
                return "CLASS{  ";
            case CLASS_END:
                return "}CLASS  ";
            case METHOD_BEGIN:
                return "METHOD{ ";
            case METHOD_END:
                return "}METHOD ";
            case ASSIGN:
                return "ASSIGN  ";
            case WHILE_BEGIN:
                return "WHILE{  ";
            case WHILE_END:
                return "}WHILE  ";
            case FOR_BEGIN:
                return "FOR{    ";
            case FOR_END:
                return "}FOR    ";
            case TRY_BEGIN:
                return "TRY{    ";
            case EXCEPT_BEGIN:
                return "CATCH{  ";
            case EXCEPT_END:
                return "}CATCH  ";
            case FINALLY:
                return "FINALLY ";
            case IF_BEGIN:
                return "IF{     ";
            case IF_END:
                return "}IF     ";
            case APPLY:
                return "APPLY   ";
            case BREAK:
                return "BREAK   ";
            case CONTINUE:
                return "CONTINUE";
            case RETURN:
                return "RETURN  ";
            case RAISE:
                return "RAISE   ";
            case DEC_BEGIN:
                return "DECOR{  ";
            case DEC_END:
                return "}DECOR  ";
            case LAMBDA:
                return "LAMBDA  ";
            case ARRAY:
                return "ARRAY   ";
            case ASSERT:
                return "ASSERT  ";
            case YIELD:
                return "YIELD   ";
            case DEL:
                return "DEL     ";
            case WITH_BEGIN:
                return "WITH{   ";
            case WITH_END:
                return "}WITH   ";
            default:
                System.err.println("*UNKNOWN: " + type);
                return "*UNKNOWN" + type;
        }
    }
}
