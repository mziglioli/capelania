package com.capelania.model;

import com.capelania.enums.DayValue;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;

import com.capelania.utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, of = { "day", "start" })
@ToString(of = { "title", "description", "day", "start", "duration" })
@Entity
@Table(name = "mass", uniqueConstraints = { @UniqueConstraint(columnNames = { "day", "start" }) })
public class Mass extends EntityJpa {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotEmpty
    private String title;

    @Column(nullable = false)
    @NotEmpty
    private String description;

    @Column(nullable = false, length = 20)
    @NotEmpty
    private String day;

    @Column(nullable = false)
    @NotEmpty
    private String start;

    @Column(nullable = false)
    private Integer duration;

    @Column
    private boolean weekly;

    @Column(nullable = false)
    @NotEmpty
    private String date;

    private transient int dayValue;

    public int getDayValue() {
        return DayValue.getValueFromToday(day);
    }

    public int getDayValue(LocalDate localDate) {
        return DayValue.getValueFromToday(day, localDate);
    }
}