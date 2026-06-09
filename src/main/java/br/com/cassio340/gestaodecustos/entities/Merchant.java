package br.com.cassio340.gestaodecustos.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name ="tb_merchant")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Merchant implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToOne(mappedBy = "merchant")
    private Expense expense;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Merchant merchant = (Merchant) o;
        return Objects.equals(id, merchant.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
