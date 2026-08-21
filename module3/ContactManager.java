import java.util.*;

public class ContactManager {
  
  public static void main(String[] args) {
    HashMap<String, Contact> contacts = new HashMap<>();

    // Step 4

    Contact contact1 = new Contact("Ada Lovelace", "+1 617 555 0101");
    Contact contact2 = new Contact("Bob Smith", "+1 617 555 0102");
    Contact contact3 = new Contact("Charlie Brown", "+1 617 555 0103");
    Contact contact4 = new Contact("David Wilson", "+1 617 555 0104");
    Contact contact5 = new Contact("Elliot Johnson", "+1 617 555 0105");

    contacts.put(contact1.getName(), contact1);
    contacts.put(contact2.getName(), contact2);
    contacts.put(contact3.getName(), contact3);
    contacts.put(contact4.getName(), contact4);
    contacts.put(contact5.getName(), contact5);

    // Step 5

    Contact existingContact = contacts.get("Ada Lovelace");
    Contact nonExistingContact = contacts.get("Frank Miller");
    ArrayList<Contact> testCases = new ArrayList<>();
    testCases.add(existingContact);
    testCases.add(nonExistingContact);

    for (Contact contact : testCases) {
      if (contact != null) {
        System.out.println(contact.toString());
      } else {
        System.out.println("Contact not found.");
      }
    }

    // Step 6

    ArrayList<Contact> sorted = new ArrayList<>(contacts.values());
    sorted.sort((a,b) -> a.getName().compareTo(b.getName()));

    System.out.println("\n=== All Contacts ===");
    for (Contact contact : sorted) {
      System.out.println(contact.toString());
    }
  }
}