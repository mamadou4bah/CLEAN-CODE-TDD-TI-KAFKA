package com.mamadou.bah.si.pmu.infrastructure.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
@Table(
        name = "partant",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"course_id", "number"})
        }
)
public class PartantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Positive
    private Long number;

    @ManyToOne(optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    private CourseEntity course;
}
