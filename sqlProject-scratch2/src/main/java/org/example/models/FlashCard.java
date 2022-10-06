package org.example.models;

public class FlashCard {

    int id;
    String question;
    String answer;
    int creator_id;

    FlashCard(){
    }

    public FlashCard(int id, String question, String answer, int creator_id) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.creator_id = creator_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(int creator_id) {
        this.creator_id = creator_id;
    }

    @Override
    public String toString() {
        return "FlashCard{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", creator_id=" + creator_id +
                '}';
    }
}
