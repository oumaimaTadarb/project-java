package com.example.projet;

import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.Queue;

public class Person {
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty profession;
    private final StringProperty address;
    private final IntegerProperty postalCode;
    private final ObjectProperty<LocalDate> birthday;

    public Person(String firstName, String lastName, String profession,String address, int postalCode, LocalDate birthday){
        this.firstName=new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.profession = new SimpleStringProperty(profession);
        this.address = new SimpleStringProperty(address);
        this.postalCode = new SimpleIntegerProperty(postalCode);
        this.birthday = new SimpleObjectProperty<>(birthday);
    }
    public Person(String lastName, String firstName){
        this.firstName=new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.profession = new SimpleStringProperty("");
        this.address = new SimpleStringProperty("");
        this.postalCode = new SimpleIntegerProperty(0);
        this.birthday = new SimpleObjectProperty<>(null);
    }

    public Person() {
        this.firstName=new SimpleStringProperty("");
        this.lastName = new SimpleStringProperty("");
        this.profession = new SimpleStringProperty("");
        this.address = new SimpleStringProperty("");
        this.postalCode = new SimpleIntegerProperty(0);
        this.birthday = new SimpleObjectProperty<>(null);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getProfession() {
        return profession.get();
    }

    public StringProperty professionProperty() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession.set(profession);
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public int getPostalCode() {
        return postalCode.get();
    }

    public IntegerProperty postalCodeProperty() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode.set(postalCode);
    }

    public LocalDate getBirthday() {
        return birthday.get();
    }

    public ObjectProperty<LocalDate> birthdayProperty() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday.set(birthday);
    }
}
