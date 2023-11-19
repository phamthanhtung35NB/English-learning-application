package com.example.baitaplon;

public class WordSQL extends Word{
    protected int ID;
    protected String html;
    protected String pronounce;

    public WordSQL(int id, String word_target, String word_explain, String html, String pronounce) {
        super(word_target, word_explain);
        this.ID = ID;
        this.html = html;
        this.pronounce = pronounce;
    }
    //getter
    public int getID() {
        return ID;
    }
    public String getHtml() {
        return html;
    }
    public String getPronounce() {
        return pronounce;
    }
    //setter
    public void setID(int id) {
        this.ID = ID;
    }
    public void setHtml(String html) {
        this.html = html;
    }
    public void setPronounce(String pronounce) {
        this.pronounce = pronounce;
    }

}
