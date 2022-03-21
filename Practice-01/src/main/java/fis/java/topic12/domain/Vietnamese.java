package fis.java.topic12.domain;

public class Vietnamese implements Language{
    @Override
    public String getGreeting() {
        return "Xin chao";
    }

    @Override
    public String getBye() {
        return "Tam biet";
    }
}
