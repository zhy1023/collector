package com.zhy.lib_library.arithmetic;

/**
 * @Author ；zhy
 * @ClassName: ListNode 链表
 * @Date : 2020/12/24 17:23
 * @Describe :
 */
public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}
