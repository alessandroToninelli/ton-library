package com.toninelli.category


class TonCategoryTree constructor(private val list: List<TonCategory>) {

    data class Node(
        val data: TonCategory,
        val parent: Node?,
        var leftChild: Node?,
        var rightSibling: Node?
    )

    private val root = Node(TonCategory("5", "00000000","root", "0",0), null, null, null)

    init {
        populateTree(root,list.toMutableList())
    }

    private fun populateTree(node: Node, mutableCategories: MutableList<TonCategory>) {
        val nodeId = node.data.id
        val leftChild = mutableCategories.firstOrNull { it.parent == nodeId }
        val rightSibling = mutableCategories.firstOrNull { it.parent == node.parent?.data?.id }
        leftChild?.let {
            mutableCategories.remove(it)
            val newNode = Node(it, node, null, null)
            node.leftChild = newNode
            populateTree(newNode, mutableCategories)
        }
        rightSibling?.let {
            mutableCategories.remove(it)
            val newNode = Node(it, node.parent, null, null)
            node.rightSibling = newNode
            populateTree(newNode, mutableCategories)
        }
    }

    fun getSubCateg(id: String): List<TonCategory> {

        val node = searchNode(id, root)
        val list = mutableListOf<TonCategory>()
        node?.let {
            levelorderTraversal(it.leftChild, list)
        }

        return list
    }

    fun getAllSubCateg(id: String, includeParent: Boolean = false): List<TonCategory> {

        val node = searchNode(id, root)
        val list = mutableListOf<TonCategory>()
        node?.let {
            if (includeParent) list.add(it.data)
            preorderTraversal(it.leftChild, list)
        }
        return list
    }


    private fun preorderTraversal(node: Node?, cat: MutableList<TonCategory>) {
        node?.let {
            cat.add(it.data)
            preorderTraversal(it.leftChild, cat)
            preorderTraversal(it.rightSibling, cat)
        }
    }

    private fun levelorderTraversal(node: Node?, cat: MutableList<TonCategory>) {
        node?.let {
            cat.add(it.data)
            levelorderTraversal(it.rightSibling, cat)
        }
    }

    private fun searchNode(id: String, node: Node?): Node? {
        if (node == null || node.data.id == id)
            return node

        val leftChild = searchNode(id, node.leftChild)
        if (leftChild != null)
            return leftChild

        return searchNode(id, node.rightSibling)
    }


}