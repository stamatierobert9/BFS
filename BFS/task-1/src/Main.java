import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    static class Task {
        public static final String INPUT_FILE = "in";
        public static final String OUTPUT_FILE = "out";

        // numarul maxim de noduri
        public static final int NMAX = (int)1e5 + 5; // 10^5 + 5 = 100.005

        // n = numar de noduri, m = numar de muchii/arce
        int n, m;

        // adj[node] = lista de adiacenta a nodului node
        // exemplu: daca adj[node] = {..., neigh, ...} => exista arcul (node, neigh)
        @SuppressWarnings("unchecked")
        ArrayList<Integer> adj[] = new ArrayList[NMAX];

        public void solve() {
            readInput();
            writeOutput(getResult());
        }

        private void readInput() {
            try {
                Scanner sc = new Scanner(new BufferedReader(new FileReader(
                        INPUT_FILE)));
                n = sc.nextInt();
                m = sc.nextInt();

                for (int node = 1; node <= n; node++) {
                    adj[node] = new ArrayList<>();
                }

                for (int i = 1, x, y; i <= m; i++) {
                    // arc (x, y)
                    x = sc.nextInt();
                    y = sc.nextInt();
                    adj[x].add(y);
                }

                sc.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private void writeOutput(ArrayList<Integer> topsort) {
            try {
                PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(
                        OUTPUT_FILE)));
                for (Integer node : topsort) {
                    pw.printf("%d ", node);
                }
                pw.printf("\n");
                pw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private void Dfs_recursivity(int source, ArrayList<Boolean> visited, ArrayList<Integer> topsort) {
            visited.set(source, true);
            for(int i = 0; i < adj[source].size(); i++){
                int neighbour = adj[source].get(i);
                if(!visited.get(neighbour)){
                    Dfs_recursivity(neighbour, visited, topsort);
                }
            }
            topsort.add(source);
        }

        private int getResult() {
            // TODO: DFS
            ArrayList<Boolean> visisted = new ArrayList<Boolean>();
            ArrayList<Integer> topsort = new ArrayList<Integer>();

            int counter = 0;

            for(int i = 0; i <= n; i++){
                ArrayList<Boolean> visited = null;
                if(!visited.get(i)){
                    Dfs_recursivity(i, visited, topsort);
                    counter++;
                }
            }
            return counter;
        }

        private void print_output(int counter){
try {
                PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(
                        OUTPUT_FILE)));
                pw.printf("%d\n", counter);
                pw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        new Task().solve();
    }
}
