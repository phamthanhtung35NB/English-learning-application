package com.example.baitaplon;

public class Vocabulary {
    private int id;
    private String englishVocabulary; // tu tieng anh
    private String vietnameseVocabulary; // tu tieng viet
    private String spellTranscribePhonetically; // phien am
    private String synonymous; // dong nghia
    private String antonyms; // trai nghia

    public Vocabulary() {

    }

    public Vocabulary(int id, String englishVocabulary, String vietnameseVocabulary,
                      String spellTranscribePhonetically, String synonymous, String antonyms) {
        super();
        this.id = id;
        this.englishVocabulary = englishVocabulary;
        this.vietnameseVocabulary = vietnameseVocabulary;
        this.spellTranscribePhonetically = spellTranscribePhonetically;
        this.synonymous = synonymous;
        this.antonyms = antonyms;
    }

    /*
    get
     */
    public int getId() {
        return id;
    }

    public String getEnglishVocabulary() {
        return englishVocabulary;
    }

    public String getVietnameseVocabulary() {
        return vietnameseVocabulary;
    }

    public String getSpellTranscribePhonetically() {
        return spellTranscribePhonetically;
    }

    public String getAntonyms() {
        return antonyms;
    }

    public String getSynonymous() {
        return synonymous;
    }

    /*
    set
     */
    public void setId(int id) {
        this.id = id;
    }

    public void setEnglishVocabulary(String englishVocabulary) {
        this.englishVocabulary = englishVocabulary;
    }

    public void setVietnameseVocabulary(String vietnameseVocabulary) {
        this.vietnameseVocabulary = vietnameseVocabulary;
    }


    public void setSpellTranscribePhonetically(String spellTranscribePhonetically) {
        this.spellTranscribePhonetically = spellTranscribePhonetically;
    }

    public void setSynonymous(String synonymous) {
        this.synonymous = synonymous;
    }

    public void setAntonyms(String antonyms) {
        this.antonyms = antonyms;
    }

}
