package net.java.cargotracker.domain.model.location;
import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.apache.commons.lang3.Validate;
/**
 * 国際ロケーションコード
 * <p/>
 * United nations location code.
 * <p/>
 * http://www.unece.org/cefact/locode/
 * http://www.unece.org/cefact/locode/DocColumnDescription.htm#LOCODE
 */
@Embeddable
public class UnLocode implements Serializable {
    private static final long serialVersionUID = 1L;
    @NotNull
    // Country code is exactly two letters.
    // Location code is usually three letters, but may contain the numbers 2-9
    // as well.
    @Pattern(regexp = "[a-zA-Z]{2}[a-zA-Z2-9]{3}")
    private String unlocode;
    private static final java.util.regex.Pattern VALID_PATTERN = java.util.regex.Pattern
        .compile("[a-zA-Z]{2}[a-zA-Z2-9]{3}");
    public UnLocode() {
        // Nothing to initialize.
    }
    /**
     * @param countryAndLocation Location string.
     */
    public UnLocode(final String countryAndLocation) {
        Validate.notNull(countryAndLocation, "Country and location may not be null");
        Validate.isTrue(VALID_PATTERN.matcher(countryAndLocation).matches(), countryAndLocation
            + " is not a valid UN/LOCODE (does not match pattern)");
        unlocode = countryAndLocation.toUpperCase();
    }
    /**
     * @return country code and location code concatenated, always upper case.
     */
    public String getIdString() {
        return unlocode;
    }
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }
        final UnLocode other = (UnLocode) o;
        return sameValueAs(other);
    }
    @Override
    public int hashCode() {
        return unlocode.hashCode();
    }
    boolean sameValueAs(final UnLocode other) {
        return (other != null) && unlocode.equals(other.unlocode);
    }
    @Override
    public String toString() {
        return getIdString();
    }
}
