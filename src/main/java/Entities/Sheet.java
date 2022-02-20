package Entities;

public class Sheet {
    private String name;
    private String post;
    private String date;
    private String time;
    private String reason;

    public Sheet(String name, String post, String date, String time, String reason){
        this.name = name;
        this.post = post;
        this.date = date;
        this.time = time;
        this.reason = reason;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public void setPost(String post){
        this.post = post;
    }
    public String getPost(){
        return post;
    }

    public void setDate(String date){
        this.date = date;
    }
    public String getDate(){
        return date;
    }

    public void setTime(String time){
        this.time = time;
    }
    public String getTime(){
        return time;
    }

    public void setReason(String reason){
        this.name = reason;
    }
    public String getReason(){
        return reason;
    }
}