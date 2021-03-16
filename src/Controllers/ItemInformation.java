package Controllers;

public class ItemInformation {
    private String name, description, purchasingPrice, sellingPrice;

    public ItemInformation(String name, String description, int purchasingPrice, int sellingPrice) {
        this.name = name;
        this.description = description;
        this.purchasingPrice = Integer.toString(purchasingPrice);
        this.sellingPrice = Integer.toString(sellingPrice);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPurchasingPrice() {
        return purchasingPrice;
    }

    public String getSellingPrice() {
        return sellingPrice;
    }
}
