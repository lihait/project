package network;

public class Packet {

	private String netName;
	private int step;
	private String nextR;
	public Packet(){
		netName="";
		step=-1;
		nextR="";
	}
	public Packet(String n,int s,String nextR){
		this.netName=n;
		this.step=s;
		this.nextR=nextR;
	}
	public String getNetName() {
		return netName;
	}
	public void setNetName(String netName) {
		this.netName = netName;
	}
	public String getNextR() {
		return nextR;
	}
	public void setNextR(String nextR) {
		this.nextR = nextR;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		if(step>16){
			step=16;
		}
		this.step = step;
	}
	public String toString(){
		String s="";
		s+=netName+"\t\t"+step+"\t\t"+nextR;
		return s;
	}
	
}
