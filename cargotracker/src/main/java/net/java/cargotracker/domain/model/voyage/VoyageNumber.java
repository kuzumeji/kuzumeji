package net.java.cargotracker.domain.model.voyage;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.Validate;
/**
 * 航海番号
 * @author nilcy
 */
@Embeddable
public class VoyageNumber implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "voyage_number")
    @NotNull
    private String number;
    public VoyageNumber() {
        // Nothing to initialize.
    }
    public VoyageNumber(final String number) {
        Validate.notNull(number);
        this.number = number;
    }
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof VoyageNumber)) {
            return false;
        }
        final VoyageNumber other = (VoyageNumber) o;
        return sameValueAs(other);
    }
    @Override
    public int hashCode() {
        return number.hashCode();
    }
    boolean sameValueAs(final VoyageNumber other) {
        return (other != null) && number.equals(other.number);
    }
    @Override
    public String toString() {
        return number;
    }
    public String getIdString() {
        return number;
    }
}
