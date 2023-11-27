package et.backapi.domain.academicEducation;

import com.fasterxml.jackson.annotation.JsonBackReference;
import et.backapi.domain.curriculum.Curriculum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "et_curriculum_academic_education")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AcademicEducation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String instituicao;

    private String course;

    private Date begin_data;
    private Date completion_date;

    @ManyToOne
    @JoinColumn(name = "curriculumId", nullable = false)
    @JsonBackReference
    private Curriculum cv;


}
