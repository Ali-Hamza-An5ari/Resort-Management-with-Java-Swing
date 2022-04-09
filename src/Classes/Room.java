package Classes;

public class Room
{
    private String roomNo;
    private boolean isBooked;
    private String view;

    @Override
    public String toString() {
        return roomNo + ","+ isBooked +","+view ;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public String getView() {
        return view;
    }

    public Room(String roomNo, boolean isBooked, String view) {
        this.roomNo = roomNo;
        this.isBooked = isBooked;
        this.view = view;
    }
}
