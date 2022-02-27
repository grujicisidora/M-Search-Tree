
public class DotFile {

	public String readSubtreeDot(MSearchTree tree, MNode node, Integer n) {
		String res = "";
		for(Integer i = 0; i < tree.M; i++) {
			
			if (tree.readNode(node.children[i]) == " Empty ") 
				//res = res + "Node " + n.toString() + "." + i.toString() + "." + node.array[0] + ":" + tree.readNode(node.children[i]);
				continue;
			else {
				res += "\"";
				res += tree.readNode(node);
				res += "\"";
				res += "->";
				res += "\"";
				res += tree.readNode(node.children[i]);
				res += "\"";
				res += ";";
			}		
		}
		for(Integer i = 0; i < tree.M; i++) {
			if(node.children[i] == null) continue;
			else res += readSubtreeDot(tree, node.children[i], n+1);
		}
		return res;
	}
	
	public String toDot(MSearchTree tree, MNode node) {
		
		String res = "digraph MSearchTree {";
		res += readSubtreeDot(tree, node, 0);
		res += "}";
		return res;
	}	
}
