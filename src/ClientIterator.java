/**
 * @author Sahil Satish Kumar e Tiago Vieira
 * Recebe um vetor do tipo Client e executa as operacoes de iteracao
 */
public class ClientIterator {
	//Variaveis de instancia
	private Client[] clients;
	private int counter, nextClient;
	
	//Construtor
	/**
	 * Recebe as informacoes necessarias para iteracao
	 * @param cl, vetor de clientes a iterar
	 * @param counter, primeira posicao livre do vetor a iterar
	 */
	public ClientIterator(Client[] cl, int counter) {
		clients = cl;
		this.counter = counter;
		nextClient = 0;
	}
	//Metodos
	/**
	 * Verifica a existencia de um cliente na posicao seguinte do vetor 
	 * @return se existe um cliente na posicao seguinte
	 */
	public boolean hasNext() {
		return nextClient < counter;
	}
	/**
	 * Passa ao proximo cliente do vetor depois de devolver o cliente da posicao atual
	 * @return o cliente da posicao atual
	 */
	public Client next() {
		return clients[nextClient++];
	}
}
