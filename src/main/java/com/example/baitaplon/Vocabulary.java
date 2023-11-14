package com.example.baitaplon;

public class Vocabulary extends Word {
    private String spellTranscribePhonetically; // phien am
    private String synonymous; // dong nghia
    private String antonyms; // trai nghia

//    public Vocabulary() {
//
//    }

    public Vocabulary(String englishVocabulary, String vietnameseVocabulary,
                      String spellTranscribePhonetically, String synonymous, String antonyms) {
        super(englishVocabulary, vietnameseVocabulary);
        this.spellTranscribePhonetically = spellTranscribePhonetically;
        this.synonymous = synonymous;
        this.antonyms = antonyms;
    }

    public String getSpellTranscribePhonetically() {
        return spellTranscribePhonetically;
    }

    public void setSpellTranscribePhonetically(String spellTranscribePhonetically) {
        this.spellTranscribePhonetically = spellTranscribePhonetically;
    }

    public String getSynonymous() {
        return synonymous;
    }

    public void setSynonymous(String synonymous) {
        this.synonymous = synonymous;
    }

    public String getAntonyms() {
        return antonyms;
    }

    public void setAntonyms(String antonyms) {
        this.antonyms = antonyms;
    }


}
