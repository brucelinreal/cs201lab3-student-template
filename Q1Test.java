import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Q1Test {
     public static void main(String[] args) {

          String result, traversal1, traversal2, traversal3;

          traversal1 = "1-2";
          traversal2 = "2-1-3";
          traversal3 = "1-3-2";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println("Expected Result : Invalid traversals");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "1-2-3";
          traversal2 = "2-1-3";
          traversal3 = "1-3-2";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "1-2-3";
          traversal2 = "3-2-1";
          traversal3 = "2-3-1";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println("Expected Result : Invalid traversals");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "3-1-2-5-4";
          traversal2 = "2-1-4-5-3";
          traversal3 = "1-2-3-4-5";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Preorder, Traversal 2 - Postorder, Traversal 3 - Inorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "10-20-30-40-50";
          traversal2 = "20-10-30-40-50";
          traversal3 = "10-50-40-30-20";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();
     }

     // write your codes here
     // write your codes here
     static class Node {
          int val;
          Node left, right;

          Node(int val) {
               this.val = val;
          }
     }

     public static String verify(String traversal1, String traversal2, String traversal3) {
          String[] t = { traversal1, traversal2, traversal3 };

          for (int pre = 0; pre < 3; pre++) {
               Node root = build(t[pre]); 

               for (int in = 0; in < 3; in++) {
                    if (in == pre)
                         continue;
                    int post = 3 - pre - in; 

                    if (inorder(root).equals(t[in]) && postorder(root).equals(t[post])) {
                         String[] names = new String[3];
                         names[pre] = "Preorder";
                         names[in] = "Inorder";
                         names[post] = "Postorder";
                         return "Traversal 1 - " + names[0]
                                   + ", Traversal 2 - " + names[1]
                                   + ", Traversal 3 - " + names[2];
                    }
               }
          }
          return "Invalid traversals";
     }

     // Build a BST by inserting values in the given order
     private static Node build(String s) {
          Node root = null;
          for (String part : s.split("-")) {
               root = insert(root, Integer.parseInt(part.trim()));
          }
          return root;
     }

     private static Node insert(Node node, int v) {
          if (node == null)
               return new Node(v);
          if (v < node.val)
               node.left = insert(node.left, v);
          else
               node.right = insert(node.right, v);
          return node;
     }

     private static String inorder(Node root) {
          List<Integer> out = new ArrayList<>();
          inorder(root, out);
          return join(out);
     }

     private static void inorder(Node n, List<Integer> out) {
          if (n == null)
               return;
          inorder(n.left, out);
          out.add(n.val);
          inorder(n.right, out);
     }

     private static String postorder(Node root) {
          List<Integer> out = new ArrayList<>();
          postorder(root, out);
          return join(out);
     }

     private static void postorder(Node n, List<Integer> out) {
          if (n == null)
               return;
          postorder(n.left, out);
          postorder(n.right, out);
          out.add(n.val);
     }

     private static String join(List<Integer> list) {
          return list.stream().map(String::valueOf).collect(Collectors.joining("-"));
     }
}