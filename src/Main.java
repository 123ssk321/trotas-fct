/*
 * @author Sahil Satish Kumar
 * Esta classe serve de interface entre o utilizador(input) e a classe do sistema
 * de aluguer, do cliente e da trotinete
 */
import java.util.Scanner;
public class Main {
	
	//Constantes
	private static final String NO_CLIENT = "Cliente inexistente.";
	private static final String NO_TROT = "Trotinete inexistente.";
	private static final String NO_RENTED_TROT = "Trotinete nao alugada.";
	private static final String INVALID_VALUE = "Valor invalido.";
	
	//Metodos auxiliares dos metodos para as varias acoes do utilizador
	/*
	 * Le a informacao digitada pelo teclado
	 * Pre: in!=null
	 */
	private static String readSInput(Scanner in) {
		return in.next().trim();
		}
	/*
	 * Compara o nif introduzido pelo utilizador ao nif ja existente no sistema
	 * Pre: nif!=null && os!=null
	 */
	private static boolean compareNif(String nif, OperatingSystem os) {
		boolean comparasion = false;
		if (os.callClient() != null)
			comparasion = os.callClient().showNif().equalsIgnoreCase(nif);
		return comparasion;
	}
	/*
	 * Compara o id da trotinete introduzido pelo utilizador ao id da trotinete ja existente no sistema
	 * Pre: id!=null && os!=null
	 */
	private static boolean compareId(String id, OperatingSystem os) {
		return os.callTrot().showIdTrot().equalsIgnoreCase(id);
	}
	/*Mostra uma mensagem ao utilizador */
	private static void showMessage(String msg) {
		System.out.println(msg);
	}
	
	//Metodos que processao os varios comandos que podem ser digitados pelo utilizador
	
