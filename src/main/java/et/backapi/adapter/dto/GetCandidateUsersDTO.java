package et.backapi.adapter.dto;

import et.backapi.domain.candidatestack.CandidateStack;
import et.backapi.domain.curriculum.Curriculum;
import et.backapi.domain.user.User;

import java.util.List;

public record GetCandidateUsersDTO(
        User user,
        List<CandidateStack> stacks,

        CurriculumCreateRequestDto cv
) {
}
