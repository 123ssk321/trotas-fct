/*
 * @author Sahil Satish Kumar
 * Esta classe manipula as informacoes relativas a trotinete, como por exemplo a atualizacao 
 * das variaveis de instancia
 */
public class Trot {
	
	//Variaveis de instancia
	private String idTrot, licensePlate, state, clientNif, clientName, functional;
	private boolean isRented, isActive;
	private int totalRents, totalMovMinutes;

	//Construtores
	 
	/* Pre: id!="" && license!=""*/
	public Trot(String id, String license) {
		idTrot = id;
		licensePlate = license;
		state = "parada";
		functional = "activa";
		clientNif = "";
		clientName = "";
		isRented = false;
		isActive = true;
		totalRents = 0;
		totalMovMinutes = 0;
	}
	/*Pre: t!=null*/
	public Trot(Trot t) {
		idTrot = t.showIdTrot();
		licensePlate = t.showLicensePlate();
		state = "parada";
		functional = "activa";
		isRented = false;
		isActive = true;
		totalRents = t.showTotalRents();
		totalMovMinutes = t.showTotalMovingMinutes();
	}
	
	//Metodos modificadores
	
	/*
	 * Inicia o aluguer da trotinete, atualizando o seu estado 
	 * Guarda  o nif e o nome do cliente
	 * Pre: nif!="" && name!=""
	 */
	public void rent(String nif,String name) {
		state = "alugada";
		isRented = true;
		clientNif = nif;
		clientName = name;
	}
	/*
	 * Acaba o aluguer da trotinete, atualizando o seu estado
	 * Vai acumulando o numero total de minutos do aluguer e 
	 * numero total de alugueres da trotinete
	 * Pre: minutes > 0
	 */
	public void release(int minutes) {
		state = "parada";
		isRented = false;
		totalMovMinutes+=minutes;
		totalRents++;
	}
	/*
	 * Ativa a trotinete, atualizando o seu estado
	 */
	public void activate() {
		functional = "activa";
		isActive = true;
	}
	/*
	 * Desativa a trotinete, atualizando o seu estado
	 */
	public void inactive() {
		functional = "inactiva";
		isRented = false;
		isActive = false;
	}
	
	//Metodos de consulta
	public String showIdTrot() {
		return idTrot;
	}
	public String showLicensePlate() {
		return licensePlate;
	}
	public String showClientNif() {
		return clientNif;
	}
	public String showClientName() {
		return clientName;
	}
	public String trotState() {
		if (this.isTrotActive()) {
			if (this.isTrotRented())
				state = "alugada";
			else
				state = "parada";
		}
		else 
			state = functional;
		return state;
	}
	public boolean isTrotRented() {
		return isRented;
	}
	public boolean isTrotActive() {
		return isActive;
	}
	public int showTotalRents() {
		return totalRents;
	}
	public int showTotalMovingMinutes() {
		return totalMovMinutes;
	}
	
}
