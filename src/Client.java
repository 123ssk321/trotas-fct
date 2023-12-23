/*
 * @author Sahil Satish Kumar
 * Esta classe manipula as informacoes relativas ao cliente, como por exemplo a atualizacao 
 * das variaveis de instancia
 */
public class Client {
	
	//Variaveis de instancia
	private String nif, email, phoneNumber, clientName, trotId, trotLicense;
	private boolean rentingTrot;
	private int numberOfRents, balance, totalCents, totalMinutes, maxMinutes, medMinutes;
	
	//Construtores
	
	/* Pre: nif!="" && email!="" && phoneNum!="" && name!="" */
	public Client(String nif, String email, String phoneNum, String name) {
		this.nif = nif;
		this.email = email;
		phoneNumber = phoneNum;
		clientName = name;
		trotId = "";
		trotLicense = "";
		rentingTrot = false;
		balance = 200;
		totalCents = 0;
		numberOfRents = 0;
		totalMinutes = 0;
		maxMinutes = 0;
		medMinutes = 0;
	}
	/*Pre: c!=null*/
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
	
	/*
	 * Inicia o aluguer de uma trotinete, atualizando o estado do cliente
	 * Guarda o id e a matricula da trotinete
	 * Pre: id!="" && license!=""
	 */
	public void rentBegin(String id, String license) {
		rentingTrot = true;
		trotId = id;
		trotLicense = license;
	}
	/*
	 * Acaba o aluguer da trotinete, atualizando o estado do cliente
	 * Atualiza o saldo do cliente e o numero maximo de minutos de um aluguer
	 * Vai acumulando o dinheiro gasto pelo cliente (totalCents), o total de minutos de aluguer
	 * e numero de alugueres efetuado pelo cliente
	 * Pre: price>0 && minutes>0
	 */
	public void rentEnd(int price,int minutes) {
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
	/*
	 * Adiciona dinheiro ao saldo do cliente
	 * Pre: cents>0
	 */
	public void addBalance(int cents) {
		balance+=cents;
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
	public String showUsingTrotId() {
		return trotId;				
	}
	public String showUsingTrotLicense() {
		return trotLicense;
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
	public int showMedMinutes() {
		if(numberOfRents>0)
			medMinutes = totalMinutes/numberOfRents;
		return medMinutes;
	}
	
}
