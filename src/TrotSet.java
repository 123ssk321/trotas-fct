/**
 * @author Sahil Satish Kumar e Tiago Vieira
 * Nesta classe estão armazenadas as trotinetes
 */
public class TrotSet {
	//Variaveis de instancia
	private Trot[] trots;
	private int counter;
	
	//Constantes
	private static final int SIZE = 77, GROWTH_FACTOR = 2;
	
	//Construtores
	
	/**
	 * Cria um novo vetor para armazenar as trotinetes
	 */
	public TrotSet() {
		trots = new Trot[SIZE];
		counter = 0;
	}
	
	//Metodos auxiliares
	private boolean isFull() {
		return counter == trots.length;
	}
	private void resize() {
		Trot[] tmp = new Trot[GROWTH_FACTOR*trots.length];
		for (int i = 0;i<counter;i++)
			tmp[i] = trots[i];
		trots = tmp;
	}
	/**
	 * Pesquisa (binaria) o indice da trotinete com o ID dado
	 * @param id, ID a pesquisar
	 * @return indice da trotinete com o ID dado
	 */
	private int searchIndex(String id) {
		int pos = -1;
		boolean found = false;
		int i = 0;
		while(!found && i<counter) {
			if(trots[i].showIdTrot().equalsIgnoreCase(id)) {
				found = true;
				pos = i;
			}
			else {
				i++;
			}
		}
		return pos;
	}
	
	//Metodos principais
	/**
	 * Adiciona uma trotinete com as informacoes dadas ao vetor
	 * @param id, ID da trotinete a adicionar
	 * @param license, matricula da trotinete a adicionar
	 */
	public void addToSet(String id, String license) {
		Trot tmpTr = new Trot(id, license);
		if(isFull())
			resize();
		trots[counter++] = tmpTr;
	}
	/**
	 * Devolve A trotinete com o ID dado
	 * @param id, ID da Trotinete a devolver
	 * @return Trotinete com o ID dado
	 */
	public Trot getTrot(String id) {
		Trot tmpTr = null;
		if (searchIndex(id)>=0)
			tmpTr = trots[searchIndex(id)];
		return tmpTr;
	}
	/**
	 * Inicia um iterador com o vetor das trotinetes
	 * @return iterador com o vetor de trotinetes existentes
	 */
	public TrotIterator iterator() {
		return new TrotIterator(trots, counter);
	}
	/**
	 * Cria um vetor com as trotinetes que tem localizacao por ordem de distancia ao cliente
	 * Inicia um iterador com o vetor das trotinetes criado acima
	 * @param clientX, longitude do cliente
	 * @param clientY, latitude do cliente
	 * @return iterador com o vetor das trotinetes com localizacao criado
	 */
	public TrotIterator distanceOrdIterartor(double clientX, double clientY) {
		Trot[] tmpTrots = new Trot[trots.length];
		int c = 0;
		for (int i = 0;i<counter;i++) {
			if(trots[i].releasedWithLoc())
				tmpTrots[c++] = trots[i];
		}
		for(int i = 1;i<c;i++) {
			for(int j = c-1;j>=i;j--) {
				if(tmpTrots[j-1].distanceToClient(clientX, clientY) > tmpTrots[j].distanceToClient(clientX, clientY)) {
					Trot tmp = tmpTrots[j-1];
					tmpTrots[j-1] = tmpTrots[j];
					tmpTrots[j] = tmp;				
				}
			}
		}
		return new TrotIterator(tmpTrots,c);
	}
}

