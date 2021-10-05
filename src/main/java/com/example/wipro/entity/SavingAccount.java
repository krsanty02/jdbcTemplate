package com.example.wipro.entity;

import java.io.Serializable;

public class SavingAccount  implements Serializable{
private String savingAccounttype;

public SavingAccount(String savingAccounttype) {
	super();
	this.savingAccounttype = savingAccounttype;
}

public String getSavingAccounttype() {
	return savingAccounttype;
}

public void setSavingAccounttype(String savingAccounttype) {
	this.savingAccounttype = savingAccounttype;
}

}
