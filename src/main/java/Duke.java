import java.io.File;
import java.io.IOException;

public class Duke {

    private Storage storage;
    private TaskList tasks;

    /**
     * Constructor for Duke.
     */
    public Duke() {
        String filePath = new File("").getAbsolutePath();
        int ind = filePath.lastIndexOf("/");
        String path = filePath.substring(0, ind + 1);
        filePath = path.concat("duke.txt");
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            //if file is empty
            tasks = new TaskList();
        }
    }

    /**
     * Runs the code well.
     *
     * @throws IOException Throws IOException.
     */
    public String getResponse(String fullCommand) throws IOException {
        try {
            Command c = Parser.parse(0,fullCommand,0);
            return c.execute(tasks, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
