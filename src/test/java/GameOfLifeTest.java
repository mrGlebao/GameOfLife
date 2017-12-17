import org.junit.Before;
import org.junit.Test;
import sbt.bit.zaborovskiy.GameOfLife;
import sbt.bit.zaborovskiy.GameOfLifeImpl;
import sbt.bit.zaborovskiy.SingleThreadedGameOfLife;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

/**
 * Created by Mintas on 12/10/2017.
 */
public class GameOfLifeTest {
    private GameOfLife gameOfLife;

    private static List<String> readFile(String fileName) throws FileNotFoundException {
        ArrayList<String> lines = new ArrayList<>();

        Scanner scan = new Scanner(new File(fileName));
        while (scan.hasNextLine()) {
            lines.add(scan.nextLine());
        }
        scan.close();

        return lines;
    }

    @Before
    public void before() {
        gameOfLife = new GameOfLifeImpl();
    }

    @Test
    public void testGame() throws Exception {
        testOneGame("resources/input100.txt", "resources/output100.txt");
    }

    private void testOneGame(String inputFile, String expectedOutputFile) throws FileNotFoundException {
        List<String> result = gameOfLife.play(inputFile);
        assertEquals(readFile(expectedOutputFile), result);
    }
}
