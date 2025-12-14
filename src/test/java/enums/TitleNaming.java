package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TitleNaming {
    PRODUCTS("Products"),
    CARTS("Your cart"),
    CHECKOUT("Checkout: Your Infotmation");

    private final String displayName;
}
