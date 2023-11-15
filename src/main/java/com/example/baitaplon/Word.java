package com.example.baitaplon;

public class Word {
<<<<<<< HEAD
    protected String word_target; //tieng anh
    protected String word_explain; //nghia tieng viet
=======
    String word_target; //tieng anh
    String word_explain; //nghia tieng viet
>>>>>>> b953160e984bee9caeee7297ae341bdd0ae7a07f


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
