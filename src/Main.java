/**
 * @author Sahil Satish Kumar e Tiago Vieira
 * Interface entre o utilizador(input) e a classe do sistema de aluguer, 
 * do cliente e da trotinete.
 */
import java.util.Scanner;
import java.util.Locale;
public class Main {
	
	/*Constantes*/
	
	 //Opcoes
	private static final String AD_CLIENT = "adcliente";
	private static final String REM_CLIENT = "remcliente";
	private static final String AD_TROT = "adtrot";
	private static final String DATA_CLIENT = "dadoscliente";
	private static final String TROT = "trot";
	private static final String DATA_TROT = "dadostrot";
	private static final String CLIENT = "cliente";
	private static final String ADD_BALANCE = "carrsaldo";
	private static final String RENT = "alugar";
	private static final String RELEASE = "libertar";
	private static final String PROMOTE = "promocao";
	private static final String DES_TROT = "destrot";
	private static final String REAC_TROT = "reactrot";
	private static final String LIST_TROT = "listtrot";
	private static final String LIST_CLIENT = "listcliente";
	private static final String LIST_DEV_CLIENT = "listdev";
	private static final String RELEASE_LOC = "libloc";
	private static final String LOC_TROT = "loctrot";
	private static final String SYSTEM_STATE = "estadosistema";
	private static final String QUIT = "sair";
	
	 //Mensagens
	private static final String NO_CLIENT = "Cliente inexistente.";
	private static final String NO_TROT = "Trotinete inexistente.";
	private static final String NO_RENTED_TROT = "Trotinete nao alugada.";
	private static final String INVALID_VALUE = "Valor invalido.";
	private static final String QUITTING = "Saindo...";
	private static final String INVALID_COMMAND = "Comando invalido.";
	private static final String NO_LOC_TROT = "Nao existem trotinetes localizadas.";
	private static final String DISTANCE = "Distancia: %.6f\n%s: %s, %d, %d, %.6f, %.6f\n";
	private static final String ACTUAL_STATE = "Estado actual: %d, %d, %d\n";
	private static final String TROT_NOT_INAC = "Trotinete nao inactiva.";
	private static final String TROT_REAC = "Trotinete reactivada.";
	private static final String TROT_MOVING = "Trotinete em movimento.";
	private static final String TROT_DEAC = "Trotinete desactivada.";
	private static final String RENT_ENDED = "Aluguer terminado.";
	private static final String LOC_INVALID = "Localizacao invalida.";
	private static final String RENT_SUCCESS = "Aluguer efectuado com sucesso.";
	private static final String NO_BALANCE = "Cliente sem saldo suficiente.";
	private static final String CLIENT_MOVING = "Cliente em movimento.";
	private static final String TROT_CANT_BE_RENTED = "Trotinete nao pode ser alugada.";
	private static final String ADDED_BALANCE = "Carregamento efectuado.";
	private static final String WITHOUT_TROT = "Cliente sem trotinete.";
	private static final String TROT_EXISTS = "Trotinete existente.";
	private static final String ADD_TROT_SUCCESS = "Insercao de trotinete com sucesso.";
	private static final String REM_CLIENT_SUCCESS ="Cliente removido com sucesso.";
	private static final String ADD_CLIENT_SUCCESS = "Insercao de cliente com sucesso.";
	private static final String CLIENT_EXISTS = "Cliente existente.";
	
