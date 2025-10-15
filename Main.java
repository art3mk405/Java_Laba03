import java.util.*;

/**
 * Laboratory Work #3.
 * <p>
 * Topic: Classes in Java programming language.
 * Variant: 4 (Clothing class)
 * <p>
 * This program demonstrates:
 * - creating a custom class {@link Clothing};
 * - initializing an array of objects;
 * - sorting objects by two fields (ascending & descending);
 * - finding an identical object in the array;
 * - handling exceptions safely.
 *
 * @author Student
 * @version 1.0
 */
public class Main {

    /**
     * Entry point of the program.
     * <p>
     * The method initializes a list of {@link Clothing} objects,
     * allows user input for sorting criteria,
     * performs a combined sort (ascending & descending),
     * and searches for an identical element in the array.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("===========================================");
        System.out.println("           Laboratory Work #3");
        System.out.println("===========================================");
        System.out.println("Topic: Classes in Java programming language");
        System.out.println("Variant: 4");
        System.out.println("===========================================\n");

        try (Scanner sc = new Scanner(System.in)) {
            int recordNumber = 4;
            int C11 = recordNumber % 11;

            System.out.println("Results for Variant 4:");
            System.out.println("C11 = " + C11 + "  - Task: Clothing class (>=5 fields)");
            System.out.println("===========================================\n");

            // Initialize array of Clothing objects
            Clothing[] clothes = {
                    new Clothing("Uniqlo", "M", "Olive", 1499.0, "Puffer Jacket"),
                    new Clothing("Bershka", "S", "Beige", 899.0, "Oversized Shirt"),
                    new Clothing("The North Face", "L", "Black", 4999.0, "Tech Fleece Hoodie"),
                    new Clothing("Stone Island", "L", "Navy", 8499.0, "Crewneck Sweater"),
                    new Clothing("Off-White", "M", "Gray", 12999.0, "Denim Jacket"),
                    new Clothing("H&M", "XL", "White", 799.0, "Cotton Hoodie"),
                    new Clothing("Zara", "M", "Burgundy", 1299.0, "Turtleneck Sweater"),
                    new Clothing("Armani Exchange", "M", "Black", 4999.0, "Leather Jacket"),
                    new Clothing("Nike", "L", "Blue", 1999.0, "Tech Pants"),
                    new Clothing("Carhartt", "M", "Brown", 2799.0, "Work Jacket")
            };

            System.out.println("Initial array:");
            for (Clothing c : clothes) System.out.println(c);
            System.out.println("-------------------------------------------");

            // Input sorting fields
            System.out.println("Available fields: brand, size, color, price, category");
            System.out.print("Enter field for ascending sort: ");
            String fieldAsc = sc.nextLine().trim().toLowerCase();

            System.out.print("Enter field for descending sort: ");
            String fieldDesc = sc.nextLine().trim().toLowerCase();

            // Create combined comparator
            Comparator<Clothing> comparator = getComparator(fieldAsc, true)
                    .thenComparing(getComparator(fieldDesc, false));

            // Sort array
            Arrays.sort(clothes, comparator);

            System.out.println("\nSorted array (" + fieldAsc + " ↑, " + fieldDesc + " ↓):");
            for (Clothing c : clothes) System.out.println(c);
            System.out.println("-------------------------------------------");

            // Find identical object
            Clothing target = new Clothing("Nike", "L", "Blue", 1999.0, "Tech Pants");
            boolean found = false;

            for (Clothing c : clothes) {
                if (c.equals(target)) {
                    System.out.println("Identical object found: " + c);
                    found = true;
                    break;
                }
            }

            if (!found) System.out.println("Identical object not found.");
            System.out.println("===========================================");

        } catch (Exception e) {
            System.out.println("Error: unexpected issue occurred!");
            System.out.println("Details: " + e.getMessage());
        }
    }

    /**
     * Returns a comparator for sorting {@link Clothing} objects
     * by the specified field in ascending or descending order.
     *
     * @param field     field name: "brand", "size", "color", "price", or "category"
     * @param ascending true to sort in ascending order, false for descending
     * @return configured {@link Comparator} for sorting
     */
    private static Comparator<Clothing> getComparator(String field, boolean ascending) {
        Comparator<Clothing> comparator;
        switch (field) {
            case "brand" -> comparator = Comparator.comparing(Clothing::getBrand, String.CASE_INSENSITIVE_ORDER);
            case "size" -> comparator = Comparator.comparing(Clothing::getSize, String.CASE_INSENSITIVE_ORDER);
            case "color" -> comparator = Comparator.comparing(Clothing::getColor, String.CASE_INSENSITIVE_ORDER);
            case "price" -> comparator = Comparator.comparingDouble(Clothing::getPrice);
            case "category" -> comparator = Comparator.comparing(Clothing::getCategory, String.CASE_INSENSITIVE_ORDER);
            default -> comparator = Comparator.comparing(Clothing::getBrand);
        }
        return ascending ? comparator : comparator.reversed();
    }
}

/**
 * Represents a clothing item with brand, size, color, price, and category.
 */
class Clothing {
    private String brand;
    private String size;
    private String color;
    private double price;
    private String category;

    /**
     * Constructor to initialize a clothing item.
     *
     * @param brand    brand name of the clothing item
     * @param size     size (e.g., S, M, L, XL)
     * @param color    color of the clothing item
     * @param price    price of the clothing item in UAH
     * @param category type or category (e.g., Jacket, Hoodie)
     */
    public Clothing(String brand, String size, String color, double price, String category) {
        this.brand = brand;
        this.size = size;
        this.color = color;
        this.price = price;
        this.category = category;
    }

    /** @return brand of the clothing item */
    public String getBrand() { return brand; }

    /** @return size of the clothing item */
    public String getSize() { return size; }

    /** @return color of the clothing item */
    public String getColor() { return color; }

    /** @return price of the clothing item */
    public double getPrice() { return price; }

    /** @return category of the clothing item */
    public String getCategory() { return category; }

    /**
     * Compares two clothing objects for equality by all fields.
     *
     * @param obj object to compare with
     * @return true if all fields are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Clothing c = (Clothing) obj;
        return brand.equals(c.brand)
                && size.equals(c.size)
                && color.equals(c.color)
                && price == c.price
                && category.equals(c.category);
    }

    /**
     * Returns a formatted string containing all field values.
     *
     * @return string representation of the clothing object
     */
    @Override
    public String toString() {
        return String.format("Clothing{brand='%s', size='%s', color='%s', price=%.2f, category='%s'}",
                brand, size, color, price, category);
    }
}
