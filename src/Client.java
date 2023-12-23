/**
 * Um objeto desta classe representa um cliente.
 * @author Sahil Satish Kumar e Tiago Vieira
 * Esta classe manipula as informacoes relativas ao cliente, como por exemplo a atualizacao 
 * das variaveis de instancia
 */
public class Client {
	
	//Variaveis de instancia
	private String nif, email, phoneNumber, clientName;
	private boolean rentingTrot;
	private int numberOfRents, balance, totalCents, totalMinutes, maxMinutes, medMinutes;
	private Trot trot;
	
	//Constantes
	private static final int INITIAL_BALANCE = 200;
	
	//Construtores
	
	/**
	 * Cria um novo cliente com as informacoes dadas 
	 * @param nif, NIF do cliente
	 * @param email, email do cliente
	 * @param phoneNum, numero de telefone do cliente
	 * @param name, nome do cliente
	 * Pre: nif!="" && email!="" && phoneNum!="" && name!=""
	 */
	public Client(String nif, String email, String phoneNum, String name) {
		this.nif = nif;
		this.email = email;
		phoneNumber = phoneNum;
		clientName = name;
		rentingTrot = false;
		balance = INITIAL_BALANCE;
		totalCents = 0;
		numberOfRents = 0;
		totalMinutes = 0;
		maxMinutes = 0;
		medMinutes = 0;
	}
	/**
	 * Igual ao construtor Client(String, String, String, String)
	 * E usado em metodos onde se torna mais simples criar um cliente temporario a partir das informacoes do cliente existente,
	 * chamado por outro metodo, que queremos utilizar
	 * @param c, cliente existente
	 * Pre: c!= null
	 */
	public Client(Client c) {
		nif = c.showNif();
		email = c.showEmail();
		phoneNumber = c.showPhoneNumber();
		clientName = c.showName();
		rentingTrot = false;
		balance = c.showBalance();
		totalCents = c.showTotalSpendCents();
		numberOfRents = c.showTotalRents();
		totalMinutes = c.showTotalSpendMinutes();
		maxMinutes = c.showMaxMinutes();
		medMinutes = c.showMedMinutes();	
	}
	
	//Metodos modificadores
	
	/**
	 * Inicia o aluguer de uma trotinete, atualizando o estado do cliente
	 * @param t, trotinete que foi alugada
	 * t != null
	 */
	public void rentBegin(Trot t) {
		rentingTrot = true;
		trot = t;
	}
	/**
	 * Acaba o aluguer da trotinete, atualizando o estado e informacoes do cliente
	 * @param price, preco do aluguer em centimos
	 * @param minutes, tempo de aluguer em minutos
	 * Pre: price>0 && minutes>0
	 */
	public void rentEnd(int price,int minutes) {
		trot = null;
		rentingTrot = false;
		balance-=price;
		totalCents+=price;
		totalMinutes+=minutes;
		numberOfRents++;
		if (numberOfRents == 1)
			maxMinutes = minutes;
		else 
			if (numberOfRents > 1 && minutes > maxMinutes)
				maxMinutes = minutes;
	}
	/**
	 * Carrega o saldo do cliente
	 * @param cents, valor a carregar em centimos
	 * Pre: cents>0
	 */
	public void addBalance(int cents) {
		balance+=cents;
	}
	/**
	 * Verifica se o NIF do cliente e maior do que o NIF dado
	 * @param nif, NIF a comparar com o NIF do cliente
	 * @return se o NIF do cliente e maior do que o nif dado
	 * Pre: nif !=null
	 */
	public boolean greaterThan(String nif) {
		return this.showNif().compareToIgnoreCase(nif) > 0; 
	}
	
	//Metodos de consulta
	public String showNif() {
		return nif;
	}
	public String showEmail() {
		return email;
	}
	public String showPhoneNumber() {
		return phoneNumber;
	}
	public String showName() {
		return clientName;
	}
	public boolean isClientRenting() {
		return rentingTrot;
	}
	public int showBalance() {
		return balance;
	}
	public int showTotalSpendCents() {
		return totalCents;
	}
	public int showTotalRents() {
		return numberOfRents;
	}
	public int showTotalSpendMinutes() {
		return totalMinutes;
	}
	public int showMaxMinutes() {
		return maxMinutes;
	}
	/**
	 * Calcula o tempo medio dos alugueres efetuados pelo cliente
	 * @return tempo medio dos alugueres efetuados pelo cliente em minutos
	 */
	public int showMedMinutes() {
		if(numberOfRents>0)
			medMinutes = totalMinutes/numberOfRents;
		return medMinutes;
	}
	/**
	 * @return Trotinete atualmente alugada pelo cliente
	 * Pre: isClientRenting() = true
	 */
	public Trot showTrot() {
		return trot;
	}
}
