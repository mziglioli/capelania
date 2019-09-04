package com.capelania.model;

import java.time.LocalDateTime;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, of = { "title", "date" })
@ToString(of = { "title", "description", "header", "text", "footer", "date", "expire" })
@Entity
@Table(name = "feed", uniqueConstraints = { @UniqueConstraint(columnNames = { "title", "date" }) })
public class Feed extends EntityJpa {

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
    private String header;

    @Column(nullable = false)
    @NotEmpty
    private String text;

    @Column(nullable = false)
    @NotEmpty
    private String footer;

    @Column(nullable = false)
    private String date;

    @Column(nullable = false)
    private String expire;

}