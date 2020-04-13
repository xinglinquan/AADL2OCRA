package aadl2ocra.utils;

public class PortUtils {
	private String name;
	private String dir;
	private String type;
	private String classifier;
	public PortUtils(String name, String dir, String type, String classifier) {
		super();
		this.name = name;
		this.dir = dir;
		this.type = type;
		this.classifier = classifier;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getClassifier() {
		return classifier;
	}
	public void setClassifier(String classifier) {
		this.classifier = classifier;
	}
}
