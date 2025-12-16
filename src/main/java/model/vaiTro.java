package model;

public class vaiTro {
	private String tdn;
	private String mk;
	private boolean quyen;
	public vaiTro(String tdn, String mk, boolean quyen) {
		super();
		this.tdn = tdn;
		this.mk = mk;
		this.quyen = quyen;
	}
	public String getTdn() {
		return tdn;
	}
	public void setTdn(String tdn) {
		this.tdn = tdn;
	}
	public String getMk() {
		return mk;
	}
	public void setMk(String mk) {
		this.mk = mk;
	}
	public boolean isQuyen() {
		return quyen;
	}
	public void setQuyen(boolean quyen) {
		this.quyen = quyen;
	}
	
}
