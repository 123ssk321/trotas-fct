/**
 * @author Sahil Satish Kumar e Tiago Vieira
 * Nesta classe estao armazenados todos os clientes
 */
public class ClientSet {
	//Variaveis de instancia
	private Client[] clients;
	private int counter;
	
	//Constantes
	private static final int SIZE = 77, GROWTH_FACTOR = 2;
	
	//Construtores
	public ClientSet() {
		clients = new Client[SIZE];
	}
	
	//Metodos auxiliares
	
	private boolean isFull() {
		return counter == clients.length;
	}
	private void resize() {
		Client[] tmp = new Client[GROWTH_FACTOR*clients.length];
		for (int i = 0;i<counter;i++)
			tmp[i] = clients[i];
		clients = tmp;
	}
	private void insertAt(Client cl, int pos) {
		if (isFull())
			resize();
		for(int i = counter-1;i>=pos;i--)
			clients[i+1] = clients[i];
		clients[pos] = cl;
		counter++;
	}
	/**
	 * Pesquisa (binaria) o indice do cliente com o NIF dado
	 * @param nif, NIF a pesquisar
	 * @return indice do cliente com o NIF dado
	 */
	private int searchIndex(String nif) {
		int pos = -1;
		boolean found = false;
		int low = 0;
		int high = counter-1;
		while(!found && low<=high) {
			int mid = (low + high)/2;
			if(clients[mid].showNif().equalsIgnoreCase(nif)) {
				found = true;
				pos = mid;
			}
			else {
				if(clients[mid].greaterThan(nif))
					high = mid-1;
				else
					low = mid+1;
			}
		}
		return pos;
	}
	
	//Metodos principais
	
	/**
	 * Adiciona um cliente, indicando o indice onde deve ser inserido de forma a manter o vetor por ordem crescente de NIF
	 * @param nif, NIF do cliente a ser criado
	 * @param email, email do cliente a ser criado
	 * @param phoneNum, telefone do cliente a ser criado
	 * @param name, nome do cliente a ser a criado
	 */
	public void addToSet(String nif, String email, String phoneNum, String name) {
		int pos = -1;
		int i = 0;
		Client tmpCl = new Client(nif, email, phoneNum, name);
		if(isFull())
			resize();
		while(i<counter && pos==-1) {
			if(clients[i].greaterThan(nif))
				pos = i;
			else
				i++;
		}
		if(pos==-1) 
			pos = counter;
		insertAt(tmpCl, pos);
	}
	/**
	 * Remove o cliente com o NIF dado
	 * @param nif, NIF do cliente a remover
	 */
	public void remFromSet(String nif) {
		for(int i = searchIndex(nif); i < counter-1;i++)
			clients[i] = clients[i+1];
		counter--;
	}
	/**
	 * Devolve o cliente com o NIF dado
	 * @param nif, NIF do cliente a devolver
	 * @return cliente com o NIF dado
	 */
	public Client getClient(String nif) {
		Client tmpCl = null;
		if (searchIndex(nif)>=0)
			tmpCl = clients[searchIndex(nif)];
		return tmpCl;
	}
	/**
	 * Cria um vetor com os clientes com saldo negativo, ordenados por ordem crescente de saldo 
	 * e inicia um iterador com esse vetor
	 * @return iterador com o vetor dos clientes com saldo negativo criado
	 */
	public ClientIterator debtClientsOrd() {
		Client[] tmpClients = new Client[clients.length];
		int c = 0;
		for (int i = 0;i<counter;i++) {
			if(clients[i].showBalance()<0)
				tmpClients[c++] = clients[i];
		}
		for(int i = 1;i<c;i++) {
			for(int j = c-1;j>=i;j--) {
				if(tmpClients[j-1].showBalance() > tmpClients[j].showBalance()) {
					Client tmp = tmpClients[j-1];
					tmpClients[j-1] = tmpClients[j];
					tmpClients[j] = tmp;				
				}
			}
		}
		return new ClientIterator(tmpClients,c);
	}
	/**
	 * Inicia um iterador com o vetor dos Clientes
	 * @return iterador com o vetor de clientes existentes
	 */
	public ClientIterator iterator() {
		return new ClientIterator(clients,counter);
	}
}