	/**
	 * Le a informacao digitada pelo teclado
	 * @param in, scanner que recebe input
	 * @return input do utilizador sem espacos no fim e no inicio
	 */
	private static String readSInput(Scanner in) {
		return in.next().trim();
		}
	/**
	 * Compara o NIF introduzido pelo utilizador ao nif ja existente no sistema
	 * @param nif a comparar com os NIFs existentes
	 * @param os, sistema onde vao ser utilizados os metodos necessarios
	 * @return se existe um cliente com esse NIF
	 * Pre: nif!=null && os!=null
	 */
	private static boolean compareNif(String nif, OperatingSystem os) {
		boolean comparasion = false;
		if (os.callClient(nif) != null)
			comparasion = os.callClient(nif).showNif().equalsIgnoreCase(nif);
		return comparasion;
	}
	/**
	 * Compara o NIF introduzido pelo utilizador ao nif ja existente no sistema
	 * @param id a comparar com os nifs existentes
	 * @param os, sistema onde vao ser utilizados os metodos necessarios
	 * @return se existe uma trotinete com esse ID
	 * Pre: id!=null && os!=null
	 */
	private static boolean compareId(String id, OperatingSystem os) {
		boolean comparasion = false;
		if(os.callTrot(id) !=null)
			comparasion = os.callTrot(id).showIdTrot().equalsIgnoreCase(id);
		return comparasion;
	}
	/**
	 * Lista a informacao referente ao cliente
	 * @param c, Cliente a listar
	 */
	private static void listClientInfo(Client c) {
		System.out.printf("%s: %s, %s, %s, %d, %d, %d, %d, %d, %d\n",
				c.showName(), c.showNif(), c.showEmail(), c.showPhoneNumber(), c.showBalance(), 
				c.showTotalSpendMinutes(),c.showTotalRents(), c.showMaxMinutes(), c.showMedMinutes(),
				c.showTotalSpendCents());
	}
	/**
	 * Lista a informacao referente a trotinete
	 * @param t, Trotinete a listar
	 */
	private static void listTrotInfo(Trot t) {
		System.out.printf("%s: %s, %d, %d\n", t.showLicensePlate(), t.trotState(), 
				t.showTotalRents(), t.showTotalMovingMinutes());
	}
	/**
	 * Mostra uma mensagem ao utilizador
	 * @param msg, mensagem a mostrar
	 */
	private static void showMessage(String msg) {
		System.out.println(msg);
	}
	
	//Metodos que processao os varios comandos que podem ser digitados pelo utilizador
	
