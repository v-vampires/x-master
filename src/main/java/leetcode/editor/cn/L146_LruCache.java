package leetcode.editor.cn;
//运用你所掌握的数据结构，设计和实现一个 LRU (最近最少使用) 缓存机制 。 
//
// 
// 
// 实现 LRUCache 类： 
//
// 
// LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存 
// int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。 
// void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上
//限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。 
// 
//
// 
// 
// 
//
// 进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作？ 
//
// 
//
// 示例： 
//
// 
//输入
//["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
//[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
//输出
//[null, null, null, 1, null, -1, null, -1, 3, 4]
//
//解释
//LRUCache lRUCache = new LRUCache(2);
//lRUCache.put(1, 1); // 缓存是 {1=1}
//lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
//lRUCache.get(1);    // 返回 1
//lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
//lRUCache.get(2);    // 返回 -1 (未找到)
//lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
//lRUCache.get(1);    // 返回 -1 (未找到)
//lRUCache.get(3);    // 返回 3
//lRUCache.get(4);    // 返回 4
// 
//
// 
//
// 提示： 
//
// 
// 1 <= capacity <= 3000 
// 0 <= key <= 3000 
// 0 <= value <= 104 
// 最多调用 3 * 104 次 get 和 put 
// 
// Related Topics 设计 
// 👍 1399 👎 0

import java.util.HashMap;
import java.util.Map;

/**
 * lru实现思路：双向链表+hashMap
 * 数据结构：Node， NodeList
 */
public class L146_LruCache{
  public static void main(String[] args) {
      new L146_LruCache().test();

  }

  public void test(){
      final LRUCache lruCache = new LRUCache(1);
      lruCache.put(2, 1);

      System.out.println(lruCache.get(2));
  }
  //leetcode submit region begin(Prohibit modification and deletion)
class LRUCache {

      private Map<Integer, Node> nodeMap;
      private NodeList nodeList;
      private int capacity;

      public LRUCache(int capacity) {
          this.nodeMap = new HashMap<>(capacity);
          this.nodeList = new NodeList();
          this.capacity = capacity;
      }

      public int get(int key) {
          if(nodeMap.containsKey(key)){
              final Node node = nodeMap.get(key);
              put(key, node.value);
              return node.value;
          }
          return -1;
      }

      public void put(int key, int value) {
          Node node = new Node(key, value);
          if(nodeMap.containsKey(key)){
              nodeList.remove(nodeMap.get(key));
              nodeList.addLast(node);
              nodeMap.put(key, node);
          }else{
              if(nodeMap.size() == capacity){
                  Node removeNode = nodeList.removeFirst();
                  nodeMap.remove(removeNode.key);
              }
              nodeMap.put(key, node);
              nodeList.addLast(node);
          }
      }


    class Node{
        private int key;
        private int value;
        private Node pre;
        private Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    class NodeList{
        private Node head = null;
        private Node tail = null;

        public Node removeFirst() {
            Node n = head;
            remove(n);
            return n;
        }

        public void addLast(Node node) {
            if(head == null){
                head = tail = node;
            }else{
                node.pre = tail;
                tail.next = node;
                tail = node;
            }
        }

        public void remove(Node node) {
            if(node == head && node == tail){
                head = null;
                tail = null;
            }else if(node == head) {
                final Node next = node.next;
                next.pre = null;
                node.next = null;
                head = next;
            }else if(node == tail){
                final Node pre = node.pre;
                pre.next = null;
                node.pre = null;
                tail = pre;
            }else{
                final Node pre = node.pre;
                final Node next = node.next;
                pre.next = next;
                next.pre = pre;
                node.next = null;
            }
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//leetcode submit region end(Prohibit modification and deletion)

}