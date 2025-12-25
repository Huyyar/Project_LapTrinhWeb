package vn.edu.hcmuaf.fit.project_ltweb.model;

public abstract class PageInfo {
    String name, title, content;
    String[] css, js;

    public PageInfo(String name, String title, String content, String[] css, String[] js) {
        this.name = name;
        this.title = title;
        this.content = content;
        this.css = css;
        this.js = js;
    }

    public PageInfo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String[] getCss() {
        return css;
    }

    public void setCss(String[] css) {
        this.css = css;
    }

    public String[] getJs() {
        return js;
    }

    public void setJs(String[] js) {
        this.js = js;
    }
}
