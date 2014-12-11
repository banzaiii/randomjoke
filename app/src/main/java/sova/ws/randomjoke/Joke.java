package sova.ws.randomjoke;

/**
 * Created by ololo on 12/11/14.
 */
public class Joke {
    public String content;

    @Override
    public String toString()
    {
        return this.content;
    }
    public Joke(String content) {
        this.content = content;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
