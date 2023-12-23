/**
 * @author Sahil Satish Kumar e Tiago Vieira
 * Esta classe controla as classes do cliente e da trotinete e as informacoes relativas
 * ao sistema de aluguer
 * permite a interacao entre a classe Main e as os objetos Cliente e Trotinete armazenados nas
 * classes ClientSet e TrotSet
 */
public class OperatingSystem {
	//Constantes
	private static final int PRICE = 100, LEGALTIME = 60,  EXTRATIME = 30;
	
	//Variaveis de instancia
	private Rect campusFCT;
	private Client cl, tmpCl;
	private Trot tr,tmpTr;
	private ClientSet clients;
	private TrotSet trots;
	private int totalDelayTime, numberOfRents, totalMoney, trotsWithLoc;
	private int tmpTotalDelayTime, tmpNumberOfRents, tmpTotalMoney;
	private boolean isDiscounted;
	
	//Construtor
	public OperatingSystem() {
		clients = new ClientSet();
		trots = new TrotSet();
		campusFCT = new Rect();
		cl= new Client("","","",""); 
		tr = new Trot("","");
		tmpCl = new Client(cl);
		tmpTr = new Trot(tr);
		isDiscounted = false;
		totalDelayTime = tmpTotalDelayTime = 0;
		numberOfRents = tmpNumberOfRents = 0;
		totalMoney = tmpTotalMoney = 0;
	}
	
	//Metodos que permitem a interacao entre a classe Main e a classe Client e Trot
	public Client callClient(String nif) {
		return clients.getClient(nif);
	}
	/**
	 * Devolve a trotinete com o ID dado
	 * @param id, ID da trotinete a devolver
	 * @return
	 */
	public Trot callTrot(String id) {
		return trots.getTrot(id);
	}
	//Metodos modificadores
	
