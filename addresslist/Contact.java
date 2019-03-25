package addresslist;
import java.io.Serializable;

public class Contact implements Serializable {

    private static final long serialVersionUID = 1L;

    public String name,sure;

	String email;

	public String post;
    public String phoneNumber;
    public Contact(){
    	
    }
    public Contact(String name, String sure, String email, String post,String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.post=post;
        this.email=email;
        this.sure=sure;
    }
    
    
    
    public void addContact(String name, String sure, String email, String post,String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.post=post;
        this.email=email;
        this.sure=sure;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setSure(String sure) {
        this.sure = sure;
    }
    public String getSure() {
        return sure;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
    public void setPost(String post) {
        this.post = post;
    }
    public String getPost() {
        return post;
    }
    
    public void setphoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getphoneNumber() {
        return phoneNumber;
    }

    

    public String toString() {
        return name + ", " +sure + ", " +phoneNumber+ ", " +email+ ", " +post;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Contact) {
            Contact contact = (Contact) obj;
            return (name.equals(contact.getName()) && phoneNumber
                    .equals(contact.getphoneNumber()));
        }

        return false;
    }

}