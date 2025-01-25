import java.io.*;
import java.util.*;

public class InventoryManager {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        String fileName = "inventory.txt";

        while(true){
            System.out.println("\n Inventory Manager");
            System.out.println("1. View Inventory");
            System.out.println("2. Add New Item");
            System.out.println("3. Update Existing Item");
            System.out.println("4. Exit");
            System.out.println("Enter your choice:");

            int choice = sc.nextInt();
            sc.nextLine();

            switch(choice){
                case 1:
                    readInventory(fileName);
                    break;
                case 2:
                    System.out.println("Enter item name:");
                    String itemName = sc.nextLine();
                    System.out.println("Enter item count");
                    int itemCount = sc.nextInt();
                    sc.nextLine();
                    addItem(fileName,itemName,itemCount);
                    break;
                case 3:
                    System.out.println("Enter itm name to update:");
                    String updateItemName= sc.nextLine();
                    System.out.println("Enter new count");
                    int newCount = sc.nextInt();
                    sc.nextLine();
                    updateItem(fileName,updateItemName,newCount);
                    break;
                case 4:
                    System.out.println("Exiting program");
                    sc.close();
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    public static void readInventory(String fileName) {
       

        File file = new File(fileName);
        if(!file.exists()){
            System.out.println("Inventory is empty");
            return;
        }
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line;
            System.out.println("Inventory");
            while((line = reader.readLine())!= null){
                System.out.println(line);
            }
        }catch(IOException e){
            System.out.println("Error while reading Inventory"+ e.getMessage());
        }
    }

    public static void addItem(String fileName, String itemName, int itemCount) {
       File file = new File(fileName);
       boolean itemExist = false;
       List<String> inventory = new ArrayList<>();

       try(BufferedReader reader = new BufferedReader(new FileReader(file))){
        String line;
        System.out.println("Inventory");
        while((line = reader.readLine())!= null){
            if(line.startsWith(itemName + ":")){
                itemExist=true;
                break;
            }
            inventory.add(line);
        }
    }catch(IOException e){
        System.out.println("Error while reading Inventory"+ e.getMessage());
    }

    if(itemExist){
        System.out.println("Item already exist ");

    }else{
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file,true))){
            writer.write(itemName + ":" + itemCount);
            writer.newLine();
            System.out.println("Item added sucessfully ");

        }catch(IOException e){
            System.out.println("Error while adding item " + e.getMessage());

        }
    }
        
    }

    public static void updateItem(String fileName, String itemName, int itemCount) {
 
        File file = new File(fileName);
        boolean itemExist = false;
        List<String> inventory = new ArrayList<>();
 
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
         String line;
         System.out.println("Inventory");
         while((line = reader.readLine())!= null){
             if(line.startsWith(itemName + ":")){
                inventory.add(itemName + ":"+ itemCount);
                 itemExist=true;
                 break;
             }
             inventory.add(line);
         }
     }catch(IOException e){
         System.out.println("Error while reading Inventory"+ e.getMessage());
     }
     if(!itemExist){
        System.out.println("Item not exist ");

    }else{
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
           for(String entry :inventory){
                writer.write(entry);
                writer.newLine();
            }
            System.out.println("Item updaed sucessfully");

        }catch(IOException e){
            System.out.println("Error while updating item" +e.getMessage());
        }

    
}
    }
}
