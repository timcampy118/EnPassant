package com.database.csce310project;

public class Game
{
    public Integer getId() { return id; } //gameid

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFen(){
        return Fen;
    }

    public void setFen(String Fen) {
        this.Fen = Fen;
    }

    private Integer id;
    private String name;
    private String Fen;
}
