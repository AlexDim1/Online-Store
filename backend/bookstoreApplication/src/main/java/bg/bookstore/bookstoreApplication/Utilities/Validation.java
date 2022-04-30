package bg.bookstore.bookstoreApplication.Utilities;

import bg.bookstore.bookstoreApplication.Payload.Request.PlaceOrderRequest;

public class Validation {
    public static boolean validateBuyerInfo(PlaceOrderRequest request) {
        return !request.getBuyerAddress().isEmpty() && !request.getBuyerName().isEmpty();
    }
}
