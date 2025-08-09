package mininotion;

import java.util.*;

public class NotionLiteApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        mininotion.NoteManager nm = new mininotion.NoteManager();
        TaskManager tm = new TaskManager();

        // Load saved data
        nm.setNotebooks(FileManager.loadNotebooks());
        tm.setTasks(FileManager.loadTasks());

        boolean running = true;

        while (running) {
            System.out.println("\n--- Mini Notion Console ---");
            System.out.println("=== Note Management ===");
            System.out.println("1. Create Notebook | 2. Add Note | 3. View Notes | 4. Update Note");
            System.out.println("5. Delete Note | 6. Search by Tag | 7. Undo Note | 8. Pin Note");
            System.out.println("=== Task Management ===");
            System.out.println("9. Create Task | 10. View Tasks | 11. Complete Task | 12. Undo Task");
            System.out.println("=== System ===");
            System.out.println("13. Session Summary | 14. Exit");
            System.out.print("Choose option: ");

            int choice;
            try {
                choice = sc.nextInt(); sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                sc.nextLine();
                continue;
            }

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter notebook name: ");
                        nm.createNotebook(sc.nextLine());
                        break;
                    case 2:
                        System.out.print("Notebook: ");
                        String nb = sc.nextLine();
                        System.out.print("Title: ");
                        String title = sc.nextLine();
                        System.out.print("Content: ");
                        String content = sc.nextLine();
                        System.out.print("Tags (comma-separated): ");
                        Set<String> tags = new HashSet<>(Arrays.asList(sc.nextLine().split(",")));
                        nm.addNote(nb, title, content, tags);
                        break;
                    case 3:
                        System.out.print("Notebook name: ");
                        nm.viewAllNotes(sc.nextLine());
                        break;
                    case 4:
                        System.out.print("Notebook: ");
                        nb = sc.nextLine();
                        System.out.print("Note ID: ");
                        int nid = sc.nextInt(); sc.nextLine();
                        System.out.print("New title: ");
                        title = sc.nextLine();
                        System.out.print("New content: ");
                        content = sc.nextLine();
                        nm.updateNote(nb, nid, title, content);
                        break;
                    case 5:
                        System.out.print("Notebook: ");
                        nb = sc.nextLine();
                        System.out.print("Note ID: ");
                        nm.deleteNote(nb, sc.nextInt()); sc.nextLine();
                        break;
                    case 6:
                        System.out.print("Enter tag: ");
                        nm.searchByTag(sc.nextLine());
                        break;
                    case 7:
                        System.out.print("Notebook: ");
                        nb = sc.nextLine();
                        System.out.print("Note ID: ");
                        nm.undoNote(nb, sc.nextInt()); sc.nextLine();
                        break;
                    case 8:
                        System.out.print("Notebook: ");
                        nb = sc.nextLine();
                        System.out.print("Note ID: ");
                        nm.pinNote(nb, sc.nextInt()); sc.nextLine();
                        break;
                    case 9:
                        System.out.print("Title: ");
                        String taskTitle = sc.nextLine();
                        System.out.print("Description: ");
                        String taskDesc = sc.nextLine();
                        System.out.print("Due date (YYYY-MM-DD): ");
                        String dueDate = sc.nextLine();
                        System.out.print("Priority (Low/Medium/High): ");
                        String priority = sc.nextLine();
                        tm.createTask(taskTitle, taskDesc, dueDate, priority);
                        break;
                    case 10:
                        tm.displayTasks();
                        break;
                    case 11:
                        System.out.print("Task ID: ");
                        tm.markTaskAsCompleted(sc.nextInt()); sc.nextLine();
                        break;
                    case 12:
                        tm.undo();
                        break;
                    case 13:
                        System.out.println("\n=== SESSION SUMMARY ===");
                        nm.sessionSummary();
                        tm.taskSummary();
                        break;
                    case 14:
                        running = false;
                        // Save data on exit
                        FileManager.saveNotebooks(nm.getNotebooks());
                        FileManager.saveTasks(tm.getTasks());
                        System.out.println("Exiting... Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please try again.");
                sc.nextLine();
            }
        }
        sc.close();
    }
}
