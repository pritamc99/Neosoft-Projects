package mypacks.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;




@Entity
@Table(name="Register")
public class Register 
{
	@Id
	/*@SequenceGenerator(name="Reg_Seq",sequenceName="Register_Sequence",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="Reg_Seq")
	*/
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="RegisterId")
	private int registerId;
	
	@Column(name="FirstName")
	@NotEmpty(message="Please enter First Name")	
	private String firstName;
	
	@NotEmpty(message="Please enter Last Name")	
	@Column(name="LastName")
	private String lastName;
	
	@NotEmpty(message="Please enter Address")
	@Column(name="Address")
	private String address;
	
	@NotEmpty(message="Please enter Email Id")	
	@Email
	@Pattern(regexp=".+@.+\\..+",message="Please enter valid email")
	@Column(name="Email")
	private String emailId;
	
	//@NotEmpty(message="Please enter Phone Number")
	//@Length(min=10,max=12,message="Please Enter valid Mobile Number")
	//@Pattern(regexp="([0-9]++){10,12}+",message="Please Enter valid Mobile Number")
	@Pattern(regexp="(\\d{10})",message="Please enter Phone Number")
	
	@Column(name="MobileNumber")
	private String mobileNumber;
	
	@NotEmpty(message="Please enter Username")	
	@Column(name="UserName")
	private String userName;
	
	@NotEmpty(message="Please enter Password")	
	@Column(name="Password")
	private String password;
		
	/*@ManyToOne(cascade=CascadeType.ALL,targetEntity=Role.class)
	@JoinColumn(name="UserRoleName",referencedColumnName="RoleId")
	private Role roleName;*/
	
	@ManyToOne(cascade=CascadeType.ALL,targetEntity=Role.class)
	@JoinColumns({
        @JoinColumn(name="UserRoleId", referencedColumnName="RoleId"),
        /* @JoinColumn(name="UserRoleName", referencedColumnName="RoleName"),
        @JoinColumn(name="enabled", referencedColumnName="enabled"),*/
    })
	private Role roleName;
	
	@Column(name="Salt")
	private String salt;
	
	@OneToMany(mappedBy="userId",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<DeliveryAddress> addressList;
	
	public int getRegisterId() {
		return registerId;
	}
	public void setRegisterId(int registerId) {
		this.registerId = registerId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Role getRoleName() {
		return roleName;
	}
	public void setRoleName(Role roleName) {
		this.roleName = roleName;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public List<DeliveryAddress> getAddressList() {
		return addressList;
	}
	public void setAddressList(List<DeliveryAddress> addressList) {
		this.addressList = addressList;
	}
	
	
	
}
