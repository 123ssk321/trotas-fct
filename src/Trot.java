/**
 * Um objeto desta classe representa uma trotinete.
 * @author Sahil Satish Kumar e Tiago Vieira
 * Esta classe manipula as informacoes relativas a trotinete, como por exemplo a atualizacao 
 * das variaveis de instancia
 */
public class Trot {
	
	//Variaveis de instancia
	private String idTrot, licensePlate, state, functional;
	private boolean isRented, isActive, withLoc;
	private int totalRents, totalMovMinutes;
	private double locX, locY, distanceToClient;
	private Client client;
	
	//Construtores
	 
	/* Pre: id!="" && license!=""*/
	/**
	 * Cria uma nova trotinete com as informacoes dadas
	 * @param id, ID da nova trotine
	 * @param license, matricula da nova trotinete
	 * Pre: id!="" && license!=""
	 */
	public Trot(String id, String license) {
		idTrot = id;
		licensePlate = license;
		state = "parada";
		functional = "activa";
		isRented = false;
		isActive = true;
		totalRents = 0;
		totalMovMinutes = 0;
		distanceToClient = 0;
		locX=0;
		locY=0;
		withLoc = false;
	}
	
	/**
	 * Igual ao construtor Trot(String, String)
	 * E usado em metodos onde se torna mais simples criar uma Trotinete temporaria a partir das informacoes da trotinete existente,
	 * chamada por outro metodo, que queremos utilizar
	 * @param t, trotinete existente
	 * Pre: t!= null
	 */
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
	
	/**
	 * Inicia o aluguer da trotinete, atualizando o seu estado
	 * @param c, cliente que alugou a trotinete
	 * Pre: c != null;
	 */
	public void rent(Client c) {
		state = "alugada";
		isRented = true;
		client = c;
	}
	/**
	 * Acaba o aluguer da trotinete, atualizando o seu estado e informacoes
	 * @param minutes, tempo de aluguer em minutos
	 * Pre: minutes > 0
	 */
	public void release(int minutes) {
		client = null;
		state = "parada";
		isRented = false;
		totalMovMinutes+=minutes;
		totalRents++;
		withLoc = false;
	}
	/**
	 * Acaba o aluguer da trotinete, atualizando o seu estado, informacoes e localizacao
	 * @param minutes, tempo de aluguer em minutos
	 * @param x, longitude da trotinete
	 * @param y, latitude da trotinete
	 * Pre: minutes > 0 && x != null && y != null
	 */
	public void releaseWithLoc(int minutes, double x, double y) {
		client = null;
		state = "parada";
		isRented = false;
		totalMovMinutes+=minutes;
		totalRents++;
		locX = x;
		locY = y;
		withLoc = true;
	}
	/**
	 * Ativa a trotinete, atualizando o seu estado
	 * Pre: isActive = false
	 */
	public void activate() {
		functional = "activa";
		isActive = true;
	}
	/**
	 * Desativa a trotinete, atualizando o seu estado
	 * Pre: isRented = false && isActive = true
	 */
	public void inactive() {
		functional = "inactiva";
		isActive = false;
	}
	/**
	 * Calcula a distancia da trotinete ao cliente
	 * @param clientX, longitude do cliente
	 * @param clientY, latitude do cliente
	 * @return distancia da trotinete ao cliente
	 * Pre: clientX != null && clientY != null
	 */
	public double distanceToClient(double clientX, double clientY) {
		double distanceX = Math.pow(clientX-locX, 2);
		double distanceY = Math.pow(clientY-locY, 2);
		distanceToClient = Math.sqrt(distanceX+distanceY); 
		return Math.sqrt(distanceX+distanceY); 
	}
	/**
	 * Verifica se o ID da trotinete e maior do que o ID dado
	 * @param id, ID a comparar com o ID da Trotinete
	 * @return se o ID da trotiente e maior que o ID dado
	 * Pre: ID != null
	 */
	public boolean greaterThan(String id) {
		return this.showIdTrot().compareToIgnoreCase(id) > 0; 
	}
	
	//Metodos de consulta
	
	public String showIdTrot() {
		return idTrot;
	}
	public String showLicensePlate() {
		return licensePlate;
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
	/**
	 * @return se a trotinete foi libertada com informacao de localizacao
	 */
	public boolean releasedWithLoc() {
		return withLoc;
	}
	public int showTotalRents() {
		return totalRents;
	}
	public int showTotalMovingMinutes() {
		return totalMovMinutes;
	}
	public double showLat() {
		return locX;
	}
	public double showLong() {
		return locY;
	}
	/**
	 * @return cliente que no momento esta a alugar a trotinete
	 */
	public Client showClient() {
		return client;
	}	
	public double showDistance() {
		return distanceToClient;
	}
}
