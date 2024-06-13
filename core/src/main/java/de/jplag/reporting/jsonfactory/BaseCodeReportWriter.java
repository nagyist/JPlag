package de.jplag.reporting.jsonfactory;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import de.jplag.JPlagComparison;
import de.jplag.JPlagResult;
import de.jplag.Match;
import de.jplag.Submission;
import de.jplag.Token;
import de.jplag.reporting.FilePathUtil;
import de.jplag.reporting.reportobject.model.BaseCodeMatch;
import de.jplag.reporting.reportobject.writer.JPlagResultWriter;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseCodeReportWriter {

    private final JPlagResultWriter resultWriter;
    private final Function<Submission, String> submissionToIdFunction;
    public static final String BASEPATH = "basecode";

    public BaseCodeReportWriter(Function<Submission, String> submissionToIdFunction, JPlagResultWriter resultWriter) {
        this.submissionToIdFunction = submissionToIdFunction;
        this.resultWriter = resultWriter;
    }

    public void writeBaseCodeReport(JPlagResult jPlagResult) {
        Set<Submission> submissions = new HashSet<>();

        int numberOfComparisons = jPlagResult.getOptions().maximumNumberOfComparisons();
        List<JPlagComparison> comparisons = jPlagResult.getComparisons(numberOfComparisons);
        for (JPlagComparison comparison : comparisons) {
            submissions.add(comparison.firstSubmission());
            submissions.add(comparison.secondSubmission());
        }
        submissions.forEach(this::writeBaseCodeFile);
    }

    private void writeBaseCodeFile(Submission submission) {
        List<BaseCodeMatch> matches = List.of();
        if (submission.getBaseCodeComparison() != null) {
            matches = submission.getBaseCodeComparison().matches().stream().map(match -> convertToBaseCodeMatch(submission, match)).toList();
        }
        resultWriter.addJsonEntry(matches, Path.of(BASEPATH, submissionToIdFunction.apply(submission).concat(".json")));
    }

    private BaseCodeMatch convertToBaseCodeMatch(Submission submission, Match match) {
        List<Token> tokensFirst = submission.getTokenList().subList(match.startOfFirst(), match.endOfFirst() + 1);

        Comparator<? super Token> lineComparator = Comparator.comparingInt(Token::getLine);
        Token start = tokensFirst.stream().min(lineComparator).orElseThrow();
        Token end = tokensFirst.stream().max(lineComparator).orElseThrow();

        return new BaseCodeMatch(FilePathUtil.getRelativeSubmissionPath(start.getFile(), submission, submissionToIdFunction).toString(), start.getLine(),
                start.getColumn(), match.startOfFirst(),
                end.getLine(), end.getColumn() + end.getLength() - 1, match.endOfFirst(), match.length());
    }
}