package minigit;

// javac -d bin src\interfaces\*.java src\minigit\*.java

public class MiniGit {
    public static void main(String[] args) {
        // runs on each program command
        // runs are stateless in memory, info is stored on local disk
        System.out.println("MiniGit start!");
        // yes this prints everytime you run it
        Repository repo = new Repository(new Index());

        // check for invalid inputs by user
        if (args.length == 0) {
            System.out.println("Usage: java minigit.MiniGit <command> [args]");
            return;
        }

        // CLI parser
        // usage: java -cp bin minigit.MiniGit <COMMAND>
        String command = args[0];
        switch (command) {
            case "init":
                repo.init();
                break;

            case "add":
                if (args.length < 2) {
                    System.out.println("Specify a file to add");
                } else {
                    repo.add(args[1]);
                }
                break;

            case "commit":
                if (args.length < 2) {
                    System.out.println("Specify a commit message");
                } else {
                    repo.commit(args[1]);
                }
                break;

            case "status":
                repo.status();
                break;

            case "log":
                repo.log();
                break;

            default:
                System.out.println("Unknown command: " + command);
                break;
        }
    }
}
