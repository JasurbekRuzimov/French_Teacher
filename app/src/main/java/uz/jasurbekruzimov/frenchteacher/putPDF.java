package uz.jasurbekruzimov.frenchteacher;

public class putPDF {
    String name;
    String url;

    public putPDF(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "putPDF{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

}
