package de.noah.infoha.abiturklassen;


public class Traversierung {

    public enum Type {
        INORDER,
        PREORDER,
        POSTORDER
    }

    private StringBuilder baumString;

    public Traversierung() {

    }

    public String traversiereBaum(BinaryTree<?> tree, Type type) {
        baumString = new StringBuilder();
        switch (type) {
            case INORDER:
                inorder(tree);
                break;
            case PREORDER:
                preorder(tree);
                break;
            case POSTORDER:
                postorder(tree);
                break;
        }
        return baumString.toString();
    }

    private void inorder(BinaryTree<?> tree) {
        if(!tree.isEmpty()) {
            inorder(tree.getLeftTree());
            baumString.append(tree.getContent());
            inorder(tree.getRightTree());
        }
    }

    private void preorder(BinaryTree<?> tree) {
        if(!tree.isEmpty()) {
            baumString.append(tree.getContent());
            preorder(tree.getLeftTree());
            preorder(tree.getRightTree());
        }
    }

    private void postorder(BinaryTree<?> tree) {
        if(!tree.isEmpty()) {
            preorder(tree.getLeftTree());
            preorder(tree.getRightTree());
            baumString.append(tree.getContent());
        }
    }


    public String traversiereBaum(BinarySearchTree<?> tree, Type type) {
        baumString = new StringBuilder();
        switch (type) {
            case INORDER:
                inorder(tree);
                break;
            case PREORDER:
                preorder(tree);
                break;
            case POSTORDER:
                postorder(tree);
                break;
        }
        return baumString.toString();
    }

    private void inorder(BinarySearchTree<?> tree) {
        if(!tree.isEmpty()) {
            inorder(tree.getLeftTree());
            baumString.append(tree.getContent());
            inorder(tree.getRightTree());
        }
    }

    private void preorder(BinarySearchTree<?> tree) {
        if(!tree.isEmpty()) {
            baumString.append(tree.getContent());
            preorder(tree.getLeftTree());
            preorder(tree.getRightTree());
        }
    }

    private void postorder(BinarySearchTree<?> tree) {
        if(!tree.isEmpty()) {
            preorder(tree.getLeftTree());
            preorder(tree.getRightTree());
            baumString.append(tree.getContent());
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

}
