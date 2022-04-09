package Classes;

public class Customer
{
    private String name;
    private String IC;
    private String contact;
    private String email;

    @Override
    public String toString() {
        return name + "," + IC + ","+ contact+","+email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIC(String IC) {
        this.IC = IC;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getIC() {
        return IC;
    }

    public String getContact() {
        return contact;
    }

    public String getEmail() {
        return email;
    }

    public Customer(String name, String IC, String contact, String email) {
        this.name = name;
        this.IC = IC;
        this.contact = contact;
        this.email = email;
    }
}
