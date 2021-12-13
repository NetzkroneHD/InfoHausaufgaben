package de.noah.infoha.abiturklassen;


import java.util.Random;

public class Traversierung {

    public enum Type {
        INORDER,
        PREORDER,
        POSTORDER
    }

    private StringBuilder baumString;

    public Traversierung() {

    }

    public String traversiereBaum(BinaryTree<?> tree, Type type, String separator) {
        baumString = new StringBuilder();
        switch (type) {
            case INORDER:
                inorder(tree, separator);
                break;
            case PREORDER:
                preorder(tree, separator);
                break;
            case POSTORDER:
                postorder(tree, separator);
                break;
        }
        return baumString.toString();
    }

    public String traversiereBaum(BinaryTree<?> tree, Type type) {
        return traversiereBaum(tree, type, "");
    }

    private void inorder(BinaryTree<?> tree, String separator) {
        if(!tree.isEmpty()) {
            inorder(tree.getLeftTree(), separator);
            baumString.append(tree.getContent()).append(separator);
            inorder(tree.getRightTree(), separator);
        }
    }

    private void preorder(BinaryTree<?> tree, String separator) {
        if(!tree.isEmpty()) {
            baumString.append(tree.getContent()).append(separator);
            preorder(tree.getLeftTree(), separator);
            preorder(tree.getRightTree(), separator);
        }
    }

    private void postorder(BinaryTree<?> tree, String separator) {
        if(!tree.isEmpty()) {
            preorder(tree.getLeftTree(), separator);
            preorder(tree.getRightTree(), separator);
            baumString.append(tree.getContent()).append(separator);
        }
    }


    public String traversiereBaum(BinarySearchTree<?> tree, Type type, String separator) {
        baumString = new StringBuilder();
        switch (type) {
            case INORDER:
                inorder(tree, separator);
                break;
            case PREORDER:
                preorder(tree, separator);
                break;
            case POSTORDER:
                postorder(tree, separator);
                break;
        }
        return baumString.toString();
    }

    public String traversiereBaum(BinarySearchTree<?> tree, Type type) {
        return traversiereBaum(tree, type, "");
    }

    private void inorder(BinarySearchTree<?> tree, String separator) {
        if(!tree.isEmpty()) {
            inorder(tree.getLeftTree(), separator);
            baumString.append(tree.getContent()).append(separator);
            inorder(tree.getRightTree(), separator);
        }
    }

    private void preorder(BinarySearchTree<?> tree, String separator) {
        if(!tree.isEmpty()) {
            baumString.append(tree.getContent()).append(separator);
            preorder(tree.getLeftTree(), separator);
            preorder(tree.getRightTree(), separator);
        }
    }

    private void postorder(BinarySearchTree<?> tree, String separator) {
        if(!tree.isEmpty()) {
            preorder(tree.getLeftTree(), separator);
            preorder(tree.getRightTree(), separator);
            baumString.append(tree.getContent()).append(separator);
        }
    }


    public void fuegeEin(BinaryTree<TraversierungExample> tree, TraversierungExample content) {
        if(tree.isEmpty()) {
            tree.setContent(content);
        } else {
            final BinaryTree<TraversierungExample> newTree = new BinaryTree<>();
            if(tree.getContent().isGreater(content)) {
                if(tree.getRightTree() != null) {
                    fuegeEin(tree.getRightTree(), content);
                } else tree.setRightTree(newTree);
            } else if(tree.getContent().isLess(content) || tree.getContent().isEqual(content)) {
                if(tree.getLeftTree() != null) {
                    fuegeEin(tree.getLeftTree(), content);
                } else tree.setLeftTree(newTree);
            }
        }
    }

    public static void main(String[] args) {
        final BinaryTree<TraversierungExample> bt = new BinaryTree<>();
        final Traversierung tv = new Traversierung();
        final Random r = new Random();

        for(int i = 0; i < 30000; i++) {
            tv.fuegeEin(bt, new TraversierungExample(r.nextInt(2)));
            System.out.println(i);
        }

        System.out.print(tv.traversiereBaum(bt, Type.INORDER, ", "));
        final BaumZeichner bz = new BaumZeichner(600, 400, bt);


        bz.setVisible(true);

    }

}
