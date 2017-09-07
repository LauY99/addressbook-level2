package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

    public Block block;
    public Street street;
    public Unit unit;
    public PCode postalCode;

    public static final String EXAMPLE = "123, some street";
    public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Person addresses can be in any format";
    public static final String ADDRESS_VALIDATION_REGEX = ".+";

    //public final String value;
    private boolean isPrivate;

    /**
     * Validates given address.
     *
     * @throws IllegalValueException if given address string is invalid.
     */
    public Address(String address, boolean isPrivate) throws IllegalValueException {
        String trimmedAddress = address.trim();
        this.isPrivate = isPrivate;
        if (!isValidAddress(trimmedAddress)) {
            throw new IllegalValueException(MESSAGE_ADDRESS_CONSTRAINTS);
        }

        // split comma-separated address
        String[] addresses = trimmedAddress.split(",");

        this.block = new Block(addresses[0]);
        this.street = new Street(addresses[1]);
        this.unit = new Unit(addresses[2]);
        this.postalCode = new PCode(addresses[3]);

        //this.value = trimmedAddress;
    }

    /**
     * Returns true if a given string is a valid person address.
     */
    public static boolean isValidAddress(String test) {
        return test.matches(ADDRESS_VALIDATION_REGEX);
    }

    public String getCompleteAddress() {
        return block.value + ", " + street.value + ", " + unit.value + ", " + postalCode.value;
    }

    @Override
    public String toString() {
        return getCompleteAddress();
    }

    @Override
    public boolean equals(Object other) {
        String ownAddress = this.getCompleteAddress();
        String otherAddress = ((Address) other).getCompleteAddress();

        return other == this // short circuit if same object
                || (other instanceof Address // instanceof handles nulls
                && ownAddress.equals(otherAddress)); // state check
    }

    @Override
    public int hashCode() {
        String completeAddress = getCompleteAddress();
        return completeAddress.hashCode();
    }

    public boolean isPrivate() {
        return isPrivate;
    }
}

