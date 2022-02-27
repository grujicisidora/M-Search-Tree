import javax.swing.*;

public class MNode {
	
	int max; //polje koje odredjuje maksimalan broj kljuceva u cvoru
	Integer pos; //pomocno polje koje cuva trenutnu poziciju u cvoru - za lakse manipulisanje cvorom
	Integer[] array; //vrednosti kljuceva u cvoru
	MNode[] children; // niz dece odredjene instance objekta MNode, koja su po tipu takodje MNode
	
	//konstruktor klase MNode, koji kao parametar prima red stabla
	public MNode(int M) {
		
		max = M-1; 
		
		pos = null; //inicijalizujemo poziciju na nulu
		array = new Integer[max]; //alociramo memoriju za vrednosti kljuceva
		children = new MNode[M]; //alociramo memoriju za decu cvora
		
		for(int i = 0; i < max; i++) array[i] = null;
		for(int i = 0; i < M; i++) children[i] = null;
	}
	
	//pomocna metoda za ispitivanje da li je cvor prazan
	int isEmpty() {
		
		 if (this.array[0] == null) return 1;
		 else return 0;
	}
	
	//pomocna metoda za ispitivanje da li je cvor pun
	int isFull() {
		
		if (array[max-1] != null) return 1;
		else return 0;
	}
	
	//pomocna metoda za odredjivanje koliko cvor ima kljuceva
	int getKeyCnt() {
		if (this.isEmpty() == 1) return 0;
		else if(this.isFull() == 1) return max;
		else {
			int keyCnt = 0;
			for(int i = 0; i < max; i++)
				if(array[i] != null) keyCnt++;
			return keyCnt;
		}	
	}
	
	//pomocna metoda za pronalazenje najmanjeg broja u stablu
	int getMinNum() throws EmptyNodeException {
		if (this.isEmpty() == 1) throw new EmptyNodeException("The node you're trying to access is empty.");
		else return array[0];
	}
	
	//pomocna metoda za pronalazenje najveceg broja u stablu
	int getMaxNum() throws EmptyNodeException {
		if (this.isEmpty() == 1) throw new EmptyNodeException("The node you're trying to access is empty.");
		else {
			int keyCnt = this.getKeyCnt();
			return array[keyCnt - 1];
		}
	}
	
