package com.mamadou.bah.si.pmu.infrastructure.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
@Table(
        name = "course",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"date_course", "number"})
        }
)
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "date_course", nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    @Positive
    private Long number;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("number ASC")
    private List<PartantEntity> partants = new ArrayList<>();
}
