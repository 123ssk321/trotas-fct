/*
 * @author Sahil Satish Kumar
 * Esta classe controla as classes do cliente e da trotinete e as informacoes relativas
 * ao sistema de aluguer
 * permite a interacao entre a classe Main e a classe Client e Trot
 */
public class OperatingSystem {
	//Constantes
	private static final int PRICE = 100, LEGALTIME = 60,  EXTRATIME = 30;
	
	//Variaveis de instancia
	private Client cl, tmpCl;
	private Trot tr,tmpTr;
	private int totalDelayTime, numberOfRents, totalMoney;
	private int tmpTotalDelayTime, tmpNumberOfRents, tmpTotalMoney;
	private boolean isDiscounted;
	
	//Construtor
	public OperatingSystem() {
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
	public Client callClient() {
		return cl;
	}
	public Trot callTrot() {
		return tr;
	}
	
	//Metodos modificadores
	
	/*Pre: nif!="" && email!="" && phoneNum!="" && name!="" */
	public void addClient(String nif, String email, String phoneNum, String name) {
		cl = new Client(nif, email, phoneNum, name);
	}
	public void remClient() {
		cl = null;
	}
	public void addTrot(String id, String license) {
		tr = new Trot(id, license);
	}
	/*
	 * Inicia um aluguer de uma trotinete
	 * Associa o nif e o nome do cliente a trotinete
	 * Associa o id e a matricula da trotintete ao cliente
	 */
	public void rentTrot() {
		tr.rent(cl.showNif(), cl.showName());
		cl.rentBegin(tr.showIdTrot(), tr.showLicensePlate());
	}
	/*
	 * Acaba o aluguer de uma trotinete, guardando os dados do aluguer anterior(relativos
	 * ao cliente, a trotinete e ao sistema) antes de chamar o metodo para terminar o aluguer
	 * Atualizas variaveis de instancia do sistema
	 */
	public void releaseTrot(int minutes) {
		int delayPrice = 0;
		int delayTime = 0;
		
		tmpCl = new Client(cl);
		tmpTr = new Trot(tr);
		tmpTotalDelayTime = totalDelayTime;
		tmpNumberOfRents = numberOfRents;
		tmpTotalMoney = totalMoney;
		isDiscounted = false;
		
		numberOfRents++;
		tr.release(minutes);
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
	/*
	 * Carrega o saldo do cliente
	 * Pre: cents>0
	 */
	public void rechargeBalance(int cents) {
		cl.addBalance(cents);
	}
	/*
	 * Muda os dados do sistema, do cliente e da trotinete para os dados antes do ultimo aluguer efetuado
	 */
	public void discount() {
		cl = tmpCl;
		tr = tmpTr;
		isDiscounted = true;
		totalDelayTime = tmpTotalDelayTime;
		numberOfRents = tmpNumberOfRents;
		totalMoney = tmpTotalMoney;
	}
	public void activateTrot() {
		tr.activate();
	}
	public void deactivateTrot() {
		tr.inactive();
	}
	
	//Metodos de consulta
	public int totalDelayTime() {
		return totalDelayTime;
	}
	public int totalMoneyWon() {
		return totalMoney;
	}
	public int totalClientUses() {
		return numberOfRents;
	}
	public boolean isDiscounted() {
		return isDiscounted;
	}
}
