package Chat;
import java.io.Serializable;
import java.util.Date;


public class MessageFormat implements Serializable,Comparable<MessageFormat> {
	private static final long serialVersionUID = 1L;
String name="";
String msg;
Date  time;
public MessageFormat (){
	
}
public MessageFormat (String msg ){
	this.msg=msg;
	this.time=new Date();
}
public MessageFormat (String msg, Date t ){
	this.msg=msg;
	this.time=t;
}
public String getmsg(){
	return msg;
}
public String getname(){
	return name;
}
public Date gettime(){
	return time;
}
public void setname(String n){
	this.name=n;
	
}
public void setmsg(String n){
	this.msg=n;
	
}
public void settime(Date n){
	this.time=n;
	
}
	@Override
public int compareTo(MessageFormat o) {
	int returnedValue;
	returnedValue= this.gettime().compareTo(o.gettime());
	
	if(returnedValue == 0){
		returnedValue= this.getname().compareTo(o.getname());
	}
	if(returnedValue == 0){
		returnedValue= this.getmsg().compareTo(o.getmsg());
	}
	return returnedValue;
}

public String toString(){
	return "From"+this.getname()+":"+this.getmsg()+"time: "+this.gettime();
	}
}