	//metoda za pretrazivanje cvora prema zadatoj vrednosti, vraca 1 ako je cvor nadjen i 0 ako nije
	int searchNode(int val) {
		int minNum;
		int maxNum;
		
		int keyCnt = this.getKeyCnt();
		try {
			minNum = this.getMinNum();
			maxNum = this.getMaxNum();
			if(val < minNum) {
				pos = 0;
				return 0; //ako je trazena vrednost manja od najmanjeg broja u cvoru, onda se sigurno ne nalazi u tom cvoru
			}
			else if (val > maxNum) {
				pos = keyCnt;
				return 0; //ako je trazena vrednost veca od najveceg broja u cvoru, onda se broj ne nalazi u njemu
			}
			else {
				int indicator = 0;
				for(int i = 0; i < keyCnt; i++) {
					pos = i; 
					if (array[i] == val) {
						indicator = 1;
						break;
					}
				}
				return indicator;
			}
		}
		catch(EmptyNodeException e) {
			return 0;
		}		
	}
	
	
	//metoda za pronalazenje levog pod-stabla u odnosu na zadatu vrednost, znajuci da ta vrednost postoji u cvoru
	MNode getLeftSubtree(int val) throws KeyNotFoundException {
		int indicator = this.searchNode(val);
		//ako je kljuc pronadjen
		if(indicator == 1)
			return children[pos];
		else throw new KeyNotFoundException ("The value you're looking for in this node doesn't exist."); //ako kljuc nije pronadjen u cvoru
	}
	
	
	//metoda za pronalazenje desnog pod-stabla u odnosu na zadatu vrednost
	MNode getRightSubtree(int val) throws KeyNotFoundException {
		int indicator = this.searchNode(val);
		//ako je kljuc pronadjen
		if(indicator == 1)
			return children[pos + 1];
		else throw new KeyNotFoundException ("The value you're looking for in this node doesn't exist."); //ako kljuc nije pronadjen u cvoru
	}
	
	
	//metoda za ubacivanje vrednosti u cvor kada vec znamo da je to odgovarajuci cvor i da ima mesta da se u njem smesti odgovarajuca vrednost
	void setValue(int val) {
		if(isEmpty() == 1) array[0] = val;
		else merge(val);
	}
	
	
	//pomocna metoda za deljenje niza po nekoj vrednosti
	//vraca broj koji predstavlja koliko brojeva imamo u "levom" nizu
	int split(int val) {
		int i;
		int keyCnt = getKeyCnt();
		if (val < array[0]) return 0;
		else if (val > array[keyCnt - 1]) return keyCnt;
		else {
			for (i = 0; i < keyCnt - 1; i++)
				if (val > array[i] && val < array[i + 1]) break;
			int x = i + 1;
			return x;
		}
	}
	
	
	//metoda koja spaja dva niza u jedan i dodaje novu vrednost kljuca
	void merge(int val) {
		Integer[] temp = new Integer[max]; //privremeni niz za kljuceve
		MNode [] chTemp = new MNode[max + 1]; //privremeni niz za decu
		int left = split(val); //broj kljuceva u "levom" nizu
		int keyCnt = getKeyCnt();
		if(left == 0) {
			temp[0] = val;
			chTemp[0] = null;
			for(int i = 1; i < keyCnt + 1; i++) {
				temp[i] = array[i - 1];
				chTemp[i] = children[i - 1];
			}
			array = temp;
			children = chTemp;
		}
		else if(left == keyCnt) 
			array[keyCnt] = val;
		else {
			for(int i = 0; i < left; i++) {
				temp[i] = array[i];
				chTemp[i] = children[i];
			}
			temp[left] = val;
			chTemp[left] = null;
			for(int i = left + 1; i < keyCnt + 1; i++) temp[i] = array[i - 1];
			for(int i = left + 1; i < max + 1; i++) chTemp[i] = children[i - 1];
			array = temp;
			children = chTemp;
		}	
	}
	
	
	//metoda za brisanje kljuca iz cvora, ako znamo da se kljuc nalazi u tom cvoru
	void del(int val) {
		try {
			MNode n = getLeftSubtree(val);
			MNode m = getRightSubtree(val);
			if(n == null && m == null) {
				array[pos] = null;
				this.shiftLeft();
			}
			else if(n == null && m!= null) { 
				if(m.isEmpty() == 1) {
					children[pos + 1] = null;
					del(val);
				}
			}
			else if(n != null && m == null) { 
				if(n.isEmpty() == 1) {
					children[pos] = null;
					del(val);
				}
				else {
					int b = children[pos].getKeyCnt();
					int a = children[pos].array[b - 1];
					array[pos] = a;
					children[pos].del(a);
				}
			}
			else {
				if(n.isEmpty() == 1) {
					children[pos] = null;
					del(val);
				}
				else if(m.isEmpty() == 1) {
					children[pos + 1] = null;
					del(val);
				}
				else {
					int a = children[pos + 1].array[0];
					array[pos] = a;
					children[pos + 1].del(a);
				}
			}
		}
		catch(KeyNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}	
	}
	
	
	//pomocna metoda za pomeranje kljuceva ulevo, samo ako su svi odgovarajuci uslovi ispunjeni (da left i right subtree budu null)
	void shiftLeft() {
		if(isFull() == 1) {
			int i;
			Integer[] temp = new Integer[max];
			MNode[] chTemp = new MNode[max + 1];
			for(i = 0; i < max; i++) {
				if(array[i] != null) {
					temp[i] = array[i];
					chTemp[i] = children[i];
				}
				else break;
			}
			if(i == max - 1) {
				temp[i] = null;
				chTemp[i + 1] = null;
			}
			else {
				for(int j = i; j < max - 1; j++) {
					temp[j] = array[j + 1];
					chTemp[j] = children[j + 1];
				}
				temp[max - 1] = null;
				chTemp[max - 1] = children[max];
				chTemp[max] = null;
			}
			array = temp;
			children = chTemp;
		}
	}
	
	
}
