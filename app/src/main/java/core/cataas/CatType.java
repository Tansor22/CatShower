package core.cataas;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Getter
@Accessors(fluent = true, prefix = "_")
@AllArgsConstructor
public enum CatType {
    SMALL("sm"),
    MEDIUM("md"),
    SQUARE("sq"),
    ORIGINAL("or"),
    ;
    String _value;
}
