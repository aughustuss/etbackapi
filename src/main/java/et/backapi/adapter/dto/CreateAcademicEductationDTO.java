package et.backapi.adapter.dto;

import java.util.Date;

public record CreateAcademicEductationDTO(
        Long id,
        String name,
        String instituicao,

        String course,

        Date begin_data,
        Date completion_date

){}
