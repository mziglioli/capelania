package com.capelania.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;

import com.capelania.enums.DayValue;
import com.capelania.utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

import static com.capelania.utils.DateUtils.parseDisplay;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, of = { "title", "date" })
@ToString(of = { "title", "description", "text", "img", "date" })
@Entity
@Table(name = "event", uniqueConstraints = { @UniqueConstraint(columnNames = { "title", "date" }) })
public class Event extends EntityJpa {

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

    @Column(nullable = false)
    @NotEmpty
    private String text;

    @Column
    private String img;

    @Column(nullable = false)
    private String date;

    public String getDateDisplay() {
        return parseDisplay(date);
    }
}