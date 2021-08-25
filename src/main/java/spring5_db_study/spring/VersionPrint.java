package spring5_db_study.spring;

public class VersionPrint {
	private int maiorVersion;
	private int minorVersion;
	
	public void print() {
		System.out.printf("이 프로그램 버전은 %d.%d입니다%n",maiorVersion,minorVersion);
	}

	public void setMaiorVersion(int maiorVersion) {
		this.maiorVersion = maiorVersion;
	}

	public void setMinorVersion(int minorVersion) {
		this.minorVersion = minorVersion;
	}
	
}
