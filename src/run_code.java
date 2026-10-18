import java.util.*;
class Tree_element {
    int data;
    Tree_element left;
    Tree_element right;

}


public class run_code{
     static void main() {
        run_code run = new run_code();
        Tree_element root = make(50);
        root.left = make(10);
        root.right = make(60);
        root.right.left = make(70);
        root.right.right = make(75);
        root.right.left.left = make(65);
        root.right.left.right = make(80);
        run.allPaths.clear();
        run.obhod(root, null);
        run.find_max(run.allPaths);

    }
    private final List<List<Integer>> allPaths = new ArrayList<>();

    private static Tree_element make(int value) {
        Tree_element n = new Tree_element();
        n.data = value;
        return n;
    }

    private void obhod(Tree_element tree, Integer parentValue) {
        if (tree == null) return;

        if (parentValue == null || tree.data <= parentValue) {
            List<Integer> path = new ArrayList<>();
            go_down(tree, path);
            allPaths.add(path);
        }
        obhod(tree.left,  tree.data);
        obhod(tree.right, tree.data);
    }

    private void go_down(Tree_element tree, List<Integer> path) {
        path.add(tree.data);
        boolean goLeft  = tree.left  != null && tree.left.data  > tree.data;
        boolean goRight = tree.right != null && tree.right.data > tree.data;

        if (goLeft && goRight) {
            List<Integer> leftPath  = new ArrayList<>(path);
            List<Integer> rightPath = new ArrayList<>(path);
            go_down(tree.left,  leftPath);
            go_down(tree.right, rightPath);
        } else if (goLeft) {
            go_down(tree.left, path);
        } else if (goRight) {
            go_down(tree.right, path);
        }else {
            allPaths.add(new ArrayList<>(path));
        }
    }

    private void find_max(List<List<Integer>> allPaths){
        Optional<List<Integer>> longestList = allPaths.stream()
                .max(Comparator.comparingInt(List::size));
        if (longestList.isPresent()) {
            System.out.print("Самый длинный список: " + longestList.get() + "\n");
            System.out.print("Длина: " + longestList.get().size());
        }
    }
}






