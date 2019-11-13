package com.capelania.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, of = { "id" })
@ToString(callSuper = false, of = { "id", "name", "email" })
@Entity
@Table(name = "user", uniqueConstraints = { @UniqueConstraint(columnNames = { "email" }) })
public class User extends EntityJpa implements UserDetails {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	@NotNull(message = "error.empty.name")
	@NotEmpty(message = "error.empty.name")
	private String name;

	@Column
	@NotNull(message = "error.empty.email")
	@NotEmpty(message = "error.empty.email")
	private String email;

	@JsonIgnore
	@Column
	private String password;

    @JsonIgnore
	@ManyToMany(cascade= CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinTable(
		name="user_role",
		joinColumns={@JoinColumn(name="user_id", referencedColumnName="id")},
		inverseJoinColumns={@JoinColumn(name="role_id", referencedColumnName="id")})
	private List<Role> roles;

	private transient String allRoles;
    private transient String pass;

    @Override
	@JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
            .map(r -> new SimpleGrantedAuthority(r.getName()))
            .collect(Collectors.toList());
    }

    @Override
	@JsonIgnore
    public String getUsername() {
        return email;
    }

    @Override
	@JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
	@JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
	@JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
	@JsonIgnore
    public boolean isEnabled() {
        return isActive();
    }

    public String getAllRoles() {
        return roles.stream()
            .map(Role::getName)
            .collect(Collectors.joining(","));
    }

    public String getPass() {
        return "*******";
    }
}