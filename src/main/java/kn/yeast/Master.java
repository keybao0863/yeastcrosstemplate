package kn.yeast;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.validator.constraints.UniqueElements;

@Entity
public class Master {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique=true)
	private String name;
	private String mat;
	private String leu1;
	private String his2;
	private String ura4;
	private String ade6;
	private String additionalGenotype;

	public Master() {
		
	}
	
	public Master(String name, String mat, String leu1, String his2, String ura4 ,String ade6, String additionalGenotype) {
		this.name = name;
		this.mat=mat;
		this.leu1=leu1;
		this.his2=his2;
		this.ura4=ura4;
		this.ade6=ade6;
		this.additionalGenotype=additionalGenotype;
	}
	
	public String getName() {
		return name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMat() {
		return mat;
	}
	public void setMat(String mat) {
		this.mat = mat;
	}
	public String getLeu1() {
		return leu1;
	}
	public void setLeu1(String leu1) {
		this.leu1 = leu1;
	}
	public String getHis2() {
		return his2;
	}
	public void setHis2(String his2) {
		this.his2 = his2;
	}
	public String getUra4() {
		return ura4;
	}
	public void setUra4(String ura4) {
		this.ura4 = ura4;
	}
	public String getAde6() {
		return ade6;
	}
	public void setAde6(String ade6) {
		this.ade6 = ade6;
	}
	public String getAdditionalGenotype() {
		return additionalGenotype;
	}
	public void setAdditionalGenotype(String additionalGenotype) {
		this.additionalGenotype = additionalGenotype;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(name + " (");
		
		sb.append(" " + mat);
		if (his2!=null) {
			sb.append(" " + his2);
		}
		
		if (leu1!=null) {
			sb.append(" " + leu1);
		}
		
		if (ura4!=null) {
			sb.append(" " + ura4);
		}
		
		if (ade6!=null) {
			sb.append(" " + ade6);
		}
		
		if (additionalGenotype!=null) {
			sb.append(" " + additionalGenotype);
		}
		
		sb.append(")");
	
		return sb.toString();
		
		
	}
	

}
