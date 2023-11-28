package et.backapi.adapter.dto;

import et.backapi.adapter.enums.UserSeniority;


public record CurriculumCreateRequestDto(
        String ccrUserRole,
        UserSeniority ccrUserSeniority,
        String linkPortifolio,
        String linkGitHub,
        String linkInstagram,
        String objetivo,
        String linkProfile
) {

}
