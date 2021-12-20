package de.noah.infoha.automatentheorie.binary;

import de.noah.infoha.abiturklassen.BinarySearchTree;
import de.noah.infoha.abiturklassen.BinaryTree;

import java.util.regex.Pattern;

public class Traversierungen {

    public enum Type {
        INORDER,
        POSTORDER,
        PREORDER;
    }

    String baumString;

    public Traversierungen() {
        baumString = "";




    }


    public void traversiereBaum(Type pOrder, BinaryTree<AkzeptatorResponse> tree) {
        baumString = "";
        switch (pOrder) {
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

    }

    public void traversiereBaum(Type pOrder, BinarySearchTree<AkzeptatorResponse> tree) {
        baumString = "";
        switch (pOrder) {
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
    }

    public void preorder(BinaryTree<?> pBaum) {
        if (!pBaum.isEmpty()) {
            baumString = baumString + pBaum.getContent();
            preorder(pBaum.getLeftTree());
            preorder(pBaum.getRightTree());
        } // end of if
    }

    public void inorder(BinaryTree<?> pBaum) {
        if (!pBaum.isEmpty()) {
            inorder(pBaum.getLeftTree());
            baumString = baumString + pBaum.getContent();
            inorder(pBaum.getRightTree());
        }
    }

    public void postorder(BinaryTree<AkzeptatorResponse> pBaum) {
        if (!pBaum.isEmpty()) {
            postorder(pBaum.getLeftTree());
            postorder(pBaum.getRightTree());
            baumString = baumString + pBaum.getContent();
        }
    }

    public void preorder(BinarySearchTree<?> pBaum) {
        if (!pBaum.isEmpty()) {
            baumString = baumString + pBaum.getContent();
            preorder(pBaum.getLeftTree());
            preorder(pBaum.getRightTree());
        } // end of if
    }

    public void inorder(BinarySearchTree<?> pBaum) {
        if (!pBaum.isEmpty()) {
            inorder(pBaum.getLeftTree());
            baumString = baumString + pBaum.getContent();
            inorder(pBaum.getRightTree());
        }
    }

    public void postorder(BinarySearchTree<?> pBaum) {
        if (!pBaum.isEmpty()) {
            postorder(pBaum.getLeftTree());
            postorder(pBaum.getRightTree());
            baumString = baumString + pBaum.getContent();
        }
    }

    public void fuegeEin(BinaryTree<AkzeptatorResponse> tree, AkzeptatorResponse res) {
        if(tree.isEmpty()) {
            tree.setContent(res);
        } else {
            final BinaryTree<AkzeptatorResponse> newTree = new BinaryTree<>();
            newTree.setContent(res);
            if(res.isGreater(tree.getContent())) {
                if(tree.getRightTree() == null) {
                    tree.setRightTree(newTree);
                } else fuegeEin(tree.getRightTree(), res);
            } else if(res.isLess(tree.getContent()) || res.isEqual(tree.getContent())) {
                if(tree.getLeftTree() == null) {
                    tree.setLeftTree(newTree);
                } else fuegeEin(tree.getLeftTree(), res);

            }
        }
    }


    public String getBaumString() {
        return baumString;
    }

}