	/**
	 * Inicia o processo de adicionar um cliente com as informacoes dadas
	 * @param in, informacao digitada pelo utilizador
	 * @param os, sistema onde vao ser utilizados os metodos necessarios 
	 * Pre: in!=null && os!=null
	 */
	private static void processAdCliente(Scanner in, OperatingSystem os) {
		String nif = readSInput(in);
		String email = readSInput(in);
		String phoneNum = readSInput(in);
		String name = in.nextLine().trim();
		if (compareNif(nif,os)) 
			showMessage(CLIENT_EXISTS);
		
		else {
			os.addClient(nif, email, phoneNum, name);
			showMessage(ADD_CLIENT_SUCCESS);
		}
	}
	/**
	 * Apos receber o NIF, inicia o processo de remocao do cliente indicado
	 * @param in, NIF do cliente a remover introduzido pelo utilizador
	 * @param os, sistema onde vao ser utilizados os metodos necessarios
	 * Pre: in!=null && os!=null 
	 */
	private static void processRemCliente(Scanner in, OperatingSystem os) {
		String nif = readSInput(in);
		in.nextLine();
		if (os.callClient(nif) == null || !compareNif(nif,os))
			showMessage(NO_CLIENT);
		else {
			if (os.callClient(nif).isClientRenting())
				showMessage(CLIENT_MOVING);
			else {
				os.remClient(nif);
				showMessage(REM_CLIENT_SUCCESS);
			}
		}
	}	
	/**
	 * Inicia o processo de adicionar uma trotinete com os dados recebidos
	 * @param in, dados da trotiente a adicionar introduzidos pelo utilizador
	 * @param os, sistema onde vao ser utilizados os metodos necessarios
	 * Pre: in!=null && os!=null
	 */
	private static void processAdTrot(Scanner in,OperatingSystem os) {
		String id = readSInput(in);
		String license = readSInput(in);
		in.nextLine();
		if (!compareId(id,os)) {
			os.addTrot(id, license);
			showMessage(ADD_TROT_SUCCESS);
		}
		else
			showMessage(TROT_EXISTS);	
	}
	/**
	 * Recebe o NIF do cliente a listar, se existir, inicia  listagem
	 * @param in, NIF do cliente a listar
	 * @param os, sistema onde vao ser utilizados os metodos necessarios
	 * Pre: in!=null && os!=null
	 */
	private static void processDadosCliente(Scanner in,OperatingSystem os) {
		String nif = readSInput(in);
		in.nextLine();
		if (compareNif(nif,os)) {
			Client c = os.callClient(nif);
			listClientInfo(c);
		}
		else
			showMessage(NO_CLIENT);
	}
	/**
	 * Devolve os dados da trotinete actualmente alugada pelo cliente com o NIF dado.
	 * @param in, NIF do cliente com a trotinete alugada
	 * @param os, sistema onde vao ser utilizados os metodos necessarios
	 * Pre: in!=null && os!=null
	 */
	private static void processTrot(Scanner in,OperatingSystem os) {
		String nif = readSInput(in);
		in.nextLine();
		if (!compareNif(nif,os))
			showMessage(NO_CLIENT);
		else {
			Client c = os.callClient(nif);
			if (!c.isClientRenting())
				showMessage(WITHOUT_TROT);
			else
				System.out.printf("%s, %s\n",c.showTrot().showIdTrot(), c.showTrot().showLicensePlate());
		}
	}
	/**
	 * Recebe o ID da trotinete a listar, se existir inicia a listagem
	 * @param in, ID da trotinete a listar introduzido pelo utilizador
	 * @param os, sistema onde vao ser utilizados os metodos necessarios
	 * Pre: in!=null && os!=null
	 */
	private static void processDadosTrot(Scanner in,OperatingSystem os) {
		String id = readSInput(in);
		in.nextLine();
		if (compareId(id, os)) {
			Trot t = os.callTrot(id);
			listTrotInfo(t);
		}
		else
			showMessage(NO_TROT);
	}
	/**
	 * Devolve os dados do cliente que actualmente aluga a trotinete com o ID dado.
	 * @param in,ID da trotinete alugada introduzido pelo utilizador
	 * @param os, sistema onde vao ser utilizados os metodos necessarios
	 * Pre: in!=null && os!=null
	 */
	private static void processCliente(Scanner in,OperatingSystem os) {
		String id = readSInput(in);
		in.nextLine();
		if (!compareId(id,os))
			showMessage(NO_TROT);
		else {
			if (!os.callTrot(id).isTrotRented())
				showMessage(NO_RENTED_TROT);
			else {
				Client c = os.callTrot(id).showClient();
				System.out.printf("%s, %s\n", c.showNif(), c.showName());
			}
		}
	}
	/**
	 * Carrega o saldo do cliente com o NIF introduzido
	 * @param in, NIF do cliente ao qual vai ser carregado o saldo, introduzido pelo cliente
	 * @param os, sistema onde vao ser utilizados os metodos necessarios
	 * Pre: in!=null && os!=null
	 */
	private static void processCarrSaldo(Scanner in,OperatingSystem os) {
		String nif = readSInput(in);
		int value = in.nextInt();
		in.nextLine();
		if (value <= 0)
			showMessage(INVALID_VALUE);
		else {
			if (!compareNif(nif, os))
				showMessage(NO_CLIENT);
			else {
				os.callClient(nif).addBalance(value);
				showMessage(ADDED_BALANCE);
			}
		}
	}
	/**
	 * Inicia o aluguer de uma trotinete por um cliente
	 * @param in, NIF do cliente que efetua o aluguer e ID da trotinete a alugar 
	 * @param os, sistema onde vao ser utilizados os metodos necessarios
	 * Pre: in!=null && os!=null
	 */
	private static void processAlugar(Scanner in,OperatingSystem os) {
		String nif = readSInput(in);
		String id = readSInput(in);
		in.nextLine();
		if (!compareNif(nif, os))
			showMessage(NO_CLIENT);
		else {
			if (!compareId(id, os))
				showMessage(NO_TROT);
			else {
				if (os.callTrot(id).isTrotRented() || !os.callTrot(id).isTrotActive())
					showMessage(TROT_CANT_BE_RENTED);
				else {
					if(os.callClient(nif).isClientRenting())
						showMessage(CLIENT_MOVING);					
					else {
						if (os.callClient(nif).showBalance() < 100)
							showMessage(NO_BALANCE);
						else {
							os.rentTrot(os.callClient(nif), os.callTrot(id));
							showMessage(RENT_SUCCESS);
						}
					}
				}
			}
		}
	}
	/**
	 * Encerra o aluguer da trotinete
	 * @param in, ID da trotinete a libertar e duracao do aluguer em minutos introduzidos pelo utilizador
	 * @param os, sistema onde vao ser utilizados os metodos necessarios
	 * Pre: in!=null && os!=null
	 */
	private static void processLibertar(Scanner in,OperatingSystem os) {
		String id = readSInput(in);
		int minutes = in.nextInt();
		in.nextLine();
		if (minutes <= 0)
			showMessage(INVALID_VALUE);
		else
			if (!compareId(id,os))
				showMessage(NO_TROT);
			else {
				if (!os.callTrot(id).isTrotRented())
					showMessage(NO_RENTED_TROT);
				else {
					os.releaseTrot(id,minutes);
					showMessage(RENT_ENDED);
				}
			}
	}
	/**
	 * Encerra o aluguer da trotinete, recebendo ainda a sua localizacao
	 * @param in, ID da trotinete a libertar, duracao do aluguer em minutos e localizacao da trotinete introduzidos pelo utilizador
	 * @param os, sistema onde vao ser utilizados os metodos necessarios
	 * Pre: in!=null && os!=null && coordenadas introduzidas no formato Longitude Latitude
	 */
	private static void processLibLoc(Scanner in, OperatingSystem os) {
		String id = readSInput(in);
		int minutes = in.nextInt();
		double tX = in.nextDouble();
		double tY = in.nextDouble();
		in.nextLine();
		if (!os.isInCampusFCT(tY, tX)) 
			showMessage(LOC_INVALID);
		else {
			if (minutes <= 0)
				showMessage(INVALID_VALUE);
			else {
				if (!compareId(id,os))
					showMessage(NO_TROT);
				else {
					if (!os.callTrot(id).isTrotRented())
						showMessage(NO_RENTED_TROT);
					else {
						os.releaseTrotWithLoc(id,minutes,tX,tY);
						showMessage(RENT_ENDED);
					}
				}
			}
		}
	}
	
