class Student {
    private String name;
    private String grade;

    public Student(String name, String grade) {
        this.name = name;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String newGrade) {
        this.grade = newGrade;
    }
}
