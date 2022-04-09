package Classes;

public class Booking
{
    Room room;
    Customer customer;
    float charges;
    int days;
    String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public float getCharges() {
        return charges;
    }

    public void setCharges(int d) {
        this.charges = 350*d+(350*d)/10+10*d;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public Booking(Room room, Customer customer,String date, int days) {
        this.room = room;
        this.customer = customer;
        this.charges = 350*days+(350*days)/10+10*days;
        this.days = days;
        this.date = date;
    }

    @Override
    public String toString() {
        return room +","+customer +","+charges +","+date+","+days ;
    }
}
