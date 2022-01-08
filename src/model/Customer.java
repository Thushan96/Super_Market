package model;

import java.util.Objects;

public class Customer {
    private String id;
    private String tittle;
    private String name;
    private String address;
    private String city;
    private String province;
    private String postalCode;

    public Customer(String id, String tittle, String name, String address, String city, String province, String postalCode) {
        this.setId(id);
        this.setTittle(tittle);
        this.setName(name);
        this.setAddress(address);
        this.setCity(city);
        this.setProvince(province);
        this.setPostalCode(postalCode);
    }

    public Customer() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(getId(), customer.getId()) &&
                Objects.equals(getTittle(), customer.getTittle()) &&
                Objects.equals(getName(), customer.getName()) &&
                Objects.equals(getAddress(), customer.getAddress()) &&
                Objects.equals(getCity(), customer.getCity()) &&
                Objects.equals(getProvince(), customer.getProvince()) &&
                Objects.equals(getPostalCode(), customer.getPostalCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTittle(), getName(), getAddress(), getCity(), getProvince(), getPostalCode());
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", tittle='" + tittle + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }
}
