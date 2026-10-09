package model;

/** Mỗi đối tượng lưu một môn học của sinh viên trong một học kỳ. */
public class Student {
    private int id;
    private String name;
    private int semester;
    private String course;

    /**
     * Khởi tạo đối tượng Student chưa có thông tin.
     */
    public Student() {
    }

    /**
     * Khởi tạo đối tượng Student với thông tin được cung cấp.
     * @param id mã định danh
     * @param name tên sinh viên
     * @param semester học kỳ
     * @param course môn học
     */
    public Student(int id, String name, int semester, String course) {
        this.id = id;
        this.name = name;
        this.semester = semester;
        this.course = course;
    }

    /**
     * Lấy mã định danh.
     * @return mã định danh
     */
    public int getId() {
        return id;
    }

    /**
     * Cập nhật mã định danh.
     * @param id mã định danh
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Lấy tên sinh viên.
     * @return tên sinh viên
     */
    public String getName() {
        return name;
    }

    /**
     * Cập nhật tên sinh viên.
     * @param name tên sinh viên
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Lấy học kỳ.
     * @return học kỳ
     */
    public int getSemester() {
        return semester;
    }

    /**
     * Cập nhật học kỳ.
     * @param semester học kỳ
     */
    public void setSemester(int semester) {
        this.semester = semester;
    }

    /**
     * Lấy tên môn học đang được lưu.
     * @return tên môn học của bản ghi
     */
    public String getCourse() {
        return course;
    }

    /**
     * Cập nhật môn học.
     * @param course môn học
     */
    public void setCourse(String course) {
        this.course = course;
    }

}
