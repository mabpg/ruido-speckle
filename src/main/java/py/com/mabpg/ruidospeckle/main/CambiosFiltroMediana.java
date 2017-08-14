package py.com.mabpg.ruidospeckle.main;

public class CambiosFiltroMediana {
	
	private Pixel ubicacion;
	private int mediana;
	
	
	public CambiosFiltroMediana() {
		super();
	}
	
	public CambiosFiltroMediana(int valor, int fil, int col) {
		this.mediana = valor;
		Pixel p = new Pixel(fil,col);
		this.ubicacion = p;
	}
	
	public Pixel getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(Pixel ubicacion) {
		this.ubicacion = ubicacion;
	}
	public int getMediana() {
		return mediana;
	}
	public void setMediana(int mediana) {
		this.mediana = mediana;
	}
	
	

}
