package minigit;

// javac -d bin src\interfaces\*.java src\minigit\*.java

public class MiniGit {
    public static void main(String[] args) {
        System.out.println("MiniGit start!");
        Repository repo = new Repository(new Index());

        if (args.length == 0) {
            System.out.println("Usage: java minigit.MiniGit <command> [args]");
            return;
        }

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
