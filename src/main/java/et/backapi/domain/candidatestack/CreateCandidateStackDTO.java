package et.backapi.domain.candidatestack;

import java.util.List;

public record CreateCandidateStackDTO (List<String> stackName , Long candidate ){
}
