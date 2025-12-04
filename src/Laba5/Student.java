package Laba5;

abstract class Student {
    protected String name;
    protected String gender;
    protected int age;

    public Student(String name, String gender, int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public abstract boolean canGiveSpecialScholarship();

    public String getName() { return name; }
    public String getGender() { return gender; }
    public int getAge() { return age; }

    @Override
    public String toString() {
        return name + " (" + gender + ", " + age + " лет)";
    }
}
