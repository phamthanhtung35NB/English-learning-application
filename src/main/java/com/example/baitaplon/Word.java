package com.example.baitaplon;

public class Word {
    protected String word_target; //tieng anh
    protected String word_explain; //nghia tieng viet

    public Word(String word_target, String word_explain) {
        this.word_target = word_target;
        this.word_explain = word_explain;
//        .toLowerCase();
    }

    public String getWord_target() {
        return word_target;
    }
    public String getWord_explain() {
        return word_explain;
    }

    public void setWord_explain(String word_explain) {
        this.word_explain = word_explain;
    }
    public void setWord_target(String word_target) {
        this.word_target = word_target;
    }

}
