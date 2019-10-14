package com.capelania.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, of = { "day" })
@ToString(of = { "day", "startAm", "endAm", "startPm", "endPm" })
@Entity
@Table(name = "opening", uniqueConstraints = { @UniqueConstraint(columnNames = { "day" }) })
public class Opening extends EntityJpa {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 10)
    @NotEmpty
    private String day;

    @Column(nullable = false, length = 5, name = "start_am")
    @NotEmpty
    private String startAm;

    @Column(nullable = false, length = 5, name = "end_am")
    @NotEmpty
    private String endAm;

    @Column(nullable = false, length = 5, name = "start_pm")
    @NotEmpty
    private String startPm;

    @Column(nullable = false, length = 5, name = "end_pm")
    @NotEmpty
    private String endPm;

    @JsonIgnore
    public String combineTime() {
        return startAm + endAm + startPm + endPm;
    }
}