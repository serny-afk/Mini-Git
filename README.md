# Mini-Git

MiniGit is a simplified Git-like version control system implemented in Java.
User can stage files, commit changes, and view commit history, storing objects in a .minigit folder.

# Features
- Stage files: Add files to the staging area using add.
- Commit changes: Save staged files as a commit with a message.
- View status: See which files are staged and their blob hashes.
- View history: Traverse commit history using log.
- SHA-1 hashing: Generates unique object IDs for files and commits.

# Requirements
- Java 17 or later
- Command-line access (PowerShell, Terminal, or similar)

# Setup
1. Clone or download this repository.
2. Compile the project via: javac -d bin src\interfaces*.java src\minigit*.java
3. Run MiniGit: java -cp bin minigit.MiniGit [args]

# Usage
- Initialize repository: java -cp bin minigit.MiniGit init
- Stage file for commit (create blob): java -cp bin minigit.MiniGit add .txt
- Check status: java -cp bin minigit.MiniGit status
- Commit changes: java -cp bin minigit.MiniGit commit "Commit message"
- View commit history: java -cp bin minigit.MiniGit log

# Notes / Limitations
- Only UTF-8 text files supported for now.
- Binary files may not work correctly.
- Linear commit history only — no branching or merging yet.
- Objects are stored as raw file content, not Git-compressed objects.
- Future improvements may include Git-style object storage, trees, and branches.