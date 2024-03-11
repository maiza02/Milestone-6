package Store;
//Maiza Falcon Rojas
//CST-239
//02/29/2024
//This is my own code

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a shopping cart that can hold a collection of {@link CartItem}s.
 */
class ShoppingCart {
    private List<CartItem> items;

    /**
     * Constructs an empty shopping cart.
     */
    public ShoppingCart() {
        this.items = Collections.emptyList();
    }

    /**
     * Checks if the shopping cart is empty.
     *
     * @return true if the shopping cart is empty, false otherwise.
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }


    /**
     * Retrieves the list of items in the shopping cart.
     *
     * @return the list of {@link CartItem}s in the shopping cart.
     */
    public List<CartItem> getItems() {
        return items;
    }

    /**
     * Adds a specified quantity of a {@link SalableProduct} to the shopping cart.
     * If the product is already in the cart, the quantity is adjusted accordingly.
     *
     * @param product  the product to be added to the cart.
     * @param quantity the quantity of the product to be added.
     */
    public void addItem(SalableProduct product, int quantity) {
        CartItem existingItem = findItem(product);
        List<CartItem> mutableItems = new ArrayList<>(items);

        if (existingItem != null) {
            existingItem.adjustQuantity(quantity);
        } else {
            CartItem newItem = new CartItem(product, quantity);
            mutableItems.add(newItem);
        }

        items = List.copyOf(mutableItems);
    }
 
    
    
    /**
     * Removes a specified quantity of a {@link SalableProduct} from the shopping cart.
     * If the quantity becomes zero or less, the item is completely removed from the cart.
     *
     * @param product  the product to be removed from the cart.
     * @param quantity the quantity of the product to be removed.
     */
    public void removeItem(SalableProduct product, int quantity) {
        CartItem existingItem = findItem(product);
        if (existingItem != null) {
            existingItem.adjustQuantity(-quantity);
            if (existingItem.getQuantity() <= 0) {
                items.remove(existingItem);
            }
        }
    }

    
    /**
     * Finds and returns a {@link CartItem} associated with the specified {@link SalableProduct}.
     *
     * @param product the product to search for in the cart.
     * @return the {@link CartItem} associated with the specified product, or null if not found.
     */
    private CartItem findItem(SalableProduct product) {
        for (CartItem item : items) {
            if (item.getProduct().equals(product)) {
                return item;
            }
        }
        return null;
    }
}

/**
 * Represents an item in the shopping cart, consisting of a {@link SalableProduct} and a quantity.
 */
class CartItem {
    private SalableProduct product;
    private int quantity;

    
    /**
     * Constructs a cart item with the specified product and quantity.
     *
     * @param product  the product associated with the cart item.
     * @param quantity the quantity of the product in the cart item.
     */
    public CartItem(SalableProduct product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    
    /**
     * Retrieves the {@link SalableProduct} associated with the cart item.
     *
     * @return the product associated with the cart item.
     */
    public SalableProduct getProduct() {
        return product;
    }

    
    /**
     * Retrieves the quantity of the product in the cart item.
     *
     * @return the quantity of the product in the cart item.
     */
    public int getQuantity() {
        return quantity;
    }

    

    /**
     * Adjusts the quantity of the product in the cart item by the specified delta.
     *
     * @param quantityDelta the change in quantity to be applied.
     */
    public void adjustQuantity(int quantityDelta) {
        this.quantity += quantityDelta;
    }
}