	//Metodo nao usado neste trabalho
	/*Pre: in!=null && os!=null */
	private static void processPromocao(Scanner in,OperatingSystem os) {
		String nif = readSInput(in);
		in.nextLine();
		if (!compareNif(nif,os))
			showMessage(NO_CLIENT);
		else {
			if (os.callClient(nif).isClientRenting())
				showMessage("Cliente iniciou novo aluguer.");
			else {
				if (os.isDiscounted())
					showMessage("Promocao ja aplicada.");
				else {
					os.discount();
					showMessage("Promocao aplicada.");
				}
			} 	
		}
	}
	/**
	 * Desativa a trotinete
	 * @param in, ID da trotinete a desativar introduzido pelo utilizador
	 * @param os, sistema onde vao ser utilizados os metodos necessarios
	 * Verifica se a trotinete esta parada antes de proceder a sua desativacao
	 * Pre: in!=null && os!=null
	 */
	private static void processDesTrot(Scanner in,OperatingSystem os) {
		String id = readSInput(in);
		in.nextLine();
		if (!compareId(id,os))
			showMessage(NO_TROT);
		else {
			if (os.callTrot(id).isTrotRented())
				showMessage(TROT_MOVING);
			else {
				os.callTrot(id).inactive();
				showMessage(TROT_DEAC);
			}
		}
		
	}
	/**
	 * Reativa a trotinete
	 * @param in, ID da trotinete a reativar
	 * @param os, sistema onde vao ser utilizados os metodos necessarios
	 * Verifica se a trotinete esta desativada antes de a reativar
	 * Pre: in!=null && os!=null
	 */
	private static void processReacTrot(Scanner in,OperatingSystem os) {
		String id = readSInput(in);
		in.nextLine();
		if (!compareId(id,os))
			showMessage(NO_TROT);
		else {
			if (os.callTrot(id).isTrotActive())
				showMessage(TROT_NOT_INAC);
			else {
				os.callTrot(id).activate();
				showMessage(TROT_REAC);
			}
		}
	}
	/**
	 * Lista as informacoes de todas as trotinetes existentes
	 * @param os, sistema onde vao ser utilizados os metodos necessarios
	 * Pre: os!=null
	 */
	private static void processListTrot(OperatingSystem os) {
		TrotIterator it = os.trotIterator();
		while(it.hasNext()) {
			Trot tr = it.next();
			listTrotInfo(tr);
		}
	}
	/**
	 * Lista as informacoes de todos os clientes existentes por ordem crescente de NIF
	 * @param os, sistema onde vao ser utilizados os metodos necessarios
	 * Pre: os!=null
	 */
	private static void processListCliente(OperatingSystem os) {
		ClientIterator it = os.clientIterator();
		while(it.hasNext()) {
			Client cl = it.next();
			listClientInfo(cl);
		}
	}
	/**
	 * Lista as informacoes de todos os clientes com saldo negativo por ordem crescente de saldo 
	 * @param os, sistema onde vao ser utilizados os metodos necessarios
	 * Pre: os!=null
	 */
	private static void processListDev(OperatingSystem os) {
		ClientIterator it = os.debtClientIterator();
		while(it.hasNext()) {
			Client cl = it.next();
			listClientInfo(cl);
		}
	}
	/**
	 * Lista as trotinetes com localizacao por ordem crescente da sua distancia ao cliente
	 * @param in, coordenadas do cliente
	 * @param os, sistema onde vao ser utilizados os metodos necessarios
	 * Pre: os!=null && in!=null && coordenadas introduzidas no formato Longitude Latitude
	 */
	private static void processLocTrot(Scanner in,OperatingSystem os) {
		double tX = in.nextDouble();
		double tY = in.nextDouble();
		TrotIterator it = os.trotWithLocIterator(tX,tY);
		in.nextLine();
		if (os.numberTrotsWithLoc()>0) {
			while(it.hasNext()) {
				Trot t = it.next();
				System.out.printf(DISTANCE, t.showDistance(),t.showLicensePlate(),t.trotState(),t.showTotalRents(),
						t.showTotalMovingMinutes(),t.showLat(),t.showLong());
			}
		}
		else
			showMessage(NO_LOC_TROT);
	}
	/**
	 * Devolve o estado atual do sistema
	 * @param os, sistema a devolver o estado
	 * Pre: os!=null
	 */
	private static void processEstadoSistema(OperatingSystem os) {
		System.out.printf(ACTUAL_STATE, os.totalClientUses(), os.totalMoneyWon(), os.totalDelayTime());
	}
	/**
	 * Le o comando a ser executado e escolhe o processo de execucao desse comando
	 * @param in, input do utilizador necessario na execucao de algumas operacoes
	 * @param os, sistema onde vao ser chamados os metodos a serem executados
	 * @param cmd, comando dado pelo utilizador
	 * Pre: in!=null && os!=null && cmd!=""
	 */
	private static void executeCommand(Scanner in,OperatingSystem os,String cmd) {
		switch(cmd) {
			case AD_CLIENT:
				processAdCliente(in,os);
				break;
			case REM_CLIENT:
				processRemCliente(in,os);
				break;
			case AD_TROT:
				processAdTrot(in,os);
				break;
			case DATA_CLIENT:
				processDadosCliente(in,os);
				break;
			case TROT:
				processTrot(in,os);
				break;
			case DATA_TROT:
				processDadosTrot(in,os);
				break;
			case CLIENT:
				processCliente(in,os);
				break;
			case ADD_BALANCE:
				processCarrSaldo(in,os);
				break;
			case RENT:
				processAlugar(in,os);
				break;
			case RELEASE:
				processLibertar(in,os);
				break;
			case PROMOTE:
				processPromocao(in,os);
				break;
			case DES_TROT:
				processDesTrot(in,os);
				break;
			case REAC_TROT:
				processReacTrot(in,os);
				break;
			case LIST_TROT:{
				in.nextLine();
				processListTrot(os);
				}
				break;
			case LIST_CLIENT:{
				in.nextLine();
				processListCliente(os);
				}
				break;
			case LIST_DEV_CLIENT:{
				in.nextLine();
				processListDev(os);
				}
				break;
			case RELEASE_LOC:
				processLibLoc(in,os);
				break;
			case LOC_TROT:
				processLocTrot(in,os);
				break;
			case SYSTEM_STATE:{
				in.nextLine();
				processEstadoSistema(os);
				}
				break;	
			case QUIT:{
				in.nextLine();
				System.out.println(QUITTING);
				processEstadoSistema(os);
				}
				break;
			default:
				in.nextLine();
				System.out.println(INVALID_COMMAND);
		}
	}

	//Gere a parte de interacao com o utilizador
	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner input = new Scanner(System.in); 
		OperatingSystem trotasFCT = new OperatingSystem();
		
		String command = "";
		while (!command.equals(QUIT)) {
			command = readSInput(input).toLowerCase();
			executeCommand(input, trotasFCT, command);
		}
		input.close();
	}
}
