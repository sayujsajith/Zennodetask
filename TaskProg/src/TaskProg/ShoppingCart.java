package TaskProg;

import java.util.Scanner;

public class ShoppingCart {

	public static void main(String[] args) {
	
		// Catalog with product details
        String[] products = {"Product A", "Product B", "Product C"};
        double[] prices = {20.0, 40.0, 50.0};

        // Discount rules
        String[] discountRules = {"flat_10_discount", "bulk_5_discount", "bulk_10_discount", "tiered_50_discount"};
        double[] discountAmounts = {10.0, 5.0, 10.0, 50.0};

        // Input quantities and gift wrap preferences
        Scanner scanner = new Scanner(System.in);
        int[] quantities = new int[3];
        boolean[] giftWrap = new boolean[3];

        for (int i = 0; i < products.length; i++) {
            System.out.print("Enter the quantity of " + products[i] + ": ");
            quantities[i] = scanner.nextInt();

            System.out.print("Wrap " + products[i] + " as a gift? (true/false): ");
            giftWrap[i] = scanner.nextBoolean();
        }

        // Calculate subtotal
        double subtotal = 0.0;
        for (int i = 0; i < products.length; i++) {
            subtotal += quantities[i] * prices[i];
        }

        // Apply discounts
        String appliedDiscount = "";
        double discount = 0.0;

        // Rule 1: flat_10_discount
        if (subtotal > 200.0 && discountAmounts[0] > discount) {
            discount = discountAmounts[0];
            appliedDiscount = discountRules[0];
        }

        // Rule 2: bulk_5_discount
        for (int i = 0; i < products.length; i++) {
            if (quantities[i] > 10 && discountAmounts[1] > discount) {
                discount = discountAmounts[1];
                appliedDiscount = discountRules[1];
                break;
            }
        }

        // Rule 3: bulk_10_discount
        int totalQuantity = 0;
        for (int i = 0; i < products.length; i++) {
            totalQuantity += quantities[i];
        }

        if (totalQuantity > 20 && discountAmounts[2] > discount) {
            discount = discountAmounts[2];
            appliedDiscount = discountRules[2];
        }

        // Rule 4: tiered_50_discount
        if (totalQuantity > 30) {
            for (int i = 0; i < products.length; i++) {
                if (quantities[i] > 15 && discountAmounts[3] > discount) {
                    discount = discountAmounts[3];
                    appliedDiscount = discountRules[3];
                    break;
                }
            }
        }

        // Calculate shipping fee
        int totalPackages = (int) Math.ceil((double) totalQuantity / 10);
        double shippingFee = totalPackages * 5.0;

        // Calculate gift wrap fee
        double giftWrapFee = 0.0;
        for (int i = 0; i < products.length; i++) {
            if (giftWrap[i]) {
                giftWrapFee += quantities[i];
            }
        }
        giftWrapFee *= 1.0; // $1 per unit

        // Calculate total
        double total = subtotal - discount + shippingFee + giftWrapFee;

        // Output details
        System.out.println("\nProduct Details:");
        for (int i = 0; i < products.length; i++) {
            double productAmount = quantities[i] * prices[i];
            System.out.println(products[i] + " - Quantity: " + quantities[i] + ", Total: $" + productAmount);
        }

        System.out.println("\nSubtotal: $" + subtotal);
        System.out.println("Discount Applied: " + appliedDiscount + ", Amount: $" + discount);
        System.out.println("Shipping Fee: $" + shippingFee);
        System.out.println("Gift Wrap Fee: $" + giftWrapFee);
        System.out.println("Total: $" + total);

        scanner.close();

	}

}
