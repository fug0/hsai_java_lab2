import java.util.Arrays;
import java.util.Random;

public class App
{
    static int[] matrixDims = {400, 1200, 2400};
    static int [][] nThreads = { {2, 4, 6, 8}, {4, 8, 16, 32}, {10, 20, 40, 80} };

    private static final Random Random = new Random();

    public static double[][] createMatrix(int size){
        double[][] a = new double[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                a[i][j] = Random.nextInt(100) +1;
            }
        }
        return a;
    }

    public static double[] createVecs(int size){
        double[] b = new double[size];
        for (int i = 0; i < size; i++) {
            b[i] = Random.nextInt(100);
        }
        return b;
    }

    public static double[][] newCopy(double[][] a) {
        double[][] b = new double[a.length][a.length];
        for (int i = 0; i < a.length; i++) {
            b[i] = Arrays.copyOf(a[i], a.length);
        }
        return b;
    }

    public static void main( String[] args )
    {
        final LinearEquation solver = new LinearEquation();
        long time;
        double[] result;

        for (int i = 0; i < 3; ++i) {
            System.out.println("\nResearch N" + (i + 1));
            int size = matrixDims[i];
            double[][] factors = createMatrix(size);
            double[] sleVecs = createVecs(size);

            double[][] factors1 = newCopy(factors);
            double[][] factors2 = newCopy(factors);
            double[][] factors3 = newCopy(factors);
            double[][] factors4 = newCopy(factors);

            double[] sleVecs1 = Arrays.copyOf(sleVecs, sleVecs.length);
            double[] sleVecs2 = Arrays.copyOf(sleVecs, sleVecs.length);
            double[] sleVecs3 = Arrays.copyOf(sleVecs, sleVecs.length);
            double[] sleVecs4 = Arrays.copyOf(sleVecs, sleVecs.length);

            System.out.println("Matrix size = " + size + " x " + size + "\n-----------Results-----------");
            try {
                time = System.currentTimeMillis();
                result = solver.solveSerial(factors, sleVecs);
                System.out.println("1 thread: " + (System.currentTimeMillis() - time) + " milliseconds");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            time = System.currentTimeMillis();
            result = solver.solveParallel(factors1, sleVecs1, nThreads[i][0]);
            System.out.println(nThreads[i][0] + " threads: " + (System.currentTimeMillis() - time) + " milliseconds");

            time = System.currentTimeMillis();
            result = solver.solveParallel(factors2, sleVecs2, nThreads[i][1]);
            System.out.println(nThreads[i][1] + " threads: " + (System.currentTimeMillis() - time) + " milliseconds");

            time = System.currentTimeMillis();
            result = solver.solveParallel(factors3, sleVecs3, nThreads[i][2]);
            System.out.println(nThreads[i][2] + " threads: " + (System.currentTimeMillis() - time) + " milliseconds");

            time = System.currentTimeMillis();
            result = solver.solveParallel(factors4, sleVecs4, nThreads[i][3]);
            System.out.println(nThreads[i][3] + " threads: " + (System.currentTimeMillis() - time) + " milliseconds");
        }
    }
}