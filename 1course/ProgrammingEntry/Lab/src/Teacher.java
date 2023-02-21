
public class Teacher {
    private String name;
    private String chair;
    private String status;

    public Teacher(TeacherList chair){
        this.name = DataInput.getStr("Name: ");
        this.status = DataInput.getStr("Status: ");
        this.chair = chair.chairName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // треба буде перероблювати в майбутньому це полу і робити клас Chair
    public String getChair() {
        return chair;
    }

    public void setChair(String chair) {
        this.chair = chair;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}