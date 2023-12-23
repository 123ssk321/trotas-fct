/**
 * @author Sahil Satish Kumar e Tiago Vieira
 *Esta classe permite a criacao de um retangulo, usado para definir os limites do campus da FCT
 */
public class Rect {
	//Constantes
	private static final double CAMPUS_NORTH_LIMIT = 38.663964, CAMPUS_SOUTH_LIMIT = 38.658475;
	private static final double CAMPUS_WEST_LIMIT = -9.209269, CAMPUS_EAST_LIMIT = -9.201978;
	
	public Rect() {}
	/**
	 * Verifica se as cordenadas dadas estao contidas no Campus da FCT
	 * @param x, longitude
	 * @param y, latitude
	 * @return se as cordenadas dadas estao contidas no Campus da FCT
	 */
	public boolean inCampus(double x, double y) {
		return CAMPUS_WEST_LIMIT <= x && x <= CAMPUS_EAST_LIMIT && CAMPUS_SOUTH_LIMIT <= y && y <= CAMPUS_NORTH_LIMIT;
	}	
}
