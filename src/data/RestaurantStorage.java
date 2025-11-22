package data;

import domain.Restaurant;
import java.io.*;

//Storage with restaurant//
public class RestaurantStorage {

    public static void save(Restaurant restaurant, String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(restaurant);
            System.out.println("Restaurant data saved successfully");
        }catch (IOException e){
            System.err.println("Error saving restaurant data " + e.getMessage());
        }
    }

    public static Restaurant load(String filename){
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))){
            return (Restaurant) in.readObject();
        }catch (IOException | ClassNotFoundException e){
            System.err.println("Error loading restaurant data " + e.getMessage());
            return null;
        }
    }
}
//exportar