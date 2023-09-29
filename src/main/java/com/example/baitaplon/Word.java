package com.example.baitaplon;

public class Word {
    String word_target; //tieng anh
    String word_explain; //nghia tieng viet


    public Word(String word_target, String word_explain) {
        this.word_target = word_target;
        this.word_explain = word_explain;
    }

    public String getWord() {
        return word_target;
    }
    public String getDef() {
        return word_explain;
    }

    public void setDef(String word_explain) {
        this.word_explain = word_explain;
    }
    public void setWord(String word_target) {
        this.word_target = word_target;
    }

}
