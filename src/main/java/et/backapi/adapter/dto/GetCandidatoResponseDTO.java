package et.backapi.adapter.dto;

import et.backapi.domain.candidate.Candidate;
import et.backapi.domain.curriculum.Curriculum;
import et.backapi.domain.user.User;

import java.util.Optional;

public record GetCandidatoResponseDTO(
        User user,
        Candidate candidate ,

        Optional<Curriculum> curriculum

) {
}
