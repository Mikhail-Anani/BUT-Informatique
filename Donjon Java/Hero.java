public class Hero{
	private int pv;
	private int points;
	private int arme;

	public Hero(int pv,int points,int arme){
		this.pv=pv;
		this.points=points;
		this.arme=arme;
	}

	 public int getpv() {
        return pv;
    }

    public void setpv(int pv) {
        this.pv = pv;
    }

    public int getpoints(){
    	return points;
    }

    public void setpoints(int points){
    	this.points=points;
    }

    public int getarme() {
        return arme;
    }

    public void setarme(int arme) {
        this.arme = arme;
    }
}