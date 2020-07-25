package models;

public class News {
    private int id;
    private String news,department;

    public News(String news, String department){
        this.news=news;
        this.department=department;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNews(String news) {
        this.news = news;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public String getNews() {
        return news;
    }

    public String getDepartment() {
        return department;
    }
}
