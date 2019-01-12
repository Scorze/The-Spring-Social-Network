package org.springcourse.project.springsocialnetwork.model;

public enum UserRole {
    USER("User"), ADMIN("Admin"), UNKNOWN("Unknown");

    private String label;

    private UserRole(final String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }

    public static <T extends Enum<T>> T valueOfIgnoreCase(final Class<T> enumeration,
            final String name) {

        for (final T enumValue : enumeration.getEnumConstants()) {
            if (enumValue.toString().equalsIgnoreCase(name)) {
                return enumValue;
            }
        }

        throw new IllegalArgumentException(String.format(
                "There is no value with name '%s' in Enum %s", name, enumeration.getName()));
    }
}
