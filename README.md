Project Summary – MiniNotion
MiniNotion is a Java-based desktop productivity application that combines note-taking and task management into a single, lightweight platform designed for small teams and students. The application is built using Object-Oriented Programming (OOP) principles and leverages Java Collections Framework and custom data structures for efficient data handling.

Key Features:
Notes Module:

Create, edit, delete, and search notes.

Support for tagging, pinning, and version history.

Notes are auto-sorted using a custom Bubble Sort method based on pinned status and latest updates.

Tasks Module:

Create tasks with priority levels and due dates.

Tasks are organized using a Priority Queue for efficient scheduling.

Undo functionality to recover recently deleted or updated tasks.

Persistent Storage:

Data is saved locally using Java Object Serialization, ensuring the application works offline and restores user data on restart.

Optimized Design:

Clear separation of concerns through modular classes (Task, Note, NoteManager, TaskManager).

Follows OOP principles like encapsulation, abstraction, and inheritance for maintainability.

User-Friendly Interface:

Minimalistic, distraction-free UI for improved productivity.

Logical menu navigation and simple input handling.

Technology Stack:
Language: Java (Core Java, Collections Framework)

Data Structures: HashMap, PriorityQueue, LinkedList, Stack

Storage: Java Object Serialization

Algorithms: Custom sorting (Bubble Sort for pinned notes), Priority-based task scheduling

Design Pattern: Singleton for managers and utility classes

Why It’s Unique:
Combines two essential productivity tools (notes + tasks) in one lightweight, offline app.

Implements version control for notes, which is not common in simple note apps.

Uses custom data structures and sorting algorithms instead of relying entirely on built-in methods — demonstrating in-depth learning.
