import org.junit.Before;
import org.junit.Test;
import sbt.bit.zaborovskiy.GameOfLife;
import sbt.bit.zaborovskiy.MultiThreadedGameOfLife;
import sbt.bit.zaborovskiy.SingleThreadedGameOfLife;
import sbt.bit.zaborovskiy.parser.Parser;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Mintas on 12/10/2017.
 */
public class GameOfLifeTest {
    private GameOfLife gameOfLife;

    private static List<String> readFile(String fileName) throws FileNotFoundException {
        return Parser.readFile(fileName);
    }

    @Before
    public void before() {
        gameOfLife = new MultiThreadedGameOfLife();
    }

    @Test
    public void testGame() throws Exception {
        testOneGame("resources/input100.txt", "resources/output100.txt");
        testOneGame("resources/input100.txt", "resources/output100.txt");
        testOneGame("resources/input100.txt", "resources/output100.txt");
        testOneGame("resources/input100.txt", "resources/output100.txt");
        testOneGame("resources/input100.txt", "resources/output100.txt");
        testOneGame("resources/input100.txt", "resources/output100.txt");
        testOneGame("resources/input100.txt", "resources/output100.txt");
        testOneGame("resources/input100.txt", "resources/output100.txt");
    }

    private void testOneGame(String inputFile, String expectedOutputFile) throws FileNotFoundException {
        List<String> result = gameOfLife.play(inputFile);
        assertEquals(readFile(expectedOutputFile).toArray(), result.toArray());
    }
}
