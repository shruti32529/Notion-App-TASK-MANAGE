package mininotion;

import java.io.*;
import java.util.*;

public class FileManager {
    private static final String NOTEBOOKS_FILE = "notebooks.dat";
    private static final String TASKS_FILE = "tasks.dat";

    public static void saveNotebooks(Map<String, Notebook> notebooks) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(NOTEBOOKS_FILE))) {
            oos.writeObject(notebooks);
        } catch (IOException e) {
            System.out.println("Error saving notebooks: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Notebook> loadNotebooks() {
        File file = new File(NOTEBOOKS_FILE);
        if (!file.exists()) return new HashMap<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (Map<String, Notebook>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading notebooks: " + e.getMessage());
            return new HashMap<>();
        }
    }

    public static void saveTasks(List<Task> tasks) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(TASKS_FILE))) {
            oos.writeObject(tasks);
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Task> loadTasks() {
        File file = new File(TASKS_FILE);
        if (!file.exists()) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Task>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
