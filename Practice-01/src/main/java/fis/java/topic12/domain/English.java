package fis.java.topic12.domain;

public class English implements Language{
    @Override
    public String getGreeting() {
        return "Hello";
    }

    @Override
    public String getBye() {
        return "Good bye";
    }
}
