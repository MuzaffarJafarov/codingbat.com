package uz.muzaffar.codingbat.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Progress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private boolean isPassed;

    @Column(nullable = false)
    private boolean isStar;

    @Column(nullable = false)
    private Integer chance;

    @ManyToOne (optional = false)
    private Task task;

    @ManyToOne
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Progress progress = (Progress) o;

        return Objects.equals(id, progress.id);
    }

    @Override
    public int hashCode() {
        return 731934069;
    }
}