	/*Pre: nif!="" && email!="" && phoneNum!="" && name!="" */
	public void addClient(String nif, String email, String phoneNum, String name) {
		clients.addToSet(nif, email, phoneNum, name);
	}
	public void remClient(String nif) {
		clients.remFromSet(nif);;
	}
	public void addTrot(String id, String license) {
		trots.addToSet(id, license);
	}
	/**
	 * Inicia o aluguer de uma trotinete, chamando os metodos de inicio de aluguer do cliente
	 * e da trotinete
	 * @param cl, cliente que vai iniciar o aluguer
	 * @param tr, trotinete que vai ser alugada
	 */
	public void rentTrot(Client cl, Trot tr) {
		tr.rent(cl);
		cl.rentBegin(tr);
	}
	/**
	 * Acaba o aluguer de uma trotinete, calculando o dinheiro gasto e chamando os metodos para terminar o aluguer
	 * Atualiza os dados do sistema
	 * @param id, ID da trotinete que vai deixar de estar alugada
	 * @param minutes, tempo de aluguer em minutos
	 * Pre: minutes > 0
	 */
	public void releaseTrot(String id,int minutes) {
		int delayPrice = 0;
		int delayTime = 0;
		
		Client cl = trots.getTrot(id).showClient(); 
		tmpCl = new Client(cl);
		tmpTr = new Trot(trots.getTrot(id));
		tmpTotalDelayTime = totalDelayTime;
		tmpNumberOfRents = numberOfRents;
		tmpTotalMoney = totalMoney;
		isDiscounted = false;
		numberOfRents++;
		trots.getTrot(id).release(minutes);;
		if (minutes<=LEGALTIME) {
			cl.rentEnd(PRICE, minutes);
			totalMoney+=PRICE;
		}
		else {
			delayTime = minutes-LEGALTIME;
			totalDelayTime+=delayTime;               //Acumula os tempos de atraso
			if (delayTime % EXTRATIME == 0 ) {            //Verifica se o tempo de atraso e divisivel pelo EXTRATIME
				delayPrice = (delayTime / EXTRATIME) * PRICE;      //Calcula o preco a pagar com atraso
				cl.rentEnd(delayPrice + PRICE, minutes);
				totalMoney+=(delayPrice + PRICE);
			}
			else {
				delayPrice = (delayTime / EXTRATIME) * PRICE + PRICE;  //Calcula o preco a pagar pelo atraso
				cl.rentEnd(delayPrice + PRICE, minutes);
				totalMoney+=(delayPrice + PRICE);
			}
		}		
	}
	/**
	 * Igual ao metodo releaseTrot(String, String) mas no ato de libertar
	 * acrescenta a informacao da localizacao
	 * @param id, ID da trotinete que vai deixar de estar alugada
	 * @param minutes, tempo de aluguer em minutos
	 * @param x, longitude da trotinete
	 * @param y, latitude da trotinete
	 * Pre: minutes > 0
	 */
	public void releaseTrotWithLoc(String id,int minutes, double x, double y) {
		int delayPrice = 0;
		int delayTime = 0;
		
		Client cl = trots.getTrot(id).showClient(); 
		tmpCl = new Client(cl);
		tmpTr = new Trot(trots.getTrot(id));
		tmpTotalDelayTime = totalDelayTime;
		tmpNumberOfRents = numberOfRents;
		tmpTotalMoney = totalMoney;
		isDiscounted = false;
		trotsWithLoc++;
		numberOfRents++;
		trots.getTrot(id).releaseWithLoc(minutes, x, y);;
		if (minutes<=LEGALTIME) {
			cl.rentEnd(PRICE, minutes);
			totalMoney+=PRICE;
		}
		else {
			delayTime = minutes-LEGALTIME;
			totalDelayTime+=delayTime;               //Acumula os tempos de atraso
			if (delayTime % EXTRATIME == 0 ) {            //Verifica se o tempo de atraso e divisivel pelo EXTRATIME
				delayPrice = (delayTime / EXTRATIME) * PRICE;      //Calcula o preco a pagar com atraso
				cl.rentEnd(delayPrice + PRICE, minutes);
				totalMoney+=(delayPrice + PRICE);
			}
			else {
				delayPrice = (delayTime / EXTRATIME) * PRICE + PRICE;  //Calcula o preco a pagar pelo atraso
				cl.rentEnd(delayPrice + PRICE, minutes);
				totalMoney+=(delayPrice + PRICE);
			}
		}		
	}
	/**
	 * Chama o metodo do cliente para carregar o saldo
	 * @param nif, NIF do cliente cujo saldo vai ser carregado
	 * @param cents, valor em centimos a carregar
	 * Pre: cents > 0
	 */
	public void rechargeBalance(String nif,int cents) {
		clients.getClient(nif).addBalance(cents);
	}
	/*
	 * Muda os dados do sistema, do cliente e da trotinete para os dados antes do ultimo aluguer efetuado
	 * Nao usado neste trabalho	 
	 */
	public void discount() {
		cl = tmpCl;
		tr = tmpTr;
		isDiscounted = true;
		totalDelayTime = tmpTotalDelayTime;
		numberOfRents = tmpNumberOfRents;
		totalMoney = tmpTotalMoney;
	}
	/**
	 * @return iterador dos clientes existentes
	 */
	public ClientIterator clientIterator() {
		return clients.iterator();
	}
	/**
	 * @return iterador dos clientes com saldo negativo
	 */
	public ClientIterator debtClientIterator() {
		return clients.debtClientsOrd();
	}
	/**
	 * @return iterador das trotinetes existentes
	 */
	public TrotIterator trotIterator() {
		return trots.iterator();
	}
	//Metodos de consulta
	
	/**
	 * @return total de minutos de atraso de todos os clientes
	 */
	public int totalDelayTime() { 
		return totalDelayTime;
	}
	/**
	 * @return total de dinheiro gasto no sistema
	 */
	public int totalMoneyWon() {
		return totalMoney;
	}
	/**
	 * @return numero de alugueres efetuados por todos os clientes
	 */
	public int totalClientUses() {
		return numberOfRents;
	}
	/**
	 * @return o numero de trotinetes com localizaco
	 */
	public int numberTrotsWithLoc() {
		return trotsWithLoc;
	}
	/**
	 * Chama o metodo que verifica se a localizacao da trotinete pertence ao campus da FCT
	 * @param x, longitude da trotinete
	 * @param y, latitude da trotinete
	 * @return se a trotinete se encontra dentro do campus
	 */
	public boolean isInCampusFCT(double x, double y) {
		return campusFCT.inCampus(x, y);
	}
	/**
	 * Chama o metodo que cria um vetor com todas as trotinetes que possuem localizacao
	 * ordenadas por ordem de distancia ao cliente e de seguida devolve um iterador desse vetor
	 * @param clientX, longitude do cliente
	 * @param clientY, latitude do cliente
	 * @return iterador das trotinetes com localizacao
	 */
	public TrotIterator trotWithLocIterator(double clientX, double clientY) {
		return trots.distanceOrdIterartor(clientX, clientY);	
	}
	public boolean isDiscounted() {
		return isDiscounted;
	}
}