	/*Pre: in!=null && os!=null */
	private static void processAdCliente(Scanner in, OperatingSystem os) {
		String nif = readSInput(in);
		String email = readSInput(in);
		String phoneNum = readSInput(in);
		String name = in.nextLine().trim();
		if (compareNif(nif,os)) 
			showMessage("Cliente existente.");
		else {
			os.addClient(nif, email, phoneNum, name);
			showMessage("Insercao de cliente com sucesso.");
		}
	}
	/*Pre: in!=null && os!=null */
	private static void processRemCliente(Scanner in, OperatingSystem os) {
		String nif = readSInput(in);
		in.nextLine();
		if (os.callClient() == null || !compareNif(nif,os))
			showMessage(NO_CLIENT);
		else {
			if (os.callClient().isClientRenting())
				showMessage("Cliente em movimento.");
			else {
				os.remClient();
				showMessage("Cliente removido com sucesso.");
			}
		}
	}	
	/*Pre: in!=null && os!=null */
	private static void processAdTrot(Scanner in,OperatingSystem os) {
		String id = readSInput(in);
		String license = readSInput(in);
		in.nextLine();
		if (!compareId(id,os)) {
			os.addTrot(id, license);
			showMessage("Insercao de trotinete com sucesso.");
		}
		else
			showMessage("Trotinete existente.");	
	}
	/*Pre: in!=null && os!=null */
	private static void processDadosCliente(Scanner in,OperatingSystem os) {
		String nif = readSInput(in);
		in.nextLine();
		Client c = os.callClient(); 
		if (compareNif(nif,os)) {
				System.out.printf("%s: %s, %s, %s, %d, %d, %d, %d, %d, %d\n",
						c.showName(), c.showNif(), c.showEmail(), c.showPhoneNumber(), c.showBalance(), 
						c.showTotalSpendMinutes(),c.showTotalRents(), c.showMaxMinutes(), c.showMedMinutes(),
						c.showTotalSpendCents());
		}
		else
			showMessage(NO_CLIENT);
	}
	/*Pre: in!=null && os!=null */
	private static void processTrot(Scanner in,OperatingSystem os) {
		String nif = readSInput(in);
		in.nextLine();
		Client c = os.callClient();
		if (!compareNif(nif,os))
			showMessage(NO_CLIENT);
		else {
			if (!os.callClient().isClientRenting())
				showMessage("Cliente sem trotinete.");
			else
				System.out.printf("%s, %s\n", c.showUsingTrotId(), c.showUsingTrotLicense());
		}
	}
	/*Pre: in!=null && os!=null */
	private static void processDadosTrot(Scanner in,OperatingSystem os) {
		String id = readSInput(in);
		in.nextLine();
		Trot t = os.callTrot();
		if (compareId(id, os)) {
				System.out.printf("%s: %s, %d, %d\n", t.showLicensePlate(), t.trotState(), 
						t.showTotalRents(), t.showTotalMovingMinutes());
		}
		else
			showMessage(NO_TROT);
	}
	private static void processCliente(Scanner in,OperatingSystem os) {
		String id = readSInput(in);
		in.nextLine();
		Trot t = os.callTrot();
		if (!compareId(id,os))
			showMessage(NO_TROT);
		else {
			if (!os.callTrot().isTrotRented())
				showMessage(NO_RENTED_TROT);
			else
				System.out.printf("%s, %s\n", t.showClientNif(), t.showClientName());
		}
	}
	/*Pre: in!=null && os!=null */
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
				os.callClient().addBalance(value);
				showMessage("Carregamento efectuado.");
			}
		}
	}
	/*Pre: in!=null && os!=null */
	private static void processAlugar(Scanner in,OperatingSystem os) {
		String nif = readSInput(in);
		String id = readSInput(in);
		in.nextLine();
		if (!compareNif(nif, os))
			showMessage(NO_CLIENT);
		else
			if (!compareId(id, os))
				showMessage(NO_TROT);
			else {
				if (os.callTrot().isTrotRented() || !os.callTrot().isTrotActive())
					showMessage("Trotinete nao pode ser alugada.");
				else {
					if (os.callClient().showBalance() < 100)
						showMessage("Cliente sem saldo suficiente.");
					else {
						os.rentTrot();
						showMessage("Aluguer efectuado com sucesso.");
					}
				}
			}	
	}
	/*Pre: in!=null && os!=null */
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
				if (!os.callTrot().isTrotRented())
					showMessage(NO_RENTED_TROT);
				else {
					os.releaseTrot(minutes);
					showMessage("Aluguer terminado.");
				}
			}
	}
	/*Pre: in!=null && os!=null */
	private static void processPromocao(Scanner in,OperatingSystem os) {
		String nif = readSInput(in);
		in.nextLine();
		if (!compareNif(nif,os))
			showMessage(NO_CLIENT);
		else {
			if (os.callClient().isClientRenting())
				System.out.println("Cliente iniciou novo aluguer.");
			else {
				if (os.isDiscounted())
					System.out.println("Promocao ja aplicada.");
				else {
					os.discount();
					System.out.println("Promocao aplicada.");
				}
			} 	
		}
	}
	/*Pre: in!=null && os!=null */
	private static void processDesTrot(Scanner in,OperatingSystem os) {
		String id = readSInput(in);
		in.nextLine();
		if (!compareId(id,os))
			showMessage(NO_TROT);
		else {
			if (os.callTrot().isTrotRented())
				System.out.println("Trotinete em movimento.");
			else {
				os.callTrot().inactive();
				System.out.println("Trotinete desactivada.");
			}
		}
		
	}
	/*Pre: in!=null && os!=null */
	private static void processReacTrot(Scanner in,OperatingSystem os) {
		String id = readSInput(in);
		in.nextLine();
		if (!compareId(id,os))
			showMessage(NO_TROT);
		else {
			if (os.callTrot().isTrotActive())
				System.out.println("Trotinete nao inactiva.");
			else {
				os.callTrot().activate();
				System.out.println("Trotinete reactivada.");
			}
		}
	}
	/*Pre: os!=null */
	private static void processEstadoSistema(OperatingSystem os) {
		System.out.printf("Estado actual: %d, %d, %d\n", os.totalClientUses(), os.totalMoneyWon(), os.totalDelayTime());
	}
	
	//Metodo que le os comando a ser executado e escolhe um processo de execucao desse comando
	/*Pre: in!=null && os!=null && cmd!="" */
	private static void executeCommand(Scanner in,OperatingSystem os,String cmd) {
		switch(cmd) {
			case "adcliente":
				processAdCliente(in,os);
				break;
			case "remcliente":
				processRemCliente(in,os);
				break;
			case "adtrot":
				processAdTrot(in,os);
				break;
			case "dadoscliente":
				processDadosCliente(in,os);
				break;
			case "trot":
				processTrot(in,os);
				break;
			case "dadostrot":
				processDadosTrot(in,os);
				break;
			case "cliente":
				processCliente(in,os);
				break;
			case "carrsaldo":
				processCarrSaldo(in,os);
				break;
			case "alugar":
				processAlugar(in,os);
				break;
			case "libertar":
				processLibertar(in,os);
				break;
			case "promocao":
				processPromocao(in,os);
				break;
			case "destrot":
				processDesTrot(in,os);
				break;
			case "reactrot":
				processReacTrot(in,os);
				break;
			case "estadosistema":{
				in.nextLine();
				processEstadoSistema(os);
				}
				break;
			case "sair":{
				in.nextLine();
				System.out.println("Saindo...");
				processEstadoSistema(os);
				}
				break;
			default:
				in.nextLine();
				System.out.println("Comando invalido.");
		}
	}

	//Gere a parte de interacao com o utilizador
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in); 
		OperatingSystem trotasFCT = new OperatingSystem();
		
		String command = "";
		while (!command.equals("sair")) {
			command = readSInput(input).toLowerCase();
			executeCommand(input, trotasFCT, command);
		}
		input.close();
	}

}
