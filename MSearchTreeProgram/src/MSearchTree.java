import javax.swing.*;

public class MSearchTree {
	
	int M; //polje koje odredjuje red stabla
	MNode root; // polje koje predstavlja koren stabla, objekat klase MNode
	
	//konstruktor klase MSearchTree, poziva se pri unosu reda stabla, prilikom startovanja programa
	public MSearchTree(int M) {
		this.M = M;
		root = new MNode(M);
	}
	
	

	MNode getNode(int val, MNode n) {
		if(n == null) return n; //ako pocetni cvor ne postoji, ne mozemo naci vrednost, ali vracamo n
		else {
			int indicator = n.searchNode(val); //ako pocetni cvor postoji, gledamo da li u njemu mozemo da nadjemo zadatu vrednost
			if(indicator == 1) return n; //ako pronadjemo vrednost u cvoru, onda njega i vracamo
			//ako ne nadjemo zadatu vrednost u tom cvoru, nalazimo sledeci cvor koji pretrazujemo
			//zato je ovo rekurzivna funkcija
			else {
				int keyCnt = n.getKeyCnt();
				if (keyCnt == 0)
					return n; //ako se slucajno desi da pretrazujemo prazan cvor, vracamo taj cvor
				try {
					if(val < n.array[0]) {
						if(n.getLeftSubtree(n.array[0]) == null) return n;
						else return getNode(val, n.getLeftSubtree(n.array[0]));
					}
					else if(val > n.array[keyCnt - 1]) {
						if(n.getRightSubtree(n.array[keyCnt - 1]) == null) return n;
						else return getNode(val, n.getRightSubtree(n.array[keyCnt - 1]));
					}
					else {
						int i;
						for(i = 0; i < keyCnt - 1; i++)
							if(val > n.array[i] && val < n.array[i + 1]) break;
						if(n.getRightSubtree(n.array[i]) == null) return n;
						else return getNode(val, n.getRightSubtree(n.array[i]));
					}
				}
				catch(KeyNotFoundException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					return null;
				}
				
			}
		}
	}
	
	
	//metoda za pretrazivanje stabla, vraca 1 ako je vrednost nadjena, odnosno 0 ako nije
	int search(int val) {
		if(root.isEmpty() == 1) {
			return 2;
		}
		int x = getNode(val, root).searchNode(val);
		if(x == 1) return 1;
		else return 0;
	}
	
	
	//metoda za dodavanje kljuca u stablo
	int insert(int val) {
		if(root == null) {
			return 0;
		}
		else {
			int x = search(val);
			if(x == 1) {
				JOptionPane.showMessageDialog(null, "This key value already exists.");
				return 0;
			}
			else {
				MNode n = getNode(val, root);
				if(n.isFull() == 1) {
					int y = n.split(val);
					if(n.children[y] == null) {
						getNode(val, root).children[y] = new MNode(M);
					}
					getNode(val, root).setValue(val);
				}
				else getNode(val, root).setValue(val);
				return 1;
			}
		}
	}
	
	
	//metoda za brisanje kljuca iz stabla
	int deleteKey(int val) {
		if(root.isEmpty() == 1) {
			JOptionPane.showMessageDialog(null, "Your tree is empty!"); 
			return 0;
		} 
		else {
			int x = search(val);
			if(x == 1) {
				getNode(val, root).del(val);
				return 1;
			}
			else {
				JOptionPane.showMessageDialog(null, "This key value doesn't exist in your tree."); 
				return 0;
			} 
		}
	}
	
	String readNode(MNode node) /*throws NonExistingNodeException*/ {
		if(node == null) return " Empty ";
			//throw new NonExistingNodeException("Add some more key values so you can see the structure.");
		else {
			String res = "";
			for (int i = 0; i < node.max; i++) {
				if (node.array[i] == null) res += "__ ";
				else {
					res += node.array[i].toString();
					res += "   ";
				}
			}
			return res;	
		}	
	}	
	
	String readTree(MNode start) {
		//try {
			String res = "";
			res += readNode(start);
			res += "  >>>  ";
			for (Integer i = 0; i < start.max + 1; i++) {
				res += readNode(start.children[i]);
				res += "   |   ";
			}
		    return res;
		//}
		//catch(NonExistingNodeException e) {
			//return e.getMessage();
		//}
		
	}

}
