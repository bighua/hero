package blog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

/**
 * 我们利用了最短路径的一个特点：即第二次经过一个节点的时候，路径长度不比第一次经过它时短。因此这样的路径是没有圈的
 * 本题需要记录全部的路径，我们第二次经过一个节点时，路径长度可能会和第一次经过一个节点时路径长度一样。这是因为，我们可能在第i层中有多个节点可以到达第(i + 1)层的同一个位置，这样那个位置有多条路径都是最短路径。
 * 我们记录经过这个位置的前面所有位置的集合。这样一个节点的前驱不是一个节点，而是一个节点的集合。如此，当我们第二次经过一个第(i+ 1)层的位置时，我们便保留前面那第i层位置的集合作为前驱
 * @author Administrator
 *
 */
public class LeastOpTimes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] dict = {"hot","dot","dog","lot","log"};
//		Set<String> dictS = new HashSet<String>();
//		for (String s : dict) {
//			dictS.add(s);
//		}
//		findLadders1("hit", "cog", dictS);
		findLadders("hit", "cog", dict);
	}
	
	public static void findLadders(String start, String end, String[] dict) {
		Map<String , List<String>> allword = new HashMap<String, List<String>>();
		List<String> words = new ArrayList<String>();
		words.add(start);
		words.add(end);
		allword.put(start, new ArrayList<String>());
		allword.put(end, new ArrayList<String>());
		for (int i = 0; i < dict.length; i++) {
			words.add(dict[i]);
			allword.put(dict[i], new ArrayList<String>());
		}
		
		// 建立邻接表
		for (int i = 0; i < words.size(); i++) {
			String w = words.get(i);
			for (int j = 0; j < w.length(); j++) {
				char origin = w.charAt(j);
				char c = origin;
				c++;
				while (c <= 'z') {
					String comp = w.replace(origin, c);
					if (allword.containsKey(comp)) {
						allword.get(w).add(comp);
						allword.get(comp).add(w);
					}
					c++;
				}
			}
		}
		
		// BFS
		List<String> queue = new ArrayList<String>();
		Map<String, Integer> d = new HashMap<String, Integer>();
		// 节点后继集合
		Map<String , List<String>> next = new HashMap<String, List<String>>();
		for (int i = 0; i < words.size(); i++) {
			d.put(words.get(i), -1);
			next.put(words.get(i), new ArrayList<String>());
		}
		queue.add(end);
		d.put(end, 0);
		// 已经到达的遍历层数
		int now = 0;
		while(!queue.isEmpty()) {
			String node = queue.get(0);
			now = d.get(node) + 1;
			// 到达start节点
			if (d.get(start) >=0 && now > d.get(start)) {
				break;
			}
			queue.remove(0);
			List<String> neigbour = allword.get(node);
			for (int i = 0; i < neigbour.size(); i++) {
				String nei = neigbour.get(i);
				// first time pass
				if (d.get(nei) == -1) {
					queue.add(nei);
					// 路径距离
					d.put(nei, now);
					// 后继
					next.get(nei).add(node);
				// 同一层的兄弟节点已经经过，也是自己的路径 	！！！不能写else,会把所有邻接节点都加为后继
				} else if (d.get(nei) == now) {
					// 后继
					next.get(nei).add(node);
				}
			}
		}
		if (d.get(start) >= 0) {
			List<String> path = new ArrayList<String>();
			getPath(start, d, next, path);
		}
		
	}
	
	public static void getPath(String from, Map<String, Integer> d, Map<String , List<String>> next, List<String> path) {
		path.add(from);
		if (d.get(from) == 0) {
			printPath(path);
		} else {
			List<String> ns = next.get(from);
			for (int i = 0; i < ns.size(); i++) {
				getPath(ns.get(i), d, next, path);
			}
		}
		path.remove(path.size() - 1);
	}
	
	public static void printPath(List<String> path) {
		for (String s: path) System.out.print(s + "->");
		System.out.println();
	}

//	public static void getPath1(String from, Map<String, Integer> d, Map<String , List<String>> next, Vector<String> path, Vector<Vector<String>> res) {
//		path.add(from);
//		if (d.get(from) == 0) {
//			res.add((Vector<String>)path.clone());
//		} else {
//			List<String> ns = next.get(from);
//			for (int i = 0; i < ns.size(); i++) {
//				getPath1(ns.get(i), d, next, path, res);
//			}
//		}
//		path.remove(path.size() - 1);
//	}
//	public static Vector<Vector<String>> findLadders1(String start, String end, Set<String> dict) {
//		Map<String , List<String>> allword = new HashMap<String, List<String>>();
//		List<String> words = new ArrayList<String>();
//		words.add(start);
//		words.add(end);
//		allword.put(start, new ArrayList<String>());
//		allword.put(end, new ArrayList<String>());
//		Iterator<String> is = dict.iterator();
//		while (is.hasNext()) {
//			String w = is.next();
//			words.add(w);
//			allword.put(w, new ArrayList<String>());
//		}
//		
//		// 建立邻接表
//		for (int i = 0; i < words.size(); i++) {
//			String w = words.get(i);
//			for (int j = 0; j < w.length(); j++) {
//				char origin = w.charAt(j);
//				char c = origin;
//				c++;
//				while (c <= 'z') {
//					String comp = w.replace(origin, c);
//					if (allword.containsKey(comp)) {
//						allword.get(w).add(comp);
//						allword.get(comp).add(w);
//					}
//					c++;
//				}
//			}
//		}
//		
//		// BFS
//		Vector<Vector<String>> res = new Vector<>();
//		List<String> queue = new ArrayList<String>();
//		Map<String, Integer> d = new HashMap<String, Integer>();
//		// 节点后继集合
//		Map<String , List<String>> next = new HashMap<String, List<String>>();
//		for (int i = 0; i < words.size(); i++) {
//			d.put(words.get(i), -1);
//			next.put(words.get(i), new ArrayList<String>());
//		}
//		queue.add(end);
//		d.put(end, 0);
//		int now = 0;
//		while(!queue.isEmpty()) {
//			String node = queue.get(0);
//			now = d.get(node) + 1;
//			// 到达start节点
//			if (d.get(start) >=0 && now > d.get(start)) {
//				break;
//			}
//			queue.remove(0);
//			List<String> neigbour = allword.get(node);
//			for (int i = 0; i < neigbour.size(); i++) {
//				String nei = neigbour.get(i);
//				// first time pass
//				if (d.get(nei) == -1) {
//					queue.add(nei);
//					// 路径距离
//					d.put(nei, now);
//					// 后继
//					next.get(nei).add(node);
//				// 是从上一层经过的，所以要保存
//				} else if (d.get(nei) == now) {
//					// 后继
//					next.get(nei).add(node);
//				}
//			}
//		}
//		if (d.get(start) >= 0) {
//			Vector<String> path = new Vector<String>();
//			getPath1(start, d, next, path, res);
//		}
//		return res;
//		
//	}

}
