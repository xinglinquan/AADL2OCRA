package aadl2ocra.utils;

public class ContractUtils {
	private String contractName;
	private String assume;
	private String guarantee;
	private String refinedby;
	public ContractUtils(String contractName, String assume, String guarantee, String refinedby) {
		super();
		this.contractName = contractName;
		this.assume = assume;
		this.guarantee = guarantee;
		this.refinedby = refinedby;
	}
	public String getContractName() {
		return contractName;
	}
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}
	public String getAssume() {
		return assume;
	}
	public void setAssume(String assume) {
		this.assume = assume;
	}
	public String getGuarantee() {
		return guarantee;
	}
	public void setGuarantee(String guarantee) {
		this.guarantee = guarantee;
	}
	public String getRefinedby() {
		return refinedby;
	}
	public void setRefinedby(String refinedby) {
		this.refinedby = refinedby;
	};
}
