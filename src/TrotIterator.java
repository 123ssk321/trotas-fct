/**
 * @author Sahil Satish Kumar e Tiago Vieira
 *Recebe um vetor do tipo Trot e executa as operacoes de iteracao
 */

public class TrotIterator {
	//Variaveis de instancia
	private Trot[] trots;
	private int counter, nextTrot;
	
	//Construtor
	/**
	 * Recebe as informacoes necessarias para iteracao
	 * @param tr, vetor de trotinetes a iterar
	 * @param counter, primeira posicao livre do vetor a iterar
	 */
	public TrotIterator(Trot[] tr, int counter) {
		trots = tr;
		this.counter = counter;
		nextTrot = 0;
	}
	
	//Metodos
	/**
	 * Verifica a existencia de uma trotinete na posicao seguinte do vetor 
	 * @return se existe uma trotinete na posicao seguinte
	 */
	public boolean hasNext() {
		return nextTrot < counter;
	}
	/**
	 * Passa a proxima trotinete do vetor depois de devolver a trotinete da posicao atual
	 * @return o cliente da posicao atual
	 */
	public Trot next() {
		return trots[nextTrot++];
	}
}
